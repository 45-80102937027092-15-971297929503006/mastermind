package civare.mastermind.windows.index;

import civare.mastermind.eventDrivers.Command;
import civare.mastermind.resourceManagers.constants.Constant;
import civare.mastermind.resourceManagers.constants.ConstantsManager;
import civare.mastermind.windows.settings.SettingsWindowListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class MainFrame extends JFrame implements PropertyChangeListener {

	private final NorthPanel northPanel;
	private final CenterPanel centerPanel;
	private final RestartButton restartButton;

	public MainFrame() {
		super("minesweeper");

//		load constants
		ConstantsManager.initializeConstants();

//		frame default settings
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		setSize(((Double) Constant.WIDTH.getValue()).intValue(),
				((Double) Constant.HEIGHT.getValue()).intValue());

		setLocation((Integer) Constant.LOCATION_X.getValue(), (Integer) Constant.LOCATION_Y.getValue());

//		utils panel
		northPanel = new NorthPanel();
		restartButton = northPanel.getRestartButton();
		add(northPanel, BorderLayout.NORTH);

//		game panel
		centerPanel = new CenterPanel();
		add(centerPanel, BorderLayout.CENTER);

//		listeners
		addWindowListener(new MainFrameWindowListener(this));
//        centerPanel.addListener(northPanel);
		SettingsWindowListener.getInstance().addListener(this);
		SettingsWindowListener.getInstance().addListener(northPanel);
		restartButton.addListener(centerPanel);
		restartButton.addListener(northPanel);
//		addPropertyChangeListener(centerPanel);


		centerPanel.addListener(northPanel);

	}

	/**
	 * delete current {@code MainFrame} and create new
	 * used when current game is done or when settings are changed
	 * this is main mechanism for restarting game
	 */
	public void restartSequence() {
		System.out.println("*** " + (new Throwable().getStackTrace())[0].getMethodName() + " ***");

//		detach linked listeners
		SettingsWindowListener.getInstance().removeListener(this);
		SettingsWindowListener.getInstance().removeListener(northPanel);

		restartButton.removeListener(centerPanel);
//        centerPanel.removeListener(northPanel);
		restartButton.removeListener(northPanel);

//		delete current and create new MainFrame
		dispose();
		new MainFrame();
	}

	/**
	 * waits for change in listeners
	 * @param evt
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("*** " + (new Throwable().getStackTrace())[0].getMethodName() + " ***");

		if (evt.getNewValue() == Command.RESTART_MAINFRAME) {

			restartSequence();

		} else {
			System.out.println("non good var in mainframe ");
		}


	}

	/**
	 * saves all constants on close
	 * logs location and dimensions
	 */
	private static class MainFrameWindowListener implements WindowListener {

		private final JFrame jFrame;

		public MainFrameWindowListener(JFrame jFrame) {
			this.jFrame = jFrame;
		}

		@Override
		public void windowClosing(WindowEvent e) {
			System.out.println("closing");

			Constant.LOCATION_X.setValue(jFrame.getX());
			Constant.LOCATION_Y.setValue(jFrame.getY());

			Constant.WIDTH.setValue(jFrame.getSize().getWidth());
			Constant.HEIGHT.setValue(jFrame.getSize().getHeight());

//            fixme
//            ConstantsManager.updateConstants(Config.getConstantsMemoryPath());
		}

		@Override
		public void windowOpened(WindowEvent e) {
		}

		@Override
		public void windowClosed(WindowEvent e) {
		}

		@Override
		public void windowIconified(WindowEvent e) {
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
		}

		@Override
		public void windowActivated(WindowEvent e) {
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
		}
	}

}
