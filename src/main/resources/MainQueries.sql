1. List all customers that are not part of Brand02’s program.

select c.id,c.name
from customer c 
minus
select c.id,c.name
from customer c join customer_lp_enroll cle on c.id=cle.customer_id
join loyalty_program lp on cle.loyalty_program_code=lp.id
join brand b on lp.brand_id=b.id
where b.id='Brand02';


2. List customers that have joined a loyalty program but have not participated in any activity
in that program (list the customerid and the loyalty program id).

select c.id,cle.loyalty_program_code
from customer c join customer_lp_enroll cle on c.id=cle.customer_id
minus
select c.id,loyalty_program_code
from customer c join customer_lp_enroll cle on c.id=cle.customer_id
join customer_activity ca on c.id=ca.customer_id
group by c.id,loyalty_program_code
having count(*)>0;



3. List the rewards that are part of Brand01 loyalty program.

select rc.reward_name
from reward_category rc join rewards_for_loyalty_program rlp on rc.id=rlp.reward_category_code
join loyalty_program lp on lp.id=rlp.loyalty_program_code
join brand b on lp.brand_id=b.id
where b.id='Brand01';




4. List all the loyalty programs that include “refer a friend” as an activity in at least one of
their reward rules.


select DISTINCT lp.program_name
from loyalty_program lp join re_rule_for_lp relp on lp.id=relp.lp_code
join re_rule re on re.re_rule_code=relp.re_rule_code
join activity_category ac on ac.id=re.activity_category_code
where ac.activity_name='Refer a friend';



5. For Brand01, list for each activity type in their loyalty program, the number instances that
have occurred.


select ac.activity_name,count(*)
from brand b join loyalty_program lp on lp.brand_id=b.id
join customer_lp_enroll cle on cle.loyalty_program_code=lp.id
join customer_activity ca on ca.customer_id=cle.customer_id
join activities_for_loyalty_program alp on alp.activity_lp_map_id=ca.activity_lp_map_id
join activity_category ac on alp.activity_category_code=ac.id
where b.name='Brand01'
group by ac.activity_name;


6. List customers of Brand01 that have redeemed at least twice.


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


select b.name
from customer_redeem_activity cre join rewards_for_loyalty_program rlp on cre.redeem_lp_map_id=rlp.reward_lp_map_id
join loyalty_program lp on lp.id=rlp.loyalty_program_code
join brand b on b.id=lp.brand_id
group by b.name
having sum(cre.points)<=500;

8. For Customer C0003, and Brand02, number of activities they have done in the period of
08/1/2021 and 9/30/2021


select count(*)
from customer c join customer_lp_enroll cle on c.id=cle.customer_id
join loyalty_program lp on cle.loyalty_program_code=lp.id
join brand b on b.id=lp.brand_id
join customer_activity ca on ca.customer_id=cle.customer_id
where c.name='C0003' and b.name='Brand02' and ca.activity_date between '01-AUG-21' and '30-SEP-21';