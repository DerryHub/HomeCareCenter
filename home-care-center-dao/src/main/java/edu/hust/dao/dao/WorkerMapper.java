package edu.hust.dao.dao;

import edu.hust.dao.dto.Worker;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Worker类数据库接口
 * @author: Derry Lin
 * @create: 2020-09-04 08:37
 **/
@Mapper
public interface WorkerMapper {

    /**
     * @Author Derry Lin
     * @Description 查询工人
     * @Date 下午3:26 2020/9/7
     * @Param []
     * @return java.util.List<edu.hust.dao.dto.Worker>
     **/
    List<Worker> selectList();

    /**
     * @Author Derry Lin
     * @Description 根据id查询
     * @Date 下午3:26 2020/9/7
     * @Param [id]
     * @return edu.hust.dao.dto.Worker
     **/
    Worker selectById(@Param("id") String id);

    /**
     * @Author Derry Lin
     * @Description 根据身份证号查询
     * @Date 下午3:26 2020/9/7
     * @Param [idCardNo]
     * @return edu.hust.dao.dto.Worker
     **/
    Worker selectByIdCardNo(@Param("id_card_no") String idCardNo);

    /**
     * @Author Derry Lin
     * @Description 根据工人种类查询
     * @Date 下午3:26 2020/9/7
     * @Param [type]
     * @return java.util.List<edu.hust.dao.dto.Worker>
     **/
    List<Worker> selectByType(@Param("type") int type);

    /**
     * @Author Derry Lin
     * @Description 根据姓名查询
     * @Date 下午3:26 2020/9/7
     * @Param [name]
     * @return java.util.List<edu.hust.dao.dto.Worker>
     **/
    List<Worker> selectByName(@Param("name") String name);

    /**
     * @Author Derry Lin
     * @Description 根据区域id查询
     * @Date 下午3:26 2020/9/7
     * @Param [areaId]
     * @return java.util.List<edu.hust.dao.dto.Worker>
     **/
    List<Worker> selectByAreaId(@Param("area_id") String areaId);

    /**
     * @Author Derry Lin
     * @Description 根据姓名类型查找
     * @Date 下午9:48 2020/9/21
     * @Param [name, type]
     * @return java.util.List<edu.hust.dao.dto.Worker>
     **/
    List<Worker> selectByNameAndType(@Param("name") String name, @Param("type") Integer type);

    /**
     * @Author Derry Lin
     * @Description 添加工人
     * @Date 下午3:26 2020/9/7
     * @Param [worker]
     * @return int
     **/
    int add(@Param("worker") Worker worker);

    /**
     * @Author Derry Lin
     * @Description 批量添加
     * @Date 下午3:26 2020/9/7
     * @Param [workerList]
     * @return int
     **/
    int addBatch(@Param("worker_list") List<Worker> workerList);

    /**
     * @Author Derry Lin
     * @Description 更新工人信息
     * @Date 下午3:27 2020/9/7
     * @Param [worker]
     * @return int
     **/
    int update(@Param("worker") Worker worker);

    /**
     * @Author Derry Lin
     * @Description 根据id删除
     * @Date 下午3:27 2020/9/7
     * @Param [id]
     * @return int
     **/
    int deleteById(@Param("id") String id);

    /**
     * @Author Derry Lin
     * @Description 根据种类删除
     * @Date 下午3:27 2020/9/7
     * @Param [type]
     * @return int
     **/
    int deleteByType(@Param("type") int type);

    /**
     * @Author Derry Lin
     * @Description 根据身份证号删除
     * @Date 下午3:27 2020/9/7
     * @Param [idCardNo]
     * @return int
     **/
    int deleteByIdCardNo(@Param("id_card_no") String idCardNo);

    /**
     * @Author Derry Lin
     * @Description 删除所有
     * @Date 下午3:29 2020/9/7
     * @Param []
     * @return int
     **/
    int deleteAll();

}
