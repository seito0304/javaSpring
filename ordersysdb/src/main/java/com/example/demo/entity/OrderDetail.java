package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "t_orderdetail")
@IdClass(OrderDetailCompositeKey.class)
@Data
public class OrderDetail {
	@Id
	@Column(name = "orderid")
	private Integer orderid;
	@Id
	@Column(name = "pid")
	private String pid;
	@Column(name = "amount")
	private Integer amount;
}