package com.load.simulation.server;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Worker {
    public static void main(String[] args) {
       try{
           ArrayList<WorkerInfo> workers = new ArrayList<>();
           BufferedReader workerFile = new BufferedReader(new FileReader(new File("workers.txt")));
           // Populate worker list from workers.txt.
           while (workerFile.read() != -1) {
               String[] info = workerFile.readLine().split(",");
               workers.add(new WorkerInfo(info[0], Integer.parseInt(info[1])));
           }
           for (WorkerInfo info:workers){
               System.out.println("Server running at port : "+info.getPort());
               Runnable runnable = new WorkerServer(info.getPort());
               new Thread(runnable).start();
           }
       }catch (Exception e){
           e.printStackTrace();
       }
    }
}
