package CluelessPackage;


import java.util.ArrayList;
import java.util.Random;

public class Deck {
	ArrayList<String> characterCards;
	ArrayList<String> weaponCards;
	ArrayList<String> roomCards;
	ArrayList<String> deck;
	Random randomGenerator = new Random();
	
	/**
	 * This class implements the singleton pattern for global access.
	 */
	private static Deck thisDeck = null;

	


	
	private Deck(){ 
		characterCards = new ArrayList<String>();
		weaponCards = new ArrayList<String>();
		roomCards = new ArrayList<String>();
		populateCharacterCards();
		populateWeaponCards();
		populateRoomCards();
	}

	static Deck getDeck() {
		if (thisDeck == null){
			thisDeck = new Deck();
		}
		return thisDeck;
	}
	

	private void populateRoomCards() {
		roomCards.add("Conservatory");
		roomCards.add("Billiard Room");
		roomCards.add("Library");
		roomCards.add("Ballroom");
		roomCards.add("Stairway");
		roomCards.add("Hall");
		roomCards.add("Kitchen");
		roomCards.add("Dining Room");
		roomCards.add("Hall");
		
	}

	private void populateWeaponCards() {
		weaponCards.add("Candlestick");
		weaponCards.add("Dagger");
		weaponCards.add("Lead Pipe");
		weaponCards.add("Revolver");
		weaponCards.add("Rope");
		weaponCards.add("Spanner");
		
	}
	

	private void populateCharacterCards(){
		characterCards.add("Miss Scarlett");
		characterCards.add("Professor Plum");
		characterCards.add("Miss Scarlett");
		characterCards.add("Mrs. Peacock");
		characterCards.add("Mr. Green");
		characterCards.add("Mrs. White");
		
	}

	String drawCharacter() {
		int i = randomGenerator.nextInt(characterCards.size()-1);
		return characterCards.get(i);
	}
	
	String drawWeapon() {
		int i = randomGenerator.nextInt(weaponCards.size()-1);
		return weaponCards.get(i);
	}
	
	String drawRoom() {
		int i = randomGenerator.nextInt(roomCards.size()-1);
		return roomCards.get(i);
	}

	void combineDecks() {
		deck = new ArrayList<String>();
		deck.addAll(characterCards);
		deck.addAll(weaponCards);
		deck.addAll(roomCards);
		
		characterCards = null;
		weaponCards= null;
		roomCards= null; 
	}

	boolean stillContainsCards() {
		if (deck.isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}

	 String drawCard() {
		String returnCard = deck.get(randomGenerator.nextInt(deck.size()));
		deck.remove(returnCard);
		return returnCard;
	}

	
}