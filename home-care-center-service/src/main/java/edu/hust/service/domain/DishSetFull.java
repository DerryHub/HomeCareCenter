package edu.hust.service.domain;

import edu.hust.dao.dto.Dish;
import edu.hust.dao.dto.DishSet;
import lombok.Data;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: 完整DishSet类
 * @author: Derry Lin
 * @create: 2020-09-07 16:59
 **/
@Data
public class DishSetFull extends DishSet {

    private List<String> monId;
    private List<String> tueId;
    private List<String> wedId;
    private List<String> thuId;
    private List<String> friId;
    private List<String> satId;
    private List<String> sunId;

    private List<Dish> monDish;
    private List<Dish> tueDish;
    private List<Dish> wedDish;
    private List<Dish> thuDish;
    private List<Dish> friDish;
    private List<Dish> satDish;
    private List<Dish> sunDish;

}
