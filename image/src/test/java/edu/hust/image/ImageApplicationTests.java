package edu.hust.image;

import edu.hust.image.config.Config;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ImageApplicationTests {

    @Autowired
    private Config config;

    @Test
    void contextLoads() {
        System.out.println(config);

    }

}
