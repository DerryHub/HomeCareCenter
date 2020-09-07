package edu.hust.service.service;

import edu.hust.dao.dto.Area;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Area服务层接口
 * @author: Derry Lin
 * @create: 2020-09-07 10:23
 **/
public interface AreaService {

    /**
     * 获取区域列表
     * @return
     */
    List<Area> getAreaInfoList();

    /**
     * 根据id获取区域
     * @param id
     * @return
     */
    Area getAreaInfoById(String id);

    /**
     * 根据title获取区域
     * @param title
     * @return
     */
    Area getAreaInfoByTitle(String title);

    /**
     * 添加区域
     * @param area
     */
    void addArea(Area area);

    /**
     * 批量添加区域
     * @param areaList
     */
    void addAreaList(List<Area> areaList);

    /**
     * 更新区域信息
     * @param area
     */
    void updateArea(Area area);

    /**
     * 根据区域id删除区域
     * @param id
     */
    void deleteAreaById(String id);

    /**
     * 根据title删除区域
     * @param title
     */
    void deleteAreaByTitle(String title);

    /**
     * 删除所有区域
     */
    void deleteAllArea();

}
