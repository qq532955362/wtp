package org.example.user.service;

import org.example.user.client.AmazonSellerApiClient;
import org.example.user.constants.ShipmentCooperation;
import org.example.user.pojo.vo.ShipmentTrackVO;

public class TrackServiceImpl implements TrackService {

    private AmazonSellerApiClient amazonSellerApiClient;

    @Override
    public ShipmentTrackVO getTrack(String trackNumber, ShipmentCooperation shipmentCooperation) {

        return null;
    }
}
