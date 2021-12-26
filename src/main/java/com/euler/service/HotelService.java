package com.euler.service;

import com.euler.domain.Hotel;

import java.util.List;
import java.util.Map;
/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/24
 */
public interface HotelService {
    Map<Integer,String> getHotelName();

    List<Hotel> getAllHotels();

    void deleteHotel(Integer hotelId);

    Hotel modifyHotel(Hotel hotel);

    Hotel addHotel(Hotel hotel);
}
