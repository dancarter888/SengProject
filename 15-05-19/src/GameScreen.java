import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.SwingConstants;

public class GameScreen {

	private JFrame frame;
	private GameEnvironment game;
	private CrewMember memberSelected;
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
	private JButton select_1;
	private JButton select_2;
	private JButton select_3;
	private JLabel lblCrewMemberSelected;
	
	
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
		if((game.getCurrentDay() == game.getTotalDays()) || (game.getNumberOfCrew() == 0)) {
			JOptionPane.showMessageDialog(frame, "You Ran Out of Days!");
			finishedWindow();
		} else if(game.getCrew().getPiecesFound() == game.getPiecesNeeded()) {
			JOptionPane.showMessageDialog(frame, "You Found All The Pieces!");
			finishedWindow();
		} else {
			game.newDay();
			if(game.getNumberOfCrew() > 0) {
				setLabels();
			} else {
				JOptionPane.showMessageDialog(frame, "All Crew Members Are Dead!");
				finishedWindow();
			}
		}
	}
	
	public void visitOutPost() {
		game.launchOutPostGui(this);
	}
	
	public void takeAction(int num) {
		game.takeAction(num, memberSelected);
		setCrewLabels();
	}
	
	public void crewStatus() {
		String membersString = "";
		ArrayList<CrewMember> members = game.getCrew().getCrewMemberList();
		
		for(CrewMember member : members) {
			membersString += member.toString() + "\n";
		}
		
		JOptionPane.showMessageDialog(frame, membersString);
	}
	
	public void shipStatus() {
		String shipString = "Ship Status:\n";
		shipString += game.getCrew().getShipStatus();
		
		JOptionPane.showMessageDialog(frame, shipString);
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
				frame.remove(select_2);
				lblCrew_3.setText("");
				frame.remove(select_3);
				break;
			case 3:
				lblCrew.setText(String.format("%s: %s (%d/2)", members.get(0).getName(), members.get(0).getClassType(), members.get(0).getActionsRemaining()));
				lblCrew_1.setText(String.format("%s: %s (%d/2)", members.get(1).getName(), members.get(1).getClassType(), members.get(1).getActionsRemaining()));
				lblCrew_2.setText(String.format("%s: %s", members.get(2).getName(), members.get(2).getClassType()));
				lblCrew_3.setText("");
				frame.remove(select_3);
				break;
			case 4:
				lblCrew.setText(String.format("%s: %s (%d/2)", members.get(0).getName(), members.get(0).getClassType(), members.get(0).getActionsRemaining()));
				lblCrew_1.setText(String.format("%s: %s (%d/2)", members.get(1).getName(), members.get(1).getClassType(), members.get(1).getActionsRemaining()));
				lblCrew_2.setText(String.format("%s: %s", members.get(2).getName(), members.get(2).getClassType()));
				lblCrew_3.setText(String.format("%s: %s", members.get(3).getName(), members.get(3).getClassType()));
				break;
			default:
				lblCrew.setText(String.format("%s: %s (%d/2)", members.get(0).getName(), members.get(0).getClassType(), members.get(0).getActionsRemaining()));
				lblCrew_1.setText("");
				frame.remove(select_1);
				lblCrew_2.setText("");
				lblCrew_3.setText("");
		}
	}
	
	public void selectCrewMember(int num) {
		ArrayList<CrewMember> members = game.getCrew().getCrewMemberList();
		memberSelected = members.get(num);
		lblCrewMemberSelected.setText("Crew Member Selected: " + memberSelected.getName()); 
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
		frame.setBounds(100, 100, 977, 552);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblCrewMembers = new JLabel("Crew members:");
		lblCrewMembers.setBounds(734, 121, 217, 42);
		frame.getContentPane().add(lblCrewMembers);
		
		lblStuff = new JLabel("Days: x, Pieces: x, Crew: x");
		lblStuff.setBounds(734, 174, 217, 42);
		frame.getContentPane().add(lblStuff);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(734, 415, 217, 87);
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
		btnNexDay.setBounds(788, 11, 163, 65);
		btnNexDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextDay();
			}
		});
		frame.getContentPane().add(btnNexDay);
		
		lblPlanet = new JLabel("Planet");
		lblPlanet.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		lblPlanet.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlanet.setBounds(271, 8, 376, 65);
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
		btnCrewStatus.setBounds(311, 121, 115, 42);
		btnCrewStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crewStatus();
			}
		});
		frame.getContentPane().add(btnCrewStatus);
		
		btnShipStatus = new JButton("Ship Status");
		btnShipStatus.setBounds(499, 121, 115, 42);
		btnShipStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shipStatus();
			}
		});
		frame.getContentPane().add(btnShipStatus);
		
		btnVisitOutpost = new JButton("Visit Outpost");
		btnVisitOutpost.setBounds(311, 203, 115, 42);
		btnVisitOutpost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visitOutPost();
			}
		});
		frame.getContentPane().add(btnVisitOutpost);
		
		btnUseItem = new JButton("Use Item");
		btnUseItem.setBounds(499, 203, 115, 42);
		btnUseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takeAction(1);
			}
		});
		frame.getContentPane().add(btnUseItem);
		
		btnSleep = new JButton("Sleep");
		btnSleep.setBounds(311, 280, 115, 42);
		btnSleep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takeAction(2);
			}
		});
		frame.getContentPane().add(btnSleep);
		
		btnRepairShip = new JButton("Repair Ship");
		btnRepairShip.setBounds(499, 280, 115, 42);
		btnRepairShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takeAction(3);
			}
		});
		frame.getContentPane().add(btnRepairShip);
		
		btnSearchPlanet = new JButton("Search Planet");
		btnSearchPlanet.setBounds(311, 356, 115, 42);
		btnSearchPlanet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takeAction(4);
			}
		});
		frame.getContentPane().add(btnSearchPlanet);
		
		btnPilotShip = new JButton("Pilot Ship");
		btnPilotShip.setBounds(499, 356, 115, 42);
		btnPilotShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takeAction(5);
			}
		});
		frame.getContentPane().add(btnPilotShip);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(192, 174, 89, 23);
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectCrewMember(0);;
			}
		});
		frame.getContentPane().add(btnSelect);
		
		select_1 = new JButton("Select");
		select_1.setBounds(192, 243, 89, 23);
		select_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectCrewMember(1);;
			}
		});
		frame.getContentPane().add(select_1);
		
		select_2 = new JButton("Select");
		select_2.setBounds(192, 309, 89, 23);
		select_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectCrewMember(2);;
			}
		});
		frame.getContentPane().add(select_2);
		
		select_3 = new JButton("Select");
		select_3.setBounds(192, 376, 89, 23);
		select_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectCrewMember(3);;
			}
		});
		frame.getContentPane().add(select_3);
		
		lblCrewMemberSelected = new JLabel("Crew Member Selected:");
		lblCrewMemberSelected.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCrewMemberSelected.setBounds(10, 455, 604, 34);
		frame.getContentPane().add(lblCrewMemberSelected);
	}
}
