import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class ThreadedServer {
    public static void main(String[] args) {
        ArrayList<ThreadTask> threadList = new ArrayList<>();

        try (ServerSocket serverSocket = new ServerSocket(8888)){
            System.out.println("Server listen host 127.0.0.1  port 8888");
            while (serverSocket.isBound()) {
                Socket socket = serverSocket.accept();
                ThreadTask serverThread = new ThreadTask(socket, threadList);
                threadList.add(serverThread);
                serverThread.start();

            }
        } catch(Exception e)
        {
            System.out.println(e.toString());
        }

    }
}
