package edu.hust.service.service.impl;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.dao.dao.NursingRecordMapper;
import edu.hust.dao.dto.NursingRecord;
import edu.hust.service.domain.ClientFull;
import edu.hust.service.domain.NursingRecordFull;
import edu.hust.service.domain.WorkerFull;
import edu.hust.service.service.NursingRecordService;
import edu.hust.common.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: NursingRecord类服务类
 * @author: Derry Lin
 * @create: 2020-09-08 09:53
 **/
@Service
public class NursingRecordServiceImpl implements NursingRecordService {

    @Autowired
    private NursingRecordMapper nursingRecordMapper;

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private WorkerServiceImpl workerService;

    @Override
    public List<NursingRecordFull> getNursingRecordList() {
        List<NursingRecordFull> nursingRecordFullList = new ArrayList<>();
        List<NursingRecord> nursingRecordList = nursingRecordMapper.selectList();
        for (NursingRecord nursingRecord : nursingRecordList) {
            NursingRecordFull nursingRecordFull = this.convert(nursingRecord);
            ClientFull clientFull = clientService.getClientInfoById(nursingRecordFull.getClientId());
            WorkerFull nurseFull = workerService.getWorkerById(nursingRecordFull.getNurseId());
            nursingRecordFull.setClientFull(clientFull);
            nursingRecordFull.setNurse(nurseFull);
            nursingRecordFullList.add(nursingRecordFull);
        }
        return nursingRecordFullList;
    }

    @Override
    public NursingRecordFull getNursingRecordById(String id) {
        NursingRecordFull nursingRecordFull = this.convert(nursingRecordMapper.selectById(id));
        ClientFull clientFull = clientService.getClientInfoById(nursingRecordFull.getClientId());
        WorkerFull nurseFull = workerService.getWorkerById(nursingRecordFull.getNurseId());
        nursingRecordFull.setClientFull(clientFull);
        nursingRecordFull.setNurse(nurseFull);
        return nursingRecordFull;
    }

    @Override
    public List<NursingRecordFull> getNursingRecordByDateAndNurseIdAndClientId(Date startDate, Date endDate, String nurseId, String clientId) {
        List<NursingRecordFull> nursingRecordFullList = new ArrayList<>();
        List<NursingRecord> nursingRecordList = nursingRecordMapper.selectByDateAndNurseIdAndClientId(startDate, endDate, nurseId, clientId);
        for (NursingRecord nursingRecord : nursingRecordList) {
            NursingRecordFull nursingRecordFull = this.convert(nursingRecord);
            ClientFull clientFull = clientService.getClientInfoById(nursingRecordFull.getClientId());
            WorkerFull nurseFull = workerService.getWorkerById(nursingRecordFull.getNurseId());
            nursingRecordFull.setClientFull(clientFull);
            nursingRecordFull.setNurse(nurseFull);
            nursingRecordFullList.add(nursingRecordFull);
        }
        return nursingRecordFullList;
    }

    @Override
    public void addNursingRecord(NursingRecord nursingRecord) {
        if (nursingRecordMapper.add(nursingRecord) == 0) {
            throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
        }
    }

    @Override
    public void addNursingRecordList(List<NursingRecord> nursingRecordList) {
        if (nursingRecordMapper.addBatch(nursingRecordList) != nursingRecordList.size()) {
            throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
        }
    }

    @Override
    public void updateNursingRecord(NursingRecord nursingRecord) {
        if (nursingRecordMapper.update(nursingRecord) == 0) {
            throw new GlobalException(ApiCodeEnum.FAIL_TO_UPDATE);
        }
    }

    @Override
    public void deleteNursingRecordById(String id) {
        nursingRecordMapper.deleteById(id);
    }

    @Override
    public void deleteAllNursingRecord() {
        nursingRecordMapper.deleteAll();
    }

    private NursingRecordFull convert(NursingRecord nursingRecord) {
        NursingRecordFull nursingRecordFull = new NursingRecordFull();

        nursingRecordFull.setId(nursingRecord.getId());
        nursingRecordFull.setClientId(nursingRecord.getClientId());
        nursingRecordFull.setNurseId(nursingRecord.getNurseId());
        nursingRecordFull.setContent(nursingRecord.getContent());
        nursingRecordFull.setDate(nursingRecord.getDate());
        nursingRecordFull.setRemark(nursingRecord.getRemark());

        return nursingRecordFull;
    }

}
