package org.example.user.pojo.vo;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * FBA包裹轨迹
 */
@Data
public class ShipmentTrackVO {

    private Integer packageNumber;

    private String trackNumber;

    private List<TrackEvent> trackEventList;

    @Data
    public static class TrackEvent{
        private String message;
        private ZonedDateTime eventTime;
        private String code;
        private String location;
    }
}
