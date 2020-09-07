package edu.hust.service.service.impl;

import edu.hust.dao.dao.DishSetMapper;
import edu.hust.dao.dto.DishSet;
import edu.hust.service.domain.DishSetFull;
import edu.hust.service.service.DishSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public DishSetFull getDishSetById(String id) {
        return null;
    }

    @Override
    public void addDishSet(DishSet dishSet) {

    }

    @Override
    public void addDishSetList(List<DishSet> dishSetList) {

    }

    @Override
    public void updateDishSet(DishSet dishSet) {

    }

    @Override
    public void deleteDishSetById(String id) {

    }

    @Override
    public void deleteAllDishSet() {

    }

    private String listToString(List<String> stringList) {
        return String.join(",", stringList);
    }

    private List<String> stringToList(String string) {
        String str[] = string.split(",");
        return Arrays.asList(str);
    }

    private DishSetFull convert(DishSet dishSet) {
        return null;
    }

}
