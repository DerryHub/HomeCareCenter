package edu.hust.service.domain;

import edu.hust.dao.dto.Client;
import edu.hust.dao.dto.NursingRecord;
import edu.hust.dao.dto.Worker;

/**
 * @program: HomeCareCenter
 * @description: 完整NursingRecord类
 * @author: Derry Lin
 * @create: 2020-09-07 09:46
 **/
public class NursingRecordFull extends NursingRecord {

    //客户类
    private Client client;

    //护士类
    private Worker nurse;

}
