# 실전 스프링 부트와 JPA 활용1 <br> <small>(웹 애플리케이션 개발 따라하기)</small>

### [인프런 해당 강의 바로가기][address] <br>
[address]: https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-JPA-%ED%99%9C%EC%9A%A9-1

## 파일 구조

2021-12-20
```cmd
─jpabook
        └─jpashop
            │  JpabookApplication.java
            │
            ├─Controller
            │      HomeController.java
            │      MemberController.java
            │      MemberForm.java
            │
            ├─domain
            │  │  Address.java
            │  │  Category.java
            │  │  Delivery.java
            │  │  DeliveryStauts.java
            │  │  Member.java
            │  │  Order.java
            │  │  OrderItem.java
            │  │  OrderStatus.java
            │  │
            │  └─item
            │          Album.java
            │          Book.java
            │          Item.java
            │          Movie.java
            │
            ├─exception
            │      NotEnoughStockException.java
            │
            ├─repositroy
            │      ItemRepository.java
            │      MemberRepository.java
            │      OrderRepository.java
            │      OrderSearch.java
            │
            └─service
                    ItemService.java
                    MemberService.java
                    OrderService.java
```

2021-12-24
```cmd
─jpabook
        └─jpashop
            │  InitDB.java
            │  JpabookApplication.java
            │
            ├─api
            │      MemberApiController.java
            │
            ├─Controller
            │      BookForm.java
            │      HomeController.java
            │      ItemController.java
            │      MemberController.java
            │      MemberForm.java
            │      OrderController.java
            │
            ├─domain
            │  │  Address.java
            │  │  Category.java
            │  │  Delivery.java
            │  │  DeliveryStauts.java
            │  │  Member.java
            │  │  Order.java
            │  │  OrderItem.java
            │  │  OrderStatus.java
            │  │
            │  └─item
            │          Album.java
            │          Book.java
            │          Item.java
            │          Movie.java
            │
            ├─exception
            │      NotEnoughStockException.java
            │
            ├─repositroy
            │      ItemRepository.java
            │      MemberRepository.java
            │      OrderRepository.java
            │      OrderSearch.java
            │
            └─service
                    ItemService.java
                    MemberService.java
                    OrderService.java
```
