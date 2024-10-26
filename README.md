# SE355 LAB1 - Node Communication System

This project demonstrates a simple system where four nodes (A, B, C, D) communicate with each other by sending and receiving random integers in a circular topology. Each node acts as both a server and a client, sending a random number to the next node in the chain.

## The Circular Topology

The communication follows a circular topology:
                +--------+
                |        |
           +--->+   A    +----+
           |    |        |    |
           |    +--------+    |
           |                  |
        +--v--+            +--v--+
        |     |            |     |
        +  D  +            +  B  +
        |     |            |     |
        +--+--+            +--+--+
            ^                 ^
            |   +--------+    |
            +---+   C    +<---+
                |        |
                +--------+

1. Node A generates a random number and sends it to Node B.
2. Node B receives the number from A, sends it to Node C.
3. Node C receives the number from B, sends it to Node D.
4. Node D receives the number from C, sends it back to Node A.
5. The received number at Node A is displayed as output.


## Project Structure

- **Main.java**: This is the entry point of the application. It creates the four nodes, starts their servers, generates a random integer, and sends the number from node A -> B -> C -> D -> A.
  
- **Node.java**: This class represents a node in the system. Each node can:
  - Act as a server to receive numbers from other nodes.
  - Act as a client to send numbers to other nodes.
  
- **ClientHandler.java**: A helper class that handles incoming connections to a node. It runs in a separate thread and waits for other nodes to send numbers.

