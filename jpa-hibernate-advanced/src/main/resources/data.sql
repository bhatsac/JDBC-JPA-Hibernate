insert into course(id,name,created_date,last_updated_date)
values(10001,'JPA Full Tutorials',sysdate(),sysdate());
insert into course(id,name,created_date,last_updated_date)
values(10002,'Microservices Full Tutorials',sysdate(),sysdate());
insert into course(id,name,created_date,last_updated_date)
values(10003,'Angular Full Tutorials',sysdate(),sysdate());

insert into passport(id,number,created_date,last_updated_date)
values(40001,'KA-XYZ1',sysdate(),sysdate());
insert into passport(id,number,created_date,last_updated_date)
values(40002,'KA-XYZ2',sysdate(),sysdate());
insert into passport(id,number,created_date,last_updated_date)
values(40003,'KA-XYZ3',sysdate(),sysdate());

insert into student(id,name,passport_id,created_date,last_updated_date)
values(20001,'sac',40001,sysdate(),sysdate());
insert into student(id,name,passport_id,created_date,last_updated_date)
values(20002,'kee',40002,sysdate(),sysdate());
insert into student(id,name,passport_id,created_date,last_updated_date)
values(20003,'sud',40003,sysdate(),sysdate());




insert into review(id,rating,description,course_id,created_date,last_updated_date)
values(50001,'1',NULL,10001,sysdate(),sysdate());
insert into review(id,rating,description,course_id,created_date,last_updated_date)
values(50002,'3',NULL,10001,sysdate(),sysdate());
insert into review(id,rating,description,course_id,created_date,last_updated_date)
values(50003,'4',NULL,10003,sysdate(),sysdate());


insert into student_course(student_id,course_id)
values(20001,10001);
insert into student_course(student_id,course_id)
values(20002,10001);
insert into student_course(student_id,course_id)
values(20001,10002);
insert into student_course(student_id,course_id)
values(20002,10002);
