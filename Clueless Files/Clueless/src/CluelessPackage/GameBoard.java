package CluelessPackage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.WeakHashMap;

public class GameBoard {

	private ArrayList<Location> boardLocations = new ArrayList<Location>();
	private int numberOfLocations = 21;
	WeakHashMap<User, Integer> userLocations;

	
	
	/**
	 * This class implements the singleton pattern for global access.
	 */
	private static GameBoard thisBoard = null;

	private GameBoard() {
		// This hashmap maps users to Integers. Each integer is used to identify
		// a location. This should probably be changes to mapping
		// users to their actual Location. At any rate, this is how the board
		// keeps track of who is where.
		userLocations = new WeakHashMap<User, Integer>();
	}

	/**
	 * @return the singleton instance of the gameboard.
	 */
	static GameBoard getBoard() {
		if (thisBoard == null) {
			thisBoard = new GameBoard();
		}
		return thisBoard;
	}

	/**
	 * Called by the GameRunner at the start of a game to set-up the board.
	 */
	void initialize() {
		createLocations();
		connectLocations();
	}

	/**
	 * creates the boards and tells them their index and whether or not they are
	 * hallways.
	 */
	private void createLocations() {
		for (int i = 0; i < numberOfLocations; i++) {
			if (i == 0 || i == 2 || i == 4 || i == 8 || i == 10 || i == 12 || i == 16 || i == 18 || i == 20) {
				// These are the rooms.
				boardLocations.add(new Location(i, false));
			} else {
				// these are the hallways.
				boardLocations.add(new Location(i, true));
			}
		}
	}

	/**
	 * This method just goes through and sets-up the connections of what
	 * location is connected to where (adjacent locations are stored as
	 * adjacency lists, and this method just builds the list for each location).
	 */
	private void connectLocations() {
		ArrayList<Location> links = new ArrayList<Location>();
		links.add(boardLocations.get(1));
		links.add(boardLocations.get(5));
		boardLocations.get(0).addLinks(links);
		links.clear();

		links.add(boardLocations.get(0));
		links.add(boardLocations.get(2));
		boardLocations.get(1).addLinks(links);
		links.clear();

		links.add(boardLocations.get(1));
		links.add(boardLocations.get(3));
		links.add(boardLocations.get(6));
		boardLocations.get(2).addLinks(links);
		links.clear();

		links.add(boardLocations.get(2));
		links.add(boardLocations.get(4));
		boardLocations.get(3).addLinks(links);
		links.clear();

		links.add(boardLocations.get(3));
		links.add(boardLocations.get(7));
		boardLocations.get(4).addLinks(links);
		links.clear();

		links.add(boardLocations.get(0));
		links.add(boardLocations.get(8));
		boardLocations.get(5).addLinks(links);
		links.clear();

		links.add(boardLocations.get(2));
		links.add(boardLocations.get(10));
		boardLocations.get(6).addLinks(links);
		links.clear();

		links.add(boardLocations.get(4));
		links.add(boardLocations.get(12));
		boardLocations.get(7).addLinks(links);
		links.clear();

		links.add(boardLocations.get(5));
		links.add(boardLocations.get(9));
		links.add(boardLocations.get(13));
		boardLocations.get(8).addLinks(links);
		links.clear();

		links.add(boardLocations.get(8));
		links.add(boardLocations.get(10));
		boardLocations.get(9).addLinks(links);
		links.clear();

		links.add(boardLocations.get(6));
		links.add(boardLocations.get(9));
		links.add(boardLocations.get(11));
		links.add(boardLocations.get(14));
		boardLocations.get(10).addLinks(links);
		links.clear();

		links.add(boardLocations.get(10));
		links.add(boardLocations.get(12));
		boardLocations.get(11).addLinks(links);
		links.clear();

		links.add(boardLocations.get(7));
		links.add(boardLocations.get(11));
		links.add(boardLocations.get(15));
		boardLocations.get(12).addLinks(links);
		links.clear();

		links.add(boardLocations.get(8));
		links.add(boardLocations.get(16));
		boardLocations.get(13).addLinks(links);
		links.clear();

		links.add(boardLocations.get(10));
		links.add(boardLocations.get(18));
		boardLocations.get(14).addLinks(links);
		links.clear();

		links.add(boardLocations.get(12));
		links.add(boardLocations.get(20));
		boardLocations.get(15).addLinks(links);
		links.clear();

		links.add(boardLocations.get(13));
		links.add(boardLocations.get(17));
		boardLocations.get(16).addLinks(links);
		links.clear();

		links.add(boardLocations.get(16));
		links.add(boardLocations.get(18));
		boardLocations.get(17).addLinks(links);
		links.clear();

		links.add(boardLocations.get(14));
		links.add(boardLocations.get(17));
		links.add(boardLocations.get(19));
		boardLocations.get(18).addLinks(links);
		links.clear();

		links.add(boardLocations.get(18));
		links.add(boardLocations.get(20));
		boardLocations.get(19).addLinks(links);
		links.clear();

		links.add(boardLocations.get(19));
		links.add(boardLocations.get(15));
		boardLocations.get(20).addLinks(links);

	}

