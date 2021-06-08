package com.zazium.Net;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CliRunner extends Thread{
    //variables and objecs creation
    ObjectOutputStream oos;
    ObjectInputStream ois;
    CliStarts main;
    //Messenger msg;

    boolean state1;
    boolean state2;
    boolean finState1 = false;
    boolean finState2 = false;
    public double timer1;
    public double timer2;

    final int port = 1234;
    final String host = "localhost";

    //constructor
    public CliRunner() {
    }

    @Override
    public synchronized void run() {
        try {
            Socket socket = new Socket(host, port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

            main = new CliStarts();
            System.out.println("clien runner");

            System.out.println(ois.readObject());
            oos.writeObject("yooo from clients");
            oos.flush();

            while (true) {
                //oos.writeObject(msg);
                //msg = (Messenger) ois.readObject();
            }
        } catch (Exception ex) {
            Logger.getLogger(CliRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
