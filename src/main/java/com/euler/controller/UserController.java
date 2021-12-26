package com.euler.controller;

import com.euler.bo.BaseResponse;
import com.euler.bo.LoginUserInfo;
import com.euler.domain.User;
import com.euler.repository.UserRepository;
import com.euler.service.UserService;
import com.euler.utils.IpLocationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.euler.constant.Constant.SESSION_KEY_USER;


/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/3/25
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
    final UserService userService;
    final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public BaseResponse login(@RequestParam String username, @RequestParam String password, HttpServletRequest httpServletRequest) {
        return userService.login(username, password, httpServletRequest);
    }

    @ApiOperation("获取登陆用户信息")
    @GetMapping("/info")
    public BaseResponse login(HttpServletRequest httpServletRequest) throws IOException {
        Integer userId = (Integer) httpServletRequest.getSession(false).getAttribute(SESSION_KEY_USER);
        String loginAddress=IpLocationUtil.getLocationByIp(httpServletRequest.getRemoteAddr());
        User userInDB=userRepository.findById(userId).orElse(null);
        if(userInDB==null){
            throw new RuntimeException("用户Id不存在");
        }
        return new BaseResponse(HttpServletResponse.SC_OK, null, LoginUserInfo.fromUser((userInDB),loginAddress));
    }
}
