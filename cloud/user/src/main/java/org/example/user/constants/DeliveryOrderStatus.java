package org.example.user.constants;

import lombok.Getter;

@Getter
public enum DeliveryOrderStatus {
    New("新建","new",0),
    Received("已收货","Received",1),
    Planning("计划发货","planning",2),
    Processing("配送中","processing",3),
    Cancelled("已取消","cancelled",4),
    Complete("完成","complete",5),
    CompletePartial("部分完成","complete partial",6),
    Unfulfillable("不可配送","unfulfillable",7),
    Invalid("无效","invalid",8),

    ;
    private String cnName;
    private String name;
    private Integer code;

    DeliveryOrderStatus(String cnName, String name, Integer code) {
        this.cnName = cnName;
        this.name = name;
        this.code = code;
    }
}
