import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class ServerHandler implements Runnable {
    private Node node;

    public ServerHandler(Node node) {
        this.node = node;
    }

    public void run() {
        while (true) {
            try (Socket connection = this.node.getServer().accept()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                int receivedNumber = Integer.parseInt(br.readLine());

                // Forward the received number to node Z if current node is X or Y
                if (this.node.getPortNumber() == 7000 || this.node.getPortNumber() == 8000) {
                    this.node.sendNumber("127.0.0.1", 9000, receivedNumber);
                    Thread.sleep(150); 
                } 
                // Print the number if it is received by node Z
                else if (this.node.getPortNumber() == 9000) {
                    System.out.println("Z: " + receivedNumber);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
