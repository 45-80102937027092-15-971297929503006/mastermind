package civare.mastermind.resourceManagers.images.types;

import civare.mastermind.resourceManagers.images.Common;

import javax.swing.*;

public enum Pegs implements Common {
	BLACK,
	BLUE,
	RED,
	;

	public static void main(String[] args) {
//		System.out.println(BLACK.getPath());

		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton button = new JButton("alt text");

		button.setIcon(BLUE.getImageIcon());

		button.addActionListener(e -> {
			System.out.println("pressed");
		});
		f.add(button);

		f.setBounds(300, 200, 400, 300);
		f.setVisible(true);

	}

}
