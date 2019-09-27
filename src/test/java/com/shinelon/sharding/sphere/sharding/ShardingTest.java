package com.shinelon.sharding.sphere.sharding;

import com.alibaba.fastjson.JSON;
import com.shinelon.sharding.sphere.SphereApplicationTests;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ShardingTest
 * @Author syq
 * @Date 2019/9/26 11:21
 **/
public class ShardingTest extends SphereApplicationTests {

    @Autowired
    private JdbcTemplate pointsJdbcTemplate;

    @Test
    public void shardingTest() throws ParseException {
        try (HintManager hintManager = HintManager.getInstance()){
            Date yyyyMMdd1 = DateUtils.parseDate("20190926", "yyyyMMdd");
            Date yyyyMMdd2 = DateUtils.parseDate("20191001", "yyyyMMdd");
            Date yyyyMMdd3 = DateUtils.parseDate("20191101", "yyyyMMdd");
            hintManager.addTableShardingValue("pt_trade_common_order",yyyyMMdd1);
            hintManager.addTableShardingValue("pt_trade_common_order",yyyyMMdd2);
            hintManager.addTableShardingValue("pt_trade_common_order",yyyyMMdd3);
            List<Map<String, Object>> maps = pointsJdbcTemplate.queryForList("select * from pt_trade_common_order");
            logger.info("{}", JSON.toJSONString(maps));
            List<Map<String, Object>> maps2 = pointsJdbcTemplate.queryForList("select * from pt_product_rule");
            logger.info("{}", JSON.toJSONString(maps2));
        }
    }

    @Test
    public void sharding2Test(){
        List<Map<String, Object>> maps = pointsJdbcTemplate.queryForList("select * from pt_trade_common_order where create_time> ? ",new Date());
        logger.info("{}", JSON.toJSONString(maps));
    }

    @Test
    public void sharding3Test() throws ParseException {
//STR_TO_DATE('2019-10-25',"%Y-%m-%d")
//        pointsJdbcTemplate.update("insert into pt_trade_common_order(create_time) value (?)", DateUtils.parseDate("2019-10-25","yyyy-MM-dd"));
//        logger.info("{}", JSON.toJSONString(maps));

        try (HintManager hintManager = HintManager.getInstance()){
            Date yyyyMMdd2 = DateUtils.parseDate("20191001", "yyyyMMdd");
            Date yyyyMMdd3 = DateUtils.parseDate("20191101", "yyyyMMdd");
            hintManager.addTableShardingValue("pt_trade_common_order",yyyyMMdd2);
            hintManager.addTableShardingValue("pt_trade_common_order",yyyyMMdd3);
            pointsJdbcTemplate.update("insert into pt_trade_common_order(create_time) value (?)", DateUtils.parseDate("2019-10-25","yyyy-MM-dd"));
        }

    }
}
