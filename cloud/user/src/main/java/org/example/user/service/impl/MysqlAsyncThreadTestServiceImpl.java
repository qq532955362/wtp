package org.example.user.service.impl;

import org.example.user.pojo.domain.AppShippingAddress;
import org.example.user.service.MysqlAsyncThreadTestService;
import org.example.user.service.base.AppShippingAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class MysqlAsyncThreadTestServiceImpl implements MysqlAsyncThreadTestService {

    @Resource
    private AppShippingAddressService appShippingAddressService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean asyncThreadRollBackTest() {

        AppShippingAddress appShippingAddress = new AppShippingAddress();
        appShippingAddressService.save(appShippingAddress);

        //id 5452
        new Thread(()->{
            AppShippingAddress addressUpdate = new AppShippingAddress();
            addressUpdate.setId(5452);
            addressUpdate.setUserId(111111);
            appShippingAddressService.updateById(addressUpdate);
            int i = 4 / 0;
        }).start();

        return Boolean.TRUE;
    }
}
