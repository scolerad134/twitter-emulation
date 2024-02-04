create sequence hibernate_sequence start 2 increment 1;

create table messages
(
    id       bigserial,
    filename varchar(255),
    tag      varchar(255),
    text     varchar(2048) not null,
    user_id  int8,
    primary key (id)
);

create table user_role
(
    user_id int8 not null,
    roles   varchar(255)
);

create table users
(
    id              bigserial,
    active          boolean      not null,
    password        varchar(255) not null,
    username        varchar(255) not null,
    primary key (id)
);

create table images
(
    id                 bigserial not null,
    bytes              bytea,
    content_type       varchar(255),
    name               varchar(255),
    original_file_name varchar(255),
    size               bigint,
    message_id         bigint,
    primary key (id)
);

alter table if exists messages
    add constraint message_user_fk
    foreign key (user_id) references users;

alter table if exists user_role
    add constraint user_role_user_fk
    foreign key (user_id) references users;

alter table if exists images
    add constraint image_message_fk
    foreign key (message_id) references messages;