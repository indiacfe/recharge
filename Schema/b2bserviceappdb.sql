/*
SQLyog Community Edition- MySQL GUI v8.14 
MySQL - 5.1.36-community : Database - b2bserviceappdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`b2bserviceappdb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `b2bserviceappdb`;

/*Table structure for table `baseentity` */

DROP TABLE IF EXISTS `baseentity`;

CREATE TABLE `baseentity` (
  `DTYPE` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `agentid` varchar(255) DEFAULT NULL,
  `api_version` double DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `loginstatus` varchar(255) DEFAULT NULL,
  `thirpartyprovidername` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `baseentity` */

/*Table structure for table `circles` */

DROP TABLE IF EXISTS `circles`;

CREATE TABLE `circles` (
  `ID` int(40) NOT NULL AUTO_INCREMENT,
  `CIRCLE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `CIRCLE` (`CIRCLE`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

/*Data for the table `circles` */

insert  into `circles`(`ID`,`CIRCLE`) values (1,'ALL'),(24,'ANDHRA PRADESH'),(2,'ASSAM'),(3,'BIHAR'),(4,'CHENNAI'),(5,'DELHI'),(6,'GUJARAT'),(7,'HARYANA'),(8,'HIMACHAL PRADESH'),(9,'JAMMU AND KASHMIR'),(10,'KARNATAKA'),(11,'KERALA'),(12,'KOLKATA'),(13,'MADHYA PRADESH'),(14,'MAHARASHTRA'),(15,'MUMBAI'),(16,'NORTH EAST INDIA'),(17,'ORISSA'),(18,'PUNJAB'),(19,'RAJASTHAN'),(20,'TAMIL NADU'),(21,'UTTAR PRADESH EAST'),(22,'UTTAR PRADESH WEST'),(23,'WEST BENGAL AND ANDAMAN & NICOBAR ISLANDS');

/*Table structure for table `company_distributor_transaction_log` */

DROP TABLE IF EXISTS `company_distributor_transaction_log`;

CREATE TABLE `company_distributor_transaction_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `distributor_id` bigint(20) NOT NULL,
  `transfer_amount` double NOT NULL,
  `transfer_type` varchar(50) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `transaction_id` varchar(50) NOT NULL,
  `pre_balance` double DEFAULT NULL,
  `new_balance` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

/*Data for the table `company_distributor_transaction_log` */

insert  into `company_distributor_transaction_log`(`id`,`distributor_id`,`transfer_amount`,`transfer_type`,`created_at`,`transaction_id`,`pre_balance`,`new_balance`) values (15,101,1000,'DEBIT','2013-09-09 17:44:26','COMPTODIST130909174426',61115,60115),(16,101,1000,'DEBIT','2013-09-09 17:45:30','COMPTODIST130909174530',60115,59115),(17,101,1000,'DEBIT','2013-09-09 18:15:42','COMPTODIST130909181542',59115,58115),(18,101,1000,'DEBIT','2013-09-09 18:19:46','COMPTODIST130909181946',58115,57115);

/*Table structure for table `companybalance` */

DROP TABLE IF EXISTS `companybalance`;

CREATE TABLE `companybalance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `companyname` varchar(100) NOT NULL,
  `currbalance` double NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `companybalance` */

insert  into `companybalance`(`id`,`companyname`,`currbalance`,`created_at`) values (1,'Cybertel',57115,'2013-09-04 13:01:07');

/*Table structure for table `companyoperatorcomission` */

DROP TABLE IF EXISTS `companyoperatorcomission`;

CREATE TABLE `companyoperatorcomission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `retailercommision` double NOT NULL,
  `operator_name` varchar(30) DEFAULT NULL,
  `recharge_type` varchar(30) DEFAULT NULL,
  `thirdparty_service_provider` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1;

/*Data for the table `companyoperatorcomission` */

insert  into `companyoperatorcomission`(`id`,`retailercommision`,`operator_name`,`recharge_type`,`thirdparty_service_provider`) values (4,2,'AIRTEL','MOBILE_PREPAID','payworld'),(5,2,'AIRCEL','MOBILE_PREPAID','instantpay'),(6,2,'BSNL - SPECIAL TARIFF','MOBILE_PREPAID','instantpay'),(7,2,'BSNL - TALKTIME','MOBILE_PREPAID','instantpay'),(8,2,'IDEA','MOBILE_PREPAID','instantpay'),(9,2,'LOOP MOBILE','MOBILE_PREPAID','instantpay'),(10,2,'MTNL-SPECIAL TARIFF','MOBILE_PREPAID','instantpay'),(11,2,'MTNL_TALKTIME','MOBILE_PREPAID','instantpay'),(12,2,'MTS','MOBILE_PREPAID','instantpay'),(13,2,'RELIANCE','MOBILE_PREPAID','instantpay'),(14,2,'T24 MOBILE-SPECIAL TARIFF','MOBILE_PREPAID','instantpay'),(15,2,'T24 MOBILE-TALKTIME','MOBILE_PREPAID','instantpay'),(16,2,'TATA DOCOMO CDMA','MOBILE_PREPAID','instantpay'),(17,2,'TATA DOCOMO GSM-SPECIAL T','MOBILE_PREPAID','instantpay'),(18,2,'TATA DOCOMO GSM-TALKTIME','MOBILE_PREPAID','instantpay'),(19,2,'UNINOR-SPECIAL TARIFF','MOBILE_PREPAID','instantpay'),(20,2,'UNINOR-TALKTIME','MOBILE_PREPAID','instantpay'),(21,2,'VIDEOCON SPECIAL TARIFF','MOBILE_PREPAID','instantpay'),(22,2,'VIDEOCON-TALKTIME','MOBILE_PREPAID','instantpay'),(23,2,'VODAFONE','MOBILE_PREPAID','instantpay'),(35,2,'AIRTEL DIGITAL TV','DTH','payworld'),(36,2,'DISH TV','DTH','payworld'),(37,2,'RELIANCE DIGITAL TV','DTH','payworld'),(38,2,'SUN DIRECT','DTH','payworld'),(39,2,'TATA SKY','DTH','payworld'),(40,2,'VIDEOCON D2H','DTH','payworld');

/*Table structure for table `current_thirdparty_service_provider` */

DROP TABLE IF EXISTS `current_thirdparty_service_provider`;

CREATE TABLE `current_thirdparty_service_provider` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `service_type` varchar(50) NOT NULL,
  `third_party_service_provider` varchar(50) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `operator_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=latin1;

/*Data for the table `current_thirdparty_service_provider` */

insert  into `current_thirdparty_service_provider`(`id`,`service_type`,`third_party_service_provider`,`created_at`,`operator_name`) values (1,'MOBILE_PREPAID','payworld','2013-08-26 20:57:41','AIRTEL'),(2,'MOBILE_PREPAID','instantpay','2013-08-26 20:57:41','MTS'),(3,'MOBILE_PREPAID','instantpay','2013-09-09 17:30:05','IDEA'),(4,'DTH','payworld','2013-09-09 18:56:31','DISH TV'),(6,'MOBILE_PREPAID','instantpay','2013-09-09 20:53:41','AIRCEL'),(7,'MOBILE_PREPAID','instantpay','2013-09-09 20:53:41','BSNL - SPECIAL TARIFF'),(8,'MOBILE_PREPAID','instantpay','2013-09-09 20:53:41','BSNL - TALKTIME'),(9,'DTH','payworld','2013-09-09 20:53:41','AIRTEL DIGITAL TV'),(11,'BILL_PAY','payworld','2013-09-09 20:53:41','BSES RAJDHANI POWER LIMIT'),(12,'BILL_PAY','payworld','2013-09-09 20:53:41','BSES YAMUNA POWER LIMIT'),(13,'MOBILE_PREPAID','instantpay','2013-09-09 20:53:41','BSNL'),(16,'BILL_PAY','payworld','2013-09-09 20:53:41','ICICI PRUDENTIAL LIFE INS'),(20,'MOBILE_PREPAID','instantpay','2013-09-09 20:53:41','LOOP MOBILE'),(21,'BILL_PAY','payworld','2013-09-09 20:53:41','MAHANAGAR GAS LIMITED'),(22,'MOBILE_PREPAID','instantpay','2013-09-09 20:53:41','MTNL-SPECIAL TARIFF'),(23,'MOBILE_PREPAID','instantpay','2013-09-09 20:53:41','MTNL_TALKTIME'),(24,'MOBILE_PREPAID','instantpay','2013-09-09 20:53:41','MTNL DELHI'),(26,'MOBILE_PREPAID','instantpay','2013-09-09 20:53:41','RELIANCE'),(28,'DTH','payworld','2013-09-09 20:53:41','RELIANCE DIGITAL TV'),(29,'BILL_PAY','payworld','2013-09-09 20:53:41','RELIANCE ENERGY LIMITED'),(31,'DTH','payworld','2013-09-09 20:53:41','SUN DIRECT'),(32,'MOBILE_PREPAID','instantpay','2013-09-09 20:53:41','T24 MOBILE-SPECIAL TARIFF'),(33,'MOBILE_PREPAID','instantpay','2013-09-09 20:53:41','T24 MOBILE-TALKTIME'),(34,'BILL_PAY','payworld','2013-09-09 20:53:41','Tata AIA LIFE INSURANCE'),(36,'MOBILE_PREPAID','instantpay','2013-09-09 20:53:41','TATA DOCOMO CDMA'),(37,'MOBILE_PREPAID','instantpay','2013-09-09 20:53:41','TATA DOCOMO GSM'),(38,'MOBILE_PREPAID','instantpay','2013-09-09 20:53:41','TATA DOCOMO GSM-SPECIAL T'),(39,'MOBILE_PREPAID','instantpay','2013-09-09 20:53:41','TATA DOCOMO GSM-TALKTIME'),(40,'BILL_PAY','payworld','2013-09-09 20:53:41','Tata Power Delhi Distribu'),(41,'DTH','payworld','2013-09-09 20:53:41','TATA SKY'),(42,'MOBILE_PREPAID','instantpay','2013-09-09 20:53:41','UNINOR-SPECIAL TARIFF'),(43,'MOBILE_PREPAID','instantpay','2013-09-09 20:53:41','UNINOR-TALKTIME'),(44,'MOBILE_PREPAID','instantpay','2013-09-09 20:53:41','VIDEOCON SPECIAL TARIFF'),(45,'MOBILE_PREPAID','instantpay','2013-09-09 20:53:41','VIDEOCON-TALKTIME'),(46,'DTH','payworld','2013-09-09 20:53:41','VIDEOCON D2H'),(48,'MOBILE_PREPAID','instantpay','2013-09-09 20:53:41','VODAFONE');

/*Table structure for table `customerdetail` */

DROP TABLE IF EXISTS `customerdetail`;

CREATE TABLE `customerdetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mobilenumber` varchar(13) NOT NULL,
  `customername` varchar(100) DEFAULT NULL,
  `amount` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `customerdetail` */

/*Table structure for table `distributor_commision` */

DROP TABLE IF EXISTS `distributor_commision`;

CREATE TABLE `distributor_commision` (
  `id` bigint(20) NOT NULL,
  `commision` double DEFAULT NULL,
  `created_at` varchar(255) DEFAULT NULL,
  `distributor_id` varchar(255) DEFAULT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `new_unique_key` (`distributor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `distributor_commision` */

insert  into `distributor_commision`(`id`,`commision`,`created_at`,`distributor_id`,`mobile_no`) values (1,2,'2013-09-03','100',NULL);

/*Table structure for table `distributorbaltransferlog` */

DROP TABLE IF EXISTS `distributorbaltransferlog`;

CREATE TABLE `distributorbaltransferlog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `distributorid` bigint(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `transfer_type` varchar(50) DEFAULT NULL,
  `transfer_from` varchar(50) DEFAULT NULL,
  `transfer_to` varchar(50) DEFAULT NULL,
  `preb2bcurrbal` double DEFAULT NULL,
  `preb2adunitbal` double DEFAULT NULL,
  `newb2bcurrbal` double DEFAULT NULL,
  `newb2badunitbal` double DEFAULT NULL,
  `transfer_amount` double DEFAULT NULL,
  `transaction_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `distributorbaltransferlog` */

insert  into `distributorbaltransferlog`(`id`,`distributorid`,`created_at`,`transfer_type`,`transfer_from`,`transfer_to`,`preb2bcurrbal`,`preb2adunitbal`,`newb2bcurrbal`,`newb2badunitbal`,`transfer_amount`,`transaction_id`) values (3,101,'2013-09-09 15:32:20','INTERACCOUNT','DISTCURRADUNIT','DISTCURR',1988,38885,3008,37885,1000,'DISTCURRADUNITTODISTCURR130909153220'),(4,101,'2013-09-09 18:19:46','DEBIT','COMP','DIST_AD_UNIT',0,40885,0,41885,1000,'COMPTODIST130909181946');

/*Table structure for table `distributorcurrbal` */

DROP TABLE IF EXISTS `distributorcurrbal`;

CREATE TABLE `distributorcurrbal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `curracbalance` double NOT NULL DEFAULT '0',
  `b2bcurracbalance` double NOT NULL DEFAULT '0',
  `b2bcurracadunitbal` double NOT NULL DEFAULT '0',
  `distributorid` bigint(20) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `distributorcurrbal` */

insert  into `distributorcurrbal`(`id`,`curracbalance`,`b2bcurracbalance`,`b2bcurracadunitbal`,`distributorid`,`created_at`,`updated_at`) values (1,1000,3008,41885,101,'2013-09-05 14:42:47','2013-09-07 14:32:21'),(3,34,240,143,102,'2013-09-07 19:20:45',NULL);

/*Table structure for table `franchiseebaltransferlog` */

DROP TABLE IF EXISTS `franchiseebaltransferlog`;

CREATE TABLE `franchiseebaltransferlog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `franchiseeid` bigint(20) NOT NULL,
  `transaction_type` varchar(20) NOT NULL,
  `transaction_from` varchar(50) NOT NULL,
  `transaction_to` varchar(50) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `distributorid` bigint(20) NOT NULL,
  `transaction_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `franchiseebaltransferlog` */

/*Table structure for table `franchiseecurrbal` */

DROP TABLE IF EXISTS `franchiseecurrbal`;

CREATE TABLE `franchiseecurrbal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `franchiseeid` bigint(20) NOT NULL,
  `b2bcurrbal` double DEFAULT NULL,
  `b2bcurrbaladunit` double DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `distributorid` bigint(20) NOT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `franchiseecurrbal` */

insert  into `franchiseecurrbal`(`id`,`franchiseeid`,`b2bcurrbal`,`b2bcurrbaladunit`,`created_at`,`distributorid`,`updated_at`) values (1,100,2324,54,'2013-09-05 14:21:30',101,'2013-09-07 14:32:21');

/*Table structure for table `keywords` */

DROP TABLE IF EXISTS `keywords`;

CREATE TABLE `keywords` (
  `ID` int(255) NOT NULL AUTO_INCREMENT,
  `KEYWORD` varchar(50) DEFAULT NULL,
  `FORMAT` varchar(200) DEFAULT NULL,
  `DESCRIPTION` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `keywords` */

insert  into `keywords`(`ID`,`KEYWORD`,`FORMAT`,`DESCRIPTION`) values (1,'FBAL','FBAL SPIN','TO CHECK CURRENT ACCOUNT BALANCE'),(2,'CHSF','CHSF NEW SPIN*CURRENT SPIN','CHANGE FRANCHISEE SPIN'),(3,'FTOPS','FTOPS MOBILENO*AMT*SPIN','TO RECHARGE PINLESS'),(4,'FTOPS','FTOPS MOBILENO*AMT*SPIN*OPERATOR CODE','TO RECHARGE MNP'),(5,'MOBILE OPERATOR CODES','OPERATOR CODE','AIRTEL, AIRCEL, BSNL,DOCO, IDEA, INDICOM , MTNL, MTS, RELGSM, RELCDMA, VIDEO, VODA'),(6,'FTOPS','FTOPS MOBILENO*AMT*SPIN*R','TO DO RECHARGE OF MTNL AND BSNL'),(7,'FDTH','FDTH SERVICE PROVIDER CODE*AMT*SPIN','TO PURCHASE DTH PIN FOR TATA and VIDEOCON'),(8,'FDTH','FDTH SERVICE PROVIDER CODE*AMOUNT*SPIN*CONNECTION NUMBER','TO RECHARGE DIRECT FOR TATA, SUN, AIRTEL, BIG, VIDEOCON AND DISH'),(9,'FBILL','FBILL BILLER CODE*AMOUNT*SPIN*CONNECTION NUMBER','FOR BILLER CODES SEE BILL PAYMENT PAGE');

/*Table structure for table `masterdata` */

DROP TABLE IF EXISTS `masterdata`;

CREATE TABLE `masterdata` (
  `ID` int(50) NOT NULL AUTO_INCREMENT,
  `KEY_VAL` varchar(50) DEFAULT NULL,
  `DISPLAY_NAME` varchar(100) DEFAULT NULL,
  `VALUE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

/*Data for the table `masterdata` */

insert  into `masterdata`(`ID`,`KEY_VAL`,`DISPLAY_NAME`,`VALUE`) values (1,'TELECOM','AIRCEL','AIRCEL'),(2,'TELECOM','AIRCEL SOUTH','AIRCEL SOUTH'),(3,'TELECOM','AIRTEL','AIRTEL'),(4,'TELECOM','BPL MOBILE COMMUNICATION','BPL MOBILE COMMUNICATION'),(5,'TELECOM','BSNL','BSNL'),(6,'TELECOM','BSNL VALIDITY','BSNL VALIDITY'),(7,'TELECOM','IDEA','IDEA'),(8,'TELECOM','MTNL','MTNL'),(9,'TELECOM','MTS','MTS'),(10,'TELECOM','RELIANCE GSM','RELIANCE GSM'),(11,'TELECOM','RELIANCE TELECOM','RELIANCE TELECOM'),(12,'TELECOM','TATA DOCOMO','TATA DOCOMO'),(13,'TELECOM','TATA INDICOM','TATA INDICOM'),(14,'TELECOM','UNINOR','UNINOR'),(15,'TELECOM','UNINOR SPECIAL','UNINOR SPECIAL'),(16,'TELECOM','VIDEOCON MOBILE','VIDEOCON MOBILE'),(17,'TELECOM','VIDEOCON MOBILE OLD','VIDEOCON MOBILE OLD'),(18,'TELECOM','VODAFONE','VODAFONE'),(19,'DTH','SUN','SUN'),(20,'DTH','DISH','DISH'),(21,'DTH','TATA','TATA'),(22,'DTH','VIDEOCON','VIDEOCON'),(23,'DTH','BIGTV','BIGTV'),(24,'DTH','AIRTELDTH','AIRTELDTH'),(25,'DATACARD','TATA PHOTON GSM','TATA PHOTON GSM'),(26,'DATACARD','TATA PHOTON CDMA','TATA PHOTON CDMA'),(27,'DATACARD','RELIANCE NETCONNECT','RELIANCE NETCONNECT'),(28,'DATACARD','MTS','MTS'),(29,'DATACARD','MTNL MOBILE INTERNET','MTNL MOBILE INTERNET'),(30,'DATACARD','BSNL MOBILE INTERNET','BSNL MOBILE INTERNET');

/*Table structure for table `recharge_transaction` */

DROP TABLE IF EXISTS `recharge_transaction`;

CREATE TABLE `recharge_transaction` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `connection_no` bigint(20) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `errorcode` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  `operator` varchar(255) DEFAULT NULL,
  `responsecode` varchar(255) DEFAULT NULL,
  `franchisee_id` varchar(255) DEFAULT NULL,
  `STATUS` double DEFAULT NULL,
  `thirdPartyTransDateTime` varchar(255) DEFAULT NULL,
  `thirdpartytransid` varchar(255) DEFAULT NULL,
  `transaction_name` varchar(255) DEFAULT NULL,
  `transid` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `credit_amount_franchisee` double DEFAULT NULL,
  `thirdpartyoperatortransid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;

/*Data for the table `recharge_transaction` */

insert  into `recharge_transaction`(`id`,`amount`,`connection_no`,`created_at`,`errorcode`,`message`,`mobile_no`,`operator`,`responsecode`,`franchisee_id`,`STATUS`,`thirdPartyTransDateTime`,`thirdpartytransid`,`transaction_name`,`transid`,`updated_at`,`credit_amount_franchisee`,`thirdpartyoperatortransid`) values (1,10,0,'2013-08-27 00:32:55',NULL,NULL,'9818631670','AI','A14.SUCCESS','100',0,'2013-08-27 00:32:55','mob9818631670130827003300','MOBILE_RECHARGE','mob9818631670130903180638',NULL,0,NULL),(3,20,0,NULL,NULL,NULL,'9818631670','AI','A14.SUCCESS','100',0,'2013-08-27 00:32:55','mob9818631670130827003300','MOBILE_RECHARGE','mob9818631670130903184957',NULL,0,NULL),(4,20,0,NULL,NULL,NULL,'9818631670','AI','A14.SUCCESS','100',0,'2013-08-27 00:32:55','mob9818631670130827003300','MOBILE_RECHARGE','mob9818631670130903185928',NULL,0,NULL),(5,100,0,NULL,NULL,NULL,'9818631670','AI','A14.SUCCESS','100',0,'2013-08-27 00:32:55','mob9818631670130827003300','MOBILE_RECHARGE','mob9818631670130903190135',NULL,0,NULL),(6,100,0,NULL,NULL,NULL,'9818631670','AI','A14.SUCCESS','100',0,'2013-08-27 00:32:55','mob9818631670130827003300','MOBILE_RECHARGE','mob9818631670130903191032',NULL,0,NULL),(7,20,0,NULL,NULL,NULL,'9818631670','AI','A14.SUCCESS','100',0,'2013-08-27 00:32:55','mob9818631670130827003300','MOBILE_RECHARGE','mob9818631670130903191313',NULL,2,NULL),(8,200,0,NULL,NULL,NULL,'9818631670','AI','A14.SUCCESS','100',0,'2013-08-27 00:32:55','mob9818631670130827003300','MOBILE_RECHARGE','mob9818631670130903191546',NULL,0.02,NULL),(9,100,0,NULL,NULL,NULL,'9818631670','AI','A14.SUCCESS','100',0,'2013-08-27 00:32:55','mob9818631670130827003300','MOBILE_RECHARGE','mob9818631670130903191916',NULL,2,NULL),(10,100,0,NULL,NULL,NULL,'9818631670','AI','A14.SUCCESS','100',0,'2013-08-27 00:32:55','mob9818631670130827003300','MOBILE_RECHARGE','mob9818631670130903191935',NULL,2,NULL),(11,10,1000019,NULL,NULL,NULL,'9579119652','AIRTEL','A14.SUCCESS','100',0,'2013-08-27 00:32:55','A01000000000000000137754377597788900','Mobile_recharge','mob9818631670130827003300',NULL,0.2,NULL),(12,10,1000019,NULL,NULL,NULL,'9579119652','AIRTEL','A14.SUCCESS','100',0,'2013-08-27 00:32:55','A01000000000000000137754377597788900','Mobile_recharge','mob9818631670130827003300',NULL,0.2,NULL),(13,10,0,NULL,NULL,NULL,'8459505105','MTS','A14.SUCCESS','100',0,'2013-08-27 00:32:55','A01000000000000000137754377597788900','MOBILE_RECHARGE','mob9818631670130827003300',NULL,0.2,NULL),(14,10,0,NULL,'2','sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target','8459505105','MTS',NULL,'100',0,NULL,NULL,'MOBILE_RECHARGE','mob8459505105130909161048',NULL,0,NULL),(15,10,0,NULL,NULL,NULL,'8459505105','MTS','TXN','100',0,'2013-09-09 16:15:17','113090916010706810','MOBILE_RECHARGE','mob8459505105130909161459',NULL,0.2,NULL),(16,10,0,NULL,NULL,NULL,'8459505105','MTS','TXN','100',0,'2013-09-09 16:47:51','113090916010708074','MOBILE_RECHARGE','mob8459505105130909164749',NULL,0.2,NULL),(17,10,0,NULL,NULL,NULL,'8459505105','MTS',NULL,'100',0,NULL,NULL,'MOBILE_RECHARGE','mob8459505105130909165000',NULL,0.2,NULL),(18,20,0,NULL,NULL,NULL,'8459505105','MTS','TXN','100',0,'2013-09-09 16:51:46','113090916010708247','MOBILE_RECHARGE','mob8459505105130909165145',NULL,0.4,NULL),(19,10,0,NULL,NULL,NULL,'8459505105','MTS','TXN','100',0,'2013-09-09 17:11:41','113090917010709053','MOBILE_RECHARGE','mob8459505105130909171139',NULL,0.2,'417729003'),(20,10,0,NULL,'DTX',NULL,'8459505105','MTS',NULL,'100',0,NULL,NULL,'MOBILE_RECHARGE','mob8459505105130909171304',NULL,0.2,NULL),(21,10,0,NULL,'DTX',NULL,'8459505105','MTS',NULL,'100',0,NULL,NULL,'MOBILE_RECHARGE','mob8459505105130909171555',NULL,0.2,NULL),(22,10,0,NULL,'DTX',NULL,'8459505105','MTS',NULL,'100',0,NULL,NULL,'MOBILE_RECHARGE','mob8459505105130909171818',NULL,0.2,NULL),(23,10,0,NULL,'DTX',NULL,'8459505105','MTS',NULL,'100',0,NULL,NULL,'MOBILE_RECHARGE','mob8459505105130909171848',NULL,0.2,NULL),(24,10,0,NULL,'DTX',NULL,'8459505105','MTS',NULL,'100',0,NULL,NULL,'MOBILE_RECHARGE','mob8459505105130909172227',NULL,0.2,NULL),(25,10,0,NULL,'DTX',NULL,'8459505105','MTS',NULL,'100',0,NULL,NULL,'MOBILE_RECHARGE','mob8459505105130909172449',NULL,0.2,NULL),(26,10,0,NULL,'DTX',NULL,'8459505105','MTS',NULL,'100',0,NULL,NULL,'MOBILE_RECHARGE','mob8459505105130909172505',NULL,0.2,NULL),(27,20,0,NULL,NULL,NULL,'9990258975','IDEA','TXN','100',0,'2013-09-09 17:36:16','113090917010710171','MOBILE_RECHARGE','MOB9990258975130909173615',NULL,0.4,'DL0090917360030'),(28,20,0,NULL,'DTX',NULL,'9990258975','IDEA',NULL,'100',0,NULL,NULL,'MOBILE_RECHARGE','MOB9990258975130909173956',NULL,0.4,NULL),(29,100,1000014348,NULL,'2','http://220.226.204.98/mainlinkpos/purchase/pw_etrans.php3loginstatus=TEST&agentid=1&retailerid=1&transid=DTH1000014348130909191152&operatorcode=20&circode=null&mobileno=null&recharge=100.0&appver=3.38',NULL,'DISH TV',NULL,'100',0,NULL,NULL,'DTH_RECHARGE','DTH1000014348130909191152',NULL,0,NULL),(30,100,1000014348,NULL,'2','http://220.226.204.98/mainlinkpos/purchase/pw_etrans.php3loginstatus=TEST&agentid=1&retailerid=1&transid=DTH1000014348130909191152&operatorcode=20&circode=null&mobileno=null&recharge=100.0&appver=3.38',NULL,'DISH TV',NULL,'100',0,NULL,NULL,'DTH_RECHARGE','DTH1000014348130909191152',NULL,0,NULL),(31,100,1000014348,NULL,'2','http://220.226.204.98/mainlinkpos/purchase/pw_etrans.php3loginstatus=TEST&agentid=1&retailerid=1&transid=DTH1000014348130909191241&operatorcode=20&circode=null&mobileno=null&recharge=100.0&appver=3.38',NULL,'DISH TV',NULL,'100',0,NULL,NULL,'DTH_RECHARGE','DTH1000014348130909191241',NULL,0,NULL),(32,10,1000014348,NULL,'FAILED','Invalid Request (Circle).',NULL,'DISH TV',NULL,'100',0,NULL,NULL,'DTH_RECHARGE','DTH1000014348130909191351',NULL,0,NULL),(33,100,1000014348,NULL,'FAILED','ERROR-Please Specify Mobile Number.',NULL,'DISH TV',NULL,'100',0,NULL,NULL,'DTH_RECHARGE','DTH1000014348130909191806',NULL,0,NULL),(34,100,1000014348,NULL,'FAILED','ERROR-Invalid V.C. Number.',NULL,'DISH TV',NULL,'100',0,NULL,NULL,'DTH_RECHARGE','DTH1000014348130909192900',NULL,0,NULL),(35,100,1517666525,NULL,'FAILED','ERROR-Invalid V.C. Number.',NULL,'DISH TV',NULL,'100',0,NULL,NULL,'DTH_RECHARGE','DTH1517666525130909193029',NULL,0,NULL),(36,100,1517666525,NULL,'FAILED','ERROR-Invalid V.C. Number.',NULL,'DISH TV',NULL,'100',0,NULL,NULL,'DTH_RECHARGE','DTH1517666525130909193029',NULL,0,NULL),(37,100,1517666525,NULL,'FAILED','ERROR-Invalid V.C. Number.',NULL,'DISH TV',NULL,'100',0,NULL,NULL,'DTH_RECHARGE','DTH1517666525130909193037',NULL,0,NULL),(38,250,1517666525,NULL,NULL,'Successfully Completed the Transaction$$$',NULL,'DISH TV','A14.SUCCESS','100',0,'2013-09-09 19:32:39','ZA011378735360097849','DTH_RECHARGE','DTH01517666525130909193243',NULL,5,NULL),(39,10,NULL,'2013-09-09 23:21:46','FAILED','Could not recharge.','8459505105','MTS',NULL,'100',0,NULL,NULL,'MOBILE_RECHARGE','MOB8459505105130909232146','2013-09-09 23:21:46',0,NULL),(40,10,NULL,'2013-09-09 23:24:09',NULL,'Successfully Completed the Transaction$$$','8459505105','AIRTEL DIGITAL TV','A14.SUCCESS','100',0,'2013-08-27 00:32:55','A01000000000000000137754377597788900','MOBILE_RECHARGE','mob9818631670130827003300','2013-09-09 23:24:09',0,NULL),(41,10,NULL,'2013-09-09 23:25:08',NULL,'Successfully Completed the Transaction$$$','8459505105','AIRTEL DIGITAL TV','A14.SUCCESS','100',0,'2013-08-27 00:32:55','A01000000000000000137754377597788900','DTH_RECHARGE','mob9818631670130827003300','2013-09-09 23:25:08',0,NULL),(42,10,1000019,'2013-09-09 23:25:56',NULL,'Successfully Completed the Transaction$$$',NULL,'AIRTEL DIGITAL TV','A14.SUCCESS','100',0,'2013-08-27 00:32:55','A01000000000000000137754377597788900','DTH_RECHARGE','mob9818631670130827003300','2013-09-09 23:25:56',0,NULL);

/*Table structure for table `thirdparty_operator_list` */

DROP TABLE IF EXISTS `thirdparty_operator_list`;

CREATE TABLE `thirdparty_operator_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `thirdparty_service_provider` varchar(25) NOT NULL,
  `operator_name` varchar(25) NOT NULL,
  `operator_code` varchar(10) NOT NULL,
  `recharge_type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=latin1;

/*Data for the table `thirdparty_operator_list` */

insert  into `thirdparty_operator_list`(`id`,`thirdparty_service_provider`,`operator_name`,`operator_code`,`recharge_type`) values (1,'payworld','AIRTEL','7','MOBILE_PREPAID'),(2,'instantpay','AIRCEL','ACB','MOBILE_PREPAID'),(3,'instantpay','BSNL - SPECIAL TARIFF','BVP','MOBILE_PREPAID'),(4,'instantpay','BSNL - TALKTIME','BGP','MOBILE_PREPAID'),(5,'instantpay','AIRTEL DIGITAL TV','ATV','DTH'),(6,'instantpay','AIRTEL','ATL','LANDLINE'),(7,'instantpay','BSES RAJDHANI POWER LIMIT','BRE','ELECTRICITY'),(8,'instantpay','BSES YAMUNA POWER LIMIT','BYE','ELECTRICITY'),(9,'instantpay','BSNL','BGL','LANDLINE'),(10,'instantpay','BSNL','BGC','MOBILE_POSTPAID'),(11,'instantpay','DISH TV','DTV','DTH'),(12,'instantpay','ICICI PRUDENTIAL LIFE INS','IPI','INSURANCE'),(13,'instantpay','IDEA','IDC','MOBILE_POSTPAID'),(14,'instantpay','IDEA','IDP','MOBILE_PREPAID'),(15,'instantpay','LOOP MOBILE','LMC','MOBILE_POSTPAID'),(16,'instantpay','LOOP MOBILE','LMP','MOBILE_PREPAID'),(17,'instantpay','MAHANAGAR GAS LIMITED','MMG','GAS'),(18,'instantpay','MTNL-SPECIAL TARIFF','MSP','MOBILE_PREPAID'),(19,'instantpay','MTNL_TALKTIME','MMP','MOBILE_PREPAID'),(20,'instantpay','MTNL DELHI','MDL','LANDLINE'),(21,'instantpay','MTS','MTP','MOBILE_PREPAID'),(22,'instantpay','RELIANCE','RGC','MOBILE_POSTPAID'),(23,'instantpay','RELIANCE','RGP','MOBILE_PREPAID'),(24,'instantpay','RELIANCE DIGITAL TV','RTV','DTH'),(25,'instantpay','RELIANCE ENERGY LIMITED','REE','ELECTRICITY'),(26,'instantpay','RELIANCE','RGL','LANDLINE'),(27,'instantpay','SUN DIRECT','STV','DTH'),(28,'instantpay','T24 MOBILE-SPECIAL TARIFF','TVP','MOBILE_PREPAID'),(29,'instantpay','T24 MOBILE-TALKTIME','TMP','MOBILE_PREPAID'),(30,'instantpay','Tata AIA LIFE INSURANCE','TAI','INSURANCE'),(31,'instantpay','TATA DOCOMO CDMA','TTC','MOBILE_POSTPAID'),(32,'instantpay','TATA DOCOMO CDMA','TCP','MOBILE_PREPAID'),(33,'instantpay','TATA DOCOMO GSM','TDC','MOBILE_POSTPAID'),(34,'instantpay','TATA DOCOMO GSM-SPECIAL T','TSP','MOBILE_PREPAID'),(35,'instantpay','TATA DOCOMO GSM-TALKTIME','TGP','MOBILE_PREPAID'),(36,'instantpay','Tata Power Delhi Distribu','NDE','ELECTRICITY	'),(37,'instantpay','TATA SKY','TTV','DTH'),(38,'instantpay','UNINOR-SPECIAL TARIFF','USP','MOBILE_PREPAID'),(39,'instantpay','UNINOR-TALKTIME','UGP','MOBILE_PREPAID'),(40,'instantpay','VIDEOCON SPECIAL TARIFF','VSP','MOBILE_PREPAID'),(41,'instantpay','VIDEOCON-TALKTIME','VGP','MOBILE_PREPAID'),(42,'instantpay','VIDEOCON D2H','VTV','DTH'),(43,'instantpay','VODAFONE','VFC','MOBILE_POSTPAID'),(44,'instantpay','VODAFONE','VFP','MOBILE_PREPAID'),(45,'payworld','DISH TV','20','DTH'),(46,'payworld','TATA SKY','17','DTH'),(47,'payworld','AIRTEL DIGITAL TV','31','DTH'),(48,'payworld','VIDEOCON D2H','37','DTH');

/*Table structure for table `thirdpartyapidetail` */

DROP TABLE IF EXISTS `thirdpartyapidetail`;

CREATE TABLE `thirdpartyapidetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `thirpartyprovidername` varchar(100) NOT NULL,
  `api_version` double DEFAULT NULL,
  `loginstatus` varchar(50) DEFAULT NULL,
  `agentid` varchar(100) DEFAULT NULL,
  `connect_url` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `retailerid` varchar(255) DEFAULT NULL,
  `token` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `thirdpartyapidetail` */

insert  into `thirdpartyapidetail`(`id`,`thirpartyprovidername`,`api_version`,`loginstatus`,`agentid`,`connect_url`,`created_at`,`retailerid`,`token`) values (1,'payworld',3.38,'TEST','1','http://220.226.204.98/mainlinkpos/purchase',NULL,NULL,NULL),(2,'instantpay',NULL,NULL,'1','https://www.instantpay.in/ws/api/transaction',NULL,NULL,'dshgdghdhjfhjfkhfkmckmc');

/*Table structure for table `user_detail` */

DROP TABLE IF EXISTS `user_detail`;

CREATE TABLE `user_detail` (
  `USER_DETAIL_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BANK_ACCOUNT` varchar(255) DEFAULT NULL,
  `BANK_BRANCH` varchar(255) DEFAULT NULL,
  `BANK_BRANCH_CITY` varchar(255) DEFAULT NULL,
  `BANK_NAME` varchar(255) DEFAULT NULL,
  `DISTRICT` varchar(255) DEFAULT NULL,
  `EMAIL_ID` varchar(255) DEFAULT NULL,
  `FIRM_NAME` varchar(255) DEFAULT NULL,
  `IFS_CODE` varchar(255) DEFAULT NULL,
  `LAND_LINE` varchar(255) DEFAULT NULL,
  `OFFICE_ADDRESS` varchar(255) DEFAULT NULL,
  `PAN_CARD` varchar(255) DEFAULT NULL,
  `PERMANENT_ADDRESS` varchar(255) DEFAULT NULL,
  `PIN_CODE` varchar(255) DEFAULT NULL,
  `STATE` varchar(255) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `USER_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USER_DETAIL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `user_detail` */

insert  into `user_detail`(`USER_DETAIL_ID`,`BANK_ACCOUNT`,`BANK_BRANCH`,`BANK_BRANCH_CITY`,`BANK_NAME`,`DISTRICT`,`EMAIL_ID`,`FIRM_NAME`,`IFS_CODE`,`LAND_LINE`,`OFFICE_ADDRESS`,`PAN_CARD`,`PERMANENT_ADDRESS`,`PIN_CODE`,`STATE`,`USER_ID`,`USER_NAME`) values (1,'1','weqw','ewqe','ewe','wrwq','vikas@cfeindia','CFE india',NULL,NULL,'office address',NULL,'parmananet address',NULL,NULL,100,'vikas');

/*Table structure for table `user_roles` */

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `USER_ROLE_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USER_ID` int(10) unsigned NOT NULL,
  `AUTHORITY` varchar(45) NOT NULL,
  PRIMARY KEY (`USER_ROLE_ID`),
  KEY `FK_user_roles` (`USER_ID`),
  CONSTRAINT `FK_user_roles` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `user_roles` */

insert  into `user_roles`(`USER_ROLE_ID`,`USER_ID`,`AUTHORITY`) values (1,100,'ROLE_FRANCHISEE'),(2,101,'ROLE_DISTRIBUTOR'),(4,102,'ROLE_EMPLOYEE'),(5,103,'ROLE_FRANCHISEE');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `USER_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  `ENABLED` tinyint(1) NOT NULL,
  `USER_TYPE` varchar(50) DEFAULT NULL,
  `FIRST_NAME` varchar(50) DEFAULT NULL,
  `LAST_NAME` varchar(50) DEFAULT NULL,
  `Address1` varchar(100) DEFAULT NULL,
  `Address2` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `Unique_Username` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`USER_ID`,`USERNAME`,`PASSWORD`,`ENABLED`,`USER_TYPE`,`FIRST_NAME`,`LAST_NAME`,`Address1`,`Address2`) values (100,'vikas','bebe68374a49cb41b7c9219e97250044',1,NULL,NULL,NULL,NULL,NULL),(101,'vikas1','bebe68374a49cb41b7c9219e97250044',1,NULL,NULL,NULL,NULL,NULL),(102,'ashish','bebe68374a49cb41b7c9219e97250044',1,NULL,NULL,NULL,NULL,NULL),(103,'1234567899','bebe68374a49cb41b7c9219e97250044',1,NULL,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
