/* CLIENT */
import java.io.*;
import java.net.*;

public class NetworkingClient {

    public static void main(String args[]) {

        Socket client = null;

        // Default port number we are going to use
        int portnumber = 7777;
        if (args.length > 1)
            portnumber = Integer.parseInt(args[0]);

        for (int i=0; i<1; i++) {
            try {
                String msg = "";

                // Create a client socket
                client = new Socket(InetAddress.getLocalHost(), portnumber);
                System.out.println("Client socket is created " + client);

                // Create an output stream of the client socket
                OutputStream clientOut = client.getOutputStream();
                PrintWriter pw = new PrintWriter(clientOut, true);

                // Create an input stream of the client socket
                InputStream clientIn = client.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(clientIn));

                // Create BufferedReader for a standard input
                BufferedReader stdIn = new BufferedReader(
                        new InputStreamReader(System.in));

                System.out.print("Enter your name. Type bye to exit: ");

                // Read data from standard input device and write it
                // to the output stream of the client socket.
                msg = stdIn.readLine();
                pw.println(msg);
                pw.flush();

                // Read data from the input stream of the client socket.
                System.out.println("Message returned from the server = " +
                        br.readLine());

                pw.close();
                br.close();
                client.close();

                // Stop the operation
                if (msg.equalsIgnoreCase("Bye")) {
                    break;
                }

            } catch (IOException ie) {
                System.out.println("I/O error " + ie);
            }
        }
    }
}
