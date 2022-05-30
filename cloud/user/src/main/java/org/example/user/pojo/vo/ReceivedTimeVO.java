package org.example.user.pojo.vo;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ReceivedTimeVO {

    private ZonedDateTime earliestShipDate;
    private ZonedDateTime latestShipDate;

    private ZonedDateTime earliestArrivalDate;
    private ZonedDateTime latestArrivalDate;
}
