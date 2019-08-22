package com.southeast.passbook.service;

import com.alibaba.fastjson.JSON;
import com.southeast.passbook.constant.FeedbackType;
import com.southeast.passbook.vo.Feedback;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1>用户反馈服务测试</h1>
 * @author drewsir
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FeedbackServiceTest extends AbstractServiceTest {

    @Autowired
    private IFeedbackService feedbackService;

    @Test
    public void testCreateFeedback() {

        Feedback appFeedback = new Feedback();
        appFeedback.setUserId(userId);
        appFeedback.setType(FeedbackType.APP.getCode());
        appFeedback.setTemplateId("-1");// app 类型的评论, 则没有 PassTemplate RowKey
        appFeedback.setComment("分布式卡包应用");

        System.out.println(JSON.toJSONString(
                feedbackService.createFeedback(appFeedback))//创建评论
        );

        Feedback passFeedback = new Feedback();
        passFeedback.setUserId(userId);
        passFeedback.setType(FeedbackType.PASS.getCode());
        passFeedback.setTemplateId("f8e97f46b58f54edfcdbfe59258ca437");//从用户的优惠券的响应信息里取到
        passFeedback.setComment("优惠券评论");

        System.out.println(JSON.toJSONString(
                feedbackService.createFeedback(passFeedback)
        ));

        // {"errorCode":0,"errorMsg":""}
        // {"errorCode":0,"errorMsg":""}
    }

//    {
//        "data": [
//        {
//            "comment": "优惠券评论",
//                "templateId": "3617cf73e7a1099097242115042cb7b0",
//                "type": "pass",
//                "userId": 149276
//        },
//        {
//            "comment": "分布式卡包应用",
//                "templateId": "-1",
//                "type": "app",
//                "userId": 149276
//        }
//  ],
//        "errorCode": 0,
//        "errorMsg": ""
//    }

    @Test
    public void testGetFeedback() {

        System.out.println(JSON.toJSONString(
                feedbackService.getFeedback(userId))
        );
    }
}
