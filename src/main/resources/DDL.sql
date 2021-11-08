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
activity_category_code references Activity_category(id) on DELETE CASCADE,
nums_points number,
version number,
status VARCHAR2(1) DEFAULT 'E' 
);

create table reward_category(
id varchar2(100) primary key,
reward_name varchar2(100) not null
-- brand_id REFERENCES brand(id) on DELETE CASCADE, // Removed
-- lp_program_id REFERENCES loyalty_program(id) on DELETE CASCADE // Removed
);

create table rr_rule(
rr_rule_code varchar2(100) primary key,
<<<<<<< HEAD
reward varchar2(200) references reward_category(id),
=======
reward varchar2(200) REFERENCES reward_category(id) on DELETE CASCADE,
>>>>>>> origin/master
num_points number,
version number,
status VARCHAR2(1) DEFAULT 'E'
);

create table brand(
id varchar2(100) primary key,
name varchar2(100) Unique,
address varchar2(200),
join_date date DEFAULT CURRENT_DATE,
user_name varchar2(100) references actor(user_name)
);



create table Loyalty_program(
id varchar2(100) primary key,
program_name varchar2(200) not null,
-- activity_code references Activity_category(id),
brand_id REFERENCES brand(id) on DELETE CASCADE,
tier_type varchar2(100) not null,
state varchar2(10) DEFAULT 'INACTIVE'
-- re_rule_code references re_rule(re_rule_code),
-- rr_rule_code references rr_rule(rr_rule_code)
);

create table re_rule_for_lp(
    lp_code references Loyalty_program(id) on DELETE CASCADE,
    re_rule_code references re_rule(re_rule_code) on DELETE CASCADE,
    CONSTRAINT pk_re_lp_map PRIMARY KEY (lp_code, re_rule_code)
);

create table rr_rule_for_lp(
    lp_code references Loyalty_program(id) on DELETE CASCADE,
    rr_rule_code references rr_rule(rr_rule_code) on DELETE CASCADE,
    CONSTRAINT pk_rr_lp_map PRIMARY KEY (lp_code, rr_rule_code)
);

create table activities_for_loyalty_program(
    activity_lp_map_id varchar2(50) primary key,
    loyalty_program_code REFERENCES Loyalty_program(id) on DELETE CASCADE,
    activity_category_code references Activity_category(id) on DELETE CASCADE
);

create table customer(
id varchar2(100) primary key,
name varchar2(100) not NULL,
phone number(10),
address varchar2(200),
user_name varchar2(100) references actor(user_name)
);


create table customer_lp_enroll(
customer_id REFERENCES customer(id),
loyalty_program_code references Loyalty_program(id) on delete cascade,
UNIQUE(customer_id,loyalty_program_code)
);




create table wallet(
id varchar2(100) primary key,
points number DEFAULT 0,
customer_id REFERENCES customer(id) on DELETE CASCADE,
loyalty_program_code REFERENCES Loyalty_program(id) on DELETE CASCADE,
check(points>=0)
);


create table rewards_for_loyalty_program(
    reward_lp_map_id varchar2(50) primary key,
    loyalty_program_code REFERENCES Loyalty_program(id) on DELETE CASCADE,
    reward_category_code references reward_category(id) on DELETE CASCADE,
    reward_count number,
    reward_value varchar2(50)
);


create table customer_redeem_activity(
id varchar2(100) primary key,
customer_id references customer(id) on delete CASCADE,
activity_date date DEFAULT CURRENT_DATE,
redeem_lp_map_id references rewards_for_loyalty_program(reward_lp_map_id),
points number(10)
);

create table customer_activity(
id varchar2(100) primary key,
customer_id references customer(id) on delete CASCADE,
activity_date date DEFAULT CURRENT_DATE,
activity_lp_map_id references activities_for_loyalty_program(activity_lp_map_id),
customer_redeem_activity_id references customer_redeem_activity(id),
points number(10) 
);

create table tier(
id varchar2(100) primary key,
name varchar2(100) not null,
points number not null,
multiplier number not null,
lp_program_id REFERENCES loyalty_program(id) on DELETE CASCADE
);

create or replace trigger update_reward_count_and_wallet
after insert on customer_redeem_activity
for each row
begin
    update rewards_for_loyalty_program set reward_count = reward_count - 1 where reward_lp_map_id = :new.redeem_lp_map_id;
    update wallet set points = points - :new.points;
end;

create or replace trigger calc_points
after insert on customer_activity
for each row
begin
    if :new.points is null then
        update customer_activity set points =
            (select nums_points
            from re_rile where activity_category_code =
                               (select activity_category_code
                               from activities_for_loyalty_program
                               where activities_for_loyalty_program.activity_lp_map_id = :new.activity_lp_map_id) and
                                version = (select max(version)
                                            from re_rule
                                            where re_rule.activity_category_code =
                                                  (select activity_category_code
                                                   from activities_for_loyalty_program
                                                   where activities_for_loyalty_program.activity_lp_map_id = :new.activity_lp_map_id)));
    end if;
end;
create or replace trigger brand_insert_trigger
after insert on brand
for each row
begin
insert into actor values(:new.user_name, 'abcd1234', 'brand');
end;

create or replace trigger customer_insert_trigger
after insert on customer
for each row
begin
insert into actor values(:new.user_name, 'abcd1234', 'customer');
end;

create or replace trigger customer_wallet_trigger
after insert on customer_lp_enroll
for each row
begin
    insert into wallet (id,customer_id,loyalty_program_code) values(sys_guid(),:new.customer_id, :new.loyalty_program_code);
end;