# Pure Java Multi-Threaded Chat Room

A real-time, console-based chat application built entirely in Java without external frameworks. This project demonstrates advanced **Socket Programming** concepts, including broadcasting messages to multiple connected clients and handling asynchronous I/O streams.

## 🚀 Features

*   **Real-Time Broadcasting:** When one user sends a message, the server instantly pushes it to *all* other connected users.
*   **Multi-Threaded Server:** Can handle multiple clients simultaneously without blocking using a thread-per-client model.
*   **Asynchronous Client:** The client uses separate threads for reading and writing, allowing users to receive incoming messages instantly while typing a new message.
*   **Thread Safety:** Uses `synchronized` blocks to safely manage the list of connected users.
*   **Pure Java:** Built using only `java.net` and `java.io`.

## 📂 Project Structure

*   **`ChatServer.java`**: 
    *   Listens on port `8080`.
    *   Maintains a `Set` of all connected client output streams.
    *   When a message is received from one client, it iterates through the set and broadcasts the message to everyone.
    *   Handles client disconnections gracefully.

*   **`ChatClient.java`**: 
    *   Connects to the server.
    *   **Thread 1 (Main):** Waits for user input from the keyboard and sends it to the server.
    *   **Thread 2 (Background):** Continuously listens for incoming messages from the server and prints them to the console.

## 🛠️ Prerequisites

*   Java Development Kit (JDK) 8 or higher.

## ⚙️ How to Run

### 1. Compile the Code
Navigate to the `src` folder in your terminal:

```bash
javac sockets/*.java
```
### 2. Start the Server
```
java sockets.ChatServer
```

### 3. Start Client A
```
cd src
java sockets.ChatClient
```
### 4. start Client B
```
cd src
java sockets.ChatClient
```

### RESOURCES 
- [geeksforgeeks](https://www.geeksforgeeks.org/java/simple-chat-application-using-sockets-in-java/)
