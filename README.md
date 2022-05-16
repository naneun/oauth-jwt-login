# 2-weeks-of-individual-study

# JPA, Spring Data JPA, QueryDSL 을 학습하여 SIDE DISH 에 적용해보기

- ## 1주차

  - 5/9 기본편, 활용 1 학습 및 테이블 설계
  - 5/10 활용 1, 활용 2 학습 및 테이블 수정 및 엔티티 설계
  - 5/11 활용 2, Spring Data JPA, JWT 학습 및 엔티티 설계, 레포지토리 설계
  - 5/12 Spring Data JPA, QueryDSL 학습 및 엔티티 설계 완료
  - 5/13 엔티티 연관관계 편의 메서드 작성
  - 5/14 jwt 로그인 기능 구현 (access-token 을 사용하여 `인증`, refresh-token 을 사용하여 `만료된` access-token `갱신`)
  - 5/15 `Github` OAuth2 로그인 기능 구현

- ## 2주차

  - 5/16 - 호눅스 백엔드 수업(5/9) 복습 및 `Google` OAuth2 로그인 기능(+ refresh-token 을 사용하여 `만료된` access-token `갱신`)

# 테이블 설계도
![Untitled Diagram drawio (20)](https://user-images.githubusercontent.com/47964708/168468785-e8ed36dc-5091-4628-9995-8236e5210f9d.png)  

- ## 수정사항
  - 2022.05.11 - delivery_area: `값 타입 컬렉션`을 사용해보기 위해 '다대다'로 연관관계를 맺지 않았습니다.
  - 2022.05.12 - 다대다 연관관계를 사용하지 않은 근거가 필요하여 delivery_area -> excluded_area 변경
  - 2022.05.15 - 어플리케이션이 서버 입장에서 `jwt_refresh_token` 을, 클라이언트 입장에서 `oauth_access_token`, `oauth_refresh_token` 을 저장하고 있도록 member 테이블에 칼럼 추가

- ## 참고사항
  - ~2022.05.15 - GitHub 의 경우, Refresh-Token 이 따로 존재하지 않고 Access-Token 의 만료기간 또한 존재하지 않는다.~
> ![SmartSelectImage_2022-05-16-04-27-08](https://user-images.githubusercontent.com/47964708/168490486-e473ac2b-9296-45c2-b68a-49d1b9cbf15f.png) 
> ![SmartSelectImage_2022-05-16-04-26-48](https://user-images.githubusercontent.com/47964708/168490490-c633428b-cad9-41d0-8c85-1b63d25160f6.png)  
