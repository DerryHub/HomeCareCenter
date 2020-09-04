package edu.hust.dao.dto;

import lombok.Data;

import java.util.Date;

/**
 * @program: HomeCareCenter
 * @description: 病历实体类
 * @author: Derry Lin
 * @create: 2020-09-02 17:26
 **/
@Data
public class MedicalRecord {

    //唯一标识
    private String id;

    //客户对象
    private String clientId;

    //医生id
    private String doctorId;

    //日期
    private Date date;

    //处方
    private String prescription;

    //疾病
    private String disease;

    //备注
    private String remark;

}
