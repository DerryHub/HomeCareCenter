package edu.hust.service.service.impl;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.dao.dao.OutRegistrationMapper;
import edu.hust.dao.dto.OutRegistration;
import edu.hust.service.domain.ClientFull;
import edu.hust.service.domain.OutRegistrationFull;
import edu.hust.service.domain.WorkerFull;
import edu.hust.service.service.OutRegistrationService;
import edu.hust.common.exception.GlobalException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: OutRegistration类服务层类
 * @author: Derry Lin
 * @create: 2020-09-08 10:10
 **/
@Service
public class OutRegistrationServiceImpl implements OutRegistrationService {

    @Autowired
    private OutRegistrationMapper outRegistrationMapper;

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private WorkerServiceImpl workerService;

    @Override
    public List<OutRegistrationFull> getOutRegistrationList() {
        List<OutRegistrationFull> outRegistrationFullList = new ArrayList<>();
        List<OutRegistration> outRegistrationList = outRegistrationMapper.selectList();
        for (OutRegistration outRegistration : outRegistrationList) {
            OutRegistrationFull outRegistrationFull = this.convert(outRegistration);
            ClientFull clientFull = clientService.getClientInfoById(outRegistrationFull.getClientId());
            WorkerFull nurseFull = workerService.getWorkerById(outRegistrationFull.getNurseId());
            if (clientFull == null || nurseFull == null) {
                continue;
            }
            outRegistrationFull.setClientFull(clientFull);
            outRegistrationFull.setNurse(nurseFull);
            outRegistrationFullList.add(outRegistrationFull);
        }
        return outRegistrationFullList;
    }

    @Override
    public OutRegistrationFull getOutRegistrationById(String id) {
        OutRegistration outRegistration = outRegistrationMapper.selectById(id);
        if (outRegistration == null) {
            return null;
        }
        OutRegistrationFull outRegistrationFull = this.convert(outRegistration);
        ClientFull clientFull = clientService.getClientInfoById(outRegistrationFull.getClientId());
        WorkerFull nurseFull = workerService.getWorkerById(outRegistrationFull.getNurseId());
        if (clientFull == null || nurseFull == null) {
            return null;
        }
        outRegistrationFull.setClientFull(clientFull);
        outRegistrationFull.setNurse(nurseFull);
        return outRegistrationFull;
    }

    @Override
    public List<OutRegistrationFull> getOutRegistrationByDateAndNurseIdAndClientId(Date startOutTime, Date endOutTime, String nurseId, String clientId) {
        List<OutRegistrationFull> outRegistrationFullList = new ArrayList<>();
        List<OutRegistration> outRegistrationList = outRegistrationMapper.selectByDateAndNurseIdAndClientId(startOutTime, endOutTime, nurseId, clientId);
        for (OutRegistration outRegistration : outRegistrationList) {
            OutRegistrationFull outRegistrationFull = this.convert(outRegistration);
            ClientFull clientFull = clientService.getClientInfoById(outRegistrationFull.getClientId());
            WorkerFull nurseFull = workerService.getWorkerById(outRegistrationFull.getNurseId());
            if (clientFull == null || nurseFull == null) {
                continue;
            }
            outRegistrationFull.setClientFull(clientFull);
            outRegistrationFull.setNurse(nurseFull);
            outRegistrationFullList.add(outRegistrationFull);
        }
        return outRegistrationFullList;
    }

    @Override
    public List<OutRegistrationFull> getOutRegistrationByBackTimeReal() {
        List<OutRegistrationFull> outRegistrationFullList = new ArrayList<>();
        List<OutRegistration> outRegistrationList = outRegistrationMapper.selectByBackTimeReal();
        for (OutRegistration outRegistration : outRegistrationList) {
            OutRegistrationFull outRegistrationFull = this.convert(outRegistration);
            ClientFull clientFull = clientService.getClientInfoById(outRegistrationFull.getClientId());
            WorkerFull nurseFull = workerService.getWorkerById(outRegistrationFull.getNurseId());
            if (clientFull == null || nurseFull == null) {
                continue;
            }
            outRegistrationFull.setClientFull(clientFull);
            outRegistrationFull.setNurse(nurseFull);
            outRegistrationFullList.add(outRegistrationFull);
        }
        return outRegistrationFullList;
    }

    @Override
    public void addOutRegistration(OutRegistration outRegistration) {
        try {
            if (outRegistrationMapper.add(outRegistration) == 0) {
                throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
            }
        } catch (DataAccessException e) {
            throw new GlobalException(ApiCodeEnum.UNIQUE_ERROR);
        }
    }

    @Override
    public void addOutRegistrationList(List<OutRegistration> outRegistrationList) {
        try {
            if (outRegistrationMapper.addBatch(outRegistrationList) != outRegistrationList.size()) {
                throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
            }
        } catch (DataAccessException e) {
            throw new GlobalException(ApiCodeEnum.UNIQUE_ERROR);
        }
    }

    @Override
    public void updateOutRegistration(OutRegistration outRegistration) {
        try {
            if (outRegistrationMapper.update(outRegistration) == 0) {
                throw new GlobalException(ApiCodeEnum.FAIL_TO_UPDATE);
            }
        } catch (DataAccessException e) {
            throw new GlobalException(ApiCodeEnum.UNIQUE_ERROR);
        }
    }

    @Override
    public void deleteOutRegistrationById(String id) {
        outRegistrationMapper.deleteById(id);
    }

    @Override
    public void deleteAllOutRegistration() {
        outRegistrationMapper.deleteAll();
    }

    private OutRegistrationFull convert(OutRegistration outRegistration) {
        OutRegistrationFull outRegistrationFull = new OutRegistrationFull();

        outRegistrationFull.setId(outRegistration.getId());
        outRegistrationFull.setClientId(outRegistration.getClientId());
        outRegistrationFull.setNurseId(outRegistration.getNurseId());
        outRegistrationFull.setReason(outRegistration.getReason());
        outRegistrationFull.setOutTime(outRegistration.getOutTime());
        outRegistrationFull.setPartnerName(outRegistration.getPartnerName());
        outRegistrationFull.setPartnerPhone(outRegistration.getPartnerPhone());
        outRegistrationFull.setRemark(outRegistration.getRemark());
        outRegistrationFull.setBackTimeExpected(outRegistration.getBackTimeExpected());
        outRegistrationFull.setBackTimeReal(outRegistration.getBackTimeReal());

        return outRegistrationFull;
    }

}
