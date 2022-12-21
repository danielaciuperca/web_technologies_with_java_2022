create database if not exists course10;

create table if not exists bankaccounts(
    id int not null auto_increment primary key,
    account_number varchar(100) not null,
    balance double not null,
    type varchar(100) not null,
    card_number varchar(16)
);