import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.SwingConstants;

public class GameScreen {

	private JFrame frame;
	private GameEnvironment game;
	private JLabel lblStuff;
	private JLabel lblCrewMembers;
	private JLabel lblPlanet;
	private JLabel lblDays;
	private JLabel lblCrew;
	private JLabel lblCrew_1;
	private JLabel lblCrew_2;
	private JLabel lblCrew_3;
	private JLabel lblCrewMembers_1;
	private JButton btnCrewStatus;
	private JButton btnShipStatus;
	private JButton btnVisitOutpost;
	private JButton btnUseItem;
	private JButton btnSleep;
	private JButton btnRepairShip;
	private JButton btnSearchPlanet;
	private JButton btnPilotShip;
	
	
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
	
	public void nextDay() {
		// check for if everyone dead maybe
		if(game.getCurrentDay() == game.getTotalDays()) {
			finishedWindow();
		} else if(game.getCrew().getPiecesFound() == game.getPiecesNeeded()) {
			finishedWindow();
		} else {
			game.newDay();
			setLabels();
		}
	}
	
	public void takeAction(int num) {
		game.takeAction(num);
		setCrewLabels();
	}
	
	public void setLabels() {
		
		lblStuff.setText(String.format("Days: %d, Pieces: %d, Crew: %d", game.getTotalDays(), game.getPiecesNeeded(), game.getNumberOfCrew()));
		lblCrewMembers.setText("Crew members:\n" + game.getCrew().getCrewMemberList());
		lblPlanet.setText(game.getCrew().getTheShip().getLocation().getName());
		lblDays.setText(String.format("Days %d/%d", game.getCurrentDay(), game.getTotalDays()));
		setCrewLabels();
	}

