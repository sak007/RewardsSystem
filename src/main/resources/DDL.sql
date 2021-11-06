create table actor(
user_name varchar2(100) primary key,
password varchar2(200),
role_name varchar2(100)
);

create table Activity_category(
id varchar2(100) primary key,
activity_name varchar2(200)
);

create table re_rule(
re_rule_code varchar2(100) primary key,
activity_category_code references Activity_category(id),
nums_points number,
version number 
);

create table rr_rule(
rr_rule_code varchar2(100) primary key,
reward varchar2(200),
num_points number,
instances number not null,
version number,
status VARCHAR2(1) DEFAULT 'E',
check(INSTANCES>=0)
);

create table brand(
id varchar2(100) primary key,
name varchar2(100) Unique,
address varchar2(200),
join_date date DEFAULT CURRENT_DATE
);

create table Loyalty_program(
id varchar2(100) primary key,
program_name varchar2(200) not null,
-- activity_code references Activity_category(id),
brand_id REFERENCES brand(id) on DELETE CASCADE,
tier_type varchar2(100) not null,
state varchar2(10) DEFAULT 'INACTIVE',
re_rule_code references re_rule(re_rule_code),
rr_rule_code references rr_rule(rr_rule_code)
);

create table activities_for_loyalty_program(
    id varchar2(100) primary key,
    loyalty_program_code REFERENCES Loyalty_program(id),
    activity_category_code references Activity_category(id)
);

create table customer(
id varchar2(100) primary key,
name varchar2(100) not NULL,
phone number(10),
lp_program_id REFERENCES loyalty_program(id),
address varchar2(200)
);

create table customer_lp_enroll(
customer_id REFERENCES customer(id),
loyalty_program_code references Loyalty_program(id),
UNIQUE(customer_id,loyalty_program_code)
);

create table wallet(
id varchar2(100) primary key,
date_activity date,
points number DEFAULT 0,
re_rule_code references re_rule(re_rule_code),
activity varchar2(200),
loyalty_program_code references Loyalty_program(id),
customer_id REFERENCES customer(id) on DELETE CASCADE,
check(points>=0)
);


create table reward_category(
id varchar2(100) primary key,
reward_name varchar2(100) not null
-- brand_id REFERENCES brand(id) on DELETE CASCADE, // Removed
-- lp_program_id REFERENCES loyalty_program(id) on DELETE CASCADE // Removed
);

create table rewards_for_loyalty_program(
    id varchar2(100) primary key,
    loyalty_program_code REFERENCES Loyalty_program(id) on DELETE CASCADE,
    reward_category_code references reward_category(id) on DELETE CASCADE
);

create table reward_instance(
reward_id varchar2(100) primary key,
brand_id references brand(id) on DELETE CASCADE,
value number,
expiry_date date DEFAULT CURRENT_DATE + 365
);


create table customer_activity(
id varchar2(100) primary key,
customer_id references customer(id) on delete CASCADE,
activity_date date DEFAULT CURRENT_DATE,
activity_category_code references Activity_category(id)
);

create table tier(
id varchar2(100) primary key,
name varchar2(100) not null,
points number not null,
multiplier number not null,
lp_program_id REFERENCES loyalty_program(id) on DELETE CASCADE
);




