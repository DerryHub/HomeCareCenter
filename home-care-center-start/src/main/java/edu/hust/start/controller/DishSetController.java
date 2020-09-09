package edu.hust.start.controller;

import edu.hust.common.util.RandomUUID;
import edu.hust.common.vo.ApiResult;
import edu.hust.dao.dto.DishSet;
import edu.hust.service.domain.DishSetFull;
import edu.hust.service.service.DishSetService;
import edu.hust.start.monitor.Monitor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: DishSet控制类
 * @author: Derry Lin
 * @create: 2020-09-09 10:08
 **/
@RestController
@Api("套餐接口")
@RequestMapping("HomeCareCenter/dishSet/")
public class DishSetController {

    @Resource
    private DishSetService dishSetService;

    @Autowired
    private RandomUUID randomUUID;

    @ApiOperation("查询套餐")
    @GetMapping("search")
    @Monitor("searchDishSet")
    public ApiResult search(@RequestParam(value = "id", required = false) String id) {
        if (id == null) {
            List<DishSetFull> dishSetFullList = dishSetService.getDishSetList();
            return ApiResult.buildSuccess(dishSetFullList);
        } else {
            DishSetFull dishSetFull = dishSetService.getDishSetById(id);
            return ApiResult.buildSuccess(dishSetFull);
        }
    }

    @ApiOperation("添加套餐")
    @PostMapping("add")
    @Monitor("addDishSet")
    public ApiResult add(@RequestBody DishSetFull dishSetFull) {
        dishSetFull.setId(randomUUID.nextIdStr());
        dishSetService.addDishSet(dishSetFull);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("批量添加套餐")
    @PostMapping("addBatch")
    @Monitor("addBatchDishSet")
    public ApiResult addBatch(@RequestBody List<DishSetFull> dishSetFullList) {
        for (DishSetFull dishSetFull : dishSetFullList) {
            dishSetFull.setId(randomUUID.nextIdStr());
        }
        dishSetService.addDishSetList(dishSetFullList);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("更新套餐")
    @PostMapping("update")
    @Monitor("updateDishSet")
    public ApiResult update(@RequestBody DishSetFull dishSetFull) {
        dishSetService.updateDishSet(dishSetFull);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("删除套餐")
    @GetMapping("delete")
    @Monitor("deleteDishSet")
    public ApiResult delete(@RequestParam(value = "id", required = false) String id) {
        if (id == null) {
            dishSetService.deleteAllDishSet();
        } else {
            dishSetService.deleteDishSetById(id);
        }
        return ApiResult.buildSuccess();
    }
}
