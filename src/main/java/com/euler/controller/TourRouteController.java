package com.euler.controller;

import com.euler.domain.Attraction;
import com.euler.vo.AttractionVO;
import com.euler.bo.BaseResponse;
import com.euler.domain.TourRoute;
import com.euler.service.TourRouteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/25
 */
@Api(tags="旅行线路管理")
@RestController
@RequestMapping("/tourRoute")
public class TourRouteController {
    @Resource
    TourRouteService tourRouteService;

    @ApiOperation("获取线路名称")
    @GetMapping("/tourRouteName")
    BaseResponse<Map<Integer,String>> getTourRouteName(){
        return new BaseResponse(HttpServletResponse.SC_OK,null,tourRouteService.getTourRouteName());
    }

    @ApiOperation("获取所有线路")
    @GetMapping("/getAllTourRoute")
    BaseResponse<List<TourRoute>> getAllTourRoute(){
        return new BaseResponse(HttpServletResponse.SC_OK,null,tourRouteService.getAllTourRoute());
    }

    @ApiOperation("根据线路id获取线路景点信息")
    @GetMapping("/getAttractionVO")
    BaseResponse<List<AttractionVO>> getAttractionVO(Integer tourRouteId){
        return new BaseResponse(HttpServletResponse.SC_OK,null,tourRouteService.getAttractionVO(tourRouteId));
    }

    @ApiOperation("根据线路id获取线路以外景点信息")
    @GetMapping("/getOtherAttractionVO")
    BaseResponse<List<AttractionVO>> getOtherAttractionVO(Integer tourRouteId){
        return new BaseResponse(HttpServletResponse.SC_OK,null,tourRouteService.getOtherAttractionVO(tourRouteId));
    }

    @ApiOperation("删除线路")
    @DeleteMapping("/deleteTourRoute")
    BaseResponse<Void> deleteTourRoute(Integer tourRouteId){
        tourRouteService.deleteTourRoute(tourRouteId);
        return new BaseResponse<>(HttpServletResponse.SC_OK);
    }


    @ApiOperation("修改线路信息")
    @PostMapping("/modifyTourRoute")
    BaseResponse<TourRoute> modifyTourRoute(TourRoute tourRoute){
        return new BaseResponse(HttpServletResponse.SC_OK,null,tourRouteService.modifyTourRoute(tourRoute));
    }

    @ApiOperation("修改线路中景点信息")
    @PostMapping("/modifySequence")
    BaseResponse<Void> modifySequence(@RequestParam(value = "sequence" ,required=false)List<Integer> sequence){
        tourRouteService.modifySequence(sequence);
        return new BaseResponse<>(HttpServletResponse.SC_OK);
    }

    @ApiOperation("新增线路信息")
    @PostMapping("/addTourRoute")
    BaseResponse<TourRoute> addTourRoute(TourRoute tourRoute){
        return new BaseResponse(HttpServletResponse.SC_OK,null,tourRouteService.addTourRoute(tourRoute));
    }

    @ApiOperation("增加线路中的景点信息")
    @PostMapping("/addTourRouteAttraction")
    BaseResponse<Void> addTourRouteAttraction(Integer tourRouteId,@RequestParam(value = "attractionList" ,required=false)List<Integer> attractionList){
        tourRouteService.addTourRouteAttraction(tourRouteId,attractionList);
        return new BaseResponse<>(HttpServletResponse.SC_OK);
    }


}
