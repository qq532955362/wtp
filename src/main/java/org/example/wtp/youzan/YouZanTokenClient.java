package org.example.wtp.youzan;

import com.youzan.cloud.open.sdk.common.exception.SDKException;
import com.youzan.cloud.open.sdk.core.client.core.DefaultYZClient;
import com.youzan.cloud.open.sdk.core.oauth.model.OAuthToken;
import com.youzan.cloud.open.sdk.core.oauth.token.TokenParameter;
import com.youzan.cloud.open.sdk.gen.v4_0_0.api.YouzanTradesSoldGet;
import com.youzan.cloud.open.sdk.gen.v4_0_0.model.YouzanTradesSoldGetParams;

/**
 * @author wtp
 * @date 2021/7/24/024 17:32
 */
public class YouZanTokenClient {

    private final static String CLIENT_ID = "c1ec50fec659767243";

    private static final String CLIENT_SECRET = "a8c0d8dd7e636cbce03b696cce7ac1ab";

    private static final String GRANT_ID = "42652714";


    public static void main(String[] args) throws SDKException {
        DefaultYZClient yzClient = new DefaultYZClient();
        TokenParameter tokenParameter = TokenParameter.self()
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .grantId(GRANT_ID)
                .refresh(true)
                .build();

        OAuthToken oAuthToken = yzClient.getOAuthToken(tokenParameter);

        System.out.println(oAuthToken.getAccessToken());


        YouzanTradesSoldGet youzanTradesSoldGet = new YouzanTradesSoldGet();

        YouzanTradesSoldGetParams youzanTradesSoldGetParams = new YouzanTradesSoldGetParams();
        youzanTradesSoldGetParams.setGoodsId(876051907L);
        youzanTradesSoldGet.setAPIParams(youzanTradesSoldGetParams);
    }




    public YouZanTokenClient() throws SDKException {
    }
}
