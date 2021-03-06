create table actor(
user_name varchar2(100) primary key,
password varchar2(200),
role_name varchar2(100)
);

create table Activity_category(
id varchar2(100) primary key,
activity_name varchar2(200)
);

create table brand(
id varchar2(100) primary key,
name varchar2(100) Unique,
address varchar2(200),
join_date date DEFAULT CURRENT_DATE,
user_name varchar2(100) references actor(user_name) on DELETE CASCADE
);

create table Loyalty_program(
 id varchar2(100) primary key,
 program_name varchar2(200) not null,
brand_id REFERENCES brand(id) on DELETE CASCADE,
tier_type varchar2(100) not null,
state varchar2(10) DEFAULT 'INACTIVE',
CONSTRAINT uniq_brand UNIQUE(brand_id)
);

create table re_rule(
re_rule_code varchar2(100),
activity_category_code references Activity_category(id) on DELETE CASCADE,
nums_points number,
version number,
status VARCHAR2(1) DEFAULT 'E',
lp_code references Loyalty_program(id) on DELETE CASCADE,
CONSTRAINT pk_re_rule PRIMARY KEY (re_rule_code, version),
CONSTRAINT uniq_re_rule UNIQUE(activity_category_code, version, lp_code)
);

create table reward_category(
id varchar2(100) primary key,
reward_name varchar2(100) not null
);

create table rr_rule(
rr_rule_code varchar2(100),
reward varchar2(200) REFERENCES reward_category(id) on DELETE CASCADE,
num_points number,
version number,
status VARCHAR2(1) DEFAULT 'E',
lp_code references Loyalty_program(id) on DELETE CASCADE,
CONSTRAINT pk_rr_rule PRIMARY KEY (rr_rule_code, version),
CONSTRAINT uniq_rr_rule UNIQUE(reward, version, lp_code)
);

create table activities_for_loyalty_program(
    activity_lp_map_id varchar2(50) primary key,
    loyalty_program_code REFERENCES Loyalty_program(id) on DELETE CASCADE,
    activity_category_code references Activity_category(id) on DELETE CASCADE,
    UNIQUE (loyalty_program_code, activity_category_code)
);

create table customer(
id varchar2(100) primary key,
name varchar2(100) not NULL,
phone number(10),
address varchar2(200),
user_name varchar2(100) references actor(user_name) on DELETE CASCADE
);


create table customer_lp_enroll(
customer_id REFERENCES customer(id) on DELETE CASCADE,
loyalty_program_code references Loyalty_program(id) on delete cascade,
UNIQUE(customer_id,loyalty_program_code)
);

create table tier(
id varchar2(100) primary key,
name varchar2(100) not null,
points number not null,
multiplier number not null,
lp_program_id REFERENCES loyalty_program(id) on DELETE CASCADE,
check(points>=0),
check(multiplier>=0)
);

create table wallet(
id varchar2(100) primary key,
points number DEFAULT 0,
customer_id REFERENCES customer(id) on DELETE CASCADE,
loyalty_program_code REFERENCES Loyalty_program(id) on DELETE CASCADE,
tier_id references tier(id) on DELETE CASCADE,
check(points>=0)
);


create table rewards_for_loyalty_program(
reward_lp_map_id varchar2(50) primary key,
loyalty_program_code REFERENCES Loyalty_program(id) on DELETE CASCADE,
reward_category_code references reward_category(id) on DELETE CASCADE,
reward_count number,
reward_value varchar2(50),
UNIQUE (reward_category_code,loyalty_program_code),
check(reward_count>=0),
check(reward_value>=0)
);


create table customer_redeem_activity(
id varchar2(100) primary key,
customer_id references customer(id) on delete CASCADE,
activity_date date DEFAULT CURRENT_DATE,
redeem_lp_map_id references rewards_for_loyalty_program(reward_lp_map_id) on DELETE CASCADE,
points number(10),
CHECK(points>=0)
);

create table customer_activity(
id varchar2(100) primary key,
customer_id references customer(id) on delete CASCADE,
activity_date date DEFAULT CURRENT_DATE,
activity_lp_map_id references activities_for_loyalty_program(activity_lp_map_id),
customer_redeem_activity_id references customer_redeem_activity(id) on DELETE CASCADE,
points number(10),
CHECK(points>=0)
);

create or replace
procedure update_customer_tier(
    cId customer.id%type, lpId loyalty_program.id%type)
is
tierId varchar2(100);
begin
    update wallet set tier_id =
                          (select id
                           from tier t
                           where lp_program_id = lpId
                             and points = (select max(points) from tier where lp_program_id = lpId
                                                                          and points <= (select points
                                                                                         from wallet where customer_id = cId and loyalty_program_code = lpId)))
    where customer_id = cId and loyalty_program_code = lpId;
end;
/
create or replace trigger update_reward_count_and_wallet
    after insert on customer_redeem_activity
    for each row
declare
    lpCode varchar2(100);
begin
    select loyalty_program_code into lpCode
    from rewards_for_loyalty_program
    where reward_lp_map_id = :new.redeem_lp_map_id;

    update rewards_for_loyalty_program set reward_count = reward_count - 1 where reward_lp_map_id = :new.redeem_lp_map_id;
    update wallet
    set points = points - :new.points
    where customer_id = :new.customer_id and
            loyalty_program_code = lpCode;
    update_customer_tier(:new.customer_id, lpCode);
end;
/
create or replace trigger calc_points
    before insert on customer_activity
    for each row
declare
    pts number(10);
    actCode varchar2(100);
    lpCode varchar2(100);
    mlt number(10);
begin
    select activity_category_code into actCode
    from activities_for_loyalty_program
    where activities_for_loyalty_program.activity_lp_map_id = :new.activity_lp_map_id;
    select loyalty_program_code into lpCode
    from activities_for_loyalty_program
    where activities_for_loyalty_program.activity_lp_map_id = :new.activity_lp_map_id;
    if :new.points is null then
        select nums_points into pts
        from re_rule
        where activity_category_code = actCode  and lp_code = lpCode
          and version = (select max(version)
                         from re_rule
                         where activity_category_code = actCode and lp_code = lpCode);
        select multiplier into mlt
        from tier
        where id = (select tier_id from wallet where customer_id = :new.customer_id and loyalty_program_code = lpCode);
        :new.points := pts * mlt;
        update wallet set points = points + pts * mlt where customer_id = :new.customer_id and loyalty_program_code = lpCode;
        update_customer_tier(:new.customer_id, lpCode);
    end if;
exception
    when no_data_found then
        mlt := 1;
        :new.points := pts * mlt;
        update wallet set points = points + pts * mlt where customer_id = :new.customer_id and loyalty_program_code = lpCode;
        update_customer_tier(:new.customer_id, lpCode);
end;
/
create or replace trigger brand_insert_trigger
    after insert on brand
    for each row
begin
    insert into actor values(:new.user_name, 'abcd1234', 'brand');
end;
/
create or replace trigger customer_insert_trigger
    after insert on customer
    for each row
begin
    insert into actor values(:new.user_name, 'abcd1234', 'customer');
end;
/
create or replace trigger customer_wallet_trigger
    after insert on customer_lp_enroll
    for each row
declare
    tierType varchar2(100);
begin
    insert into wallet (id,customer_id,loyalty_program_code) values(sys_guid(),:new.customer_id, :new.loyalty_program_code);
    update_customer_tier(:new.customer_id, :new.loyalty_program_code);
end;
/