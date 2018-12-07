Create Table pharmacy(
	id long primary key,
	name varchar(300) not null,
	address varchar(2000) not null,
	city varchar(65) not null,
	state varchar(65) not null,
	zip int not null,
	latitude double not null ,
	longitude double not null
);