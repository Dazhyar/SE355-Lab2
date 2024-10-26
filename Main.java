public class Main {
    public static void main(String[] args) {
        // IP address that all nodes will use (local loopback address)
        String addr = "127.0.0.1";

        try {
            Node A = new Node(addr, 5000);
            Node B = new Node(addr, 6000);
            Node X = new Node(addr, 7000);
            Node Y = new Node(addr, 8000);
            Node Z = new Node(addr, 9000);

            // Initialize servers on nodes X, Y, and Z to receive numbers
            X.receiveNumber();
            Y.receiveNumber();
            Z.receiveNumber();

            // Nodes A and B send numbers to nodes X and Y
            A.sendNumbersInfinitely(X.getIPAddr(), X.getPortNumber()); // A --> X
            A.sendNumbersInfinitely(Y.getIPAddr(), Y.getPortNumber()); // A --> Y
            B.sendNumbersInfinitely(X.getIPAddr(), X.getPortNumber()); // B --> X
            B.sendNumbersInfinitely(Y.getIPAddr(), Y.getPortNumber()); // B --> Y
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
