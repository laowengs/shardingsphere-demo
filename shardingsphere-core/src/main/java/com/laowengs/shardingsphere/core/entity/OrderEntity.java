package com.laowengs.shardingsphere.core.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * order table
 *
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