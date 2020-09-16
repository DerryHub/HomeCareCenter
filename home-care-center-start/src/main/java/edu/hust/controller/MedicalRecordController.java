package edu.hust.controller;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.common.util.RandomUUID;
import edu.hust.common.vo.ApiResult;
import edu.hust.dao.dto.MedicalRecord;
import edu.hust.monitor.Monitor;
import edu.hust.service.domain.MedicalRecordFull;
import edu.hust.service.domain.WorkerFull;
import edu.hust.service.service.ClientService;
import edu.hust.service.service.MedicalRecordService;
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
 * @description: MedicalRecord控制类
 * @author: Derry Lin
 * @create: 2020-09-09 14:52
 **/
@CrossOrigin
@RestController
@Api("病例接口")
@RequestMapping("HomeCareCenter/medicalRecord/")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private RandomUUID randomUUID;

    @ApiOperation("查询病例")
    @GetMapping("search")
    @Monitor("searchMedicalRecord")
    public ApiResult search(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "startDate", required = false) Date startDate,
            @RequestParam(value = "endDate", required = false) Date endDate,
            @RequestParam(value = "doctorId", required = false) String doctorId,
            @RequestParam(value = "clientId", required = false) String clientId
            ) {
        if (id == null && startDate == null && endDate == null && doctorId == null && clientId == null) {
            List<MedicalRecordFull> medicalRecordFullList = medicalRecordService.getMedicalRecordList();
            return ApiResult.buildSuccess(medicalRecordFullList);
        } else if (startDate == null && endDate == null && doctorId == null && clientId == null) {
            MedicalRecordFull medicalRecordFull = medicalRecordService.getMedicalRecordById(id);
            return ApiResult.buildSuccess(medicalRecordFull);
        } else if (id == null) {
            List<MedicalRecordFull> medicalRecordFullList = medicalRecordService.getMedicalRecordByDateAndDoctorIdAndClientId(
                    startDate, endDate, doctorId, clientId);
            return ApiResult.buildSuccess(medicalRecordFullList);
        } else {
            throw new GlobalException(ApiCodeEnum.PARAM_ERROR);
        }
    }

    @ApiOperation("添加病例")
    @PostMapping("add")
    @Monitor("addMedicalRecord")
    public ApiResult add(@RequestBody MedicalRecord medicalRecord) {
        if (!legal(medicalRecord)) {
            throw new GlobalException(ApiCodeEnum.ILLEGAL_DATA);
        }
        medicalRecord.setId(randomUUID.nextIdStr());
        medicalRecordService.addMedicalRecord(medicalRecord);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("批量添加病例")
    @PostMapping("addBatch")
    @Monitor("addBatchMedicalRecord")
    public ApiResult addBatch(@RequestBody List<MedicalRecord> medicalRecordList) {
        for (MedicalRecord medicalRecord : medicalRecordList) {
            if (!legal(medicalRecord)) {
                throw new GlobalException(ApiCodeEnum.ILLEGAL_DATA);
            }
            medicalRecord.setId(randomUUID.nextIdStr());
        }
        medicalRecordService.addMedicalRecordList(medicalRecordList);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("更新病例")
    @PostMapping("update")
    @Monitor("updateMedicalRecord")
    public ApiResult update(@RequestBody MedicalRecord medicalRecord) {
        if (
                (
                        medicalRecord.getClientId() != null
                        && clientService.getClientInfoById(medicalRecord.getClientId()) == null
                        )
                || (
                        medicalRecord.getDoctorId() != null
                        && (workerService.getWorkerById(medicalRecord.getDoctorId()) == null
                            || workerService.getWorkerById(medicalRecord.getDoctorId()).getType() != 1)
                        )
        ) {
            throw new GlobalException(ApiCodeEnum.ILLEGAL_DATA);
        }
        medicalRecordService.updateMedicalRecord(medicalRecord);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("删除病例")
    @GetMapping("delete")
    @Monitor("deleteMedicalRecord")
    public ApiResult delete(@RequestParam(value = "id", required = false) String id) {
        if (id == null) {
            medicalRecordService.deleteAllMedicalRecord();
        } else {
            medicalRecordService.deleteMedicalRecordById(id);
        }
        return ApiResult.buildSuccess();
    }

    private boolean legal(MedicalRecord medicalRecord) {
        if (
                medicalRecord.getClientId() == null
                || clientService.getClientInfoById(medicalRecord.getClientId()) == null
                || medicalRecord.getDoctorId() == null
                || medicalRecord.getDate() == null
                || medicalRecord.getPrescription() == null
        ) {
            return false;
        }
        WorkerFull workerFull = workerService.getWorkerById(medicalRecord.getDoctorId());
        if (workerFull == null || workerFull.getType() != 1) {
            return false;
        }
        return true;
    }
}
