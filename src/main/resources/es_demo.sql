/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.6.24 : Database - es_demo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`es_demo` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `es_demo`;

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `add_time` datetime DEFAULT NULL COMMENT '信息添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `message` */

insert  into `message`(`id`,`title`,`content`,`add_time`) values (5,'寻找融资平台?路演平台?投资人?来专业路演融资平台｜路演推介','知名投资人，投资机构路演!专业路演融资平台向全行业公开招募参加路演企业。搭建资金对接平台，国内知名投资人，投资机构等参加路演。','2015-10-30 11:55:57'),(7,'全文检索，理解用户意图进行检索，语义推理','GSE是极天信息基于自然语言理解平台GNLP结构的智能机器人问答，语言分析，理解用户意图进行检索，语义推理;Cloud版本支持PB定位搜索市场','2015-10-30 14:07:50'),(8,'全文检索_百度百科','全文检索是一种将文件中所有文本与检索项匹配的文字资料检索方法。全文检索系统是按照全文检索理论建立起来的用于提供全文检索服务的软件系统。基本介绍全文检索...','2015-10-30 14:08:47'),(9,'Lucene学习总结之一:全文检索的基本原理 - 觉先 - 博客园','2009年12月14日 - 全文检索大体分两个过程,索引创建(Indexing)和搜索索引(Search)。 索引创建:将现实世界中所有的结构化和非...','2015-10-30 14:09:09'),(10,'13 款开源的全文搜索引擎 - OPEN资讯','2013年4月2日 - Lucene的开发语言是Java,也是Java家族中最为出名的一个开源搜索引擎,在Java世界中已经是标准的全文检索程序,它提供了完整的查询引擎和索引引擎,没有中...','2015-10-30 14:09:24'),(11,'什么是全文检索啊?_百度知道','问题描述: 哪家公司做的比较好呢??\r\n最佳答案: 全文检索是计算机程序通过扫描文章中的每一个词,对每一个词建立一个索引,指明该词在文章中出现的次数和位置。当用户查询时根据建立的索引查找,类似于通过...','2015-10-30 14:09:43'),(12,'全文检索，理解用户意图进行检索，语义推理','GSE是极天信息基于自然语言理解平台GNLP结构的智能机器人问答，语言分析，理解用户意图进行检索，语义推理;Cloud版本支持PB定位搜索市场，用于企业级和专业级搜索:133160..','2015-10-30 14:10:02'),(13,'全文检索 lucene','2014年8月24日 - 百度到的资料,目前Lucene已经更新到4.9版本,这个版本需要1.7以上的JDK,所以如果还用1.6甚至是1.5的小盆友,请参考低版本,由于我用的1.6,因此在使用Lucene4.0。 这...','2015-10-30 14:10:48'),(14,'lucene全文检索入门实例 - Free Coding - ITeye技术网站','2012年2月29日 - package com.ln.ydc.lucene.test; import java.io.File; import java.io.IOException; import org.apache.lucene.analysis.Analyzer; import org.apac...','2015-10-30 14:11:06'),(15,'67中国娱乐网-中国最大的娱乐新闻网站','本站是国内首家独立娱乐新闻网站,致力于报道全方位的明星资讯,曾率先报道陈晓旭出家等独家娱乐消息;与50多家娱乐媒体、300多位娱乐作者具有合作关系,提供最权威的华语...','2015-10-30 17:26:37'),(16,'JSTL fn:substringBefore()函数 | 菜鸟教程','fn:substringBefore()函数 fn:substringBefore()函数返回一个字符串中指定子串前面的部分。 语法 fn:substringBefore()函数的语法如下: java.lang.String substring...','2015-10-30 17:36:16'),(17,'jstl标签fn:substring使用技巧 和 JSTL中fn表达式的使用说明 - ...','2013年12月24日 - fn:substringAfter(string, substring) 返回参数substring在参数string中后面的那一部分字符串 fn:substringBefore(string, substring) 返回参数subst...','2015-10-30 17:37:52'),(18,'《碟中谍5西瓜影音》电影在线观看_西瓜影音快播动作片_西瓜影院','2015电影《碟中谍5西瓜影音》高清在线观看,最新动作片《碟中谍5西瓜影音》分为百度影音高清播放器,西瓜影音播放器,吉吉影音播放器,快播QVOD播放器,以及...','2015-10-30 17:43:33'),(19,'使用elasticsearch遇到的一些问题以及解决方法(不断更新) | IT瘾','2014年9月23日 - 字段存储在索引(所以它稍后可以被检索使用选择性加载搜索时),并得到分析(分解成可搜索条件). elasticsearch的javaAPI之query - - CSDN博客云计算推荐...','2015-10-30 18:49:47'),(20,'elasticsearch JAVA客户端操作---搜索的过滤、分组高亮,elastic...','elasticsearch JAVA客户端操作---搜索的过滤、分组高亮,elasticsearchjavaelasticsearch 提供的API比较多,看名字差不多可以明白什么意思...','2015-10-30 20:31:05');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
