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
		postMessage("SYSTEM MESSAGE: "+string);
		
	}

	private void postMessage(String string) {
		for (UserUI ui :UIs ){
			ui.addChatLine(string);
		}
		
	}

	 void addUI(UserUI userUI) {
		UIs.add(userUI);
		
	}
	 void sendUserMessage(String username, String string) {
		postMessage(username+": "+string);
		
	}
}
