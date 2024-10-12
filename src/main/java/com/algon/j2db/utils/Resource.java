package com.algon.j2db.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.stream.Collectors;

@Slf4j
public class Resource {

    public static String asStr(String name) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream resourceStream = loader.getResourceAsStream(name)) {
            return new BufferedReader(new InputStreamReader(resourceStream))
                    .lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            log.error("Resource.asStr exception: can't load resource '{}'. Check if exists in the resource folder.", name, e);
            throw new UncheckedIOException(e);
        }
    }

}
