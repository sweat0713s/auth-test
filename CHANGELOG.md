## CHANGELOG

#### 5-2. K8S 배포 2
✅ k8s 용 application properties 만들기
✅ docker image 만들기
✅ docker image push
✅ deployment yaml 추가 및 적용

#### 5-1. K8S 배포 1
✅ multi 환경을 위한 application properties 설정
✅ 환경 구축하기
✅ Mysql k8s 클러스터 배포

#### 4-5. App2app 2
✅ app2app 토큰 발급
✅ app2app 토큰 밸리데이션

#### 4-4. App2app 1
✅ 시스템 생성 및 시스템 별 기능을 API로 등록
✅ 시스템 별 권한 세팅

```
CREATE TABLE app (
	id BIGINT auto_increment NOT NULL,
	name varchar(45) NOT NULL,
	CONSTRAINT app_pk PRIMARY KEY (id),
	CONSTRAINT app_unique UNIQUE KEY (name)
);

INSERT INTO app (name) VALUES('calendar');
INSERT INTO app (name) VALUES('meeting-room');
INSERT INTO app (name) VALUES('vacation');

CREATE TABLE api (
	id BIGINT auto_increment NOT NULL,
	app_id BIGINT NOT NULL,
	`method` varchar(45) NULL,
	`path` varchar(45) NULL,
	CONSTRAINT api_pk PRIMARY KEY (id),
	CONSTRAINT api_app_FK FOREIGN KEY (app_id) REFERENCES app(id) 
);

INSERT INTO api (app_id, `method`, `path`) VALUES(1, 'GET', '/calendars');
INSERT INTO api (app_id, `method`, `path`) VALUES(1, 'POST', '/calendars');
INSERT INTO api (app_id, `method`, `path`) VALUES(1, 'DELETE', '/calendars');
INSERT INTO api (app_id, `method`, `path`) VALUES(2, 'GET', '/meeting-rooms');
INSERT INTO api (app_id, `method`, `path`) VALUES(2, 'POST', '/meeting-rooms');
INSERT INTO api (app_id, `method`, `path`) VALUES(2, 'DELETE', '/meeting-rooms');
INSERT INTO api (app_id, `method`, `path`) VALUES(3, 'GET', '/vacations');
INSERT INTO api (app_id, `method`, `path`) VALUES(3, 'POST', '/vacations');

RENAME TABLE `role` TO employee_role;

CREATE TABLE app_role (
	id BIGINT auto_increment NOT NULL,
	app_id BIGINT NOT NULL,
	api_id BIGINT NOT NULL,
	CONSTRAINT app_role_pk PRIMARY KEY (id),
	CONSTRAINT app_role_api_FK FOREIGN KEY (api_id) REFERENCES api(id),
	CONSTRAINT app_role_app_FK FOREIGN KEY (app_id) REFERENCES app(id)
);

INSERT INTO app_role (app_id, api_id) VALUES(1, 5);
INSERT INTO app_role (app_id, api_id) VALUES(2, 1);
```
#### 4-3. RBAC(Role Based Access Control)
✅ API 별 RBAC 설정 추가

#### 4-2. Spring Security
✅ spring security 적용하기
✅ 토큰 검증 로직 추가

#### 4-1. JWT, Junit
✅ JWT 토큰 유틸 작성 및 Junit 테스트

#### 3-4. Kakao Social Login 2
✅ 카카오 소셜 로그인 정보 확인
✅ 임직원 카카오 닉네임 정보 등록
✅ 확인된 정보가 등록된 유저의 정보인지 판단
```
ALTER TABLE employee ADD kakao_nick_name varchar(45) NULL;
ALTER TABLE employee ADD CONSTRAINT employee_unique UNIQUE KEY (kakao_nick_name);
-- kako_nick_name 입력
ALTER TABLE employee MODIFY COLUMN kakao_nick_name varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL;
```

#### 3-3. Kakao Social Login 1
✅ 회원 등록
✅ 카카오 소셜 로그인 지원

#### 3-2. Swagger 문서와 JPA Part 2
✅swagger 연동
✅jpa w/ relationship
```
create table employeeRole (id bigint primary key auto_increment, name varchar(45));
create table employee_role_mapping (id bigint primary key auto_increment, employee_id bigint, role_id bigint);

insert into employeeRole (name) values ('인사팀');
insert into employeeRole (name) values ('조직장');

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