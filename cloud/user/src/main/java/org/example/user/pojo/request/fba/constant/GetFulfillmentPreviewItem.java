package org.example.user.pojo.request.fba.constant;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class GetFulfillmentPreviewItem {
    @NotBlank
    @Length(max = 50)
    private String sellerSku;

    @NotBlank
    private Integer quantity;

    private Money perUnitDeclaredValue;

    @NotBlank
    private String sellerFulfillmentOrderItemId;
}
