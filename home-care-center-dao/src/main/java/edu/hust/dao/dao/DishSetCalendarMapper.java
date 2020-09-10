package edu.hust.dao.dao;

import edu.hust.dao.dto.DishSetCalendar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: DishSetCalendar类数据库接口
 * @author: Derry Lin
 * @create: 2020-09-03 20:15
 **/
@Mapper
public interface DishSetCalendarMapper {

    /**
     * @Author Derry Lin
     * @Description 查询套餐日历
     * @Date 下午3:23 2020/9/7
     * @Param []
     * @return java.util.List<edu.hust.dao.dto.DishSetCalendar>
     **/
    List<DishSetCalendar> selectList();

    /**
     * @Author Derry Lin
     * @Description 根据id查询
     * @Date 下午3:23 2020/9/7
     * @Param [id]
     * @return edu.hust.dao.dto.DishSetCalendar
     **/
    DishSetCalendar selectById(@Param("id") String id);

    /**
     * @Author Derry Lin
     * @Description 根据客户id查询
     * @Date 下午3:23 2020/9/7
     * @Param [clientId]
     * @return java.util.List<edu.hust.dao.dto.DishSetCalendar>
     **/
    List<DishSetCalendar> selectByClientId(@Param("client_id") String clientId);

    /**
     * @Author Derry Lin
     * @Description 根据套餐id查询
     * @Date 下午3:23 2020/9/7
     * @Param [dishSetId]
     * @return java.util.List<edu.hust.dao.dto.DishSetCalendar>
     **/
    List<DishSetCalendar> selectByDishSetId(@Param("dish_set_id") String dishSetId);

    /**
     * @Author Derry Lin
     * @Description 添加套餐日历
     * @Date 下午3:23 2020/9/7
     * @Param [dishSetCalendar]
     * @return int
     **/
    int add(@Param("dish_set_calendar") DishSetCalendar dishSetCalendar);

    /**
     * @Author Derry Lin
     * @Description 批量添加
     * @Date 下午3:23 2020/9/7
     * @Param [dishSetCalendarList]
     * @return int
     **/
    int addBatch(@Param("dish_set_calendar_list") List<DishSetCalendar> dishSetCalendarList);

    /**
     * @Author Derry Lin
     * @Description 更新套餐日历
     * @Date 下午3:23 2020/9/7
     * @Param [dishSetCalendar]
     * @return int
     **/
    int update(@Param("dish_set_calendar") DishSetCalendar dishSetCalendar);

    /**
     * @Author Derry Lin
     * @Description 根据id删除套餐日历
     * @Date 下午3:23 2020/9/7
     * @Param [id]
     * @return int
     **/
    int deleteById(@Param("id") String id);

    /**
     * @Author Derry Lin
     * @Description 根据所有
     * @Date 下午3:37 2020/9/7
     * @Param []
     * @return int
     **/
    int deleteAll();

}
