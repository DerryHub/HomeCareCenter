package edu.hust.dao.dao;

import edu.hust.dao.dto.Bed;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Bed类数据库接口
 * @author: Derry Lin
 * @create: 2020-09-03 09:43
 **/
public interface BedMapper {

    //查找床位信息
    List<Bed> selectList();

    //根据床位id查找
    Bed selectById(@Param("id") String id);

    //根据床位名查找
    Bed selectByBedTitle(@Param("bed_title") String bedTitle);

    //根据房间id查找
    List<Bed> selectByRoomId(@Param("room_id") String roomId);

    //添加床位
    int add(@Param("bed") Bed bed);

    //批量添加床位
    int addBatch(@Param("bed_list") List<Bed> bedList);

    //更新床位信息
    int update(@Param("bed") Bed bed);

    //根据id删除床位
    int deleteById(@Param("id") String id);

    //根据房间号删除床位
    int deleteByRoomId(@Param("room_id") String roomId);

}
