package edu.hust.service.service;

import edu.hust.dao.dto.DishSetCalendar;
import edu.hust.service.domain.DishSetCalendarFull;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: DishSetCalendar类服务层接口
 * @author: Derry Lin
 * @create: 2020-09-07 14:39
 **/
@Service
public interface DishSetCalendarService {

    /**
     * @Author Derry Lin
     * @Description 查询套餐日历
     * @Date 下午3:06 2020/9/7
     * @Param []
     * @return java.util.List<edu.hust.dao.dto.DishSetCalendar>
     **/
    List<DishSetCalendarFull> getDishSetCalendarList();

    /**
     * @Author Derry Lin
     * @Description 根据id查询
     * @Date 下午3:06 2020/9/7
     * @Param [id]
     * @return edu.hust.dao.dto.DishSetCalendar
     **/
    DishSetCalendarFull getDishSetCalendarById(String id);

    /**
     * @Author Derry Lin
     * @Description 根据客户id查询
     * @Date 下午3:06 2020/9/7
     * @Param [clientId]
     * @return java.util.List<edu.hust.dao.dto.DishSetCalendar>
     **/
    List<DishSetCalendarFull> getDishSetCalendarByClientId(String clientId);

    /**
     * @Author Derry Lin
     * @Description 根据套餐id查询
     * @Date 下午3:06 2020/9/7
     * @Param [dishSetId]
     * @return java.util.List<edu.hust.dao.dto.DishSetCalendar>
     **/
    List<DishSetCalendarFull> getDishSetCalendarByDishSetId(String dishSetId);

    /**
     * @Author Derry Lin
     * @Description 添加套餐日历
     * @Date 下午3:06 2020/9/7
     * @Param [dishSetCalendar]
     * @return void
     **/
    void addDishSetCalendar(DishSetCalendar dishSetCalendar);

    /**
     * @Author Derry Lin
     * @Description 批量添加
     * @Date 下午3:06 2020/9/7
     * @Param [dishSetCalendarList]
     * @return void
     **/
    void addDishSetCalendarList(List<DishSetCalendar> dishSetCalendarList);

    /**
     * @Author Derry Lin
     * @Description 更新套餐日历
     * @Date 下午3:06 2020/9/7
     * @Param [dishSetCalendar]
     * @return void
     **/
    void updateDishSetCalendar(DishSetCalendar dishSetCalendar);

    /**
     * @Author Derry Lin
     * @Description 根据id删除套餐日历
     * @Date 下午3:06 2020/9/7
     * @Param [id]
     * @return void
     **/
    void deleteDishSetCalendarById(String id);

    /**
     * @Author Derry Lin
     * @Description 根据所有
     * @Date 下午3:19 2020/9/7
     * @Param []
     * @return void
     **/
    void deleteAllDishSetCalendar();

}
