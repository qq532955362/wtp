package org.example.user.pojo.request.fba;

import org.example.user.pojo.request.fba.constant.Address;
import org.example.user.pojo.request.fba.constant.GetFulfillmentPreviewItem;

import java.util.List;

/**
 * 预计送达时间预测
 */
public class GetFulfillmentPreviewRequest {
    private  String marketplaceId;
    private Address address;
    private List<GetFulfillmentPreviewItem> items;
}
