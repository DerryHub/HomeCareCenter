package edu.hust.dao.dto;

import lombok.Data;

import java.util.Date;

/**
 * @program: HomeCareCenter
 * @description: 客户实体类
 * @author: Derry Lin
 * @create: 2020-09-02 16:48
 **/
@Data
public class Client {

    //唯一标识
    private String id;

    //姓名
    private String name;

    //性别 男0女1
    private Integer gender;

    //身份证号
    private String idCardNo;

    //身高 厘米
    private Integer height;

    //体重 千克
    private Integer weight;

    //亲属姓名
    private String relativeName;

    //亲属电话
    private String relativePhone;

    //婚姻状况 0未婚 1已婚
    private Integer marriage;

    //头像
    private String headImg;

    //备注
    private String remark;

    //床铺对象
    private Bed bed;

    //护理级别
    private Integer levelOfCare;

    //入住时间
    private Date inDate;

    //退住时间
    private Date outDate;

    //食品要求
    private String foodReq;

    //手机号码
    private String phoneNo;

    //生日
    private Date birthday;

}
