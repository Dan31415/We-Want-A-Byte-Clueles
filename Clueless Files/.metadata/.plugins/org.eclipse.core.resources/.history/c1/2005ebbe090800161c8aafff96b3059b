


import java.util.ArrayList;

public class User {

	
	String username;
	String character;
	private UserUI view;
	boolean isInTheGame;
	ArrayList<String> cards;
	private Game game;
	//private GameBoard board;
	private boolean isUserTurn;
	
	User(String username, String character){
		this.username = username;
		this.character = character;
		isInTheGame = true;
		cards = new ArrayList<String>();
		game = Game.getGame();
		isUserTurn = false;
		//board = GameBoard.getBoard();
	
		//creates a view and UI for the user
		view = new UserUI(this);
		
		//just makes sure that everyone starts in the "it's not my turn" state until the game picks a starting player.
		deactivate();
		
		
	}
	
	void deactivate() {
		view.deactivateAllButtonsExceptChat();
		isUserTurn = false;
		
	}

	void endTurn() {
		game.endTurnRequest(this);
	}

	void beginTurn() {		
		isUserTurn = true;
		int[] validMoves = game.requestValidMoves(this);
		view.enableLocations(validMoves);
		view.enableAccuseButton();
		view.enableEndTurnButton();
	}

	void moveTo(int i){
		game.requestMoverTo(this, i);
		view.deactivateMovement();
	}
	
	void mustMakeSuggestion() {
		if (isUserTurn){
	view.openSuggestionWindow();
	}
	}
	
	void makeAccusation(String accusedCharacter, String accusedWeapon, String accusedRoom){
		game.handleAccusation(accusedCharacter, accusedWeapon, accusedRoom, this);
	}

	void makeSuggestion(String suggestedCharacter, String suggestedWeapon, String suggestedRoom) {
		game.handleSuggestion(suggestedCharacter, suggestedWeapon, suggestedRoom, this);
	}
	
	void initializeView(){
		ArrayList<User> users = game.users;
		view.updateCardList(cards);
		
		for (int i = 0; i < users.size() ; i++){
			view.setPlayer(i, users.get(i));
		}

	}
	
	UserUI getUserUI(){
		
		return this.view;
		
	}

	void updatePlayerPositionsView() {
		for (int i = 0; i < game.users.size() ; i++){
			view.setPlayerLocation(i, game.requestLocationOfPlayer(i));
			
		}
	}
		
	

	
		
		
		
		
		
		/*
		for (Location l : occupiedLocations){
			for (int i = 0; i < users.size() ; i++){
				if (l.usersOccputingLocation.contains(users.get(i))){
					view.setPlayerLocation(i, Integer.toString(l.locNumber));
				}
			}
			
		}
		
	}
*/
	void isOutOfTheGame() {
		isInTheGame = false;
		
	}

	void takeCard(String drawCard) {
		cards.add(drawCard);
		
	}

	void notifySuggestionSuccess(String matchingCard, User matchingUser) {
		System.out.println("player notified");
		view.makeSuccessfulSuggestionWindow(matchingCard, matchingUser.username);
		
	}

	
	

}
