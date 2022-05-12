INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('andres', '$2a$10$P4sRbae9hJnsqi5MOU7oROrTmK51ZYQn.ohZ7aUsrXs0tgFj1la3S', 1, 'Andres', 'Guzman', 'profesor@mail.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('admin', '$2a$10$6rX5wJzpwdmUgxf.a6i.a.6hZ4ECJ5wBxmE98WS5w8z/iJ0j1if/O', 1, 'John', 'Doe', 'jhon.doe@mail.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2,2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2,1);