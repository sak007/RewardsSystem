1. List all customers that are not part of Brand02’s program.

select c.id,c.name
from customer c join customer_lp_enroll cle on c.id=cle.customer_id
join loyalty_program lp on cle.loyalty_program_code=lp.id
join brand b on lp.brand_id=b.id
minus
select c.id,c.name
from customer c join customer_lp_enroll cle on c.id=cle.customer_id
join loyalty_program lp on cle.loyalty_program_code=lp.id
join brand b on lp.brand_id=b.id
where b.name='Brand02';


2. List customers that have joined a loyalty program but have not participated in any activity
in that program (list the customerid and the loyalty program id).

select c.id,c.name
from customer c join customer_lp_enroll cle on c.id=cle.customer_id
minus
select c.id,c.name
from customer c join customer_lp_enroll cle on c.id=cle.customer_id
join customer_activity ca on c.id=ca.customer_id
group by c.id,c.name
having count(*)>0;



3. List the rewards that are part of Brand01 loyalty program.

select rc.reward_name
from reward_category rc join rewards_for_loyalty_program rlp on rc.id=rlp.reward_category_code
join loyalty_program lp on lp.id=rlp.loyalty_program_code
join brand b on lp.brand_id=b.id
where b.name='Brand01';




4. List all the loyalty programs that include “refer a friend” as an activity in at least one of
their reward rules.



5. For Brand01, list for each activity type in their loyalty program, the number instances that
have occurred.


6. List customers of Brand01 that have redeemed at least twice.

customer,loyalty_program,brand,customer_redeem_activity,customer_lp_enroll

select c.id,c.name
from customer c join customer_lp_enroll cle on c.id=cle.customer_id
join loyalty_program lp on cle.loyalty_program_code=lp.id
join brand b on b.id=lp.brand_id
where b.name='Brand01'
group by c.id,c.name
having 2<= (
select count(cra.customer_id)
from customer_redeem_activity cra
where cra.customer_id=c.id);



7. All brands where total number of points redeemed overall is less than 500 points


select distinct b.name
from customer_redeem_activity cre join rewards_for_loyalty_program rlp on cre.redeem_lp_map_id=rlp.reward_lp_map_id
join loyalty_program lp on lp.id=rlp.loyalty_program_code
join brand b on b.id=lp.brand_id
where cre.points<=500;

8. For Customer C0003, and Brand02, number of activities they have done in the period of
08/1/2021 and 9/30/2021