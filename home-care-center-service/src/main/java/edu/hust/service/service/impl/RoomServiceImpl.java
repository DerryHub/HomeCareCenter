package edu.hust.service.service.impl;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.dao.dao.RoomMapper;
import edu.hust.dao.dto.Room;
import edu.hust.service.service.RoomService;
import edu.hust.start.interceptor.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Room类服务层类
 * @author: Derry Lin
 * @create: 2020-09-08 09:20
 **/
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public List<Room> getRoomList() {
        return roomMapper.selectList();
    }

    @Override
    public Room getRoomById(String id) {
        return roomMapper.selectById(id);
    }

    @Override
    public Room getRoomByRoomTitle(String title) {
        return roomMapper.selectByRoomTitle(title);
    }

    @Override
    public List<Room> getRoomByAreaId(String areaId) {
        return roomMapper.selectByAreaId(areaId);
    }

    @Override
    public void addRoom(Room room) {
        if (roomMapper.add(room) == 0) {
            throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
        }
    }

    @Override
    public void addRoomList(List<Room> roomList) {
        if (roomMapper.addBatch(roomList) != roomList.size()) {
            throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
        }
    }

    @Override
    public void updateRoom(Room room) {
        if (roomMapper.update(room) == 0) {
            throw new GlobalException(ApiCodeEnum.FAIL_TO_UPDATE);
        }
    }

    @Override
    public void deleteRoomById(String id) {
        roomMapper.deleteById(id);
    }

    @Override
    public void deleteRoomByAreaId(String areaId) {
        roomMapper.deleteByAreaId(areaId);
    }

    @Override
    public void deleteAllRoom() {
        roomMapper.deleteAll();
    }
}
