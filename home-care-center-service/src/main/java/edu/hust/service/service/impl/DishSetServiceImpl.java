package edu.hust.service.service.impl;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.dao.dao.DishSetMapper;
import edu.hust.dao.dto.DishSet;
import edu.hust.service.domain.DishSetFull;
import edu.hust.service.service.DishSetService;
import edu.hust.start.interceptor.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
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
            dishSetFullList.add(this.convert(dishSet));
        }
        return dishSetFullList;
    }

    @Override
    public DishSetFull getDishSetById(String id) {
        return this.convert(dishSetMapper.selectById(id));
    }

    @Override
    public void addDishSet(DishSet dishSet) {
        if (dishSetMapper.add(dishSet) == 0) {
            throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
        }
    }

    @Override
    public void addDishSetList(List<DishSet> dishSetList) {
        if (dishSetMapper.addBatch(dishSetList) != dishSetList.size()) {
            throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
        }
    }

    @Override
    public void updateDishSet(DishSet dishSet) {
        if (dishSetMapper.update(dishSet) == 0) {
            throw new GlobalException(ApiCodeEnum.FAIL_TO_UPDATE);
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

    public static String listToString(List<String> stringList) {
        return String.join(",", stringList);
    }

    public static List<String> stringToList(String string) {
        String str[] = string.split(",");
        return Arrays.asList(str);
    }

    private DishSetFull convert(DishSet dishSet) {
        DishSetFull dishSetFull = new DishSetFull();
        dishSetFull.setId(dishSet.getId());
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



}
