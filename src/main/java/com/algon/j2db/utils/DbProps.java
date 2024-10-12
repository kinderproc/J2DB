package com.algon.j2db.utils;

public class DbProps {

    public static String userDbUrl(String dbName) {
        return EnvProps.metaUrl().replaceAll("//(.+)/(.+)", "//$1/" + dbName);
    }

}
