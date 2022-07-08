package com.laowengs.shardingsphere.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * order table
 *
 * @author lance
 * @date 2022/2/20 20:48
 */
@Data
@Entity
@Table(name = "t_order")
public class OrderEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long orderId;
  private Long userId;
  private Date createTime;
  private Date updateTime;
}