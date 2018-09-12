create table `access_tokens` (
	`token_id` varchar (64) NOT NULL PRIMARY KEY,
	`token` blob ,
	`authentication_id` varchar (765) UNIQUE NOT NULL,
	`user_id` varchar (64),
	`client_id` varchar (64) NOT NULL,
	`authentication` blob ,
	`refresh_token_id` varchar (64),
  	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP NOT NULL
); 

CREATE TABLE `client_details` (
  `client_id` varchar(765) NOT NULL PRIMARY KEY,
  `resource_ids` varchar(765) NOT NULL,
  `client_secret` varchar(765) NOT NULL,
  `scopes` varchar(765) NOT NULL,
  `authorized_grant_types` varchar(765) NOT NULL,
  `web_server_redirect_uri` varchar(765) DEFAULT NULL,
  `authorities` varchar(765) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(12288) DEFAULT NULL,
  `autoapprove` tinyint(1) DEFAULT '0',
  `creator` varchar(765) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modifior` varchar(765) NOT NULL,
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
);

