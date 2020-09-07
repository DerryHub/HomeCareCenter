package edu.hust.service.service;

import edu.hust.dao.dto.Room;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Room类服务层接口
 * @author: Derry Lin
 * @create: 2020-09-07 15:10
 **/
public interface RoomService {

    /**
     * @Author Derry Lin
     * @Description 查找房间信息
     * @Date 下午3:12 2020/9/7
     * @Param []
     * @return java.util.List<edu.hust.dao.dto.Room>
     **/
    List<Room> getRoomList();

    /**
     * @Author Derry Lin
     * @Description 根据房间id查找
     * @Date 下午3:12 2020/9/7
     * @Param [id]
     * @return edu.hust.dao.dto.Room
     **/
    Room getRoomById(String id);

    /**
     * @Author Derry Lin
     * @Description 根据房间名查找
     * @Date 下午3:12 2020/9/7
     * @Param [roomTitle]
     * @return edu.hust.dao.dto.Room
     **/
    Room getRoomByRoomTitle(String roomTitle);

    /**
     * @Author Derry Lin
     * @Description 根据区域id查找
     * @Date 下午3:12 2020/9/7
     * @Param [areaId]
     * @return java.util.List<edu.hust.dao.dto.Room>
     **/
    List<Room> getRoomByAreaId(String areaId);

    /**
     * @Author Derry Lin
     * @Description 添加房间
     * @Date 下午3:12 2020/9/7
     * @Param [room]
     * @return void
     **/
    void addRoom(Room room);

    /**
     * @Author Derry Lin
     * @Description 批量添加房间
     * @Date 下午3:12 2020/9/7
     * @Param [roomList]
     * @return void
     **/
    void addRoomList(List<Room> roomList);

    /**
     * @Author Derry Lin
     * @Description 更新房间
     * @Date 下午3:12 2020/9/7
     * @Param [room]
     * @return void
     **/
    void updateRoom(Room room);

    /**
     * @Author Derry Lin
     * @Description 根据房间id删除
     * @Date 下午3:13 2020/9/7
     * @Param [id]
     * @return void
     **/
    void deleteRoomById(String id);

    /**
     * @Author Derry Lin
     * @Description 根据区域id删除
     * @Date 下午3:13 2020/9/7
     * @Param [areaId]
     * @return void
     **/
    void deleteRoomByAreaId(String areaId);

    /**
     * @Author Derry Lin
     * @Description 删除所有
     * @Date 下午3:13 2020/9/7
     * @Param []
     * @return void
     **/
    void deleteAllRoom();

}
