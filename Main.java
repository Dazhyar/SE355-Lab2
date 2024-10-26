import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // IP address that all nodes will use (local loopback address)
        String addr = "127.0.0.1";


        try {
            // Create 4 nodes (A, B, C, D), each acting as both a client and a server
            Node A = new Node(addr, 6000);
            Node B = new Node(addr, 7000);
            Node C = new Node(addr, 8000);
            Node D = new Node(addr, 9000);


            // Opening the server socket in each node to be ready for receiving the numbers
            A.receiveNumber();
            B.receiveNumber();
            C.receiveNumber();
            D.receiveNumber();


            // Generate a random integer between 1 and 100.
            Random random = new Random();
            int randomInt = random.nextInt(100) + 1;


            // Send the random number in the following order: 
            // A -> B -> C -> D -> A. A full circle.
            A.sendNumber(B.getIPAddr(), B.getPortNumber(), randomInt); // A --> B
            Thread.sleep(150);

            B.sendNumber(C.getIPAddr(), C.getPortNumber(), B.getReceivedNumberMessage()); // B --> C
            Thread.sleep(150);

            C.sendNumber(D.getIPAddr(), D.getPortNumber(), C.getReceivedNumberMessage()); // C --> D
            Thread.sleep(150);
            
            D.sendNumber(A.getIPAddr(), A.getPortNumber(), D.getReceivedNumberMessage()); // D -- > A
            Thread.sleep(150);
            
            System.out.println("A received the number: " + A.getReceivedNumberMessage());


            // Exit the program.
            System.exit(0);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}