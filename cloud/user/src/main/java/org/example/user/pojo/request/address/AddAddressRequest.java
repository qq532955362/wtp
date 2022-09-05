package org.example.user.pojo.request.address;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class AddAddressRequest {

    /**
     * iso国家码
     */
    @Length(min = 0,max = 10)
    @NotBlank
    private String site;
    /**
     * 邮箱
     */
    @Length(min = 0,max = 50)
    private String email;
    /**
     * 手机号
     */
    @Length(min = 1,max = 50)
    @NotBlank
    private String phone;
    /**
     * 姓
     */
    @Length(min = 0,max = 50)
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
    @NotBlank
    private String addressDetail;
    /**
     *
     */
    @Length(min = 0,max = 50)
    private String apartment;
    /**
     * 市
     */
    @Length(min = 1,max = 50)
    @NotBlank
    private String city;
    /**
     * 省/州
     */
    @Length(min = 1,max = 50)
    @NotBlank
    private String province;
    /**
     * 州编码
     */
    @Length(min = 0,max = 20)
    private String provinceCode;
    /**
     * 邮编
     */
    @Length(min = 0,max = 50)
    private String zip;
    /**
     * 是否默认地址
     */
    private Long defaultAddress;
}
