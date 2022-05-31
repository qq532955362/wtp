package org.example.user.pojo.request;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class QueryAppDeliveryOrderRequest {

    @Min(value = 1)
    @NotNull
    private Integer pageNumber;

    @Digits(integer = 1, fraction = 10)
    @NotNull
    private Integer pageSize;
}
