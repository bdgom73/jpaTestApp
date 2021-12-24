package com.jpabook.jpashop.Controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class BookForm {

	private Long id;
	
	private String name;
	private int price;
	private int stockQuantity;
	
	private String author;
	private String isbn;
	
	
}
