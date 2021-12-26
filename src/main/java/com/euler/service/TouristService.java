package com.euler.service;

import com.euler.vo.TouristVO;
import com.euler.domain.Tourist;

import java.util.List;
/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/24
 */
public interface TouristService {
    List<TouristVO> getAllTourists();

    void deleteTourist(Integer touristId);

    TouristVO modifyTourist(Tourist tourist);

    TouristVO addTourist(Tourist tourist);
}
