package CluelessPackage;



import java.awt.Component;
import javax.swing.JFrame;  
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JDialog;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

public class UserUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4112667917539290543L;
	private JPanel contentPane;
	
	
	private JButton accusationButton;
	private JButton endTurnButton;
	private JTextArea cardDisplay;
	private JTextArea chatDisplay;
	
	Chatboard userChat;

		
	private ArrayList<LocationButton> locationButtonList;
	private ArrayList<JLabel> userlabels;
	private ArrayList<JTextField> userLocations;
	public ClientMessenger cMessenger;
private UserUI thisUI = this;
	private User user;
	private JTextField chatEntry;
	private int playersDrawn;
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserUI frame = new UserUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 * @param userView 
	 * @throws Exception 
	 */
	UserUI(User user) throws Exception {
		Chatboard.getChatboard().addUI(this);
		this.user = user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 827, 867);
              //  JScrollPane scrPane = new JScrollPane();
		contentPane = new JPanel();
               // scrPane.add(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		locationButtonList = new ArrayList<LocationButton>();
		userChat = Chatboard.getChatboard();
		
		cMessenger = new ClientMessenger(this);

		
		UserUI frame = this;
		frame.setVisible(true);
		
		frame.setTitle(user.username + " (" +user.character +")");
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 542, 542);
		
		accusationButton = new JButton("Accuse");
		accusationButton.setBounds(594, 55, 180, 50);

				contentPane.setLayout(null);
				
				cardDisplay = new JTextArea();
				cardDisplay.setEditable(false);
				cardDisplay.setBounds(354, 589, 174, 185);
				contentPane.add(cardDisplay);
				
				LocationButton location20 = new LocationButton("Hall", 20);
				location20.setBounds(440, 440, 100, 100);
				locationButtonList.add(location20);
			panel.setLayout(null);
			
			//Add all of the location buttons to a list.
			LocationButton location0 = new LocationButton("Conservatory", 0);
			location0.setBounds(0, 0, 100, 100);
			locationButtonList.add(location0);
			panel.add(location0);
			
			LocationButton location1 = new LocationButton("", 1);
			location1.setBounds(110, 40, 100, 20);
			locationButtonList.add(location1);
			panel.add(location1);
			
			LocationButton location2 = new LocationButton("Billiard Room", 2);
			location2.setBounds(220, 0, 100, 100);
			locationButtonList.add(location2);
			panel.add(location2);
			
			LocationButton location3 = new LocationButton("",3);
			location3.setBounds(330, 40, 100, 20);
			locationButtonList.add(location3);
			panel.add(location3);
			
			LocationButton location4 = new LocationButton("Library", 4);
			location4.setBounds(440, 0, 100, 100);
			locationButtonList.add(location4);
			panel.add(location4);
			
			LocationButton location5 = new LocationButton("", 5);
			location5.setBounds(40, 110, 20, 100);
			locationButtonList.add(location5);
			panel.add(location5);
			
			LocationButton location6 = new LocationButton("", 6);
			location6.setBounds(260, 110, 20, 100);
			locationButtonList.add(location6);
			panel.add(location6);
			
			LocationButton location7 = new LocationButton("", 7);
			location7.setBounds(480, 110, 20, 100);
			locationButtonList.add(location7);
			panel.add(location7);
			
			LocationButton location8 = new LocationButton("Ballroom", 8);
			location8.setBounds(0, 220, 100, 100);
			locationButtonList.add(location8);
			panel.add(location8);
			
				LocationButton location9 = new LocationButton("", 9);
				location9.setBounds(110, 260, 100, 20);
				locationButtonList.add(location9);
				panel.add(location9);
			
			LocationButton location10 = new LocationButton("Stairway", 10);
			location10.setBounds(220, 220, 100, 100);
			locationButtonList.add(location10);
			panel.add(location10);
			
			LocationButton location11 = new LocationButton("", 11);
			location11.setBounds(330, 260, 100, 20);
			locationButtonList.add(location11);
			panel.add(location11);
			
			LocationButton location12 = new LocationButton("Hall", 12);
			location12.setBounds(440, 220, 100, 100);
			locationButtonList.add(location12);
			panel.add(location12);
			
			LocationButton location13 = new LocationButton("", 13);
			location13.setBounds(40, 330, 20, 100);
			locationButtonList.add(location13);
			panel.add(location13);
			
			LocationButton location14 = new LocationButton("", 14);
			location14.setBounds(260, 330, 20, 100);
			locationButtonList.add(location14);
			panel.add(location14);
			
			LocationButton location15 = new LocationButton("", 15);
			location15.setBounds(480, 330, 20, 100);
			locationButtonList.add(location15);
			panel.add(location15);
			
			LocationButton location16 = new LocationButton("Kitchen", 16);
			location16.setBounds(0, 440, 100, 100);
			locationButtonList.add(location16);
			panel.add(location16);
			
			LocationButton location17 = new LocationButton("", 17);
			location17.setBounds(110, 480, 100, 20);
			locationButtonList.add(location17);
			panel.add(location17);
			
			LocationButton location18 = new LocationButton("Dining Room", 18);
			location18.setBounds(220, 440, 100, 100);
			locationButtonList.add(location18);
			panel.add(location18);
			
			LocationButton location19 = new LocationButton("", 19);
			location19.setBounds(330, 480, 100, 20);
			locationButtonList.add(location19);
			panel.add(location19);
			panel.add(location20);
		contentPane.add(panel);
		
		
		contentPane.add(accusationButton);
		//accusationButton.addActionListener(new AccusationListener(this));
		
		accusationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AccusationWindow(thisUI);
				
			}





			
		});
		
		for (LocationButton b: locationButtonList){
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					moveRequest(b.locationNumber);
				}
			});
		}
		
		
		endTurnButton = new JButton("End Turn");
		endTurnButton.setBounds(594, 156, 180, 50);
		contentPane.add(endTurnButton);
		
		userLocations = new ArrayList<JTextField>();
		JTextField player1loc = new JTextField();
		player1loc.setBounds(741, 256, 33, 20);
		contentPane.add(player1loc);
		player1loc.setColumns(10);
		userLocations.add(player1loc);
		
		JTextField player2loc = new JTextField();
		player2loc.setColumns(10);
		player2loc.setBounds(741, 301, 33, 20);
		contentPane.add(player2loc);
		userLocations.add(player2loc);
		
		JTextField player3loc = new JTextField();
		player3loc.setColumns(10);
		player3loc.setBounds(741, 341, 33, 20);
		contentPane.add(player3loc);
		userLocations.add(player3loc);
		
		userlabels = new ArrayList<JLabel> ();
		
		JLabel user1Label = new JLabel("Player 1");
		user1Label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		user1Label.setBounds(562, 259, 169, 14);
		contentPane.add(user1Label);
		userlabels.add(user1Label);
		
		
		JLabel user2Label = new JLabel("Player 2");
		user2Label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		user2Label.setBounds(562, 304, 169, 14);
		contentPane.add(user2Label);
		userlabels.add(user2Label);
		
		JLabel user3Label = new JLabel("Player 3");
		user3Label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		user3Label.setBounds(562, 344, 169, 14);
		contentPane.add(user3Label);
		userlabels.add(user3Label);
		
		playersDrawn = 0; // initially, only single player connected
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLocation.setBounds(706, 231, 84, 14);
		contentPane.add(lblLocation);
		
		JLabel lblYourCards = new JLabel("Your Cards");
		lblYourCards.setBounds(408, 564, 97, 14);
		contentPane.add(lblYourCards);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 564, 287, 162);
		contentPane.add(scrollPane);
		
		setChatDisplay(new JTextArea());
		scrollPane.setViewportView(getChatDisplay());
		
		chatEntry = new JTextField();
		chatEntry.setBounds(20, 737, 287, 20);
		contentPane.add(chatEntry);
		chatEntry.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Chatboard.getChatboard().sendUserMessage(user.username, chatEntry.getText() );
			}
		});
		btnSend.setBounds(117, 768, 89, 23);
		contentPane.add(btnSend);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(563, 589, 227, 185);
		contentPane.add(editorPane);
		
                JButton helpButton = new JButton("Help");
                helpButton.setBounds(594, 500, 180, 50);
                helpButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        helpEvent();
                    }
                
                });
                contentPane.add(helpButton);
                
                
		JLabel lblNotePad = new JLabel("Note Pad");
		lblNotePad.setBounds(652, 564, 97, 14);
		contentPane.add(lblNotePad);
		endTurnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				endTurnRequested();
			}
		});
                
   
                  
	}
	
	User getCurrentUser(){
		
		return this.user;
		
	}
	
	int getPlayersDrawn() {
		return playersDrawn;
	}
	
	void addPlayer(String username, String character) {
		userlabels.get(playersDrawn).setText(username+" ("+character+")");
		playersDrawn ++;
	}
	
	void deactivateAllButtonsExceptChat() {
		for (LocationButton b: locationButtonList){
			b.setEnabled(false);	
		}
		accusationButton.setEnabled(false);
		endTurnButton.setEnabled(false);
	}

	void enableLocations(int[] validMoves) {
		for (int i : validMoves){
			for (LocationButton b: locationButtonList){
				
				if (b.locationNumber == i){
				b.setEnabled(true);
			}
		}
		}
	}
	void enableAccuseButton() {
		accusationButton.setEnabled(true);
		
	}
	
	void enableEndTurnButton() {
		endTurnButton.setEnabled(true);
		
	}
	
	 void deactivateMovement() {
		for (LocationButton b: locationButtonList){
			b.setEnabled(false);	
		}
	}


	 void moveRequest(int location) {
		user.moveTo(location);
		
	}

	 void endTurnRequested() {
		user.endTurn();
		
	}


	 void openSuggestionWindow() {
		new SuggestionWindow(this);
		
	}

	 void passOnAccusation(String character, String weapon, String room) {
		user.makeAccusation(character, weapon, room);
	}
	
	 void passOnSuggestion(String character, String weapon, String room) {
		
		user.makeSuggestion(character, weapon, room);
	}

	 void setPlayer(int i, User thisUser) {
		userlabels.get(i).setText(thisUser.username+" ("+thisUser.character+")");
		
	}

	 void setPlayerLocation(int i, String location) {
		userLocations.get(i).setText(location);
		
	}

	 void deactivateAccusationButton() {
		accusationButton.setEnabled(false);
		
	}

	 void updateCardList(ArrayList<String> cards) {
		for (String s: cards){
			appendToTextArea(cardDisplay, s);
		}
		
		
	}

	private void appendToTextArea(JTextArea pane, String s) {
		String currentPaneContents = pane.getText();
		String newPaneContents = currentPaneContents+"\n"+s;
		pane.setText(newPaneContents);
		pane.validate();
		
	}

	void makeSuccessfulSuggestionWindow(String matchingCard, String username) {
		System.out.println("view notified");
		new SuccessfulSuggestionWindow(matchingCard, username);
		
	}

	void addChatLine(String string) {
		
		
		appendToTextArea(getChatDisplay(), string);
		
	}

	public JTextArea getChatDisplay() {
		return chatDisplay;
	}
	
	public void setChatDisplayText(String chatDisplay){
		
		this.chatDisplay.setText(chatDisplay);
		
	}
	
	public void setChatDisplay(JTextArea chatDisplay){
		
		this.chatDisplay = chatDisplay;
		
	}
        
        public void helpEvent() {

            JTabbedPane tabbedPane = new JTabbedPane();
            JComponent panel1 = makeTextPanel1();
            tabbedPane.add("Rules",panel1);
            
            JDialog jd = new JDialog();
            jd.add(tabbedPane);
            jd.setBounds(0,0,600,800);
            jd.setVisible(true);
            

            
            

        }
        
        protected JComponent makeTextPanel1() {
            
            JPanel panel = new JPanel(false);
            String text1 = "Clue Game Rules \n \n" ;
            String text2 = "Number of Players : 3 - 6 \n\n"
                    + "Goal: Correctly name the murderer, murder weapon, and murder location \n\n"
                    + "9 Rooms, 12 Hallways, 2 Secret Passges \n\n"
                    + "The cards contain 6 suspects, six weapons, and 9 rooms \n"
                    + "\n"
                    + "At the beginning of the game, a guilty person, weapon, and room \n"
                    + "are selected by the game randomly and the remaining cards are \n"
                    + "distributed evenly to the players \n\n"
                    + "Make sure to pay attention to what cards are in your hand\n"
                    + "Because they are in your hand, your cards could not have\n"
                    + "been involved in the crime!\n\n"
                    
                    + "Miss Scarlet is first and players turns moves clockwise from there \n"
                    + "On your turn, move about the game board\n"
                    + "In this version there are limited movements \n\n"
                    + "Movements are as follows: \n"
                    + "- Allowed a single move per turn \n"
                    + "- Must be in a room to make a suggestion \n"
                    + "- Can only use secret passage ways in rooms where it is available \n"
                    + "- If both hallways are blocked and there is no secret passageway, \n"
                    + "  the only move you can make is an accusation\n\n"
                    + "Making a Suggestion: \n"
                    + "- You must make suggestions to figure out the crime information\n"
                    + "- Suggestions can only include the room you are currently in\n"
                    + "- If you want to make a second suggestion, you must\n"
                    + " leave and then re-enter the room\n"
                    + "- Each player will have the chance to dissprove a suggestion\n\n"
                    + "Making an Accusation: \n"
                    + "- Each player can only make on accusation the entire game\n"
                    + "- Can be in any room to manke an accusation\n"
                    + "- If accusation is correct, that player wins the game\n"
                    + "- If accusation is incorrect, that player is out\n";
                   
            
            JTextArea filler = new JTextArea();
            filler.setAlignmentX(Component.CENTER_ALIGNMENT);
            filler.append(text1);
            filler.append(text2);
 
            panel.add(filler);
            return panel;
        }
}