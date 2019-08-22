package com.southeast.passbook.vo;

import com.southeast.passbook.entity.Merchants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <h1>优惠券模板信息</h1>
 * 用户未领取的优惠券信息
 * @author drewsir
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassTemplateInfo{

    private PassTemplate passTemplate; //优惠券模板

    private Merchants merchants; //对应的商户信息
}
