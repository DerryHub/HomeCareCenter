package edu.hust.service.service;

import edu.hust.dao.dto.DishSet;
import edu.hust.service.domain.DishSetFull;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: DishSet类服务层接口
 * @author: Derry Lin
 * @create: 2020-09-07 11:08
 **/
public interface DishSetService {

    /**
     * @Author Derry Lin
     * @Description 获取套餐列表
     * @Date 下午5:19 2020/9/7
     * @Param []
     * @return java.util.List<edu.hust.service.domain.DishSetFull>
     **/
    List<DishSetFull> getDishSetList();

    /**
     * @Author Derry Lin
     * @Description 根据id查找
     * @Date 下午5:30 2020/9/7
     * @Param [id]
     * @return edu.hust.service.domain.DishSetFull
     **/
    DishSetFull getDishSetById(String id);
    
    /**
     * @Author Derry Lin
     * @Description 根据name查找
     * @Date 下午4:49 2020/9/15
     * @Param [name]
     * @return edu.hust.service.domain.DishSetFull
     **/
    DishSetFull getDishSetByName(String name);

    /**
     * @Author Derry Lin
     * @Description 添加套餐
     * @Date 下午3:07 2020/9/7
     * @Param [dishSet]
     * @return void
     **/
    void addDishSet(DishSetFull dishSetFull);

    /**
     * @Author Derry Lin
     * @Description 批量添加
     * @Date 下午3:07 2020/9/7
     * @Param [dishSetList]
     * @return void
     **/
    void addDishSetList(List<DishSetFull> dishSetFullList);

    /**
     * @Author Derry Lin
     * @Description 更新套餐
     * @Date 下午3:07 2020/9/7
     * @Param [dishSet]
     * @return void
     **/
    void updateDishSet(DishSetFull dishSetFull);

    /**
     * @Author Derry Lin
     * @Description 根据id删除套餐
     * @Date 下午3:07 2020/9/7
     * @Param [id]
     * @return void
     **/
    void deleteDishSetById(String id);

    /**
     * @Author Derry Lin
     * @Description 删除所有
     * @Date 下午3:20 2020/9/7
     * @Param []
     * @return void
     **/
    void deleteAllDishSet();

}
