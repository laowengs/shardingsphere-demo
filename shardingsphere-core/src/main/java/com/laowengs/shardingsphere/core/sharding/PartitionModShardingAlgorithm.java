package com.laowengs.shardingsphere.core.sharding;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Slf4j
public class PartitionModShardingAlgorithm implements StandardShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        log.info("collection: {}",collection);
        log.info("preciseShardingValue: {}",preciseShardingValue);
        log.info("dataNodeInfo: {}", JSON.toJSONString(preciseShardingValue.getDataNodeInfo()));
        List<String> collect = collection.stream().collect(Collectors.toList());
        return collect.get(0);
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
        log.info("collection: {}",collection);
        log.info("preciseShardingValue: {}",rangeShardingValue);
        List<String> collect = collection.stream().collect(Collectors.toList());
        return collect;
    }

    @Override
    public Properties getProps() {
        return null;
    }

    @Override
    public void init(Properties properties) {

    }
}
