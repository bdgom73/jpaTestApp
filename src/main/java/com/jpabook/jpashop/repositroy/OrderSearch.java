package com.jpabook.jpashop.repositroy;

import com.jpabook.jpashop.domain.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {

	private String memberName; // 회원이름
	private OrderStatus orderStatus; // [ORDER, CANCEL]
	
}
