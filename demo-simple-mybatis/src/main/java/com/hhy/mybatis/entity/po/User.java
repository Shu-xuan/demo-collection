package com.hhy.mybatis.entity.po;

import com.hhy.mybatis.annotation.FieldName;
import com.hhy.mybatis.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 描述: 用户
 * </p>
 * <p>版权所有: &copy;古月HYuan</p>
 *
 * @Author 枢璇
 * @Date 2025/3/18 9:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {
    @FieldName("id")
    private Long id;

    @FieldName("user_name")
    private String userName;

    @FieldName("password")
    private String password;

    @FieldName("nick_name")
    private String nickName;
}
