package com.load.simulation.client;

import java.net.Socket;


public class Client {
    public static void main(String[] args) {

        try {
            while (true){
                // Open connection to Load Balancer.
                Socket loadBalancerSocket = new Socket("localhost", 12345);
                // Start a new thread to send request.
                Thread requestSender = new Thread(new RequestSender(loadBalancerSocket));
                requestSender.start();
                // To clearly observe print statements on Client, Load Balancer and Workers:
                // Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
