INSERT INTO `user`(`id`, `login`, `password`, `name`, `email`, `phone`, `mobile`, `is_enabled`, `remarks`) VALUES (1, 'system', '$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG', 'System', 'system@localhost', NULL, NULL, 1, NULL);
INSERT INTO `user`(`id`, `login`, `password`, `name`, `email`, `phone`, `mobile`, `is_enabled`, `remarks`) VALUES (2, 'anonymoususer', '$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO', 'Anonymous', 'anonymous@localhost', NULL, NULL, 1, NULL);
INSERT INTO `user`(`id`, `login`, `password`, `name`, `email`, `phone`, `mobile`, `is_enabled`, `remarks`) VALUES (3, 'admin', '$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', 'Administrator', 'admin@localhost', NULL, NULL, 1, NULL);
INSERT INTO `user`(`id`, `login`, `password`, `name`, `email`, `phone`, `mobile`, `is_enabled`, `remarks`) VALUES (4, 'user', '$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K', 'User', 'user@localhost', NULL, NULL, 1, NULL);

INSERT INTO `role`(`id`, `code`, `name`, `remarks`) VALUES (1, 'ROLE_ADMIN', '管理员', NULL);
INSERT INTO `role`(`id`, `code`, `name`, `remarks`) VALUES (2, 'ROLE_USER', '普通用户', NULL);

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('1', '2');
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('3', '1');
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('3', '2');
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('4', '2');
