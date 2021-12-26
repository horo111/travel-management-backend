package com.euler.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/24
 */
@Data
@Entity
@Table(name="route_display")
@NoArgsConstructor
@AllArgsConstructor
public class RouteDisplay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer routeDisplayId;
    private Integer tourRouteId;
    private Integer attractionId;
    private Integer sequence;
}
