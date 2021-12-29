package com.jpabook.jpashop.service.query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderStatus;

import lombok.Data;

@Data
public class OrderDto {

	private Long orderId;
	private String name;
	private LocalDateTime orderDate;
	private OrderStatus orderStatus;
	private Address address;
	private List<OrderItemDto> orderItems;
	
	public OrderDto(Order order) {
		orderId = order.getId();
		name = order.getMember().getName();
		orderDate = order.getOrderDate();
		orderStatus = order.getStatus();
		address = order.getDelivery().getAddress();		
		orderItems = order.getOrderItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
	}
}
