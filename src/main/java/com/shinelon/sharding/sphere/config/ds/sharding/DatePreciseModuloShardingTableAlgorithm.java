package com.shinelon.sharding.sphere.config.ds.sharding;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;
import java.util.Date;

/**
 * @ClassName DatePreciseModuloShardingTableAlgorithm
 * @Author syq
 * @Date 2019/9/26 11:03
 **/
public class DatePreciseModuloShardingTableAlgorithm implements PreciseShardingAlgorithm<Date> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> shardingValue) {
        String yyyyMM = DateFormatUtils.format(shardingValue.getValue(), "yyyyMM");
        for (String each : availableTargetNames) {
            if (each.endsWith(yyyyMM)) {
                return each;
            }
        }
        throw new UnsupportedOperationException();

    }
}
