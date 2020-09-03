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

    //查询客人信息
    List<Client> selectList();

    //根据id查询
    Client selectById(@Param("id") String id);

    //根据身份证号查询
    Client selectByIdCardNo(@Param("id_card_no") String idCardNo);

    //根据姓名查询
    List<Client> selectByName(@Param("name") String name);

    //根据床铺id查询
    Client selectByBedId(@Param("bed_id") String bedId);

    //添加客人
    int add(@Param("client") Client client);

    //批量添加客人
    int addBatch(@Param("client_list") List<Client> clientList);

    //更新客人信息
    int update(@Param("client") Client client);

    //根据客人id删除
    int deleteById(@Param("id") String id);

    //根据身份证号删除
    int deleteByIdCardNo(@Param("id_card_no") String idCardNo);

}
