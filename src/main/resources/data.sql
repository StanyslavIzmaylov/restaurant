INSERT INTO RESTAURANT (NAME) VALUES  ('Рыбный ресторан');
INSERT INTO RESTAURANT (NAME) VALUES  ('Кофейня');
INSERT INTO RESTAURANT (NAME) VALUES  ('Столовая');
INSERT INTO RESTAURANT (NAME) VALUES  ('Шашлычная');

INSERT INTO MENU (DATE, RESTAURANT_ID ) VALUES  ('2024-10-10', 100000);
INSERT INTO MENU (DATE, RESTAURANT_ID ) VALUES  ('2024-10-10', 100001);
INSERT INTO MENU (DATE, RESTAURANT_ID ) VALUES  ('2024-10-10', 100002);
INSERT INTO MENU (DATE, RESTAURANT_ID ) VALUES  ('2024-10-11', 100001);
INSERT INTO MENU (DATE, RESTAURANT_ID ) VALUES  ('2024-10-11', 100003);


INSERT INTO MEAL (NAME, PRICE, MENU_ID ) VALUES  ('Рыба',100,100004);
INSERT INTO MEAL (NAME, PRICE, MENU_ID ) VALUES  ('Устрицы',200,100004);
INSERT INTO MEAL (NAME, PRICE, MENU_ID ) VALUES  ('Мидии',300,100004);

INSERT INTO MEAL (NAME, PRICE, MENU_ID ) VALUES  ('Булочка',150,100005);
INSERT INTO MEAL (NAME, PRICE, MENU_ID ) VALUES  ('Кофе',50,100005);


INSERT INTO MEAL (NAME, PRICE, MENU_ID ) VALUES  ('Щи',150, 100006);
INSERT INTO MEAL (NAME, PRICE, MENU_ID ) VALUES  ('Хлеб',20, 100006);
INSERT INTO MEAL (NAME, PRICE, MENU_ID ) VALUES  ('Компот',70, 100006);
INSERT INTO MEAL (NAME, PRICE, MENU_ID ) VALUES  ('Пюре',120, 100006);
INSERT INTO MEAL (NAME, PRICE, MENU_ID ) VALUES  ('Мясо',220, 100006);

INSERT INTO MEAL (NAME, PRICE, MENU_ID ) VALUES  ('Шашлык',350, 100007);
INSERT INTO MEAL (NAME, PRICE, MENU_ID ) VALUES  ('Пиво',150, 100007);

INSERT INTO MEAL (NAME, PRICE, MENU_ID ) VALUES  ('Пюре',120, 100008);
INSERT INTO MEAL (NAME, PRICE, MENU_ID ) VALUES  ('Мясо',220, 100008);

INSERT INTO USERS (NAME, EMAIL, PASSWORD) VALUES  ('Admin','admin@ya.ru','admin');
INSERT INTO USERS (NAME, EMAIL, PASSWORD) VALUES  ('User','user@ya.ru','user');
INSERT INTO USERS (NAME, EMAIL, PASSWORD) VALUES  ('User1','user1@ya.ru','user');
INSERT INTO USERS (NAME, EMAIL, PASSWORD) VALUES  ('User2','user2@ya.ru','user');
INSERT INTO USERS (NAME, EMAIL, PASSWORD) VALUES  ('User3','user3@ya.ru','user');
INSERT INTO USERS (NAME, EMAIL, PASSWORD) VALUES  ('User4','user4@ya.ru','user');

INSERT INTO user_role (role, user_id) VALUES ('ADMIN', 100023);
INSERT INTO user_role (role, user_id) VALUES ('USER', 100024);
INSERT INTO user_role (role, user_id) VALUES ('USER', 100025);
INSERT INTO user_role (role, user_id) VALUES ('USER', 100026);
INSERT INTO user_role (role, user_id) VALUES ('USER', 100027);
INSERT INTO user_role (role, user_id) VALUES ('USER', 100028);

INSERT INTO votes (restaurant_id,VOTES_DATE,VOTE_TIME, user_id) VALUES (100000,'2024-10-10','10:00:00', 100024);
INSERT INTO votes (restaurant_id,VOTES_DATE,VOTE_TIME, user_id) VALUES (100000,'2024-10-10','09:00:00', 100025);