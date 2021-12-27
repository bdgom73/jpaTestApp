package com.jpabook.jpashop.repository.order.simpleQuery;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

	private final EntityManager em;
	
	// DTO로 반환하기
	public List<OrderSimpleQueryDto> findOrderDtos() {
		return em.createQuery(
					"select new com.jpabook.jpashop.repository.order.simpleQuery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" + 
					" from Order o" +
					" join o.member m" +
					" join o.delivery d", OrderSimpleQueryDto.class)
			.getResultList();
	}
}
