package edu.hust.service.service.impl;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.dao.dao.BedMapper;
import edu.hust.dao.dao.ClientMapper;
import edu.hust.dao.dto.Bed;
import edu.hust.dao.dto.Client;
import edu.hust.service.service.BedService;
import edu.hust.common.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Bed类服务层类
 * @author: Derry Lin
 * @create: 2020-09-07 16:13
 **/
@Service
public class BedServiceImpl implements BedService {

    @Autowired
    private BedMapper bedMapper;

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public List<Bed> getBedList() {
        return bedMapper.selectList();
    }

    @Override
    public Bed getBedById(String id) {
        return bedMapper.selectById(id);
    }

    @Override
    public Bed getBedByTitle(String title) {
        return bedMapper.selectByBedTitle(title);
    }

    @Override
    public List<Bed> getBedByRoomId(String roomId) {
        return bedMapper.selectByRoomId(roomId);
    }

    @Override
    public List<Bed> getEmptyBed() {
        List<Bed> allBed = bedMapper.selectList();
        List<Client> clientList = clientMapper.selectList();
        List<String> busyBedId = new ArrayList<>();
        for (Client client : clientList) {
            busyBedId.add(client.getBedId());
        }
        List<Bed> emptyBed = new ArrayList<>();
        for (Bed bed : allBed) {
            if (!busyBedId.contains(bed.getId())) {
                emptyBed.add(bed);
            }
        }
        return emptyBed;
    }

    @Override
    public void addBed(Bed bed) {
        try {
            if (bedMapper.add(bed) == 0){
                throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
            }
        } catch (DataAccessException e) {
            throw new GlobalException(ApiCodeEnum.UNIQUE_ERROR);
        }
    }

    @Override
    public void addBedList(List<Bed> bedList) {
        try {
            if (bedMapper.addBatch(bedList) != bedList.size()){
                throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
            }
        } catch (DataAccessException e) {
            throw new GlobalException(ApiCodeEnum.UNIQUE_ERROR);
        }
    }

    @Override
    public void updateBed(Bed bed) {
        try {
            if (bedMapper.update(bed) == 0){
                throw new GlobalException(ApiCodeEnum.FAIL_TO_UPDATE);
            }
        } catch (DataAccessException e) {
            throw new GlobalException(ApiCodeEnum.UNIQUE_ERROR);
        }
    }

    @Override
    public void deleteBedById(String id) {
        bedMapper.deleteById(id);
    }

    @Override
    public void deleteBedByRoomId(String roomId) {
        bedMapper.deleteByRoomId(roomId);
    }

    @Override
    public void deleteAllBed() {
        bedMapper.deleteAll();
    }
}
