package CluelessPackage;


/**
 * This is basically just a class to store the "main" method to get the ball rolling. Plan for now  is to replace this class with 
 * a class that covers joining (i.e. creates the users) then uses a method to pass the users to the game before calling the "selectFirstPlayer"
 * and "startNewTurn
 * which 
 * @author WWAB
 *
 */

/*** WE MUST APPEND ARGUMENTS TO THE RUNNING APPLICATION - EITHER "player name" or "server" ***/

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
				//initialize the gameboard --> now only done on server end
				//GameBoard.getBoard().initialize();
		
				//initialize the game --> now only done on server end
				System.out.println(args[0]);
				//Game.getGame().initialize(args[0]);
		
				//*******insert something like Game.getGame.AddUsers(<a list of users>);************
		
				//Get the game going --> now only done on server end
				//Game.getGame().selectFirstPlayer();
				
				/*** ------------Start of new client code---------------- ***/
				// initialize user, gameboard (?) on client end
				// hard coding these in... Gerard and Elaine can't play at same time
				String t_char;
				switch (args[0]){
			      case "Dan":
			    	  t_char = "Miss Scarlet";
			    	  break;
			      case "Nabil":
			    	  t_char = "Mrs. Peacock";
			    	  break;
			      case "Amanda":
			    	  t_char = "Mr. Green";
			    	  break;
			      case "Gerard":
			    	  t_char = "Professor Plum";
			    	  break;
			      case "Elaine":
			    	  t_char = "Professor Plum";
			    	  break;
			      case "Yuriy":
			    	  t_char = "Col Mustard";
			    	  break;
			      case "Adam":
			    	  t_char = "Mrs. White";
			    	  break;
			      default:
			    	  t_char = "Bad input";
			    	  break;
				}
				
				User u = new User(args[0], t_char, false);
				
			}
		}
		else { 
			//initialize the gameboard
			//GameBoard.getBoard().initialize();
	
			//initialize the game
			//Game.getGame().initialize();
	
			//*******insert something like Game.getGame.AddUsers(<a list of users>);************
	
			//Get the game going
			//Game.getGame().selectFirstPlayer();
			
							/*** ------------Start of new client code---------------- ***/
				// initialize user, gameboard (?) on client end
				// hard coding these in... Gerard and Elaine can't play at same time
				String t_char;
				switch ("")   {
			      case "Dan":
			    	  t_char = "Miss Scarlet";
			    	  break;
			      case "Nabil":
			    	  t_char = "Mrs. Peacock";
			    	  break;
			      case "Amanda":
			    	  t_char = "Mr. Green";
			    	  break;
			      case "Gerard":
			    	  t_char = "Professor Plum";
			    	  break;
			      case "Elaine":
			    	  t_char = "Professor Plum";
			    	  break;
			      case "Yuriy":
			    	  t_char = "Col Mustard";
			    	  break;
			      case "Adam":
			    	  t_char = "Mrs. White";
			    	  break;
			      default:
			    	  t_char = "Bad input";
			    	  break;
				}
				User u = new User("Nabil", "Mrs. Peacock", false);
				
		}
	}

}
