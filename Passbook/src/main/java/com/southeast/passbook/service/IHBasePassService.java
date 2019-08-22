package com.southeast.passbook.service;

import com.southeast.passbook.vo.PassTemplate;

/**
 * <h1>Pass HBase 服务</h1>
 * @author drewsir
 */
public interface IHBasePassService {

    /**
     * 将 PassTemplate 写入 HBase
     * @param passTemplate {@link PassTemplate}
     * @return true/false
     */
    boolean dropPassTemplateToHBase(PassTemplate passTemplate);
}
