package com.jpabook.jpashop;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Delivery;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderItem;
import com.jpabook.jpashop.domain.item.Book;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitDB {
	
	private final InitService initService;
	
	@PostConstruct
	public void init() {
		initService.dbinit1();
		initService.dbinit2();
	}
	
	@Component
	@Transactional
	@RequiredArgsConstructor
	static class InitService{
		private final EntityManager em;		
		public void dbinit1() {
			Member member = createMember("userA","서울","광화문","12345");
			
			Book book1 = createBook("JPA1 BOOK",10000,100);	
			Book book2 = createBook("JPA2 BOOK",10000,100);
			
			OrderItem item1 = OrderItem.createOrderItem(book1, book1.getPrice(), 50);
			OrderItem item2 = OrderItem.createOrderItem(book2, book1.getPrice(), 2);
			
			Delivery delivery = createDelivery(member);
			
			Order createOrder = Order.createOrder(member, delivery, item1, item2);
			
			em.persist(createOrder);
		}

			
		public void dbinit2() {
			Member member = createMember("userB","부천","삼작로","14493");
			
			Book book1 = createBook("spring1 book", 20000, 100);
			Book book2 = createBook("spring2 book", 40000, 100);
				
			OrderItem item1 = OrderItem.createOrderItem(book1, book1.getPrice(), 1);
			OrderItem item2 = OrderItem.createOrderItem(book2, book2.getPrice(), 2);
			
			Delivery delivery = createDelivery(member);
			
			Order createOrder = Order.createOrder(member, delivery, item1, item2);
			
			em.persist(createOrder);
		}


		private Delivery createDelivery(Member member) {
			Delivery delivery = new Delivery();
			delivery.setAddress(member.getAddress());
			return delivery;
		}
		
		private Book createBook(String name, int price, int stockQuantity) {
			Book book = new Book();
			book.setName(name);
			book.setPrice(price);
			book.setStockQuantity(stockQuantity);
			em.persist(book);
			return book;
		}

		private Member createMember(String name, String city, String street , String zipcode) {
			Member member = new Member();
			member.setName(name);
			member.setAddress(new Address(city,street,zipcode));
			em.persist(member);
			return member;
		}
	}
	
}


