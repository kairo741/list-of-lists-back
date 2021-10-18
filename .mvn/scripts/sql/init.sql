INSERT INTO `role` (`active`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`, `name`)
VALUES (b'1', 'Admin', '2021-08-24 21:59:17', 'Admin', '2021-08-24 21:59:20', 'ROLE_ADMIN');

INSERT INTO `role` (`active`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`, `name`)
VALUES (b'1', 'Admin', '2021-08-24 21:59:17', 'Admin', '2021-08-24 21:59:20', 'ROLE_USER');

INSERT INTO `role` (`active`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`, `name`)
VALUES (b'1', 'Admin', '2021-08-24 21:59:17', 'Admin', '2021-08-24 21:59:20', 'ROLE_MODERATOR');

INSERT INTO `user` (`active`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`, `email`,
                    `password`, `username`, `name`)
VALUES (b'1', 'Admin', '2021-08-24 21:59:48', 'Admin', '2021-08-24 21:59:50', 'kairo@email.com',
        '', 'kairo', 'Kairo');

INSERT INTO `user_role` (`user_id`, `role_id`)
VALUES ('1', '1');


