CREATE TABLE `people` (
  `personId` integer UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `social_name` varchar(150),
  `birthday` date,
  `cpf` varchar(15) NOT NULL,
  `rg` varchar(15),
  `email` varchar(100),
  `phone_number` varchar(15),
  `created_at` timestamp DEFAULT (now())
);

CREATE TABLE `teachers` (
  `teacher_id` integer PRIMARY KEY,
  `teacher_internal_id` integer,
  `personId` integer,
  `created_at` timestamp DEFAULT (now())
);

CREATE TABLE `students` (
  `student_id` integer PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `typeStudent` integer(10),
  `student_internal_id` integer,
  `personId` integer,
  `created_at` timestamp DEFAULT (now())
);

CREATE TABLE `system_users` (
  `system_user_id` integer PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `login` varchar(150),
  `passwd` varchar(200),
  `account_status` boolean,
  `personId` integer,
  `created_at` timestamp DEFAULT (now())
);

CREATE TABLE `register_login` (
  `register_login_id` integer PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `tolking` varchar(200) NOT NULL,
  `experied_in` timestamp NOT NULL,
  `user_id` integer,
  `created_at` timestamp DEFAULT (now())
);

CREATE TABLE `defense` (
  `defense_id` integer PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `type_defense` integer NOT NULL,
  `defense_title` varchar(100),
  `date` timestamp DEFAULT (now()),
  `local` varchar(100) NOT NULL,
  `teacher_advisor` integer NOT NULL,
  `student_defending` integer NOT NULL,
  `final_pontuation` number,
  `status` integer,
  `observation` varchar(250)
);

CREATE TABLE `boardOfTeachers` (
  `board_of_teachers_id` integer PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `teacher` integer NOT NULL,
  `defense_id` integer NOT NULL
);

ALTER TABLE `people` ADD FOREIGN KEY (`personId`) REFERENCES `teachers` (`personId`);

ALTER TABLE `students` ADD FOREIGN KEY (`personId`) REFERENCES `people` (`personId`);

ALTER TABLE `system_users` ADD FOREIGN KEY (`personId`) REFERENCES `people` (`personId`);

ALTER TABLE `boardOfTeachers` ADD FOREIGN KEY (`defense_id`) REFERENCES `defense` (`defense_id`);

ALTER TABLE `boardOfTeachers` ADD FOREIGN KEY (`teacher`) REFERENCES `teachers` (`teacher_id`);

ALTER TABLE `register_login` ADD FOREIGN KEY (`user_id`) REFERENCES `system_users` (`system_user_id`);

ALTER TABLE `teachers` ADD FOREIGN KEY (`teacher_id`) REFERENCES `defense` (`teacher_advisor`);

ALTER TABLE `students` ADD FOREIGN KEY (`student_id`) REFERENCES `defense` (`student_defending`);
