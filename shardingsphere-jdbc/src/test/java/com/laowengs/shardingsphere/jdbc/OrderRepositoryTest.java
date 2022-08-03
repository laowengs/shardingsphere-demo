package com.laowengs.shardingsphere.jdbc;

import com.alibaba.fastjson.JSON;
import com.laowengs.shardingsphere.core.entity.OrderEntity;
import com.laowengs.shardingsphere.core.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@SpringBootTest
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void save() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < 2; i++) {
            OrderEntity order = new OrderEntity();
            order.setOrderId(Math.abs(random.nextLong()));
            order.setUserId(Math.abs(random.nextLong()));
            order.setCreateTime(new Date());
            order.setUpdateTime(new Date());
            orderRepository.save(order);
        }
    }

    @Test
    void saveAll() {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        List<OrderEntity> orders = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            OrderEntity order = new OrderEntity();
            order.setOrderId(Math.abs(random.nextLong()));
            order.setUserId(Math.abs(random.nextLong()));
            order.setCreateTime(new Date());
            order.setUpdateTime(new Date());
            orders.add(order);
        };
        log.info(JSON.toJSONString(orders));
        orderRepository.saveAll(orders);

    }

    @Test
    void selectAll() {
        List<OrderEntity> all = orderRepository.findAll();
        System.out.println(all);
    }

    @Test
    void limit() {
        orderRepository.limit();
    }

    @Test
    void joinByOnePartition() {
        orderRepository.joinByOnePartition();
    }

    @Test
    void joinByManyPartition() {
        orderRepository.joinByManyPartition();
    }

    @Test
    void joinByNoBindingTable() {
        orderRepository.joinByNoBindingTable();
    }

    @Test
    void joinByBindingTable() {
        orderRepository.joinByBindingTable();
    }

    @Test
    void subByManyPartition() {
        orderRepository.subByManyPartition();
    }

    @Test
    void subByOnePartition() {
        orderRepository.subByOnePartition();
    }

    @Test
    void unionByOnePartition() {
        orderRepository.unionByOnePartition();
    }

    @Test
    void unionByManyPartition() {
        orderRepository.unionByManyPartition();
    }

    @Test
    void groupByCountUsePartitionKey() {
        orderRepository.groupByCountUsePartitionKey();
    }

    @Test
    void groupByAvgUsePartitionKey() {
        orderRepository.groupByAvgUsePartitionKey();
    }

    @Test
    void groupByCountNoPartitionKey() {
        orderRepository.groupByCountNoPartitionKey();
    }

    @Test
    void groupByAvgNoPartitionKey() {
        orderRepository.groupByAvgNoPartitionKey();
    }

    @Test
    void groupByCountPartitionKey() {
        orderRepository.groupByCountPartitionKey();
    }

    @Test
    void groupByAvgPartitionKey() {
        orderRepository.groupByAvgPartitionKey();
    }

}