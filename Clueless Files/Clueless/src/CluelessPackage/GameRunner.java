package CluelessPackage;


/**
 * This is basically just a class to store the "main" method to get the ball rolling. Plan for now  is to replace this class with 
 * a class that covers joining (i.e. creates the users) then uses a method to pass the users to the game before calling the "selectFirstPlayer"
 * and "startNewTurn
 * which 
 * @author WWAB
 *
 */
public class GameRunner {

	public static void main(String[] args) {
		
		//initialize the gameboard
		GameBoard.getBoard().initialize();
		
		//initialize the game
		Game.getGame().initialize();
		
		//*******insert something like Game.getGame.AddUsers(<a list of users>);************
		
		//Get the game going
		Game.getGame().selectFirstPlayer();
	}

}
