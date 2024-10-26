# Node Communication System

This project demonstrates a simple system of communication between multiple nodes using Java sockets. Nodes can send and receive random integer numbers to and from each other. Specifically, two nodes (`A` and `B`) continuously send random numbers to two other nodes (`X` and `Y`), which then forward the received numbers to a final node (`Z`). Node `Z` displays the received numbers on the console.

## Project Topology
    +---+       +---+       
    | A |------>| X |------------|
    +---+       +---+            |
        \        ^               |
         \      /                |
          \    /                 v 
           \  /                +---+
            \/                 | Z |
           / \                 +---+
          /   \                  ^
         /     v                 |
    +---+       +---+            |
    | B |------>| Y |------------| 
    +---+       +---+

## Project Structure
- **`Node.java`**: Defines the `Node` class, which represents a network node capable of sending and receiving numbers.
- **`ClientSendingHandler.java`**: A thread handler that continuously sends random numbers to a specified server.
- **`ServerHandler.java`**: A thread handler that listens for incoming connections, receives numbers, and forwards them if needed.
- **`Main.java`**: The main entry point of the program, which sets up the nodes and initiates communication.

## How It Works
1. **Initialization**:
    - Nodes `A`, `B`, `X`, `Y`, and `Z` are created using the local loopback address `127.0.0.1` and specific port numbers.
    - Nodes `X`, `Y`, and `Z` start their server sockets to listen for incoming connections.
2. **Communication**:
    - Nodes `A` and `B` send random numbers continuously to nodes `X` and `Y`.
    - Nodes `X` and `Y` forward the received numbers to node `Z`.
    - Node `Z` displays the numbers on the console as it receives them.