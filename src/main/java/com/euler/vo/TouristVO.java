package com.euler.vo;

import com.euler.domain.TourGroup;
import com.euler.domain.Tourist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TouristVO {
    private Integer touristId;
    private String touristName;
    private String touristSex;
    private Integer touristAge;
    private String touristIdNumber;
    private String touristPhone;
    private String tourGroupName;

    public static TouristVO fromTouristAndTourGroup(Tourist tourist, TourGroup tourGroup){
        return TouristVO.builder()
                .touristId(tourist.getTouristId())
                .touristName(tourist.getTouristName())
                .touristSex(tourist.getTouristSex())
                .touristAge(tourist.getTouristAge())
                .touristIdNumber(tourist.getTouristIdNumber())
                .touristPhone(tourist.getTouristPhone())
                .tourGroupName(tourGroup.getTourGroupName())
                .build();
    }
}
