begin;
INSERT IGNORE INTO `aca_role`
(id,name)VALUES
  (1, 'USER'),
  (2, 'ADMIN'),
  (3, 'SYSTEM');
commit;

BEGIN ;
INSERT IGNORE INTO `aca_user`
(username, password, profile_id)VALUES
  ('system', 'system', NULL );
COMMIT ;

BEGIN ;
INSERT IGNORE INTO `aca_user_roles`
(user_username, roles_id) VALUES
  ('system', 1),
  ('system', 2),
  ('system', 3);
COMMIT ;