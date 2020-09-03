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

    //周一至周日菜品集
    private List<Dish> mon;
    private List<Dish> tue;
    private List<Dish> wed;
    private List<Dish> thu;
    private List<Dish> fri;
    private List<Dish> sat;
    private List<Dish> sun;

    //备注
    private String remark;

}
