package edu.hust.dao.dto;

import lombok.Data;

/**
 * @program: HomeCareCenter
 * @description: 床铺实体类
 * @author: Derry Lin
 * @create: 2020-09-02 16:44
 **/
@Data
public class Bed {

    //唯一标识
    private String id;

    //床名
    private String bedName;

    //所属房间对象
    private Room room;

}
