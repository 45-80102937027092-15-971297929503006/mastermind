package civare.mastermind.windows.index;

import civare.mastermind.eventDrivers.Command;
import civare.mastermind.resourceManagers.constants.Constant;
import civare.mastermind.resourceManagers.constants.ConstantsManager;
import civare.mastermind.resourceManagers.images.ImageManager;
import civare.mastermind.resourceManagers.images.types.Time;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.TimeUnit;

//            System.out.println(new java.text.SimpleDateFormat("hh:mm:ss").format(TimerElement.time));

public class TimerElement extends JPanel implements PropertyChangeListener {

	private final long startTime = 1;
	private final Timer timer;
	private final JLabel mostSigMinDigitLabel;
	private final JLabel leastSigMinDigitLabel;
	private final JLabel mostSigSecDigitLabel;
	private final JLabel leastSigSecDigitLabel;
	private long time;

	private int mostSigMinDigit;
	private int leastSigMinDigit;
	private int mostSigSecDigit;
	private int leastSigSecDigit;

	private boolean isTicking = false;

	public TimerElement() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(new FlowLayout());


//        try {
//            ImageIcon zeroImage = Image.T_ZERO.getImageIcon();
//        } catch (Exception e) {
//            System.out.println("no init image");
//        }

		ImageIcon zero = Time.T_0.getImageIcon();

		mostSigMinDigitLabel = new JLabel(Time.T_0.getImageIcon());
		add(mostSigMinDigitLabel);

		leastSigMinDigitLabel = new JLabel(zero);
		add(leastSigMinDigitLabel);

		JLabel separator = new JLabel(":");
		add(separator);

		mostSigSecDigitLabel = new JLabel(zero);
		add(mostSigSecDigitLabel);

		leastSigSecDigitLabel = new JLabel(zero);
		add(leastSigSecDigitLabel);


		ActionListener taskPerformer = evt -> {

			time += 1000;


			int seconds = (int) (TimeUnit.MILLISECONDS.toSeconds(time)
					- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));

			int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(time);

			mostSigMinDigit = minutes / 10;
			leastSigMinDigit = minutes % 10;

			mostSigSecDigit = seconds / 10;
			leastSigSecDigit = seconds % 10;

			updateImage(mostSigMinDigitLabel, mostSigMinDigit);
			updateImage(leastSigMinDigitLabel, leastSigMinDigit);
			updateImage(mostSigSecDigitLabel, mostSigSecDigit);
			updateImage(leastSigSecDigitLabel, leastSigSecDigit);

			if (minutes == 60) {
				System.out.println("end");
				System.out.println("handle this in TimerElement action listener");
				System.exit(-1);
			}


		};
		int delay = 1000;
		timer = new Timer(delay, taskPerformer);

		time = startTime;
	}

	private static void updateImage(JLabel tile, int digit) {
		switch (digit) {


			case 0: {
				tile.setIcon(Time.T_0.getImageIcon());
				break;
			}
			case 1: {
				tile.setIcon(Time.T_1.getImageIcon());
				break;
			}
			case 2: {
				tile.setIcon(Time.T_2.getImageIcon());
				break;
			}
			case 3: {
				tile.setIcon(Time.T_3.getImageIcon());
				break;
			}
			case 4: {
				tile.setIcon(Time.T_4.getImageIcon());
				break;
			}
			case 5: {
				tile.setIcon(Time.T_5.getImageIcon());
				break;
			}
			case 6: {
				tile.setIcon(Time.T_6.getImageIcon());
				break;
			}
			case 7: {
				tile.setIcon(Time.T_7.getImageIcon());
				break;
			}
			case 8: {
				tile.setIcon(Time.T_8.getImageIcon());
				break;
			}
			case 9: {
				tile.setIcon(Time.T_9.getImageIcon());
				break;
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(TestFrame::new);
	}

	public boolean isTicking() {
		return isTicking;
	}

	public void startOrContinueTimer() {

//        if (!isStarted) {
		timer.start();
		isTicking = true;


//            isStarted = true;
//        } else {
//            System.out.println("timer already started");
//        }
	}

	public void stopTimer() {

		timer.stop();
//        isTicking = false;
	}


	public void restartTimer() {

		timer.stop();

		time = startTime;

		isTicking = false;

		mostSigMinDigitLabel.setIcon(Time.T_0.getImageIcon());
		leastSigMinDigitLabel.setIcon(Time.T_0.getImageIcon());
		mostSigSecDigitLabel.setIcon(Time.T_0.getImageIcon());
		leastSigSecDigitLabel.setIcon(Time.T_0.getImageIcon());

	}

	public String getTime() {
		return String.valueOf(mostSigMinDigit + leastSigMinDigit + mostSigSecDigit + leastSigSecDigit);
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getNewValue() == Command.RESTART_TIMER) {
			System.out.println("property changed: restarting timer");
			restartTimer();
		} else if (evt.getNewValue() == Command.STOP_TIMER) {
			System.out.println("property changed: stopping timer");
			stopTimer();
		} else {
			System.out.println("unsupported command in timer element");
			System.out.println(evt);
			System.out.println();
		}
	}

	private static class TestFrame extends JFrame {
		TestFrame() {
			ConstantsManager.initializeConstants();
			setVisible(true);
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			setLayout(new FlowLayout());

			setSize(((Double) Constant.WIDTH.getValue()).intValue(), ((Double) Constant.HEIGHT.getValue()).intValue());
			setLocation((Integer) Constant.LOCATION_X.getValue(), (Integer) Constant.LOCATION_Y.getValue());

			TimerElement timer = new TimerElement();

			add(timer);

			JButton startButton = new JButton("start/continue");
			startButton.addActionListener(e -> timer.startOrContinueTimer());
			add(startButton);


			JButton stop = new JButton("stop");
			stop.addActionListener(e -> timer.stopTimer());
			add(stop);

			JButton restart = new JButton("restart");
			restart.addActionListener(e -> timer.restartTimer());
			add(restart);

		}
	}
}
