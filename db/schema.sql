/*
 Navicat Premium Data Transfer

 Source Server         : MyTicketServiceDB
 Source Server Type    : PostgreSQL
 Source Server Version : 160003 (160003)
 Source Host           : localhost:5432
 Source Catalog        : ticket_to_ride
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 160003 (160003)
 File Encoding         : 65001

 Date: 21/07/2024 20:12:11
*/


-- ----------------------------
-- Sequence structure for routes_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."routes_id_seq";
CREATE SEQUENCE "public"."routes_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for segments_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."segments_id_seq";
CREATE SEQUENCE "public"."segments_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for tickets_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."tickets_id_seq";
CREATE SEQUENCE "public"."tickets_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for towns_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."towns_id_seq";
CREATE SEQUENCE "public"."towns_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for travellers_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."travellers_id_seq";
CREATE SEQUENCE "public"."travellers_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Table structure for routes
-- ----------------------------
DROP TABLE IF EXISTS "public"."routes";
CREATE TABLE "public"."routes" (
  "id" int8 NOT NULL DEFAULT nextval('routes_id_seq'::regclass),
  "start_town_id" int8 NOT NULL,
  "end_town_id" int8 NOT NULL,
  "segments_count" int4 NOT NULL
)
;

-- ----------------------------
-- Records of routes
-- ----------------------------
INSERT INTO "public"."routes" VALUES (1, 6, 1, 5);
INSERT INTO "public"."routes" VALUES (2, 6, 2, 7);
INSERT INTO "public"."routes" VALUES (3, 6, 3, 5);
INSERT INTO "public"."routes" VALUES (4, 6, 4, 4);
INSERT INTO "public"."routes" VALUES (5, 6, 5, 2);
INSERT INTO "public"."routes" VALUES (6, 6, 7, 1);
INSERT INTO "public"."routes" VALUES (7, 7, 1, 6);
INSERT INTO "public"."routes" VALUES (8, 7, 2, 6);
INSERT INTO "public"."routes" VALUES (9, 7, 3, 4);
INSERT INTO "public"."routes" VALUES (10, 7, 4, 5);
INSERT INTO "public"."routes" VALUES (11, 7, 5, 3);
INSERT INTO "public"."routes" VALUES (12, 5, 1, 3);
INSERT INTO "public"."routes" VALUES (13, 5, 2, 6);
INSERT INTO "public"."routes" VALUES (14, 5, 3, 6);
INSERT INTO "public"."routes" VALUES (15, 5, 4, 2);
INSERT INTO "public"."routes" VALUES (16, 4, 1, 1);
INSERT INTO "public"."routes" VALUES (17, 4, 2, 4);
INSERT INTO "public"."routes" VALUES (18, 4, 3, 5);
INSERT INTO "public"."routes" VALUES (19, 3, 1, 4);
INSERT INTO "public"."routes" VALUES (20, 3, 2, 2);
INSERT INTO "public"."routes" VALUES (21, 2, 1, 3);

-- ----------------------------
-- Table structure for segments
-- ----------------------------
DROP TABLE IF EXISTS "public"."segments";
CREATE TABLE "public"."segments" (
  "id" int8 NOT NULL DEFAULT nextval('segments_id_seq'::regclass),
  "from_city" varchar(255) COLLATE "pg_catalog"."default",
  "to_city" varchar(255) COLLATE "pg_catalog"."default",
  "distance" int4
)
;

-- ----------------------------
-- Records of segments
-- ----------------------------
INSERT INTO "public"."segments" VALUES (1, 'Birgmingham', 'Bristol', 3);
INSERT INTO "public"."segments" VALUES (2, 'Bristol', 'Swindon', 2);
INSERT INTO "public"."segments" VALUES (3, 'Swindon', 'Birgmingham', 4);
INSERT INTO "public"."segments" VALUES (4, 'Birgmingham', 'Coventry', 1);
INSERT INTO "public"."segments" VALUES (5, 'Coventry', 'Northhampton', 2);
INSERT INTO "public"."segments" VALUES (6, 'Northhampton', 'London', 2);
INSERT INTO "public"."segments" VALUES (7, 'London', 'Reading', 1);
INSERT INTO "public"."segments" VALUES (8, 'Reading', 'Swindon', 4);

