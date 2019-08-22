package com.drewsir.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>创建商户响应对象</h1>
 * @author drew
 * @create 2019-06-16 22:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMerchantsResponse {

    /** 商户 id：若创建失败则为 -1 */
    private Integer id;
}
