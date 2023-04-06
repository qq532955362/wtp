package org.example.wtp.pojo.req;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarSpeedReq2 {
    private Integer id;
    private BigDecimal speed;
    private Long createTime;
}
