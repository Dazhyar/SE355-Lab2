import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.ServerSocket;

public class Node {
    private ServerSocket server;
    private InetAddress ipAddr;
    private int portNumber;
    private int receivedNumberMessage;

    // Constructor to initialize a node with a specific IP address and port number
    public Node(String ipAddr, int portNumber) {
        try {
            this.ipAddr = InetAddress.getByName(ipAddr);
            this.portNumber = portNumber;
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    // Getter methods for IP address, port number, server, and the received number properties
    public String getIPAddr() {
        return this.ipAddr.getHostAddress();
    }

    public int getPortNumber() {
        return this.portNumber;
    }

    public ServerSocket getServer() {
        return this.server;
    }

    public int getReceivedNumberMessage() {
        return this.receivedNumberMessage;
    }


    // Setter for the receivedNumberMessage property
    public void setReceivedNumberMessage(int receivedNumberMessage) {
        this.receivedNumberMessage = receivedNumberMessage;
    }


    // Initialize the serverSocket property for a node
    private void createServerSocket() {
        try {
            this.server = new ServerSocket(this.portNumber);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    // Method to send a number to a specific node's server. Requires the server's IP address,
    // port number, and the number to be sent
    public void sendNumber(String serverIPAddr, int serverPortNumber, int number) {
        try (Socket client = new Socket(serverIPAddr, serverPortNumber)) {
            OutputStreamWriter osw = new OutputStreamWriter(client.getOutputStream());

            osw.write(number + "\n");
            osw.flush();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    // This method is responsible for running the server in a new thread
    // Allowing it to listen for incoming numbers from other nodes
    public void receiveNumber() {
        createServerSocket();

        try {            
            // the server will be handed to the 
            // ClientHandler's class
            ClientHandler ch = new ClientHandler(this); 


            // The thread object will run the ch object's run method.                                            
            Thread t1 = new Thread(ch);           
            t1.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}