insert into customer values(1, 'rmm-user', '$2a$10$Btyu5ahVCeoB8dk.5uD5Du38rEifCjDQHIegXBIg0IyIimKBRPgca');


insert into service values(1, 'Antivirus');
insert into service values(2, 'Cloudberry');
insert into service values(3, 'PSA');
insert into service values(4, 'TeamViewer');


insert into device_type values(1, 'Windows WorkStation', 4);
insert into device_type values(2, 'Windows Server', 4);
insert into device_type values(3, 'Mac', 4);


insert into service_price values(1, 1, 1, 5);
insert into service_price values(2, 1, 2, 3);
insert into service_price values(3, 1, 3, 2);
insert into service_price values(4, 1, 4, 1);
insert into service_price values(5, 2, 1, 5);
insert into service_price values(6, 2, 2, 3);
insert into service_price values(7, 2, 3, 2);
insert into service_price values(8, 2, 4, 1);
insert into service_price values(9, 3, 1, 7);
insert into service_price values(10, 3, 2, 3);
insert into service_price values(11, 3, 3, 2);
insert into service_price values(12, 3, 4, 1);


insert into device values(1, 'User-PC', 1, 1);
insert into device values(2, 'User-Server', 2, 1);
insert into device values(3, 'User-iMac', 3, 1);
insert into device values(4, 'User-Mac', 3, 1);
insert into device values(5, 'User-MacBook', 3, 1);


insert into customer_service values(1, 1, 1);
insert into customer_service values(2, 1, 2);
insert into customer_service values(3, 1, 4);