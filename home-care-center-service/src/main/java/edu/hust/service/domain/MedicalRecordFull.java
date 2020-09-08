package edu.hust.service.domain;

import edu.hust.dao.dto.MedicalRecord;
import lombok.Data;

/**
 * @program: HomeCareCenter
 * @description: 完整MedicalRecord类
 * @author: Derry Lin
 * @create: 2020-09-07 09:45
 **/
@Data
public class MedicalRecordFull extends MedicalRecord {

    //客户类
    private ClientFull clientFull;

    //医生类
    private WorkerFull doctor;

}
