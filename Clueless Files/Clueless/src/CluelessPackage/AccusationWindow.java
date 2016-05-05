package CluelessPackage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AccusationWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2823691096941989424L;

	private JPanel contentPane;
	private UserUI UI;
	JButton btnAccuse;
	
	JComboBox characterComboBox;
	String[] characters = { "Miss Scarlet","Professor Plum","Col Mustard","Mrs. Peacock","Mr. Green","Mrs. White"};
	
	JComboBox weaponComboBox;
	String[] weapons = { "Candlestick","Dagger","Lead Pipe","Revolver","Rope","Spanner"};
	
	JComboBox roomComboBox;
	String[] rooms = { "Conservatory","Billiard Room","Library","Ballroom","Stairway","Hall1","Kitchen","Dining Room","Hall2"};
	
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
	 * @param thisUI 
	 */
	AccusationWindow(UserUI thisUI) {
		UI= thisUI;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 484, 271);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblYouveMadeAnd = new JLabel("You Have Made An Accusation");
		lblYouveMadeAnd.setBounds(154, 27, 300, 14);
		contentPane.add(lblYouveMadeAnd);
		
		JLabel characterLabel = new JLabel("Character");
		characterLabel.setBounds(57, 52, 58, 14);
		contentPane.add(characterLabel);
		
		JLabel weaponLabel = new JLabel("Weapon");
		weaponLabel.setBounds(194, 52, 58, 14);
		contentPane.add(weaponLabel);
		
		JLabel roomLabel = new JLabel("Room");
		roomLabel.setBounds(336, 52, 58, 14);
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
		
		btnAccuse = new JButton("ACCUSE!");
		btnAccuse.setBounds(174, 199, 89, 23);
		contentPane.add(btnAccuse);
		//btnAccuse.addActionListener(new AccusationWindowListener(this));
		
		btnAccuse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateTextFields();
				String character = getCharacter();
				String weapon = getWeapon();;
				String room = getRoom();
				try {
					passOnAccusation(character, weapon, room);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		AccusationWindow frame = this;
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

		void passOnAccusation(String character, String weapon, String room) throws Exception {
			UI.passOnAccusation(character, weapon, room);
			
			setVisible(false);
			dispose();
		
	}

}
