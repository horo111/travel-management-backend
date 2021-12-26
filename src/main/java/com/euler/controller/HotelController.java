package com.euler.controller;

import com.euler.bo.BaseResponse;
import com.euler.domain.Hotel;
import com.euler.service.HotelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
@Api(tags="酒店管理")
@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Resource
    HotelService hotelService;

    @ApiOperation("获取酒店名称")
    @GetMapping("/hotelName")
    BaseResponse<Map<Integer,String>> getHotelName(){
        return new BaseResponse(HttpServletResponse.SC_OK,null,hotelService.getHotelName());
    }

    @ApiOperation("获取所有酒店信息")
    @GetMapping("/allHotelInfo")
    BaseResponse<List<Hotel>> getAllHotelInfo(){
        return new BaseResponse(HttpServletResponse.SC_OK,null,hotelService.getAllHotels());
    }

    @ApiOperation("删除酒店信息")
    @PostMapping("/deleteHotel")
    BaseResponse<Void> deleteHotel(Integer hotelId, HttpServletRequest httpServletRequest) {
        Integer userId = (Integer) httpServletRequest.getSession(false).getAttribute(SESSION_KEY_USER);
        hotelService.deleteHotel(hotelId);
        return new BaseResponse<>(HttpServletResponse.SC_OK);
    }

    @ApiOperation("修改酒店信息")
    @PostMapping("/modifyHotel")
    BaseResponse<Hotel> modifyHotel(Hotel hotel){
        return new BaseResponse(HttpServletResponse.SC_OK,null,hotelService.modifyHotel(hotel));
    }

    @ApiOperation("添加酒店信息")
    @PostMapping("/addHotel")
    BaseResponse<Hotel> addHotel(Hotel hotel){
        return new BaseResponse(HttpServletResponse.SC_OK,null,hotelService.addHotel(hotel));
    }

}
