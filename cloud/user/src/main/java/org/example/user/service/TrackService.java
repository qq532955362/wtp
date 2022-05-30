package org.example.user.service;

import org.example.user.constants.ShipmentCooperation;
import org.example.user.pojo.vo.ShipmentTrackVO;

public interface TrackService {

    ShipmentTrackVO getTrack(String trackNumber, ShipmentCooperation shipmentCooperation);

}
