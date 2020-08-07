CREATE DATABASE library;
USE library;

CREATE TABLE IF NOT EXISTS authors (
  idauthor INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  surname VARCHAR(45) NOT NULL,
  PRIMARY KEY (idauthor))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS books (
  idbook INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  idauthor INT NOT NULL,
  publicationdate DATE NULL,
  amount INT NULL,
  PRIMARY KEY (idbook), 
  CONSTRAINT idAuthorFk
    FOREIGN KEY (idauthor)
    REFERENCES authors (idauthor)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS users (
  idUser INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  surname VARCHAR(45) NOT NULL,
  adress VARCHAR(45) NULL,
  phone VARCHAR(45) NULL,
  PRIMARY KEY (idUser))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS abonementCards (
  idCard INT NOT NULL AUTO_INCREMENT,
  idUser INT NOT NULL,
  createDate DATE NOT NULL,
  PRIMARY KEY (idCard),  
  CONSTRAINT idUserFkCd
    FOREIGN KEY (idUser)
    REFERENCES users (idUser)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS extradition (
  idextradition INT NOT NULL AUTO_INCREMENT,
  idbook INT NOT NULL,
  idcard INT NOT NULL,
  dateExtradition DATE NOT NULL,
  dateDelivery DATE NULL,
  PRIMARY KEY (idextradition),  
  CONSTRAINT idCardFkEx
    FOREIGN KEY (idcard)
    REFERENCES abonementCards (idCard)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT idBookFkEx
    FOREIGN KEY (idbook)
    REFERENCES books (idbook)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

INSERT INTO authors (idauthor, name, surname) VALUES (1, 'George', ' Martin');
INSERT INTO authors (idauthor, name, surname) VALUES (2, 'John', 'Tolkien');
INSERT INTO authors (idauthor, name, surname) VALUES (3, 'Andrzej', 'Sapkowski');
INSERT INTO authors (idauthor, name, surname) VALUES (4, 'Richard', 'Mateson');
INSERT INTO authors (idauthor, name, surname) VALUES (5, 'Joanne', 'Rowling');

INSERT INTO books (idbook, name, idauthor, publicationdate, amount) VALUES (123, 'A Game of Thrones', 1, '2017-02-04', 2);
INSERT INTO books (idbook, name, idauthor, publicationdate, amount) VALUES (124, 'Lord of the Rings', 2, '2015-04-10', 1);
INSERT INTO books (idbook, name, idauthor, publicationdate, amount) VALUES (125, 'A Storm of Swords', 1, '2014-10-07', 3);
INSERT INTO books (idbook, name, idauthor, publicationdate, amount) VALUES (126, 'The Witcher', 3, '2010-11-05', 5);
INSERT INTO books (idbook, name, idauthor, publicationdate, amount) VALUES (127, 'I am a legend', 4, '2016-09-16', 7);

INSERT INTO users (idUser, name, surname, adress, phone) VALUES (1, 'Ivan', 'Ivanov', 'st.Lenina', '7863445');
INSERT INTO users (idUser, name, surname, adress, phone) VALUES (2, 'Jon', 'Snow', 'North', '');
INSERT INTO users (idUser, name, surname, adress, phone) VALUES (3, 'Arya', 'Stark ', 'Winterfell', '');
INSERT INTO users (idUser, name, surname, adress, phone) VALUES (4, 'Maks', 'Sidorenko', 'st.Lugovaya', '3245421');
INSERT INTO users (idUser, name, surname, adress, phone) VALUES (5, 'Fedor', 'Teplikov', 'st.Lenina', '7234343');

INSERT INTO abonementCards (idCard, idUser, createDate) VALUES (11, 1, '2019-07-12');
INSERT INTO abonementCards (idCard, idUser, createDate) VALUES (12, 2, '2019-08-01');
INSERT INTO abonementCards (idCard, idUser, createDate) VALUES (13, 3, '2019-08-22');
INSERT INTO abonementCards (idCard, idUser, createDate) VALUES (14, 4, '2019-06-24');
INSERT INTO abonementCards (idCard, idUser, createDate) VALUES (15, 5, '2019-10-01');

INSERT INTO extradition (idextradition, idbook, idcard, dateExtradition) VALUES ('1', '123', '11', '2020-02-04');
INSERT INTO extradition (idextradition, idbook, idcard, dateExtradition, dateDelivery) VALUES ('2', '123', '13', '2019-11-08', '2020-02-05');
INSERT INTO extradition (idextradition, idbook, idcard, dateExtradition, dateDelivery) VALUES ('3', '124', '12', '2019-10-22', '2019-11-20');
INSERT INTO extradition (idextradition, idbook, idcard, dateExtradition, dateDelivery) VALUES ('4', '126', '13', '2020-01-17', '2020-02-10');
INSERT INTO extradition (idextradition, idbook, idcard, dateExtradition, dateDelivery) VALUES ('5', '126', '14', '2020-01-15', '2020-02-21');
