package com.euler.repository;

import com.euler.domain.RouteDisplay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/25
 */
public interface RouteDisplayRepository extends JpaRepository<RouteDisplay,Integer>{
    List<RouteDisplay> findByTourRouteId(Integer tourRouteId);
    RouteDisplay findByTourRouteIdAndAttractionId(Integer tourRouteId,Integer attractionId);
    void deleteByTourRouteId(Integer tourRouteId);
}
