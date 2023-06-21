package org.example.wtp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.session.ResultHandler;
import org.example.wtp.domain.CarSpeed;

/**
 * @author wangtaiping
 * @description 针对表【car_speed】的数据库操作Mapper
 * @createDate 2023-03-31 17:20:02
 * @Entity org.example.wtp.domain.CarSpeed
 */
public interface CarSpeedMapper extends BaseMapper<CarSpeed> {

    @Options(resultSetType = ResultSetType.FORWARD_ONLY, fetchSize = 5)
    void selectCursor(ResultHandler<CarSpeed> resultHandler);

}




