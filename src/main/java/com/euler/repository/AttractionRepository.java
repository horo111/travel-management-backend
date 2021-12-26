package com.euler.repository;

import com.euler.domain.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/24
 */
public interface AttractionRepository extends JpaRepository<Attraction, Integer>{
    Attraction findByAttractionId(Integer attractionId);
}
