package org.example.user.service.impl;

import org.example.user.service.MysqlAsyncThreadTestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MysqlAsyncThreadTestServiceImpl implements MysqlAsyncThreadTestService {


    @Override
    public Boolean asyncThreadRollBackTest() {
        return null;
    }
}
