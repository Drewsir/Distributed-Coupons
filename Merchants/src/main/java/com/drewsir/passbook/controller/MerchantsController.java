package com.drewsir.passbook.controller;

import com.alibaba.fastjson.JSON;
import com.drewsir.passbook.service.IMerchantsServ;
import com.drewsir.passbook.vo.CreateMerchantsRequest;
import com.drewsir.passbook.vo.PassTemplate;
import com.drewsir.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <h1>商户服务 Controller</h1>
 * @author drew
 */
@Slf4j
@RestController
@RequestMapping("/merchants")
public class MerchantsController {

    /** 商户服务接口 */
    private final IMerchantsServ merchantsServ;

    @Autowired
    public MerchantsController(IMerchantsServ merchantsServ){
        this.merchantsServ = merchantsServ;
    }

    @ResponseBody
    @PostMapping("/create")
    public Response createMerchants(@RequestBody  CreateMerchantsRequest request){
        log.info("CreateMerchants:{}", JSON.toJSONString(request));
        return merchantsServ.createMerchants(request);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Response buildMerchantsInfo(@PathVariable Integer id){
        log.info("BuildMerchantsInfo:{}", id);
        return merchantsServ.buildMerchantsInfoById(id);
    }

    @ResponseBody
    @PostMapping("/drop")
    public Response dropPassTemplate(@RequestBody PassTemplate passTemplate){
        log.info("DropPassTemplate:{}", passTemplate);
        return merchantsServ.dropPassTemplate(passTemplate);
    }
}
