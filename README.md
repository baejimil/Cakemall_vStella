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
<br>

# :bug:
[MySQL] Field '칼럼' doesn't have a default value 에러 : 
해당 칼럼 NOT NULL 에서 NULL로 변경해 해결
