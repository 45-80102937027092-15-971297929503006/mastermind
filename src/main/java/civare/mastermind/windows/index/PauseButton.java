package civare.mastermind.windows.index;

import civare.mastermind.resourceManagers.images.types.Stop;

import javax.swing.*;

public class PauseButton extends JButton {

	//    private final PropertyChangeSupport support;
	private final State state = State.NORMAL;

	public PauseButton() {

//        this.setIcon(Image.STOP.getImageIcon());
	}

	public State getState() {
		return state;
	}

	public void stopGame() {
		this.setIcon(Stop.PAUSE.getImageIcon());
	}


	enum State {
		PAUSED,
		NORMAL,

	}
//
//    public void
}
