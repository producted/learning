package com.haohuo.transactional.web;

import com.haohuo.transactional.service.TransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author Zhang Peike
 * @Date 2019/2/24 10:33
 **/
@RestController
@RequestMapping(value = {"API/trans","api/trans"})
public class TestController {

    @Autowired
    private TransService transService;

    @RequestMapping(value = "testTrans")
    public Object test(){
        return transService.transTest();
    }
}
