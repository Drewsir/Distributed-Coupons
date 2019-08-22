package com.drewsir.passbook.service.impl;

import com.alibaba.fastjson.JSON;
import com.drewsir.passbook.constants.Constants;
import com.drewsir.passbook.constants.ErrorCode;
import com.drewsir.passbook.dao.MerchantsDao;
import com.drewsir.passbook.entity.Merchants;
import com.drewsir.passbook.service.IMerchantsServ;
import com.drewsir.passbook.vo.CreateMerchantsRequest;
import com.drewsir.passbook.vo.CreateMerchantsResponse;
import com.drewsir.passbook.vo.PassTemplate;
import com.drewsir.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * <h1>商户服务接口实现</h1>
 * @author drew
 */
@Slf4j
@Service
public class MerchantsServImpl implements IMerchantsServ {

    /** Merchants 数据库接口 */
    private final MerchantsDao merchantsDao;

    /** kafka 客户端 */
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public MerchantsServImpl(MerchantsDao merchantsDao,
                             KafkaTemplate<String, String> kafkaTemplate) {
        this.merchantsDao = merchantsDao;
        this.kafkaTemplate =  kafkaTemplate;
    }

    @Override
    @Transactional //只有保存成功才提交
    public Response createMerchants(CreateMerchantsRequest request) {
        Response response = new Response();

        //创建商户的响应对象
        CreateMerchantsResponse merchantsResponse = new CreateMerchantsResponse();

        //验证请求的有效性
        ErrorCode errorCode = request.validate(merchantsDao);
        if(errorCode != ErrorCode.SUCCESS){ // 请求失败
            merchantsResponse.setId(-1); //设置为 -1，表示创建失败   商户在平台上存放的响应id
            response.setErrorCode(errorCode.getCode());//商户可查看的错误信息
            response.setErrorMsg(errorCode.getDesc());
        }else{
            // id 就是存入表格 merchants 的 id
            merchantsResponse.setId(merchantsDao.save(request.toMerchants()).getId());//商户在我们平台生成的数据库里面的主键 id
        }
        response.setData(merchantsResponse);
        return response;
    }

    @Override
    public Response buildMerchantsInfoById(Integer id) {
        Response response = new Response();

        Merchants merchants = merchantsDao.findById(id);
        if(null == merchants){
            response.setErrorCode(ErrorCode.MERCHANTS_NOT_EXIST.getCode());
            response.setErrorMsg(ErrorCode.MERCHANTS_NOT_EXIST.getDesc());
        }
        response.setData(merchants);

        return response;
    }

    @Override
    public Response dropPassTemplate(PassTemplate template) {
        Response response = new Response();

        //先判断商户是否可以投放优惠券，商户不存在自然不能存优惠券
        ErrorCode errorCode = template.validate(merchantsDao);//判断商户是否在平台上注册过
        if(errorCode != ErrorCode.SUCCESS){
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        }else{
            //将 tmplate 对象转换为 JSON 字符串
            String passTemplate = JSON.toJSONString(template);
            kafkaTemplate.send(//利用 kafka 的客户端发送出去
                    Constants.TEMPLATE_TOPIC,
                    Constants.TEMPLATE_TOPIC,
                    passTemplate
            );
            log.info("Drop Template{}",passTemplate);
        }

        return response;
    }
}
