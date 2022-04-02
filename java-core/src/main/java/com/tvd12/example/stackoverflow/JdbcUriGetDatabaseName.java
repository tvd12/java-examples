package com.tvd12.example.stackoverflow;

import java.net.URI;

public class JdbcUriGetDatabaseName {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/ezyplatform";
        int startFromIndex = jdbcUrl.indexOf("://");
        int index = jdbcUrl.indexOf('/', startFromIndex + 3);
        int toIndex = jdbcUrl.indexOf('?');
        System.out.println(jdbcUrl.substring(index + 1, toIndex < 0 ? jdbcUrl.length() : toIndex));
        System.out.println(getTableName("CREATE TABLE IF NOT EXISTS `${prefix}_admins` ("));
        System.out.println(getTableName("CREATE TABLE `${prefix}_admins` ("));
    }

    private static final String PREFIX_CREATE_TABLE = "CREATE TABLE";
    private static final String PREFIX_CREATE_TABLE_IF_NO_EXISTS = "CREATE TABLE IF NOT EXISTS";

    private static String getTableName(String line) {
        int prefixLength = PREFIX_CREATE_TABLE_IF_NO_EXISTS.length();
        int index = line.indexOf(PREFIX_CREATE_TABLE_IF_NO_EXISTS);
        if (index < 0) {
            prefixLength = PREFIX_CREATE_TABLE.length();
            index = line.indexOf(PREFIX_CREATE_TABLE);
        }
        if (index < 0) {
            return null;
        }
        int lastIndex = line.indexOf('(');
        if (lastIndex < 0) {
            lastIndex = line.length();
        }
        return line.substring(index + prefixLength, lastIndex)
            .replace("`", "")
            .trim();
    }
}
