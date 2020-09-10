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
@Mapper
public interface BedMapper {

    /**
     * @Author Derry Lin
     * @Description 查找床位信息
     * @Date 下午3:20 2020/9/7
     * @Param []
     * @return java.util.List<edu.hust.dao.dto.Bed>
     **/
    List<Bed> selectList();

    /**
     * @Author Derry Lin
     * @Description 根据床位id查找
     * @Date 下午3:21 2020/9/7
     * @Param [id]
     * @return edu.hust.dao.dto.Bed
     **/
    Bed selectById(@Param("id") String id);

    /**
     * @Author Derry Lin
     * @Description 根据床位名查找
     * @Date 下午3:21 2020/9/7
     * @Param [bedTitle]
     * @return edu.hust.dao.dto.Bed
     **/
    Bed selectByBedTitle(@Param("bed_title") String bedTitle);

    /**
     * @Author Derry Lin
     * @Description 根据房间id查找
     * @Date 下午3:21 2020/9/7
     * @Param [roomId]
     * @return java.util.List<edu.hust.dao.dto.Bed>
     **/
    List<Bed> selectByRoomId(@Param("room_id") String roomId);

    /**
     * @Author Derry Lin
     * @Description 添加床位
     * @Date 下午3:21 2020/9/7
     * @Param [bed]
     * @return int
     **/
    int add(@Param("bed") Bed bed);

    /**
     * @Author Derry Lin
     * @Description 批量添加床位
     * @Date 下午3:21 2020/9/7
     * @Param [bedList]
     * @return int
     **/
    int addBatch(@Param("bed_list") List<Bed> bedList);

    /**
     * @Author Derry Lin
     * @Description 更新床位信息
     * @Date 下午3:21 2020/9/7
     * @Param [bed]
     * @return int
     **/
    int update(@Param("bed") Bed bed);

    /**
     * @Author Derry Lin
     * @Description 根据id删除床位
     * @Date 下午3:21 2020/9/7
     * @Param [id]
     * @return int
     **/
    int deleteById(@Param("id") String id);

    /**
     * @Author Derry Lin
     * @Description 根据房间号删除床位
     * @Date 下午3:21 2020/9/7
     * @Param [roomId]
     * @return int
     **/
    int deleteByRoomId(@Param("room_id") String roomId);

    /**
     * @Author Derry Lin
     * @Description 删除所有
     * @Date 下午3:29 2020/9/7
     * @Param []
     * @return int
     **/
    int deleteAll();

}
