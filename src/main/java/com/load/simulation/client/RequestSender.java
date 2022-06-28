package com.load.simulation.client;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Random;

class RequestSender implements Runnable{
    private final Socket loadBalancerSocket;
    RequestSender(Socket loadBalancerSocket){
        this.loadBalancerSocket = loadBalancerSocket;
    }
    @Override
    public void run() {
        try {
            BufferedWriter lbWriter = new BufferedWriter(new OutputStreamWriter(loadBalancerSocket.getOutputStream(), StandardCharsets.UTF_8));
            BufferedReader lbReader = new BufferedReader(new InputStreamReader(loadBalancerSocket.getInputStream(), StandardCharsets.UTF_8));

            // Get a random Student ID in range [1, 7](The number of rows).
            int sid = new Random().nextInt(7) + 1;

            // Send to Load Balancer.
            lbWriter.write(sid + "\n");
            lbWriter.flush();

            // Get worker's response, sent via Load Balancer.
            String jsonString = lbReader.readLine();
            JSONObject json = new JSONObject(jsonString);
            String result = "Information received for Student with ID="+sid+":"+
                    "\nName: "+json.getString("name")+
                    "\nDate of Birth: "+json.getString("dob")+
                    "\nMajor of Study: "+json.getString("major")+
                    "\nEducation Level: "+json.getString("level")+
                    "\nYear of Study: "+json.getString("year");
            System.out.println(result+"\n\n");

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
