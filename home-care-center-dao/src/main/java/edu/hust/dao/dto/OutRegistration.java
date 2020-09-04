package edu.hust.dao.dto;

import lombok.Data;

import java.util.Date;

/**
 * @program: HomeCareCenter
 * @description: 外出登记单
 * @author: Derry Lin
 * @create: 2020-09-02 16:46
 **/
@Data
public class OutRegistration {

    //唯一标识
    private String id;

    //客户对象id
    private String clientId;

    //护士对象id
    private String nurseId;

    //外出原因
    private String reason;

    //外出时间
    private Date outTime;

    //陪同人
    private String partnerName;

    //陪同人电话
    private String partnerPhone;

    //备注
    private String remark;

    //预期返回时间
    private Date backTimeExpected;

    //实际返回时间
    private Date backTimeReal;

}
