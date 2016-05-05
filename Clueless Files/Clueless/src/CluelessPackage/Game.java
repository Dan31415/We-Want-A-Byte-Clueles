package CluelessPackage;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.JTextArea; 

public class Game {
	
	ArrayList<User> users = new ArrayList<User>();
	//Within the "users" array, this stores the index of the player whose turn it currently is.
	int playerTurn;
	private String murderer;
	private String murderWeapon;
	private String murderRoom;
	private Chatboard systemChat;
	private Deck deck;
	private GameBoard gameboard; 
	private static String username;
	public ServerMessenger sMessenger;
	/**
	 * This class implements the singleton pattern for global access.
	 */
	private static Game thisGame = null;
	
	private Game() {
		systemChat= Chatboard.getChatboard();
		deck = Deck.getDeck();
		gameboard = GameBoard.getBoard();
	}

	static Game getGame() {
		if (thisGame == null){
			thisGame = new Game();
		}
		return thisGame;
	}

	
	/**
	 * Adds new users. Just a stand-in for the dynamic joining for now.
	 * @throws Exception 
	 */
	 void initialize(ArrayList<Map<String, Map<String, String>>> players) throws Exception {
		 this.sMessenger = sMessenger;
		//Make as many new users as have joined
		 for (int i = 0; i < players.size(); i++) {
			 for (String key : players.get(i).keySet()) {
				 for (String key2 : players.get(i).get(key).keySet()) {
					 users.add(new User(key2, players.get(i).get(key).get(key2), true));
				 }
			 }
		 }
		
		//users.add(new User("Dan", "Miss Scarlet"));
		//users.add(new User("Nabil", "Professor Plum"));
		//users.add(new User("Amanda", "Mrs. Peacock"));
		
		//put them on the board
		 //sMessenger.sendMessage("start_loc");
		gameboard.putUserOnStartingLocation(this.users);
		
		//determine the cards that go in the envelope
		murderer = deck.drawCharacter();
		System.out.println(murderer);
		murderWeapon = deck.drawWeapon();
		murderRoom= deck.drawRoom();
		
		
		
		//now, mix all the cards together before dealing
		deck.combineDecks();
		
		//and deal the cards out
		dealCardsToUsers();
		
		//set-up the users' UIs
		for (User u: users){
			u.sendInitializeView();
		}
		
		//send system message saying the games has started
		//systemChat.sendSystemMessage("WELCOME to a new game... Let's start.");
		sMessenger = new ServerMessenger();
		sMessenger.sendMessage("startgame");
		
		//get the game going.
		startNewTurn();
		sMessenger.sendMessage("chat,"+"the murderer is " +murderer+ " the murder weapon is " +murderWeapon+" the murder room is " +murderRoom );
		
			
		}
	
		
	/**
	 * Deals on card to each user one-by-one until the deck is empty.
	 */
	private void dealCardsToUsers() {
		
		while (deck.stillContainsCards()){
			for (User u : users){
				if (deck.stillContainsCards()){
				u.takeCard(deck.drawCard());
		}
			}
	}
	}
	
	
	/**
	 * Scans the users to see who Mrs. Scarlet it. Sets them up a starting player.
	 */
	void selectFirstPlayer() {
		User missScarlet = null;
		for (User u: users){
			if(u.character.equals("Miss Scarlet")){
				missScarlet = u;
			}
		}
		playerTurn = users.indexOf(missScarlet);
	}


	 void startNewTurn() {

		 //Everyone's UIs are updated to show any movement that took place.
		notifyUsersOfNewPositions();

		
		//activate the next player. First check if they are "out" (i.e. they made a wrong accusation). If so, skip them. Otherwise, 
		//start their turn.
		if (users.get(playerTurn).isInTheGame){
		users.get(playerTurn).sendBeginTurn();
		}
		else{endTurnRequest(users.get(playerTurn));
		}
		
		//Send a system message so everyone knows whose turn it is.
		//systemChat.sendSystemMessage("it is now "+users.get(playerTurn).username+"'s turn.");
		sMessenger.sendMessage("chat,"+"it is now "+users.get(playerTurn).username+"'s turn.");
				
	}

