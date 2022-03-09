package org.example.seata.runner;

import io.seata.common.util.NetUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import io.seata.server.Server;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangtaiping
 * 2022/3/9 15:12
 */
@Component
public class SeataRunner implements ApplicationRunner {

    @Value("${server.port}")
    private String port;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Server.main(getEnvProperties());
    }

    private String[] getEnvProperties() throws Exception {
        List<String> params =new ArrayList<>();
        if (StringUtils.isEmpty(port)){
            throw new Exception("server port is null,please check it!");
        }
        params.add("-p");
        params.add(port);

        params.add("-h");
        params.add(NetUtil.getLocalIp());

        return params.toArray(new String[2]);
    }
}
