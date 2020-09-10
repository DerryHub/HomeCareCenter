package edu.hust.service.service;

import edu.hust.dao.dto.OutRegistration;
import edu.hust.service.domain.OutRegistrationFull;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: OutRegistration类服务层接口
 * @author: Derry Lin
 * @create: 2020-09-07 14:51
 **/
public interface OutRegistrationService {

    /**
     * @Author Derry Lin
     * @Description 查询记录
     * @Date 下午3:09 2020/9/7
     * @Param []
     * @return java.util.List<edu.hust.dao.dto.OutRegistration>
     **/
    List<OutRegistrationFull> getOutRegistrationList();

    /**
     * @Author Derry Lin
     * @Description 根据id查询
     * @Date 下午3:10 2020/9/7
     * @Param [id]
     * @return edu.hust.dao.dto.OutRegistration
     **/
    OutRegistrationFull getOutRegistrationById(String id);

    /**
     * @Author Derry Lin
     * @Description 根据起止时间、护士id、客户id查询
     * @Date 下午3:10 2020/9/7
     * @Param [startOutTime, endOutTime, nurseId, clientId]
     * @return java.util.List<edu.hust.dao.dto.OutRegistration>
     **/
    List<OutRegistrationFull> getOutRegistrationByDateAndNurseIdAndClientId(
            Date startOutTime,
            Date endOutTime,
            String nurseId,
            String clientId
    );

    /**
     * @Author Derry Lin
     * @Description 查询未回归的客户
     * @Date 下午3:10 2020/9/7
     * @Param []
     * @return java.util.List<edu.hust.dao.dto.OutRegistration>
     **/
    List<OutRegistrationFull> getOutRegistrationByBackTimeReal();

    /**
     * @Author Derry Lin
     * @Description 添加记录
     * @Date 下午3:10 2020/9/7
     * @Param [outRegistration]
     * @return void
     **/
    void addOutRegistration(OutRegistration outRegistration);

    /**
     * @Author Derry Lin
     * @Description 批量添加
     * @Date 下午3:10 2020/9/7
     * @Param [outRegistrationList]
     * @return void
     **/
    void addOutRegistrationList(List<OutRegistration> outRegistrationList);

    /**
     * @Author Derry Lin
     * @Description 更新记录
     * @Date 下午3:10 2020/9/7
     * @Param [outRegistration]
     * @return void
     **/
    void updateOutRegistration(OutRegistration outRegistration);

    /**
     * @Author Derry Lin
     * @Description 根据id删除
     * @Date 下午3:10 2020/9/7
     * @Param [id]
     * @return void
     **/
    void deleteOutRegistrationById(String id);

    /**
     * @Author Derry Lin
     * @Description 删除所有
     * @Date 下午3:23 2020/9/7
     * @Param []
     * @return void
     **/
    void deleteAllOutRegistration();

}
