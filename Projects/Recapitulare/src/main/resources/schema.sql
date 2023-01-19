use ridesharing;

create table if not exists drivers (
    id int not null primary key auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    city varchar(100) not null
);