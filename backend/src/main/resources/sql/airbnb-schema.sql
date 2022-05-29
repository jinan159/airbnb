# SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `accommodation`;
CREATE TABLE `accommodation` (
    `id`            BIGINT        NOT NULL AUTO_INCREMENT,
    `title`         VARCHAR(255)  NOT NULL,
    `price`         DECIMAL(10,0) NOT NULL,
    `image_url`     TEXT,
    `point`         POINT,
    `address_id`    BIGINT,
    `provides_id`   BIGINT,
    PRIMARY KEY (id)
) engine=InnoDB;

DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
    `id`            BIGINT NOT NULL AUTO_INCREMENT,
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
    `capacity`              TINYINT     NOT NULL,
    `bed_rooms`             TINYINT,
    `beds`                  TINYINT,
    `wash_rooms`            TINYINT,
    `has_air_conditioner`   BOOLEAN,
    `has_hair_dryer`        BOOLEAN,
    `has_kitchin`           BOOLEAN,
    `has_wifi`               BOOLEAN,
    primary key (id)
) engine=InnoDB;

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation` (
    `id`                BIGINT      NOT NULL AUTO_INCREMENT,
    `visitors`          TINYINT     NOT NULL,
    `start_date`        TIMESTAMP   NOT NULL,
    `end_date`          TIMESTAMP   NOT NULL,
    `accommodation_id`  BIGINT      NOT NULL,
    `member_id`         BIGINT      NOT NULL,
    PRIMARY KEY (id)
) engine=InnoDB;

DROP TABLE IF EXISTS `wish`;
CREATE TABLE `wish` (
    `id`                BIGINT  NOT NULL AUTO_INCREMENT,
    `accommodation_id`  BIGINT  NOT NULL,
    `member_id`         BIGINT  NOT NULL,
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
