package civare.mastermind.windows.index;

import civare.mastermind.eventDrivers.Command;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

//    TODO
//      enable/ disable left click operations while under right click element (flag, question mark)

public class CenterPanel extends JPanel implements PropertyChangeListener {
	JButton timer;
	JButton timer2;
	JButton timer3;

	JButton restart;
	JButton resultLeft;
	JButton resultRight;
	JButton startStop;

	public CenterPanel() {
		support = new PropertyChangeSupport(this);

//		timer
		timer = new JButton("start/continue timer");
		timer.addActionListener(e -> {
			support.firePropertyChange("game won", null, Command.START_OR_CONTINUE_TIMER);
		});
		add(timer);

		timer2 = new JButton("stop timer");
		timer2.addActionListener(e -> {
			support.firePropertyChange("", null, Command.STOP_TIMER);
		});
		add(timer2);


		timer3 = new JButton("restart timer");
		timer3.addActionListener(e -> {
			support.firePropertyChange("", null, Command.RESTART_TIMER);
		});
		add(timer3);

//		restart button
		JButton dead = new JButton("game lost");
		dead.addActionListener(e -> {
			support.firePropertyChange("", null, Command.GAME_OVER);
		});
		add(dead);

		JButton won = new JButton("game won");
		won.addActionListener(e -> {
			support.firePropertyChange("", null, Command.GAME_WON);
		});
		add(won);

		startStop = new JButton("start stop");
		add(startStop);

	}

    private  PropertyChangeSupport support;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() == Command.NEW_GAME) {
//            restart();
        } else {
            System.out.println("unsupported command in center panel");
            System.out.println(evt);
        }
    }

    public void addListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removeListener(PropertyChangeListener p) {
        support.removePropertyChangeListener(p);
    }

}
