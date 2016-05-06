package CluelessPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.net.*;

public class ServerMessenger {
	public static ArrayList<Map<String, Map<String, String>>> Users;
	static String IP;
	static int port;
	static int playerCount;
	public static Game game;
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
			boolean found = false;
			String t_playerList;
            while (true) {
                try {
                	found = false;
                    str_incoming = buffin.readLine(); // parse the incoming data
                    System.out.println(str_incoming);
                    if (str_incoming != null) {
                    	args_incoming = convertToData(str_incoming); // get the "," delimited command conversion (arbitrary protocol)
                    	if (args_incoming.get(0).equals("exit")) {
                    		socket.close();
                    		System.out.println("Disconnected from client" + playerNum);
                    		playerCount--;
                    		return ;
                    	}
                    	if(args_incoming.get(0).equals("startGame")){
                    		if (playerCount >2  ) {
                    			System.out.println("Received request to start game");
                            	// begin change for Game addition
                            	System.out.println("Clueless Game Initialized");

                            	// let Game initialize on player list
                    			game = Game.getGame();

                    			game.initialize(Users);
                    			game.selectFirstPlayer();
                    			game.startNewTurn();
                    		}
                    		else {
                    			for (int i = 0; i < connectedClients.size(); i++) {
                    				try {
                    					connectedClients.get(i).sendMessage("chat, Game cannot start until at least 3 players joined.Thanks.");
                    				} catch (Exception e) {
                    					e.printStackTrace();
                    				}
                    				//out.println(str_incoming + "\n\r");
                    				//out.flush();
                    			}
                    		}
                    	}
                    	if (args_incoming.get(0).equals("end_turn")) {
                    		System.out.println("Received request from " + args_incoming.get(1) + " to end turn.");
                    		User t = null;
                    		for (int i = 0; i < game.users.size(); i++) {
                    			if (game.users.get(i).getCharacter().equals(args_incoming.get(1))) {
                    				t = game.users.get(i);
                    				break;
                    			}
                    		}
                    		game.endTurnRequest(t);
                    	} 
                    	if (args_incoming.get(0).equals("pass_on_suggestion")) {
                    		System.out.println("Received request to make suggestion from " + args_incoming.get(4));
                    		game.handleSuggestion(args_incoming.get(1),args_incoming.get(2),args_incoming.get(3), args_incoming.get(4));
                    	} 
                    	if (args_incoming.get(0).equals("move")) {
                    		System.out.println("Received request from " + args_incoming.get(1) + " to move to " +args_incoming.get(2) );
                    		User t = null;
                    		for (int i = 0; i < game.users.size(); i++) {
                    			if (game.users.get(i).getCharacter().equals(args_incoming.get(1))) {
                    				t = game.users.get(i);
                    				break;
                    			}
                    		}
                    		game.requestMoverTo(t,Integer.parseInt(args_incoming.get(2)));
                    	} 
                    	if (args_incoming.get(0).equals("game_won")) {
                    		System.out.println("Game over, someone won");
                    		for (int i = 0; i < connectedClients.size(); i++) {
                    			try {
                    				connectedClients.get(i).sendMessage("chat,"+"Game won");
									connectedClients.get(i).sendMessage(str_incoming);
								} catch (Exception e) {
									e.printStackTrace();
								}
                    			//out.println(str_incoming + "\n\r");
                    			//out.flush();
                    		}
                    	} 
                    	else {
                    		if (Users == null && args_incoming.get(0).equals("init")) {
                    			TreeMap n = new TreeMap();
                    			TreeMap n2 = new TreeMap();
                    			n.put(args_incoming.get(1), args_incoming.get(2));
                    			n2.put(String.valueOf(playerNum), n);
                    			Users = new ArrayList<Map<String, Map<String, String>>>();
                    			Users.add(n2);

                    		}
                    		else {
                    		// need to handle the case where someone just connected to existing game
                    		if (connectedClients.size() > 0 && args_incoming.get(0).equals("init")) {
                    			TreeMap n = new TreeMap();
                    			n.put(args_incoming.get(1), args_incoming.get(2));
                    			// look for any players that already match the incoming name (by convention, if they match name,
                    			// they match character as well.
                    			for (int i = 0; i < Users.size(); i++) {
                    				for (String key : Users.get(i).keySet()) {
                    					if (Users.get(i).get(key).get(args_incoming.get(1)) != null) { // if a player of same name does exist,
                    						found = true;									  // close its connection, and let user know
                    						break;
                    					}
                    				}
                    			}
                    			if (found) {
                    				try {
										connectedClients.get(playerNum - 1).sendMessage("exit,Could not connect:\nPlayer of same name exists in game.");
										System.out.println("Disconnecting player " + (playerNum - 1));
                    				} catch (Exception e) {
										e.printStackTrace();
									}
                    				break;
                    			}

                    			TreeMap n2 = new TreeMap();
                    			n2.put(String.valueOf(playerNum), n);
                    			Users.add(n2);
                    			// append all players to list
                    			t_playerList = "";
                    			for (int i = 0; i < Users.size(); i++) {
                    				for (String key : Users.get(i).keySet()) {
                        				for (String key2 : Users.get(i).get(key).keySet()) {
                        					if (i == (Users.size() - 1)) { // we hit end, don't put comma
                        						t_playerList = t_playerList + key2 + "," + Users.get(i).get(key).get(key2);
                        					}
                        					else {
                        						t_playerList = t_playerList + key2 + "," + Users.get(i).get(key).get(key2) + ",";
                        					}
                        				}
                    				}
                    			}
                    			for (int i = 0; i < connectedClients.size(); i++) { // for each client, send the whole player list
                    				str_incoming = t_playerList;
                    			}
                    		}
                    		}
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
                		playerCount--;
                		connectedClients.remove(this);
                		int del = -1;
                		// find and remove player from Users list
                		for (int i = 0; i < Users.size(); i++) {
                			for (String key : Users.get(i).keySet()) {
                				if (key == String.valueOf(playerNum)) {
                					del = i;
                					found = true;
                					break;
                				}
                			}
            				if (found) {
            					break;
            				}
                		}
                		if (del != -1) {
                			Users.remove(del);
                		}
                		else {
                			t_playerList = "";
                			for (int i = 0; i < Users.size(); i++) {
                				for (String key : Users.get(i).keySet()) {
                					for (String key2 : Users.get(i).get(key).keySet()) {
                						if (i == (Users.size() - 1)) { // we hit end, don't put comma
                							t_playerList = t_playerList + key2 + "," + Users.get(i).get(key).get(key2);
                						}
                						else {
                							t_playerList = t_playerList + key2 + "," + Users.get(i).get(key).get(key2) + ",";
                						}
                					}
                				}
                			}
                			str_incoming = "init";
                			for (int i = 0; i < connectedClients.size(); i++) { // for each client, send the whole player list
                				str_incoming += t_playerList;
                			}
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
                		return ;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return ;
                } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
    }
    
    public void sendMessage(String p_message) {
    	// iterate through all the connected clients, sending the data that we just received
		for (int i = 0; i < connectedClients.size(); i++) {
			try {
				connectedClients.get(i).sendMessage(p_message);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//out.println(str_incoming + "\n\r");
			//out.flush();
		}
    }
    
    public static void main(String args[]) throws Exception {
        ServerSocket serverPort = null;
        Socket socket = null;
        port = 3000;
        IP = "108.31.213.246";
        //IP = "54.172.214.77";
        playerCount = 0;
        System.out.println("Clueless Server Process Initialized");
        connectedClients = new ArrayList<ConnectionThread>();
        		
        try {
        	InetAddress addr = InetAddress.getByName(IP);
        	serverPort = new ServerSocket(port); //, 50, addr);
        } catch (IOException e) {
            System.out.println("Could not open the port on local host IP");

        }
        while (playerCount < 6) {
            try {
                socket = serverPort.accept();
                System.out.println("Connected to new player " + playerCount);
                playerCount++;

            } catch (IOException e) {
                System.out.println("Could not connect to players");
            }
            ConnectionThread t = new ConnectionThread(socket);
            connectedClients.add(t);
            t.start();
        }
        TimeUnit.SECONDS.sleep(1);

        // end change
    }
}
