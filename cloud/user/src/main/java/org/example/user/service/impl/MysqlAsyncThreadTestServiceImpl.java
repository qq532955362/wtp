package org.example.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.user.pojo.domain.AppShippingAddress;
import org.example.user.service.MysqlAsyncThreadTestService;
import org.example.user.service.base.AppShippingAddressService;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class MysqlAsyncThreadTestServiceImpl implements MysqlAsyncThreadTestService {

    @Resource
    private AppShippingAddressService appShippingAddressService;
    @Resource
    private DataSourceTransactionManager wtpDataSourceTransactionManager;
    @Resource
    private TransactionDefinition transactionDefinition;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean asyncThreadRollBackTest() {

        //主线程会被事务@Transactional注解的自动回滚
        AppShippingAddress appShippingAddress = new AppShippingAddress();
        appShippingAddress.setId(5453);
        appShippingAddress.setUserId(11111111);
        appShippingAddressService.updateById(appShippingAddress);

        //id 5452 异步线程手动回滚
        new Thread(() -> {

            TransactionStatus transaction = wtpDataSourceTransactionManager.getTransaction(transactionDefinition);
            try {
                AppShippingAddress addressUpdate = new AppShippingAddress();
                addressUpdate.setId(5452);
                addressUpdate.setUserId(111111);
                appShippingAddressService.updateById(addressUpdate);
                int i = 4 / 0;
                wtpDataSourceTransactionManager.commit(transaction);
            } catch (Exception e) {
                log.error("副线程异常，异常信息:{}",e.getMessage());
                wtpDataSourceTransactionManager.rollback(transaction);
            }
        }).start();

        int a = 1 / 0;

        return Boolean.TRUE;
    }
}
