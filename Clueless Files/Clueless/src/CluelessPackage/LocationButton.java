package CluelessPackage;

import javax.swing.JButton;

public class LocationButton extends JButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7457032125628661450L;
	int locationNumber;
	
	
	public LocationButton(String name, int locNumber) {
	this.setText(name);
	locationNumber = locNumber;
	
}
}
