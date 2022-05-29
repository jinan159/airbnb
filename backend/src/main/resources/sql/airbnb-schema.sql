# SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `accommodation`;
CREATE TABLE `accommodation` (
    `id`            BIGINT      NOT NULL AUTO_INCREMENT,
    `image_url`     TEXT,
    `point`         POINT,
    `title`         VARCHAR(255) NOT NULL,
    `address_id`    BIGINT,
    `provides_id`   BIGINT,
    PRIMARY KEY (id)
) engine=InnoDB;

DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
    `id`            BIGINT NOT  NULL AUTO_INCREMENT,
    `city`          VARCHAR(20),
    `sigungu`       VARCHAR(20),
    `street`        VARCHAR(80),
    PRIMARY KEY (id)
) engine=InnoDB;

DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
    `id`    BIGINT          NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(255)    NOT NULL,
    `name`  VARCHAR(255),
    PRIMARY KEY (id)
) engine=InnoDB;

DROP TABLE IF EXISTS `provides`;
CREATE TABLE `provides` (
    `id`                    BIGINT      NOT NULL AUTO_INCREMENT,
    `bed_rooms`             INTEGER,
    `beds`                  INTEGER,
    `capacity`              INTEGER     NOT NULL,
    `has_air_conditioner`   BOOLEAN,
    `has_hair_dryer`        BOOLEAN,
    `has_kitchin`           BOOLEAN,
    `has_wifi`               BOOLEAN,
    `wash_rooms`            INTEGER,
    primary key (id)
) engine=InnoDB;

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation` (
    `id`                BIGINT      NOT NULL AUTO_INCREMENT,
    `end_date`          TIMESTAMP,
    `start_date`        TIMESTAMP,
    `visitors`          INTEGER     NOT NULL,
    `accommodation_id`  BIGINT,
    `member_id`         BIGINT,
    PRIMARY KEY (id)
) engine=InnoDB;

DROP TABLE IF EXISTS `wish`;
CREATE TABLE `wish` (
    `id`                BIGINT  NOT NULL AUTO_INCREMENT,
    `wished`            BOOLEAN NOT NULL,
    `accommodation_id`  BIGINT,
    `member_id`         BIGINT,
    PRIMARY KEY (id)
) engine=InnoDB;

-- FK
# ALTER TABLE `accommodation` ADD CONSTRAINT `fk_accommodation_address` FOREIGN KEY (address_id) REFERENCES `address` (id);
# ALTER TABLE `accommodation` ADD CONSTRAINT `fk_accommodation_provides` FOREIGN KEY (provides_id) REFERENCES `provides` (id);
# ALTER TABLE `reservation` ADD CONSTRAINT `fk_reservation_accommodation` FOREIGN KEY (accommodation_id) REFERENCES `accommodation` (id);
# ALTER TABLE `reservation` ADD CONSTRAINT `fk_reservation_member` FOREIGN KEY (member_id) REFERENCES `member` (id);
# ALTER TABLE `wish` ADD CONSTRAINT `fk_wish_accommodation` FOREIGN KEY (accommodation_id) REFERENCES `accommodation` (id);
# ALTER TABLE `wish` ADD CONSTRAINT `fk_wish_member` FOREIGN KEY (member_id) REFERENCES `member` (id);
#
# SET FOREIGN_KEY_CHECKS = 1;
