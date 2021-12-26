package com.euler.bo;

import com.euler.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserInfo {
    private String username;
    private String lastLoginTime;
    private String loginAddress;

    public static LoginUserInfo fromUser(User user,String loginAddress){
        return LoginUserInfo.builder()
                .username(user.getUsername())
                .lastLoginTime(user.getLastLoginTime())
                .loginAddress(loginAddress)
                .build();
    }
}
