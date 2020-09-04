package edu.hust.dao.dao;

import edu.hust.dao.dto.MedicalRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: MedicalRecord类数据库接口
 * @author: Derry Lin
 * @create: 2020-09-04 10:31
 **/
public interface MedicalRecordMapper {

    //查询记录
    List<MedicalRecord> selectList();

    //根据id查询
    MedicalRecord selectById(@Param("id") String id);

    //根据起止时间、医生id、客户id查询
    List<MedicalRecord> selectByDateAndDoctorIdAndClientId(
            @Param("start_date") Date startDate,
            @Param("end_date") Date endDate,
            @Param("doctor_id") String doctorId,
            @Param("client_id") String clientId
    );

    //添加记录
    int add(@Param("medical_record") MedicalRecord medicalRecord);

    //批量添加
    int addBatch(@Param("medical_record_list") List<MedicalRecord> medicalRecordList);

    //更新记录
    int update(@Param("medical_record") MedicalRecord medicalRecord);

    //根据id删除
    int deleteById(@Param("id") String id);

    //删除所有
    int deleteAll();

}
