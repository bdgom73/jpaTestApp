package com.jpabook.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpabook.jpashop.domain.Delivery;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderItem;
import com.jpabook.jpashop.domain.item.Item;
import com.jpabook.jpashop.repositroy.ItemRepository;
import com.jpabook.jpashop.repositroy.MemberRepository;
import com.jpabook.jpashop.repositroy.OrderRepository;
import com.jpabook.jpashop.repositroy.OrderSearch;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class OrderService {

	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;
	
	/** 
	 * 주문
	 */	
	@Transactional
	public Long order(Long memberId, Long itemId, int count) {
		// 엔티티조회
		Member member = memberRepository.findOne(memberId);
		Item item = itemRepository.findOne(itemId);
		
		// 배송정보저장
		Delivery delivery = new Delivery();
		delivery.setAddress(member.getAddress());
		
		// 주문상품 생성
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

		// 주문생성
		Order order = Order.createOrder(member, delivery, orderItem);
		
		//주문 저장
		orderRepository.save(order);
		
		return order.getId();
		
	}
	/**
	 * 취소
	 */
	@Transactional
	public void cancelOrder(Long orderId) {
		// 주문엔티티 조회
		Order order = orderRepository.findOne(orderId);
		// 주문 취소
		order.cancel();
	}
	
	//	검색
	public List<Order> searchOrder(OrderSearch orderSearch){
		return orderRepository.findAll(orderSearch);
	}
}
