use course8;

create table if not exists bankaccounts(
    id int not null auto_increment primary key,
    accountNumber varchar(100) not null,
    balance double not null,
    type varchar(100) not null,
    cardNumber varchar(16)
);