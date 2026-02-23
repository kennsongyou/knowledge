CREATE DATABASE `knowledge_rag` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE knowledge_rag;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `knowledge_base_document` (
                                           `id` bigint NOT NULL AUTO_INCREMENT,
                                           `knowledge_base_id` varchar(255) NOT NULL,
                                           `document_id` varchar(255) NOT NULL,
                                           `tenant_id` bigint NOT NULL,
                                           `created_by` bigint NOT NULL,
                                           `created_at` datetime(6) NOT NULL,
                                           PRIMARY KEY (`id`),
                                           UNIQUE KEY `knowledge_base_id_document_id_UNIQUE` (`knowledge_base_id`,`document_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `sys_tenant_dify_register` (
                                            `id` bigint NOT NULL AUTO_INCREMENT,
                                            `sys_tenant_dify_register_id` varchar(255) NOT NULL,
                                            `tenant_id` bigint NOT NULL,
                                            `dataset_id` varchar(255) NOT NULL,
                                            `dataset_metadata` json NOT NULL,
                                            `app_id` varchar(255) NOT NULL,
                                            `app_api_key` varchar(255) NOT NULL,
                                            `deleted` tinyint NOT NULL DEFAULT '0',
                                            `created_at` datetime(6) NOT NULL,
                                            `updated_at` datetime(6) NOT NULL,
                                            PRIMARY KEY (`id`),
                                            UNIQUE KEY `sys_tenant_dify_register_id_UNIQUE` (`sys_tenant_dify_register_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `sys_tenant_dify_register` (`id`,`sys_tenant_dify_register_id`,`tenant_id`,`dataset_id`,`dataset_metadata`,`app_id`,`app_api_key`,`deleted`,`created_at`,`updated_at`) VALUES (1,'1',1,'02f16206-4b46-4071-abfc-cbef350c3a07','{\"neuron_knowledge_base_id\": \"815406af-552b-4702-803a-e2eaa4464376\"}','3a681cf4-af46-4171-be07-1d808bb7b305','app-qRYFJBjZO8tg1rQqZo4jkj7X',0,'2026-02-23 15:48:53.000000','2026-02-23 15:48:53.000000');
