insert into actor values('admin','admin','admin');

insert into activity_category values('A01','Purchase');
insert into activity_category values('A02','Write a review');
insert into activity_category values('A03','Refer a friend');


insert into reward_category values('R01','Gift Card');
insert into reward_category values('R02','Free Product');

insert into brand values('Brand01','Brand X','503 Rolling Creek Dr Austin, AR','01-APR-21','Brand01');
insert into brand values('Brand02','Brand Y','939 Orange Ave Coronado, CA','25-MAR-21','Brand02');
insert into brand values('Brand03','Brand Z','20 Roszel Rd Princeton, NJ','08-MAY-21','Brand03');

insert into loyalty_program values('TLP01','SportGoods','Brand01','Tiered','ACTIVE');
insert into loyalty_program values('TLP02','MegaCenter','Brand02','Tiered','ACTIVE');
insert into loyalty_program values('RLP01','TechSups','Brand03','Regular','ACTIVE');

insert into tier values('1','Bronze',0,1,'TLP01');
insert into tier values('2','Silver',170,2,'TLP01');
insert into tier values('3','Gold',270,3,'TLP01');
insert into tier values('4','Special',0,1,'TLP02');
insert into tier values('5','Premium',210,2,'TLP02');

insert into re_rule values('1','A01',15,1,'E');
insert into re_rule values('2','A02',10,1,'E');

insert into re_rule_for_lp values('TLP01','1');
insert into re_rule_for_lp values('TLP01','2');

insert into re_rule values('3','A01',40,1,'E');
insert into re_rule values('4','A03',30,1,'E');

insert into re_rule_for_lp values('TLP02','3');
insert into re_rule_for_lp values('TLP02','4');

insert into re_rule values('5','A03',10,1,'E');

insert into re_rule_for_lp values('RLP01','5');

insert into rr_rule values('1','R01',80,1,'E');
insert into rr_rule values('2','R02',70,1,'E');
insert into rr_rule values('3','R01',120,1,'E');
insert into rr_rule values('4','R02',90,1,'E');
insert into rr_rule values('5','R01',100,1,'E');

insert into rr_rule_for_lp values('TLP01','1');
insert into rr_rule_for_lp values('TLP01','2');
insert into rr_rule_for_lp values('TLP02','3');
insert into rr_rule_for_lp values('TLP02','4');
insert into rr_rule_for_lp values('RLP01','5');

insert into rewards_for_loyalty_program values('1','TLP01','R01',40,'10');
insert into rewards_for_loyalty_program values('2','TLP01','R02',25,'10');
insert into rewards_for_loyalty_program values('3','TLP02','R01',30,'10');
insert into rewards_for_loyalty_program values('4','TLP02','R02',50,'10');
insert into rewards_for_loyalty_program values('5','RLP01','R01',25,'10');

insert into activities_for_loyalty_program values('1','TLP01','A01');
insert into activities_for_loyalty_program values('2','TLP01','A02');
insert into activities_for_loyalty_program values('3','TLP02','A01');
insert into activities_for_loyalty_program values('4','TLP02','A03');
insert into activities_for_loyalty_program values('5','RLP01','A03');

insert into customer values('C0001','Peter Parker',8636368778,'20 Ingram Street, NY','C0001');
insert into customer values('C0002','Steve Rogers',8972468552,'569 Leaman Place, NY','C0002');
insert into customer values('C0003','Diana Prince',8547963210,'1700 Broadway St, NY','C0003');
insert into customer values('C0004','Billy Batson',8974562583,'5015 Broad St,Philadelphia, PA','C0004');
insert into customer values('C0005','Tony Stark',8731596464,'10880 Malibu Point, CA','C0005');

insert into customer_lp_enroll values('C0001','TLP01');
insert into customer_lp_enroll values('C0001','TLP02');
insert into customer_lp_enroll values('C0002','TLP01');
insert into customer_lp_enroll values('C0003','TLP02');
insert into customer_lp_enroll values('C0003','RLP01');
insert into customer_lp_enroll values('C0005','TLP01');
insert into customer_lp_enroll values('C0005','TLP02');
insert into customer_lp_enroll values('C0005','RLP01');

insert into customer_activity values('1','C0001','10-JUN-21','1',null,null);
insert into customer_activity values('2','C0001','10-JUN-21','1',null,null);
insert into customer_activity values('3','C0001','10-JUN-21','1',null,null);
insert into customer_activity values('4','C0001','10-JUN-21','1',null,null);

