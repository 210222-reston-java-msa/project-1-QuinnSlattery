DROP TABLE project1.USERS, project1.USER_ROLES, project1.reimbursement_type, project1.reimbursement_status, project1.reimbursement;

CREATE TABLE REIMBURSEMENT (
	REIMB_ID SERIAL PRIMARY KEY,
	REIMB_AMOUNT NUMERIC(20, 2),
	REIMB_SUBMITTED VARCHAR(50),
	REIMB_RESOLVED VARCHAR(50),
	REIMB_DESCRIPTION VARCHAR(250),
	REIMB_AUTHOR VARCHAR(50) REFERENCES USERS(USERNAME),
	REIMB_RESOLVER VARCHAR(50) REFERENCES USERS(USERNAME),
	REIMB_STATUS_ID INTEGER REFERENCES REIMBURSEMENT_STATUS(STATUS_ID),
	REIMB_TYPE_ID INTEGER REFERENCES REIMBURSEMENT_TYPE(TYPE_ID)
);

CREATE TABLE USERS (
	USERNAME VARCHAR(50) PRIMARY KEY,
	USER_PASSWORD VARCHAR(50),
	FIRST_NAME VARCHAR(50),
	LAST_NAME VARCHAR(50),
	EMAIL VARCHAR(50) UNIQUE,
	ROLE_ID INTEGER REFERENCES USER_ROLES(ROLE_ID)
);

CREATE TABLE REIMBURSEMENT_STATUS (
STATUS_ID INTEGER PRIMARY KEY,
STATUS VARCHAR(10)
);

CREATE TABLE REIMBURSEMENT_TYPE (
TYPE_ID INTEGER UNIQUE PRIMARY KEY,
REIMB_TYPE VARCHAR(25)
);

CREATE TABLE USER_ROLES (
ROLE_ID INTEGER UNIQUE PRIMARY KEY,
USER_ROLE VARCHAR(10)
);

INSERT INTO project1.reimbursement_status 
	(STATUS_ID, STATUS)
VALUES
	(1, 'PENDING');
	
INSERT INTO project1.reimbursement_status
	(STATUS_ID, STATUS)
VALUES
	(2, 'ACCEPTED');
INSERT INTO reimbursement_status 
	(STATUS_ID, STATUS)
VALUES
	(3, 'REJECTED');
	

INSERT INTO users (username, user_password, first_name, last_name, email, role_id)
VALUES ('QuinnSlattery', 'pass', 'Quinn', 'Slattery', 'notquinnslattery@gmail.com', 1);

INSERT INTO users (username, user_password, first_name, last_name, email, role_id)
VALUES ('TaylorRoss', 'pass', 'Taylor', 'Ross', 'taynross@gmail.com', 1);

INSERT INTO users (username, user_password, first_name, last_name, email, role_id)
VALUES ('Manager', 'pass', 'Mike', 'Smith', 'mike@gmail.com', 2);

INSERT INTO USER_ROLES 
	(role_id, USER_ROLE)
VALUES 
	(1, 'Employee');
INSERT INTO user_roles 
	(role_id, user_role)
VALUES
	(2, 'Manager');

INSERT INTO project1.reimbursement_type 
	(type_id, reimb_type)
VALUES
	(1, 'Lodging');
	
INSERT INTO project1.reimbursement_type 
	(type_id, reimb_type)
VALUES
	(2, 'Travel');

INSERT INTO project1.reimbursement_type 
	(type_id, reimb_type)
VALUES
	(3, 'Food');
	
INSERT INTO project1.reimbursement_type 
	(type_id, reimb_type)
VALUES
	(4, 'Other');
	
SELECT * FROM project1.users WHERE users.username = 'QuinnSlattery';

UPDATE project1.users SET user_password = 'pass', first_name = 'Quinn'
	WHERE username = 'QuinnSlattery';
	
UPDATE project1.reimbursement SET reimb_description = 'food' WHERE reimb_amount = 500;


SELECT * FROM reimbursement WHERE reimb_status_id = 2 OR reimb_status_id = 3;