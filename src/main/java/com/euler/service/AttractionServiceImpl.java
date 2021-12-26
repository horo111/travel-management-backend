package com.euler.service;

import com.euler.domain.Attraction;
import com.euler.repository.AttractionRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/24
 */
@Service
public class AttractionServiceImpl implements AttractionService{
    @Resource
    AttractionRepository attractionRepository;

    @Override
    public List<Attraction> getAllAttractions(){
        return attractionRepository.findAll();
    }

    @Override
    public void deleteAttraction(Integer attractionId){
        Attraction attraction=attractionRepository.findByAttractionId(attractionId);
        if(attraction==null){
            throw new IllegalArgumentException("已添加的对应景点不存在," + "attractionId:" + attractionId);
        }
        attractionRepository.delete(attraction);
    }

    @Override
    public Attraction addAttraction(Attraction attraction){
        Attraction attraction1=attractionRepository.saveAndFlush(attraction);
        return attraction1;
    }

    @Override
    public Attraction modifyAttraction(Attraction attraction){
        Attraction attractionInDB=attractionRepository.findByAttractionId(attraction.getAttractionId());
        if(attractionInDB==null){
            throw new IllegalArgumentException("要修改的对应景点信息不存在" );
        }
        else{
            Attraction attraction1=attractionRepository.saveAndFlush(attraction);
            return attraction1;
        }
    }

    @Override
    public Map<Integer,String> getAttractionName(){
        Map<Integer,String> attractionNameMap=new HashMap<>();
        attractionRepository.findAll().stream().forEach(attraction ->
                attractionNameMap.put(attraction.getAttractionId(),attraction.getAttractionName())
        );
        return attractionNameMap;
    }

    @Override
    public Attraction getAttraction(Integer attractionId){
        return attractionRepository.findByAttractionId(attractionId);
    }
}
