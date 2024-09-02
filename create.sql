create sequence restaurant_seq start with 1 increment by 50;
create table restaurant (rest_id integer not null, name varchar(255), primary key (rest_id));
