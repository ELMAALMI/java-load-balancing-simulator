echo "***************************** start simulation *****************************"

mvn compile exec:java -Dexec.mainClass="com.load.simulation.server.Worker" \-Dexec.arguments="20001"
