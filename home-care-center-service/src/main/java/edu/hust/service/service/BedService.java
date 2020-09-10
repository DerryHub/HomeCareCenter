package edu.hust.service.service;

import edu.hust.dao.dto.Bed;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Bed类服务层接口
 * @author: Derry Lin
 * @create: 2020-09-07 10:41
 **/
@Service
public interface BedService {

    /**
     * @Author Derry Lin
     * @Description 查找床位信息
     * @Date 下午3:02 2020/9/7
     * @Param []
     * @return java.util.List<edu.hust.dao.dto.Bed>
     **/
    List<Bed> getBedList();

    /**
     * @Author Derry Lin
     * @Description 根据床位id查找
     * @Date 下午3:02 2020/9/7
     * @Param [id]
     * @return edu.hust.dao.dto.Bed
     **/
    Bed getBedById(String id);

    /**
     * @Author Derry Lin
     * @Description 根据床位名查找
     * @Date 下午3:02 2020/9/7
     * @Param [title]
     * @return edu.hust.dao.dto.Bed
     **/
    Bed getBedByTitle(String title);

    /**
     * @Author Derry Lin
     * @Description 根据房间id查找
     * @Date 下午3:02 2020/9/7
     * @Param [roomId]
     * @return java.util.List<edu.hust.dao.dto.Bed>
     **/
    List<Bed> getBedByRoomId(String roomId);

    /**
     * @Author Derry Lin
     * @Description 添加床位
     * @Date 下午3:02 2020/9/7
     * @Param [bed]
     * @return void
     **/
    void addBed(Bed bed);

    /**
     * @Author Derry Lin
     * @Description 批量添加床位
     * @Date 下午3:02 2020/9/7
     * @Param [bedList]
     * @return void
     **/
    void addBedList(List<Bed> bedList);

    /**
     * @Author Derry Lin
     * @Description 更新床位信息
     * @Date 下午3:03 2020/9/7
     * @Param [bed]
     * @return void
     **/
    void updateBed(Bed bed);

    /**
     * @Author Derry Lin
     * @Description 根据id删除床位
     * @Date 下午3:03 2020/9/7
     * @Param [id]
     * @return void
     **/
    void deleteBedById(String id);

    /**
     * @Author Derry Lin
     * @Description 根据房间号删除床位
     * @Date 下午3:03 2020/9/7
     * @Param [roomId]
     * @return void
     **/
    void deleteBedByRoomId(String roomId);

    /**
     * @Author Derry Lin
     * @Description 删除所有
     * @Date 下午3:13 2020/9/7
     * @Param []
     * @return void
     **/
    void deleteAllBed();
}
