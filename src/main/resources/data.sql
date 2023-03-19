


insert into school ( created_at, deleted, name)
values (NOW() ,false,'school1'),
       (NOW() ,false,'school2'),
       (NOW() ,false,'school3');

insert into grade ( created_at, deleted, name, school_id)
values (NOW() ,false,'grade1',1),
       (NOW() ,false,'grade2',1),
       (NOW() ,false,'grade3',1),
       (NOW() ,false,'grade4',1),
       (NOW() ,false,'grade5',1);

insert into grade ( created_at, deleted, name, school_id)
values (NOW() ,false,'grade1',2),
       (NOW() ,false,'grade2',2),
       (NOW() ,false,'grade3',2),
       (NOW() ,false,'grade4',2),
       (NOW() ,false,'grade5',2);


insert into grade ( created_at, deleted, name, school_id)
values (NOW() ,false,'grade1',3),
       (NOW() ,false,'grade2',3),
       (NOW() ,false,'grade3',3),
       (NOW() ,false,'grade4',3),
       (NOW() ,false,'grade5',3);


insert into class ( created_at, deleted, name, grade_id)
values  (NOW() ,false,'class1',1),
        (NOW() ,false,'class2',2),
        (NOW() ,false,'class3',3),
        (NOW() ,false,'class4',4),
        (NOW() ,false,'class5',5);

insert into student (created_at, deleted, name, class_id)
values (NOW() ,false,'student1',1),
       (NOW() ,false,'student2',2),
       (NOW() ,false,'student3',3),
       (NOW() ,false,'student4',4),
       (NOW() ,false,'student5',5);

insert into school_time ( class_time, created_at, deleted, subject, class_id)
values (NOW(),NOW(),false,'math',1),
       (NOW(),NOW(),false,'math',2),
       (NOW(),NOW(),false,'math',3),
       (NOW(),NOW(),false,'math',4),
       (NOW(),NOW(),false,'math',5);