package edu.hust.start;

import edu.hust.dao.dao.AreaMapper;
import edu.hust.dao.dao.BedMapper;
import edu.hust.dao.dto.Area;
import edu.hust.dao.dto.Bed;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class HomeCareCenterStartApplicationTests {

    @Autowired
    BedMapper bedMapper;

//    @Autowired
//    AreaMapper areaMapper;

    @Test
    void contextLoads() {
        List<Bed> bedList = bedMapper.selectList();
        System.out.println(bedList);
//        List<Area> areaList = areaMapper.selectList();
//        System.out.println(areaList);
    }

}
