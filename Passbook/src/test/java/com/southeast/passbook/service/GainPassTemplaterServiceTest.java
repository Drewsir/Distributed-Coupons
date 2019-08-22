package com.southeast.passbook.service;

import com.alibaba.fastjson.JSON;

import com.southeast.passbook.service.IGainPassTemplateService;
import com.southeast.passbook.vo.GainPassTemplateRequest;
import com.southeast.passbook.vo.PassTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1>用户领取优惠券功能测试</h1>
 * @author drewsir
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GainPassTemplaterServiceTest extends AbstractServiceTest {

    @Autowired
    private IGainPassTemplateService gainPassTemplateService;

    @Test
    public void testGainPassTemplate() throws Exception {

        PassTemplate target = new PassTemplate();//待领取的目标优惠券
        target.setId(2);
        target.setTitle("测试-1");
        target.setHasToken(true);

        System.out.println(JSON.toJSONString(
                gainPassTemplateService.gainPassTemplate(//用户领取优惠券
                        new GainPassTemplateRequest(userId, target)//用户领取优惠券的请求对象
                )
        ));
    }
}
