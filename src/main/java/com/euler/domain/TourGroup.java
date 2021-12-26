package com.euler.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/24
 */
@Data
@Entity
@Table(name="tour_group")
@NoArgsConstructor
@AllArgsConstructor
public class TourGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tourGroupId;
    private Integer hotelId;
    private Integer tourRouteId;
    private String tourGroupName;
    private Integer tourGroupTotal;
    private String tourGroupStatus;
    private String tourGroupPhone;
    private String tourGroupPrice;
    private Date startTime;
    private Date endTime;

}
