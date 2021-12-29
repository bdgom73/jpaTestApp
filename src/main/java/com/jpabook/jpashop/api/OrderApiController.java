package com.jpabook.jpashop.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.BatchSize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderItem;
import com.jpabook.jpashop.domain.OrderStatus;
import com.jpabook.jpashop.repository.OrderRepository;
import com.jpabook.jpashop.repository.OrderSearch;
import com.jpabook.jpashop.repository.order.query.OrderFlatDto;
import com.jpabook.jpashop.repository.order.query.OrderItemQueryDto;
import com.jpabook.jpashop.repository.order.query.OrderQueryDto;
import com.jpabook.jpashop.repository.order.query.OrderQueryRepository;
import com.jpabook.jpashop.service.query.OrderDto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

	private final OrderRepository orderRepository;
	private final OrderQueryRepository orderQueryRepository;
	
	/** 2021 12.29 OSIV -> false */
	
	/**
	 * 1 + N 문제
	 * 엔티티로 직접반환
	 * */
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
	
	/**
	 * 엔티티 조회 후 DTO로 변환
	 * */
	@GetMapping("/api/v2/orders")
	public List<OrderDto> ordersV2(){
		List<Order> orders = orderRepository.findAll(new OrderSearch());
		return orders.stream().map(OrderDto::new).collect(Collectors.toList());
	}
	
	/**
	 * O to Many일땐 join fetch x
	 * 페이징 불가능
	 * 컬렉션 페치조인은 1개만 사용가능함.
	 * */
	@GetMapping("/api/v3/orders")
	public List<OrderDto> ordersV3(){
		List<Order> orders = orderRepository.findAllWithItem();
		return orders.stream().map(OrderDto::new).collect(Collectors.toList());
	}
	
	/**
	 * Batch Size 설정 (max = 1000) 적당한사이즈. 100 - 1000
	 * 1 + N -> 1 + 1 로 최적화
	 * 0 to One일땐 페치 조인시 영향 x 
	 * 페이징 가능
	 * */
	@GetMapping("/api/v3.1/orders")
	public List<OrderDto> ordersV3_page(
			@RequestParam(value="offset", defaultValue = "0") int offset,
			@RequestParam(value="limit", defaultValue = "100") int limit){
		List<Order> orders = orderRepository.findAllWithMemeberDelivery(offset, limit);
		return orders.stream().map(OrderDto::new).collect(Collectors.toList());
	}
	
	
	/**
	 * DTO로 직접받기
	 * 1 + N 문제발생
	 * */
	@GetMapping("/api/v4/orders")
	public List<OrderQueryDto> ordersV4(){
		return orderQueryRepository.findOrderQueryDtos();
	}
	
	/**
	 * DTO로 직접받기
	 * in절로 한번에 가져와서 조립해서 리턴
	 * */
	@GetMapping("/api/v5/orders")
	public List<OrderQueryDto> ordersV5(){
		return orderQueryRepository.findAllByDto_optimization();
	}
	
	/**
	 * DTO로 직접받기
	 * 쿼리한번으로 가져와서 조립
	 * */
	@GetMapping("/api/v6/orders")
	public List<OrderQueryDto> ordersV6(){
		 List<OrderFlatDto> flats = orderQueryRepository.findAllByDto_flat();
		 
		 return flats.stream()
				 .collect(
							 Collectors.groupingBy(o -> new OrderQueryDto( 
										 o.getOrderId(), o.getName(), o.getOrderDate(), o.getOrderStatus(), o.getAddress()
								 ),
							 Collectors.mapping(o -> new OrderItemQueryDto(
										 o.getOrderId(), o.getItemName(), o.getOrderPrice(), o.getCount()
									 ) , Collectors.toList()
								 )
							 )
						 ).entrySet().stream()
				.map(e -> 
					new OrderQueryDto(
						e.getKey().getOrderId(),
						e.getKey().getName(), e.getKey().getOrderDate(), e.getKey().getOrderStatus(),
						e.getKey().getAddress(), e.getValue()
					)
				).collect(Collectors.toList());
	}
		
}
