package edu.hust.service.domain;

import edu.hust.dao.dto.Client;
import edu.hust.dao.dto.MedicalRecord;
import edu.hust.dao.dto.Worker;

/**
 * @program: HomeCareCenter
 * @description: 完整MedicalRecord类
 * @author: Derry Lin
 * @create: 2020-09-07 09:45
 **/
public class MedicalRecordFull extends MedicalRecord {

    //客户类
    private Client client;

    //医生类
    private Worker doctor;

}
