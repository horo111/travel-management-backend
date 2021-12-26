package com.euler.controller;

import com.euler.bo.BaseResponse;
import com.euler.domain.Attraction;
import com.euler.exception.IllegalRequestParamException;
import com.euler.service.AttractionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Attr;

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
@Api(tags="景点管理")
@RestController
@RequestMapping("/attraction")
public class AttractionController {
    @Resource
    AttractionService attractionService;

    @ApiOperation("获取所有景点信息")
    @GetMapping("/allAttractionInfo")
    BaseResponse<List<Attraction>> getAllAttractionInfo(){
        return new BaseResponse(HttpServletResponse.SC_OK,null,attractionService.getAllAttractions());
    }

    @ApiOperation("删除景点信息")
    @PostMapping("/deleteAttraction")
    BaseResponse<Void> deleteAttraction(Integer attractionId,HttpServletRequest httpServletRequest){
        Integer userId = (Integer) httpServletRequest.getSession(false).getAttribute(SESSION_KEY_USER);
        attractionService.deleteAttraction(attractionId);
        return new BaseResponse<>(HttpServletResponse.SC_OK);
    }

    @ApiOperation("添加景点信息")
    @PostMapping("/addAttraction")
    BaseResponse<Attraction> addAttraction(Attraction attraction){
        return new BaseResponse(HttpServletResponse.SC_OK,null,attractionService.addAttraction(attraction));
    }

    @ApiOperation("修改景点信息")
    @PostMapping("/modifyAttraction")
    BaseResponse<Attraction> modifyAttraction(Attraction attraction,HttpServletRequest httpServletRequest){
        return new BaseResponse(HttpServletResponse.SC_OK,null,attractionService.modifyAttraction(attraction));
    }

    @ApiOperation("获取所有景点名称")
    @GetMapping("/getAttractionName")
    BaseResponse<Map<Integer,String>> getAttractionName(){
        return new BaseResponse(HttpServletResponse.SC_OK,null,attractionService.getAttractionName());
    }

    @ApiOperation("根据景点id获取景点信息")
    @GetMapping("/getAttractionInfo")
    BaseResponse<Attraction> getAttractionInfo(Integer attractionId){
        return new BaseResponse(HttpServletResponse.SC_OK,null,attractionService.getAttraction(attractionId));
    }
}
