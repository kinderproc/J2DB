package com.algon.j2db.common;

import com.algon.j2db.utils.EnvProps;
import org.springframework.stereotype.Component;

@Component
public class UserDbDataManager {

    public String getUserDbUrl(String dbName) {
        return EnvProps.metaUrl().replaceAll("//(.+)/(.+)", "//$1/" + dbName);
    }
}
