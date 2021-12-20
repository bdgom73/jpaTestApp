package com.jpabook.jpashop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.persistence.EntityManager;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.exceptions.TemplateAssertionException;

import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderStatus;
import com.jpabook.jpashop.domain.item.Book;
import com.jpabook.jpashop.domain.item.Item;
import com.jpabook.jpashop.exception.NotEnoughStockException;
import com.jpabook.jpashop.repositroy.OrderRepository;

@SpringBootTest
@Transactional
public class OrderServiceTest {

	@Autowired
	OrderService orderService;
	@Autowired
	EntityManager em;
	@Autowired
	OrderRepository orderRepository;
	
	@Test
	void orderTest() {
		//given
		Member member = createMember();	
		Book book = createBook("테스트책",10000,10);
		
		//when
		int orderCount = 2;
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
		
		//then
		Order or = orderRepository.findOne(orderId);
	
		assertEquals(OrderStatus.ORDER , or.getStatus(), "상품 주문시 상태는 ORDER" );
		assertEquals(1 ,or.getOrderItems().size() , "상품 주문 수" );
		assertEquals(10000*orderCount , or.getTotalPrice(),"상품 가격은 가격 * 수량이다");
		assertEquals(8 , book.getStockQuantity() ,"주문 수량만큼 재고가 줄어야한다.");
	}

	
	
	@Test
	void orderStockUpTest() throws Exception {
		//given
		Member member = createMember();	
		Book book = createBook("테스트책",10000,10);
		
		
		//when
		int orderCount = 11;
		//then
		assertThrows(NotEnoughStockException.class , ()-> orderService.order(member.getId(), book.getId(), orderCount), "재고가 초과되지않았습니다.");
	}

	@Test
	void orderCancelTest() {
		//given
		Member member = createMember();
		Book book = createBook("테스트책", 10000, 10);
		
		int orderCount = 2;
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
		
		//when
		orderService.cancelOrder(orderId);
		
		//then
		Order order = orderRepository.findOne(orderId);
		
		assertEquals(OrderStatus.CANCLE, order.getStatus(),"주문 취소시 상태는 CANCEL이다");
		assertEquals(10, book.getStockQuantity() ,"주문 취소시 수량은 원복되어야한다.");
	}
	
	@Test
	void orderStockTest() {
		//given
		
		//when
		
		//then
	}
	
	private Book createBook(String name, int price, int stock) {
		Book book = new Book();
		book.setName(name);
		book.setPrice(price);
		book.setStockQuantity(stock);
		em.persist(book);
		return book;
	}

	private Member createMember() {
		Member member = new Member();
		member.setName("userA");
		member.setAddress(new Address("서울","강가","12-12"));
		em.persist(member);
		return member;
	}
}
