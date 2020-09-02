package edu.hust.dao.dto;

import lombok.Data;

import java.util.Date;

/**
 * @program: HomeCareCenter
 * @description: 护理记录实体类
 * @author: Derry Lin
 * @create: 2020-09-02 17:15
 **/
@Data
public class NursingRecord {

    //唯一标识
    private String id;

    //客户对象
    private Client client;

    //护士对象
    private Worker nurse;

    //护理内容
    private String content;

    //护理日期
    private Date date;

    //备注
    private String remark;

}
