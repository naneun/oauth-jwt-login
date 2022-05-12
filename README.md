# 2-weeks-of-individual-study

# JPA, Spring Data JPA, QueryDSL 을 학습하여 SIDE DISH 에 적용해보기

- 5/9 기본편, 활용 1 학습 및 테이블 설계
- 5/10 활용 1, 활용 2 학습 및 테이블 수정 및 엔티티 설계
- 5/11 활용 2, Spring Data JPA, JWT 학습 및 엔티티, 레포지토리 설계
- 5/12 Spring Data JPA, QueryDSL 학습 및 엔티티, 레포지토리 설계

# 테이블 설계도
![Untitled Diagram drawio (13)](https://user-images.githubusercontent.com/47964708/168067304-687b2624-45a3-4f43-9dcd-2e15c79a3453.png)  

- ~delivery_area: `값 타입 컬렉션`을 사용해보기 위해 '다대다'로 연관관계를 맺지 않았습니다.~
- 다대다 연관관계를 사용하지 않은 근거가 필요하여 delivery_area -> excluded_area 변경 (2022.05.12)
