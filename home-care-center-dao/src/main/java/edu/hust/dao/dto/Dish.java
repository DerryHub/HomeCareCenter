package edu.hust.dao.dto;

import lombok.Data;

/**
 * @program: HomeCareCenter
 * @description: 菜品实体类
 * @author: Derry Lin
 * @create: 2020-09-02 17:08
 **/
@Data
public class Dish {

    //唯一标识
    private String id;

    //菜名
    private String name;

    //标签
    private String labels;

    //备注
    private String remark;

}
