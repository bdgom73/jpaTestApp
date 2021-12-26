package com.jpabook.jpashop.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderItem;
import com.jpabook.jpashop.repository.OrderRepository;
import com.jpabook.jpashop.repository.OrderSearch;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

	private final OrderRepository orderRepository;
	
	@GetMapping("/api/v1/orders")
	public List<Order> ordersV1(){
		List<Order> orders = orderRepository.findAll(new OrderSearch());
		
		for(Order order : orders) {
			order.getMember().getName();
			order.getDelivery().getAddress();
			
			List<OrderItem> orderItems = order.getOrderItems();
			orderItems.stream().forEach(o-> o.getItem().getName());
		}
		
		return orders;
	}
}
