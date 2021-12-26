package com.jpabook.jpashop.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jpabook.jpashop.api.OrderSimpleApiController;
import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderStatus;
import com.jpabook.jpashop.repository.order.query.OrderSimpleQueryDto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

	private final EntityManager em;
	
	public void save(Order order) {
		em.persist(order);
	}
	
	public Order findOne(Long id) {
		return em.find(Order.class, id);
	}
	
	public List<Order> findAll(OrderSearch orderSearch){		
		//language=JPAQL
		 String jpql = "select o From Order o join o.member m";
		 boolean isFirstCondition = true;
		 
		 //주문 상태 검색
		 if (orderSearch.getOrderStatus() != null) {
			 if (isFirstCondition) {
				 jpql += " where";
				 isFirstCondition = false;
			 } else {
				 jpql += " and";
			 }
			 jpql += " o.status = :status";
		 }
		 
		 //회원 이름 검색
		 if (StringUtils.hasText(orderSearch.getMemberName())) {
			 if (isFirstCondition) {
				 jpql += " where";
				 isFirstCondition = false;
			 } else {
				 jpql += " and";
			 }
			 jpql += " m.name like :name";
		 }
		 
		 TypedQuery<Order> query = em.createQuery(jpql, Order.class)
				 .setMaxResults(1000); //최대 1000건
		 
		 if (orderSearch.getOrderStatus() != null) {
			 query = query.setParameter("status", orderSearch.getOrderStatus());
		 }
		 if (StringUtils.hasText(orderSearch.getMemberName())) {
			 query = query.setParameter("name", orderSearch.getMemberName());
		 }
		 
		 return query.getResultList();	
	}

	public List<Order> findAllWithMemeberDelivery() {
		return em.createQuery(
						"select o from Order o" + 
						" join fetch o.member m" +
						" join fetch o.delivery d", Order.class)
						.getResultList();
	}

	
	
}