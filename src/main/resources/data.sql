/* exhibition */
insert into exhibition (id, title)
values (1, '일반');

insert into exhibition (id, title)
values (2, '기획전1');

insert into exhibition (id, title)
values (3, '기획전2');

/* category */
insert into category (id, title, exhibition_id, parent_id)
values (1, '상위_카테고리1', 1, null);

insert into category (id, title, exhibition_id, parent_id)
values (2, '하위_카테고리1_1', 1, 1), (3, '하위_카테고리1_2', 1, 1);

insert into category (id, title, exhibition_id, parent_id)
values (4, '상위_카테고리2', 1, null);

insert into category (id, title, exhibition_id, parent_id)
values (5, '하위_카테고리2_1', 1, 4), (6, '하위_카테고리2_2', 1, 4);

/* product */
insert into product (id, name, description, price, stock, created_at, modified_at)
values (1, '상품1', '첫 번째 상품입니다.', 1000, 10, now(), now());

insert into product (id, name, description, price, stock, created_at, modified_at)
values (2, '상품2', '두 번째 상품입니다.', 2000, 20, now(), now());

insert into product (id, name, description, price, stock, created_at, modified_at)
values (3, '상품3', '세 번째 상품입니다.', 3000, 30, now(), now());

insert into product (id, name, description, price, stock, created_at, modified_at)
values (4, '상품4', '네 번째 상품입니다.', 4000, 40, now(), now());

insert into product (id, name, description, price, stock, created_at, modified_at)
values (5, '상품5', '다섯 번째 상품입니다.', 5000, 50, now(), now());

insert into product (id, name, description, price, stock, created_at, modified_at)
values (6, '상품6', '여섯 번째 상품입니다.', 6000, 60, now(), now());

insert into product (id, name, description, price, stock, created_at, modified_at)
values (7, '상품7', '일곱 번째 상품입니다.', 7000, 70, now(), now());

insert into product (id, name, description, price, stock, created_at, modified_at)
values (8, '상품8', '여덟 번째 상품입니다.', 8000, 80, now(), now());

/* product image */
insert into product_image (id, image_url, image_seq, product_id)
values (1, 'image_url1_1', 1, 1), (2, 'image_url1_2', 2, 1), (3, 'image_url1_3', 3, 1);

insert into product_image (id, image_url, image_seq, product_id)
values (4, 'image_url2_1', 1, 1), (5, 'image_url2_2', 2, 2), (6, 'image_url2_3', 3, 2);

insert into product_image (id, image_url, image_seq, product_id)
values (7, 'image_url3_1', 1, 1), (8, 'image_url3_2', 2, 3), (9, 'image_url3_3', 3, 3);

insert into product_image (id, image_url, image_seq, product_id)
values (10, 'image_url4_1', 1, 1), (11, 'image_url4_2', 2, 4), (12, 'image_url4_3', 3, 4);

/* category_to_product */
insert into category_to_product (category_id, product_id)
values (2, 1), (2, 2);

insert into category_to_product (category_id, product_id)
values (3, 3), (3, 4);

insert into category_to_product (category_id, product_id)
values (5, 5), (5, 6);

insert into category_to_product (category_id, product_id)
values (6, 7), (6, 8);
