package com.euler.repository;

import com.euler.domain.TourRoute;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/25
 */
public interface TourRouteRepository extends JpaRepository<TourRoute,Integer>{
    TourRoute findByTourRouteId(Integer tourRouteId);
}
