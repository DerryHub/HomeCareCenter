package edu.hust.service.domain;

import edu.hust.dao.dto.Area;
import edu.hust.dao.dto.Worker;
import lombok.Data;

/**
 * @program: HomeCareCenter
 * @description: 完整Worker类
 * @author: Derry Lin
 * @create: 2020-09-08 09:27
 **/
@Data
public class WorkerFull extends Worker {

    private Area area;

}
