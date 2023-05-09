INSERT INTO lector (`id`,`degree`,`first_name`,`last_name`,`salary`) VALUES (1,'PROFESSOR','Yaroslav','Fedyna',29300.00);
INSERT INTO lector (`id`,`degree`,`first_name`,`last_name`,`salary`) VALUES (2,'PROFESSOR','Mykola','Salo',25000.00);
INSERT INTO lector (`id`,`degree`,`first_name`,`last_name`,`salary`) VALUES (3,'PROFESSOR','Olena','Kolomyets',27000.00);
INSERT INTO lector (`id`,`degree`,`first_name`,`last_name`,`salary`) VALUES (4,'ASSISTANT','Taras','Kirylenko',15000.00);
INSERT INTO lector (`id`,`degree`,`first_name`,`last_name`,`salary`) VALUES (5,'ASSOCIATE_PROFESSOR','Iryna','Khorkavtsy',21000.00);
INSERT INTO lector (`id`,`degree`,`first_name`,`last_name`,`salary`) VALUES (6,'PROFESSOR','Yaroslav','Salo',29100.00);

INSERT INTO department (`id`,`name`,`head_of_department_id`) VALUES (1,'Dep1',1);
INSERT INTO department (`id`,`name`,`head_of_department_id`) VALUES (2,'Dep2',2);
INSERT INTO department (`id`,`name`,`head_of_department_id`) VALUES (3,'Dep3',3);

INSERT INTO departments_lectors (`department_id`,`lector_id`) VALUES (1,1);
INSERT INTO departments_lectors (`department_id`,`lector_id`) VALUES (2,2);
INSERT INTO departments_lectors (`department_id`,`lector_id`) VALUES (3,3);
INSERT INTO departments_lectors (`department_id`,`lector_id`) VALUES (1,4);
INSERT INTO departments_lectors (`department_id`,`lector_id`) VALUES (1,5);
INSERT INTO departments_lectors (`department_id`,`lector_id`) VALUES (2,1);
INSERT INTO departments_lectors (`department_id`,`lector_id`) VALUES (3,1);
INSERT INTO departments_lectors (`department_id`,`lector_id`) VALUES (2,5);
INSERT INTO departments_lectors (`department_id`,`lector_id`) VALUES (1,6);
