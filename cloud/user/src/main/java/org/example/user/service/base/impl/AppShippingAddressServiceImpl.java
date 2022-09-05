package org.example.user.service.base.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.user.pojo.domain.AppShippingAddress;
import org.example.user.service.base.AppShippingAddressService;
import org.example.user.pojo.mapper.AppShippingAddressMapper;
import org.springframework.stereotype.Service;

/**
* @author wangtaiping
* @description 针对表【app_shipping_address(App用户地址)】的数据库操作Service实现
* @createDate 2022-09-05 14:34:25
*/
@Service
public class AppShippingAddressServiceImpl extends ServiceImpl<AppShippingAddressMapper, AppShippingAddress>
    implements AppShippingAddressService{

}




