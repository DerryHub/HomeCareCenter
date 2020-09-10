package edu.hust.service.service.impl;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.dao.dao.BedMapper;
import edu.hust.dao.dao.ClientMapper;
import edu.hust.dao.dto.Bed;
import edu.hust.dao.dto.Client;
import edu.hust.service.domain.ClientFull;
import edu.hust.service.service.ClientService;
import edu.hust.common.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Client类服务层类
 * @author: Derry Lin
 * @create: 2020-09-07 16:17
 **/

public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private BedMapper bedMapper;

    @Override
    public ClientFull getClientInfoById(String id) {
        Client client = clientMapper.selectByBedId(id);
        Bed bed = bedMapper.selectById(client.getBedId());
        ClientFull clientFull = this.convert(client);
        clientFull.setBed(bed);
        return clientFull;
    }

    @Override
    public ClientFull getClientInfoByIdCardNo(String idCardNo) {
        Client client = clientMapper.selectByIdCardNo(idCardNo);
        Bed bed = bedMapper.selectById(client.getBedId());
        ClientFull clientFull = this.convert(client);
        clientFull.setBed(bed);
        return clientFull;
    }

    @Override
    public List<ClientFull> getClientInfoList() {
        List<Client> clientList = clientMapper.selectList();
        List<ClientFull> clientFullList = new ArrayList<>();
        for (Client client : clientList) {
            ClientFull clientFull = this.convert(client);
            Bed bed = bedMapper.selectById(client.getBedId());
            clientFull.setBed(bed);
            clientFullList.add(clientFull);
        }

        return clientFullList;
    }

    @Override
    public List<ClientFull> getClientInfoByName(String name) {
        List<Client> clientList = clientMapper.selectByName(name);
        List<ClientFull> clientFullList = new ArrayList<>();
        for (Client client : clientList) {
            ClientFull clientFull = this.convert(client);
            Bed bed = bedMapper.selectById(client.getBedId());
            clientFull.setBed(bed);
            clientFullList.add(clientFull);
        }

        return clientFullList;
    }

    @Override
    public ClientFull getClientInfoByBedId(String bedId) {
        Client client = clientMapper.selectByBedId(bedId);
        Bed bed = bedMapper.selectById(client.getBedId());
        ClientFull clientFull = this.convert(client);
        clientFull.setBed(bed);
        return clientFull;
    }

    @Override
    public void addClient(Client client) {
        if (clientMapper.add(client) == 0) {
            throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
        }
    }

    @Override
    public void addClientList(List<Client> clientList) {
        if (clientMapper.addBatch(clientList) != clientList.size()) {
            throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
        }
    }

    @Override
    public void updateClient(Client client) {
        if (clientMapper.update(client) == 0) {
            throw new GlobalException(ApiCodeEnum.FAIL_TO_UPDATE);
        }
    }

    @Override
    public void deleteClientById(String id) {
        clientMapper.deleteById(id);
    }

    @Override
    public void deleteClientByIdCardNo(String idCardNo) {
        clientMapper.deleteByIdCardNo(idCardNo);
    }

    @Override
    public void deleteAllClient() {
        clientMapper.deleteAll();
    }

    private ClientFull convert(Client client) {
        ClientFull clientFull = new ClientFull();
        clientFull.setId(client.getId());
        clientFull.setName(client.getName());
        clientFull.setGender(client.getGender());
        clientFull.setIdCardNo(client.getIdCardNo());
        clientFull.setHeight(client.getHeight());
        clientFull.setWeight(client.getWeight());
        clientFull.setRelativeName(client.getRelativeName());
        clientFull.setRelativePhone(client.getRelativePhone());
        clientFull.setMarriage(client.getMarriage());
        clientFull.setHeadImg(client.getHeadImg());
        clientFull.setRemark(client.getRemark());
        clientFull.setBedId(client.getBedId());
        clientFull.setLevelOfCare(client.getLevelOfCare());
        clientFull.setInDate(client.getInDate());
        clientFull.setOutDate(client.getOutDate());
        clientFull.setFoodReq(client.getFoodReq());
        clientFull.setPhoneNo(client.getPhoneNo());
        clientFull.setBirthday(client.getBirthday());
        return clientFull;
    }
}
