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
@Table(name="tourist")
@NoArgsConstructor
@AllArgsConstructor
public class Tourist {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer touristId;
    private Integer tourGroupId;
    private String touristName;
    private String touristSex;
    private Integer touristAge;
    private String touristIdNumber;
    private String touristPhone;
}
