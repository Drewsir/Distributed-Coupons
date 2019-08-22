package com.southeast.passbook.service;

import com.alibaba.fastjson.JSON;
import com.southeast.passbook.constant.Constants;
import com.southeast.passbook.vo.PassTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * <h1>消费 Kafka 中的 PassTemplate</h1>
 * @author drewsir
 */
@Slf4j
@Component
public class ConsumePassTemplate {

    /** pass 相关的 HBase 服务 */
    private final IHBasePassService passService;

    @Autowired
    public ConsumePassTemplate(IHBasePassService passService) {
        this.passService = passService;
    }

    @KafkaListener(topics = {Constants.TEMPLATE_TOPIC}) // KafkaListener 标识 Kafka 消费者
    public void receive(@Payload String passTemplate,// 从 Kafka 中接收的负载数据
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){

        log.info("Consume Receive PassTemplate: {}", passTemplate);

        PassTemplate pt;
        try{
            pt = JSON.parseObject(passTemplate, PassTemplate.class);//将 passTemplate 字符串序列化为 PassTemplate 对象
        }catch (Exception e){
            log.error("Parse PassTemplate Error:{}", e.getMessage());
            return;
        }
        log.info("DropPassTemplatetToHBase:{}", passService.dropPassTemplateToHBase(pt));
    }
}
