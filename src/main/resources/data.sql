BEGIN ;
INSERT IGNORE INTO `aca_user`
(username, password, profile_id, roles)VALUES
  ('system', 'system', NULL ,'ROLE_USER,ROLE_ADMIN,ROLE_SYSTEM'),
  ('teacher1', 'teacher', NULL, 'ROLE_USER,ROLE_ADMIN'),
  ('teacher2', 'teacher', NULL, 'ROLE_USER,ROLE_ADMIN'),
  ('201500301001', '123456', NULL, 'ROLE_USER');
COMMIT ;

BEGIN ;
INSERT IGNORE INTO `aca_semester` (id,begin_date, end_date, name) VALUES
  (1,'2017-02-19 00:00:00', '2017-09-07 23:59:59', '2016-2017-2');
INSERT IGNORE INTO academic.aca_semester (id,begin_date, end_date, name) VALUES
  (2,'2017-09-08 00:00:00', '2018-01-28 23:59:59', '2017-2018-1');
INSERT IGNORE INTO academic.aca_semester (id,begin_date, end_date, name) VALUES
  (3,'2018-01-29 00:00:00', '2018-09-04 23:59:59', '2017-2018-2');
COMMIT ;


BEGIN ;
INSERT IGNORE INTO `aca_academy` (id, name) VALUES
  (1, '软件学院'),
  (2, '计算机学院');
COMMIT ;

BEGIN ;
INSERT IGNORE INTO `aca_major` (name, academy_id) VALUES
  ('软件工程', 1),
  ('计算机科学与技术', 2);
COMMIT ;

BEGIN ;
INSERT IGNORE INTO `aca_course` (course_name, credit, remaining, total, week,
                                 academy_id, semester_id, teacher_username)VALUES
  ('软件工程', 4.5, 200, 200, '001111111111110000', 1, 1, 'teacher1'),
  ('计算机网络', 5.0, 220, 220, '001111110011110000', 1, 2, 'teacher2'),
  ('计算机网络', 5.0, 220, 220, '001111110011110000', 2, 2, 'teacher2');
COMMIT ;

BEGIN ;
INSERT IGNORE INTO academic.aca_profile
(address, birthday, gender, id_no, major_id, name, unit, work_no)
VALUES
('山东省济南市高新区舜华路1500号', '1999-09-01 22:28:05',
 '男', '111120199909011131', 1, '张三', '山东大学', '201500301001');
COMMIT ;