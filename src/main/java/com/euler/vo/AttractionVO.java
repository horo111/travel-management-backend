package com.euler.vo;

import com.euler.domain.Attraction;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttractionVO {
    private Integer attractionId;
    private String attractionName;
    private String attractionCity;

    public static AttractionVO fromAttraction(Attraction attraction){
        return AttractionVO.builder()
                .attractionId(attraction.getAttractionId())
                .attractionName(attraction.getAttractionName())
                .attractionCity(attraction.getAttractionCity())
                .build();
    }
}
