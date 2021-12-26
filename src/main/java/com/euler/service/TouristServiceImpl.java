package com.euler.service;

import com.euler.domain.TourGroup;
import com.euler.repository.TourGroupRepository;
import com.euler.repository.TouristRepository;
import com.euler.vo.TouristVO;
import com.euler.domain.Tourist;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/24
 */
@Service
public class TouristServiceImpl implements TouristService {
    @Resource
    TouristRepository touristRepository;

    @Resource
    TourGroupRepository tourGroupRepository;

    @Override
    public List<TouristVO> getAllTourists() {
        List<TourGroup> tourGroupList = tourGroupRepository.findAll();
        List<Tourist> touristList = touristRepository.findAll();
        return touristList.stream().map(tourist ->
                TouristVO.fromTouristAndTourGroup(tourist, tourGroupRepository.findByTourGroupId(tourist.getTourGroupId()))
        ).collect(Collectors.toList());
    }

    @Override
    public void deleteTourist(Integer touristId) {
        Tourist tourist = touristRepository.findByTouristId(touristId);
        if (null == tourist) {
            throw new IllegalArgumentException("已添加的对应游客不存在," + "touristId:" + touristId);
        }
        touristRepository.delete(tourist);
    }

    @Override
    public TouristVO modifyTourist(Tourist tourist) {
        Tourist touristInDB = touristRepository.findByTouristId(tourist.getTouristId());
        if (touristInDB == null) {
            throw new IllegalArgumentException("要修改的对应游客不存在" );
        }
        else {
            Tourist tourist1=touristRepository.saveAndFlush(tourist);
            return TouristVO.fromTouristAndTourGroup(tourist1, tourGroupRepository.findByTourGroupId(tourist1.getTourGroupId()));
        }
    }

    @Override
    public TouristVO addTourist(Tourist tourist){
        Tourist tourist1=touristRepository.saveAndFlush(tourist);
        return TouristVO.fromTouristAndTourGroup(tourist1, tourGroupRepository.findByTourGroupId(tourist1.getTourGroupId()));
    }
}
