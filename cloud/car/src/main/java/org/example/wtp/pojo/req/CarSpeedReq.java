package org.example.wtp.pojo.req;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarSpeedReq {
    private Integer id;
    private BigDecimal speed;
    private Long createTime;
}
