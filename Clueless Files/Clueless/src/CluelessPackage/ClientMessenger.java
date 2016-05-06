package CluelessPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import java.net.*;

public class ClientMessenger {

        public static String ServerMessengerIP;
        public static int ServerMessengerPort;
        public static String transmit_message;
        public static Socket serverPort;
        public static PrintStream os;
        public static DataInputStream is;
        public static UserUI attachedUserUI;
        
        ClientMessenger(UserUI u) throws Exception {
            ServerMessengerIP = "108.31.213.246";
            ServerMessengerPort = 3000;
            transmit_message = "";
            attachedUserUI = u;
            new ConnectionThread().start();
        }

        public void sendMessage(String p_message) throws Exception { // takes in "," separated string
        	transmit_message = p_message;
        	ArrayList<String> t_data = convertToData(transmit_message);
        	if (t_data.get(0) == "chat") { // if we are trying to send out a chat message...
        		System.out.println("Attempting to transmit chat: '" + transmit_message + "' to server");
        	}
        	switch (t_data.get(0)) {
            case "chat": System.out.println("Attempting to transmit chat: '" + transmit_message + "' to server");
                     break;
            case "init": System.out.println("Attempting to transmit connection initialization to server");
                     break;
            default: System.out.println("Template message");
                     break;
        }
            os.println(transmit_message); //writeBytes(transmit_message);
            os.flush();
        }
        
        public static ArrayList<String> convertToData(String p_data) {
        	// parse data based on ','s
        	return new ArrayList<String>(Arrays.asList(p_data.split("\\s*,\\s*")));
        }
        
        static class ConnectionThread extends Thread {
            public void run() {
            	 serverPort = null;
                 os = null;
                 is = null;
                 DataInputStream serverReceipt = null;
                 InputStream inp = null;
                 BufferedReader buffin = null;
                 PrintStream out = null;

                 try{
                	 serverPort = new Socket(ServerMessengerIP, ServerMessengerPort);
                     os = new PrintStream(serverPort.getOutputStream());
                     is = new DataInputStream(serverPort.getInputStream());
                     serverReceipt = new DataInputStream(new BufferedInputStream(System.in));
                     
                     inp = serverPort.getInputStream(); // from here, capture everything being sent over from client
                     buffin = new BufferedReader(new InputStreamReader(inp));
                     out = new PrintStream(serverPort.getOutputStream());
                 } catch (UnknownHostException e) {
                     System.err.println("Could not connect to " + ServerMessengerIP);
                 } catch (IOException e) {
                     System.err.println("Could not shake hands with port " + ServerMessengerPort);
                 }
                 
                 String str_incoming;
                 String header;
                 ArrayList<String> data;
                 
                 if (serverPort != null && os != null && is != null) {
                     try {
                         while (true) {
                        	 str_incoming = buffin.readLine(); // parse the incoming data
                        	 data = convertToData(str_incoming);
                        	 header = data.get(0);
                        	 switch (header) {
                        	 	case "chat" : 
                        	 		// we know chat transmission looks like "chat,[MESSAGE]", so parse accordingly
                        	 		data = convertToData(str_incoming);
                        	 		System.out.println("Chat transmission received:" + data.get(1));
                        	 		attachedUserUI.userChat.postMessage(data.get(1));
                        	 		break;
                        	 	case "init" :
                        	 		// we know init transmission looks like "init,username", so parse accordingly
                        	 		data = convertToData(str_incoming);
                        	 		System.out.println("Player " + data.get(1) + " has joined.");
                        	 		attachedUserUI.userChat.postMessage("Player " + data.get(1) + " has joined.");                       	 		
                        	 		attachedUserUI.addPlayer(data.get(1), attachedUserUI.user.getCharacter());
                        	 		break; // not handling with new game-on-server implementation, but keep JIC
                        	 	case "startgame" :
                        	 		// simple command, but will kick off a bunch/chain of events on client side
                        	 		System.out.println("Start game transmission received:");
                        	 		attachedUserUI.userChat.postMessage("WELCOME to a new game... Let's start.");
                        	 		break;
                        	 	case "deactivateStart" :
                        	 		attachedUserUI.user.deactiveStart();
                        	 		break;
                        	 	case "deactivate" :
                        	 		attachedUserUI.user.deactivate();
                        	 		break;
                        	 	case "begin_turn" : // will look like "beginTurn,character_name"
                        	 		if (attachedUserUI.user.getCharacter().equals(data.get(1))) {
                        	 			attachedUserUI.user.beginTurn();
                        	 		}
                        	 		break;
                        	 	case "init_view" :
                        	 		attachedUserUI.user.initializeView();
                        	 		break;
                        	 	
                        	 	case "deactivateMovement" : 
                        	 		
                        	 		attachedUserUI.deactivateMovement();
                        	 		//if (attachedUserUI.user.getCharacter().equals(data.get(1))) {
                        	 			
                        	 			//attachedUserUI.user.moveTo(Integer.parseInt(data.get(2)));
                        	 			
                        	 	//	}
                        	 		break;
                        	 	case "add_card" : // looks like "add_card,character,card
                        	 		if (attachedUserUI.user.character.equals(data.get(1))) {
                        	 			attachedUserUI.user.takeCard(data.get(2));
                        	 		}
                        	 		break;
                        	 	case "set_murderer" : // looks like "position,user_int, location"
                        	 		attachedUserUI.user.game.murderer = data.get(1);
                        	 		break;
                        	 	case "set_murder_room" : // looks like "position,user_int, location"
                        	 		attachedUserUI.user.game.murderRoom = data.get(1);
                        	 		break;
                        	 	case "set_murder_weapon" : // looks like "position,user_int, location"
                        	 		attachedUserUI.user.game.murderWeapon = data.get(1);
                        	 		break;
                        	 	case "position" : // looks like "position,user_int, location"
                        	 		attachedUserUI.setPlayerLocation(Integer.parseInt(data.get(1)), data.get(2));
                        	 		break;
                        	 	case "game_won" : // looks like "position,user_int, location"
                        	 		attachedUserUI.deactivateAllButtonsExceptChat();
                        	 		break;
                        	 	case "notify_suggestion" : // looks like notify_suggestion,matchingWeapon,matchingCharacter,User character
                        	 		attachedUserUI.user.notifySuggestionSuccess(data.get(1), data.get(2), data.get(3));
                        	 		System.out.println(data.get(1)+data.get(2)+ data.get(3));
                        	 		break;
                        	 	case "exit":
                        	 		System.out.println("Exiting...");
                        	 		return;
                        	 	default : System.out.println("Received generic message");
                        	 		break;
                        	 }
                        	 
                         }

                         /*os.close();
                         is.close();
                         serverPort.close();
                         System.out.println("Disconnected from server");*/
                     } catch (UnknownHostException e) {
                         System.err.println("Could not connect to " + ServerMessengerIP);
                     } catch (IOException e) {
                         System.err.println("Could not shake hands with port " + ServerMessengerPort);
                     } catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                 }
            }
       }
}
