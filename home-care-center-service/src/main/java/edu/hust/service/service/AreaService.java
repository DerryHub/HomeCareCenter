package edu.hust.service.service;

import edu.hust.dao.dto.Area;

import java.util.List;

/**
 * @author chain
 * @date 2020/9/8
 **/
public interface AreaService {


    List<Area> getAreaInfoList();


    Area getAreaInfoById(String id);


    Area getAreaInfoByTitle(String title);


    void addArea(Area area);


    void addAreaList(List<Area> areaList);


    void updateArea(Area area);


    void deleteAreaById(String id);


    void deleteAreaByTitle(String title);


    void deleteAllArea();
}
