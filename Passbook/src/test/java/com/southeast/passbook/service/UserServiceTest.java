package com.southeast.passbook.service;

import com.alibaba.fastjson.JSON;
import com.southeast.passbook.vo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1>用户服务测试</h1>
 * @author drewsir
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void testCreateUser() throws Exception {

        User user = new User();
        user.setBaseInfo(
                new User.BaseInfo("drewsir", 10, "m")
        );
        user.setOtherInfo(
                new User.OtherInfo("18934567890", "长沙市岳麓区")
        );
        //{"data":{"baseInfo":{"age":10,"name":"drewsir","sex":"m"},
        // "id":431224,
        // "otherInfo":{"address":"长沙市岳麓区","phone":"18934567890"}},
        // "errorCode":0,"errorMsg":""}
        System.out.println(JSON.toJSONString(userService.createUser(user)));
    }
}
