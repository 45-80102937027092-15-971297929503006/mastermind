package civare.mastermind.windows.index;

import civare.mastermind.eventDrivers.Command;
import civare.mastermind.resourceManagers.images.types.Button;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RestartButton extends JButton implements PropertyChangeListener{

	private final PropertyChangeSupport support;

	public RestartButton() {

		this.setIcon(Button.PLAY_AGAIN.getImageIcon());

		support = new PropertyChangeSupport(this);

//        setIcon(Image.PLAY_AGAIN.getImageIcon());

		addActionListener(e -> {
			System.out.println("restart button clicked");

			support.firePropertyChange("center panel", null, Command.NEW_GAME);

			support.firePropertyChange("north panel", null, Command.RESTART_NORTH_PANEL);

		});

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getNewValue() == Command.GAME_WON) {
			System.out.println("game won catched in restart");
		} else {
			System.out.println("somethoing other trigferred");
		}
	}

	public void addListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}

	public void removeListener(PropertyChangeListener propertyChangeListener) {
		support.removePropertyChangeListener(propertyChangeListener);
	}
}
