package edu.hust.service.service;

import edu.hust.dao.dto.MedicalRecord;
import edu.hust.service.domain.MedicalRecordFull;

import java.util.Date;
import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: MedicalRecord类服务层接口
 * @author: Derry Lin
 * @create: 2020-09-07 14:44
 **/
public interface MedicalRecordService {

    /**
     * @Author Derry Lin
     * @Description 查询记录
     * @Date 下午3:07 2020/9/7
     * @Param []
     * @return java.util.List<edu.hust.dao.dto.MedicalRecord>
     **/
    List<MedicalRecordFull> getMedicalRecordList();

    /**
     * @Author Derry Lin
     * @Description 根据id查询
     * @Date 下午3:07 2020/9/7
     * @Param [id]
     * @return edu.hust.dao.dto.MedicalRecord
     **/
    MedicalRecordFull getMedicalRecordById(String id);

    /**
     * @Author Derry Lin
     * @Description 根据起止时间、医生id、客户id查询
     * @Date 下午3:07 2020/9/7
     * @Param [startDate, endDate, doctorId, clientId]
     * @return java.util.List<edu.hust.dao.dto.MedicalRecord>
     **/
    List<MedicalRecordFull> getMedicalRecordByDateAndDoctorIdAndClientId(
            Date startDate,
            Date endDate,
            String doctorId,
            String clientId
    );

    /**
     * @Author Derry Lin
     * @Description 添加记录
     * @Date 下午3:08 2020/9/7
     * @Param [medicalRecord]
     * @return void
     **/
    void addMedicalRecord(MedicalRecord medicalRecord);

    /**
     * @Author Derry Lin
     * @Description 批量添加
     * @Date 下午3:08 2020/9/7
     * @Param [medicalRecordList]
     * @return void
     **/
    void addMedicalRecordList(List<MedicalRecord> medicalRecordList);

    /**
     * @Author Derry Lin
     * @Description 更新记录
     * @Date 下午3:08 2020/9/7
     * @Param [medicalRecord]
     * @return void
     **/           
    void updateMedicalRecord(MedicalRecord medicalRecord);

    /**
     * @Author Derry Lin
     * @Description 根据id删除
     * @Date 下午3:08 2020/9/7
     * @Param [id]
     * @return void
     **/
    void deleteMedicalRecordById(String id);

    /**
     * @Author Derry Lin
     * @Description 删除所有
     * @Date 下午3:21 2020/9/7
     * @Param []
     * @return void
     **/
    void deleteAllMedicalRecord();

}
