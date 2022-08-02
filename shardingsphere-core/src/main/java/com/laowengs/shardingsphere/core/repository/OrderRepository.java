package com.laowengs.shardingsphere.core.repository;

import com.laowengs.shardingsphere.core.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * order
 *
 * @author lance
 * @date 2022/2/19 11:50
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query(value = "select * from t_order  order by id limit 10,5;", nativeQuery = true)
    List<Object> limit();

    @Query(value = "SELECT * FROM t_order o JOIN t_order_item i ON o.order_id=i.order_id WHERE o.order_id in (1,5)", nativeQuery = true)
    List<Object> joinByOnePartition();

    @Query(value = "SELECT * FROM t_order o JOIN t_order_item i ON o.order_id=i.order_id WHERE o.order_id in (1,2);", nativeQuery = true)
    List<Object> joinByManyPartition();

    @Query(value = "SELECT * FROM t_order_inner o JOIN t_order_detail_inner i ON o.order_id=i.order_id WHERE o.order_id in (1,2);", nativeQuery = true)
    List<Object> joinByNoBindingTable();

    @Query(value = " SELECT * FROM t_order_inner o JOIN t_order_item_inner i ON o.order_id=i.order_id WHERE o.order_id in (1,2);", nativeQuery = true)
    List<Object> joinByBindingTable();

    @Query(value = " select * from t_order where order_id in (select order_id from t_order_item where order_id in (1,2));", nativeQuery = true)
    List<Object> subByManyPartition();

    @Query(value = " select * from t_order where order_id in (select order_id from t_order_item where order_id in (1,5));", nativeQuery = true)
    List<Object> subByOnePartition();

    @Query(value = " select * from t_order where order_id = 1\n" +
            "UNION\n" +
            "select * from t_order where order_id = 5;", nativeQuery = true)
    List<Object> unionByOnePartition();


    @Query(value = " select * from t_order where order_id = 1 UNION select * from t_order where order_id = 2;", nativeQuery = true)
    List<Object> unionByManyPartition();

    @Query(value = " select order_id,count(1) from t_order group by order_id having count(1) >1;", nativeQuery = true)
    List<Object> groupByCountUsePartitionKey();

    @Query(value = " select order_id,avg(id) from t_order group by order_id order by avg(id);", nativeQuery = true)
    List<Object> groupByAvgUsePartitionKey();


    @Query(value = " select user_id,count(1) from t_order group by user_id having count(1) >1;", nativeQuery = true)
    List<Object> groupByCountNoPartitionKey();

    @Query(value = " select user_id,avg(id) from t_order group by user_id order by avg(id) limit 2,2;", nativeQuery = true)
    List<Object> groupByAvgNoPartitionKey();


    @Query(value = " select order_id,count(1) from t_order WHERE ORDER_ID = 3 group by order_id having count(1) > 1 order by count(1) desc ;", nativeQuery = true)
    List<Object> groupByCountPartitionKey();

    @Query(value = "select order_id,avg(id) from t_order WHERE ORDER_ID = 3 group by order_id order by avg(id);", nativeQuery = true)
    List<Object> groupByAvgPartitionKey();
}