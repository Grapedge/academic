BEGIN ;
INSERT IGNORE INTO `aca_user`
(username, password, profile_id, roles)VALUES
  ('system', 'system', NULL ,'ROLE_USER,ROLE_ADMIN,ROLE_SYSTEM');
COMMIT ;