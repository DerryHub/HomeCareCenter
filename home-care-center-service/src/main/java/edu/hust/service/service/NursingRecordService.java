package edu.hust.service.service;

import edu.hust.dao.dto.NursingRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: NursingRecord类服务层接口
 * @author: Derry Lin
 * @create: 2020-09-07 14:48
 **/
public interface NursingRecordService {

    /**
     * @Author Derry Lin
     * @Description 查询记录
     * @Date 下午3:08 2020/9/7
     * @Param []
     * @return java.util.List<edu.hust.dao.dto.NursingRecord>
     **/
    List<NursingRecord> getNursingRecordList();

    /**
     * @Author Derry Lin
     * @Description 根据id查询
     * @Date 下午3:08 2020/9/7
     * @Param [id]
     * @return edu.hust.dao.dto.NursingRecord
     **/
    NursingRecord getNursingRecordById(String id);

    /**
     * @Author Derry Lin
     * @Description 根据起止时间、护士id、客户id查询
     * @Date 下午3:08 2020/9/7
     * @Param [startDate, endDate, nurseId, clientId]
     * @return java.util.List<edu.hust.dao.dto.NursingRecord>
     **/
    List<NursingRecord> getNursingRecordByDateAndNurseIdAndClientId(
            Date startDate,
            Date endDate,
            String nurseId,
            String clientId
    );

    /**
     * @Author Derry Lin
     * @Description 添加记录
     * @Date 下午3:08 2020/9/7
     * @Param [nursingRecord]
     * @return void
     **/
    void addNursingRecord(NursingRecord nursingRecord);

    /**
     * @Author Derry Lin
     * @Description 批量添加
     * @Date 下午3:09 2020/9/7
     * @Param [nursingRecordList]
     * @return void
     **/
    void addNursingRecordList(List<NursingRecord> nursingRecordList);

    /**
     * @Author Derry Lin
     * @Description 更新记录
     * @Date 下午3:09 2020/9/7
     * @Param [nursingRecord]
     * @return void
     **/
    void updateNursingRecord(NursingRecord nursingRecord);

    /**
     * @Author Derry Lin
     * @Description 根据id删除
     * @Date 下午3:09 2020/9/7
     * @Param [id]
     * @return void
     **/
    void deleteNursingRecordById(String id);

    /**
     * @Author Derry Lin
     * @Description 删除所有
     * @Date 下午3:22 2020/9/7
     * @Param []
     * @return void
     **/
    void deleteAllNursingRecord();

}
