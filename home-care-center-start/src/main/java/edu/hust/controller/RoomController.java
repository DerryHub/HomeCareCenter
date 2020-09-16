package edu.hust.controller;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.common.util.RandomUUID;
import edu.hust.common.vo.ApiResult;
import edu.hust.dao.dto.Bed;
import edu.hust.dao.dto.Room;
import edu.hust.monitor.Monitor;
import edu.hust.service.service.AreaService;
import edu.hust.service.service.BedService;
import edu.hust.service.service.RoomService;
import edu.hust.common.exception.GlobalException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Room控制类
 * @author: Derry Lin
 * @create: 2020-09-09 10:50
 **/
@CrossOrigin
@RestController
@Api("房间接口")
@RequestMapping("HomeCareCenter/room/")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private BedService bedService;

    @Autowired
    private RandomUUID randomUUID;

    @ApiOperation("查询房间")
    @GetMapping("search")
    @Monitor("searchRoom")
    public ApiResult search(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "areaId", required = false) String areaId
    ) {
        if (id == null && title == null && areaId == null) {
            List<Room> roomList = roomService.getRoomList();
            return ApiResult.buildSuccess(roomList);
        } else if (title == null && areaId == null) {
            Room room = roomService.getRoomById(id);
            return ApiResult.buildSuccess(room);
        } else if (id == null && areaId == null) {
            Room room = roomService.getRoomByRoomTitle(title);
            return ApiResult.buildSuccess(room);
        } else if (id == null && title == null) {
            List<Room> roomList = roomService.getRoomByAreaId(areaId);
            return ApiResult.buildSuccess(roomList);
        } else {
            throw new GlobalException(ApiCodeEnum.PARAM_ERROR);
        }
    }

    @ApiOperation("添加房间")
    @PostMapping("add")
    @Monitor("addRoom")
    public ApiResult add(@RequestBody Room room) {
        if (!legal(room)) {
            throw new GlobalException(ApiCodeEnum.ILLEGAL_DATA);
        }
        room.setId(randomUUID.nextIdStr());
        roomService.addRoom(room);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("批量添加房间")
    @PostMapping("addBatch")
    @Monitor("addBatchRoom")
    public ApiResult addBatch(@RequestBody List<Room> roomList) {
        for (Room room : roomList) {
            if (!legal(room)) {
                throw new GlobalException(ApiCodeEnum.ILLEGAL_DATA);
            }
            room.setId(randomUUID.nextIdStr());
        }
        roomService.addRoomList(roomList);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("更新房间")
    @PostMapping("update")
    @Monitor("updateRoom")
    public ApiResult update(@RequestBody Room room) {
        if (
                room.getArea() != null
                && room.getArea().getId() != null
                && areaService.getAreaInfoById(room.getArea().getId()) == null
        ) {
            throw new GlobalException(ApiCodeEnum.ILLEGAL_DATA);
        }
        roomService.updateRoom(room);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("删除房间")
    @GetMapping("delete")
    @Monitor("deleteRoom")
    public ApiResult delete(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "areaId", required = false) String areaId
    ) {
        if (id == null && areaId == null) {
            List<Bed> bedList = bedService.getBedList();
            if (!bedList.isEmpty()) {
                throw new GlobalException(ApiCodeEnum.ILLEGAL_DELETE);
            }
            roomService.deleteAllRoom();
            return ApiResult.buildSuccess();
        } else if (id == null) {
            List<Room> roomList = roomService.getRoomByAreaId(areaId);
            for (Room room : roomList) {
                if (!legalDelete(room.getId())) {
                    throw new GlobalException(ApiCodeEnum.ILLEGAL_DELETE);
                }
            }
            roomService.deleteRoomByAreaId(areaId);
            return ApiResult.buildSuccess();
        } else if (areaId == null) {
            if (!legalDelete(id)) {
                throw new GlobalException(ApiCodeEnum.ILLEGAL_DELETE);
            }
            roomService.deleteRoomById(id);
            return ApiResult.buildSuccess();
        } else {
            throw new GlobalException(ApiCodeEnum.PARAM_ERROR);
        }
    }

    private boolean legal(Room room) {
        if (
                room.getRoomTitle() == null
                || room.getArea() == null
                || room.getArea().getId() == null
                || areaService.getAreaInfoById(room.getArea().getId()) == null
        ) {
            return false;
        }
        return true;
    }

    private boolean legalDelete(String roomId) {
        List<Bed> bedList = bedService.getBedByRoomId(roomId);
        if (bedList.isEmpty()) {
            return true;
        }
        return false;
    }
}

