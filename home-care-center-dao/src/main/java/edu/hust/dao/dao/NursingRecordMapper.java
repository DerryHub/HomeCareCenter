package edu.hust.dao.dao;

import edu.hust.dao.dto.NursingRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: NursingRecord类数据库接口
 * @author: Derry Lin
 * @create: 2020-09-04 09:37
 **/
public interface NursingRecordMapper {

    //查询记录
    List<NursingRecord> selectList();

    //根据id查询
    NursingRecord selectById(@Param("id") String id);

    //根据起止时间、护士id、客户id查询
    List<NursingRecord> selectByDateAndNurseIdAndClientId(
            @Param("start_date") Date startDate,
            @Param("end_date") Date endDate,
            @Param("nurse_id") String nurseId,
            @Param("client_id") String clientId
    );

    //添加记录
    int add(@Param("nursing_record") NursingRecord nursingRecord);

    //批量添加
    int addBatch(@Param("nursing_record_list") List<NursingRecord> nursingRecordList);

    //更新记录
    int update(@Param("nursing_record") NursingRecord nursingRecord);

    //根据id删除
    int deleteById(@Param("id") String id);

    //删除所有
    int deleteAll();

}
