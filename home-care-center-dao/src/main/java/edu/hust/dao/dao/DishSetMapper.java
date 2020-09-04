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

    //查询套餐
    List<DishSet> selectList();

    //根据id查找
    DishSet selectById(@Param("id") String id);

    //添加套餐
    int add(@Param("dish_set") DishSet dishSet);

    //批量添加
    int addBatch(@Param("dish_set_list") List<DishSet> dishSetList);

    //更新套餐
    int update(@Param("dish_set") DishSet dishSet);

    //根据id删除套餐
    int deleteById(@Param("id") String id);

    //删除所有
    int deleteAll();

}
