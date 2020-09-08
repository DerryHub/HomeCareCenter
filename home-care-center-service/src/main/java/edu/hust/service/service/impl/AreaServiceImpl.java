package edu.hust.service.service.impl;

import edu.hust.common.constant.ApiCodeEnum;
import edu.hust.dao.dao.AreaMapper;
import edu.hust.dao.dto.Area;
import edu.hust.start.interceptor.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: HomeCareCenter
 * @description: Area服务层类
 * @author: Derry Lin
 * @create: 2020-09-07 15:29
 **/
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<Area> getAreaInfoList() {
        return areaMapper.selectList();
    }

    @Override
    public Area getAreaInfoById(String id) {
        return areaMapper.selectById(id);
    }

    @Override
    public Area getAreaInfoByTitle(String title) {
        return areaMapper.selectByAreaTitle(title);
    }

    @Override
    public void addArea(Area area) {
        if (areaMapper.add(area) == 0){
            throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
        }
    }

    @Override
    public void addAreaList(List<Area> areaList) {
        if (areaMapper.addBatch(areaList) != areaList.size()){
            throw new GlobalException(ApiCodeEnum.FAIL_TO_ADD);
        }
    }

    @Override
    public void updateArea(Area area) {
        if (areaMapper.update(area) == 0){
            throw new GlobalException(ApiCodeEnum.FAIL_TO_UPDATE);
        }
    }

    @Override
    public void deleteAreaById(String id) {
        areaMapper.deleteById(id);
    }

    @Override
    public void deleteAreaByTitle(String title) {
        areaMapper.deleteByAreaTitle(title);
    }

    @Override
    public void deleteAllArea() {
        areaMapper.deleteAll();
    }
}
