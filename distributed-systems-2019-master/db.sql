create table application_term
(
    id      int auto_increment
        primary key,
    active  tinyint(1) not null,
    percent tinyint    not null
);

create table department
(
    id   varchar(10)  not null
        primary key,
    name varchar(100) null
);

create table permission
(
    name        varchar(50)  not null
        primary key,
    description varchar(512) null
);

create table role
(
    name          varchar(50)  not null
        primary key,
    description   varchar(512) null,
    friendly_name varchar(50)  not null,
    constraint role_friendly_name_uindex
        unique (friendly_name)
);

create table role_permission
(
    role_name  varchar(50) not null,
    permission varchar(50) not null,
    primary key (role_name, permission),
    constraint role_permission_ibfk_1
        foreign key (role_name) references role (name)
            on update cascade,
    constraint role_permission_ibfk_2
        foreign key (permission) references permission (name)
);

create index permission
    on role_permission (permission);

create table user
(
    username   varchar(50)                   not null
        primary key,
    password   varchar(100)                  not null,
    role       varchar(50)                   null,
    department varchar(10)                   null,
    full_name  varchar(256)                  null,
    state      varchar(20) default 'PENDING' null,
    phone      varchar(50)                   null,
    constraint user_department_id_fk
        foreign key (department) references department (id),
    constraint user_role_name_fk
        foreign key (role) references role (name)
            on update cascade
);

create table application
(
    id                      int auto_increment
        primary key,
    commuting               tinyint(1)                not null,
    student_siblings        int                       not null,
    student_income          int                       not null,
    family_income           int                       not null,
    both_parents_unemployed tinyint(1)                not null,
    submission_date         datetime                  not null,
    application_term        int                       not null,
    submitter               varchar(50)               null,
    state                   varchar(16) default 'NEW' not null,
    constraint application_application_term_id_fk
        foreign key (application_term) references application_term (id),
    constraint application_user_username_fk
        foreign key (submitter) references user (username)
);

SET FOREIGN_KEY_CHECKS = 0;
INSERT INTO application_term (id, active, percent)
VALUES (1, 0, 80);
INSERT INTO department (id, name)
VALUES ('ddns', 'Nutrition and Dietetics');
INSERT INTO department (id, name)
VALUES ('dit', 'Informatics and Telematics');
INSERT INTO department (id, name)
VALUES ('ds', 'Environment, Geography and Applied Economics');
INSERT INTO permission (name, description)
VALUES ('PERM_ACCESS_CONTROL_PANEL', 'Access control panel');
INSERT INTO permission (name, description)
VALUES ('PERM_EDIT_USERS', 'Edit users and roles');
INSERT INTO permission (name, description)
VALUES ('PERM_MANAGE_TERM', 'Start/End application term');
INSERT INTO permission (name, description)
VALUES ('PERM_VERIFY_APPLICATIONS', 'Verify applications');
INSERT INTO permission (name, description)
VALUES ('PERM_VERIFY_STUDENT', 'Verify student accounts');
INSERT INTO permission (name, description)
VALUES ('PERM_VIEW_ACCEPTED_APPLICATIONS', 'View accepted application list');
INSERT INTO role (name, description, friendly_name)
VALUES ('ROLE_ADMIN', 'System Administrator, master of all', 'Sysadmin');
INSERT INTO role (name, description, friendly_name)
VALUES ('ROLE_EMPLOYEE', '', 'Employee');
INSERT INTO role (name, description, friendly_name)
VALUES ('ROLE_STUDENT', null, 'Student');
INSERT INTO role (name, description, friendly_name)
VALUES ('ROLE_SUPERVISOR', '', 'Supervisor');
INSERT INTO role_permission (role_name, permission)
VALUES ('ROLE_ADMIN', 'PERM_ACCESS_CONTROL_PANEL');
INSERT INTO role_permission (role_name, permission)
VALUES ('ROLE_ADMIN', 'PERM_EDIT_USERS');
INSERT INTO role_permission (role_name, permission)
VALUES ('ROLE_ADMIN', 'PERM_MANAGE_TERM');
INSERT INTO role_permission (role_name, permission)
VALUES ('ROLE_ADMIN', 'PERM_VERIFY_APPLICATIONS');
INSERT INTO role_permission (role_name, permission)
VALUES ('ROLE_ADMIN', 'PERM_VERIFY_STUDENT');
INSERT INTO role_permission (role_name, permission)
VALUES ('ROLE_ADMIN', 'PERM_VIEW_ACCEPTED_APPLICATIONS');
INSERT INTO role_permission (role_name, permission)
VALUES ('ROLE_EMPLOYEE', 'PERM_ACCESS_CONTROL_PANEL');
INSERT INTO role_permission (role_name, permission)
VALUES ('ROLE_EMPLOYEE', 'PERM_VERIFY_APPLICATIONS');
INSERT INTO role_permission (role_name, permission)
VALUES ('ROLE_EMPLOYEE', 'PERM_VERIFY_STUDENT');
INSERT INTO role_permission (role_name, permission)
VALUES ('ROLE_EMPLOYEE', 'PERM_VIEW_ACCEPTED_APPLICATIONS');
INSERT INTO role_permission (role_name, permission)
VALUES ('ROLE_SUPERVISOR', 'PERM_ACCESS_CONTROL_PANEL');
INSERT INTO role_permission (role_name, permission)
VALUES ('ROLE_SUPERVISOR', 'PERM_MANAGE_TERM');
INSERT INTO role_permission (role_name, permission)
VALUES ('ROLE_SUPERVISOR', 'PERM_VERIFY_APPLICATIONS');
INSERT INTO role_permission (role_name, permission)
VALUES ('ROLE_SUPERVISOR', 'PERM_VERIFY_STUDENT');
INSERT INTO role_permission (role_name, permission)
VALUES ('ROLE_SUPERVISOR', 'PERM_VIEW_ACCEPTED_APPLICATIONS');
INSERT INTO user (username, password, role, department, full_name, state, phone)
VALUES ('emp_ddns', '$2y$04$jwwscQy.hL.TH7arCydtr.oh27ETxp0xJMeixW7qqGDk./JEaZ/kq', 'ROLE_EMPLOYEE', 'ddns', null,
        'APPROVED', null);
