package edu.hust.controller;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.common.util.RandomUUID;
import edu.hust.common.vo.ApiResult;
import edu.hust.dao.dto.Area;
import edu.hust.dao.dto.Room;
import edu.hust.dao.dto.Worker;
import edu.hust.monitor.Monitor;
import edu.hust.service.domain.WorkerFull;
import edu.hust.service.service.AreaService;
import edu.hust.common.exception.GlobalException;
import edu.hust.service.service.RoomService;
import edu.hust.service.service.WorkerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Area控制类
 * @author: Derry Lin
 * @create: 2020-09-08 16:20
 **/
@RestController
@Api("区域接口")
@RequestMapping("HomeCareCenter/area/")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private RandomUUID randomUUID;

    @ApiOperation("查询区域")
    @GetMapping("search")
    @Monitor("searchArea")
    public ApiResult search(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "title", required = false) String title
    ) {
        if (id == null && title == null) {
            List<Area> areaList = areaService.getAreaInfoList();
            return ApiResult.buildSuccess(areaList);
        }else if (id == null) {
            Area area = areaService.getAreaInfoByTitle(title);
            return ApiResult.buildSuccess(area);
        }else if (title == null) {
            Area area = areaService.getAreaInfoById(id);
            return ApiResult.buildSuccess(area);
        }else {
            throw new GlobalException(ApiCodeEnum.PARAM_ERROR);
        }
    }

    @ApiOperation("添加区域")
    @PostMapping("add")
    @Monitor("addArea")
    public ApiResult add(@RequestBody Area area) {
        if (!legal(area)) {
            throw new GlobalException(ApiCodeEnum.ILLEGAL_DATA);
        }
        area.setId(randomUUID.nextIdStr());
        areaService.addArea(area);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("批量添加区域")
    @PostMapping("addBatch")
    @Monitor("addBatchArea")
    public ApiResult addBatch(@RequestBody List<Area> areaList) {
        for (Area area : areaList) {
            if (!legal(area)) {
                throw new GlobalException(ApiCodeEnum.ILLEGAL_DATA);
            }
            area.setId(randomUUID.nextIdStr());
        }
        areaService.addAreaList(areaList);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("更新区域")
    @PostMapping("update")
    @Monitor("updateArea")
    public ApiResult update(@RequestBody Area area) {
        areaService.updateArea(area);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("删除区域")
    @GetMapping("delete")
    @Monitor("deleteArea")
    public ApiResult delete(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "title", required = false) String title
    ) {
        if (id == null && title == null) {
            List<Room> roomList = roomService.getRoomList();
            if (!roomList.isEmpty()) {
                throw new GlobalException(ApiCodeEnum.ILLEGAL_DELETE);
            }
            List<WorkerFull> workerFullList = workerService.getWorkerList();
            for (WorkerFull workerFull : workerFullList) {
                if (workerFull.getArea() != null) {
                    throw new GlobalException(ApiCodeEnum.ILLEGAL_DELETE);
                }
            }
            areaService.deleteAllArea();
            return ApiResult.buildSuccess();
        }else if (id == null) {
            Area area = areaService.getAreaInfoByTitle(title);
            if (area == null) {
                return ApiResult.buildSuccess();
            }
            if (!legalDelete(area.getId())) {
                throw new GlobalException(ApiCodeEnum.ILLEGAL_DELETE);
            }
            areaService.deleteAreaByTitle(title);
            return ApiResult.buildSuccess();
        }else if (title == null) {
            if (!legalDelete(id)) {
                throw new GlobalException(ApiCodeEnum.ILLEGAL_DELETE);
            }
            areaService.deleteAreaById(id);
            return ApiResult.buildSuccess();
        }else {
            throw new GlobalException(ApiCodeEnum.PARAM_ERROR);
        }
    }

    private boolean legal(Area area) {
        if (area.getAreaTitle() == null) {
            return false;
        }
        return true;
    }

    private boolean legalDelete(String areaId) {
        List<Room> roomList = roomService.getRoomList();
        for (Room room : roomList) {
            if (room.getArea() != null && areaId == room.getArea().getId()) {
                return false;
            }
        }
        List<WorkerFull> workerFullList = workerService.getWorkerList();
        for (WorkerFull workerFull : workerFullList) {
            if (workerFull.getAreaId() == areaId) {
                return false;
            }
        }
        return true;
    }
}
