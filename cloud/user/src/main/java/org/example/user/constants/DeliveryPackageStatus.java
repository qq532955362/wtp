package org.example.user.constants;

import lombok.Getter;

@Getter
public enum DeliveryPackageStatus {
    PENDING("待发货","pending",0),
    SHIPPED("配送中","shipped",1),
    CANCELLED_BY_FULFILLMENT("被物流取消","cancelled by fulfillment",2),
    CANCELLED_BY_SELLER("被卖家取消","cancelled by seller",3),
    ;

    private String cnName;
    private String name;
    private Integer code;

    DeliveryPackageStatus(String cnName, String name, Integer code) {
        this.cnName = cnName;
        this.name = name;
        this.code = code;
    }
}
