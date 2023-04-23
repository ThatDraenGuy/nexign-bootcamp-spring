create sequence billing_operations_seq
    increment by 50;

create sequence call_summaries_seq
    increment by 50;

create sequence call_types_seq
    increment by 50;

create sequence clients_seq
    increment by 50;

create sequence monetary_units_seq
    increment by 50;

create sequence payments_seq
    increment by 50;

create sequence reports_seq
    increment by 50;

create sequence tariffs_seq
    increment by 50;

create sequence users_seq
    increment by 50;

create table if not exists billing_operations
(
    id                  bigint       not null
    primary key,
    operation_date_time timestamp(6) not null,
    operation_number    integer      not null
    );

create table if not exists call_types
(
    id   bigint       not null
    primary key,
    code varchar(255) not null,
    name varchar(255) not null
    );

create table if not exists monetary_units
(
    id   bigint       not null
    primary key,
    code varchar(255) not null
    constraint uk_5ji32oub3dak26du93id8r0h8
    unique,
    name varchar(255) not null
    );

create table if not exists tariffs
(
    id   bigint       not null
    primary key,
    code varchar(255) not null
    constraint uk_59lm0iw7oyx5u1gxo0akv1q98
    unique,
    name varchar(255) not null
    constraint uk_lt51ssbtjwb2x833uph2ngfag
    unique
    );

create table if not exists clients
(
    id           bigint           not null
    primary key,
    balance      double precision not null,
    phone_number varchar(11)      not null
    constraint uk_bt1ji0od8t2mhp0thot6pod8u
    unique,
    tariff_id    bigint           not null
    constraint fkm1kg1pe0ij97a1c4r9hk0biw1
    references tariffs
    );

create table if not exists payments
(
    id               bigint  not null
    primary key,
    money            integer not null,
    client_id        bigint  not null
    constraint fk7q4i5uacsdt9cx0xx77nwmaso
    references clients,
    monetary_unit_id bigint  not null
    constraint fkco9d291q65ahy0su134a5inb4
    references monetary_units
);

create table if not exists reports
(
    id                   bigint           not null
    primary key,
    total_cost           double precision not null,
    total_minutes        integer          not null,
    billing_operation_id bigint           not null
    constraint fkemd3uq440gyqff4x9o5m4pj0c
    references billing_operations,
    client_id            bigint           not null
    constraint fkmiqk34gfam6emk63vq844fem2
    references clients,
    monetary_unit_id     bigint           not null
    constraint fk1obd4if3ieppuuxskssh0pnp9
    references monetary_units
);

create table if not exists call_summaries
(
    id           bigint           not null
    primary key,
    cost         double precision not null,
    duration     numeric(21)      not null,
    end_time     timestamp(6)     not null,
    start_time   timestamp(6)     not null,
    call_type_id bigint           not null
    constraint fko177fshny4qfvpde8rua02bds
    references call_types,
    report_id    bigint           not null
    constraint fklgliu7snsrt4fdh2dpcx7g17l
    references reports
    );

create table if not exists users
(
    id        bigint not null
    primary key,
    password  varchar(255),
    username  varchar(255),
    client_id bigint
    constraint uk_g9epudg12gt4bfg0g8l9evbf2
    unique
    constraint fkqvykjc6027qa8n5es37omu3xs
    references clients
    );

create table if not exists user_roles
(
    user_id bigint not null
    constraint fkhfh9dx7w3ubf1co1vdev94g3f
    references users,
    role    varchar(255)
    );

