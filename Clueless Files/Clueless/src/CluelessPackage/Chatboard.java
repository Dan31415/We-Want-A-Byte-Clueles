package CluelessPackage;


import java.util.ArrayList;

public class Chatboard {

private static Chatboard thisChatboard = null;
private ArrayList<UserUI> UIs = new ArrayList<UserUI>();

	
	private Chatboard(){
	}

	static Chatboard getChatboard() {
		if (thisChatboard == null){
			thisChatboard = new Chatboard();
		}
		return thisChatboard;
	
}

	 void sendSystemMessage(String string) {
		String t_message = "SYSTEM MESSAGE: " + string;
		//send to server
		try {
			UIs.get(0).cMessenger.sendMessage("chat,"+t_message);
		}
		catch (Exception e) {
			System.out.println("Could not send message to server");
		}
		
	}

	void postMessage(String string) {
		for (UserUI ui :UIs ){
			ui.addChatLine(string);
		}
		
	}

	 void addUI(UserUI userUI) {
		UIs.add(userUI);
		
	}
	 void sendUserMessage(String username, String string) {
		String t_message = username + ": " + string;
		//send to server
		try {
			UIs.get(0).cMessenger.sendMessage("chat,"+t_message);
		}
		catch (Exception e) {
			//System.out.println("Could not send message to server");
		}
			
		//postMessage(username+": "+string); // onus is now on ClientMessenger
		
	}
}
