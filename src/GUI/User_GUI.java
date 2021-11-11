package GUI;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class User_GUI extends JFrame {
	JFrame User_P = new JFrame();

	public User_GUI() {
		init();

	}

	private void init() {
		this.add(User_P);
		this.setBounds(100, 100, 300, 100);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

}
