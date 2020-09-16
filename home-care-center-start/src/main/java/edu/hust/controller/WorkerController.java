package edu.hust.controller;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.common.util.JWTUtil;
import edu.hust.common.util.RandomUUID;
import edu.hust.common.vo.ApiResult;
import edu.hust.dao.dto.Worker;
import edu.hust.service.domain.WorkerFull;
import edu.hust.service.service.AreaService;
import edu.hust.service.service.WorkerService;
import edu.hust.common.exception.GlobalException;
import edu.hust.monitor.Monitor;
import edu.hust.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chain
 * @date 2020/9/4
 **/
@RestController
@Api("工作者接口")
@RequestMapping("HomeCareCenter/worker/")
public class WorkerController {

    /**
     * 缓存key: REDIS_JWT + 账号
     * value: jwt
     */
    private static final String REDIS_JWT="redis_jwt_";

    @Autowired
    private RedisUtil redisUtil;


    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private AreaService areaService;

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
        //如果jwt存在且有效 直接返回user
        String redisJWT= (String) redisUtil.get(REDIS_JWT+worker.getIdCardNo());
        if (redisJWT!=null&&jwtUtil.isTokenValid(redisJWT)){
            worker=workerService.getWorkerByIdCardNo(worker.getIdCardNo());
            return ApiResult.buildSuccess(worker);
        }
        //jwt不存在
        if ((worker= workerService.loginByIdCardNoAndPassword(idCardNo, password))!=null) {
            String jwt= jwtUtil.createJWT(worker.getId(),worker.getType()+"",worker.getIdCardNo());
            redisUtil.set(REDIS_JWT+worker.getIdCardNo(),jwt,jwtUtil.getTtl());
            Map<String,Object> map=new HashMap<>();
            map.put(REDIS_JWT+worker.getIdCardNo(),jwt);
            map.put("worker",workerService.getWorkerByIdCardNo(worker.getIdCardNo()));
            return ApiResult.buildSuccess(map);
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
        if (!legal(worker)) {
            throw new GlobalException(ApiCodeEnum.ILLEGAL_DATA);
        }
        worker.setId(randomUUID.nextIdStr());
        workerService.addWorker(worker);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("批量添加工作者")
    @PostMapping("addBatch")
    @Monitor("addBatchWorker")
    public ApiResult addBatch(@RequestBody List<Worker> workerList) {
        for (Worker worker : workerList) {
            if (!legal(worker)) {
                throw new GlobalException(ApiCodeEnum.ILLEGAL_DATA);
            }
            worker.setId(randomUUID.nextIdStr());
        }
        workerService.addWorkerList(workerList);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("更新工作者")
    @PostMapping("update")
    @Monitor("updateWorker")
    public ApiResult update(@RequestBody Worker worker) {
        if (
                worker.getAreaId() != null
                && areaService.getAreaInfoById(worker.getAreaId()) == null
        ) {
            throw new GlobalException(ApiCodeEnum.ILLEGAL_DATA);
        }
        workerService.updateWorker(worker);
        return ApiResult.buildSuccess();
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
            return ApiResult.buildSuccess();
        }else if (id == null && type ==null) {
            workerService.deleteWorkerByIdCardNo(idCardNo);
            return ApiResult.buildSuccess();
        }else if (id == null && idCardNo == null) {
            workerService.deleteWorkerByType(type);
            return ApiResult.buildSuccess();
        }else if (type == null && idCardNo == null) {
            workerService.deleteWorkerById(id);
            return ApiResult.buildSuccess();
        }else {
            throw new GlobalException(ApiCodeEnum.PARAM_ERROR);
        }
    }

    private boolean legal(Worker worker) {
        if (
                worker.getType() == null
                || worker.getName() == null
                || worker.getGender() == null
                || worker.getIdCardNo() == null
                || worker.getRegisterDate() == null
                || worker.getPassword() == null
                || worker.getHeadImg() == null
        ) {
            return false;
        }
        if (worker.getAreaId() != null && areaService.getAreaInfoById(worker.getAreaId()) == null) {
            return false;
        }
        return true;
    }
}
