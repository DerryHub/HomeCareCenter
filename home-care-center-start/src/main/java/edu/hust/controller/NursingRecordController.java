package edu.hust.controller;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.common.util.RandomUUID;
import edu.hust.common.vo.ApiResult;
import edu.hust.dao.dto.NursingRecord;
import edu.hust.monitor.Monitor;
import edu.hust.service.domain.NursingRecordFull;
import edu.hust.service.domain.WorkerFull;
import edu.hust.service.service.ClientService;
import edu.hust.service.service.NursingRecordService;
import edu.hust.common.exception.GlobalException;
import edu.hust.service.service.WorkerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: NursingRecord控制类
 * @author: Derry Lin
 * @create: 2020-09-09 15:18
 **/
@CrossOrigin
@RestController
@Api("护理记录接口")
@RequestMapping("HomeCareCenter/nursingRecord/")
public class NursingRecordController {

    @Autowired
    private NursingRecordService nursingRecordService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private RandomUUID randomUUID;

    @ApiOperation("查询护理记录")
    @GetMapping("search")
    @Monitor("searchNursingRecord")
    public ApiResult search(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "startDate", required = false) Date startDate,
            @RequestParam(value = "endDate", required = false) Date endDate,
            @RequestParam(value = "nurseId", required = false) String nurseId,
            @RequestParam(value = "clientId", required = false) String clientId
    ) {
        if (id == null && startDate == null && endDate == null && nurseId == null && clientId == null) {
            List<NursingRecordFull> nursingRecordList = nursingRecordService.getNursingRecordList();
            return ApiResult.buildSuccess(nursingRecordList);
        } else if (startDate == null && endDate == null && nurseId == null && clientId == null) {
            NursingRecordFull nursingRecordFull = nursingRecordService.getNursingRecordById(id);
            return ApiResult.buildSuccess(nursingRecordFull);
        } else if (id == null) {
            List<NursingRecordFull> nursingRecordFullList = nursingRecordService.getNursingRecordByDateAndNurseIdAndClientId(
                    startDate, endDate, nurseId, clientId);
            return ApiResult.buildSuccess(nursingRecordFullList);
        } else {
            throw new GlobalException(ApiCodeEnum.PARAM_ERROR);
        }
    }

    @ApiOperation("添加护理记录")
    @PostMapping("add")
    @Monitor("addNursingRecord")
    public ApiResult add(@RequestBody NursingRecord nursingRecord) {
        if (!legal(nursingRecord)) {
            throw new GlobalException(ApiCodeEnum.ILLEGAL_DATA);
        }
        nursingRecord.setId(randomUUID.nextIdStr());
        nursingRecordService.addNursingRecord(nursingRecord);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("批量添加护理记录")
    @PostMapping("addBatch")
    @Monitor("addBatchNursingRecord")
    public ApiResult addBatch(@RequestBody List<NursingRecord> nursingRecordList) {
        for (NursingRecord nursingRecord : nursingRecordList) {
            if (!legal(nursingRecord)) {
                throw new GlobalException(ApiCodeEnum.ILLEGAL_DATA);
            }
            nursingRecord.setId(randomUUID.nextIdStr());
        }
        nursingRecordService.addNursingRecordList(nursingRecordList);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("更新护理记录")
    @PostMapping("update")
    @Monitor("updateNursingRecord")
    public ApiResult update(@RequestBody NursingRecord nursingRecord) {
        if (
                (
                        nursingRecord.getClientId() != null
                        && clientService.getClientInfoById(nursingRecord.getClientId()) == null
                        )
                || (
                        nursingRecord.getNurseId() != null
                        && (workerService.getWorkerById(nursingRecord.getNurseId()) == null
                            || workerService.getWorkerById(nursingRecord.getNurseId()).getType() != 2)
                        )
        ) {
            throw new GlobalException(ApiCodeEnum.ILLEGAL_DATA);
        }
        nursingRecordService.updateNursingRecord(nursingRecord);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("删除护理记录")
    @GetMapping("delete")
    @Monitor("deleteNursingRecord")
    public ApiResult delete(@RequestParam(value = "id", required = false) String id) {
        if (id == null) {
            nursingRecordService.deleteAllNursingRecord();
        } else {
            nursingRecordService.deleteNursingRecordById(id);
        }
        return ApiResult.buildSuccess();
    }

    private boolean legal(NursingRecord nursingRecord) {
        if (
                nursingRecord.getClientId() == null
                || clientService.getClientInfoById(nursingRecord.getClientId()) == null
                || nursingRecord.getNurseId() == null
                || nursingRecord.getContent() == null
                || nursingRecord.getDate() == null
        ) {
            return false;
        }
        WorkerFull workerFull = workerService.getWorkerById(nursingRecord.getNurseId());
        if (workerFull == null || workerFull.getType() != 2) {
            return false;
        }
        return true;
    }
}
