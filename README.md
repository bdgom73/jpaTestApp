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
            ├─api (add)
            │      MemberApiController.java (add)
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

2021-12-16
```cmd
└─jpabook
    └─jpashop
        │  InitDB.java
        │  JpabookApplication.java
        │
        ├─api
        │      MemberApiController.java
        │      OrderApiController.java
        │      OrderSimpleApiController.java
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
        ├─repository
        │  │  ItemRepository.java
        │  │  MemberRepository.java
        │  │  OrderRepository.java
        │  │  OrderSearch.java
        │  │
        │  └─order
        │      └─query
        │              OrderSimpleQueryDto.java
        │              OrderSimpleQueryRepository.java
        │
        └─service
                ItemService.java
                MemberService.java
                OrderService.java

```

2021-12-27
```cmd
└─jpabook
    └─jpashop
        │  InitDB.java
        │  JpabookApplication.java
        │
        ├─api
        │      MemberApiController.java
        │      OrderApiController.java
        │      OrderSimpleApiController.java
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
        ├─repository
        │  │  ItemRepository.java
        │  │  MemberRepository.java
        │  │  OrderRepository.java
        │  │  OrderSearch.java
        │  │
        │  └─order
        │      ├─query
        │      │      OrderItemQueryDto.java
        │      │      OrderQueryDto.java
        │      │      OrderQueryRepository.java
        │      │
        │      └─simpleQuery
        │              OrderSimpleQueryDto.java
        │              OrderSimpleQueryRepository.java
        │
        └─service
                ItemService.java
                MemberService.java
                OrderService.java
```

2021-12-28
```cmd

└─jpabook
    └─jpashop
        │  InitDB.java
        │  JpabookApplication.java
        │
        ├─api
        │      MemberApiController.java
        │      OrderApiController.java
        │      OrderSimpleApiController.java
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
        ├─repository
        │  │  ItemRepository.java
        │  │  MemberRepository.java
        │  │  OrderRepository.java
        │  │  OrderSearch.java
        │  │
        │  └─order
        │      ├─query
        │      │      OrderFlatDto.java
        │      │      OrderItemQueryDto.java
        │      │      OrderQueryDto.java
        │      │      OrderQueryRepository.java
        │      │
        │      └─simpleQuery
        │              OrderSimpleQueryDto.java
        │              OrderSimpleQueryRepository.java
        │
        └─service
                ItemService.java
                MemberService.java
                OrderService.java
```


공통 리소스
```cmd

└─resources
    │  application.yml
    │
    ├─static
    │  │  index.html
    │  │
    │  ├─css
    │  │      bootstrap-grid.css
    │  │      bootstrap-grid.css.map
    │  │      bootstrap-grid.min.css
    │  │      bootstrap-grid.min.css.map
    │  │      bootstrap-grid.rtl.css
    │  │      bootstrap-grid.rtl.css.map
    │  │      bootstrap-grid.rtl.min.css
    │  │      bootstrap-grid.rtl.min.css.map
    │  │      bootstrap-reboot.css
    │  │      bootstrap-reboot.css.map
    │  │      bootstrap-reboot.min.css
    │  │      bootstrap-reboot.min.css.map
    │  │      bootstrap-reboot.rtl.css
    │  │      bootstrap-reboot.rtl.css.map
    │  │      bootstrap-reboot.rtl.min.css
    │  │      bootstrap-reboot.rtl.min.css.map
    │  │      bootstrap-utilities.css
    │  │      bootstrap-utilities.css.map
    │  │      bootstrap-utilities.min.css
    │  │      bootstrap-utilities.min.css.map
    │  │      bootstrap-utilities.rtl.css
    │  │      bootstrap-utilities.rtl.css.map
    │  │      bootstrap-utilities.rtl.min.css
    │  │      bootstrap-utilities.rtl.min.css.map
    │  │      bootstrap.css
    │  │      bootstrap.css.map
    │  │      bootstrap.min.css
    │  │      bootstrap.min.css.map
    │  │      bootstrap.rtl.css
    │  │      bootstrap.rtl.css.map
    │  │      bootstrap.rtl.min.css
    │  │      bootstrap.rtl.min.css.map
    │  │      jumbotron-narrow.css
    │  │
    │  └─js
    │          bootstrap.bundle.js
    │          bootstrap.bundle.js.map
    │          bootstrap.bundle.min.js
    │          bootstrap.bundle.min.js.map
    │          bootstrap.esm.js
    │          bootstrap.esm.js.map
    │          bootstrap.esm.min.js
    │          bootstrap.esm.min.js.map
    │          bootstrap.js
    │          bootstrap.js.map
    │          bootstrap.min.js
    │          bootstrap.min.js.map
    │
    └─templates
        │  hello.html
        │  home.html
        │
        ├─fragments
        │      bodyHeader.html
        │      footer.html
        │      header.html
        │
        ├─items
        │      createItemForm.html
        │      itemList.html
        │      updateItemForm.html
        │
        ├─members
        │      createForm.html
        │      memberList.html
        │
        └─order
                orderForm.html
                orderList.html
```