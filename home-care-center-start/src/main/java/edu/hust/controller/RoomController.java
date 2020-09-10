package edu.hust.controller;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.common.util.RandomUUID;
import edu.hust.common.vo.ApiResult;
import edu.hust.dao.dto.Room;
import edu.hust.monitor.Monitor;
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
@RestController
@Api("房间接口")
@RequestMapping("HomeCareCenter/room/")
public class RoomController {

    @Resource
    private RoomService roomService;

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
        room.setId(randomUUID.nextIdStr());
        roomService.addRoom(room);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("批量添加房间")
    @PostMapping("addBatch")
    @Monitor("addBatchRoom")
    public ApiResult addBatch(@RequestBody List<Room> roomList) {
        for (Room room : roomList) {
            room.setId(randomUUID.nextIdStr());
        }
        roomService.addRoomList(roomList);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("更新房间")
    @PostMapping("update")
    @Monitor("updateRoom")
    public ApiResult update(@RequestBody Room room) {
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
            roomService.deleteAllRoom();
            return ApiResult.buildSuccess();
        } else if (id == null) {
            roomService.deleteRoomByAreaId(areaId);
            return ApiResult.buildSuccess();
        } else if (areaId == null) {
            roomService.deleteRoomById(id);
            return ApiResult.buildSuccess();
        } else {
            throw new GlobalException(ApiCodeEnum.PARAM_ERROR);
        }
    }

}

