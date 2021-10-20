insert into permission(id, name, operation, description) values (1, '/groups', 'GET', 'Search for all groups');

insert into user_group(id, name) values (1, 'user');
insert into user_group(id, name) values (2, 'admin');

insert into user_group_permission(user_group_id, permission_id) values (1, 1);

insert into guest(id, user_group_id, name) values(1, 1, 'Roger Federer');
insert into guest(id, user_group_id, name) values(2, 1, 'Rafael Nadal');
insert into guest(id, user_group_id, name) values(3, 1, 'Jorge');

insert into tennis_court(id, name) values(null, 'Roland Garros - Court Philippe-Chatrier');

insert into schedule (id, start_date_time, end_date_time, tennis_court_id) values (null, '2021-11-20T20:00:00', '2021-11-20T21:00:00', 1);
insert into schedule (id, start_date_time, end_date_time, tennis_court_id) values (null, '2021-11-21T20:00:00', '2021-11-21T21:00:00', 1);
insert into schedule (id, start_date_time, end_date_time, tennis_court_id) values (null, '2021-11-22T20:00:00', '2021-11-22T21:00:00', 1);
insert into schedule (id, start_date_time, end_date_time, tennis_court_id) values (null, '2021-11-23T20:00:00', '2021-11-23T21:00:00', 1);
insert into schedule (id, start_date_time, end_date_time, tennis_court_id) values (null, '2021-11-24T20:00:00', '2021-11-24T21:00:00', 1);
insert into schedule (id, start_date_time, end_date_time, tennis_court_id) values (null, '2021-11-25T20:00:00', '2021-11-25T21:00:00', 1);

insert into reservation (id, guest_id, schedule_id, value, reservation_status, refund_value) values (null, 1, 1, 10.0, 'READY_TO_PLAY', 10.0);
insert into reservation (id, guest_id, schedule_id, value, reservation_status, refund_value) values (null, 1, 2, 10.0, 'CANCELLED', 10.0);
insert into reservation (id, guest_id, schedule_id, value, reservation_status, refund_value) values (null, 1, 3, 10.0, 'READY_TO_PLAY', 10.0);
insert into reservation (id, guest_id, schedule_id, value, reservation_status, refund_value) values (null, 1, 4, 10.0, 'RESCHEDULED', 10.0);
insert into reservation (id, guest_id, schedule_id, value, reservation_status, refund_value) values (null, 1, 6, 10.0, 'READY_TO_PLAY', 10.0);

