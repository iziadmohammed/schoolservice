
truncate table test.public.item  RESTART IDENTITY CASCADE ;
truncate table test.public.permission_group RESTART IDENTITY CASCADE ;
truncate table test.public.permission RESTART IDENTITY CASCADE ;
truncate table test.public.file RESTART IDENTITY CASCADE ;


Insert into test.public.permission_group (group_name) values ('admin');
Insert into test.public.permission (user_email,permission_level,permission_group_id) values ('admin@stc.com','EDIT',1);
Insert into test.public.permission (user_email,permission_level,permission_group_id) values ('viewer@stc.com','VIEW',1);

