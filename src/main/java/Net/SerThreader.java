package Net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SerThreader extends Thread{
    Socket socket;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    //Messenger msg;
    static volatile boolean connState1 = false;
    static volatile boolean connState2 = false;
    static volatile boolean finState1 = false;
    static volatile boolean finState2 = false;
    static volatile int level = 1;

    //constructor to get the same socket from Server
    public SerThreader(Socket socket, int pNo) {
        this.socket = socket;
    }

    // thread keeps both  updated
    @Override
    public synchronized void run() {
        //ArrayList<Scoreboard> lists = readScore();

        try {
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("serv threader");

            oos.writeObject("yooo from server");
            oos.flush();
            System.out.println(ois.readObject());

            while (true) {
            }//count .. seconds before reseting states and

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("" + ex);
        }
    }
}
