package edu.hust.service.service;

import edu.hust.dao.dto.Client;
import edu.hust.service.domain.ClientFull;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Client服务层接口
 * @author: Derry Lin
 * @create: 2020-09-07 08:47
 **/
public interface ClientService {

    /**
     * 根据id查客户
     * @param id
     * @return
     */
    ClientFull getClientInfoById(String id);

    /**
     * 根据身份证号查客户
     * @param idCardNo
     * @return
     */
    ClientFull getClientInfoByIdCardNo(String idCardNo);

    /**
     * 获取所有客户
     * @return
     */
    List<ClientFull> getClientInfoList();

    /**
     * 根据姓名获取用户
     * @param name
     * @return
     */
    List<ClientFull> getClientInfoByName(String name);

    /**
     * 根据床铺id查询客户
     * @param bedId
     * @return
     */
    ClientFull getClientInfoByBedId(int bedId);

    /**
     * 添加客户
     * @param client
     */
    void addClient(Client client);

    /**
     * 批量添加何客户
     * @param clientList
     */
    void addClientList(List<Client> clientList);

    /**
     * 更新客户信息
     * @param client
     */
    void updateClient(Client client);

    /**
     * 根据客户id删除
     * @param id
     */
    void deleteClientById(String id);

    /**
     * 根据身份证号删除
     * @param idCardNo
     */
    void deleteClientByIdCardNo(String idCardNo);

    /**
     * 删除所有客户
     */
    void deleteAllClient();

}
