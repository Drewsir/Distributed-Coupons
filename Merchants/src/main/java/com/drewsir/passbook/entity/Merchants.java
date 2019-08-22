package com.drewsir.passbook.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * <h1>商户对象模型</h1>
 * @author drew
 */
@Data//实现 getter 和 setter 方法
@NoArgsConstructor//无参构造函数
@AllArgsConstructor//全参构造函数
@Entity//实体对象
@Table(name = "merchants")//实体对象属性方面的定义，表的名字为 passbook
public class Merchants {
    /*
      `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
     `name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '商户名称',
     `logo_url` varchar(256) COLLATE utf8_bin NOT NULL COMMENT '商户 logo',
     `business_license_url` varchar(256) COLLATE utf8_bin NOT NULL COMMENT '商户营业执照',
     `phone` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '商户联系电话',
     `address` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '商户地址',
     `is_audit` BOOLEAN NOT NULL COMMENT '是否通过审核',
     */

    /** 商户 id，主键 */
    @Id//表名该属性为 passbook 表的主键
    @GeneratedValue //自动生成值，因 id 自增
    @Column(name = "id",nullable = false)//列的一些属性定义
    private Integer id;

    /** 商户名称，需要是全局唯一的 */
    @Basic//表示该列是 passbook 表的基本列，一般列默认加此注解
    @Column(name = "name",unique = true,nullable = false)
    private String name;

    /** 商户 logo */
    @Basic
    @Column(name ="logo_url",nullable = false)
    private String logoUrl;

    /** 商户营业执照 */
    @Basic
    @Column(name ="business_license_url",nullable = false)
    private String businessLicenseUrl;

    /** 商户的联系电话 */
    @Basic
    @Column(name ="phone",nullable = false)
    private String phone;

    /** 商户地址 */
    @Basic
    @Column(name ="address",nullable = false)
    private String address;

    /** 商户是否通过审核 */
    @Basic
    @Column(name ="is_audit",nullable = false)
    private Boolean isAudit = false;
}
