package com.southeast.passbook.service;

import com.alibaba.fastjson.JSON;

import com.southeast.passbook.vo.Pass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1>用户优惠券服务测试</h1>
 * @author drewsir
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserPassServiceTest extends AbstractServiceTest {

    @Autowired
    private IUserPassService userPassService;

/*    {
        "data": [
        {
            "merchants": {
            "address": "北京市",
                    "businessLicenseUrl": "www.baidu.com",
                    "id": 9,
                    "isAudit": true,
                    "logoUrl": "www.baidu.com",
                    "name": "百度",
                    "phone": "1234567890"
        },
            "pass": {
            "assignedDate": 1515513600000,
                    "templateId": "3617cf73e7a1099097242115042cb7b0",
                    "token": "token-1",
                    "userId": 149276
        },
            "passTemplate": {
            "background": 2,
                    "desc": "详情: 谷歌",
                    "end": 1529424000000,
                    "hasToken": true,
                    "id": 9,
                    "limit": 9998,
                    "start": 1527696000000,
                    "summary": "简介: 谷歌",
                    "title": "谷歌-2"
        }
        }
  ],
        "errorCode": 0,
        "errorMsg": ""
    }*/
    @Test
    public void testGetUserPassInfo() throws Exception {

        System.out.println(JSON.toJSONString(
                userPassService.getUserPassInfo(userId))//获取用户当前可用的优惠券信息，即 "我的优惠券" 功能实现
        );
    }

    // {"data":[],"errorCode":0,"errorMsg":""}
    @Test
    public void testGetUserUsedPassInfo() throws Exception {

        System.out.println(JSON.toJSONString(
                userPassService.getUserUsedPassInfo(userId)//获取用户已消费的优惠券信息，即 “已使用优惠券”功能实现
        ));
    }

//    {
//        "data": [
//        {
//            "merchants": {
//            "address": "北京市",
//                    "businessLicenseUrl": "www.baidu.com",
//                    "id": 9,
//                    "isAudit": true,
//                    "logoUrl": "www.baidu.com",
//                    "name": "百度",
//                    "phone": "1234567890"
//        },
//            "pass": {
//            "assignedDate": 1515513600000,
//                    "templateId": "3617cf73e7a1099097242115042cb7b0",
//                    "token": "token-1",
//                    "userId": 149276
//        },
//            "passTemplate": {
//            "background": 2,
//                    "desc": "详情: 谷歌",
//                    "end": 1529424000000,
//                    "hasToken": true,
//                    "id": 9,
//                    "limit": 9998,
//                    "start": 1527696000000,
//                    "summary": "简介: 谷歌",
//                    "title": "谷歌-2"
//        }
//        }
//  ],
//        "errorCode": 0,
//            "errorMsg": ""
//    }
    @Test
    public void testGetUserAllPassInfo() throws Exception {

        System.out.println(JSON.toJSONString(
                userPassService.getUserAllPassInfo(userId)//获取用户所有的优惠券
        ));
    }

    // {"errorCode":0,"errorMsg":""}
    @Test
    public void testUserUsePass() {

        Pass pass = new Pass();
        pass.setUserId(userId);
        pass.setTemplateId("f8e97f46b58f54edfcdbfe59258ca437");

        System.out.println(JSON.toJSONString(
                userPassService.userUsePass(pass)//用户使用优惠券
        ));
    }
}
