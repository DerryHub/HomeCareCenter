package edu.hust.service.service;

import edu.hust.dao.dto.Worker;
import edu.hust.service.domain.WorkerFull;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Worker类服务层接口
 * @author: Derry Lin
 * @create: 2020-09-07 15:13
 **/
public interface WorkerService {
    
    /**
     * @Author Derry Lin
     * @Description 登录
     * @Date 下午2:58 2020/9/8
     * @Param [idCardNo, password]
     * @return boolean
     **/
    Worker loginByIdCardNoAndPassword(String idCardNo, String password);

    /**
     * @Author Derry Lin
     * @Description 查询工人
     * @Date 下午3:16 2020/9/7
     * @Param []
     * @return java.util.List<edu.hust.dao.dto.Worker>
     **/
    List<WorkerFull> getWorkerList();

    /**
     * @Author Derry Lin
     * @Description 根据id查询
     * @Date 下午3:17 2020/9/7
     * @Param [id]
     * @return edu.hust.dao.dto.Worker
     **/
    WorkerFull getWorkerById(String id);

    /**
     * @Author Derry Lin
     * @Description 根据身份证号查询
     * @Date 下午3:17 2020/9/7
     * @Param [idCardNo]
     * @return edu.hust.dao.dto.Worker
     **/
    WorkerFull getWorkerByIdCardNo(String idCardNo);

    /**
     * @Author Derry Lin
     * @Description 根据工人种类查询
     * @Date 下午3:17 2020/9/7
     * @Param [type]
     * @return java.util.List<edu.hust.dao.dto.Worker>
     **/
    List<WorkerFull> getWorkerByType(int type);

    /**
     * @Author Derry Lin
     * @Description 根据姓名查询
     * @Date 下午3:17 2020/9/7
     * @Param [name]
     * @return java.util.List<edu.hust.dao.dto.Worker>
     **/
    List<WorkerFull> getWorkerByName(String name);

    /**
     * @Author Derry Lin
     * @Description 根据区域id查询
     * @Date 下午3:17 2020/9/7
     * @Param [areaId]
     * @return java.util.List<edu.hust.dao.dto.Worker>
     **/
    List<WorkerFull> getWorkerByAreaId(String areaId);

    /**
     * @Author Derry Lin
     * @Description 添加工人
     * @Date 下午3:17 2020/9/7
     * @Param [worker]
     * @return void
     **/
    void addWorker(Worker worker);

    /**
     * @Author Derry Lin
     * @Description 批量添加
     * @Date 下午3:17 2020/9/7
     * @Param [workerList]
     * @return void
     **/
    void addWorkerList(List<Worker> workerList);

    /**
     * @Author Derry Lin
     * @Description 更新工人信息
     * @Date 下午3:17 2020/9/7
     * @Param [worker]
     * @return void
     **/
    void updateWorker(Worker worker);

    /**
     * @Author Derry Lin
     * @Description 根据id删除
     * @Date 下午3:17 2020/9/7
     * @Param [id]
     * @return void
     **/
    void deleteWorkerById(String id);

    /**
     * @Author Derry Lin
     * @Description 根据种类删除
     * @Date 下午3:17 2020/9/7
     * @Param [type]
     * @return void
     **/
    void deleteWorkerByType(int type);

    /**
     * @Author Derry Lin
     * @Description 根据身份证号删除
     * @Date 下午3:17 2020/9/7
     * @Param [idCardNo]
     * @return void
     **/
    void deleteWorkerByIdCardNo(String idCardNo);

    /**
     * @Author Derry Lin
     * @Description 删除所有
     * @Date 下午3:24 2020/9/7
     * @Param []
     * @return void
     **/
    void deleteAllWorker();

}