	public void setCrewLabels() {
		int crewSize = game.getNumberOfCrew();
		ArrayList<CrewMember> members = game.getCrew().getCrewMemberList();
		
		switch(crewSize) {
			case 2:
				lblCrew.setText(String.format("%s: %s (%d/2)", members.get(0).getName(), members.get(0).getClassType(), members.get(0).getActionsRemaining()));
				lblCrew_1.setText(String.format("%s: %s (%d/2)", members.get(1).getName(), members.get(1).getClassType(), members.get(1).getActionsRemaining()));
				lblCrew_2.setText("");
				lblCrew_3.setText("");
				break;
			case 3:
				lblCrew.setText(String.format("%s: %s (%d/2)", members.get(0).getName(), members.get(0).getClassType(), members.get(0).getActionsRemaining()));
				lblCrew_1.setText(String.format("%s: %s (%d/2)", members.get(1).getName(), members.get(1).getClassType(), members.get(1).getActionsRemaining()));
				lblCrew_2.setText(String.format("%s: %s", members.get(2).getName(), members.get(2).getClassType()));
				lblCrew_3.setText("");
				break;
			case 4:
				lblCrew.setText(String.format("%s: %s (%d/2)", members.get(0).getName(), members.get(0).getClassType(), members.get(0).getActionsRemaining()));
				lblCrew_1.setText(String.format("%s: %s (%d/2)", members.get(1).getName(), members.get(1).getClassType(), members.get(1).getActionsRemaining()));
				lblCrew_2.setText(String.format("%s: %s", members.get(2).getName(), members.get(2).getClassType()));
				lblCrew_3.setText(String.format("%s: %s", members.get(3).getName(), members.get(3).getClassType()));
				break;
			default:
				lblCrew.setText("Error");
				lblCrew_1.setText("");
				lblCrew_2.setText("");
				lblCrew_3.setText("");
		}
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
		frame.setBounds(100, 100, 870, 483);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblCrewMembers = new JLabel("Crew members:");
		lblCrewMembers.setBounds(611, 121, 217, 42);
		frame.getContentPane().add(lblCrewMembers);
		
		lblStuff = new JLabel("Days: x, Pieces: x, Crew: x");
		lblStuff.setBounds(611, 174, 217, 42);
		frame.getContentPane().add(lblStuff);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(637, 357, 217, 87);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		frame.getContentPane().add(btnQuit);
		
		lblDays = new JLabel("Day x/x");
		lblDays.setHorizontalAlignment(SwingConstants.CENTER);
		lblDays.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 24));
		lblDays.setBounds(10, 11, 163, 65);
		frame.getContentPane().add(lblDays);
		
		JButton btnNexDay = new JButton("Next Day");
		btnNexDay.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		btnNexDay.setBounds(681, 11, 163, 65);
		btnNexDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextDay();
			}
		});
		frame.getContentPane().add(btnNexDay);
		
		lblPlanet = new JLabel("Planet");
		lblPlanet.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		lblPlanet.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlanet.setBounds(210, 11, 376, 65);
		frame.getContentPane().add(lblPlanet);
		
		lblCrew = new JLabel("Crew1");
		lblCrew.setFont(new Font("Comic Sans MS", Font.ITALIC, 16));
		lblCrew.setBounds(20, 168, 219, 49);
		frame.getContentPane().add(lblCrew);
		
		lblCrew_1 = new JLabel("Crew2");
		lblCrew_1.setFont(new Font("Comic Sans MS", Font.ITALIC, 16));
		lblCrew_1.setBounds(20, 243, 219, 42);
		frame.getContentPane().add(lblCrew_1);
		
		lblCrew_2 = new JLabel("Crew3");
		lblCrew_2.setFont(new Font("Comic Sans MS", Font.ITALIC, 16));
		lblCrew_2.setBounds(20, 309, 219, 42);
		frame.getContentPane().add(lblCrew_2);
		
		lblCrew_3 = new JLabel("Crew4");
		lblCrew_3.setFont(new Font("Comic Sans MS", Font.ITALIC, 16));
		lblCrew_3.setBounds(20, 376, 219, 42);
		frame.getContentPane().add(lblCrew_3);
		
		lblCrewMembers_1 = new JLabel("Crew Members:");
		lblCrewMembers_1.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblCrewMembers_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrewMembers_1.setBounds(10, 113, 147, 50);
		frame.getContentPane().add(lblCrewMembers_1);
		
		btnCrewStatus = new JButton("Crew Status");
		btnCrewStatus.setBounds(249, 121, 115, 42);
		frame.getContentPane().add(btnCrewStatus);
		
		btnShipStatus = new JButton("Ship Status");
		btnShipStatus.setBounds(439, 121, 115, 42);
		frame.getContentPane().add(btnShipStatus);
		
		btnVisitOutpost = new JButton("Visit Outpost");
		btnVisitOutpost.setBounds(249, 203, 115, 42);
		frame.getContentPane().add(btnVisitOutpost);
		
		btnUseItem = new JButton("Use Item");
		btnUseItem.setBounds(439, 203, 115, 42);
		btnUseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takeAction(1);
			}
		});
		frame.getContentPane().add(btnUseItem);
		
		btnSleep = new JButton("Sleep");
		btnSleep.setBounds(249, 280, 115, 42);
		btnSleep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takeAction(2);
			}
		});
		frame.getContentPane().add(btnSleep);
		
		btnRepairShip = new JButton("Repair Ship");
		btnRepairShip.setBounds(439, 280, 115, 42);
		btnRepairShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takeAction(3);
			}
		});
		frame.getContentPane().add(btnRepairShip);
		
		btnSearchPlanet = new JButton("Search Planet");
		btnSearchPlanet.setBounds(249, 356, 115, 42);
		btnSearchPlanet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takeAction(4);
			}
		});
		frame.getContentPane().add(btnSearchPlanet);
		
		btnPilotShip = new JButton("Pilot Ship");
		btnPilotShip.setBounds(439, 356, 115, 42);
		btnPilotShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takeAction(5);
			}
		});
		frame.getContentPane().add(btnPilotShip);
	}
}
