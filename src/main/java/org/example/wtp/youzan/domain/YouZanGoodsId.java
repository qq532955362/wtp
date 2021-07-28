package org.example.wtp.youzan.domain;

import lombok.Data;

/**
 * @author wtp
 * @date 2021/7/27/027 17:06
 */
@Data
public class YouZanGoodsId {
    private Long value;

    public YouZanGoodsId(String value) {
        this.value = Long.valueOf(value);
    }
}
