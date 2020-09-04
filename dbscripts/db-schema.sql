create table if not exists customer (
	id SERIAL primary key,
	username VARCHAR(120) unique not null,
	password VARCHAR(120) not null
);

create table if not exists service (
	id SERIAL primary key,
	service_name VARCHAR(100) not null
);

create table if not exists device_type (
	id SERIAL primary key,
	type_name VARCHAR(100) not null,
    device_price numeric(19,2) not null
);

create table if not exists service_price (
	id SERIAL primary key,
	device_type_id integer not null,
	service_id integer not null,
	price numeric(19,2) not null,
	foreign key (device_type_id) references device_type(id),
	foreign key (service_id) references service(id)
);

create table if not exists device (
	id SERIAL not null,
	system_name VARCHAR(200) not null,
	device_type_id integer not null,
	customer_id integer not null,
	foreign key (device_type_id) references device_type(id),
	foreign key (customer_id) references customer(id)
);

create table if not exists customer_service (
	id SERIAL not null,
	customer_id integer not null,
	service_id integer not null,
	foreign key (customer_id) references customer(id),
	foreign key (service_id) references service(id)
);