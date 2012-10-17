



INSERT INTO ROLE(NAME,DESCRIPTION) VALUES ('admin','Adiministrator Role');
INSERT INTO ROLE(NAME,DESCRIPTION) VALUES ('moderator','Moderator Role');
INSERT INTO ROLE(NAME,DESCRIPTION) VALUES ('employee','Employee Role');



INSERT INTO PERMISSION(ID,NAME) VALUES (1,'ADD_EMPLOYEE');
INSERT INTO PERMISSION(ID,NAME) VALUES (2,'DELETE_EMPLOYEE');
INSERT INTO PERMISSION(ID,NAME) VALUES (3,'UPDATE_EMPLOYEE');
INSERT INTO PERMISSION(ID,NAME) VALUES (4,'ADD_PRODUCT');
INSERT INTO PERMISSION(ID,NAME) VALUES (5,'UPDATE_PRODUCT');
INSERT INTO PERMISSION(ID,NAME) VALUES (6,'DELETE_PRODUCT');

INSERT INTO PERMISSION(ID,NAME) VALUES (7,'DELETE_POST');
INSERT INTO PERMISSION(ID,NAME) VALUES (8,'CHANGE_POST_STATUS');
INSERT INTO PERMISSION(ID,NAME) VALUES (9,'CHANGE_POST_EMPLOYEE');
INSERT INTO PERMISSION(ID,NAME) VALUES (10,'ANSWER_POST');
INSERT INTO PERMISSION(ID,NAME) VALUES (11,'UPDATE_POST');
INSERT INTO PERMISSION(ID,NAME) VALUES (12,'CREATE_ISSUE');
INSERT INTO PERMISSION(ID,NAME) VALUES (13,'MARK_POST_SPAM');
INSERT INTO PERMISSION(ID,NAME) VALUES (14,'MARK_POST_UNSPAM');
INSERT INTO PERMISSION(ID,NAME) VALUES (15,'MARK_POST_PRIVATE');
INSERT INTO PERMISSION(ID,NAME) VALUES (16,'MARK_POST_PUBLIC');


INSERT INTO PERMISSION(ID,NAME) VALUES (17,'ADD_ISSUETRACKER');
INSERT INTO PERMISSION(ID,NAME) VALUES (18,'UPDATE_ISSUETRACKER');
INSERT INTO PERMISSION(ID,NAME) VALUES (19,'DELETE_ISSUETRACKER');
INSERT INTO PERMISSION(ID,NAME) VALUES (20,'UPDATE_COMPANY_DETAILS');

INSERT INTO PERMISSION(ID,NAME) VALUES (21,'COMPANY_EMPLOYEE');

INSERT INTO PERMISSION(ID,NAME) VALUES (22,'CREATE_ANNOUNCEMENT');
INSERT INTO PERMISSION(ID,NAME) VALUES (23,'UPDATE_ANNOUNCEMENT');
INSERT INTO PERMISSION(ID,NAME) VALUES (24,'DELETE_ANNOUNCEMENT');
INSERT INTO PERMISSION(ID,NAME) VALUES (25,'PUBLISH_ANNOUNCEMENT');
INSERT INTO PERMISSION(ID,NAME) VALUES (26,'UNPUBLISH_ANNOUNCEMENT');



--Data for Role-Permission
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',1);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',2);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',3);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',4);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',5);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',6);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',7);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',8);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',9);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',10);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',11);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',12);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',13);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',14);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',15);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',16);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',17);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',18);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',19);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',20);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',21);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',22);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',23);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',24);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',25);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('admin',26);





INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('moderator',5);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('moderator',7);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('moderator',8);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('moderator',9);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('moderator',10);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('moderator',11);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('moderator',12);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('moderator',13);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('moderator',14);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('moderator',15);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('moderator',16);
INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('moderator',21);


INSERT INTO ROLE_PERMISSION(ROLE_NAME,PERMISSION_ID) VALUES('employee',21);

INSERT INTO
	PLAN (ID,NAME,DESCRIPTION)
	VALUES
		(1,'FREE','Free Plan'),
		(2,'ENTERPRISE','Enterprise Plan with all features');

INSERT INTO
	FEATURE (ID,NAME,FEATURE_TYPE,COUNT,PLAN_ID)
	VALUES
		(1,'FREE-ProductCount','ProductCount',1,1),
		(2,'FREE-EmployeeCount','EmployeeCount',1,1),
		(3,'FREE-DashboardSupport','DashboardSupport',1,1),
		(4,'ENTERPRISE-ProductCount','ProductCount',1000,2),
		(5,'ENTERPRISE-EmployeeCount','EmployeeCount',1000,2),
		(6,'ENTERPRISE-DashboardSupport','DashboardSupport',1,2),
		(7,'ENTERPRISE-Redmine','Redmine',1,2),
		(8,'ENTERPRISE-Bugzilla','Bugzilla',1,2),
		(9,'ENTERPRISE-JIRA','JIRA',1,2),
		(10,'ENTERPRISE-GoogleAnalytics','GoogleAnalytics',1,2),
		(11,'ENTERPRISE-JavascriptVariables','JavascriptVariables',1,2),
		(12,'ENTERPRISE-EmailNotifications','EmailNotifications',1,2),
		(13,'ENTERPRISE-DomainAliasing','DomainAliasing',1,2),
		(14,'ENTERPRISE-AnalyticsSupport','AnalyticsSupport',1,2);		