package edu.hust.service.service.impl;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.dao.dao.DishMapper;
import edu.hust.dao.dto.Dish;
import edu.hust.service.service.DishService;
import edu.hust.common.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: DishSet类服务层类
 * @author: Derry Lin
 * @create: 2020-09-07 16:44
 **/
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Override
    public List<Dish> getDishList() {
        return dishMapper.selectList();
    }

    @Override
    public Dish getDishById(String id) {
        return dishMapper.selectById(id);
    }

    @Override
    public Dish getDishByName(String name) {
        return dishMapper.selectByName(name);
    }

    @Override
    public void addDish(Dish dish) {
        try {
            if (dishMapper.add(dish) == 0) {
                throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
            }
        } catch (DataAccessException e) {
            throw new GlobalException(ApiCodeEnum.UNIQUE_ERROR);
        }
    }

    @Override
    public void addDishList(List<Dish> dishList) {
        try {
            if (dishMapper.addBatch(dishList) != dishList.size()) {
                throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
            }
        } catch (DataAccessException e) {
            throw new GlobalException(ApiCodeEnum.UNIQUE_ERROR);
        }
    }

    @Override
    public void updateDish(Dish dish) {
        try {
            if (dishMapper.update(dish) == 0) {
                throw new GlobalException(ApiCodeEnum.FAIL_TO_UPDATE);
            }
        } catch (DataAccessException e) {
            throw new GlobalException(ApiCodeEnum.UNIQUE_ERROR);
        }
    }

    @Override
    public void deleteDishById(String id) {
        dishMapper.deleteById(id);
    }

    @Override
    public void deleteDishByName(String name) {
        dishMapper.deleteByName(name);
    }

    @Override
    public void deleteAllDish() {
        dishMapper.deleteAll();
    }
}
