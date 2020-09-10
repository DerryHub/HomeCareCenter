package edu.hust.dao.dao;

import edu.hust.dao.dto.Area;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Area类数据库接口
 * @author: Derry Lin
 * @create: 2020-09-03 08:56
 **/
@Mapper
public interface AreaMapper {

    /**
     * @Author Derry Lin
     * @Description 查询所有区域信息
     * @Date 下午3:19 2020/9/7
     * @Param []
     * @return java.util.List<edu.hust.dao.dto.Area>
     **/
    List<Area> selectList();

    /**
     * @Author Derry Lin
     * @Description 根据区域id查找
     * @Date 下午3:19 2020/9/7
     * @Param [id]
     * @return edu.hust.dao.dto.Area
     **/
    Area selectById(@Param("id") String id);

    /**
     * @Author Derry Lin
     * @Description 根据区域名查找区域
     * @Date 下午3:19 2020/9/7
     * @Param [areaTitle]
     * @return edu.hust.dao.dto.Area
     **/
    Area selectByAreaTitle(@Param("area_title") String areaTitle);

    /**
     * @Author Derry Lin
     * @Description 添加区域
     * @Date 下午3:19 2020/9/7
     * @Param [area]
     * @return int
     **/
    int add(@Param("area") Area area);

    /**
     * @Author Derry Lin
     * @Description 批量添加区域
     * @Date 下午3:19 2020/9/7
     * @Param [areaList]
     * @return int
     **/
    int addBatch(@Param("area_list") List<Area> areaList);

    /**
     * @Author Derry Lin
     * @Description 更新区域
     * @Date 下午3:19 2020/9/7
     * @Param [area]
     * @return int
     **/
    int update(@Param("area") Area area);

    /**
     * @Author Derry Lin
     * @Description 根据区域id删除区域
     * @Date 下午3:19 2020/9/7
     * @Param [id]
     * @return int
     **/
    int deleteById(@Param("id") String id);

    /**
     * @Author Derry Lin
     * @Description 根据区域名删除
     * @Date 下午3:19 2020/9/7
     * @Param [areaTitle]
     * @return int
     **/
    int deleteByAreaTitle(@Param("area_title") String areaTitle);

    /**
     * @Author Derry Lin
     * @Description 删除所有
     * @Date 下午3:26 2020/9/7
     * @Param []
     * @return int
     **/
    int deleteAll();

}
