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
     * @Author Derry Lin
     * @Description 获取区域列表
     * @Date 下午3:00 2020/9/7
     * @Param []
     * @return java.util.List<edu.hust.dao.dto.Area>
     **/
    List<Area> getAreaInfoList();

    /**
     * @Author Derry Lin
     * @Description 根据id获取区域
     * @Date 下午3:01 2020/9/7
     * @Param [id]
     * @return edu.hust.dao.dto.Area
     **/
    Area getAreaInfoById(String id);

    /**
     * @Author Derry Lin
     * @Description 根据title获取区域
     * @Date 下午3:01 2020/9/7
     * @Param [title]
     * @return edu.hust.dao.dto.Area
     **/
    Area getAreaInfoByTitle(String title);

    /**
     * @Author Derry Lin
     * @Description 添加区域
     * @Date 下午3:01 2020/9/7
     * @Param [area]
     * @return void
     **/
    void addArea(Area area);

    /**
     * @Author Derry Lin
     * @Description 批量添加区域
     * @Date 下午3:01 2020/9/7
     * @Param [areaList]
     * @return void
     **/
    void addAreaList(List<Area> areaList);

    /**
     * @Author Derry Lin
     * @Description 更新区域信息
     * @Date 下午3:01 2020/9/7
     * @Param [area]
     * @return void
     **/
    void updateArea(Area area);

    /**
     * @Author Derry Lin
     * @Description 根据区域id删除区域
     * @Date 下午3:01 2020/9/7
     * @Param [id]
     * @return void
     **/
    void deleteAreaById(String id);

    /**
     * @Author Derry Lin
     * @Description 根据title删除区域
     * @Date 下午3:01 2020/9/7
     * @Param [title]
     * @return void
     **/
    void deleteAreaByTitle(String title);

    /**
     * @Author Derry Lin
     * @Description 删除所有区域
     * @Date 下午3:11 2020/9/7
     * @Param []
     * @return void
     **/
    void deleteAllArea();

}
