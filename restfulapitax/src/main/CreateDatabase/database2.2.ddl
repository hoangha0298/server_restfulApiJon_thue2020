CREATE TABLE mediumDeclareAndBill (
  taxBillid    bigint(20) NOT NULL, 
  declareTaxid bigint(20) NOT NULL);
CREATE TABLE declareTax (
  id                   bigint(20) AUTO_INCREMENT NOT NULL, 
  taxPeriod            date NOT NULL, 
  times                tinyint NOT NULL, 
  fax                  varchar(30), 
  totalIncome          bigint(20) NOT NULL, 
  minusYourSefl        bigint(20) NOT NULL, 
  minusDependentPerson bigint(20), 
  minusCharity         bigint(20), 
  minusInsurrance      bigint(20), 
  dateCreate           date NOT NULL, 
  taxCode              bigint(20) NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE taxpayers (
  taxCode        bigint(20) NOT NULL, 
  passwork       varchar(20) NOT NULL, 
  email          varchar(40) NOT NULL, 
  startDay       date NOT NULL, 
  endDay         date, 
  taxAuthorities varchar(50) NOT NULL, 
  bank           varchar(50) NOT NULL, 
  idAccountBank  bigint(20) NOT NULL, 
  description    varchar(70), 
  idCard         bigint(20) NOT NULL, 
  name           varchar(20) NOT NULL, 
  dateOfBirth    date, 
  sex            tinyint, 
  address        varchar(20) NOT NULL, 
  numberPhone    bigint(20) NOT NULL, 
  PRIMARY KEY (taxCode));
CREATE TABLE taxBill (
  id                  bigint(20) AUTO_INCREMENT NOT NULL, 
  paymentDate         date NOT NULL, 
  excessCashOrMissing bigint(20), 
  PRIMARY KEY (id));
ALTER TABLE mediumDeclareAndBill ADD CONSTRAINT FKmediumDecl946802 FOREIGN KEY (taxBillid) REFERENCES taxBill (id);
ALTER TABLE declareTax ADD CONSTRAINT FKdeclareTax266744 FOREIGN KEY (taxCode) REFERENCES taxpayers (taxCode);
ALTER TABLE mediumDeclareAndBill ADD CONSTRAINT FKmediumDecl6601 FOREIGN KEY (declareTaxid) REFERENCES declareTax (id);
