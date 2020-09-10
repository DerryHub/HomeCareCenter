package edu.hust.dao.dao;

import edu.hust.dao.dto.MedicalRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: MedicalRecord类数据库接口
 * @author: Derry Lin
 * @create: 2020-09-04 10:31
 **/
@Mapper
public interface MedicalRecordMapper {

    /**
     * @Author Derry Lin
     * @Description 查询记录
     * @Date 下午3:24 2020/9/7
     * @Param []
     * @return java.util.List<edu.hust.dao.dto.MedicalRecord>
     **/
    List<MedicalRecord> selectList();

    /**
     * @Author Derry Lin
     * @Description 根据id查询
     * @Date 下午3:24 2020/9/7
     * @Param [id]
     * @return edu.hust.dao.dto.MedicalRecord
     **/
    MedicalRecord selectById(@Param("id") String id);

    /**
     * @Author Derry Lin
     * @Description 根据起止时间、医生id、客户id查询
     * @Date 下午3:24 2020/9/7
     * @Param [startDate, endDate, doctorId, clientId]
     * @return java.util.List<edu.hust.dao.dto.MedicalRecord>
     **/
    List<MedicalRecord> selectByDateAndDoctorIdAndClientId(
            @Param("start_date") Date startDate,
            @Param("end_date") Date endDate,
            @Param("doctor_id") String doctorId,
            @Param("client_id") String clientId
    );

    /**
     * @Author Derry Lin
     * @Description 添加记录
     * @Date 下午3:24 2020/9/7
     * @Param [medicalRecord]
     * @return int
     **/
    int add(@Param("medical_record") MedicalRecord medicalRecord);

    /**
     * @Author Derry Lin
     * @Description 批量添加
     * @Date 下午3:24 2020/9/7
     * @Param [medicalRecordList]
     * @return int
     **/
    int addBatch(@Param("medical_record_list") List<MedicalRecord> medicalRecordList);

    /**
     * @Author Derry Lin
     * @Description 更新记录
     * @Date 下午3:24 2020/9/7
     * @Param [medicalRecord]
     * @return int
     **/
    int update(@Param("medical_record") MedicalRecord medicalRecord);

    /**
     * @Author Derry Lin
     * @Description 根据id删除
     * @Date 下午3:24 2020/9/7
     * @Param [id]
     * @return int
     **/
    int deleteById(@Param("id") String id);

    /**
     * @Author Derry Lin
     * @Description 删除所有
     * @Date 下午3:50 2020/9/7
     * @Param []
     * @return int
     **/
    int deleteAll();

}
