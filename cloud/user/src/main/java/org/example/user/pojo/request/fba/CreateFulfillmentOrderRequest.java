package org.example.user.pojo.request.fba;

import lombok.Data;
import org.example.user.pojo.request.fba.constant.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateFulfillmentOrderRequest {
    /**
     * 配送订单所针对的市场
     */
    private String marketplaceId;

    /**
     * 商家自定义的配送订单id，必须unique
     */
    @NotBlank
    @Length(max = 40)
    private String sellerFulfillmentOrderId;

    /**
     * 商家自定义的订单标识符，可以为 @see #org.example.user.pojo.request.FBA.GetFulfillmentOrderRequest#sellerFulfillmentOrderId
     *
     * @link https://developer-docs.amazon.com/sp-api/docs/fulfillment-outbound-api-v2020-07-01-reference#createfulfillmentorderrequest
     */
    @NotBlank
    @Length(max = 40)
    private String displayableOrderId;

    /**
     * 可以使用订单创建时间，为时间戳
     */
    @NotBlank
    private String displayableOrderDate;

    /**
     * 收件人可以看到的商品描述
     */
    @NotBlank
    @Length(max = 1000)
    private String displayableOrderComment;

    /**
     * 配送订单的配送速度，枚举
     */
    @NotNull
    private ShippingSpeedCategory shippingSpeedCategory;

    /**
     * 配送速度策略为 ScheduledDelivery 时指定的时间窗口（范围）
     */
    private DeliveryWindow deliveryWindow;

    /**
     * 配送目标地址
     */
    @NotNull
    private Address destinationAddress;

    /**
     * 指定配送行为，立即送出或者暂停在FBA中
     */
    private FulfillmentAction fulfillmentAction;

    /**
     * 配送政策,提交创建订单时指定策略
     *
     * @link https://developer-docs.amazon.com/sp-api/docs/fulfillment-outbound-api-v2020-07-01-reference#fulfillmentpolicy
     */
    private FulfillmentPolicy fulfillmentPolicy;

    /**
     * 活到付款配置
     */
    private CODSettings codSettings;

    /**
     * 发货订单所在国家的国家码ISO3166-2
     */
    private String shipFromCountryCode;

    /**
     * 通知邮件列表,FBA代表卖家向卖家发送邮件通知
     */
    private List<String> notificationEmails;

    /**
     * TODO 这个功能非必须，暂时没看懂
     */
    private List<FeatureSettings> featureConstraints;

    private List<CreateFulfillmentOrderItem> createFulfillmentOrderItemList;

}
