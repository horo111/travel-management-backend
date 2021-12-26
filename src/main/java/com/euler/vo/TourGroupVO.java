package com.euler.vo;

import com.euler.domain.TourGroup;
import com.euler.domain.Hotel;
import com.euler.domain.TourRoute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/25
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourGroupVO {
    private Integer tourGroupId;
    private String tourGroupName;
    private Integer tourGroupTotal;
    private String tourGroupStatus;
    private String tourGroupPhone;
    private String tourGroupPrice;
    private Date startTime;
    private Date endTime;
    private String hotelName;
    private String tourRouteName;

    public static TourGroupVO fromTourGroupAndHotelAndTourRoute(TourGroup tourGroup,Hotel hotel,TourRoute tourRoute){
        return TourGroupVO.builder()
                .tourGroupId(tourGroup.getTourGroupId())
                .tourGroupName(tourGroup.getTourGroupName())
                .tourGroupTotal(tourGroup.getTourGroupTotal())
                .tourGroupStatus(tourGroup.getTourGroupStatus())
                .tourGroupPhone(tourGroup.getTourGroupPhone())
                .tourGroupPrice(tourGroup.getTourGroupPrice())
                .startTime(tourGroup.getStartTime())
                .endTime(tourGroup.getEndTime())
                .hotelName(hotel.getHotelName())
                .tourRouteName(tourRoute.getTourRouteName())
                .build();
    }
}
