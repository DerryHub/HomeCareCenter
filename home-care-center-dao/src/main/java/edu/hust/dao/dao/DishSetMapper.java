package edu.hust.dao.dao;


import edu.hust.dao.dto.DishSet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: DishSet类数据库接口
 * @author: Derry Lin
 * @create: 2020-09-03 19:25
 **/
public interface DishSetMapper {

    /**
     * @Author Derry Lin
     * @Description 查询套餐
     * @Date 下午3:23 2020/9/7
     * @Param []
     * @return java.util.List<edu.hust.dao.dto.DishSet>
     **/
    List<DishSet> selectList();

    /**
     * @Author Derry Lin
     * @Description 根据id查找
     * @Date 下午3:23 2020/9/7
     * @Param [id]
     * @return edu.hust.dao.dto.DishSet
     **/
    DishSet selectById(@Param("id") String id);

    /**
     * @Author Derry Lin
     * @Description 添加套餐
     * @Date 下午3:23 2020/9/7
     * @Param [dishSet]
     * @return int
     **/
    int add(@Param("dish_set") DishSet dishSet);

    /**
     * @Author Derry Lin
     * @Description 批量添加
     * @Date 下午3:23 2020/9/7
     * @Param [dishSetList]
     * @return int
     **/
    int addBatch(@Param("dish_set_list") List<DishSet> dishSetList);

    /**
     * @Author Derry Lin
     * @Description 更新套餐
     * @Date 下午3:23 2020/9/7
     * @Param [dishSet]
     * @return int
     **/
    int update(@Param("dish_set") DishSet dishSet);

    /**
     * @Author Derry Lin
     * @Description 根据id删除套餐
     * @Date 下午3:24 2020/9/7
     * @Param [id]
     * @return int
     **/
    int deleteById(@Param("id") String id);

    /**
     * @Author Derry Lin
     * @Description 删除所有
     * @Date 下午3:39 2020/9/7
     * @Param []
     * @return int
     **/
    int deleteAll();

}
