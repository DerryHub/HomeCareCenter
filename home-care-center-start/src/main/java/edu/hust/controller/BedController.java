package edu.hust.controller;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.common.util.RandomUUID;
import edu.hust.common.vo.ApiResult;
import edu.hust.dao.dto.Bed;
import edu.hust.monitor.Monitor;
import edu.hust.service.service.BedService;
import edu.hust.common.exception.GlobalException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Bed控制类
 * @author: Derry Lin
 * @create: 2020-09-08 16:36
 **/
@RestController
@Api("床铺接口")
@RequestMapping("HomeCareCenter/bed/")
public class BedController {

    @Resource
    private BedService bedService;

    @Autowired
    private RandomUUID randomUUID;

    @ApiOperation("床铺查询")
    @GetMapping("search")
    @Monitor("searchBed")
    public ApiResult search(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "roomId", required = false) String roomId
    ) {
        if (id == null && title == null && roomId == null) {
            List<Bed> bedList = bedService.getBedList();
            return ApiResult.buildSuccess(bedList);
        }else if (id == null && title == null) {
            List<Bed> bedList = bedService.getBedByRoomId(roomId);
            return ApiResult.buildSuccess(bedList);
        }else if (id == null && roomId == null) {
            Bed bed = bedService.getBedByTitle(title);
            return ApiResult.buildSuccess(bed);
        }else if (title == null && roomId == null) {
            Bed bed = bedService.getBedById(id);
            return ApiResult.buildSuccess(bed);
        }else {
            throw new GlobalException(ApiCodeEnum.PARAM_ERROR);
        }
    }

    @ApiOperation("添加床铺")
    @PostMapping("add")
    @Monitor("addBed")
    public ApiResult add(@RequestBody Bed bed) {
        bed.setId(randomUUID.nextIdStr());
        bedService.addBed(bed);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("批量添加床铺")
    @PostMapping("addBatch")
    @Monitor("addBatchBed")
    public ApiResult addBatch(@RequestBody List<Bed> bedList) {
        for (Bed bed : bedList) {
            bed.setId(randomUUID.nextIdStr());
        }
        bedService.addBedList(bedList);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("更新床铺")
    @PostMapping("update")
    @Monitor("updateBed")
    public ApiResult update(@RequestBody Bed bed) {
        bedService.updateBed(bed);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("删除床铺")
    @GetMapping("delete")
    @Monitor("deleteBed")
    public ApiResult delete(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "roomId", required = false) String roomId
    ) {
        if (id == null && roomId == null) {
            bedService.deleteAllBed();
            return ApiResult.buildSuccess();
        }else if (id == null) {
            bedService.deleteBedByRoomId(roomId);
            return ApiResult.buildSuccess();
        }else if (roomId == null) {
            bedService.deleteBedById(id);
            return ApiResult.buildSuccess();
        }else {
            throw new GlobalException(ApiCodeEnum.PARAM_ERROR);
        }
    }
}
