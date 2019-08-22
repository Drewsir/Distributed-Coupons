package com.drewsir.passbook.vo;

import com.drewsir.passbook.constants.ErrorCode;
import com.drewsir.passbook.dao.MerchantsDao;
import com.drewsir.passbook.entity.Merchants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h2>创建商户请求对象</h2>
 * @author drew
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMerchantsRequest {

    /** 商户名称 */
    private String name;

    /** 商户 logo */
    private String logoUrl;

    /** 商户营业执照 */
    private String businessLicenseUrl;

    /** 商户联系电话 */
    private String phone;

    /** 商户地址 */
    private String address;

    /**
     * 验证请求的有效性
     * @param merchantsDao {@link MerchantsDao}
     * @return {@link ErrorCode}
     */
    public ErrorCode validate(MerchantsDao merchantsDao){
        if(merchantsDao.findByName(name) != null){
            return ErrorCode.DUPLICATE_NAME;//商户名称重复
        }
        if(null == this.logoUrl){
            return ErrorCode.EMPTY_LOGO;
        }
        if(null == this.businessLicenseUrl){
            return ErrorCode.EMPTY_BUSINESS_LICENSE;
        }
        if(null == this.address){
            return ErrorCode.EMPTY_ADDRESS;
        }
        if(null == this.phone){//可定制更细的条件，分为固话和手机
            return ErrorCode.ERROR_PHONE;
        }
        return ErrorCode.SUCCESS;
    }

    /**
     * 将请求对象转换为商户对象
     * @return {@link Merchants}
     */
    public Merchants toMerchants(){
        Merchants merchants = new Merchants();

        merchants.setName(name);
        merchants.setLogoUrl(logoUrl);
        merchants.setBusinessLicenseUrl(businessLicenseUrl);
        merchants.setAddress(address);
        merchants.setPhone(phone);

        return merchants;
    }
}
