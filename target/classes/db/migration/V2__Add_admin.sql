insert into users (username, password, active)
values ('admin', '$2a$10$J004CXi67KhElS3Pvod9tew0ivJwoUONmEgWX98/iESyb9CyNxEK2', true);

insert into user_role (user_id, roles)
values (1, 'USER'), (1, 'ADMIN');