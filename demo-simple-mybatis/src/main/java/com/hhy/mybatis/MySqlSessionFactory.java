package com.hhy.mybatis;

import com.hhy.mybatis.annotation.FieldName;
import com.hhy.mybatis.annotation.Param;
import com.hhy.mybatis.annotation.TableName;
import com.hhy.mybatis.mapper.UserMapper;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * <p>
 * 描述: 会话工厂
 * </p>
 * <p>版权所有: &copy;古月HYuan</p>
 *
 * @Author 枢璇
 * @Date 2025/3/18 10:35
 */
public class MySqlSessionFactory {
    private static String DRIVERCLASS;
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    private static Connection connection;

    static {
        try {
            InputStream resource = Main.class.getClassLoader().getResourceAsStream("simple-mybatis.properties");
            Properties properties = new Properties();
            properties.load(resource);
            DRIVERCLASS = properties.getProperty("mysql.driver");
            URL = properties.getProperty("mysql.url");
            USER = properties.getProperty("mysql.username");
            PASSWORD = properties.getProperty("mysql.password");
            Class.forName(DRIVERCLASS);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public <T> T getMapper(Class<T> mapperClass) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{mapperClass},
                new MapperInvocationHandler());
    }


    static class MapperInvocationHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // 处理select语句
            if (method.getName().startsWith("select")) {
                return invokeSelect(proxy, method, args);
            }
            return null;
        }

        @SneakyThrows
        /**
         * select 查询逻辑
         */
        private Object invokeSelect(Object proxy, Method method, Object[] args) {
            String sql = createSelectSQL(method);
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            try {
                statement = connection.prepareStatement(sql);
                // 填充参数
                for (int i = 0; i < args.length; i++) {
                    Object arg = args[i];
                    if (arg instanceof Long) {
                        statement.setLong(i + 1, (long) arg);
                    } else if (arg instanceof String) {
                        statement.setString(i + 1, arg.toString());
                    }
                }
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return parseResult(resultSet, method.getReturnType());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

        private Object parseResult(ResultSet resultSet, Class<?> returnType) throws Exception {
            // 创建最终返回对象 result
            Constructor<?> constructor = returnType.getConstructor();
            Object result = constructor.newInstance();
            Field[] fields = returnType.getDeclaredFields();
            Arrays.stream(fields).forEach(field -> {
                Object fieldValueContainer = null;
                FieldName fieldName = field.getAnnotation(FieldName.class);
                if (fieldName == null) {
                    throw new IllegalArgumentException("无法获取字段，请为DO类属性加上@FieldName注解并映射表字段名");
                }
                String column = fieldName.value();
                try {
                    if (field.getType() == Long.class) {
                        fieldValueContainer = resultSet.getLong(column);
                    } else if (field.getType() == String.class) {
                        fieldValueContainer = resultSet.getString(column);
                    }
                    // 将字段设置为可访问，可为私有字段赋值
                    field.setAccessible(true);
                    field.set(result, fieldValueContainer);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            return result;
        }

        private String createSelectSQL(Method method) {
            String cols2Query = getSelectCols(method.getReturnType());
            String tableName = getTableName(method.getReturnType());
            String condition = getSelectCondition(method);
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT ")
                    .append(cols2Query)
                    .append(" FROM ")
                    .append(tableName)
                    .append(" WHERE ")
                    .append(condition);
            return builder.toString();
        }

        private String getTableName(Class<?> returnType) {
            TableName tableName = returnType.getAnnotation(TableName.class);
            if (tableName == null) {
                throw new IllegalArgumentException("无法获取表名，请为DO类加上@TableName注解并映射表名");
            }
            return tableName.value();
        }

        private String getSelectCols(Class<?> returnType) {
            Field[] fields = returnType.getDeclaredFields();
            List<String> cols = new ArrayList<>();
            Arrays.stream(fields).forEach(field -> {
                FieldName fieldName = field.getAnnotation(FieldName.class);
                if (fieldName == null) {
                    throw new IllegalArgumentException("无法获取字段，请为DO类属性加上@FieldName注解并映射表字段名");
                }
                String column = fieldName.value();
                cols.add(column);
            });
            return String.join(",", cols);
        }

        private String getSelectCondition(Method method) {
            return Arrays.stream(method.getParameters()).map(parameter -> {
                Param param = parameter.getAnnotation(Param.class);
                if (param == null) {
                    throw new IllegalArgumentException("无法获取查询字段，请为方法参数加上@Param注解并映射字段名");
                }
                return param.value() + " = ?";
            }).collect(Collectors.joining(" AND "));
        }

    }

}
