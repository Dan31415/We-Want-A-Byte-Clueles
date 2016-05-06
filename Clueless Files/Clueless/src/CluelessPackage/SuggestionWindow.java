package CluelessPackage;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class SuggestionWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6711991319947366861L;
	private JPanel contentPane;
	
	JComboBox characterComboBox;
	String[] characters = { "Miss Scarlet","Professor Plum","Col Mustard","Mrs. Peacock","Mr. Green","Mrs. White"};
	
	JComboBox weaponComboBox;
	String[] weapons = { "Candlestick","Dagger","Lead Pipe","Revolver","Rope","Spanner"};
	
	JComboBox roomComboBox;
	String[] rooms = { "Conservatory","Billiard Room","Library","Ballroom","Stairway","Hall1","Kitchen","Dining Room","Hall2"};
	UserUI userUI;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccusationWindow frame = new AccusationWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param userUI 
	 */
	 SuggestionWindow(UserUI userUI) {
		this.userUI = userUI;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 484, 271);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblYouveMadeAnd = new JLabel("You Must Make A Suggestion");
		lblYouveMadeAnd.setBounds(154, 27, 300, 14);
		contentPane.add(lblYouveMadeAnd);
		
		JLabel characterLabel = new JLabel("Character");
		characterLabel.setBounds(61, 62, 58, 14);
		contentPane.add(characterLabel);
		
		JLabel weaponLabel = new JLabel("Weapon");
		weaponLabel.setBounds(198, 62, 58, 14);
		contentPane.add(weaponLabel);
		
		JLabel roomLabel = new JLabel("Room");
		roomLabel.setBounds(340, 62, 58, 14);
		contentPane.add(roomLabel);
		
		characterComboBox = new JComboBox(characters);
		characterComboBox.setBounds(21, 87, 132, 20);
		contentPane.add(characterComboBox);
		
		weaponComboBox = new JComboBox(weapons);
		weaponComboBox.setBounds(163, 87, 136, 20);
		contentPane.add(weaponComboBox);
		
		roomComboBox = new JComboBox(rooms);
		roomComboBox.setBounds(309, 87, 149, 20);
		contentPane.add(roomComboBox);
		
		JButton btnSuggest= new JButton("SUGGEST!");
		btnSuggest.setBounds(151, 133, 159, 23);
		contentPane.add(btnSuggest);
		SuggestionWindow frame = this;
		//btnSuggest.addActionListener(new SuggestionWindowListener(this));
		btnSuggest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateTextFields();
				String character = getCharacter();
				String weapon = getWeapon();;
				String room = getRoom();
				passOnSuggestion(character, weapon, room);
			}
		});
		
		
		
		frame.setVisible(true);
		
	}

	 void updateTextFields() {
		 characterComboBox.validate();
			weaponComboBox.validate();
			roomComboBox.validate();
	}

	 String getCharacter() {
			return characterComboBox.getSelectedItem().toString();
		}
		
		String getWeapon() {
			return weaponComboBox.getSelectedItem().toString();
		}
		
		String getRoom() {
			return roomComboBox.getSelectedItem().toString();
		}

	 void passOnSuggestion(String character, String weapon, String room) {
		try {
			userUI.passOnSuggestion(character, weapon, room);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setVisible(false);
		dispose();
		
	}
	 
	 /**Causes trying to close the window to have the same effect as clicking the suggestion button.
	 * @param e
	 */
	public void windowClosing(WindowEvent e){updateTextFields();
		String character = getCharacter();
		String weapon = getWeapon();;
		String room = getRoom();
		passOnSuggestion(character, weapon, room);
	}
     	
     }


