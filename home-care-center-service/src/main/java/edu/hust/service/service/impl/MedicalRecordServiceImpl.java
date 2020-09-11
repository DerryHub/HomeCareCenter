package edu.hust.service.service.impl;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.dao.dao.MedicalRecordMapper;
import edu.hust.dao.dto.MedicalRecord;
import edu.hust.service.domain.ClientFull;
import edu.hust.service.domain.MedicalRecordFull;
import edu.hust.service.domain.WorkerFull;
import edu.hust.service.service.MedicalRecordService;
import edu.hust.common.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: MedicalRecord类服务层类
 * @author: Derry Lin
 * @create: 2020-09-08 09:40
 **/
@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    private MedicalRecordMapper medicalRecordMapper;

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private WorkerServiceImpl workerService;

    @Override
    public List<MedicalRecordFull> getMedicalRecordList() {
        // todo 缓存优化数据库查询
        List<MedicalRecordFull> medicalRecordFullList = new ArrayList<>();
        List<MedicalRecord> medicalRecordList = medicalRecordMapper.selectList();
        for (MedicalRecord medicalRecord : medicalRecordList) {
            MedicalRecordFull medicalRecordFull = this.convert(medicalRecord);
            ClientFull clientFull = clientService.getClientInfoById(medicalRecordFull.getClientId());
            WorkerFull doctorFull = workerService.getWorkerById(medicalRecordFull.getDoctorId());
            if (clientFull == null || doctorFull == null) {
                continue;
            }
            medicalRecordFull.setClientFull(clientFull);
            medicalRecordFull.setDoctor(doctorFull);
            medicalRecordFullList.add(medicalRecordFull);
        }
        return medicalRecordFullList;
    }

    @Override
    public MedicalRecordFull getMedicalRecordById(String id) {
        MedicalRecord medicalRecord = medicalRecordMapper.selectById(id);
        if (medicalRecord == null) {
            return null;
        }
        MedicalRecordFull medicalRecordFull = this.convert(medicalRecord);
        ClientFull clientFull = clientService.getClientInfoById(medicalRecordFull.getClientId());
        WorkerFull doctorFull = workerService.getWorkerById(medicalRecordFull.getDoctorId());
        if (clientFull == null || doctorFull == null) {
            return null;
        }
        medicalRecordFull.setClientFull(clientFull);
        medicalRecordFull.setDoctor(doctorFull);
        return medicalRecordFull;
    }

    @Override
    public List<MedicalRecordFull> getMedicalRecordByDateAndDoctorIdAndClientId(Date startDate, Date endDate, String doctorId, String clientId) {
        List<MedicalRecordFull> medicalRecordFullList = new ArrayList<>();
        List<MedicalRecord> medicalRecordList = medicalRecordMapper.selectByDateAndDoctorIdAndClientId(startDate, endDate, doctorId, clientId);
        for (MedicalRecord medicalRecord : medicalRecordList) {
            MedicalRecordFull medicalRecordFull = this.convert(medicalRecord);
            ClientFull clientFull = clientService.getClientInfoById(medicalRecordFull.getClientId());
            WorkerFull doctorFull = workerService.getWorkerById(medicalRecordFull.getDoctorId());
            if (clientFull == null || doctorFull == null) {
                continue;
            }
            medicalRecordFull.setClientFull(clientFull);
            medicalRecordFull.setDoctor(doctorFull);
            medicalRecordFullList.add(medicalRecordFull);
        }
        return medicalRecordFullList;
    }

    @Override
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        try {
            if (medicalRecordMapper.add(medicalRecord) == 0) {
                throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
            }
        } catch (DataAccessException e) {
            throw new GlobalException(ApiCodeEnum.UNIQUE_ERROR);
        }
    }

    @Override
    public void addMedicalRecordList(List<MedicalRecord> medicalRecordList) {
        try {
            if (medicalRecordMapper.addBatch(medicalRecordList) != medicalRecordList.size()) {
                throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
            }
        } catch (DataAccessException e) {
            throw new GlobalException(ApiCodeEnum.UNIQUE_ERROR);
        }
    }

    @Override
    public void updateMedicalRecord(MedicalRecord medicalRecord) {
        try {
            if (medicalRecordMapper.update(medicalRecord) == 0) {
                throw new GlobalException(ApiCodeEnum.FAIL_TO_UPDATE);
            }
        } catch (DataAccessException e) {
            throw new GlobalException(ApiCodeEnum.UNIQUE_ERROR);
        }
    }

    @Override
    public void deleteMedicalRecordById(String id) {
        medicalRecordMapper.deleteById(id);
    }

    @Override
    public void deleteAllMedicalRecord() {
        medicalRecordMapper.deleteAll();
    }

    private MedicalRecordFull convert(MedicalRecord medicalRecord) {
        MedicalRecordFull medicalRecordFull = new MedicalRecordFull();

        medicalRecordFull.setId(medicalRecord.getId());
        medicalRecordFull.setClientId(medicalRecord.getClientId());
        medicalRecordFull.setDoctorId(medicalRecord.getDoctorId());
        medicalRecordFull.setDate(medicalRecord.getDate());
        medicalRecordFull.setPrescription(medicalRecord.getPrescription());
        medicalRecordFull.setDisease(medicalRecord.getDisease());
        medicalRecordFull.setRemark(medicalRecord.getRemark());

        return medicalRecordFull;
    }

}
