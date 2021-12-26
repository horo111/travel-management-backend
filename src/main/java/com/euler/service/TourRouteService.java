package com.euler.service;

import com.euler.vo.AttractionVO;
import com.euler.domain.Attraction;
import com.euler.domain.TourRoute;
import com.euler.vo.AttractionVO;
import io.swagger.models.auth.In;

import java.util.List;
import java.util.Map;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/25
 */
public interface TourRouteService {
    Map<Integer,String> getTourRouteName();

    List<TourRoute> getAllTourRoute();

    List<AttractionVO> getAttractionVO(Integer tourRouteId);

    List<AttractionVO> getOtherAttractionVO(Integer tourRouteId);

    void deleteTourRoute(Integer tourRouteId);

    TourRoute modifyTourRoute(TourRoute tourRoute);

    void modifySequence(List<Integer> sequence);

    TourRoute addTourRoute(TourRoute tourRoute);

    void addTourRouteAttraction(Integer tourRouteId,List<Integer> attractionList);
}
