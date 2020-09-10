package edu.hust.service.service.impl;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.dao.dao.DishSetCalendarMapper;
import edu.hust.dao.dto.DishSetCalendar;
import edu.hust.service.domain.ClientFull;
import edu.hust.service.domain.DishSetCalendarFull;
import edu.hust.service.domain.DishSetFull;
import edu.hust.service.service.DishSetCalendarService;
import edu.hust.common.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private DishSetServiceImpl dishSetService;

    @Override
    public List<DishSetCalendarFull> getDishSetCalendarList() {
        List<DishSetCalendarFull> dishSetCalendarFullList = new ArrayList<>();
        List<DishSetCalendar> dishSetCalendarList = dishSetCalendarMapper.selectList();
        for (DishSetCalendar dishSetCalendar : dishSetCalendarList) {
            DishSetCalendarFull dishSetCalendarFull = this.convert(dishSetCalendar);
            ClientFull clientFull = clientService.getClientInfoById(dishSetCalendarFull.getClientId());
            DishSetFull dishSetFull = dishSetService.getDishSetById(dishSetCalendarFull.getDishSetId());
            dishSetCalendarFull.setClientFull(clientFull);
            dishSetCalendarFull.setDishSetFull(dishSetFull);
            dishSetCalendarFullList.add(dishSetCalendarFull);
        }
        return dishSetCalendarFullList;
    }

    @Override
    public DishSetCalendarFull getDishSetCalendarById(String id) {
        DishSetCalendarFull dishSetCalendarFull = this.convert(dishSetCalendarMapper.selectById(id));
        ClientFull clientFull = clientService.getClientInfoById(dishSetCalendarFull.getClientId());
        DishSetFull dishSetFull = dishSetService.getDishSetById(dishSetCalendarFull.getDishSetId());
        dishSetCalendarFull.setClientFull(clientFull);
        dishSetCalendarFull.setDishSetFull(dishSetFull);
        return dishSetCalendarFull;
    }

    @Override
    public List<DishSetCalendarFull> getDishSetCalendarByClientId(String clientId) {
        List<DishSetCalendarFull> dishSetCalendarFullList = new ArrayList<>();
        List<DishSetCalendar> dishSetCalendarList = dishSetCalendarMapper.selectByClientId(clientId);
        for (DishSetCalendar dishSetCalendar : dishSetCalendarList) {
            DishSetCalendarFull dishSetCalendarFull = this.convert(dishSetCalendar);
            ClientFull clientFull = clientService.getClientInfoById(dishSetCalendarFull.getClientId());
            DishSetFull dishSetFull = dishSetService.getDishSetById(dishSetCalendarFull.getDishSetId());
            dishSetCalendarFull.setClientFull(clientFull);
            dishSetCalendarFull.setDishSetFull(dishSetFull);
            dishSetCalendarFullList.add(dishSetCalendarFull);
        }
        return dishSetCalendarFullList;
    }

    @Override
    public List<DishSetCalendarFull> getDishSetCalendarByDishSetId(String dishSetId) {
        List<DishSetCalendarFull> dishSetCalendarFullList = new ArrayList<>();
        List<DishSetCalendar> dishSetCalendarList = dishSetCalendarMapper.selectByDishSetId(dishSetId);
        for (DishSetCalendar dishSetCalendar : dishSetCalendarList) {
            DishSetCalendarFull dishSetCalendarFull = this.convert(dishSetCalendar);
            ClientFull clientFull = clientService.getClientInfoById(dishSetCalendarFull.getClientId());
            DishSetFull dishSetFull = dishSetService.getDishSetById(dishSetCalendarFull.getDishSetId());
            dishSetCalendarFull.setClientFull(clientFull);
            dishSetCalendarFull.setDishSetFull(dishSetFull);
            dishSetCalendarFullList.add(dishSetCalendarFull);
        }
        return dishSetCalendarFullList;
    }

    @Override
    public void addDishSetCalendar(DishSetCalendar dishSetCalendar) {
        if (dishSetCalendarMapper.add(dishSetCalendar) == 0) {
            throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
        }
    }

    @Override
    public void addDishSetCalendarList(List<DishSetCalendar> dishSetCalendarList) {
        if (dishSetCalendarMapper.addBatch(dishSetCalendarList) != dishSetCalendarList.size()) {
            throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
        }
    }

    @Override
    public void updateDishSetCalendar(DishSetCalendar dishSetCalendar) {
        if (dishSetCalendarMapper.update(dishSetCalendar) == 0) {
            throw new GlobalException(ApiCodeEnum.FAIL_TO_UPDATE);
        }
    }

    @Override
    public void deleteDishSetCalendarById(String id) {
        dishSetCalendarMapper.deleteById(id);
    }

    @Override
    public void deleteAllDishSetCalendar() {
        dishSetCalendarMapper.deleteAll();
    }

    private DishSetCalendarFull convert(DishSetCalendar dishSetCalendar) {
        DishSetCalendarFull dishSetCalendarFull = new DishSetCalendarFull();

        dishSetCalendarFull.setId(dishSetCalendar.getId());
        dishSetCalendarFull.setClientId(dishSetCalendar.getClientId());
        dishSetCalendarFull.setDishSetId(dishSetCalendar.getDishSetId());
        dishSetCalendarFull.setStartDate(dishSetCalendar.getStartDate());
        dishSetCalendarFull.setEndDate(dishSetCalendar.getEndDate());

        return dishSetCalendarFull;
    }

}
