use master

DROP DATABASE QuizOnline;
go

CREATE DATABASE QuizOnline;
go

use QuizOnline
go

CREATE TABLE tblAccount(
	email		nvarchar(100)	not null primary key,
	password	nvarchar(100)	not null,
	fullName	nvarchar(100)	not null,
	sex			bit				not null,
	phoneNumber	varchar(15)		not null,
	role		nvarchar(10)	not null,
	status		bit				not null,
)

CREATE TABLE tblQuestion(
	questionId		nvarchar(200)	not null primary key,
	questionContent	nvarchar(500)	not null,
	ansA			nvarchar(100)	not null,
	ansB			nvarchar(100)	not null,
	ansC			nvarchar(100)	not null,
	ansD			nvarchar(100)	not null,
	correctAns		nvarchar(100)	not null,
	createDate		datetime		not null,
	subjectId		nvarchar(10)	not null,
	status			bit				not null,
) 

CREATE TABLE tblQuiz(
	quizId			nvarchar(200)	not null primary key,
	quizName		nvarchar(100)	not null,
	limitTime		int				not null,
	email			nvarchar(100)	not null,
	subjectId		nvarchar(10)	not null,
	createDate		datetime		not null,
	numberQuestion	int				not null,
	timeStart		datetime		not null,
	timeEnd			datetime		not null,
	status			bit				not null,
) 

CREATE TABLE tblTakeQuiz(
	takeQuizId		nvarchar(200)	not null primary key,
	email			nvarchar(100)	not null,
	quizId			nvarchar(200)	not null,
	numOfCorrectAns	int				not null,
	score			int				not null,
	timeStart		datetime		not null,
	timeEnd			datetime		not null,
	subjectId		nvarchar(10)	not null,
)

CREATE TABLE tblSubject(
	subjectId		nvarchar(10)	not null primary key,
	subjectName		nvarchar(100)	not null,
)

ALTER TABLE tblQuestion
ADD FOREIGN KEY(subjectId)
REFERENCES tblSubject(subjectId)

ALTER TABLE tblQuiz
ADD FOREIGN KEY(subjectId)
REFERENCES tblSubject(subjectId)

ALTER TABLE tblTakeQuiz
ADD FOREIGN KEY(subjectId)
REFERENCES tblSubject(subjectId)

ALTER TABLE tblTakeQuiz
ADD FOREIGN KEY(email)
REFERENCES tblAccount(email)

ALTER TABLE tblTakeQuiz
ADD FOREIGN KEY(quizId)
REFERENCES tblQuiz(quizId)

ALTER TABLE tblQuiz
ADD FOREIGN KEY(email)
REFERENCES tblAccount(email)

INSERT INTO tblSubject(subjectId,subjectName)
VALUES	('sbj-1','Math'),
		('sbj-2','English');

INSERT INTO tblAccount(email,password,fullName,sex,phoneNumber,role,status)
VALUES ('tinhhse140856@fpt.edu.vn','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','huynh huu tin',1,'0912700360','admin',1),
		('thuannlse140855@fpt.edu.vn','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','nguyen le thuan',1,'0938546215','student',1),
		('nguyentgse140854@fpt.edu.vn','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','tran gia nguyen',1,'0912700360','student',1);

