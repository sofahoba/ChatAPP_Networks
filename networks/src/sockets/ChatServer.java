package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {
    private static Set<PrintWriter>clientMsg=new HashSet<>();
    public static void main(String[] args){
        int port = 8080;
        System.out.println("port is : "+port);
        try(ServerSocket serverSocket = new ServerSocket(port)){
            while (true){
                new Handler(serverSocket.accept()).start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static class Handler extends  Thread{
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public Handler(Socket socket){
            this.socket=socket;
        }
        public void run(){
            try{
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                synchronized (clientMsg){
                    clientMsg.add(out);
                }
                String msg;
                while((msg=in.readLine())!=null){
                    System.out.println("recieved "+ msg);
                    for(PrintWriter writer : clientMsg){
                        writer.println(msg);
                    }
                }

            }catch (IOException e) {
                System.out.println(e.getMessage());
            }
            finally {
                if (out != null) {
                    synchronized (clientMsg) {
                        clientMsg.remove(out);
                    }
                }
                try { socket.close(); } catch (IOException e) {}
            }
        }

    }
}
