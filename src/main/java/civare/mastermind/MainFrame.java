package civare.mastermind;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

	private final JButton[][] buttons;
	private final int x = 10;
	private final int y = 10;

	public MainFrame() {
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new GridLayout(0, x));
		setSize(1000, 1000);
		buttons = new JButton[y][x];

//        final BufferedImage source = ImageIO.read(
//                new File(""));
//        int idx = 0;
//        for (int y = 0; y < source.getHeight(); y += 32) {
//            ImageIO.write(source.getSubimage(0, y, 32, 32), "png", new File("<sourceDir>/1fby-6t-555d_" + idx++ + ".png"));
//        }

		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {

				buttons[i][j] = new JButton(i + " " + j);

				this.add(buttons[i][j]);
			}
		}


	}
}
