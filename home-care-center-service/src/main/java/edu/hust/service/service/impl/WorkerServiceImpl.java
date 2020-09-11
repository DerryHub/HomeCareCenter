package edu.hust.service.service.impl;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.dao.dao.AreaMapper;
import edu.hust.dao.dao.WorkerMapper;
import edu.hust.dao.dto.Area;
import edu.hust.dao.dto.Worker;
import edu.hust.service.domain.WorkerFull;
import edu.hust.service.service.WorkerService;
import edu.hust.common.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Worker类服务层类
 * @author: Derry Lin
 * @create: 2020-09-08 09:26
 **/
@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerMapper workerMapper;

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public Worker loginByIdCardNoAndPassword(String idCardNo, String password) {
        Worker worker = workerMapper.selectByIdCardNo(idCardNo);
        if (worker.getPassword() == password) {
            worker.setPassword(null);
            return worker;
        }
        return null;
    }

    @Override
    public List<WorkerFull> getWorkerList() {
        List<WorkerFull> workerFullList = new ArrayList<>();
        List<Worker> workerList = workerMapper.selectList();
        for (Worker worker : workerList) {
            WorkerFull workerFull = this.convert(worker);
            Area area = areaMapper.selectById(workerFull.getAreaId());
            workerFull.setArea(area);
            workerFullList.add(workerFull);
        }
        return workerFullList;
    }

    @Override
    public WorkerFull getWorkerById(String id) {
        Worker worker = workerMapper.selectById(id);
        if (worker == null) {
            return null;
        }
        WorkerFull workerFull = this.convert(worker);
        Area area = areaMapper.selectById(workerFull.getAreaId());
        workerFull.setArea(area);
        return workerFull;
    }

    @Override
    public WorkerFull getWorkerByIdCardNo(String idCardNo) {
        Worker worker = workerMapper.selectByIdCardNo(idCardNo);
        if (worker == null) {
            return null;
        }
        WorkerFull workerFull = this.convert(worker);
        Area area = areaMapper.selectById(workerFull.getAreaId());
        workerFull.setArea(area);
        return workerFull;
    }

    @Override
    public List<WorkerFull> getWorkerByType(int type) {
        List<WorkerFull> workerFullList = new ArrayList<>();
        List<Worker> workerList = workerMapper.selectByType(type);
        for (Worker worker : workerList) {
            WorkerFull workerFull = this.convert(worker);
            Area area = areaMapper.selectById(workerFull.getAreaId());
            workerFull.setArea(area);
            workerFullList.add(workerFull);
        }
        return workerFullList;
    }

    @Override
    public List<WorkerFull> getWorkerByName(String name) {
        List<WorkerFull> workerFullList = new ArrayList<>();
        List<Worker> workerList = workerMapper.selectByName(name);
        for (Worker worker : workerList) {
            WorkerFull workerFull = this.convert(worker);
            Area area = areaMapper.selectById(workerFull.getAreaId());
            workerFull.setArea(area);
            workerFullList.add(workerFull);
        }
        return workerFullList;
    }

    @Override
    public List<WorkerFull> getWorkerByAreaId(String areaId) {
        List<WorkerFull> workerFullList = new ArrayList<>();
        List<Worker> workerList = workerMapper.selectByAreaId(areaId);
        for (Worker worker : workerList) {
            WorkerFull workerFull = this.convert(worker);
            Area area = areaMapper.selectById(workerFull.getAreaId());
            workerFull.setArea(area);
            workerFullList.add(workerFull);
        }
        return workerFullList;
    }

    @Override
    public void addWorker(Worker worker) {
        try {
            if (workerMapper.add(worker) == 0) {
                throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
            }
        } catch (DataAccessException e) {
            throw new GlobalException(ApiCodeEnum.UNIQUE_ERROR);
        }
    }

    @Override
    public void addWorkerList(List<Worker> workerList) {
        try {
            if (workerMapper.addBatch(workerList) != workerList.size()) {
                throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
            }
        } catch (DataAccessException e) {
            throw new GlobalException(ApiCodeEnum.UNIQUE_ERROR);
        }
    }

    @Override
    public void updateWorker(Worker worker) {
        try {
            if (workerMapper.update(worker) == 0) {
                throw new GlobalException(ApiCodeEnum.FAIL_TO_UPDATE);
            }
        } catch (DataAccessException e) {
            throw new GlobalException(ApiCodeEnum.UNIQUE_ERROR);
        }
    }

    @Override
    public void deleteWorkerById(String id) {
        workerMapper.deleteById(id);
    }

    @Override
    public void deleteWorkerByType(int type) {
        workerMapper.deleteByType(type);
    }

    @Override
    public void deleteWorkerByIdCardNo(String idCardNo) {
        workerMapper.deleteByIdCardNo(idCardNo);
    }

    @Override
    public void deleteAllWorker() {
        workerMapper.deleteAll();
    }

    private WorkerFull convert(Worker worker) {
        WorkerFull workerFull = new WorkerFull();

        workerFull.setId(worker.getId());
        workerFull.setType(worker.getType());
        workerFull.setName(worker.getName());
        workerFull.setBirthday(worker.getBirthday());
        workerFull.setGender(worker.getGender());
        workerFull.setIdCardNo(worker.getIdCardNo());
        workerFull.setRegisterDate(worker.getRegisterDate());
        workerFull.setPhoneNo(worker.getPhoneNo());
        workerFull.setAreaId(worker.getAreaId());
        workerFull.setHeadImg(worker.getHeadImg());

        return workerFull;
    }

}
