import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class GameScreen {

	private JFrame frame;
	private GameEnvironment game;
	private JLabel lblStuff;
	private JLabel lblCrewMembers;
	
	
	public GameScreen(GameEnvironment inGame) {
		game = inGame;
		initialize();
		setLabels();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		game.closeGameGui(this);
	}
	
	public void setLabels() {
		lblStuff.setText(String.format("Days: %d, Pieces: %d, Crew: %d", game.getTotalDays(), game.getPiecesNeeded(), game.getNumberOfCrew()));
		lblCrewMembers.setText("Crew members:\n" + game.getCrew().getCrewMemberList());
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameScreen window = new GameScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(335, 227, 89, 23);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		frame.getContentPane().add(btnQuit);
		
		lblStuff = new JLabel("Days: x, Pieces: x, Crew: x");
		lblStuff.setBounds(10, 11, 414, 14);
		frame.getContentPane().add(lblStuff);
		
		lblCrewMembers = new JLabel("Crew members:");
		lblCrewMembers.setBounds(10, 61, 313, 159);
		frame.getContentPane().add(lblCrewMembers);
	}
}
