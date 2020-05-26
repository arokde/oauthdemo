INSERT into users(username,password,email,enabled) values ('user1','{noop}secret','user1@gmail.com',true);
INSERT into users(username,password,email,enabled) values ('user2','{noop}secret','user2@gmail.com',true);
INSERT into users(username,password,email,enabled) values ('admin','{noop}secret','admin@gmail.com',true);
INSERT into users(username,password,email,enabled) values ('demo','{noop}secret','demo@gmail.com',true);
INSERT into authorities values ('user2','USER');
INSERT into authorities values ('admin','ADMIN');
INSERT into authorities values ('user1','ADMIN');
INSERT into authorities values ('demo','ADMIN');

