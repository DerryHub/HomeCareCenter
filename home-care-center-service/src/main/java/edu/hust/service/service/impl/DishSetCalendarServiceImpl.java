package edu.hust.service.service.impl;

import edu.hust.dao.dao.ClientMapper;
import edu.hust.dao.dao.DishSetCalendarMapper;
import edu.hust.dao.dao.DishSetMapper;
import edu.hust.dao.dto.DishSetCalendar;
import edu.hust.service.service.DishSetCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: DishSetCalendar类服务层类
 * @author: Derry Lin
 * @create: 2020-09-07 16:53
 **/
@Service
public class DishSetCalendarServiceImpl implements DishSetCalendarService {

    @Autowired
    private DishSetCalendarMapper dishSetCalendarMapper;



    @Override
    public List<DishSetCalendar> getDishSetCalendarList() {
        return null;
    }

    @Override
    public DishSetCalendar getDishSetCalendarById(String id) {
        return null;
    }

    @Override
    public List<DishSetCalendar> getDishSetCalendarByClientId(String clientId) {
        return null;
    }

    @Override
    public List<DishSetCalendar> getDishSetCalendarByDishSetId(String dishSetId) {
        return null;
    }

    @Override
    public void addDishSetCalendar(DishSetCalendar dishSetCalendar) {

    }

    @Override
    public void addDishSetCalendarList(List<DishSetCalendar> dishSetCalendarList) {

    }

    @Override
    public void updateDishSetCalendar(DishSetCalendar dishSetCalendar) {

    }

    @Override
    public void deleteDishSetCalendarById(String id) {

    }

    @Override
    public void deleteAllDishSetCalendar() {

    }
}
