package org.example.wtp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.wtp.domain.CarSpeed;
import org.example.wtp.service.CarSpeedService;
import org.example.wtp.mapper.CarSpeedMapper;
import org.springframework.stereotype.Service;

/**
* @author wangtaiping
* @description 针对表【car_speed】的数据库操作Service实现
* @createDate 2023-03-31 17:20:02
*/
@Service
public class CarSpeedServiceImpl extends ServiceImpl<CarSpeedMapper, CarSpeed>
    implements CarSpeedService{

}




