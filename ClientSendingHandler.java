import java.util.Random;

public class ClientSendingHandler implements Runnable {
    private Node node;
    private String serverIPAddr;
    private int serverPortNumber;

    public ClientSendingHandler(Node node, String serverIPAddr, int serverPortNumber) {
        this.node = node;
        this.serverIPAddr = serverIPAddr;
        this.serverPortNumber = serverPortNumber;
    }

    // The run method continuously sends random numbers to the specified server
    public void run() {
        while (true) {
            try {
                // Generate a random integer between 1 and 100
                Random random = new Random();
                int randomInt = random.nextInt(100) + 1;

                this.node.sendNumber(serverIPAddr, serverPortNumber, randomInt);
                Thread.sleep(150);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}