package ua.danit.utils;

import java.util.Arrays;

public class ApplicationRunner {
    public static void main(String[] args) throws Exception {
        String port = args[0];
        TinderServer tinderServer = new TinderServer();
        tinderServer.tinderStart(port);
    }
}
