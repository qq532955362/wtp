package org.example.user.pojo.vo;

import lombok.Data;
import org.example.user.constants.DeliveryOrderStatus;
import org.example.user.constants.DeliveryPackageStatus;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class AppDeliveryOrderVO {
    //出库信息
    private DeliveryInfo deliveryInfo;
    //买家、地址信息
    private DeliveryAddressInfo addressInfo;
    //产品信息
    private List<DeliveryItem> itemList;
    //出库单状态
    private DeliveryOrderStatus deliveryOrderStatus;
    //配送包裹状态
    private DeliveryPackageStatus deliveryPackageStatus;
    //订单创建时间
    private ZonedDateTime createOrderTime;
    //支付时间
    private ZonedDateTime purchaseOrderTime;
    //发货时间
    private ZonedDateTime startDeliveryTime;
    //国家
    private String country;
    //iso国家码
    private String countryCode;
    //发货
    private String overseaWarehouse;

    @Data
    private static class DeliveryInfo {
        //app订单号
        private String OrderNumber;
        //订单名
        private String deliveryOrderName;
        //出库单id
        private String deliveryOrderId;
    }

    @Data
    public static class DeliveryItem {
        //sku
        private String sku;
        //数量
        private Integer quantity;
    }


    @Data
    private static class DeliveryAddressInfo {
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        //邮编
        private String zipCode;

        private String country;
        private String countryCode;
        //省级（州）
        private String province;
        private String city;

    }
}
