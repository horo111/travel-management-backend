package com.euler.service;

import com.euler.domain.TourGroup;
import com.euler.vo.TourGroupVO;

import java.util.List;
import java.util.Map;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/24
 */
public interface TourGroupService {
    Map<Integer,String > getTourGroupName();

    List<TourGroupVO> getAllTourGroups();

    void deleteTourGroup(Integer tourGroupId);

    TourGroupVO addTourGroup(TourGroup tourGroup);

    TourGroupVO modifyTourGroup(TourGroup tourGroup);
}
