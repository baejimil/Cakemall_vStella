##케이크 판매 사이트 - 케익몰 프로젝트

#📚프로젝트 설명
<br>
- 스프링부트를 이용하여 만든 프로젝트입니다.
- ROLE을 ROLE_SELLER 와 ROLE_USER 로 구분하였습니다.
- 판매자
  - 판매자 페이지, 상품 등록,삭제,수정, 상품관리, 상품 판매 내역 조회
- 구매자
  - 구매자 페이지, 장바구니, 상품 주문 및 취소, 주문내역 조회, 금액 충전

#🔧개발 환경
<br>
- 운영체제 : Mac OS
- 통합개발환경(IDE) : IntelliJ
- JDK 버전 : JDK 21
- 데이터베이스 : MYSQL
- 빌드 툴 : Maven
- 관리 툴 : Github
<br>

#💻Dependencies
- SpringBoot DevTools
- Lombok
- MySQL Drivers
- Spring Data JPA
- Spring Security
- Thymeleaf
<br>

#📝DB 설계



[MySQL] Field '칼럼' doesn't have a default value 에러 : 
해당 칼럼 NOT NULL 에서 NULL로 변경해 해결
