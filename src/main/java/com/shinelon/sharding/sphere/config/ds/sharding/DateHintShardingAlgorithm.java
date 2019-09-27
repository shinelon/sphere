package com.shinelon.sharding.sphere.config.ds.sharding;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName DateHintShardingAlgorithm
 * @Author syq
 * @Date 2019/9/26 16:14
 **/
public class DateHintShardingAlgorithm implements HintShardingAlgorithm<Date> {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Date> shardingValue) {
        List<String> actualTables = shardingValue.getValues().stream().map(e -> shardingValue.getLogicTableName()+"_"+DateFormatUtils.format(e, "yyyyMM")).collect(Collectors.toList());
        List<String> collect = availableTargetNames.stream().filter(e -> actualTables.contains(e)).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(collect)){
            return collect;
        }
        throw new UnsupportedOperationException();
    }
}