	 /**
	  * When a user says they are done, this method changes tot he next player's turn.
	 * @param user
	 */
	void endTurnRequest(User user) {
		
		//turn off all buttons (except for chat)
		//user.deactivate();	
		user.sendDeactivate();
		
		//switch to the next player
		playerTurn = (playerTurn+1)%users.size();
		
		//start the next player's turn
		
		startNewTurn();
		
	}

	/**
	 * Updates everyone's UIs to show the new positions.
	 */
	void notifyUsersOfNewPositions() {
		for (User u : users){
			u.updatePlayerPositionsView();
		}
		
	}


	 void handleSuggestion(String suggestedCharacter, String suggestedWeapon, String suggestedRoom, User suggestingUser) {
		 
		 JTextArea suggestingUserTextArea = suggestingUser.getUserUI().getChatDisplay();
		 String userText = suggestingUserTextArea.getText();
		 
		 systemChat.sendSystemMessage(suggestingUser.username +" is suggesting " +suggestedCharacter +" in the "+ suggestedRoom + " with the " +suggestedWeapon+":");
		 
		 suggestingUser.getUserUI().setChatDisplayText("");
		 suggestingUser.getUserUI().setChatDisplayText(userText);
		 
		 GameBoard b = gameboard;
		 
		//move the accused to the room
		for (User u : users){
			if (u.character.equals(suggestedCharacter)){
				b.moveUserToSuggester(u, suggestingUser);
			}
		}
		
		//initialize the matching user and card
		User matchingUser = null;
		String matchingCard = "none";
		
		//go through the cards of each user. If a match is found with the suggestion, record the matching card and user.
		for (User u: users){
			for(String c: u.cards){
				if (c.equals(suggestedCharacter)){
				matchingUser = u;
				matchingCard = suggestedCharacter;
			}
			else if (u.cards.contains(suggestedWeapon)){
				matchingUser = u;
				matchingCard = suggestedWeapon;
			}
			else if (u.cards.contains(suggestedRoom)){
				matchingUser = u;
				matchingCard = suggestedRoom;
			}
		}
		}
		if (matchingCard.equals("none")){
			
			//send failure message to users
			systemChat.sendSystemMessage("no cards were found by the suggestion.");
		}
		else{
			//send success message to users
			System.out.println("card " +matchingCard +"was found");
			
			//send message to all that a match was found with player "matchingUser"
			systemChat.sendSystemMessage(matchingUser.username +" has responded to the request.");
			suggestingUser.notifySuggestionSuccess(matchingCard, matchingUser);
		}
		
		//update the user positions since a user may have been moved during the suggestion.
		notifyUsersOfNewPositions();
		
		
	}

	void handleAccusation(String accusedCharacter, String accusedWeapon, String accusedRoom, User user) throws Exception {
		systemChat.sendSystemMessage(user +" is accusing " +accusedCharacter +" in the "+ accusedRoom + " with the " +accusedWeapon+":");
		if (hypothesisIsCorrect(accusedCharacter, accusedWeapon, accusedRoom )){
			
			///send message that game is over
			systemChat.sendSystemMessage("The accusation was correct: " +user.username +"has won the game!");
			
			//disable everyone's buttons except for chat
			for (User u : users){
				u.deactivate();
			}

		}
		
		else{
			//send message that user is out of the game.
			systemChat.sendSystemMessage("The accusation was wrong. " +user.username +" has lost.");
			user.getUserUI().deactivateAllButtonsExceptChat();
			user.isOutOfTheGame();
			user.moveTo(0);
			user.endTurn();

		}
	}
		private boolean hypothesisIsCorrect(String accusedCharacter, String accusedWeapon, String accusedRoom) {
			if (murderer.equals(accusedCharacter) && murderWeapon.equals(accusedWeapon) && murderRoom.equals(accusedRoom)){
					return true;
			}
			else{
				return false;
			}
	}
		
		 int[] requestValidMoves(User user) {
			return gameboard.getValidMoves(user);
		}

		 void requestMoverTo(User user, int i) {
			gameboard.moveUserTo(user, i);
			user.sendDeactivateMove();
		
			
		}

		 String requestLocationOfPlayer(int i) {
			
			return gameboard.userLocations.get(users.get(i)).toString();
		}		
	}





