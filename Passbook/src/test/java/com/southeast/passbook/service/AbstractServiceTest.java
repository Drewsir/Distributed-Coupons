package com.southeast.passbook.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1>服务测试抽象基类</h1>
 * 由于是用户应用子系统，针对用户，则将用户 id  保存起来，方便子测试用例使用
 * @author drewsir
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractServiceTest {

    Long userId;//用户 id

    @Before // 标记 init 方法会在测试用例之前执行
    public void init() {

        userId =  133211L;//之前使用 createUser 服务在 HBase 中创建了一个用户，生成了 userId.
    }
}
