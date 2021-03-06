package com.euler.service;

import com.euler.bo.BaseResponse;
import com.euler.domain.*;
import com.euler.exception.IllegalRequestParamException;
import com.euler.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.euler.constant.Constant.SESSION_KEY_USER;

/**
 * 用户服务实现类
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/14
 */
@Service
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public BaseResponse<List<String>> login(String username, String password, HttpServletRequest httpServletRequest) {
        if (Optional.ofNullable(username).orElse("").isEmpty() ||
                Optional.ofNullable(password).orElse("").isEmpty()) {
            throw new IllegalRequestParamException("用户登录参数缺失");
        }
        User userInDB = userRepository.findUserByUsernameAndPassword(username, password);
        if (null != userInDB) {
            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            userInDB.setLastLoginTime(dateFormat.format(date));
            userRepository.saveAndFlush(userInDB);
            List<String> stringList=new LinkedList<>();
            stringList.add(userInDB.getUsername());
            stringList.add(userInDB.getLastLoginTime());
            httpServletRequest.getSession().setAttribute(SESSION_KEY_USER, userInDB.getUserId());
            return new BaseResponse(HttpServletResponse.SC_OK, "登录成功", stringList);
        }

        return new BaseResponse(HttpServletResponse.SC_FORBIDDEN, "用户名或密码错误");
    }
}
