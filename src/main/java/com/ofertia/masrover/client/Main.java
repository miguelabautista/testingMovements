package com.ofertia.masrover.client;

import java.io.IOException;

/**
 * Created by root on 11/11/14.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Server server;

        int count = 0;

        while (true) {

            server = new Server();

            System.out.println(++count);

            server.run();

        }
    }
}
