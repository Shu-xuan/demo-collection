package com.hhy.mybatis.mapper;

import com.hhy.mybatis.annotation.Param;
import com.hhy.mybatis.entity.po.User;

/**
 * <p>
 * 描述: User表数据库操作
 * </p>
 * <p>版权所有: &copy;古月HYuan</p>
 *
 * @Author 枢璇
 * @Date 2025/3/18 10:24
 */
public interface UserMapper {
    User selectUserById(@Param("id") Long id);
}
