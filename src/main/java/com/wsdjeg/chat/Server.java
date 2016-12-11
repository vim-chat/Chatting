package com.wsdjeg.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.wsdjeg.chat.server.Account;
import com.wsdjeg.chat.server.ServerThread;

public class Server extends ServerSocket {
    private static boolean debugMode = false;
    private static int SERVER_PORT = 2013;
    private static String databaseFileName = "";
    public static final String VERSION = "0.1.0";

    public static void main (String[] args) throws IOException{
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "-h":
                    usage();
                    return;
                case "-v":
                    version();
                    return;
                case "--database":
                    databaseFileName = args[++i];
                    break;
                case "-d":
                    debugMode = true;
                    break;
                case "-D":
                    SERVER_PORT = Integer.valueOf(args[++i]);
                    break;
                case "-port":
                    SERVER_PORT = Integer.valueOf(args[++i]);
                    break;
            }
        }
        new Server();
    }

    public Server() throws IOException {
        super(SERVER_PORT);
        try {
            while (true) {
                Socket socket = accept();
                new ServerThread(socket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Server(int port) throws IOException {
        super(port);
        try {
            while (true) {
                Socket socket = accept();
                new ServerThread(socket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void usage(){
        version();
        System.out.println("  java [-classpath] com.wsdjeg.chat.Server [-h] [-v] [--database databaseFileName] [-d] [-D port]");
        System.out.println("Options:");
        System.out.println("  -h	        help");
        System.out.println("  -v	        version");
        System.out.println("  -d            enable debug mode");
        System.out.println("  -D port       start daemon on specified port");
    }
    public static void version(){
        System.out.println("Chatting server V" + VERSION);
    }
}
