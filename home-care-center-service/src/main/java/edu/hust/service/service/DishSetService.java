package edu.hust.service.service;

import edu.hust.dao.dto.DishSet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: DishSet类服务层接口
 * @author: Derry Lin
 * @create: 2020-09-07 11:08
 **/
public interface DishSetService {

    /**
     * 获取套餐列表
     * @return
     */
    List<DishSet> getDishSetList();

    /**
     * 根据id查找
     * @param id
     * @return
     */
    DishSet getDishSetById(String id);

    /**
     * 添加套餐
     * @param dishSet
     */
    void addDishSet(DishSet dishSet);

    /**
     * 批量添加
     * @param dishSetList
     */
    void addDishSetList(List<DishSet> dishSetList);

    /**
     * 更新套餐
     * @param dishSet
     */
    void updateDishSet(DishSet dishSet);

    /**
     * 根据id删除套餐
     * @param id
     */
    void deleteDishSetById(String id);

    /**
     * 删除所有
     */
    void deleteAllDishSet();

}
