package com.toko.twitchflix;

public class Server {
    private static String IP = "172.17.19.142";
    private static String PortDB = "8081";
    private static String PortMovies = "8080";

    public static String getIP(){ return IP; }

    public static String getPortDB() {
        return PortDB;
    }

    public static String getPortMovies() {
        return PortMovies;
    }
}
