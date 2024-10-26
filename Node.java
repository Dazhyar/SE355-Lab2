import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.ServerSocket;

public class Node {
    private ServerSocket server;
    private InetAddress ipAddr;
    private int portNumber;
    private int receivedNumberMessage;

    // Constructor to initialize a Node with a specific IP address and port number
    public Node(String ipAddr, int portNumber) {
        try {
            this.ipAddr = InetAddress.getByName(ipAddr);
            this.portNumber = portNumber;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Getter methods for IP address, port number, server, and received number properties
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

    // Setter for the received number property
    public void setReceivedNumberMessage(int receivedNumberMessage) {
        this.receivedNumberMessage = receivedNumberMessage;
    }

    // Method to create and initialize the server socket for the node
    private void createServerSocket() {
        try {
            this.server = new ServerSocket(this.portNumber);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Method to send a number to a specified node's server
    // Requires the server's IP address, port number, and the number to be sent
    public void sendNumber(String serverIPAddr, int serverPortNumber, int number) {
        try (Socket client = new Socket(serverIPAddr, serverPortNumber)) {
            OutputStreamWriter osw = new OutputStreamWriter(client.getOutputStream());
            osw.write(number + "\n");
            osw.flush();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Method to start a thread that sends numbers to another server indefinitely
    public void sendNumbersInfinitely(String serverIPAddr, int serverPortNumber) {
        ClientSendingHandler csh = new ClientSendingHandler(this, serverIPAddr, serverPortNumber);
        Thread t1 = new Thread(csh);
        t1.start();
    }

    // Method to set up the server to receive numbers from other nodes
    public void receiveNumber() {
        createServerSocket();
        try {
            ServerHandler ch = new ServerHandler(this);
            Thread t1 = new Thread(ch);
            t1.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