INSERT INTO tblQuestion(questionId,questionContent,ansA,ansB,ansC,ansD,correctAns,createDate,subjectId,status)
VALUES	('sbj-1-1','1+1 = ?','1','2','3','4','B','2011-01-26 14:30:00','sbj-1',1),
		('sbj-1-2','1+2 = ?','1','3','3','4','B','2011-01-26 14:30:01','sbj-1',1),
		('sbj-1-3','1+3 = ?','1','4','3','4','B','2011-01-26 14:30:02','sbj-1',1),
		('sbj-1-4','1+4 = ?','1','5','3','4','B','2011-01-26 14:30:03','sbj-1',1),
		('sbj-1-5','1+5 = ?','1','6','3','4','B','2011-01-26 14:30:04','sbj-1',1),
		('sbj-1-6','1+6 = ?','1','7','3','4','B','2011-01-26 14:30:05','sbj-1',1),
		('sbj-1-7','1+7 = ?','1','8','3','4','B','2011-01-26 14:30:06','sbj-1',1),
		('sbj-1-8','1+8 = ?','1','9','3','4','B','2011-01-26 14:30:07','sbj-1',1),
		('sbj-1-9','1+9 = ?','1','10','3','4','B','2011-01-26 14:30:08','sbj-1',1),
		('sbj-1-10','1+10 = ?','1','11','3','4','B','2011-01-26 14:30:09','sbj-1',1),
		('sbj-1-11','1+11 = ?','1','12','3','4','B','2011-01-26 14:30:10','sbj-1',1),
		('sbj-1-12','1+12 = ?','1','13','3','4','B','2011-01-26 14:30:11','sbj-1',1),
		('sbj-1-13','1+13 = ?','1','14','3','4','B','2011-01-26 14:30:12','sbj-1',1),
		('sbj-1-14','1+14 = ?','1','15','3','4','B','2011-01-26 14:30:13','sbj-1',1),
		('sbj-1-15','1+15 = ?','1','16','3','4','B','2011-01-26 14:30:14','sbj-1',1),
		('sbj-1-16','1+16 = ?','1','17','3','4','B','2011-01-26 14:30:15','sbj-1',1),
		('sbj-1-17','1+17 = ?','1','18','3','4','B','2011-01-26 14:30:16','sbj-1',1),
		('sbj-1-18','1+18 = ?','1','19','3','4','B','2011-01-26 14:30:17','sbj-1',1),
		('sbj-1-19','1+19 = ?','1','20','3','4','B','2011-01-26 14:30:18','sbj-1',1),
		('sbj-1-20','1+20 = ?','1','21','3','4','B','2011-01-26 14:30:19','sbj-1',1),
		('sbj-2-1','Hello','Hi','2','3','4','A','2011-01-26 14:30:19','sbj-2',1);


INSERT INTO tblQuiz(quizId,quizName,limitTime,email,subjectId,createDate,numberQuestion,timeStart,timeEnd,status)
VALUES	('tinhhse140856@fpt.edu.vn-1','Quiz 1',30,'tinhhse140856@fpt.edu.vn','sbj-1','2011-01-26 14:30:19',10,'2021-02-15 00:00:00','2021-05-01 23:59:59',1),
		('tinhhse140856@fpt.edu.vn-2','Quiz 2',30,'tinhhse140856@fpt.edu.vn','sbj-1','2011-01-26 14:30:20',10,'2021-02-15 00:00:00','2021-05-01 23:59:59',1),
		('tinhhse140856@fpt.edu.vn-3','Quiz 3',30,'tinhhse140856@fpt.edu.vn','sbj-2','2011-01-26 14:30:21',10,'2021-02-15 00:00:00','2021-05-01 23:59:59',1),
		('tinhhse140856@fpt.edu.vn-4','Quiz 4',1,'tinhhse140856@fpt.edu.vn','sbj-2','2011-01-26 14:30:22',1,'2021-05-01 00:00:00','2021-06-01 23:59:59',1),
		('tinhhse140856@fpt.edu.vn-5','Quiz 5',1,'tinhhse140856@fpt.edu.vn','sbj-2','2011-01-26 14:30:23',1,'2021-01-01 00:00:00','2021-02-01 23:59:59',1),
		('tinhhse140856@fpt.edu.vn-6','Quiz 6',1,'tinhhse140856@fpt.edu.vn','sbj-2','2011-01-26 14:30:24',1,'2021-01-01 00:00:00','2021-12-01 23:59:59',1);

INSERT INTO tblTakeQuiz(takeQuizId,email,quizId,numOfCorrectAns,score,timeStart,timeEnd,subjectId)
VALUES	('thuannlse140855@fpt.edu.vn-1','thuannlse140855@fpt.edu.vn','tinhhse140856@fpt.edu.vn-1',0,0,'2011-01-26 14:30:20','2011-01-26 14:30:20','sbj-1'),
		('thuannlse140855@fpt.edu.vn-2','thuannlse140855@fpt.edu.vn','tinhhse140856@fpt.edu.vn-2',0,0,'2011-01-26 14:30:21','2011-01-26 14:30:23','sbj-1'),
		('nguyentgse140854@fpt.edu.vn-1','nguyentgse140854@fpt.edu.vn','tinhhse140856@fpt.edu.vn-1',0,0,'2011-01-26 14:30:20','2011-01-26 14:30:20','sbj-1');


