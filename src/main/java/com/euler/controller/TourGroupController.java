package com.euler.controller;

import com.euler.bo.BaseResponse;
import com.euler.domain.TourGroup;
import com.euler.exception.IllegalRequestParamException;
import com.euler.service.TourGroupService;
import com.euler.vo.TourGroupVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static com.euler.constant.Constant.SESSION_KEY_USER;
/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/24
 */
@Api(tags="旅行团管理")
@RestController
@RequestMapping("/tourGroup")
public class TourGroupController {
    @Resource
    TourGroupService tourGroupService;

    @ApiOperation("获取旅行团名称")
    @GetMapping("/nameList")
    BaseResponse<Map<Integer,String >> getTourGroupName(){
        return new BaseResponse(HttpServletResponse.SC_OK,null,tourGroupService.getTourGroupName());
    }

    @ApiOperation("获取所有旅行团信息")
    @GetMapping("/allTourGroupInfo")
    BaseResponse<List<TourGroupVO>> getAllTourGroupInfo(){
        return new BaseResponse(HttpServletResponse.SC_OK,null,tourGroupService.getAllTourGroups());
    }

    @ApiOperation("删除旅行团信息")
    @PostMapping("/deleteTourGroup")
    BaseResponse<Void> deleteTourGroup(Integer tourGroupId,HttpServletRequest httpServletRequest){
        Integer userId = (Integer) httpServletRequest.getSession(false).getAttribute(SESSION_KEY_USER);
        tourGroupService.deleteTourGroup(tourGroupId);
        return new BaseResponse<>(HttpServletResponse.SC_OK);
    }

    @ApiOperation("添加旅行团信息")
    @PostMapping("/addTourGroup")
    BaseResponse<TourGroupVO> addTourGroup(TourGroup tourGroup){
        return new BaseResponse(HttpServletResponse.SC_OK,null,tourGroupService.addTourGroup(tourGroup));
    }

    @ApiOperation("修改旅行团信息")
    @PostMapping("/modifyTourGroup")
    BaseResponse<TourGroupVO> modifyTourGroup(TourGroup tourGroup){
        return new BaseResponse(HttpServletResponse.SC_OK,null,tourGroupService.modifyTourGroup(tourGroup));
    }
}
