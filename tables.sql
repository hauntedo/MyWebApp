create table Users(

    id bigserial primary key,
    firstName varchar(20) NOT NULL,
    lastName varchar(20) NOT NULL,
    email varchar(50) NOT NULL,
    password varchar(20) NOT NULL,
    token varchar

);

create table Tasks(

    id bigserial primary key,
    title varchar(20) not null,
    description text not null,
    task_state varchar not null,
    employer_id bigint not null,
    foreign key (employer_id) references Users(id)
);

create table Relation(

    id bigserial primary key,
    employee_id bigint,
    employer_id bigint,
    foreign key (employee_id) references Users(id),
    foreign key (employer_id) references Users(id)
);

create table Jobs(
    id bigserial primary key,
    title varchar(20),
    description text,
    employer_id bigint,
    foreign key (employee_id) references Users(id),
    active boolean
);