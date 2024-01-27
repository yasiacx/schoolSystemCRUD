-- schooldb.course definition

CREATE TABLE `course` (
  `course_id` bigint NOT NULL AUTO_INCREMENT,
  `course_description` varchar(255) DEFAULT NULL,
  `course_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- schooldb.student definition

CREATE TABLE `student` (
  `student_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- schooldb.exam definition

CREATE TABLE `exam` (
  `exam_id` bigint NOT NULL AUTO_INCREMENT,
  `score` float DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  PRIMARY KEY (`exam_id`),
  KEY `FKiub3ue9cklcyyra24v9ns656n` (`course_id`),
  KEY `FKrrrq06h9khmxrmccek04aog52` (`student_id`),
  CONSTRAINT `FKiub3ue9cklcyyra24v9ns656n` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `FKrrrq06h9khmxrmccek04aog52` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- schooldb.student_course definition

CREATE TABLE `student_course` (
  `student_id` bigint NOT NULL,
  `course_id` bigint NOT NULL,
  PRIMARY KEY (`student_id`,`course_id`),
  KEY `FKejrkh4gv8iqgmspsanaji90ws` (`course_id`),
  CONSTRAINT `FKejrkh4gv8iqgmspsanaji90ws` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `FKq7yw2wg9wlt2cnj480hcdn6dq` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;