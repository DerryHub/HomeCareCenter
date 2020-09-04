package edu.hust.dao.dao;

import edu.hust.dao.dto.Worker;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Worker类数据库接口
 * @author: Derry Lin
 * @create: 2020-09-04 08:37
 **/
public interface WorkerMapper {

    //查询工人
    List<Worker> selectList();

    //根据id查询
    Worker selectById(@Param("id") String id);

    //根据身份证号查询
    Worker selectByIdCardNo(@Param("id_card_no") String idCardNo);

    //根据工人种类查询
    List<Worker> selectByType(@Param("type") int type);

    //根据姓名查询
    List<Worker> selectByName(@Param("name") String name);

    //添加工人
    int add(@Param("worker") Worker worker);

    //批量添加
    int addBatch(@Param("worker_list") List<Worker> workerList);

    //更新工人信息
    int update(@Param("worker") Worker worker);

    //根据id删除
    int deleteById(@Param("id") String id);

    //根据种类删除
    int deleteByType(@Param("type") int type);

    //根据身份证号删除
    int deleteByIdCardNo(@Param("id_card_no") String idCardNo);

    //删除所有
    int deleteAll();

}
