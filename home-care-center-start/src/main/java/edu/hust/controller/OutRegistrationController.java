package edu.hust.controller;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.common.util.RandomUUID;
import edu.hust.common.vo.ApiResult;
import edu.hust.dao.dto.OutRegistration;
import edu.hust.monitor.Monitor;
import edu.hust.service.domain.OutRegistrationFull;
import edu.hust.service.domain.WorkerFull;
import edu.hust.service.service.ClientService;
import edu.hust.service.service.OutRegistrationService;
import edu.hust.common.exception.GlobalException;
import edu.hust.service.service.WorkerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: OutRegistration控制类
 * @author: Derry Lin
 * @create: 2020-09-09 15:29
 **/
@CrossOrigin
@RestController
@Api("出行登记接口")
@RequestMapping("HomeCareCenter/outRegistration/")
public class OutRegistrationController {

    @Autowired
    private OutRegistrationService outRegistrationService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private RandomUUID randomUUID;

    @ApiOperation("查询外出记录")
    @GetMapping("search")
    @Monitor("searchOutRegistration")
    public ApiResult search(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "startOutDate", required = false) Date startOutDate,
            @RequestParam(value = "endOutDate", required = false) Date endOutDate,
            @RequestParam(value = "nurseId", required = false) String nurseId,
            @RequestParam(value = "clientId", required = false) String clientId
    ) {
        if (id == null && startOutDate == null && endOutDate == null && nurseId == null && clientId == null) {
            List<OutRegistrationFull> outRegistrationFullList = outRegistrationService.getOutRegistrationList();
            return ApiResult.buildSuccess(outRegistrationFullList);
        } else if (startOutDate == null && endOutDate == null && nurseId == null && clientId == null) {
            OutRegistrationFull outRegistrationFull = outRegistrationService.getOutRegistrationById(id);
            return ApiResult.buildSuccess(outRegistrationFull);
        } else if (id == null) {
            List<OutRegistrationFull> outRegistrationFullList = outRegistrationService.getOutRegistrationByDateAndNurseIdAndClientId(
                    startOutDate, endOutDate, nurseId, clientId);
            return ApiResult.buildSuccess(outRegistrationFullList);
        } else {
            throw new GlobalException(ApiCodeEnum.PARAM_ERROR);
        }
    }

    @ApiOperation("查询未回归记录")
    @GetMapping("searchOutside")
    @Monitor("searchOutsideOutRegistration")
    public ApiResult searchOutside() {
        List<OutRegistrationFull> outRegistrationFullList = outRegistrationService.getOutRegistrationByBackTimeReal();
        return ApiResult.buildSuccess(outRegistrationFullList);
    }

    @ApiOperation("添加外出记录")
    @PostMapping("add")
    @Monitor("addOutRegistration")
    public ApiResult add(@RequestBody OutRegistration outRegistration) {
        if (!legal(outRegistration)) {
            throw new GlobalException(ApiCodeEnum.ILLEGAL_DATA);
        }
        outRegistration.setId(randomUUID.nextIdStr());
        outRegistrationService.addOutRegistration(outRegistration);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("批量添加外出记录")
    @PostMapping("addBatch")
    @Monitor("addBatchOutRegistration")
    public ApiResult addBatch(@RequestBody List<OutRegistration> outRegistrationList) {
        for (OutRegistration outRegistration : outRegistrationList) {
            if (!legal(outRegistration)) {
                throw new GlobalException(ApiCodeEnum.ILLEGAL_DATA);
            }
            outRegistration.setId(randomUUID.nextIdStr());
        }
        outRegistrationService.addOutRegistrationList(outRegistrationList);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("修改外出记录")
    @PostMapping("update")
    @Monitor("updateOutRegistration")
    public ApiResult update(@RequestBody OutRegistration outRegistration) {
        if (
                (
                        outRegistration.getClientId() != null
                        && clientService.getClientInfoById(outRegistration.getClientId()) == null
                        )
                || (
                        outRegistration.getNurseId() != null
                        && (workerService.getWorkerById(outRegistration.getNurseId()) == null
                            || workerService.getWorkerById(outRegistration.getNurseId()).getType() != 2)
                        )
        ) {
            throw new GlobalException(ApiCodeEnum.ILLEGAL_DATA);
        }
        outRegistrationService.updateOutRegistration(outRegistration);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("删除外出记录")
    @GetMapping("delete")
    @Monitor("deleteOutRegistration")
    public ApiResult delete(@RequestParam(value = "id", required = false) String id) {
        if (id == null) {
            outRegistrationService.deleteAllOutRegistration();
        } else {
            outRegistrationService.deleteOutRegistrationById(id);
        }
        return ApiResult.buildSuccess();
    }

    private boolean legal(OutRegistration outRegistration) {
        if (
                outRegistration.getClientId() == null
                || clientService.getClientInfoById(outRegistration.getClientId()) == null
                || outRegistration.getNurseId() == null
                || outRegistration.getReason() == null
                || outRegistration.getOutTime() == null
                || outRegistration.getBackTimeExpected() == null
        ) {
            return false;
        }
        WorkerFull workerFull = workerService.getWorkerById(outRegistration.getNurseId());
        if (workerFull == null || workerFull.getType() != 2) {
            return false;
        }
        return true;
    }
}
