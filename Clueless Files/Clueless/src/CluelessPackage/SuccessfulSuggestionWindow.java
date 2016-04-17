package CluelessPackage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class SuccessfulSuggestionWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6711991319947366861L;
	private JPanel contentPane;

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
	 * @param username 
	 * @param matchingCard 
	 * @param userUI 
	 */
	 SuccessfulSuggestionWindow(String matchingCard, String username) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 484, 271);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblYouveMadeAnd = new JLabel("Your Suggestion Revealed The Following:");
		lblYouveMadeAnd.setBounds(154, 27, 300, 14);
		contentPane.add(lblYouveMadeAnd);
		
		JLabel lblNewLabel = new JLabel("Player:");
		lblNewLabel.setBounds(58, 91, 113, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Card:");
		lblNewLabel_1.setBounds(310, 91, 113, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel playerLabel = new JLabel(username);
		playerLabel.setBounds(58, 144, 154, 14);
		contentPane.add(playerLabel);
		
		JLabel cardLabel = new JLabel(matchingCard);
		cardLabel.setBounds(299, 144, 135, 14);
		contentPane.add(cardLabel);
		SuccessfulSuggestionWindow frame = this;
		frame.setVisible(true);
		
	}


}
