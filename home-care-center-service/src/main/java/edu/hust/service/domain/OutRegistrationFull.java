package edu.hust.service.domain;

import edu.hust.dao.dto.OutRegistration;
import edu.hust.dao.dto.Worker;
import lombok.Data;

/**
 * @program: HomeCareCenter
 * @description: 完整OutRegistration类
 * @author: Derry Lin
 * @create: 2020-09-07 09:48
 **/
@Data
public class OutRegistrationFull extends OutRegistration {

    //客户类
    private ClientFull clientFull;

    //护士类
    private Worker nurse;

}
