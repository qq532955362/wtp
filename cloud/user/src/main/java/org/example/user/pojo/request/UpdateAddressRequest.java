package org.example.user.pojo.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UpdateAddressRequest {
    /**
     * id标识
     */
    private Integer id;
    /**
     * iso国家码
     */
    @Length(min = 1,max = 10)
    private String site;
    /**
     * 邮箱
     */
    @Length(min = 1,max = 50)
    private String email;
    /**
     * 手机号
     */
    @Length(min = 1,max = 50)
    private String phone;
    /**
     * 姓
     */
    @Length(min = 1,max = 50)
    private String firstName;
    /**
     * 名
     */
    @Length(min = 1,max = 50)
    private String lastName;
    /**
     * 详细地址
     */
    @Length(min = 1,max = 200)
    private String addressDetail;
    /**
     *
     */
    @Length(min = 1,max = 50)
    private String apartment;
    /**
     * 市
     */
    @Length(min = 1,max = 50)
    private String city;
    /**
     * 省/州
     */
    @Length(min = 1,max = 50)
    private String province;
    /**
     * 州编码
     */
    @Length(min = 1,max = 20)
    private String provinceCode;
    /**
     * 邮编
     */
    @Length(min = 1,max = 50)
    private String zip;
    /**
     * 是否默认地址
     */
    private Boolean defaultAddress;
}
