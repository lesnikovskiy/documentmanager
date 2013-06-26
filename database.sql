use `test`;

CREATE TABLE `documents` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(200) NOT NULL,
	`description` text NOT NULL,
	`filename` varchar(200) NOT NULL,
	`content` mediumblob NOT NULL,
	`content_type` varchar(255) NOT NULL,
	`created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
);