-- ============================================================
-- Hands on 2 (Section 2): Create Stock Table
-- Run in MySQL Workbench or CLI:
--   mysql -u root -p ormlearn < stock-setup.sql
-- ============================================================

USE ormlearn;

-- Create stock table
CREATE TABLE IF NOT EXISTS `ormlearn`.`stock` (
    `st_id`     INT          NOT NULL AUTO_INCREMENT,
    `st_code`   VARCHAR(10),
    `st_date`   DATE,
    `st_open`   NUMERIC(10,2),
    `st_close`  NUMERIC(10,2),
    `st_volume` NUMERIC,
    PRIMARY KEY (`st_id`)
);

-- ============================================================
-- NOTE: The actual stock data (750+ rows of FB/GOOGL/NFLX)
-- from finance.yahoo.com (Oct 2018 - Oct 2019) should be
-- populated by executing stock-data.sql from the
-- spring-data-jpa-files folder provided by Cognizant.
--
-- To load the data:
--   mysql -u root -p
--   mysql> use ormlearn;
--   mysql> source D:\spring-data-jpa-files\stock-data.sql
--
-- The stock-data.csv file contains data for:
--   FB    - Facebook
--   GOOGL - Google
--   NFLX  - Netflix
-- Period: 18 Oct 2018 to 17 Oct 2019
-- ============================================================

-- Sample records for testing (replace with full dataset)
INSERT IGNORE INTO stock (st_code, st_date, st_open, st_close, st_volume)
VALUES ('FB', '2019-09-03', 184.00, 182.39, 9779400);

INSERT IGNORE INTO stock (st_code, st_date, st_open, st_close, st_volume)
VALUES ('FB', '2019-09-04', 184.65, 187.14, 11308000);

INSERT IGNORE INTO stock (st_code, st_date, st_open, st_close, st_volume)
VALUES ('GOOGL', '2019-04-29', 1280.51, 1296.20, 3618400);

INSERT IGNORE INTO stock (st_code, st_date, st_open, st_close, st_volume)
VALUES ('NFLX', '2018-12-24', 242.00, 233.88, 9547600);

INSERT IGNORE INTO stock (st_code, st_date, st_open, st_close, st_volume)
VALUES ('FB', '2019-01-31', 165.60, 166.69, 77233600);
