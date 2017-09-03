BEGIN;

# initialize the Role table;
INSERT IGNORE INTO `aca_role`
(id, name)
VALUES
  (1, 'USER'),
  (2, 'ADMIN'),
  (3, 'SYSTEM');

# create system user
INSERT IGNORE INTO `aca_user`
(id, expired, locked, password, username, profile_id)
VALUES
  (1, 0, 0, 'system', 'system', NULL);

# add system role to system user
INSERT IGNORE INTO `aca_user_roles`
(user_id, roles_id) VALUES
  (1, 1),
  (1, 2),
  (1, 3);

COMMIT;