package org.example.user.controller;

import org.example.user.pojo.ResponseData;
import org.example.user.pojo.request.common.PreviewTimeRequest;
import org.example.user.pojo.vo.ReceivedTimeVO;
import org.example.user.pojo.vo.ShipmentTrackVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 公共分类
 *
 * @module 公共分类
 */
@RestController
@RequestMapping("/app-common")
public class CommonController {
    /**
     * 预测收货时间
     *
     * @param previewReceiveTimeRequest 预测收货时间请求
     * @return
     */
    @PostMapping("/receive/preview")
    ResponseEntity<ResponseData<ReceivedTimeVO>> predictReceivedTime(@RequestBody PreviewTimeRequest previewReceiveTimeRequest) {
        return null;
    }

    /**
     * 轨迹预测
     *
     * @param packageNumber
     * @param trackNumber
     * @return
     */
    @PostMapping("/shipment/track")
    ResponseEntity<ResponseData<ShipmentTrackVO>> getShipmentTrackInfo(@RequestParam("packageNumber") Integer packageNumber,
                                                                       @RequestParam("trackNumber") String trackNumber) {
        return null;
    }
}
