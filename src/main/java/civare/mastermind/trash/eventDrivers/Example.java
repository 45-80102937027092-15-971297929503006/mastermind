package civare.mastermind.trash.eventDrivers;

import java.awt.BorderLayout;
import java.beans.*;

import javax.swing.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
/*w ww. j  a va 2  s . c  o m*/
import javax.swing.JButton;
import javax.swing.JFrame;

public class Example implements PropertyChangeListener {
	public static void main(String args[]) {
		SwingUtilities.invokeLater(() -> {
			new Example();
		});
	}
	private final PropertyChangeSupport support;

	Button btn;

	Example() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 100);
		frame.setVisible(true);

		support = new PropertyChangeSupport(this);

        btn = new Button();

		btn.addListener(this);

		frame.add(btn, BorderLayout.SOUTH);

	}

	static class Button extends JButton implements PropertyChangeListener {
		private final PropertyChangeSupport support;

		public Button() {
			this.setIcon(civare.mastermind.resourceManagers.images.types.Button.PLAY_AGAIN.getImageIcon());
			support = new PropertyChangeSupport(this);

			addActionListener(e -> {
				System.out.println("button fired");
				support.firePropertyChange("center panel", null, Command.NEW_GAME);
			});

		}
		public void addListener(PropertyChangeListener listener) {
			support.addPropertyChangeListener(listener);
		}
		public void removeListener(PropertyChangeListener propertyChangeListener) {
			support.removePropertyChangeListener(propertyChangeListener);
		}

		@Override
		public void propertyChange(PropertyChangeEvent evt) { }
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		if (evt.getSource() == btn) {
			System.out.println("button caught");

			if (evt.getNewValue() == Command.NEW_GAME) {
				System.out.println("new game command");
			} else {
				System.out.println("other command");
			}

		} else {
			System.out.println("sth else");
		}
	}


}
