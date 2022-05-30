package org.example.user.pojo.request.fba.constant;

import lombok.Data;

@Data
public class CODSettings {
    private Boolean isCodRequest;
    private Money codCharge;
    private Money codChargeTax;
    private Money shippingCharge;
    private Money shippingChargeTax;
}
