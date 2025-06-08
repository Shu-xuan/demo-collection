package com.hhy.mybatis;

import com.hhy.mybatis.mapper.UserMapper;

public class Main {

    public static void main(String[] args) {
        MySqlSessionFactory sqlSessionFactory = new MySqlSessionFactory();
        UserMapper mapper = sqlSessionFactory.getMapper(UserMapper.class);
        System.out.println(mapper.selectUserById(1012L));
    }

}
