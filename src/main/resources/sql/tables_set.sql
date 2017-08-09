# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: localhost (MySQL 5.7.17)
# Database: citi
# Generation Time: 2017-08-09 11:39:27 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;



#Dump of database citi
CREATE DATABASE /*!32312 IF NOT EXISTS*/`@@@dbName@@@` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `@@@dbName@@@`;


# Dump of table account_set
# ------------------------------------------------------------

DROP TABLE IF EXISTS `account_set`;

CREATE TABLE `account_set` (
  `company_name` varchar(100) NOT NULL,
  `location` varchar(100) NOT NULL,
  `industry` varchar(40) NOT NULL,
  `start_date` date DEFAULT NULL,
  PRIMARY KEY (`company_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table receipts
# ------------------------------------------------------------

DROP TABLE IF EXISTS `receipts`;

CREATE TABLE `receipts` (
  `v_id` varchar(10) NOT NULL DEFAULT '',
  `path` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table subjects
# ------------------------------------------------------------

DROP TABLE IF EXISTS `subjects`;

CREATE TABLE `subjects` (
  `subjects_id` varchar(20) NOT NULL,
  `subjects_name` varchar(50) NOT NULL,
  PRIMARY KEY (`subjects_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `subjects` WRITE;
/*!40000 ALTER TABLE `subjects` DISABLE KEYS */;

INSERT INTO `subjects` (`subjects_id`, `subjects_name`)
VALUES
	('1001','库存现金'),
	('1002','银行存款'),
	('1012','其他货币资金'),
	('1101001','短期投资-股票'),
	('1101002','短期投资-债券'),
	('1101003','短期投资-基金'),
	('1121','应收票据'),
	('1122','应收账款'),
	('1123','预付账款'),
	('1131','应收股利'),
	('1132','应收利息'),
	('1221','其他应收款'),
	('1401','材料采购'),
	('1402','在途物资'),
	('1403','原材料'),
	('1404','材料成本差异'),
	('1405','库存商品'),
	('1407','商品进销差价'),
	('1408','委托加工物资'),
	('1411','周转材料'),
	('1421','消耗性生物资产'),
	('1501','长期债券投资'),
	('1511','长期股权投资'),
	('1601','固定资产'),
	('1602','累计折旧'),
	('1604','在建工程'),
	('1605','工程物资'),
	('1606','固定资产清理'),
	('1621','生产性生物资产'),
	('1622','生产性生物资产累计折旧'),
	('1701','无形资产'),
	('1702','累计摊销'),
	('1801','长期待摊费用'),
	('1901','待处理财产损溢'),
	('2001','短期借款'),
	('2201','��付票据'),
	('2202','应付账款'),
	('2203','预收账款'),
	('2211','应付职工薪酬'),
	('222100101','应交税费-应交增值税-进项税额'),
	('222100102','应交税费-应交增值税-已交税金'),
	('222100103','应交税费-应交增值税-转出未交增值税'),
	('222100104','应交税费-应交增值税-减免税款'),
	('222100105','应交税费-应交增值税-销项税额'),
	('222100106','应交税费-应交增值税-出口退税'),
	('222100107','应交税费-应交增值税-进项税额转出'),
	('222100108','应交税费-应交增值税-出口抵减内销产品应纳税额'),
	('222100109','应交税费-应交增值税-转出多交增值税'),
	('222100110','应交税费-应交增值税-销项税额抵减'),
	('2221002','应交税费-未交增值税'),
	('2221003','应交税费-应交消费税'),
	('2221004','应交税费-应交营业税'),
	('2221005','应交税费-应交资源税'),
	('2221006','应交税费-应交所得税'),
	('2221007','应交税费-应交土地增值税'),
	('2221008','应交税费-应交城市维护建设税'),
	('2221009','应交税费-应交房产税'),
	('2221010','应交税费-应交城镇土地使用税'),
	('2221011','应交税费-应交车船使用税'),
	('2221012','应交税费-应交个人所得税'),
	('2221013','应交税费-教育费附加'),
	('2221014','应交税费-地方教育费附加'),
	('2221015','应交税费-矿产资源补偿费'),
	('2221016','应交税费-排污费'),
	('2221017','应交税费-印花税'),
	('2221018','应交税费-预交增值税'),
	('2221019','应交税费-待抵扣进项税额'),
	('2221020','应交税费-待认证进项税额'),
	('2221021','应交税费-待转销项税额'),
	('2221022','应交税费-增值税留抵税额'),
	('2221023','应交税费-简易计税'),
	('2221024','应交税费-转让金融商品应交增值税'),
	('2221025','应交税费-代扣代交增值税'),
	('2231','应付利息'),
	('2232','应付利润'),
	('2241','其他应付款'),
	('2401','递延收益'),
	('2501','长期借款'),
	('2701','长期应付款'),
	('3001','实收资本'),
	('3002','资本公积'),
	('3101001','盈余公积-法定盈余公积'),
	('3101002','盈余公积-任意盈余公积'),
	('3101003','盈余公积-法定公益金'),
	('3103','本年利润'),
	('3104001','利润分配-其他转入'),
	('3104002','利润分配-提取法定盈余公积'),
	('3104003','利润分配-提取法定公益金'),
	('3104004','利润分配-提取任意盈余公积'),
	('3104005','利润分配-应付利润'),
	('3104006','利润分配-未分配利润'),
	('4001','生产成本'),
	('4101','制造费用'),
	('4301','研发支出'),
	('4401','工程施工'),
	('4403','机械作业'),
	('5001','主营业务收入'),
	('5051','其他业务收入'),
	('5111','投资收益'),
	('5301001','营业外收入-非流动资产处置净收益'),
	('5301002','营业外收入-政府补助'),
	('5301003','营业外收入-捐赠收益'),
	('5301004','营业外收入-盘盈收益'),
	('5401','主营业务成本'),
	('5402','其他业务成本'),
	('5403','税金及附加'),
	('5601001','销售费用-销售人员职工薪酬'),
	('5601002','销售费用-业务招待费'),
	('5601003','销售费用-修理费'),
	('5601004','销售费用-办公费'),
	('5601005','销售费用-水电费'),
	('5601006','销售费用-差旅费'),
	('5601007','销售费用-折旧费'),
	('5601008','销售费用-摊销费'),
	('5601009','销售费用-展览费'),
	('5601010','销售费用-商品维修费'),
	('5601011','销售费用-运输费'),
	('5601012','销售费用-装卸费'),
	('5601013','销售费用-包装费'),
	('5601014','销售费用-保险费'),
	('5601015','销售费用-广告费'),
	('5601016','销售费用-业务宣传费'),
	('5602001','管理费用-管理人员职工薪酬'),
	('5602002','管理费用-业务招待费'),
	('5602003','管理费用-修理费'),
	('5602004','管理费用-办公费'),
	('5602005','管理费用-水电费'),
	('5602006','管理费用-差旅费'),
	('5602007','管理费用-折旧费'),
	('5602008','管理费用-摊销费'),
	('5602009','管理费用-开办费'),
	('5602010','管理费用-研究费用'),
	('5602011','管理费用-咨询费'),
	('5602012','管理费用-长期待摊费用摊销'),
	('5603001','财务费用-利息费用'),
	('5603002','财务费用-手续费'),
	('5603003','财务费用-汇兑损益'),
	('5603004','财务费用-现金折扣'),
	('5711001','营业外支出-非流动资产处置净损失'),
	('5711002','营业外支出-赞助支出'),
	('5711003','营业外支出-捐赠支出'),
	('5711004','营业外支出-盘亏损失'),
	('5711005','营业外支出-坏账损失'),
	('5711006','营业外支出-存货毁损报废损失'),
	('5711007','营业外支出-无法收回的长期债券投资损失'),
	('5711008','营业外支出-无法收回的长期股权投资损失'),
	('5711009','营业外支出-自然灾害等不可抗力因素造成的损失'),
	('5711010','营业外支出-税收滞纳金'),
	('5711011','营业外支出-罚款支出'),
	('5801','所得税费用');

/*!40000 ALTER TABLE `subjects` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table subjects_balance
# ------------------------------------------------------------

DROP TABLE IF EXISTS `subjects_balance`;

CREATE TABLE `subjects_balance` (
  `subjects_id` varchar(20) NOT NULL,
  `balances` double NOT NULL,
  PRIMARY KEY (`subjects_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table voucher
# ------------------------------------------------------------

DROP TABLE IF EXISTS `voucher`;

CREATE TABLE `voucher` (
  `v_id` varchar(10) NOT NULL,
  `date` date NOT NULL,
  `is_addedreceipts` tinyint(1) NOT NULL,
  `voucher_maker` varchar(50) NOT NULL,
  `remark` varchar(200) NOT NULL,
  PRIMARY KEY (`v_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table voucher_amount
# ------------------------------------------------------------

DROP TABLE IF EXISTS `voucher_amount`;

CREATE TABLE `voucher_amount` (
  `v_id` varchar(10) NOT NULL,
  `a_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `abstract` varchar(100) NOT NULL,
  `subject` varchar(20) NOT NULL,
  `debit_amount` double NOT NULL,
  `credit_amount` double NOT NULL,
  PRIMARY KEY (`a_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table voucher_template
# ------------------------------------------------------------

DROP TABLE IF EXISTS `voucher_template`;

CREATE TABLE `voucher_template` (
  `template_id` varchar(10) NOT NULL,
  `catagory` varchar(10) NOT NULL,
  `template_name` varchar(20) NOT NULL,
  PRIMARY KEY (`template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table voucher_template_amount
# ------------------------------------------------------------

DROP TABLE IF EXISTS `voucher_template_amount`;

CREATE TABLE `voucher_template_amount` (
  `template_id` varchar(10) NOT NULL,
  `abstract` varchar(100) NOT NULL,
  `subject` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
