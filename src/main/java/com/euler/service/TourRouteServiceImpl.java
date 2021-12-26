package com.euler.service;

import com.euler.vo.AttractionVO;
import com.euler.domain.TourRoute;
import com.euler.domain.RouteDisplay;
import com.euler.domain.Attraction;
import com.euler.domain.Tourist;
import com.euler.repository.RouteDisplayRepository;
import com.euler.repository.TourRouteRepository;
import com.euler.repository.AttractionRepository;
import com.euler.vo.AttractionVO;
import com.sun.xml.bind.v2.TODO;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 类描述
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/12/25
 */
@Service
public class TourRouteServiceImpl implements TourRouteService{
    @Resource
    TourRouteRepository tourRouteRepository;

    @Resource
    RouteDisplayRepository routeDisplayRepository;

    @Resource
    AttractionRepository attractionRepository;

    @Override
    public Map<Integer,String> getTourRouteName(){
        Map<Integer,String> tourRouteNameMap=new HashMap<>();
        tourRouteRepository.findAll().stream().forEach(tourRoute ->
                tourRouteNameMap.put(tourRoute.getTourRouteId(), tourRoute.getTourRouteName())
        );
        return tourRouteNameMap;
    }

    @Override
    public List<TourRoute> getAllTourRoute(){
        return tourRouteRepository.findAll();
    }

    @Override
    public List<AttractionVO> getAttractionVO(Integer tourRouteId){
        List<RouteDisplay> routeDisplayList= routeDisplayRepository.findByTourRouteId(tourRouteId);
        List<Attraction> attractionList=new LinkedList<>();
        Map<Integer,Integer> sequenceMap=new HashMap<>();
        routeDisplayList.stream().forEach(routeDisplay ->
                sequenceMap.put(routeDisplay.getSequence(),routeDisplay.getAttractionId())
        );
        sequenceMap.forEach((key,value)->
                attractionList.add(attractionRepository.findByAttractionId(value))
        );
        List<AttractionVO> attractionVOList=new LinkedList<>();
        attractionList.stream().forEach(attraction ->
                    attractionVOList.add(AttractionVO.fromAttraction(attraction))
        );
        return attractionVOList;
    }

    @Override
    public List<AttractionVO> getOtherAttractionVO(Integer tourRouteId){
        List<Attraction> attractionList=attractionRepository.findAll();
        List<AttractionVO> attractionVOList=new LinkedList<>();
        List<RouteDisplay> routeDisplayList=routeDisplayRepository.findByTourRouteId(tourRouteId);
        List<Integer> integerList=new LinkedList<>();
        routeDisplayList.stream().forEach(routeDisplay ->
                integerList.add(routeDisplay.getAttractionId())
        );
        attractionList.stream().forEach(attraction ->
                {
                    if(!integerList.contains(attraction.getAttractionId())){
                            attractionVOList.add(AttractionVO.fromAttraction(attraction));
                    }
                }
        );
        return attractionVOList;
    }

    @Override
    public void deleteTourRoute(Integer tourRouteId){
        TourRoute tourRoute=tourRouteRepository.findByTourRouteId(tourRouteId);
        if(tourRoute==null){
            throw new IllegalArgumentException("已添加的对应旅行路线不存在," + "tourRouteId:" + tourRouteId);
        }
        tourRouteRepository.delete(tourRoute);
        routeDisplayRepository.deleteByTourRouteId(tourRouteId);
    }

    @Override
    public TourRoute modifyTourRoute(TourRoute tourRoute){
        TourRoute tourRouteInDB=tourRouteRepository.findByTourRouteId(tourRoute.getTourRouteId());
        if(tourRouteInDB==null){
            throw new IllegalArgumentException("要修改的对应旅行线路信息不存在" );
        }
        else{
            TourRoute tourRoute1=tourRouteRepository.saveAndFlush((tourRoute));
            return tourRoute1;
        }
    }

    @Override
    public void modifySequence(List<Integer> sequence){
        List<Integer> sequence1=sequence.subList(1,sequence.size());
        sequence1.stream().forEach(integer->
                {
                    RouteDisplay routeDisplay=routeDisplayRepository.findByTourRouteIdAndAttractionId(sequence.get(0), integer);
                    if(routeDisplay==null){
                        routeDisplayRepository.saveAndFlush(new RouteDisplay(null,sequence.get(0),integer,sequence1.indexOf(integer)+1));
                    }
                    else{
                        routeDisplay.setSequence(sequence1.indexOf(integer)+1);
                        routeDisplayRepository.saveAndFlush(routeDisplay);
                    }
                }
        );
    }

    @Override
    public TourRoute addTourRoute(TourRoute tourRoute){
        TourRoute tourRoute1=tourRouteRepository.saveAndFlush(tourRoute);
        return tourRoute1;
    }

    @Override
    public void addTourRouteAttraction(Integer tourRouteId,List<Integer> attractionList){
        attractionList.stream().forEach(integer ->
                {
                    routeDisplayRepository.saveAndFlush(new RouteDisplay(null,tourRouteId,integer,attractionList.indexOf(integer)+1));
                }
        );
    }
}
