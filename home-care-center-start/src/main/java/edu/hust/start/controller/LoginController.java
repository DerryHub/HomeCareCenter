package edu.hust.start.controller;

import edu.hust.start.monitor.Monitor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chain
 * @date 2020/9/4
 **/
@RestController
public class LoginController {

    @Monitor("sad")
    public void test(){

    }

}
