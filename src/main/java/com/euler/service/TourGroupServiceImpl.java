package com.euler.service;

import com.euler.domain.Hotel;
import com.euler.domain.TourGroup;
import com.euler.domain.TourRoute;
import com.euler.repository.HotelRepository;
import com.euler.repository.TourGroupRepository;
import com.euler.repository.TourRouteRepository;
import com.euler.vo.TourGroupVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/24
 */
@Service
public class TourGroupServiceImpl implements TourGroupService {
    @Resource
    TourGroupRepository tourGroupRepository;

    @Resource
    HotelRepository hotelRepository;

    @Resource
    TourRouteRepository tourRouteRepository;

    @Override
    public Map<Integer, String> getTourGroupName() {
        Map<Integer, String> groupNameMap = new HashMap<>();
        tourGroupRepository.findAll().stream().forEach(tourGroup ->
                groupNameMap.put(tourGroup.getTourGroupId(), tourGroup.getTourGroupName())
        );
        return groupNameMap;
    }

    @Override
    public List<TourGroupVO> getAllTourGroups(){
        List<TourGroup> tourGroupList=tourGroupRepository.findAll();
        List<Hotel> hotelList=hotelRepository.findAll();
        List<TourRoute> tourRouteList=tourRouteRepository.findAll();
        return tourGroupList.stream().map(tourGroup ->
                TourGroupVO.fromTourGroupAndHotelAndTourRoute(tourGroup,hotelRepository.findByHotelId(tourGroup.getHotelId()),tourRouteRepository.findByTourRouteId(tourGroup.getTourRouteId()))
        ).collect(Collectors.toList());
    }

    @Override
    public void deleteTourGroup(Integer tourGroupId){
        TourGroup tourGroup=tourGroupRepository.findByTourGroupId(tourGroupId);
        if(tourGroup==null){
            throw new IllegalArgumentException("已添加的对应旅行团不存在," + "tourGroupId:" + tourGroupId);
        }
        tourGroupRepository.delete(tourGroup);
    }

    @Override
    public TourGroupVO addTourGroup(TourGroup tourGroup){
        TourGroup tourGroup1=tourGroupRepository.saveAndFlush(tourGroup);
        return TourGroupVO.fromTourGroupAndHotelAndTourRoute(tourGroup1,hotelRepository.findByHotelId(tourGroup1.getHotelId()),tourRouteRepository.findByTourRouteId(tourGroup1.getTourRouteId()));
    }

    @Override
    public TourGroupVO modifyTourGroup(TourGroup tourGroup){
        TourGroup tourGroupInDB=tourGroupRepository.findByTourGroupId(tourGroup.getTourGroupId());
        if(tourGroupInDB==null){
            throw new IllegalArgumentException("要修改的对应旅行团信息不存在" );
        }
        else{
            TourGroup tourGroup1=tourGroupRepository.saveAndFlush(tourGroup);
            return TourGroupVO.fromTourGroupAndHotelAndTourRoute(tourGroup1,hotelRepository.findByHotelId(tourGroup1.getHotelId()),tourRouteRepository.findByTourRouteId(tourGroup1.getTourRouteId()));
        }
    }
}
