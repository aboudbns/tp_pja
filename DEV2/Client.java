import java.net.*;
import java.io.*;

class Client {
    public static void main(String args[]) throws Exception {
        try {

            BufferedReader bufread = new BufferedReader(new InputStreamReader(System.in));
            Socket socket = new Socket("localhost", 8888);
            DataInputStream din = new DataInputStream(socket.getInputStream());
            DataOutputStream dataout = new DataOutputStream(socket.getOutputStream());
            String inmessage = "";
            while (inmessage != null) {

                inmessage = bufread.readLine();
                dataout.writeUTF(inmessage);
                dataout.flush();
                String msgrevieved = din.readUTF();
                System.out.println(msgrevieved);
            }

            dataout.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
