package edu.hust.dao.dao;

import edu.hust.dao.dto.NursingRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: NursingRecord类数据库接口
 * @author: Derry Lin
 * @create: 2020-09-04 09:37
 **/
@Mapper
public interface NursingRecordMapper {

    /**
     * @Author Derry Lin
     * @Description 查询记录
     * @Date 下午3:24 2020/9/7
     * @Param []
     * @return java.util.List<edu.hust.dao.dto.NursingRecord>
     **/
    List<NursingRecord> selectList();

    /**
     * @Author Derry Lin
     * @Description 
     * @Date 下午3:24 2020/9/7
     * @Param [id]
     * @return edu.hust.dao.dto.NursingRecord
     **/
    NursingRecord selectById(@Param("id") String id);

    /**
     * @Author Derry Lin
     * @Description 根据起止时间、护士id、客户id查询
     * @Date 下午3:24 2020/9/7
     * @Param [startDate, endDate, nurseId, clientId]
     * @return java.util.List<edu.hust.dao.dto.NursingRecord>
     **/
    List<NursingRecord> selectByDateAndNurseIdAndClientId(
            @Param("start_date") Date startDate,
            @Param("end_date") Date endDate,
            @Param("nurse_id") String nurseId,
            @Param("client_id") String clientId
    );

    /**
     * @Author Derry Lin
     * @Description 添加记录
     * @Date 下午3:24 2020/9/7
     * @Param [nursingRecord]
     * @return int
     **/
    int add(@Param("nursing_record") NursingRecord nursingRecord);

    /**
     * @Author Derry Lin
     * @Description 批量添加
     * @Date 下午3:25 2020/9/7
     * @Param [nursingRecordList]
     * @return int
     **/
    int addBatch(@Param("nursing_record_list") List<NursingRecord> nursingRecordList);

    /**
     * @Author Derry Lin
     * @Description 更新记录
     * @Date 下午3:25 2020/9/7
     * @Param [nursingRecord]
     * @return int
     **/
    int update(@Param("nursing_record") NursingRecord nursingRecord);

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
     * @Date 下午4:13 2020/9/7
     * @Param []
     * @return int
     **/
    int deleteAll();

}
