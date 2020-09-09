package edu.hust.start.controller;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.common.util.RandomUUID;
import edu.hust.common.vo.ApiResult;
import edu.hust.dao.dto.DishSetCalendar;
import edu.hust.service.domain.DishSetCalendarFull;
import edu.hust.service.service.DishSetCalendarService;
import edu.hust.start.interceptor.GlobalException;
import edu.hust.start.monitor.Monitor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: DishSetCalendar控制类
 * @author: Derry Lin
 * @create: 2020-09-09 10:31
 **/
@RestController
@Api("套餐日历接口")
@RequestMapping("HomeCareCenter/dishSetCalendar/")
public class DishSetCalendarController {

    @Resource
    private DishSetCalendarService dishSetCalendarService;

    @Autowired
    private RandomUUID randomUUID;

    @ApiOperation("查询套餐日历")
    @GetMapping("search")
    @Monitor("searchDishSetCalendar")
    public ApiResult search(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "clientId", required = false) String clientId,
            @RequestParam(value = "dishSetId", required = false) String dishSetId
    ) {
        if (id == null && clientId == null && dishSetId == null) {
            List<DishSetCalendarFull> dishSetCalendarList = dishSetCalendarService.getDishSetCalendarList();
            return ApiResult.buildSuccess(dishSetCalendarList);
        } else if (clientId == null && dishSetId == null) {
            DishSetCalendarFull dishSetCalendarFull = dishSetCalendarService.getDishSetCalendarById(id);
            return ApiResult.buildSuccess(dishSetCalendarFull);
        } else if (id == null && dishSetId == null) {
            List<DishSetCalendarFull> dishSetCalendarFullList = dishSetCalendarService.getDishSetCalendarByClientId(clientId);
            return ApiResult.buildSuccess(dishSetCalendarFullList);
        } else if (id == null && clientId == null) {
            List<DishSetCalendarFull> dishSetCalendarFullList = dishSetCalendarService.getDishSetCalendarByDishSetId(dishSetId);
            return ApiResult.buildSuccess(dishSetCalendarFullList);
        } else {
            throw new GlobalException(ApiCodeEnum.PARAM_ERROR);
        }
    }

    @ApiOperation("添加套餐日历")
    @PostMapping("add")
    @Monitor("addDishSetCalendar")
    public ApiResult add(@RequestBody DishSetCalendar dishSetCalendar) {
        dishSetCalendar.setId(randomUUID.nextIdStr());
        dishSetCalendarService.addDishSetCalendar(dishSetCalendar);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("批量添加套餐日历")
    @PostMapping("addBatch")
    @Monitor("addBatchDishSetCalendar")
    public ApiResult addBatch(@RequestBody List<DishSetCalendar> dishSetCalendarList) {
        for (DishSetCalendar dishSetCalendar : dishSetCalendarList) {
            dishSetCalendar.setId(randomUUID.nextIdStr());
        }
        dishSetCalendarService.addDishSetCalendarList(dishSetCalendarList);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("更新套餐日历")
    @PostMapping("update")
    @Monitor("updateDishSetCalendar")
    public ApiResult update(@RequestBody DishSetCalendar dishSetCalendar) {
        dishSetCalendarService.updateDishSetCalendar(dishSetCalendar);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("删除套餐日历")
    @GetMapping("delete")
    @Monitor("deleteDishSetCalendar")
    public ApiResult delete(@RequestParam(value = "id", required = false) String id) {
        if (id == null) {
            dishSetCalendarService.deleteAllDishSetCalendar();
        } else {
            dishSetCalendarService.deleteDishSetCalendarById(id);
        }
        return ApiResult.buildSuccess();
    }
}
