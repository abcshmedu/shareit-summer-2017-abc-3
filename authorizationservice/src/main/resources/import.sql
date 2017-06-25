
drop table PermissionEntity if exists
drop table TokenEntity if exists
drop table UserEntity if exists
drop table UserRoleEntity if exists
drop table UserRoleEntity_PermissionEntity if exists
drop sequence hibernate_sequence if exists
create sequence hibernate_sequence start with 1 increment by 1
create table PermissionEntity (id bigint not null, method varchar(255), primary key (id))
create table TokenEntity (token varchar(255) not null, ttl bigint, user_id bigint, primary key (token))
create table UserEntity (id bigint not null, secret varchar(255), username varchar(255), userRoleEntity_id bigint, primary key (id))
create table UserRoleEntity (id bigint not null, name varchar(255), primary key (id))
create table UserRoleEntity_PermissionEntity (UserRoleEntity_id bigint not null, permissions_id bigint not null, primary key (UserRoleEntity_id, permissions_id))
alter table TokenEntity add constraint FK2t4enjo1a84nrq6r1tjvbah1s foreign key (user_id) references UserEntity
alter table UserEntity add constraint FKkw3abnacyl4agcvl1rv19m3ce foreign key (userRoleEntity_id) references UserRoleEntity
alter table UserRoleEntity_PermissionEntity add constraint FK7fvyqw6ap6k954gegiwm8ij9a foreign key (permissions_id) references PermissionEntity
alter table UserRoleEntity_PermissionEntity add constraint FKf714rlf551svhbhnk82vnan3 foreign key (UserRoleEntity_id) references UserRoleEntity
INSERT INTO PERMISSIONENTITY VALUES(1,'addBook')
INSERT INTO PERMISSIONENTITY VALUES(2,'getBook')
INSERT INTO PERMISSIONENTITY VALUES(3,'getBooks')
INSERT INTO PERMISSIONENTITY VALUES(4,'updateBooks')
INSERT INTO PERMISSIONENTITY VALUES(5,'addDisc')
INSERT INTO PERMISSIONENTITY VALUES(6,'getDisc')
INSERT INTO PERMISSIONENTITY VALUES(7,'getDiscs')
INSERT INTO PERMISSIONENTITY VALUES(8,'updateDisc')
INSERT INTO USERROLEENTITY VALUES(9,'ADMIN')
INSERT INTO USERROLEENTITY VALUES(10,'REGISTRED')
INSERT INTO USERENTITY VALUES(11,'shareitpassword','shareitadmin',9)
INSERT INTO USERENTITY VALUES(12,'shareitpassword','shareituser',10)
INSERT INTO USERROLEENTITY_PERMISSIONENTITY VALUES(9,4)
INSERT INTO USERROLEENTITY_PERMISSIONENTITY VALUES(9,7)
INSERT INTO USERROLEENTITY_PERMISSIONENTITY VALUES(9,2)
INSERT INTO USERROLEENTITY_PERMISSIONENTITY VALUES(9,8)
INSERT INTO USERROLEENTITY_PERMISSIONENTITY VALUES(9,6)
INSERT INTO USERROLEENTITY_PERMISSIONENTITY VALUES(9,3)
INSERT INTO USERROLEENTITY_PERMISSIONENTITY VALUES(9,1)
INSERT INTO USERROLEENTITY_PERMISSIONENTITY VALUES(9,5)
INSERT INTO USERROLEENTITY_PERMISSIONENTITY VALUES(10,7)
INSERT INTO USERROLEENTITY_PERMISSIONENTITY VALUES(10,2)
INSERT INTO USERROLEENTITY_PERMISSIONENTITY VALUES(10,6)
INSERT INTO USERROLEENTITY_PERMISSIONENTITY VALUES(10,3)
