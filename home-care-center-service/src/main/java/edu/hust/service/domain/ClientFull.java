package edu.hust.service.domain;

import edu.hust.dao.dto.Bed;
import edu.hust.dao.dto.Client;
import lombok.Data;

/**
 * @program: HomeCareCenter
 * @description: 完整User类
 * @author: Derry Lin
 * @create: 2020-09-07 09:16
 **/
@Data
public class ClientFull extends Client {

    //客户所属床铺
    private Bed bed;

}
