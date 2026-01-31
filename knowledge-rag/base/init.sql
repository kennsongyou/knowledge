CREATE DATABASE `knowledge_rag` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `chat_message` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `chat_message_id` varchar(255) NOT NULL,
    `conversation_id` varchar(255) NOT NULL,
    `role` varchar(255) NOT NULL,
    `content` text,
    `deleted_at` datetime(6) DEFAULT NULL,
    `created_by` bigint NOT NULL,
    `created_at` datetime(6) NOT NULL,
    `updated_by` bigint NOT NULL,
    `updated_at` datetime(6) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `chat_message_id_UNIQUE` (`chat_message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `conversation` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `conversation_id` varchar(255) NOT NULL,
                                `name` varchar(255) DEFAULT NULL,
                                `dify_conversation_id` varchar(255) NOT NULL,
                                `deleted_at` datetime(6) DEFAULT NULL,
                                `created_by` bigint NOT NULL,
                                `created_at` datetime(6) NOT NULL,
                                `updated_by` bigint NOT NULL,
                                `updated_at` datetime(6) NOT NULL,
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `conversation_id_UNIQUE` (`conversation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `dify_app_dsl_config` (
                                       `id` bigint NOT NULL AUTO_INCREMENT,
                                       `type` varchar(255) NOT NULL,
                                       `version` varchar(255) NOT NULL,
                                       `param_schema` json NOT NULL,
                                       `yaml_template` text NOT NULL,
                                       `deleted_at` datetime(6) DEFAULT NULL,
                                       `created_by` bigint NOT NULL,
                                       `created_at` datetime(6) NOT NULL,
                                       `updated_by` bigint NOT NULL,
                                       `updated_at` datetime(6) NOT NULL,
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `document` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `document_id` varchar(255) NOT NULL,
                            `name` varchar(255) NOT NULL,
                            `ext` varchar(255) NOT NULL,
                            `knowledge_base_id` varchar(255) NOT NULL,
                            `dify_document_id` varchar(255) NOT NULL,
                            `dify_dataset_id` varchar(255) NOT NULL,
                            `deleted_at` datetime(6) DEFAULT NULL,
                            `created_by` bigint NOT NULL,
                            `created_at` datetime(6) NOT NULL,
                            `updated_by` bigint NOT NULL,
                            `updated_at` datetime(6) NOT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `document_id_UNIQUE` (`document_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `knowledge_base` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `knowledge_base_id` varchar(255) NOT NULL,
                                  `name` varchar(255) NOT NULL,
                                  `description` text,
                                  `dify_dataset_id` varchar(255) NOT NULL,
                                  `dify_app_id` varchar(255) NOT NULL,
                                  `dify_api_key` varchar(255) NOT NULL,
                                  `tenant_id` bigint NOT NULL,
                                  `deleted_at` datetime(6) DEFAULT NULL,
                                  `created_by` bigint NOT NULL,
                                  `created_at` datetime(6) NOT NULL,
                                  `updated_by` bigint NOT NULL,
                                  `updated_at` datetime(6) NOT NULL,
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `knowledge_base_id_UNIQUE` (`knowledge_base_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
