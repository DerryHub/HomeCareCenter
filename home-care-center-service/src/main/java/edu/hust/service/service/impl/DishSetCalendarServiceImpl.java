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
import org.springframework.dao.DataAccessException;
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
            if (clientFull == null || dishSetFull == null) {
                continue;
            }
            dishSetCalendarFull.setClientFull(clientFull);
            dishSetCalendarFull.setDishSetFull(dishSetFull);
            dishSetCalendarFullList.add(dishSetCalendarFull);
        }
        return dishSetCalendarFullList;
    }

    @Override
    public DishSetCalendarFull getDishSetCalendarById(String id) {
        DishSetCalendar dishSetCalendar = dishSetCalendarMapper.selectById(id);
        if (dishSetCalendar == null) {
            return null;
        }
        DishSetCalendarFull dishSetCalendarFull = this.convert(dishSetCalendar);
        ClientFull clientFull = clientService.getClientInfoById(dishSetCalendarFull.getClientId());
        DishSetFull dishSetFull = dishSetService.getDishSetById(dishSetCalendarFull.getDishSetId());
        if (clientFull == null || dishSetFull == null) {
            return null;
        }
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
            if (clientFull == null || dishSetFull == null) {
                continue;
            }
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
            if (clientFull == null || dishSetFull == null) {
                continue;
            }
            dishSetCalendarFull.setClientFull(clientFull);
            dishSetCalendarFull.setDishSetFull(dishSetFull);
            dishSetCalendarFullList.add(dishSetCalendarFull);
        }
        return dishSetCalendarFullList;
    }

    @Override
    public void addDishSetCalendar(DishSetCalendar dishSetCalendar) {
        try {
            if (dishSetCalendarMapper.add(dishSetCalendar) == 0) {
                throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
            }
        } catch (DataAccessException e) {
            throw new GlobalException(ApiCodeEnum.UNIQUE_ERROR);
        }
    }

    @Override
    public void addDishSetCalendarList(List<DishSetCalendar> dishSetCalendarList) {
        try {
            if (dishSetCalendarMapper.addBatch(dishSetCalendarList) != dishSetCalendarList.size()) {
                throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
            }
        } catch (DataAccessException e) {
            throw new GlobalException(ApiCodeEnum.UNIQUE_ERROR);
        }
    }

    @Override
    public void updateDishSetCalendar(DishSetCalendar dishSetCalendar) {
        try {
            if (dishSetCalendarMapper.update(dishSetCalendar) == 0) {
                throw new GlobalException(ApiCodeEnum.FAIL_TO_UPDATE);
            }
        } catch (DataAccessException e) {
            throw new GlobalException(ApiCodeEnum.UNIQUE_ERROR);
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
