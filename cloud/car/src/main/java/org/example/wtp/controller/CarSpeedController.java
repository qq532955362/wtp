package org.example.wtp.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.wtp.domain.CarSpeed;
import org.example.wtp.mapper.CarSpeedMapper;
import org.example.wtp.pojo.req.CarSpeedReq;
import org.example.wtp.pojo.req.CarSpeedReq2;
import org.example.wtp.pojo.req.common.Result;
import org.example.wtp.service.CarSpeedService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/car-speed")
@Slf4j
public class CarSpeedController {
    @Resource
    private CarSpeedService carSpeedService;
    @Resource
    private CarSpeedMapper carSpeedMapper;
    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @PostMapping
    public void add(@RequestBody Map<String, Object> body) {
        String jsonString = JSONObject.toJSONString(body);
        CarSpeed carSpeed = JSONObject.parseObject(jsonString, CarSpeed.class);
        carSpeed.setCreateTime(System.currentTimeMillis() + 3000);
        Random random = new Random();
        double v = random.nextDouble() * 100;
        carSpeed.setSpeed(new BigDecimal(v));
        carSpeed.setCarId(random.nextInt(10));
        boolean save = carSpeedService.save(carSpeed);
        log.info("" + save);
    }

    @GetMapping
    public Boolean list(CarSpeedReq req1, CarSpeedReq2 req2) {
        log.info(JSONObject.toJSONString(req1));
        log.info(JSONObject.toJSONString(req2));
        return true;
    }

    @GetMapping("/cursor")
    public List<CarSpeed> cursor() {

        List<CarSpeed> carSpeeds = new ArrayList<>();
        carSpeedMapper.selectCursor((resultContext) -> {

        });

        return carSpeeds;
    }

    @GetMapping("/list")
    public Result<List<CarSpeed>> listCars() {
        return Result.ok(carSpeedMapper.selectList(null).subList(0, 10));
    }
}
