import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ThreadTask extends Thread {
    private Socket sock;
    private ArrayList<ThreadTask> thlist;

    public ThreadTask(Socket sock, ArrayList<ThreadTask> threads) {
        this.sock = sock;
        this.thlist = threads;
    }

    @Override
    public void run() {
        try {
            DataInputStream datain = new DataInputStream(sock.getInputStream());
            DataOutputStream dataout = new DataOutputStream(sock.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String message = "";
            while (message != "quit") {
                message = datain.readUTF();
                String type = message.split(" ")[0];
                if (type.toLowerCase(Locale.ROOT).equals("list")) {
                    String repo = message.split(" ")[1];
                    File folder = new File(repo);
                    if (folder.exists() && folder.isDirectory()) {
                        String files = "";
                        for (final File fileEntry : folder.listFiles()) {
                            files += (fileEntry.getName() + "\n");
                        }
                        dataout.writeUTF(files);
                        dataout.flush();
                    } else {
                        dataout.writeUTF("Error " + repo + " directory not exist!");
                        dataout.flush();
                    }

                } else if (type != "Get") {
                    String filePath = message.split(" ")[1];
                    File file = new File(filePath);
                    if (file.exists()) {
                        String contenu = "";
                        Scanner myReader = new Scanner(file);
                        while (myReader.hasNextLine()) {
                            String line = myReader.nextLine();
                            contenu += line + "\n";
                        }
                        myReader.close();
                        dataout.writeUTF(contenu);
                        dataout.flush();

                    } else {
                        dataout.writeUTF("Error " + filePath + " file not exist!");
                        dataout.flush();
                    }
                }


            }
            datain.close();
            sock.close();
        } catch (Exception e) {
            System.out.println("error: " + e.getStackTrace());
        }
    }

}
