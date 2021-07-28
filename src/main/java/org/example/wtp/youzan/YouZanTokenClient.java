package org.example.wtp.youzan;

import com.youzan.cloud.open.sdk.common.exception.SDKException;
import com.youzan.cloud.open.sdk.core.client.auth.Token;
import com.youzan.cloud.open.sdk.core.client.core.DefaultYZClient;
import com.youzan.cloud.open.sdk.core.oauth.model.OAuthToken;
import com.youzan.cloud.open.sdk.core.oauth.token.TokenParameter;
import com.youzan.cloud.open.sdk.gen.v4_0_0.api.YouzanTradesSoldGet;
import com.youzan.cloud.open.sdk.gen.v4_0_0.model.YouzanTradesSoldGetParams;
import com.youzan.cloud.open.sdk.gen.v4_0_1.model.YouzanTradesSoldGetResult;
import org.example.wtp.youzan.constant.QiTianZhenEnlightenProperties;
import org.example.wtp.youzan.domain.YouZanGoodsId;

import java.util.Date;

/**
 * @author wtp
 * @date 2021/7/24/024 17:32
 */
public class YouZanTokenClient {


    public static void main(String[] args) throws SDKException {
        System.out.println(getToken().getAccessToken());
    }

    public static Token getToken() {
        DefaultYZClient yzClient = new DefaultYZClient();
        TokenParameter tokenParameter = null;
        try {
            tokenParameter = TokenParameter.self()
                    .clientId(QiTianZhenEnlightenProperties.CLIENT_ID)
                    .clientSecret(QiTianZhenEnlightenProperties.CLIENT_SECRET)
                    .grantId(QiTianZhenEnlightenProperties.GRANT_ID)
                    .refresh(true)
                    .build();
        } catch (SDKException e) {
            System.out.println(e.getMessage());
        }

        OAuthToken oAuthToken = null;
        try {
            oAuthToken = yzClient.getOAuthToken(tokenParameter);
        } catch (SDKException e) {
            System.out.println(e.getMessage());
        }
        return new Token(oAuthToken.getAccessToken());
    }

    public static void searchYouZanOrder(YouZanGoodsId youZanGoodsId, Date startDate, Date endDate) {
        DefaultYZClient yzClient = new DefaultYZClient();

        YouzanTradesSoldGet youzanTradesSoldGet = new YouzanTradesSoldGet();

        YouzanTradesSoldGetParams youzanTradesSoldGetParams = new YouzanTradesSoldGetParams();
        youzanTradesSoldGetParams.setGoodsId(youZanGoodsId.getValue());
        youzanTradesSoldGetParams.setStartCreated(startDate);
        youzanTradesSoldGetParams.setEndUpdate(endDate);
        youzanTradesSoldGet.setAPIParams(youzanTradesSoldGetParams);

        YouzanTradesSoldGetResult result;
        try {
            result = yzClient.invoke(youzanTradesSoldGet, getToken(), YouzanTradesSoldGetResult.class);
        } catch (SDKException e) {
            System.out.println(e.getMessage());
        }

    }


    public YouZanTokenClient() throws SDKException {

    }
}
