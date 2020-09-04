package edu.hust.dao.dto;

import lombok.Data;

import java.util.Date;

/**
 * @program: HomeCareCenter
 * @description: 工作人员实体类
 * @author: Derry Lin
 * @create: 2020-09-02 17:18
 **/
@Data
public class Worker {

    //唯一标识
    private String id;

    //类型：管理员0、医生1、护士2
    private Integer type;

    //姓名
    private String name;

    //生日
    private Date birthday;

    //性别 男0女1
    private Integer gender;

    //身份证号
    private String idCardNo;

    //注册时间
    private Date registerDate;

    //手机号码
    private String phoneNo;

    //管理区域id
    private String  areaId;

    //密码
    private String password;

    //头像
    private String headImg;

    //删除帐号 1删除 默认null
    private Integer delete;

}
