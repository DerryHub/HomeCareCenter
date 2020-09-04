package edu.hust.dao.dao;

import edu.hust.dao.dto.Room;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Room类数据库接口
 * @author: Derry Lin
 * @create: 2020-09-03 16:01
 **/
public interface RoomMapper {

    //查找房间信息
    List<Room> selectList();

    //根据房间id查找
    Room selectById(@Param("id") String id);

    //根据房间名查找
    Room selectByRoomTitle(@Param("room_title") String roomTitle);

    //根据区域id查找
    List<Room> selectByAreaId(@Param("area_id") String areaId);

    //添加房间
    int add(@Param("room") Room room);

    //批量添加房间
    int addBatch(@Param("room_list") List<Room> roomList);

    //更新房间
    int update(@Param("room") Room room);

    //根据房间id删除
    int deleteById(@Param("id") String id);

    //根据区域id删除
    int deleteByAreaId(@Param("area_id") String areaId);

    //删除所有
    int deleteAll();

}
