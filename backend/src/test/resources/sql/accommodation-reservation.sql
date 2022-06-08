DELETE FROM reservation WHERE 1=1;
DELETE FROM accommodation_provide WHERE 1=1;
DELETE FROM accommodation WHERE 1=1;
DELETE FROM address WHERE 1=1;
DELETE FROM provide WHERE 1=1;
DELETE FROM member WHERE 1=1;

INSERT INTO address (`id`, `city`, `sigungu`,`street`) VALUES (1, '서울특별시', '강남구', '테헤란로13길 65');

INSERT INTO accommodation (`id`, `title`, `price`, `image_url`, `point`, `address_id`) VALUES (1, '강남스테이힐(Gangnam Stay Hill)', 142159, 'http://tong.visitkorea.or.kr/cms/resource/28/2573328_image2_1.jpg', null, 1);

INSERT INTO provide (id, name) VALUES (1, '최대 인원');

-- 1번 숙소 최대 인원 5명
INSERT INTO accommodation_provide (accommodation_id, provide_id, count) VALUES (1,1,5);

INSERT INTO member (`id`, `email`, `name`) VALUES (1, 'test@email.com', '홍길동');

-- 1번 숙소에, 1번 회원이, '2022-06-01' ~ '2022-06-03' 예약
INSERT INTO reservation (`id`, `visitors`, `start_date`, `end_date`, `accommodation_id`, `member_id`) VALUES (1, 3, '2022-06-01', '2022-06-03', 1, 1);