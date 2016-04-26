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

	public static void main(String[] args) throws Exception {
		// also can parse for hostname, if hostname == CluelessServer...
		// if (InetAddress.getLocalHost.getHostName().equals("CluelessServer") { }
		if (args.length > 0) {
			if ( args[0].equals("server")) {
				ServerMessenger sMessenger = new ServerMessenger();
				sMessenger.main(null);
			}
			else {
				//initialize the gameboard
				GameBoard.getBoard().initialize();
		
				//initialize the game
				System.out.println(args[0]);
				Game.getGame().initialize(args[0]);
		
				//*******insert something like Game.getGame.AddUsers(<a list of users>);************
		
				//Get the game going
				Game.getGame().selectFirstPlayer();
			}
		}
		else { 
			//initialize the gameboard
			GameBoard.getBoard().initialize();
	
			//initialize the game
			Game.getGame().initialize("Generic User");
	
			//*******insert something like Game.getGame.AddUsers(<a list of users>);************
	
			//Get the game going
			Game.getGame().selectFirstPlayer();
		}
	}

}
