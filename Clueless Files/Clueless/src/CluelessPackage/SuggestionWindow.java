package CluelessPackage;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class SuggestionWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6711991319947366861L;
	private JPanel contentPane;
	private JTextField characterTextField;
	private JTextField weaponTextField;
	private JTextField roomTextField;
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
		
		JLabel lblbutThisDoesnt = new JLabel("(but this doesnt do anything yet)");
		lblbutThisDoesnt.setBounds(154, 79, 300, 14);
		contentPane.add(lblbutThisDoesnt);
		
		JLabel characterLabel = new JLabel("Character");
		characterLabel.setBounds(57, 126, 58, 14);
		contentPane.add(characterLabel);
		
		JLabel weaponLabel = new JLabel("Weapon");
		weaponLabel.setBounds(194, 126, 58, 14);
		contentPane.add(weaponLabel);
		
		JLabel roomLabel = new JLabel("Room");
		roomLabel.setBounds(336, 126, 58, 14);
		contentPane.add(roomLabel);
		
		characterTextField = new JTextField();
		characterTextField.setText("");
		characterTextField.setBounds(39, 151, 86, 20);
		contentPane.add(characterTextField);
		characterTextField.setColumns(10);
		
		weaponTextField = new JTextField();
		weaponTextField.setText("");
		weaponTextField.setColumns(10);
		weaponTextField.setBounds(166, 151, 97, 20);
		contentPane.add(weaponTextField);
		
		roomTextField = new JTextField();
		roomTextField.setText("");
		roomTextField.setColumns(10);
		roomTextField.setBounds(308, 151, 86, 20);
		contentPane.add(roomTextField);
		
		JButton btnSuggest= new JButton("SUGGEST!");
		btnSuggest.setBounds(174, 199, 89, 23);
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
		characterTextField.validate();
		weaponTextField.validate();
		roomTextField.validate();
	}

	 String getCharacter() {
		return characterTextField.getText();
	}
	
	 String getWeapon() {
		return weaponTextField.getText();
	}
	
	 String getRoom() {
		return roomTextField.getText();
	}

	 void passOnSuggestion(String character, String weapon, String room) {
		userUI.passOnSuggestion(character, weapon, room);
		setVisible(false);
		dispose();
		
	}

}
