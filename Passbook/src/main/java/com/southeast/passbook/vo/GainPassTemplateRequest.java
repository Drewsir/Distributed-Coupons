package com.southeast.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>用户领取优惠券的请求对象</h1>
 * 表明用户领取哪些 PassTemplate
 * @author drewsir
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GainPassTemplateRequest {

    private Long userId; //用户 id

    private PassTemplate passTemplate; // 投放的优惠券对象
}
