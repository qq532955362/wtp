package org.example.user.pojo.request.fba.constant;

import lombok.Data;

@Data
public class FeatureSettings {
    private String featureName;
    private FeatureFulfillmentPolicy featureFulfillmentPolicy;
}
