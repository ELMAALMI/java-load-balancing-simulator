package com.load.simulation.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class WorkerServer implements Runnable{
    private final int port;
    public WorkerServer(int port){
        this.port = port;
    }
    @Override
    public void run() {
        try {
            // Load MySQL JDBC Driver.
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/students", "root", "123456789");
            // Open socket for this worker.
            ServerSocket workerSocket = new ServerSocket(this.port);
            while(true){
                // Accept connection from Load Balancer.
                Socket loadBalancerSocket = workerSocket.accept();
                // Start a new thread to service this request.
                Thread workerTask = new Thread(new WorkerTask(loadBalancerSocket, conn));
                workerTask.start();
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
