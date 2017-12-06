package display;

import javax.swing.*;

/**
 * GUI class that holds all the other components
 * @author eau
 *
 */
public class GUI{
	private final int DISPLAY_WIDTH;
	private final int DISPLAY_HEIGHT;
	private final int HEADER_OFFSET;
	
	private JFrame mainFrame;
	
	private static JTabbedPane tabPane;
	//panels for tabbed pane:
	public static StartPane panelOne;
	public static RecTracksPane panelTwo;
	public static RecTracksPane panelThree;
	public static JPanel panelFour;
	public static JPanel panelFive;
	
	public static GUIData data;					//holds global data that other parts of GUI may need to access

	/**
	 * Constructor
	 * @param displayWidth
	 * @param displayHeight
	 */
	public GUI(int displayWidth, int displayHeight) {

		DISPLAY_WIDTH = displayWidth;
		DISPLAY_HEIGHT = displayHeight;
		HEADER_OFFSET = 18;						//to compensate for header bar
		data = new GUIData();
		prepareGUI();

	}

	/**
	 * Sets up GUI, only called once
	 */
	private void prepareGUI(){
		//Set up main JFrame
		mainFrame = new JFrame("Spotify Playlist Generator");
		mainFrame.setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT + HEADER_OFFSET);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Set up panels
		panelOne = new StartPane();
		panelTwo = new RecTracksPane(0);					// 0 for added tracks
		panelThree = new RecTracksPane(1);				// 1 for recommended tracks
		panelFour = new JPanel();
		panelFive = new JPanel();

		//Set up tabs
		tabPane = new JTabbedPane();
		tabPane.add("Start", panelOne);
		tabPane.add("Added Tracks", panelTwo);
		tabPane.add("Recommended Tracks", panelThree);
		tabPane.add("Statistics", panelFour);
		tabPane.add("Extra", panelFive);

		//disable tabs besides "Start" in the beginning
		tabPane.setEnabledAt(1, false);
		tabPane.setEnabledAt(2, false);
		tabPane.setEnabledAt(3, false);
		tabPane.setEnabledAt(4, false);

		mainFrame.add(tabPane);

		mainFrame.setVisible(true);
	}

	/**
	 * Method for other classes to change the tab (especially for classes nested way into other classes)
	 * @param tab
	 */
	public static void changeTab(int tab) {
		tabPane.setSelectedIndex(tab);
	}

	/**
	 * Method to unlock all tabs
	 */
	public static void unlockTabs() {
		for(int i = 0; i < 5; i++) {
			tabPane.setEnabledAt(i, true);
		}
	}
}
