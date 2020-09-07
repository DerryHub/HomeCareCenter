package edu.hust.dao.dao;

import edu.hust.dao.dto.Client;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Client类数据库接口
 * @author: Derry Lin
 * @create: 2020-09-03 16:24
 **/
public interface ClientMapper {

    /**
     * @Author Derry Lin
     * @Description 查询客人信息
     * @Date 下午3:21 2020/9/7
     * @Param []
     * @return java.util.List<edu.hust.dao.dto.Client>
     **/
    List<Client> selectList();

    /**
     * @Author Derry Lin
     * @Description 根据id查询
     * @Date 下午3:21 2020/9/7
     * @Param [id]
     * @return edu.hust.dao.dto.Client
     **/
    Client selectById(@Param("id") String id);

    /**
     * @Author Derry Lin
     * @Description 根据身份证号查询
     * @Date 下午3:21 2020/9/7
     * @Param [idCardNo]
     * @return edu.hust.dao.dto.Client
     **/
    Client selectByIdCardNo(@Param("id_card_no") String idCardNo);

    /**
     * @Author Derry Lin
     * @Description 根据姓名查询
     * @Date 下午3:21 2020/9/7
     * @Param [name]
     * @return java.util.List<edu.hust.dao.dto.Client>
     **/
    List<Client> selectByName(@Param("name") String name);

    /**
     * @Author Derry Lin
     * @Description 根据床铺id查询
     * @Date 下午3:22 2020/9/7
     * @Param [bedId]
     * @return edu.hust.dao.dto.Client
     **/
    Client selectByBedId(@Param("bed_id") String bedId);

    /**
     * @Author Derry Lin
     * @Description 添加客人
     * @Date 下午3:22 2020/9/7
     * @Param [client]
     * @return int
     **/
    int add(@Param("client") Client client);

    /**
     * @Author Derry Lin
     * @Description 批量添加客人
     * @Date 下午3:22 2020/9/7
     * @Param [clientList]
     * @return int
     **/
    int addBatch(@Param("client_list") List<Client> clientList);

    /**
     * @Author Derry Lin
     * @Description 更新客人信息
     * @Date 下午3:22 2020/9/7
     * @Param [client]
     * @return int
     **/
    int update(@Param("client") Client client);

    /**
     * @Author Derry Lin
     * @Description 根据客人id删除
     * @Date 下午3:22 2020/9/7
     * @Param [id]
     * @return int
     **/
    int deleteById(@Param("id") String id);

    /**
     * @Author Derry Lin
     * @Description 根据身份证号删除
     * @Date 下午3:22 2020/9/7
     * @Param [idCardNo]
     * @return int
     **/
    int deleteByIdCardNo(@Param("id_card_no") String idCardNo);

    /**
     * @Author Derry Lin
     * @Description 删除所有
     * @Date 下午3:36 2020/9/7
     * @Param []
     * @return int
     **/
    int deleteAll();

}
