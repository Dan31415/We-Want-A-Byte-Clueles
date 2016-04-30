package CluelessPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.io.*;
import java.net.*;

public class ServerMessenger {
	public static ArrayList<Map<String, Map<String, String>>> Users;
	static String IP;
	static int port;
	static int playerCount;
    public static ArrayList<ConnectionThread> connectedClients;
    
    static class ConnectionThread extends Thread {

    	public void sendMessage(String p_message) throws Exception {
    		InputStream inp = null;
            BufferedReader buffin = null;
            PrintStream out = null;
    		try {
                inp = socket.getInputStream(); // from here, capture everything being sent over from client
                buffin = new BufferedReader(new InputStreamReader(inp));
                out = new PrintStream(socket.getOutputStream());
            } catch (IOException e) {
                return ;
            }
    		out.println(p_message); //writeBytes(transmit_message);
            out.flush();
        }
    	
        final Socket socket;
        public int playerNum;
        
        public ConnectionThread(Socket clientSocket) {
            this.socket = clientSocket;
            this.playerNum = playerCount;
        }

        public ArrayList<String> convertToData(String p_data) {
        	// parse data based on ','s
        	return new ArrayList<String>(Arrays.asList(p_data.split("\\s*,\\s*")));
        }
        
        public void run() { // backend connection handling, also essentially the "main" of Threads
            InputStream inp = null;
            BufferedReader buffin = null;
            PrintStream out = null;
            try {
                inp = socket.getInputStream(); // from here, capture everything being sent over from client
                buffin = new BufferedReader(new InputStreamReader(inp));
                out = new PrintStream(socket.getOutputStream());
            } catch (IOException e) {
                return ;
            }
            String str_incoming;
            ArrayList<String> args_incoming;
            out.println("You are now connected to the server");
			out.flush();
            while (true) {
                try {
                    str_incoming = buffin.readLine(); // parse the incoming data
                    System.out.println(str_incoming);
                    if (str_incoming != null) {
                    	args_incoming = convertToData(str_incoming); // get the "," delimited command conversion (arbitrary protocol)
                    	if (args_incoming.get(0).equals("exit")) {
                    		socket.close();
                    		System.out.println("Disconnected from client" + playerNum);
                    		return ;
                    	} 
                    	else {
                    		/*// need to handle the case where someone just connected to existing game
                    		if (connectedClients.size() > 1 && args_incoming.get(0).equals("init")) {
                    			TreeMap n = new TreeMap();
                    			n.put(args_incoming.get(1), "Character X");
                    			Users.add(playerNum, n);
                    			// append all players to list
                    			for (int i = 0; i < connectedClients.size(); i++) {
                    				str_incoming = str_incoming + Users.get
                    			}
                    		}*/
                    		// iterate through all the connected clients, sending the data that we just received
                    		for (int i = 0; i < connectedClients.size(); i++) {
                    			try {
									connectedClients.get(i).sendMessage(str_incoming);
								} catch (Exception e) {
									e.printStackTrace();
								}
                    			//out.println(str_incoming + "\n\r");
                    			//out.flush();
                    		}
                    	}
                    }
                    else {
                    	socket.close();
                		System.out.println("Disconnected from player " + playerNum);
                		connectedClients.remove(this);
                		return ;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return ;
                }
            }
        }
    }
    
    public static void main(String args[]) {
        ServerSocket serverPort = null;
        Socket socket = null;
        port = 3000;
        IP = "96.255.149.87";
        playerCount = 0;
        System.out.println("Clueless Server Process Initialized");
        connectedClients = new ArrayList<ConnectionThread>();
        try {
        	InetAddress addr = InetAddress.getByName(IP);
        	serverPort = new ServerSocket(port); //, 50, addr);
        } catch (IOException e) {
            System.out.println("Could not open the port on local host IP");

        }
        while (true) {
            try {
                socket = serverPort.accept();
                playerCount++;
                System.out.println("Connected to new player " + playerCount);
            } catch (IOException e) {
                System.out.println("Could not connect to players");
            }
            ConnectionThread t = new ConnectionThread(socket);
            connectedClients.add(t);
            t.start();
        }
    }
}
