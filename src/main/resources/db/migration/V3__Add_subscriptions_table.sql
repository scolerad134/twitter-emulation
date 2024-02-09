create table user_subsciptions (
    channel_id bigserial not null references users,
    subscriber_id bigserial not null references users,
    primary key (channel_id, subscriber_id)
);