insert into customer_activity values('5','C0001','18-JUN-21','2',null,null);
insert into customer_activity values('6','C0001','18-JUN-21','2',null,null);

insert into customer_redeem_activity values('1','C0001','02-JUL-21','1',80);

insert into customer_activity values('7','C0001','09-AUG-21','3',null,null);
insert into customer_activity values('8','C0001','09-AUG-21','3',null,null);
insert into customer_activity values('9','C0001','15-AUG-21','3',null,null);

insert into customer_redeem_activity values('2','C0001','25-AUG-21','3',120);

insert into customer_activity values('10','C0001','03-OCT-21','4',null,null);

insert into customer_activity values('11','C0001','18-OCT-21','4',null,null);
insert into customer_activity values('12','C0001','18-OCT-21','4',null,null);

insert into customer_redeem_activity values('3','C0001','20-OCT-21','4',90);


insert into customer_activity values('13','C0002','02-JUL-21','1',null,null);
insert into customer_activity values('14','C0002','02-JUL-21','1',null,null);

insert into customer_activity values('15','C0002','08-JUL-21','1',null,null);
insert into customer_activity values('16','C0002','08-JUL-21','1',null,null);

insert into customer_activity values('17','C0002','05-AUG-21','2',null,null);
insert into customer_activity values('18','C0002','05-AUG-21','2',null,null);

insert into customer_redeem_activity values('4','C0002','01-SEP-21','2',70);

insert into customer_activity values('19','C0003','30-JUL-21','5',null,null);
insert into customer_activity values('20','C0003','30-JUL-21','5',null,null);
insert into customer_activity values('21','C0003','30-JUL-21','5',null,null);
insert into customer_activity values('22','C0003','30-JUL-21','5',null,null);

insert into customer_activity values('23','C0003','01-AUG-21','3',null,null);

insert into customer_activity values('24','C0003','10-AUG-21','3',null,null);
insert into customer_activity values('25','C0003','10-AUG-21','3',null,null);

insert into customer_redeem_activity values('5','C0003','26-AUG-21','4',90);

insert into customer_activity values('26','C0003','02-SEP-21','3',null,null);

insert into customer_activity values('27','C0003','01-OCT-21','4',null,null);

insert into customer_activity values('28','C0003','16-OCT-21','4',null,null);

insert into customer_redeem_activity values('6','C0003','18-OCT-21','4',90);

insert into customer_activity values('29','C0005','10-AUG-21','1',null,null);
insert into customer_activity values('30','C0005','10-AUG-21','1',null,null);
insert into customer_activity values('31','C0005','10-AUG-21','1',null,null);
insert into customer_activity values('32','C0005','10-AUG-21','1',null,null);
insert into customer_activity values('33','C0005','10-AUG-21','1',null,null);
insert into customer_activity values('34','C0005','10-AUG-21','1',null,null);

insert into customer_activity values('35','C0005','16-SEP-21','5',null,null);
insert into customer_activity values('36','C0005','16-SEP-21','5',null,null);
insert into customer_activity values('37','C0005','16-SEP-21','5',null,null);

insert into customer_activity values('38','C0005','30-SEP-21','5',null,null);
insert into customer_activity values('39','C0005','30-SEP-21','5',null,null);

insert into customer_activity values('40','C0005','30-SEP-21','2',null,null);
insert into customer_activity values('41','C0005','30-SEP-21','2',null,null);
insert into customer_activity values('42','C0005','30-SEP-21','2',null,null);
insert into customer_activity values('43','C0005','30-SEP-21','2',null,null);
insert into customer_activity values('44','C0005','30-SEP-21','2',null,null);

insert into customer_activity values('45','C0005','10-OCT-21','3',null,null);
insert into customer_activity values('46','C0005','10-OCT-21','3',null,null);
insert into customer_activity values('47','C0005','10-OCT-21','3',null,null);
insert into customer_activity values('48','C0005','10-OCT-21','3',null,null);

insert into customer_redeem_activity values('7','C0005','11-OCT-21','3',120);
insert into customer_redeem_activity values('8','C0005','11-OCT-21','1',80);

insert into customer_activity values('49','C0005','15-OCT-21','2',null,null);
insert into customer_activity values('50','C0005','15-OCT-21','2',null,null);
insert into customer_activity values('51','C0005','15-OCT-21','2',null,null);

insert into customer_redeem_activity values('9','C0005','17-OCT-21','2',70);

commit;