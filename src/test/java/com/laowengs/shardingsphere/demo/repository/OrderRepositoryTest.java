package com.laowengs.shardingsphere.demo.repository;

import com.laowengs.shardingsphere.demo.entity.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Slf4j
@SpringBootTest
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void save() {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        IntStream.range(20, 41).forEach(i -> {
            OrderEntity order = new OrderEntity();
            order.setOrderId(Math.abs(random.nextLong()));
            order.setUserId(Math.abs(random.nextLong()));
            order.setCreateTime(new Date());
            order.setUpdateTime(new Date());
            orderRepository.save(order);
        });
    }

    @Test
    void selectAll() {
        List<OrderEntity> all = orderRepository.findAll();
        System.out.println(all);
    }

    @Test
    void name() {
    }
}