package com.euler.repository;
import com.euler.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/24
 */
public interface HotelRepository extends JpaRepository<Hotel,Integer>{
    Hotel findByHotelId(Integer hotelId);
}
