# 2-weeks-of-individual-study

# JPA, Spring Data JPA, QueryDSL 을 학습하여 SIDE DISH 에 적용해보기

- ## 1주차

  - 5/9 기본편, 활용 1 학습 및 테이블 설계
  - 5/10 활용 1, 활용 2 학습 및 테이블 수정 및 엔티티 설계
  - 5/11 활용 2, Spring Data JPA, JWT 학습 및 엔티티 설계, 레포지토리 설계
  - 5/12 Spring Data JPA, QueryDSL 학습 및 엔티티 설계 완료
  - 5/13 엔티티 연관관계 편의 메서드 작성 및 QueryDsl 을 사용하여 커스텀 레포지토리 작성 - 예정

# 테이블 설계도
![Untitled Diagram drawio (16)](https://user-images.githubusercontent.com/47964708/168089102-57fa1aef-8466-425b-ae68-3b0441fe0778.png)  

- ~delivery_area: `값 타입 컬렉션`을 사용해보기 위해 '다대다'로 연관관계를 맺지 않았습니다.~
- 다대다 연관관계를 사용하지 않은 근거가 필요하여 delivery_area -> excluded_area 변경 (2022.05.12)
