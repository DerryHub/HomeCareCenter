package edu.hust.dao.dao;

import edu.hust.dao.dto.OutRegistration;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: OutRegistration类数据库接口
 * @author: Derry Lin
 * @create: 2020-09-04 11:01
 **/
@Mapper
public interface OutRegistrationMapper {

    /**
     * @Author Derry Lin
     * @Description 查询记录
     * @Date 下午3:25 2020/9/7
     * @Param []
     * @return java.util.List<edu.hust.dao.dto.OutRegistration>
     **/
    List<OutRegistration> selectList();

    /**
     * @Author Derry Lin
     * @Description 根据id查询
     * @Date 下午3:25 2020/9/7
     * @Param [id]
     * @return edu.hust.dao.dto.OutRegistration
     **/
    OutRegistration selectById(@Param("id") String id);

    /**
     * @Author Derry Lin
     * @Description 根据起止时间、护士id、客户id查询
     * @Date 下午3:25 2020/9/7
     * @Param [startOutTime, endOutTime, nurseId, clientId]
     * @return java.util.List<edu.hust.dao.dto.OutRegistration>
     **/
    List<OutRegistration> selectByDateAndNurseIdAndClientId(
            @Param("start_out_time") Date startOutTime,
            @Param("end_out_time") Date endOutTime,
            @Param("nurse_id") String nurseId,
            @Param("client_id") String clientId
    );

    /**
     * @Author Derry Lin
     * @Description 查询未回归的客户
     * @Date 下午3:25 2020/9/7
     * @Param []
     * @return java.util.List<edu.hust.dao.dto.OutRegistration>
     **/
    List<OutRegistration> selectByBackTimeReal();

    /**
     * @Author Derry Lin
     * @Description 添加记录
     * @Date 下午3:25 2020/9/7
     * @Param [outRegistration]
     * @return int
     **/
    int add(@Param("out_registration") OutRegistration outRegistration);

    /**
     * @Author Derry Lin
     * @Description 批量添加
     * @Date 下午3:25 2020/9/7
     * @Param [outRegistrationList]
     * @return int
     **/
    int addBatch(@Param("out_registration_list") List<OutRegistration> outRegistrationList);

    /**
     * @Author Derry Lin
     * @Description 更新记录
     * @Date 下午3:25 2020/9/7
     * @Param [outRegistration]
     * @return int
     **/
    int update(@Param("out_registration") OutRegistration outRegistration);

    /**
     * @Author Derry Lin
     * @Description 根据id删除
     * @Date 下午3:25 2020/9/7
     * @Param [id]
     * @return int
     **/
    int deleteById(@Param("id") String id);

    /**
     * @Author Derry Lin
     * @Description 删除所有
     * @Date 下午4:18 2020/9/7
     * @Param []
     * @return int
     **/
    int deleteAll();

}
