package com.jpabook.jpashop.service.query;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderQuerySercvice {

	// OSIV = false로 설정했을떄,
	
	private final OrderRepository orderRepository;
	
	public List<OrderDto> orderV3() {
		List<Order> orders = orderRepository.findAllWithItem();
		return orders.stream().map(OrderDto::new).collect(Collectors.toList());
	}
}
