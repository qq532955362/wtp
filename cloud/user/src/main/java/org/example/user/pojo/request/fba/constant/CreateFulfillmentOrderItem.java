package org.example.user.pojo.request.fba.constant;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateFulfillmentOrderItem {
    @NotBlank
    @Length(max = 50)
    private String sellerSku;

    @NotBlank
    @Length(max = 50)
    private String sellerFulfillmentOrderItemId;

    @NotNull
    private Integer quantity;

    @Length(max = 512)
    private String giftMessage;

    @Length(max = 250)
    private String displayableComment;

    private String fulfillmentNetworkSku;

    private Money perUnitDeclareValue;

    private Money perUnitPrice;

    private Money preUnitTax;
}
