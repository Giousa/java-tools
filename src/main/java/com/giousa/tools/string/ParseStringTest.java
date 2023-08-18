package com.giousa.tools.string;

public class ParseStringTest {

    public static void main(String[] args) {
        String text = "CREATE TABLE `emr_rel_resource` (\n" +
                "  `id` bigint(21) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',\n" +
                "  `emr_no` bigint(20) NOT NULL COMMENT '病历号（问诊单）',\n" +
                "  `resource_key` varchar(5000) DEFAULT NULL COMMENT '资源KEY',\n" +
                "  `resource_type` smallint(5) DEFAULT NULL COMMENT '资源类型',\n" +
                "  `resource_desc` varchar(1024) DEFAULT NULL COMMENT '描述',\n" +
                "  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标识',\n" +
                "  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
                "  `gmt_update` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',\n" +
                "  `biz_type` varchar(32) DEFAULT 'EMR' COMMENT '业务类型',\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  KEY `idx_emr_no` (`emr_no`) COMMENT '病历号索引'\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=586418 DEFAULT CHARSET=utf8 COMMENT='电子病历关联资源表'";

        String[] split = text.split("\\n");
        for (String str : split) {
            if (str.contains("KEY") || str.contains("key")) {
                continue;
            }
            if (str.contains("`") && (str.contains("CREATE TABLE") || str.contains("create table"))) {
                parseTable(str);
            }
            if (str.contains("`") && str.contains("'")) {
                parseColumn(str);
            }
        }
    }

    private static void parseTable(String str) {
        System.out.println("parseTable = " + str);
//        String substring = str.substring(str.indexOf("`") + 1, str.lastIndexOf("`"));
        System.out.println("parseTable tableName = " + getSubString(str, "`"));
    }

    private static void parseColumn(String str) {
//        System.out.println("parseColumn = " + str);

        String comment;
        int count = countSubstring(str, "'");
        if (count > 2) {
            String[] split = str.split("'");
            comment = split[split.length - 2];
        } else {
            comment = getSubString(str, "'");
        }
        System.out.println("字段："+getSubString(str,"`"));
        System.out.println("注解：" + comment);
        System.out.println("类型：" + getColumnType(str));
    }

    private static String getSubString(String text, String symbol) {
        return text.substring(text.indexOf(symbol) + 1, text.lastIndexOf(symbol));
    }


    /**
     * split(String regex, int limit)
     * 如果 limit > 0,(从左到右)最多分割 n - 1 次,数组的长度将不会大于 n,结尾的空字符串不会丢弃。
     * 如果 limit < 0,匹配到多少次就分割多少次,而且数组可以是任何长度。结尾的空字符串不会丢弃。
     * 如果 limit = 0,匹配到多少次就分割多少次,数组可以是任何长度,并且结尾空字符串将被丢弃。
     */
    public static int countSubstring(String text, String sub) {
        return text.split(sub, -1).length - 1;
    }

    public static String getColumnType(String text) {
        if (text.contains("datetime")) {
            return "Date";
        } else if (text.contains("int") || text.contains("smallint") || text.contains("tinyint")) {
            return "Integer";
        } else if (text.contains("bigint")) {
            return "Long";
        }
        return "String";
    }
}
