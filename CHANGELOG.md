## CHANGELOG

#### 4. Swagger 문서와 JPA Part 2
✅swagger 연동
✅jpa w/ relationship
```
create table role (id bigint primary key auto_increment, name varchar(45));
create table employee_role_mapping (id bigint primary key auto_increment, employee_id bigint, role_id bigint);

insert into role (name) values ('인사팀');
insert into role (name) values ('조직장');

insert into employee_role_mapping(employee_id, role_id) values (1, 1);
insert into employee_role_mapping(employee_id, role_id) values (1, 2);
```

#### 3. JPA를 활용한 기본 CURD 구현
✅ simple jpa 구현

#### 2. DB 구성

✅ spring-boot-docker-compose 디펜던시 설정

✅ MySQL DB 연동 및 JPA 디펜던시 설정
```
CREATE TABLE `employee` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `department_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `department` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(50) DEFAULT NULL,
  `team_lead_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into department(dept_name, team_lead_id) values ('인사팀', 1);
insert into department(dept_name, team_lead_id) values ('IT팀', 2);

insert into employee(first_name, last_name, department_id) values ('길동', '홍', 1);
insert into employee(first_name, last_name, department_id) values ('제니', '김', 2);
insert into employee(first_name, last_name, department_id) values ('nathan', 'kim', 1);

```

#### 1. 프로젝트 환경 구성

✅ Spring Boot 환경 구성