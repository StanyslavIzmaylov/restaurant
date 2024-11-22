DELETE FROM vote;
DELETE FROM user_role;
DELETE FROM USERS;
DELETE FROM MENU_ITEM;
DELETE FROM MENU;
DELETE FROM RESTAURANT;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO RESTAURANT (NAME) VALUES  ('Рыбный ресторан');
INSERT INTO RESTAURANT (NAME) VALUES  ('Кофейня');
INSERT INTO RESTAURANT (NAME) VALUES  ('Столовая');
INSERT INTO RESTAURANT (NAME) VALUES  ('Шашлычная');

INSERT INTO MENU (MENU_DATE, RESTAURANT_ID ) VALUES  (NOW(), 100000);
INSERT INTO MENU (MENU_DATE, RESTAURANT_ID ) VALUES  (NOW(), 100001);
INSERT INTO MENU (MENU_DATE, RESTAURANT_ID ) VALUES  (NOW(), 100002);
INSERT INTO MENU (MENU_DATE, RESTAURANT_ID ) VALUES  (NOW() + 1, 100001);
INSERT INTO MENU (MENU_DATE, RESTAURANT_ID ) VALUES  (NOW(), 100003);


INSERT INTO MENU_ITEM (NAME, PRICE, MENU_ID ) VALUES  ('Рыба',100,100004);
INSERT INTO MENU_ITEM (NAME, PRICE, MENU_ID ) VALUES  ('Устрицы',200,100004);
INSERT INTO MENU_ITEM (NAME, PRICE, MENU_ID ) VALUES  ('Мидии',300,100004);

INSERT INTO MENU_ITEM (NAME, PRICE, MENU_ID ) VALUES  ('Булочка',150,100005);
INSERT INTO MENU_ITEM (NAME, PRICE, MENU_ID ) VALUES  ('Кофе',50,100005);


INSERT INTO MENU_ITEM (NAME, PRICE, MENU_ID ) VALUES  ('Щи',150, 100006);
INSERT INTO MENU_ITEM (NAME, PRICE, MENU_ID ) VALUES  ('Хлеб',20, 100006);
INSERT INTO MENU_ITEM (NAME, PRICE, MENU_ID ) VALUES  ('Компот',70, 100006);
INSERT INTO MENU_ITEM (NAME, PRICE, MENU_ID ) VALUES  ('Пюре',120, 100006);
INSERT INTO MENU_ITEM (NAME, PRICE, MENU_ID ) VALUES  ('Мясо',220, 100006);

INSERT INTO MENU_ITEM (NAME, PRICE, MENU_ID ) VALUES  ('Шашлык',350, 100007);
INSERT INTO MENU_ITEM (NAME, PRICE, MENU_ID ) VALUES  ('Пиво',150, 100007);

INSERT INTO MENU_ITEM (NAME, PRICE, MENU_ID ) VALUES  ('Пюре',120, 100008);
INSERT INTO MENU_ITEM (NAME, PRICE, MENU_ID ) VALUES  ('Мясо',220, 100008);

INSERT INTO USERS (NAME, EMAIL, PASSWORD) VALUES  ('Admin','admin@ya.ru','$2y$10$hh0LDfrFtyr96AW09/ewwep4wkP6zEzdVRDflhbyy/BvWpcxmmf.i');
INSERT INTO USERS (NAME, EMAIL, PASSWORD) VALUES  ('User','user@ya.ru','$2y$10$8aOL1toxjIrNzFkI7Vqvq.anIQtgsH/Ki8iqV3Mr4Q631m24bMBwq');
INSERT INTO USERS (NAME, EMAIL, PASSWORD) VALUES  ('User1','user1@ya.ru','$2y$10$8aOL1toxjIrNzFkI7Vqvq.anIQtgsH/Ki8iqV3Mr4Q631m24bMBwq');
INSERT INTO USERS (NAME, EMAIL, PASSWORD) VALUES  ('User2','user2@ya.ru','$2y$10$8aOL1toxjIrNzFkI7Vqvq.anIQtgsH/Ki8iqV3Mr4Q631m24bMBwq');
INSERT INTO USERS (NAME, EMAIL, PASSWORD) VALUES  ('User3','user3@ya.ru','$2y$10$8aOL1toxjIrNzFkI7Vqvq.anIQtgsH/Ki8iqV3Mr4Q631m24bMBwq');
INSERT INTO USERS (NAME, EMAIL, PASSWORD) VALUES  ('User4','user4@ya.ru','$2y$10$8aOL1toxjIrNzFkI7Vqvq.anIQtgsH/Ki8iqV3Mr4Q631m24bMBwq');

INSERT INTO user_role (role, user_id) VALUES ('ADMIN', 100023);
INSERT INTO user_role (role, user_id) VALUES ('USER', 100024);
INSERT INTO user_role (role, user_id) VALUES ('USER', 100025);
INSERT INTO user_role (role, user_id) VALUES ('USER', 100026);
INSERT INTO user_role (role, user_id) VALUES ('USER', 100027);
INSERT INTO user_role (role, user_id) VALUES ('USER', 100028);

INSERT INTO vote (restaurant_id,VOTE_DATE,VOTE_TIME, user_id) VALUES (100000,NOW(),'10:00', 100024);
INSERT INTO vote (restaurant_id,VOTE_DATE,VOTE_TIME, user_id) VALUES (100000,NOW(),'11:00', 100025);
INSERT INTO vote (restaurant_id,VOTE_DATE,VOTE_TIME, user_id) VALUES (100001,NOW() - 1,'12:00', 100024);