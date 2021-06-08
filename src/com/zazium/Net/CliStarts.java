package com.zazium.Net;

import java.io.IOException;
import java.net.ServerSocket;

public class CliStarts {
    static CliRunner cRun;


    public static void main(String[] args) throws IOException, IOException {
        cRun = new CliRunner();
        cRun.start();
        System.out.println("cli starts");
    }
    //count down timer so  start together
    public void countdownTimer() {
        while (true) {
        }
    }


}
