package edu.hust.dao.dao;

import edu.hust.dao.dto.DishSetCalendar;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: DishSetCalendar类数据库接口
 * @author: Derry Lin
 * @create: 2020-09-03 20:15
 **/
public interface DishSetCalendarMapper {

    //查询套餐日历
    List<DishSetCalendar> selectList();

    //根据id查询
    DishSetCalendar selectById(@Param("id") String id);

    //根据客户id查询
    List<DishSetCalendar> selectByClientId(@Param("client_id") String clientId);

    //根据套餐id查询
    List<DishSetCalendar> selectByDishSetId(@Param("dish_set_id") String dishSetId);

    //添加套餐日历
    int add(@Param("dish_set_calendar") DishSetCalendar dishSetCalendar);

    //批量添加
    int addBatch(@Param("dish_set_calendar_list") List<DishSetCalendar> dishSetCalendarList);

    //更新套餐日历
    int update(@Param("dish_set_calendar") DishSetCalendar dishSetCalendar);

    //根据id删除套餐日历
    int deleteById(@Param("id") String id);

    //根据所有
    int deleteAll();

}
