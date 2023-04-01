package org.example.wtp.controller;

import com.alibaba.fastjson.JSONObject;
import org.example.wtp.domain.CarSpeed;
import org.example.wtp.service.CarSpeedService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/car-speed")
public class CarSpeedController {
    @Resource
    private CarSpeedService carSpeedService;

    @PostMapping
    public void add(@RequestBody Map<String,Object> body){
        String jsonString = JSONObject.toJSONString(body);
        CarSpeed carSpeed = JSONObject.parseObject(jsonString, CarSpeed.class);
        carSpeed.setCreateTime(System.currentTimeMillis() + 3000);
        Random random = new Random();
        double v = random.nextDouble() * 100;
        carSpeed.setSpeed(new BigDecimal(v));
        carSpeed.setCarId(random.nextInt(10));
        carSpeedService.save(carSpeed);
    }
}
