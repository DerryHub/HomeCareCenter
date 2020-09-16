package edu.hust.dao.dto;

import lombok.Data;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: 套餐实体类
 * @author: Derry Lin
 * @create: 2020-09-02 17:10
 **/
@Data
public class DishSet {

    //唯一标识
    private String id;

    private String name;

    //周一至周日菜品集
    private String mon;
    private String tue;
    private String wed;
    private String thu;
    private String fri;
    private String sat;
    private String sun;

    //备注
    private String remark;

}
