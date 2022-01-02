START TRANSACTION

DROP TABLE IF EXISTS hunting_log;
DROP TABLE IF EXISTS fishing_log;
DROP TABLE IF EXISTS scouting_report;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
        user_id SERIAL NOT NULL,
        first_name  varchar(50) NOT NULL,
        last_name   varchar(50) NOT NULL,
        username  varchar(50) UNIQUE,
        password_hash varchar(50) NOT NULL,
        CONSTRAINT pk_users PRIMARY KEY (user_id)
        );
CREATE TABLE scouting_report (
        scout_report_id  SERIAL NOT NULL,
        user_id   int NOT NULL,
        report_date varchar(50)  NULL,
        report_location  varchar(200)  NULL,
        report_time    varchar(50) NULL,
        weather  varchar(100) NULL,
        scout_description  varchar(2000) NOT NULL,
        CONSTRAINT pk_scouting_report PRIMARY KEY (scout_report_id),
        CONSTRAINT fk_scouting_report_users FOREIGN KEY (user_id) REFERENCES users(user_id)
        );
CREATE TABLE fishing_log (
        fish_log_id SERIAL NOT NULL,
        user_id int NOT NULL,
        log_date  varchar(50) NULL,
        log_location  varchar(100) NULL,
        log_description  varchar(2000) NOT NULL,
        images  varchar(5000) NULL,
        bait   varchar (500) NULL,
        weather varchar (100) NULL,
        fishing_trip   varchar(200) NULL,
        CONSTRAINT pk_fishing_log PRIMARY KEY (fish_log_id),
        CONSTRAINT fk_fishing_log_users FOREIGN KEY (user_id) REFERENCES users(user_id)
        );
CREATE TABLE hunting_log (
        hunt_log_id  SERIAL NOT NULL,
        user_id int NOT NULL,
        log_date  varchar(50) NULL,
        log_location  varchar(200) NULL,
        log_description varchar(2000) NOT NULL,
        images varchar(5000) NULL,
        weather varchar(100) NULL,
        hunting_type  varchar(200) NULL,   
        hunting_trip  varchar(200) NULL,
        CONSTRAINT pk_hunting_log PRIMARY KEY (hunt_log_id),
        CONSTRAINT fk_hunting_log_users FOREIGN KEY (user_id) REFERENCES users(user_id)
        );
        
 COMMIT;                 
                 





