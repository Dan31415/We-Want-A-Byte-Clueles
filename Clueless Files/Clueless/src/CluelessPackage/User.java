package CluelessPackage;




import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class User {

	
	String username;
	String character;
	private UserUI view;
	boolean isInTheGame;
	ArrayList<String> cards;
	private Game game;
	private GameBoard board;
	private boolean isUserTurn;
	public GameBoard gameBoard;
	public ServerMessenger sMessenger;
	public int[] validMoves;
	
	User(String username, String character, boolean serverSide) throws Exception{
		this.username = username;
		this.character = character;
		isInTheGame = true;
		cards = new ArrayList<String>();
		game = Game.getGame();
		isUserTurn = false;
		//board = GameBoard.getBoard();
		sMessenger = new ServerMessenger();
		
		//creates a view and UI for the user
		if (serverSide) {
			sendDeactivate();
			return;
		}
		view = new UserUI(this);
		view.cMessenger.sendMessage("init,"+username+","+character);
		
		gameBoard = GameBoard.getBoard();
		gameBoard.initialize();

		//just makes sure that everyone starts in the "it's not my turn" state until the game picks a starting player.
		deactivate();
		
		
	}
	
	void sendDeactivate() {
		sMessenger.sendMessage("deactivate");
	}
	
	void sendDeactivateSingle() {
		sMessenger.sendMessage("deactivate"+this.character);
	}
	
	void deactivate() {
		view.deactivateAllButtonsExceptChat();
		isUserTurn = false;
	}
	void sendDeactivateMove(){
		sMessenger.sendMessage("deactivateMovement");
	}
	
	void sendBeginTurn() {
		sMessenger.sendMessage("begin_turn,"+this.getCharacter());
	}
	void sendPosition(int player, String location){
		sMessenger.sendMessage("position,"+player+","+location);
	}
	void endTurn() throws Exception {
		//this.deactivate();
		
		view.cMessenger.sendMessage("end_turn,"+this.character);
		//switch to the next player
		//playerTurn = (playerTurn+1)%users.size();
		
		//start the next player's turn
		
		//startNewTurn();
		//game.endTurnRequest(this);
	}
	
	void beginTurn() throws Exception {		
		isUserTurn = true;
		//int[] validMoves = serverRequestValidMoves(this);
		//serverRequestValidMoves(this);
		int[] validMoves = game.requestValidMoves(this);
		view.enableLocations(validMoves);
		view.enableAccuseButton();
		view.enableEndTurnButton();
	}

	void serverRequestValidMoves(User user) throws Exception {
		//view.cMessenger.sendMessage("req_valid_moves," + user.character);
		//TimeUnit.SECONDS.sleep(1);
		//return gameboard.getValidMoves(user);
	}
	
	void moveTo(int i) throws Exception{
		//game.requestMoverTo(this, i);
		view.cMessenger.sendMessage("move,"+this.character+","+i);
		gameBoard.moveUserTo(this, i);
		//view.deactivateMovement();
	}
	
	void mustMakeSuggestion() {
		if (isUserTurn){
	view.openSuggestionWindow();
	}
	}
	
	void makeAccusation(String accusedCharacter, String accusedWeapon, String accusedRoom) {
		try {
			game.handleAccusation(accusedCharacter, accusedWeapon, accusedRoom, this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void makeSuggestion(String suggestedCharacter, String suggestedWeapon, String suggestedRoom) {
		game.handleSuggestion(suggestedCharacter, suggestedWeapon, suggestedRoom, this);
	}
	
	void sendInitializeView() {
		sMessenger.sendMessage("init_view");
	}
	
	void initializeView(){
		gameBoard.putSingleUserOnStartingLocation(this);

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
		//	view.setPlayerLocation(i, game.requestLocationOfPlayer(i)); // needed to be commented out since we dont have access to User view
			sendPosition(i, game.requestLocationOfPlayer(i));
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

	/**
	 * added character getters and setters
	 * @author Adam Turbiville
	 */
   public String getCharacter()
   {
      return this.character;
   }

   public void setCharacter(String character)
   {
      this.character = character;
   }

	
	

}
