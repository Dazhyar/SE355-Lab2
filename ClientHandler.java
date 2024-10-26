import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class ClientHandler implements Runnable {
    private Node node;

    public ClientHandler(Node node) {
        this.node = node;
    }

    public void run() {
        while (true) {
            // This will wait for connections from other nodes.
            try (Socket connection = this.node.getServer().accept()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                
                // Save the received number from the connected socket in the receivedNumber variable. 
                int receivedNumber = Integer.parseInt(br.readLine()); 

                // Store the received number in the current node’s receivedNumberMessage propert
                this.node.setReceivedNumberMessage(receivedNumber);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}