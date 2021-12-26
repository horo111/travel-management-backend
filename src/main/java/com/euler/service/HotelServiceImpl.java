package com.euler.service;

import com.euler.domain.Hotel;
import com.euler.repository.HotelRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/24
 */
@Service
public class HotelServiceImpl implements HotelService{
    @Resource
    HotelRepository hotelRepository;

    @Override
    public Map<Integer,String> getHotelName(){
        Map<Integer,String> hotelNameMap=new HashMap<>();
        hotelRepository.findAll().stream().forEach(hotel ->
                hotelNameMap.put(hotel.getHotelId(),hotel.getHotelName())
        );
        return hotelNameMap;
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public void deleteHotel(Integer hotelId){
        Hotel hotel=hotelRepository.findByHotelId(hotelId);
        if(hotel==null){
            throw new IllegalArgumentException("已添加的对应酒店信息不存在," + "hotelId:" + hotelId);
        }
        hotelRepository.delete(hotel);
    }

    @Override
    public Hotel addHotel(Hotel hotel){
        Hotel hotel1=hotelRepository.saveAndFlush(hotel);
        return hotel1;
    }

    @Override
    public Hotel modifyHotel(Hotel hotel){
        Hotel hotelInDB=hotelRepository.findByHotelId(hotel.getHotelId());
        if(hotelInDB==null){
            throw new IllegalArgumentException("要修改的对应酒店信息不存在" );
        }
        else{
            Hotel hotel1=hotelRepository.saveAndFlush(hotel);
            return hotel1;
        }
    }
}
