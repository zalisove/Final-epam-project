-- MySQL Script generated by MySQL Workbench
-- Thu May 13 19:31:18 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


-- DROP SCHEMA IF EXISTS `testing_bd` ;
DROP TABLE IF EXISTS `final_project_bd`.`complexity` ;
DROP TABLE IF EXISTS `final_project_bd`.`subject` ;
DROP TABLE IF EXISTS `final_project_bd`.`test` ;
DROP TABLE IF EXISTS `final_project_bd`.`question_type` ;
DROP TABLE IF EXISTS `final_project_bd`.`question` ;
DROP TABLE IF EXISTS `final_project_bd`.`answer` ;
DROP TABLE IF EXISTS `final_project_bd`.`roles` ;
DROP TABLE IF EXISTS `final_project_bd`.`user` ;
DROP TABLE IF EXISTS `final_project_bd`.`user_has_test` ;


CREATE SCHEMA IF NOT EXISTS `final_project_bd` DEFAULT CHARACTER SET utf8 ;
USE `final_project_bd` ;



CREATE TABLE IF NOT EXISTS `final_project_bd`.`complexity` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));



CREATE TABLE IF NOT EXISTS `final_project_bd`.`subject` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));



CREATE TABLE IF NOT EXISTS `final_project_bd`.`test` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(1024) NOT NULL,
  `time` INT UNSIGNED NOT NULL,
  `requests_number` INT UNSIGNED NULL DEFAULT 0,
  `complexity_id` INT NOT NULL,
  `subject_id` INT NOT NULL,
  PRIMARY KEY (`id`, `complexity_id`, `subject_id`),
  INDEX `fk_test_complexity1_idx` (`complexity_id` ASC) ,
  INDEX `fk_test_subject1_idx` (`subject_id` ASC) ,
  CONSTRAINT `fk_test_complexity1`
    FOREIGN KEY (`complexity_id`)
    REFERENCES `final_project_bd`.`complexity` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_test_subject1`
    FOREIGN KEY (`subject_id`)
    REFERENCES `final_project_bd`.`subject` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);



CREATE TABLE IF NOT EXISTS `final_project_bd`.`question_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));



CREATE TABLE IF NOT EXISTS `final_project_bd`.`question` (
  `id` INT NOT NULL auto_increment,
  `name` VARCHAR(1024) NOT NULL,
  `test_id` INT NOT NULL,
  `question_type_id` INT NOT NULL,
  PRIMARY KEY (`id`, `test_id`, `question_type_id`),
  INDEX `fk_question_test_idx` (`test_id` ASC) ,
  INDEX `fk_question_question_type1_idx` (`question_type_id` ASC) ,
  CONSTRAINT `fk_question_test`
    FOREIGN KEY (`test_id`)
    REFERENCES `final_project_bd`.`test` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_question_question_type1`
    FOREIGN KEY (`question_type_id`)
    REFERENCES `final_project_bd`.`question_type` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);



CREATE TABLE IF NOT EXISTS `final_project_bd`.`answer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(512) NOT NULL,
  `true_answer` TINYINT NOT NULL DEFAULT 0,
  `question_id` INT NOT NULL,
  PRIMARY KEY (`id`, `question_id`),
  INDEX `fk_answer_question1_idx` (`question_id` ASC) ,
  CONSTRAINT `fk_answer_question1`
    FOREIGN KEY (`question_id`)
    REFERENCES `final_project_bd`.`question` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);



CREATE TABLE IF NOT EXISTS `final_project_bd`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));



CREATE TABLE IF NOT EXISTS `final_project_bd`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(40) NOT NULL,
  `name` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `roles_id` INT NOT NULL,
  `is_blocked` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`id`, `roles_id`),
  INDEX `fk_user_roles1_idx` (`roles_id` ASC) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  CONSTRAINT `fk_user_roles1`
    FOREIGN KEY (`roles_id`)
    REFERENCES `final_project_bd`.`roles` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);



CREATE TABLE IF NOT EXISTS `final_project_bd`.`user_has_test` (
  `user_id` INT NOT NULL,
  `test_id` INT NOT NULL,
  `mark` DECIMAL(5,2) NOT NULL,
  `writing_time` DATE NOT NULL,
  PRIMARY KEY (`user_id`, `test_id`),
  INDEX `fk_user_has_test_test1_idx` (`test_id` ASC) ,
  INDEX `fk_user_has_test_user1_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_user_has_test_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `final_project_bd`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_test_test1`
    FOREIGN KEY (`test_id`)
    REFERENCES `final_project_bd`.`test` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
