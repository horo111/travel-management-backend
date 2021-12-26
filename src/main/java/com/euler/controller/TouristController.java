package com.euler.controller;

import com.euler.vo.TouristVO;
import com.euler.bo.BaseResponse;
import com.euler.domain.Tourist;
import com.euler.exception.IllegalRequestParamException;
import com.euler.service.TouristService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.euler.constant.Constant.SESSION_KEY_USER;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/24
 */
@Api(tags="游客管理")
@RestController
@RequestMapping("/tourist")
public class TouristController {
    @Resource
    TouristService touristService;

    @ApiOperation("获取所有游客信息")
    @GetMapping("/allTouristInfo")
    BaseResponse<List<TouristVO>> getAllTouristInfo(){
        return new BaseResponse(HttpServletResponse.SC_OK,null,touristService.getAllTourists());
    }

    @ApiOperation("删除游客信息")
    @DeleteMapping("/deleteTourist")
    BaseResponse<Void> deleteTourist(Integer touristId) {
        touristService.deleteTourist(touristId);
        return new BaseResponse<>(HttpServletResponse.SC_OK);
    }

    @ApiOperation("修改游客信息")
    @PostMapping("/modifyTourist")
    BaseResponse<TouristVO> modifyTourist(Tourist tourist,HttpServletRequest httpServletRequest){
        return new BaseResponse(HttpServletResponse.SC_OK,null,touristService.modifyTourist(tourist));
    }

    @ApiOperation("添加游客信息")
    @PostMapping("/addTourist")
    BaseResponse<TouristVO> addTourist(Tourist tourist){
        return new BaseResponse(HttpServletResponse.SC_OK,null,touristService.addTourist(tourist));
    }


}
