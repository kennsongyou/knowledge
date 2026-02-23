CREATE DATABASE `knowledge_rag` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `dify_document` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `dify_document_id` varchar(255) NOT NULL,
                                 `document_id` varchar(255) NOT NULL,
                                 `knowledge_base_id` varchar(255) NOT NULL,
                                 `external_document_id` varchar(255) NOT NULL,
                                 `external_dataset_id` varchar(255) NOT NULL,
                                 `tenant_id` bigint NOT NULL,
                                 `deleted` tinyint NOT NULL DEFAULT '0',
                                 `created_by` varchar(255) NOT NULL,
                                 `created_at` datetime(6) NOT NULL,
                                 `updated_by` varchar(255) NOT NULL,
                                 `updated_at` datetime(6) NOT NULL,
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `dify_document_id_UNIQUE` (`dify_document_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `dify_knowledge_base` (
                                       `id` bigint NOT NULL AUTO_INCREMENT,
                                       `dify_knowledge_base_id` varchar(255) NOT NULL,
                                       `knowledge_base_id` varchar(255) NOT NULL,
                                       `external_dataset_id` varchar(255) NOT NULL,
                                       `tenant_id` bigint NOT NULL,
                                       `deleted` tinyint NOT NULL DEFAULT '0',
                                       `created_by` bigint NOT NULL,
                                       `created_at` datetime(6) NOT NULL,
                                       `updated_by` bigint NOT NULL,
                                       `updated_at` datetime(6) NOT NULL,
                                       PRIMARY KEY (`id`),
                                       UNIQUE KEY `dify_knowledge_base_id_UNIQUE` (`dify_knowledge_base_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `document` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `document_id` varchar(255) NOT NULL,
                            `original_file_name` varchar(255) NOT NULL,
                            `display_name` varchar(255) NOT NULL,
                            `extension` varchar(255) DEFAULT NULL,
                            `object_key` varchar(255) NOT NULL,
                            `blob_provider` varchar(255) NOT NULL,
                            `tenant_id` bigint NOT NULL,
                            `deleted` tinyint NOT NULL DEFAULT '0',
                            `created_by` bigint NOT NULL,
                            `created_at` datetime(6) NOT NULL,
                            `updated_by` bigint NOT NULL,
                            `updated_at` datetime(6) NOT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `document_id_UNIQUE` (`document_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `knowledge_base` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `knowledge_base_id` varchar(255) NOT NULL,
                                  `name` varchar(255) NOT NULL,
                                  `description` text,
                                  `impl` varchar(255) NOT NULL,
                                  `tenant_id` bigint NOT NULL,
                                  `deleted` tinyint NOT NULL DEFAULT '0',
                                  `created_by` bigint NOT NULL,
                                  `created_at` datetime(6) NOT NULL,
                                  `updated_by` bigint NOT NULL,
                                  `updated_at` datetime(6) NOT NULL,
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `knowledge_base_id_UNIQUE` (`knowledge_base_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `knowledge_base_document` (
                                           `id` bigint NOT NULL AUTO_INCREMENT,
                                           `knowledge_base_id` varchar(255) NOT NULL,
                                           `document_id` varchar(255) NOT NULL,
                                           `tenant_id` bigint NOT NULL,
                                           `created_by` bigint NOT NULL,
                                           `created_at` datetime(6) NOT NULL,
                                           PRIMARY KEY (`id`),
                                           UNIQUE KEY `knowledge_base_id_document_id_UNIQUE` (`knowledge_base_id`,`document_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
