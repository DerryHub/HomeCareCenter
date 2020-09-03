package edu.hust.dao.dto;

import lombok.Data;

import java.util.Date;

/**
 * @program: HomeCareCenter
 * @description: 餐饮日历实体类
 * @author: Derry Lin
 * @create: 2020-09-02 17:13
 **/
@Data
public class DishSetCalendar {

    //唯一标识
    private String id;

    //客户对象
    private Client client;

    //套餐对象
    private DishSet dishSet;

    //开始日期
    private Date startDate;

    //结束日期
    private Date endDate;

}
