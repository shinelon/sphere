package com.shinelon.sharding.sphere.mapper;

import com.shinelon.sharding.sphere.SphereApplicationTests;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.shinelon.sharding.sphere.mapper.PtTradeCommonOrderMapper;

import java.text.ParseException;
import java.util.Date;

/**
 * @ClassName PtTradeCommonOrderMapperTest
 * @Author syq
 * @Date 2019/9/27 15:23
 **/
public class PtTradeCommonOrderMapperTest extends SphereApplicationTests {
    @Autowired
    private PtTradeCommonOrderMapper ptTradeCommonOrderMapper;

    @Test
    public void selectTest() throws ParseException {
        try (HintManager hintManager = HintManager.getInstance()){
            Date yyyyMMdd1 = DateUtils.parseDate("20190926", "yyyyMMdd");
            Date yyyyMMdd2 = DateUtils.parseDate("20191001", "yyyyMMdd");
            Date yyyyMMdd3 = DateUtils.parseDate("20191101", "yyyyMMdd");
            hintManager.addTableShardingValue("pt_trade_common_order",yyyyMMdd1);
            hintManager.addTableShardingValue("pt_trade_common_order",yyyyMMdd2);
            hintManager.addTableShardingValue("pt_trade_common_order",yyyyMMdd3);
            ptTradeCommonOrderMapper.selectList(null);
        }

    }

}
