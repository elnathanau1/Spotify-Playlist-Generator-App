package display;

import javax.swing.*;

public class GUI {
	private final int DISPLAY_WIDTH;
	private final int DISPLAY_HEIGHT;
	private final int HEADER_OFFSET;
	private JFrame mainFrame;

	public GUI(int displayWidth, int displayHeight) {
		DISPLAY_WIDTH = displayWidth;
		DISPLAY_HEIGHT = displayHeight;
		HEADER_OFFSET = 18;						//to compensate for header bar
		prepareGUI();

	}

	/**
	 * Sets up GUI, only called once
	 */
	private void prepareGUI(){
		mainFrame = new JFrame("Spotify App");
		mainFrame.setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT + HEADER_OFFSET);

		mainFrame.setVisible(true);
	}
}
