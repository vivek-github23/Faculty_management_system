create table department
(
    capacity int          not null,
    id       int auto_increment
        primary key,
    name     varchar(100) not null
);

create table token_seq
(
    next_val bigint null
);

create table user
(
    id        int                                  not null
        primary key,
    email     varchar(255)                         null,
    firstname varchar(255)                         null,
    lastname  varchar(255)                         null,
    password  varchar(255)                         null,
    role      enum ('EMPLOYEE', 'STUDENT', 'USER') null
);

create table employee
(
    department_id   int          null,
    id              int auto_increment
        primary key,
    user_id         int          null,
    title           varchar(100) null,
    photograph_path varchar(255) null,
    constraint UK_mpps3d3r9pdvyjx3iqixi96fi
        unique (user_id),
    constraint FK6lk0xml9r7okjdq0onka4ytju
        foreign key (user_id) references user (id),
    constraint FKbejtwvg9bxus2mffsm3swj3u9
        foreign key (department_id) references department (id)
);

create table employee_salary
(
    amount       decimal(10, 2) not null,
    employee_id  int            null,
    id           int auto_increment
        primary key,
    payment_date date           not null,
    description  varchar(255)   null,
    salary_slip  varchar(255)   null,
    constraint FKo7mki93c83b1kx9olp1vmcwcq
        foreign key (employee_id) references employee (id)
);

create table token
(
    expired    bit             not null,
    id         int             not null
        primary key,
    revoked    bit             not null,
    user_id    int             null,
    token      varchar(255)    null,
    token_type enum ('BEARER') null,
    constraint UK_pddrhgwxnms2aceeku9s2ewy5
        unique (token),
    constraint FKe32ek7ixanakfqsdaokm4q9y2
        foreign key (user_id) references user (id)
);

create table user_seq
(
    next_val bigint null
);


