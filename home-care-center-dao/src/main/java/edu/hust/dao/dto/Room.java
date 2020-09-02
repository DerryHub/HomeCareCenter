package edu.hust.dao.dto;

import lombok.Data;

/**
 * @program: HomeCareCenter
 * @description: 房间实体类
 * @author: Derry Lin
 * @create: 2020-09-02 16:34
 **/
@Data
public class Room {

    //唯一标识
    private String id;

    //房间名
    private String roomTitle;

    //所属区域
    private Area area;

}
