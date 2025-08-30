package com.example.bitcoin_value.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Console {

    // Códigos ANSI para cores
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private static final String PURPLE = "\u001B[35m";

    private static String getTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static void log(String message) {
        printWithColor(message, CYAN, "LOG");
    }

    public static void success(String message) {
        printWithColor(message, GREEN, "SUCESSO");
    }

    public static void error(String message) {
        printWithColor(message, RED, "ERRO");
    }

    public static void warn(String message) {
        printWithColor(message, YELLOW, "AVISO");
    }

    public static void info(String message) {
        printWithColor(message, PURPLE, "INFO");
    }

    private static void printWithColor(String message, String color, String tag) {
        String timestamp = getTimestamp();
        System.out.println();
        System.out.println(color + "[" + timestamp + "] [" + tag + "] ----------------------");
        System.out.println(message);
        System.out.println("----------------------" + RESET);
        System.out.println();
    }
}
