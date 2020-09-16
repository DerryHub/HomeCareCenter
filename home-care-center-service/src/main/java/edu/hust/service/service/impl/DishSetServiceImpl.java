package edu.hust.service.service.impl;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.dao.dao.DishSetMapper;
import edu.hust.dao.dto.DishSet;
import edu.hust.service.domain.DishSetFull;
import edu.hust.service.service.DishSetService;
import edu.hust.common.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: DishSet类服务层类
 * @author: Derry Lin
 * @create: 2020-09-07 17:04
 **/
@Service
public class DishSetServiceImpl implements DishSetService {

    @Autowired
    private DishSetMapper dishSetMapper;

    @Override
    public List<DishSetFull> getDishSetList() {
        List<DishSetFull> dishSetFullList = new ArrayList<>();
        List<DishSet> dishSetList = dishSetMapper.selectList();
        for (DishSet dishSet : dishSetList) {
            dishSetFullList.add(convertToFull(dishSet));
        }
        return dishSetFullList;
    }

    @Override
    public DishSetFull getDishSetById(String id) {
        DishSet dishSet = dishSetMapper.selectById(id);
        if (dishSet == null) {
            return null;
        }
        return convertToFull(dishSet);
    }

    @Override
    public DishSetFull getDishSetByName(String name) {
        DishSet dishSet = dishSetMapper.selectByName(name);
        if (dishSet == null) {
            return null;
        }
        return convertToFull(dishSet);
    }

    @Override
    public void addDishSet(DishSetFull dishSetFull) {
        try {
            DishSet dishSet = convertFromFull(dishSetFull);
            if (dishSetMapper.add(dishSet) == 0) {
                throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
            }
        } catch (DataAccessException e) {
            throw new GlobalException(ApiCodeEnum.UNIQUE_ERROR);
        }
    }

    @Override
    public void addDishSetList(List<DishSetFull> dishSetFullList) {
        try {
            List<DishSet> dishSetList = new ArrayList<>();
            for (DishSetFull dishSetFull : dishSetFullList) {
                DishSet dishSet = convertFromFull(dishSetFull);
                dishSetList.add(dishSet);
            }
            if (dishSetMapper.addBatch(dishSetList) != dishSetList.size()) {
                throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
            }
        } catch (DataAccessException e) {
            throw new GlobalException(ApiCodeEnum.UNIQUE_ERROR);
        }
    }

    @Override
    public void updateDishSet(DishSetFull dishSetFull) {
        try {
            DishSet dishSet = convertFromFull(dishSetFull);
            if (dishSetMapper.update(dishSet) == 0) {
                throw new GlobalException(ApiCodeEnum.FAIL_TO_UPDATE);
            }
        } catch (DataAccessException e) {
            throw new GlobalException(ApiCodeEnum.UNIQUE_ERROR);
        }
    }

    @Override
    public void deleteDishSetById(String id) {
        dishSetMapper.deleteById(id);
    }

    @Override
    public void deleteAllDishSet() {
        dishSetMapper.deleteAll();
    }

    private String listToString(List<String> stringList) {
        return String.join(",", stringList);
    }

    private List<String> stringToList(String string) {
        String str[] = string.split(",");
        return Arrays.asList(str);
    }

    private DishSetFull convertToFull(DishSet dishSet) {
        DishSetFull dishSetFull = new DishSetFull();
        dishSetFull.setId(dishSet.getId());
        dishSetFull.setName(dishSet.getName());
        dishSetFull.setMon(dishSet.getMon());
        dishSetFull.setTue(dishSet.getTue());
        dishSetFull.setWed(dishSet.getWed());
        dishSetFull.setThu(dishSet.getThu());
        dishSetFull.setFri(dishSet.getFri());
        dishSetFull.setSat(dishSet.getSat());
        dishSetFull.setSun(dishSet.getSun());
        dishSetFull.setRemark(dishSet.getRemark());

        dishSetFull.setMonId(stringToList(dishSet.getMon()));
        dishSetFull.setTueId(stringToList(dishSet.getTue()));
        dishSetFull.setWedId(stringToList(dishSet.getWed()));
        dishSetFull.setThuId(stringToList(dishSet.getThu()));
        dishSetFull.setFriId(stringToList(dishSet.getFri()));
        dishSetFull.setSatId(stringToList(dishSet.getSat()));
        dishSetFull.setSunId(stringToList(dishSet.getSun()));

        return dishSetFull;
    }

    private DishSet convertFromFull(DishSetFull dishSetFull) {
        dishSetFull.setMon(listToString(dishSetFull.getMonId()));
        dishSetFull.setTue(listToString(dishSetFull.getThuId()));
        dishSetFull.setWed(listToString(dishSetFull.getWedId()));
        dishSetFull.setThu(listToString(dishSetFull.getThuId()));
        dishSetFull.setFri(listToString(dishSetFull.getFriId()));
        dishSetFull.setSat(listToString(dishSetFull.getSatId()));
        dishSetFull.setSun(listToString(dishSetFull.getSunId()));

        DishSet dishSet = (DishSet)dishSetFull;

        return dishSet;
    }

}
