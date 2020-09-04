package edu.hust.dao.dao;

import edu.hust.dao.dto.OutRegistration;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: OutRegistration类数据库接口
 * @author: Derry Lin
 * @create: 2020-09-04 11:01
 **/
public interface OutRegistrationMapper {

    //查询记录
    List<OutRegistration> selectList();

    //根据id查询
    OutRegistration selectById(@Param("id") String id);

    //根据起止时间、护士id、客户id查询
    List<OutRegistration> selectByDateAndNurseIdAndClientId(
            @Param("start_out_time") Date startOutTime,
            @Param("end_out_time") Date endOutTime,
            @Param("nurse_id") String nurseId,
            @Param("client_id") String clientId
    );

    //添加记录
    int add(@Param("out_registration") OutRegistration outRegistration);

    //批量添加
    int addBatch(@Param("out_registration_list") List<OutRegistration> outRegistrationList);

    //更新记录
    int update(@Param("out_registration") OutRegistration outRegistration);

    //根据id删除
    int deleteById(@Param("id") String id);

    //删除所有
    int deleteAll();

}
