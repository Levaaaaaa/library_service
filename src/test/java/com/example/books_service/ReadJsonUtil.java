package com.example.books_service;

import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;

public class ReadJsonUtil {
    public static String readJson(String path) throws IOException {
        File file = ResourceUtils.getFile("classpath:" + path);
        return new String(Files.readAllBytes(file.toPath()));
    }
}
