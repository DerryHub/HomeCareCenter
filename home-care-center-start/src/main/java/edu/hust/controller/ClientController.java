package edu.hust.controller;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.common.util.RandomUUID;
import edu.hust.common.vo.ApiResult;
import edu.hust.dao.dto.Bed;
import edu.hust.dao.dto.Client;
import edu.hust.monitor.Monitor;
import edu.hust.service.domain.ClientFull;
import edu.hust.service.service.BedService;
import edu.hust.service.service.ClientService;
import edu.hust.common.exception.GlobalException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Client控制类
 * @author: Derry Lin
 * @create: 2020-09-09 09:25
 **/
@CrossOrigin
@RestController
@Api("客户接口")
@RequestMapping("HomeCareCenter/client/")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private BedService bedService;

    @Autowired
    private RandomUUID randomUUID;

    @ApiOperation("客户查询")
    @GetMapping("search")
    @Monitor("searchClient")
    public ApiResult search(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "idCardNo", required = false) String idCardNo,
            @RequestParam(value = "bedId", required = false) String bedId
    ) {
        if (id == null && name == null && idCardNo == null && bedId == null) {
            List<ClientFull> clientFullList = clientService.getClientInfoList();
            return ApiResult.buildSuccess(clientFullList);
        } else if (name == null && idCardNo == null && bedId == null) {
            ClientFull clientFull = clientService.getClientInfoById(id);
            return ApiResult.buildSuccess(clientFull);
        } else if (id == null && idCardNo == null && bedId == null) {
            List<ClientFull> clientFullList = clientService.getClientInfoByName(name);
            return ApiResult.buildSuccess(clientFullList);
        } else if (id == null && name == null && bedId == null) {
            ClientFull clientFull = clientService.getClientInfoByIdCardNo(idCardNo);
            return ApiResult.buildSuccess(clientFull);
        } else if (id == null && name == null && idCardNo == null) {
            ClientFull clientFull = clientService.getClientInfoByBedId(bedId);
            return ApiResult.buildSuccess(clientFull);
        } else {
            throw new GlobalException(ApiCodeEnum.PARAM_ERROR);
        }
    }

    @ApiOperation("添加客户")
    @PostMapping("add")
    @Monitor("addClient")
    public ApiResult add(@RequestBody Client client) {
        if (!legal(client)) {
            throw new GlobalException(ApiCodeEnum.ILLEGAL_DATA);
        }
        client.setId(randomUUID.nextIdStr());
        clientService.addClient(client);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("批量添加客户")
    @PostMapping("addBatch")
    @Monitor("addBatchClient")
    public ApiResult addBatch(@RequestBody List<Client> clientList) {
        for (Client client : clientList) {
            if (!legal(client)) {
                throw new GlobalException(ApiCodeEnum.ILLEGAL_DATA);
            }
            client.setId(randomUUID.nextIdStr());
        }
        clientService.addClientList(clientList);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("更新客户")
    @PostMapping("update")
    @Monitor("updateClient")
    public ApiResult update(@RequestBody Client client) {
        if (
                client.getBedId() != null
                && bedService.getBedById(client.getBedId()) == null
        ) {
            throw new GlobalException(ApiCodeEnum.ILLEGAL_DATA);
        }
        clientService.updateClient(client);
        return ApiResult.buildSuccess();
    }

    @ApiOperation("删除客户")
    @GetMapping("delete")
    @Monitor("deleteClient")
    public ApiResult delete(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "idCardNo", required = false) String idCardNo
    ) {
        if (id == null && idCardNo == null) {
            clientService.deleteAllClient();
            return ApiResult.buildSuccess();
        } else if (id == null) {
            clientService.deleteClientByIdCardNo(idCardNo);
            return ApiResult.buildSuccess();
        } else if (idCardNo == null) {
            clientService.deleteClientById(id);
            return ApiResult.buildSuccess();
        } else {
            throw new GlobalException(ApiCodeEnum.PARAM_ERROR);
        }
    }

    private boolean legal(Client client) {
        if (
                client.getName() == null
                || (
                        client.getGender() != 0
                        && client.getGender() != 1
                        )
                || client.getIdCardNo() == null
                || (
                        client.getMarriage() != 0
                        && client.getMarriage() != 1
                        )
                || client.getHeadImg() == null
                || client.getBedId() == null
                || bedService.getBedById(client.getBedId()) == null
                || (
                        client.getLevelOfCare() != 1
                        && client.getLevelOfCare() != 2
                        && client.getLevelOfCare() != 3
                        )
                || client.getInDate() == null
                || client.getPhoneNo() == null
        ) {
            return false;
        }
        String bedId = client.getBedId();
        List<Bed> bedList = bedService.getEmptyBed();
        List<String> idList = new ArrayList<>();
        for (Bed bed : bedList) {
            idList.add(bed.getId());
        }
        if (!idList.contains(bedId)) {
            return false;
        }
        return true;
    }
}