## 케이크 판매 사이트 - 케익몰 프로젝트

# 📚프로젝트 설명

* 스프링부트를 이용하여 만든 프로젝트입니다.
* ROLE을 ROLE_SELLER 와 ROLE_USER 로 구분하였습니다.
* 판매자
  * 판매자 페이지, 상품 등록,삭제,수정, 상품관리, 상품 판매 내역 조회
* 구매자
  * 구매자 페이지, 장바구니, 상품 주문 및 취소, 주문내역 조회, 금액 충전
<br>

# 🔧개발 환경

* 운영체제 : Mac OS
* 통합개발환경(IDE) : IntelliJ
* JDK 버전 : JDK 21
* 데이터베이스 : MYSQL
* 빌드 툴 : Maven
* 관리 툴 : Github
<br>

# 💻Dependencies
* SpringBoot DevTools
* Lombok
* MySQL Drivers
* Spring Data JPA
* Spring Security
* Thymeleaf
<br>

# 📝DB 설계
* User
* Item
* Cart
* CartItem
* Order
* OrderItem
* Sale
* SaleItem
![케익몰 ER다이어그램](https://github.com/baejimil/Cakemall_vStella/assets/68216569/40f29cd8-22c6-42d7-a64e-c2647893c755)
/Users/abc/Downloads/케익몰 ER다이어그램2.png
<br>

# 🔥구현 기능
* Entity 설계 ( User, Item, Cart, CartItem, Order, OrderItem, Sale, SaleItem)
* 상품 CRUD 기능 구현
* 상품 CRUD 관련 html 구현
* 상품 리스트 검색 기능 구현
* 상품 리스트 페이징 처리
* 구매자, 판매자 페이지 html 구현
* 장바구니 기능 구현
* 장바구니 페이지 html
* 회원정보수정 기능 구현
* 장바구니 상품 주문 기능
* 상품 관리 페이지, 판매내역 페이지 html
* 상품 상세 페이지 렌더링
* 개별 상품 구매 기능 추가
* 주문 취소 기능
* 충전 API 추가
* Event 페이지 추가하여 페이지 내 게임을 통한 쿠폰 증정 구현 ( 구매자 전용 )
<br>

# :bug:오류 해결
* [MySQL] Field '칼럼' doesn't have a default value 에러 : 해당 칼럼 NOT NULL 에서 NULL로 변경해 해결.
* 구매자 마이페이지에서 OO님 환영합니다. 배너 클릭 시 판매자 페이지 나오는 오류 해결.
* if문 내 isNaN -> Number.isNaN
* alter table cart_item add constraint cart_item___fk foreign key (item_id) references ??? (id); -> domain내 Item의 PRIMARY키가 설정되어 있지 않아서 설정 후 해결.
<br>

# 👌알아간 것
+ SecurityConfig에서 페이지 마다 권한 설정 줄 수 있지만, html파일에서 sec:authorize로 권한을 나눌 수 있다.
+ Thymeleaf th:action="@{/user/cart/{id}/{itemId}(id=${user.id}, itemId=${item.id})}" 에서 여러 개의 매개변수 넣을 때 {id}(id=${user.id})/{itemId}(itemId=${item.id})처럼 각각 하는 것이 아닌 마지막에 해당하는 곳에 한번에 부여.