	/**
	 * @param activeUser
	 *            The user whose movement options we are investigating.
	 * @return an array of ints, containing the indices of the rooms he/she can
	 *         move into.
	 */
	int[] getValidMoves(User activeUser) {

		// make a list of this user's valid moves (starts off empty).
		ArrayList<Location> validMoveLocations = new ArrayList<Location>();

		// figure-out where the user is.
		Location playerCurrentLocation = getPlayerLocation(activeUser);

		// look at all the locations connected to the one that the user is in.
		for (Location l : playerCurrentLocation.adjacencyList) {
			// if a connected location is a room or an empty hallway, then this
			// is a valid location to move to.
			if (!l.isHallway || locationIsEmpty(l.locNumber)) {
				validMoveLocations.add(l);
			}
		}

		// Now, just convert the array of locations into an array of ints (each
		// int is the index of a location the user can move into)
		int[] validMoveIndicies = new int[validMoveLocations.size()];
		for (int i = 0; i < validMoveLocations.size(); i++) {
			validMoveIndicies[i] = validMoveLocations.get(i).locNumber;
		}

		return validMoveIndicies;
	}

	/**
	 * @param locNumber
	 *            The index of the location we're looking at.
	 * @return true if there is a user in the specified location
	 */
	private boolean locationIsEmpty(int locNumber) {
		Collection<Integer> locationCollection = userLocations.values();
		for (Integer i : locationCollection) {
			if (i.intValue() == locNumber && userLocations.containsValue(i)) {
				return false;
			}
		}
		return true;
	}

	void putSingleUserOnStartingLocation(User user) {
		
		   User currentUser = user;
		   String userCharacter = currentUser.getCharacter();
		   
		   switch (userCharacter)   {
		      case "Miss Scarlet":
		         userLocations.put(currentUser, new Integer(3));
		         break;
		      case "Mrs. Peacock":
               userLocations.put(currentUser, new Integer(13));
               break;
		      case "Mr. Green":
               userLocations.put(currentUser, new Integer(17));
               break;
		      case "Professor Plum":
               userLocations.put(currentUser, new Integer(5));
               break;
		      case "Col Mustard":
               userLocations.put(currentUser, new Integer(7));
               break;
		      case "Mrs. White":
               userLocations.put(currentUser, new Integer(19));
               break;
            default:
               break;
		   }
	   }
	
	/**
	 * Only called once at the start of the game. Differnt from the regular
	 * "moveUserTo" in that it does not remove a user from their current
	 * location (which, at the start of the game, would be null).
	 * 
	 * @param users
	 *            Array list holding all the users for this game
	 * Modified: 4/23/16, Adam Turbiville           
	 */
	void putUserOnStartingLocation(ArrayList<User> users) {
		
		for (int i=0; i < users.size(); i++)  {
		   User currentUser = users.get(i);
		   String userCharacter = currentUser.getCharacter();
		   
		   switch (userCharacter)   {
		      case "Miss Scarlet":
		         userLocations.put(currentUser, new Integer(3));
		         break;
		      case "Mrs. Peacock":
               userLocations.put(currentUser, new Integer(13));
               break;
		      case "Mr. Green":
               userLocations.put(currentUser, new Integer(17));
               break;
		      case "Professor Plum":
               userLocations.put(currentUser, new Integer(5));
               break;
		      case "Col Mustard":
               userLocations.put(currentUser, new Integer(7));
               break;
		      case "Mrs. White":
               userLocations.put(currentUser, new Integer(19));
               break;
            default:
               break;
		   }
	   }
	}

	/**
	 * Used to move the user around. * @param user The user to be put on the
	 * given location.
	 * 
	 * @param moveTo
	 *            The index of the location to put the user.
	 */
	void moveUserTo(User user, int moveTo) {
		// remove the user from current location
		userLocations.remove(user);

		// and add the user to the new location
		userLocations.put(user, new Integer(moveTo));

		// detects if the user had just moved into a room
		System.out.println("before must make suggestion");
		if (userIsInARoom(user) && user.isInTheGame) {
			user.mustMakeSuggestion();
		}
	}

	private boolean userIsInARoom(User user) {
		// get User's locations index
		int locNum = userLocations.get(user).intValue();

		for (Location l : boardLocations) {
			if (l.locNumber == locNum && !l.isHallway) {
				return true;
			}
		}
		return false;
	}
	

	/**
	 * @param user The user whose location we are looking for
	 * @return The location of the user
	 */
	Location getPlayerLocation(User user) {
		//get the index of the user's location
		int locationNumber = userLocations.get(user).intValue();
		//find the location with the same index 
		for (Location l : boardLocations) {
			if (l.locNumber == locationNumber) {
				return l;
			}
		}
		return null;
	}

	
	/**
	 * Moves one user to another user's location. Used when making a suggestion.
	 * @param u the user who has been suggested and now must move.
	 * @param suggestingUser the user who made the suggestion.
	 */
	void moveUserToSuggester(User u, User suggestingUser) {
		moveUserTo(u, userLocations.get(suggestingUser).intValue());

	}

}
