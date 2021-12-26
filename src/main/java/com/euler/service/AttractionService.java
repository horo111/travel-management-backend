package com.euler.service;

import com.euler.domain.Attraction;
import org.w3c.dom.Attr;

import java.util.List;
import java.util.Map;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/24
 */
public interface AttractionService {
    List<Attraction> getAllAttractions();

    void deleteAttraction(Integer attractionId);

    Attraction addAttraction(Attraction attraction);

    Attraction modifyAttraction(Attraction attraction);

    Map<Integer,String> getAttractionName();

    Attraction getAttraction(Integer attractionId);
}
