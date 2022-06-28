#!/bin/bash

echo "***************************** start simulation *****************************"
count=0
while IFS=, read _ port;do
	mvn compile exec:java -Dexec.mainClass="com.load.simulation.server.Worker" \-Dexec.arguments="$port"
	count=$((count+1))
	echo $port
done < workers.txt
# Note: Small delay needed so as to let Workers launch completely and open Server Sockets.
#sleep 0.5s
#echo "Starting Load Balancer process..."
#Commandline parameter options for LoadBalancer: "RR" for Round Robin scheduling, "LC" for Least Connections scheduling.
#mvn compile exec:java -Dexec.mainClass="com.load.simulation.server.LoadBalancer" 1>/dev/null
#echo "Starting Client process..."
#mvn compile exec:java -Dexec.mainClass="com.load.simulation.server.Client" 1>/dev/null
#echo "All processes running."