-- ----------------------------
-- Table structure for tickets
-- ----------------------------
DROP TABLE IF EXISTS "public"."tickets";
CREATE TABLE "public"."tickets" (
  "id" int8 NOT NULL DEFAULT nextval('tickets_id_seq'::regclass),
  "traveller_id" int8,
  "departure_town_id" int8,
  "arrival_town_id" int8,
  "segments_count" int4 NOT NULL,
  "price" numeric(38,2) NOT NULL,
  "currency" varchar(255) COLLATE "pg_catalog"."default" DEFAULT 'GBP'::character varying,
  "purchase_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;

-- ----------------------------
-- Records of tickets
-- ----------------------------
INSERT INTO "public"."tickets" VALUES (5, 1, 6, 2, 7, 25.00, 'GBP', '2024-07-19 20:29:05.931241');
INSERT INTO "public"."tickets" VALUES (6, 1, 6, 2, 7, 25.00, 'GBP', '2024-07-19 20:55:42.27725');
INSERT INTO "public"."tickets" VALUES (7, 1, 4, 2, 4, 15.00, 'GBP', '2024-07-19 20:55:42.313417');
INSERT INTO "public"."tickets" VALUES (8, 1, 6, 3, 5, 17.00, 'GBP', '2024-07-19 20:55:42.322437');
INSERT INTO "public"."tickets" VALUES (9, 1, 6, 2, 7, 25.00, 'GBP', '2024-07-19 20:55:53.169768');
INSERT INTO "public"."tickets" VALUES (10, 1, 4, 2, 4, 15.00, 'GBP', '2024-07-20 14:08:52.012963');

-- ----------------------------
-- Table structure for towns
-- ----------------------------
DROP TABLE IF EXISTS "public"."towns";
CREATE TABLE "public"."towns" (
  "id" int8 NOT NULL DEFAULT nextval('towns_id_seq'::regclass),
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Records of towns
-- ----------------------------
INSERT INTO "public"."towns" VALUES (1, 'Birgmingham');
INSERT INTO "public"."towns" VALUES (2, 'Bristol');
INSERT INTO "public"."towns" VALUES (3, 'Swindon');
INSERT INTO "public"."towns" VALUES (4, 'Coventry');
INSERT INTO "public"."towns" VALUES (5, 'Northhampton');
INSERT INTO "public"."towns" VALUES (6, 'London');
INSERT INTO "public"."towns" VALUES (7, 'Reading');

-- ----------------------------
-- Table structure for travellers
-- ----------------------------
DROP TABLE IF EXISTS "public"."travellers";
CREATE TABLE "public"."travellers" (
  "id" int8 NOT NULL DEFAULT nextval('travellers_id_seq'::regclass),
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "email" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "balance" numeric(38,2) NOT NULL
)
;

-- ----------------------------
-- Records of travellers
-- ----------------------------
INSERT INTO "public"."travellers" VALUES (1, 'John Doe', 'johndoe@example.com', 3.00);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."routes_id_seq"
OWNED BY "public"."routes"."id";
SELECT setval('"public"."routes_id_seq"', 21, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."segments_id_seq"
OWNED BY "public"."segments"."id";
SELECT setval('"public"."segments_id_seq"', 8, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."tickets_id_seq"
OWNED BY "public"."tickets"."id";
SELECT setval('"public"."tickets_id_seq"', 10, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."towns_id_seq"
OWNED BY "public"."towns"."id";
SELECT setval('"public"."towns_id_seq"', 7, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."travellers_id_seq"
OWNED BY "public"."travellers"."id";
SELECT setval('"public"."travellers_id_seq"', 2, true);

-- ----------------------------
-- Primary Key structure for table routes
-- ----------------------------
ALTER TABLE "public"."routes" ADD CONSTRAINT "routes_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table segments
-- ----------------------------
ALTER TABLE "public"."segments" ADD CONSTRAINT "segments_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table tickets
-- ----------------------------
ALTER TABLE "public"."tickets" ADD CONSTRAINT "tickets_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table towns
-- ----------------------------
ALTER TABLE "public"."towns" ADD CONSTRAINT "towns_name_key" UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table towns
-- ----------------------------
ALTER TABLE "public"."towns" ADD CONSTRAINT "towns_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table travellers
-- ----------------------------
ALTER TABLE "public"."travellers" ADD CONSTRAINT "travellers_email_key" UNIQUE ("email");

-- ----------------------------
-- Primary Key structure for table travellers
-- ----------------------------
ALTER TABLE "public"."travellers" ADD CONSTRAINT "travellers_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Keys structure for table routes
-- ----------------------------
ALTER TABLE "public"."routes" ADD CONSTRAINT "routes_end_town_id_fkey" FOREIGN KEY ("end_town_id") REFERENCES "public"."towns" ("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."routes" ADD CONSTRAINT "routes_start_town_id_fkey" FOREIGN KEY ("start_town_id") REFERENCES "public"."towns" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table tickets
-- ----------------------------
ALTER TABLE "public"."tickets" ADD CONSTRAINT "tickets_arrival_town_id_fkey" FOREIGN KEY ("arrival_town_id") REFERENCES "public"."towns" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."tickets" ADD CONSTRAINT "tickets_departure_town_id_fkey" FOREIGN KEY ("departure_town_id") REFERENCES "public"."towns" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."tickets" ADD CONSTRAINT "tickets_traveller_id_fkey" FOREIGN KEY ("traveller_id") REFERENCES "public"."travellers" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
