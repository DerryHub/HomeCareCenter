package edu.hust.service.domain;

import edu.hust.dao.dto.DishSetCalendar;
import lombok.Data;

/**
 * @program: HomeCareCenter
 * @description: 完整DishSetCalendar类
 * @author: Derry Lin
 * @create: 2020-09-07 09:27
 **/
@Data
public class DishSetCalendarFull extends DishSetCalendar {

    //客户类
    private ClientFull clientFull;

    //套餐类
    private DishSetFull dishSetFull;

}
