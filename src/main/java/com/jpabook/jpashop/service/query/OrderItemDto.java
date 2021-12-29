package com.jpabook.jpashop.service.query;

import com.jpabook.jpashop.domain.OrderItem;

import lombok.Data;

@Data
public class OrderItemDto {
	
	private String itemName;
	private int orderPrice;
	private int count;
	private int totalPrice;
	
	public OrderItemDto(OrderItem orderItem) {
		itemName = orderItem.getItem().getName();
		orderPrice = orderItem.getOrderPrice();
		count = orderItem.getCount();
		totalPrice = orderItem.getTotalPrice();
	}
}
