package edu.hust.controller;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.common.util.RandomUUID;
import edu.hust.common.vo.ApiResult;
import edu.hust.dao.dto.Dish;
import edu.hust.dao.dto.DishSet;
import edu.hust.monitor.Monitor;
import edu.hust.service.domain.DishSetFull;
import edu.hust.service.service.DishService;
import edu.hust.common.exception.GlobalException;
import edu.hust.service.service.DishSetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Dish控制类
 * @author: Derry Lin
 * @create: 2020-09-09 09:51
 **/
@CrossOrigin
@RestController
@Api("餐饮接口")
@RequestMapping("HomeCareCenter/dish/")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishSetService dishSetService;

    @Autowired
    private RandomUUID randomUUID;

    @ApiOperation("餐饮查询")
    @GetMapping("search")
    @Monitor("searchDish")
    public ApiResult search(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "name", required = false) String name
    ) {
        if (id == null && name == null) {
            List<Dish> dishList = dishService.getDishList();
            return ApiResult.buildSuccess(dishList);
        } else if (id == null) {
            Dish dish = dishService.getDishByName(name);
            return ApiResult.buildSuccess(dish);
        } else if (name == null) {
            Dish dish = dishService.getDishById(id);
            return ApiResult.buildSuccess(dish);
        } else {
            throw new GlobalException(ApiCodeEnum.PARAM_ERROR);
        }
    }

    @ApiOperation("添加餐饮")
    @PostMapping("add")
    @Monitor("addDish")
    public ApiResult add(@RequestBody Dish dish) {
        if (!legal(dish)) {
            throw new GlobalException(ApiCodeEnum.ILLEGAL_DATA);
        }
        dish.setId(randomUUID.nextIdStr());
        dishService.addDish(dish);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("批量添加餐饮")
    @PostMapping("addBatch")
    @Monitor("addBatchDish")
    public ApiResult addBatch(@RequestBody List<Dish> dishList) {
        for (Dish dish : dishList) {
            if (!legal(dish)) {
                throw new GlobalException(ApiCodeEnum.ILLEGAL_DATA);
            }
            dish.setId(randomUUID.nextIdStr());
        }
        dishService.addDishList(dishList);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("更新餐饮")
    @PostMapping("update")
    @Monitor("updateDish")
    public ApiResult update(@RequestBody Dish dish) {
        dishService.updateDish(dish);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("删除餐饮")
    @GetMapping("delete")
    @Monitor("deleteDish")
    public ApiResult delete(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "name", required = false) String name
    ) {
        if (id == null && name == null) {
            List<DishSetFull> dishSetFullList = dishSetService.getDishSetList();
            if (!dishSetFullList.isEmpty()) {
                throw new GlobalException(ApiCodeEnum.ILLEGAL_DELETE);
            }
            dishService.deleteAllDish();
            return ApiResult.buildSuccess();
        } else if (id == null) {
            Dish dish = dishService.getDishByName(name);
            if (dish == null) {
                return ApiResult.buildSuccess();
            }
            if (!legalDelete(dish.getId())) {
                throw new GlobalException(ApiCodeEnum.ILLEGAL_DELETE);
            }
            dishService.deleteDishByName(name);
            return ApiResult.buildSuccess();
        } else if (name == null) {
            if (!legalDelete(id)) {
                throw new GlobalException(ApiCodeEnum.ILLEGAL_DELETE);
            }
            dishService.deleteDishById(id);
            return ApiResult.buildSuccess();
        } else {
            throw new GlobalException(ApiCodeEnum.PARAM_ERROR);
        }
    }

    private boolean legal(Dish dish) {
        if (dish.getName() == null) {
            return false;
        }
        return true;
    }

    private boolean legalDelete(String dishId) {
        List<DishSetFull> dishSetFullList = dishSetService.getDishSetList();
        for (DishSetFull dishSetFull : dishSetFullList) {
            List<String> dishList = new ArrayList<>();
            dishList.add(dishSetFull.getMon());
            dishList.add(dishSetFull.getTue());
            dishList.add(dishSetFull.getWed());
            dishList.add(dishSetFull.getThu());
            dishList.add(dishSetFull.getFri());
            dishList.add(dishSetFull.getSat());
            dishList.add(dishSetFull.getSun());
            String ids = String.join(",", dishList);
            String str[] = ids.split(",");
            List<String> idList = Arrays.asList(str);
            if (idList.contains(dishId)) {
                return false;
            }
        }
        return true;
    }
}
