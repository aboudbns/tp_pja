import java.net.*;
import java.io.*;
import java.util.Scanner;

class Server {

    public static void main(String args[]) throws Exception {
        ServerSocket servsock = new ServerSocket(8888);
        System.out.println("Server listen host 127.0.0.1  port 8888");
        Socket sock = servsock.accept();
        DataInputStream datain = new DataInputStream(sock.getInputStream());
        DataOutputStream dataout = new DataOutputStream(sock.getOutputStream());
        String command = "";
        while (command != "quit") {
            command = datain.readUTF();
            String type = command.split(" ")[0];


            if (type == "List") {

                String path = command.split(" ")[1];
                File folder = new File(path);
                if (folder.exists()) {
                    String Files = "";
                    for (final File fileEntry : folder.listFiles()) {

                        Files += (fileEntry.getName() + "\n");

                    }
                    dataout.writeUTF(Files);
                    dataout.flush();
                } else {
                    dataout.writeUTF("Error " + path + " repertoir n'exist pas!");
                    dataout.flush();
                }

            } else if (type == "Get") {
                String filePath = command.split(" ")[1];
                File file = new File(filePath);
                if (file.exists()) {
                    String contenu = "";
                    Scanner reader = new Scanner(file);
                    while (reader.hasNextLine()) {
                        String data = reader.nextLine();
                        contenu += data + "\n";
                    }
                    reader.close();
                    dataout.writeUTF(contenu);
                    dataout.flush();

                } else {
                    dataout.writeUTF("Error le fichier " + filePath + "  n'exist pas!");
                    dataout.flush();
                }
            }


        }
        datain.close();
        sock.close();
        servsock.close();
    }
}