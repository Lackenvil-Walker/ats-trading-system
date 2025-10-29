create table users (
                       id uuid primary key,
                       username varchar(128) unique not null,
                       password varchar(255) not null,
                       role varchar(32) not null
);
insert into users(id,username,password,role)
values (gen_random_uuid(),'trader', '{bcrypt}$2a$10$E4q1if9wzj0...hashed...', 'TRADER');
