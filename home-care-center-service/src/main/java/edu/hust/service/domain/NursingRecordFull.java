package edu.hust.service.domain;

import edu.hust.dao.dto.NursingRecord;
import lombok.Data;

/**
 * @program: HomeCareCenter
 * @description: 完整NursingRecord类
 * @author: Derry Lin
 * @create: 2020-09-07 09:46
 **/
@Data
public class NursingRecordFull extends NursingRecord {

    //客户类
    private ClientFull clientFull;

    //护士类
    private WorkerFull nurse;

}
