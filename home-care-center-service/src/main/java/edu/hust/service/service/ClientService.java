package edu.hust.service.service;

import edu.hust.dao.dto.Client;
import edu.hust.service.domain.ClientFull;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Client服务层接口
 * @author: Derry Lin
 * @create: 2020-09-07 08:47
 **/
@Service
public interface ClientService {

    /**
     * @Author Derry Lin
     * @Description 根据id查客户
     * @Date 下午3:03 2020/9/7
     * @Param [id]
     * @return edu.hust.service.domain.ClientFull
     **/
    ClientFull getClientInfoById(String id);

    /**
     * @Author Derry Lin
     * @Description 根据身份证号查客户
     * @Date 下午3:03 2020/9/7
     * @Param [idCardNo]
     * @return edu.hust.service.domain.ClientFull
     **/
    ClientFull getClientInfoByIdCardNo(String idCardNo);

    /**
     * @Author Derry Lin
     * @Description 获取所有客户
     * @Date 下午3:03 2020/9/7
     * @Param []
     * @return java.util.List<edu.hust.service.domain.ClientFull>
     **/
    List<ClientFull> getClientInfoList();

    /**
     * @Author Derry Lin
     * @Description 根据姓名获取用户
     * @Date 下午3:03 2020/9/7
     * @Param [name]
     * @return java.util.List<edu.hust.service.domain.ClientFull>
     **/
    List<ClientFull> getClientInfoByName(String name);

    /**
     * @Author Derry Lin
     * @Description 根据床铺id查询客户
     * @Date 下午3:03 2020/9/7
     * @Param [bedId]
     * @return edu.hust.service.domain.ClientFull
     **/
    ClientFull getClientInfoByBedId(String bedId);

    /**
     * @Author Derry Lin
     * @Description 添加客户
     * @Date 下午3:03 2020/9/7
     * @Param [client]
     * @return void
     **/
    void addClient(Client client);

    /**
     * @Author Derry Lin
     * @Description 批量添加何客户
     * @Date 下午3:04 2020/9/7
     * @Param [clientList]
     * @return void
     **/
    void addClientList(List<Client> clientList);

    /**
     * @Author Derry Lin
     * @Description 更新客户信息
     * @Date 下午3:04 2020/9/7
     * @Param [client]
     * @return void
     **/
    void updateClient(Client client);

    /**
     * @Author Derry Lin
     * @Description 根据客户id删除
     * @Date 下午3:04 2020/9/7
     * @Param [id]
     * @return void
     **/
    void deleteClientById(String id);

    /**
     * @Author Derry Lin
     * @Description 根据身份证号删除
     * @Date 下午3:04 2020/9/7
     * @Param [idCardNo]
     * @return void
     **/
    void deleteClientByIdCardNo(String idCardNo);

    /**
     * @Author Derry Lin
     * @Description 删除所有客户
     * @Date 下午3:13 2020/9/7
     * @Param []
     * @return void
     **/
    void deleteAllClient();

}
