INSERT INTO USER (username, password, is_active) VALUES
  ('tosadminuser', 'tosadminuser', true);

INSERT INTO ROLE (role_id, name) VALUES
  (1, 'ADMIN'),
  (2, 'MANAGER'),
  (3, 'EMPLOYEE'),
  (4, 'GUEST')
  ;

 INSERT INTO USER_ROLES (user_id, role_id) VALUES
  (1, 1);
  
  