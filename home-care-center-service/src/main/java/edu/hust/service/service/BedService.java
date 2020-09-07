package edu.hust.service.service;

import edu.hust.dao.dto.Bed;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Bed类服务层接口
 * @author: Derry Lin
 * @create: 2020-09-07 10:41
 **/
public interface BedService {

    /**
     * 查找床位信息
     * @return
     */
    List<Bed> getBedList();

    /**
     * 根据床位id查找
     * @param id
     * @return
     */
    Bed getBedById(String id);

    /**
     * 根据床位名查找
     * @param title
     * @return
     */
    Bed getBedByTitle(String title);

    /**
     * 根据房间id查找
     * @param roomId
     * @return
     */
    List<Bed> getBedByRoomId(String roomId);

    /**
     * 添加床位
     * @param bed
     */
    void addBed(Bed bed);

    /**
     * 批量添加床位
     * @param bedList
     */
    void addBedList(List<Bed> bedList);

    /**
     * 更新床位信息
     * @param bed
     */
    void updateBed(Bed bed);

    /**
     * 根据id删除床位
     * @param id
     */
    void deleteBedById(String id);

    /**
     * 根据房间号删除床位
     * @param roomId
     */
    void deleteBedByRoomId(String roomId);

    /**
     * 删除所有
     */
    void deleteAllBed();
}
