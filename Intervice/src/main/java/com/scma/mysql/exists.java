package com.scma.mysql;

public class exists {
}
/*
CREATE TABLE student(
 id INT(8) PRIMARY KEY,
 NAME VARCHAR(10),deptment INT(8)
)


-- 选课表：
CREATE TABLE select_course
(
  ID         INT(8) PRIMARY KEY,
  STUDENT_ID INT(8) ,
  COURSE_ID  INT(8)
)
-- 课程表：

CREATE TABLE COURSE
(
  ID     INT(8) NOT NULL,
  C_NAME VARCHAR(20),
  C_NO   VARCHAR(10)
)
*/






/*
-- 1.查询选修了所有课程的学生id、name:（即这一个学生没有一门课程他没有选的。）
SELECT * FROM student ts WHERE NOT EXISTS
	 (SELECT * FROM COURSE c ,student ts WHERE NOT EXISTS
  		(SELECT sc.* FROM select_course sc,COURSE c ,student ts WHERE sc.student_id=ts.id AND sc.course_id=c.id));

  -- 如果有一门课没有选，则此时(1)
  SELECT * FROM select_course sc WHERE sc.student_id=ts.id
AND sc.course_id=c.id   -- 存在null，


-- 2.查询没有选择所有课程的学生，即没有全选的学生。（存在这样的一个学生，他至少有一门课没有选），
SELECT id,NAME FROM student WHERE EXISTS
	(SELECT * FROM COURSE WHERE NOT EXISTS
		(SELECT * FROM select_course sc WHERE student_id=student.id AND course_id=COURSE.id));



-- 3.查询一门课也没有选的学生。（不存这样的一个学生，他至少选修一门课程），
SELECT id,NAME FROM student WHERE NOT EXISTS
	(SELECT * FROM COURSE WHERE EXISTS
		(SELECT * FROM select_course sc WHERE student_id=student.id AND course_id=COURSE.id));



-- 4.查询至少选修了一门课程的学生。
SELECT id,NAME FROM student WHERE EXISTS
	(SELECT * FROM COURSE WHERE  EXISTS
		(SELECT * FROM select_course sc WHERE student_id=student.id AND course_id=COURSE.id));



*/