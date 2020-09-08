package edu.hust.start.controller;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.common.util.RandomUUID;
import edu.hust.common.vo.ApiResult;
import edu.hust.dao.dto.Worker;
import edu.hust.service.domain.WorkerFull;
import edu.hust.service.service.WorkerService;
import edu.hust.start.interceptor.GlobalException;
import edu.hust.start.monitor.Monitor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chain
 * @date 2020/9/4
 **/
@RestController
@Api("工作者接口")
@RequestMapping("HomeCareCenter/worker/")
public class WorkerController {

    @Resource
    private WorkerService workerService;

    @Autowired
    private RandomUUID randomUUID;

    @ApiOperation("登录接口: idCardNo, password")
    @PostMapping("login")
    @Monitor("login")
    public ApiResult login(@RequestBody Worker worker) {
        String idCardNo = worker.getIdCardNo();
        String password = worker.getPassword();
        if (idCardNo == null) {
            throw new GlobalException(ApiCodeEnum.ID_is_NULL);
        }
        if (password == null) {
            throw new GlobalException(ApiCodeEnum.PASSWORD_IS_NULL);
        }

        if (workerService.loginByIdCardNoAndPassword(idCardNo, password)) {
            return ApiResult.buildSuccess(null);
        }

        return ApiResult.buildError(ApiCodeEnum.ID_OR_PSD_INCORRECT);
    }

    @ApiOperation("工作者查询")
    @GetMapping("search")
    @Monitor("searchWorker")
    public ApiResult search(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "idCardNo", required = false) String idCardNo,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "areaId", required = false) String areaId
    ) {
        if (id == null && idCardNo == null && type == null && name == null && areaId == null) {
            List<WorkerFull> workerFullList = workerService.getWorkerList();
            return ApiResult.buildSuccess(workerFullList);
        }else if (idCardNo == null && type == null && name == null && areaId == null) {
            WorkerFull workerFull = workerService.getWorkerById(id);
            return ApiResult.buildSuccess(workerFull);
        }else if (id == null && type == null && name == null && areaId == null) {
            WorkerFull workerFull = workerService.getWorkerByIdCardNo(idCardNo);
            return ApiResult.buildSuccess(workerFull);
        }else if (id == null && idCardNo == null && name == null && areaId == null) {
            List<WorkerFull> workerFullList = workerService.getWorkerByType(type);
            return ApiResult.buildSuccess(workerFullList);
        }else if (id == null && idCardNo == null && type == null && areaId == null) {
            List<WorkerFull> workerFullList = workerService.getWorkerByName(name);
            return ApiResult.buildSuccess(workerFullList);
        }else if (id == null && idCardNo == null && type == null && name == null) {
            List<WorkerFull> workerFullList = workerService.getWorkerByAreaId(areaId);
            return ApiResult.buildSuccess(workerFullList);
        }else {
            throw new GlobalException(ApiCodeEnum.PARAM_ERROR);
        }
    }

    @ApiOperation("添加工作者")
    @PostMapping("add")
    @Monitor("addWorker")
    public ApiResult add(@RequestBody Worker worker) {
        worker.setId(randomUUID.nextIdStr());
        workerService.addWorker(worker);
        return ApiResult.buildSuccess(null);
    }

    @ApiOperation("批量添加工作者")
    @PostMapping("addBatch")
    @Monitor("addBatchWorker")
    public ApiResult addBatch(@RequestBody List<Worker> workerList) {
        for (Worker worker : workerList) {
            worker.setId(randomUUID.nextIdStr());
        }
        workerService.addWorkerList(workerList);
        return ApiResult.buildSuccess(null);
    }

    @ApiOperation("更新工作者")
    @PostMapping("update")
    @Monitor("updateWorker")
    public ApiResult update(@RequestBody Worker worker) {
        workerService.updateWorker(worker);
        return ApiResult.buildSuccess(null);
    }

    @ApiOperation("删除工作者")
    @GetMapping("delete")
    @Monitor("deleteWorker")
    public ApiResult deleteById(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "id", required = false) String idCardNo
    ) {
        if (id == null && type == null && idCardNo == null) {
            workerService.deleteAllWorker();
            return ApiResult.buildSuccess(null);
        }else if (id == null && type ==null) {
            workerService.deleteWorkerByIdCardNo(idCardNo);
            return ApiResult.buildSuccess(null);
        }else if (id == null && idCardNo == null) {
            workerService.deleteWorkerByType(type);
            return ApiResult.buildSuccess(null);
        }else if (type == null && idCardNo == null) {
            workerService.deleteWorkerById(id);
            return ApiResult.buildSuccess(null);
        }else {
            throw new GlobalException(ApiCodeEnum.PARAM_ERROR);
        }
    }

}
