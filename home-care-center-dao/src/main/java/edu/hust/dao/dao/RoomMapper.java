package edu.hust.dao.dao;

import edu.hust.dao.dto.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Room类数据库接口
 * @author: Derry Lin
 * @create: 2020-09-03 16:01
 **/
@Mapper
public interface RoomMapper {

    /**
     * @Author Derry Lin
     * @Description 查找房间信息
     * @Date 下午3:26 2020/9/7
     * @Param []
     * @return java.util.List<edu.hust.dao.dto.Room>
     **/
    List<Room> selectList();

    /**
     * @Author Derry Lin
     * @Description 根据房间id查找
     * @Date 下午3:26 2020/9/7
     * @Param [id]
     * @return edu.hust.dao.dto.Room
     **/
    Room selectById(@Param("id") String id);

    /**
     * @Author Derry Lin
     * @Description 根据房间名查找
     * @Date 下午3:26 2020/9/7
     * @Param [roomTitle]
     * @return edu.hust.dao.dto.Room
     **/
    Room selectByRoomTitle(@Param("room_title") String roomTitle);

    /**
     * @Author Derry Lin
     * @Description 根据区域id查找
     * @Date 下午3:26 2020/9/7
     * @Param [areaId]
     * @return java.util.List<edu.hust.dao.dto.Room>
     **/
    List<Room> selectByAreaId(@Param("area_id") String areaId);

    /**
     * @Author Derry Lin
     * @Description 添加房间
     * @Date 下午3:26 2020/9/7
     * @Param [room]
     * @return int
     **/
    int add(@Param("room") Room room);

    /**
     * @Author Derry Lin
     * @Description 批量添加房间
     * @Date 下午3:26 2020/9/7
     * @Param [roomList]
     * @return int
     **/
    int addBatch(@Param("room_list") List<Room> roomList);

    /**
     * @Author Derry Lin
     * @Description 更新房间
     * @Date 下午3:26 2020/9/7
     * @Param [room]
     * @return int
     **/
    int update(@Param("room") Room room);

    /**
     * @Author Derry Lin
     * @Description 根据房间id删除
     * @Date 下午3:26 2020/9/7
     * @Param [id]
     * @return int
     **/
    int deleteById(@Param("id") String id);

    /**
     * @Author Derry Lin
     * @Description 根据区域id删除
     * @Date 下午3:26 2020/9/7
     * @Param [areaId]
     * @return int
     **/
    int deleteByAreaId(@Param("area_id") String areaId);

    /**
     * @Author Derry Lin
     * @Description 删除所有
     * @Date 下午4:23 2020/9/7
     * @Param []
     * @return int
     **/
    int deleteAll();

}
