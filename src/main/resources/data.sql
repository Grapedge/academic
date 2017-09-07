BEGIN;
INSERT IGNORE INTO `aca_user`
(username, password, profile_id, roles) VALUES
  ('system', 'system', NULL, 'ROLE_USER,ROLE_ADMIN,ROLE_SYSTEM'),
  ('teacher1', 'teacher', 2, 'ROLE_USER,ROLE_ADMIN'),
  ('teacher2', 'teacher', NULL, 'ROLE_USER,ROLE_ADMIN'),
  ('201500301001', '123456', 1, 'ROLE_USER'),
  ('201500301002', '123456', 3, 'ROLE_USER');
COMMIT;

BEGIN;
INSERT IGNORE INTO `aca_semester` (id, begin_date, end_date, name) VALUES
  (1, '2017-02-19 00:00:00', '2017-09-07 23:59:59', '2016-2017-2');
INSERT IGNORE INTO academic.aca_semester (id, begin_date, end_date, name) VALUES
  (2, '2017-09-08 00:00:00', '2018-01-28 23:59:59', '2017-2018-1');
INSERT IGNORE INTO academic.aca_semester (id, begin_date, end_date, name) VALUES
  (3, '2018-01-29 00:00:00', '2018-09-04 23:59:59', '2017-2018-2');
COMMIT;


BEGIN;
INSERT IGNORE INTO `aca_academy` (id, name) VALUES
  (1, '软件学院'),
  (2, '计算机学院');
COMMIT;

BEGIN;
INSERT IGNORE INTO `aca_major` (id, name, academy_id) VALUES
  (1, '软件工程', 1),
  (2, '计算机科学与技术', 2);
COMMIT;

BEGIN;
INSERT IGNORE INTO `aca_course` (id, course_name, credit, remaining, total, week,
                                 academy_id, semester_id, teacher_username) VALUES
  (1, '软件工程', 4.5, 200, 200, '001111111111110000', 1, 1, 'teacher1'),
  (2, '计算机网络', 5.0, 220, 220, '001111110011110000', 1, 2, 'teacher2'),
  (3, '计算机网络', 5.0, 220, 220, '001111110011110000', 2, 2, 'teacher2');
COMMIT;

BEGIN;
INSERT IGNORE INTO academic.aca_profile (id, address, birthday, gender, id_no, name, unit, work_no, major_id)
VALUES
  (1, '山东省济南市高新区舜华路1500号', '1999-09-01 22:28:05', '男', '111120199909011131', '张三', '山东大学',
   '201500301001', 1),
  (2, '山东省济南市高新区舜华路1500号', '1987-09-01 22:28:05', '男', '555120198709011131', '李老师', '山东大学大学软件学院',
   '2009301008', 1),
  (3, '山东省济南市高新区舜华路1500号', '1999-09-19 22:28:05', '男', '333120199909191131', '李四', '山东大学',
   '201500301002', 1);
COMMIT;

BEGIN ;
INSERT IGNORE INTO academic.aca_selection (score, user_username, course_id) VALUES (-1, '201500301001', 1);
INSERT IGNORE INTO academic.aca_selection (score, user_username, course_id) VALUES (-1, '201500301002', 1);
INSERT IGNORE INTO academic.aca_selection (score, user_username, course_id) VALUES (-1, '201500301001', 2);
COMMIT ;