package org.example.user.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * FBA包裹轨迹
 */
@Data
public class ShipmentTrackVO {

    private Integer packageNumber;

    private String trackNumber;

    /**
     * 轨迹事件列表
     */
    private List<TrackEvent> trackEventList;

    /**
     * 轨迹url点击可网页查看
     */
    private String trackingUrl;

    @Data
    public static class TrackEvent {
        //提示信息
        private String message;
        //时间节点
        private Long eventTime;
        //事件代码
        private String code;
        //事件信息(表示状态如包裹扫描、派送中)
        private String codeInfo;
        //地址信息
        private String location;
    }
}
