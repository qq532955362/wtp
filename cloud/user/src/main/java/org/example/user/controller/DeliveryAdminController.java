package org.example.user.controller;

import org.example.user.pojo.ResponseData;
import org.example.user.pojo.request.QueryAppDeliveryOrderRequest;
import org.example.user.pojo.vo.AppDeliveryOrderVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 发货管理
 *
 * @module app商城
 */
@RestController
@RequestMapping("/app-delivery-order")
public class DeliveryAdminController {

    /**
     * 出库单列表
     *
     * @param request 查询请求
     * @return SERVER_ERROR(10000)
     */
    @PostMapping
    public ResponseEntity<ResponseData<List<AppDeliveryOrderVO>>> listDeliveryOrder(@RequestBody QueryAppDeliveryOrderRequest request) {
        return null;
    }
}
