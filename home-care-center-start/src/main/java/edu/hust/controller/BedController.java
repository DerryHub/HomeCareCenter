package edu.hust.controller;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.common.util.RandomUUID;
import edu.hust.common.vo.ApiResult;
import edu.hust.dao.dto.Bed;
import edu.hust.monitor.Monitor;
import edu.hust.service.domain.ClientFull;
import edu.hust.service.service.BedService;
import edu.hust.common.exception.GlobalException;
import edu.hust.service.service.ClientService;
import edu.hust.service.service.RoomService;
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
@CrossOrigin
@RestController
@Api("床铺接口")
@RequestMapping("HomeCareCenter/bed/")
public class BedController {

    @Autowired
    private BedService bedService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private ClientService clientService;

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
        if (!legal(bed)) {
            throw new GlobalException(ApiCodeEnum.ILLEGAL_DATA);
        }
        bed.setId(randomUUID.nextIdStr());
        bedService.addBed(bed);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("批量添加床铺")
    @PostMapping("addBatch")
    @Monitor("addBatchBed")
    public ApiResult addBatch(@RequestBody List<Bed> bedList) {
        for (Bed bed : bedList) {
            if (!legal(bed)) {
                throw new GlobalException(ApiCodeEnum.ILLEGAL_DATA);
            }
            bed.setId(randomUUID.nextIdStr());
        }
        bedService.addBedList(bedList);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("更新床铺")
    @PostMapping("update")
    @Monitor("updateBed")
    public ApiResult update(@RequestBody Bed bed) {
        if (
                bed.getRoom() != null
                && bed.getRoom().getId() != null
                && roomService.getRoomById(bed.getRoom().getId()) == null
        ) {
            throw new GlobalException(ApiCodeEnum.ILLEGAL_DATA);
        }
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
            List<ClientFull> clientFullList = clientService.getClientInfoList();
            if (!clientFullList.isEmpty()) {
                throw new GlobalException(ApiCodeEnum.ILLEGAL_DELETE);
            }
            bedService.deleteAllBed();
            return ApiResult.buildSuccess();
        }else if (id == null) {
            List<Bed> bedList = bedService.getBedByRoomId(roomId);
            for (Bed bed : bedList) {
                if (!legalDelete(bed.getId())) {
                    throw new GlobalException(ApiCodeEnum.ILLEGAL_DELETE);
                }
            }
            bedService.deleteBedByRoomId(roomId);
            return ApiResult.buildSuccess();
        }else if (roomId == null) {
            if (!legalDelete(id)) {
                throw new GlobalException(ApiCodeEnum.ILLEGAL_DELETE);
            }
            bedService.deleteBedById(id);
            return ApiResult.buildSuccess();
        }else {
            throw new GlobalException(ApiCodeEnum.PARAM_ERROR);
        }
    }

    private boolean legal(Bed bed) {
        if (
                bed.getBedTitle() == null
                || bed.getRoom() == null
                || bed.getRoom().getId() == null
                || roomService.getRoomById(bed.getRoom().getId()) == null
        ) {
            return false;
        }
        return true;
    }

    private boolean legalDelete(String bedId) {
        ClientFull clientFull = clientService.getClientInfoByBedId(bedId);
        if (clientFull == null) {
            return true;
        }
        return false;
    }
}
