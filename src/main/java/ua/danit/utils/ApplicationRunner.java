package ua.danit.utils;

public class ApplicationRunner {
    public static void main(String[] args) throws Exception {
        TinderServer tinderServer = new TinderServer();
        tinderServer.tinderStart();
    }
}
