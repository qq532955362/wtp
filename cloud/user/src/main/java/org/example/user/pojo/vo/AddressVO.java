package org.example.user.pojo.vo;

import lombok.Data;

@Data
public class AddressVO {

    /**
     * id标识
     */
    private Integer id;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 姓
     */
    private String firstName;
    /**
     * 名
     */
    private String lastName;
    /**
     * 详细地址
     */
    private String addressDetail;
    /**
     *
     */
    private String apartment;
    /**
     * 市
     */
    private String city;
    /**
     * 省/州
     */
    private String province;
    /**
     * 州编码
     */
    private String provinceCode;
    /**
     * 邮编
     */
    private String zip;
    /**
     * 是否默认地址
     */
    private Boolean defaultAddress;

}
