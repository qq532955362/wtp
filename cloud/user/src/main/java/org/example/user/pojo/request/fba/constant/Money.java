package org.example.user.pojo.request.fba.constant;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Money {
    private String currencyCode;
    private BigDecimal value;
}
