package edu.hust.dao.dao;

import edu.hust.dao.dto.Area;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Area类数据库接口
 * @author: Derry Lin
 * @create: 2020-09-03 08:56
 **/
public interface AreaMapper {

    //查询所有区域信息
    List<Area> selectList();

    //根据区域id查找
    Area selectById(@Param("id") String id);

    //根据区域名查找区域
    Area selectByAreaTitle(@Param("area_title") String areaTitle);

    //添加区域
    int add(@Param("area") Area area);

    //批量添加区域
    int addBatch(@Param("area_list") List<Area> areaList);

    //更新区域
    int update(@Param("area") Area area);

    //根据区域id删除区域
    int deleteById(@Param("id") String id);

    //根据区域名删除
    int deleteByAreaTitle(@Param("area_title") String areaTitle);

}
