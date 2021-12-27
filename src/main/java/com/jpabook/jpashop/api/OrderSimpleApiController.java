package com.jpabook.jpashop.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderStatus;
import com.jpabook.jpashop.repository.OrderRepository;
import com.jpabook.jpashop.repository.OrderSearch;
import com.jpabook.jpashop.repository.order.simpleQuery.OrderSimpleQueryDto;
import com.jpabook.jpashop.repository.order.simpleQuery.OrderSimpleQueryRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

	private final OrderRepository orderRepository;
	private final OrderSimpleQueryRepository orderSimpleQueryRepository;
	
	/** 
	 * 사용 x 
	 * 연관관계 무한 루프
	 * */
	@GetMapping("/api/v1/simple-orders")
	public List<Order> ordersV1(){
		return orderRepository.findAll(new OrderSearch());
	}
	
	/**
	 * 1 + N 문제 발생
	 * 성능 저하
	 * */
	@GetMapping("/api/v2/simple-orders")
	public List<SimpleOrderDto> ordersV2(){
		List<Order> findAll = orderRepository.findAll(new OrderSearch());
		return findAll.stream()
				.map(SimpleOrderDto::new)
				.collect(Collectors.toList());
	}
	
	/**
	 * fetch join을 이용
	 * 연관관계 한번에 매핑
	 * */
	@GetMapping("/api/v3/simple-orders")
	public List<SimpleOrderDto> ordersV3(){
		List<Order> orders = orderRepository.findAllWithMemeberDelivery();
		return orders.stream().map(SimpleOrderDto::new).collect(Collectors.toList());	
	}
	
	/**
	 * DTO로 직접반환
	 * 경계가 모호해짐
	 * */
	@GetMapping("/api/v4/simple-orders")
	public List<OrderSimpleQueryDto> ordersV4(){
		return orderSimpleQueryRepository.findOrderDtos();
	}
	
	
	@Data
	static class SimpleOrderDto{
		private Long orderId;
		private String name;
		private LocalDateTime orderDate;
		private OrderStatus orderStatus;
		private Address address;
		
		public SimpleOrderDto(Order o) {
			orderId = o.getId();
			name = o.getMember().getName();
			orderDate = o.getOrderDate();
			orderStatus = o.getStatus();
			address = o.getDelivery().getAddress();
		}
	}
}