INSERT INTO user (username, password, role, department, full_name, state, phone)
VALUES ('emp_dit', '$2y$04$jwwscQy.hL.TH7arCydtr.oh27ETxp0xJMeixW7qqGDk./JEaZ/kq', 'ROLE_SUPERVISOR', 'dit', null,
        'APPROVED', null);
INSERT INTO user (username, password, role, department, full_name, state, phone)
VALUES ('emp_ds', '$2y$04$jwwscQy.hL.TH7arCydtr.oh27ETxp0xJMeixW7qqGDk./JEaZ/kq', 'ROLE_EMPLOYEE', 'ds', null,
        'APPROVED', null);
INSERT INTO user (username, password, role, department, full_name, state, phone)
VALUES ('it21701', '$2y$04$jwwscQy.hL.TH7arCydtr.oh27ETxp0xJMeixW7qqGDk./JEaZ/kq', 'ROLE_STUDENT', 'dit', null,
        'APPROVED', null);
INSERT INTO user (username, password, role, department, full_name, state, phone)
VALUES ('it21702', '$2y$04$jwwscQy.hL.TH7arCydtr.oh27ETxp0xJMeixW7qqGDk./JEaZ/kq', 'ROLE_STUDENT', 'dit', null,
        'APPROVED', null);
INSERT INTO user (username, password, role, department, full_name, state, phone)
VALUES ('it21703', '$2y$04$jwwscQy.hL.TH7arCydtr.oh27ETxp0xJMeixW7qqGDk./JEaZ/kq', 'ROLE_STUDENT', 'dit', null,
        'APPROVED', null);
INSERT INTO user (username, password, role, department, full_name, state, phone)
VALUES ('it21704', '$2y$04$jwwscQy.hL.TH7arCydtr.oh27ETxp0xJMeixW7qqGDk./JEaZ/kq', 'ROLE_STUDENT', 'dit', null,
        'APPROVED', null);
INSERT INTO user (username, password, role, department, full_name, state, phone)
VALUES ('it21705', '$2y$04$jwwscQy.hL.TH7arCydtr.oh27ETxp0xJMeixW7qqGDk./JEaZ/kq', 'ROLE_STUDENT', 'dit', null,
        'APPROVED', null);
INSERT INTO user (username, password, role, department, full_name, state, phone)
VALUES ('it21706', '$2y$04$jwwscQy.hL.TH7arCydtr.oh27ETxp0xJMeixW7qqGDk./JEaZ/kq', 'ROLE_STUDENT', 'dit', null,
        'APPROVED', null);
INSERT INTO user (username, password, role, department, full_name, state, phone)
VALUES ('it21707', '$2y$04$jwwscQy.hL.TH7arCydtr.oh27ETxp0xJMeixW7qqGDk./JEaZ/kq', 'ROLE_STUDENT', 'dit', null,
        'APPROVED', null);
INSERT INTO user (username, password, role, department, full_name, state, phone)
VALUES ('it21708', '$2y$04$jwwscQy.hL.TH7arCydtr.oh27ETxp0xJMeixW7qqGDk./JEaZ/kq', 'ROLE_STUDENT', 'dit', null,
        'APPROVED', null);
INSERT INTO user (username, password, role, department, full_name, state, phone)
VALUES ('it21709', '$2y$04$jwwscQy.hL.TH7arCydtr.oh27ETxp0xJMeixW7qqGDk./JEaZ/kq', 'ROLE_STUDENT', 'dit', null,
        'APPROVED', null);
INSERT INTO user (username, password, role, department, full_name, state, phone)
VALUES ('it21710', '$2y$04$jwwscQy.hL.TH7arCydtr.oh27ETxp0xJMeixW7qqGDk./JEaZ/kq', 'ROLE_STUDENT', 'dit', null,
        'PENDING', null);
INSERT INTO user (username, password, role, department, full_name, state, phone)
VALUES ('root', '$2y$04$jwwscQy.hL.TH7arCydtr.oh27ETxp0xJMeixW7qqGDk./JEaZ/kq', 'ROLE_ADMIN', 'dit',
        'Testing McTestington', 'APPROVED', null);
SET FOREIGN_KEY_CHECKS = 1;