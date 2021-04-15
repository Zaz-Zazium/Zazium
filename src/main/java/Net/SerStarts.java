package Net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SerStarts {
    //server variables creation
    static final int port = 1234;
    static ServerSocket ssocket;
    static Socket socket;

    public static void main(String[] args) throws IOException, IOException {
        ssocket = new ServerSocket(port);

        int peer = 1;
        while (true) {
            System.out.println("****** listening ******");
            System.out.println("");
            socket = ssocket.accept();
            SerThreader thread = new SerThreader(socket, peer);
            thread.start();
            System.out.println("cli connect");

        }
    }


}
