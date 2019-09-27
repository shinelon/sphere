package com.shinelon.sharding.sphere.service.impl;

import com.shinelon.sharding.sphere.service.OrderService;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;

/**
 * @ClassName OrderServiceImpl
 * @Author syq
 * @Date 2019/9/26 17:10
 **/
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private JdbcTemplate pointsJdbcTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public  void save() throws ParseException {
        try (HintManager hintManager = HintManager.getInstance()){
            Date yyyyMMdd2 = DateUtils.parseDate("20191001", "yyyyMMdd");
            Date yyyyMMdd3 = DateUtils.parseDate("20191101", "yyyyMMdd");
            hintManager.addTableShardingValue("pt_trade_common_order",yyyyMMdd2);
            hintManager.addTableShardingValue("pt_trade_common_order",yyyyMMdd3);
            pointsJdbcTemplate.update("insert into pt_trade_common_order(create_time) value (?)", DateUtils.parseDate("2019-10-25","yyyy-MM-dd"));
        }
    }

}
