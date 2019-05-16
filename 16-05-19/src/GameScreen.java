import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
//import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JComboBox;

public class GameScreen {

	private JFrame frmGameScreen;
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
	private JButton btnSelect_1;
	private JButton btnSelect_2;
	private JButton btnSelect_3;
	private JButton btnSelect_4;
	private JLabel lblCrewMemberSelected;
	private JLabel lblActions;
	private JComboBox<String> comboBox;
	
	
	public GameScreen(GameEnvironment inGame) {
		game = inGame;
		initialize();
		setLabels();
		frmGameScreen.setVisible(true);
	}
	
	public void closeWindow() {
		frmGameScreen.dispose();
	}
	
	public void finishedWindow() {
		game.closeGameGui(this);
	}
	
	public void nextDay() {
		// check for if everyone dead maybe
		if((game.getCurrentDay() == game.getTotalDays()) || (game.getNumberOfCrew() == 0)) {
			JOptionPane.showMessageDialog(frmGameScreen, "You Ran Out of Days!");
			finishedWindow();
		} else if(game.getCrew().getPiecesFound() == game.getPiecesNeeded()) {
			JOptionPane.showMessageDialog(frmGameScreen, "You Found All The Pieces!");
			finishedWindow();
		} else {
			ArrayList<String> strings = game.newDay();
			for(String s : strings) {
				JOptionPane.showMessageDialog(frmGameScreen, s, "New Day",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
			if(game.getNumberOfCrew() > 0) {
				setLabels();
			} else {
				JOptionPane.showMessageDialog(frmGameScreen, "All Crew Members Are Dead!");
				finishedWindow();
			}
		}
	}
	
	public void visitOutPost() {
		game.launchOutPostGui(this);
	}
	
	public void takeAction(int num) {
		if(memberSelected == null) {
			JOptionPane.showMessageDialog(frmGameScreen, "You need to select a crew member to perform an action!", "Action",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			ArrayList<String> strings = game.takeAction(num, memberSelected);
			for(String s : strings) {
				JOptionPane.showMessageDialog(frmGameScreen, s, "Action",
						JOptionPane.INFORMATION_MESSAGE);
			}
			setCrewLabels();
		}
	}
	
	public void pilotShip() {
		game.launchPilotShipGui(this);
	}
	
	public void useItem() {
		if(memberSelected == null) {
			JOptionPane.showMessageDialog(frmGameScreen, "You need to select a crew member to perform an action!", "Action",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			String item = comboBox.getSelectedItem().toString();
			ArrayList<String> strings = Actions.useItem(game.getCrew(), memberSelected, item);
			for(String s : strings) {
				JOptionPane.showMessageDialog(frmGameScreen, s, "Action",
						JOptionPane.INFORMATION_MESSAGE);
			}
			setCrewLabels();
		}
		
		
		
	}
	
	public void crewStatus() {
		String membersString = "";
		ArrayList<CrewMember> members = game.getCrew().getCrewMemberList();
		
		for(CrewMember member : members) {
			membersString += member.toString() + "\n";
		}
		
		JOptionPane.showMessageDialog(frmGameScreen, membersString, "Crew Status",
		        						JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void shipStatus() {
		String shipString = "Ship Status:\n";
		shipString += game.getCrew().getShipStatus();
		
		JOptionPane.showMessageDialog(frmGameScreen, shipString,"Ship Status",
		        						JOptionPane.INFORMATION_MESSAGE);
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
				frmGameScreen.remove(btnSelect_3);
				lblCrew_3.setText("");
				frmGameScreen.remove(btnSelect_4);
				break;
			case 3:
				lblCrew.setText(String.format("%s: %s (%d/2)", members.get(0).getName(), members.get(0).getClassType(), members.get(0).getActionsRemaining()));
				lblCrew_1.setText(String.format("%s: %s (%d/2)", members.get(1).getName(), members.get(1).getClassType(), members.get(1).getActionsRemaining()));
				lblCrew_2.setText(String.format("%s: %s (%d/2)", members.get(2).getName(), members.get(2).getClassType(), members.get(2).getActionsRemaining()));
				lblCrew_3.setText("");
				frmGameScreen.remove(btnSelect_4);
				break;
			case 4:
				lblCrew.setText(String.format("%s: %s (%d/2)", members.get(0).getName(), members.get(0).getClassType(), members.get(0).getActionsRemaining()));
				lblCrew_1.setText(String.format("%s: %s (%d/2)", members.get(1).getName(), members.get(1).getClassType(), members.get(1).getActionsRemaining()));
				lblCrew_2.setText(String.format("%s: %s (%d/2)", members.get(2).getName(), members.get(2).getClassType(), members.get(2).getActionsRemaining()));
				lblCrew_3.setText(String.format("%s: %s (%d/2)", members.get(3).getName(), members.get(3).getClassType(), members.get(3).getActionsRemaining()));
				break;
			default:
				lblCrew.setText(String.format("%s: %s (%d/2)", members.get(0).getName(), members.get(0).getClassType(), members.get(0).getActionsRemaining()));
				lblCrew_1.setText("");
				frmGameScreen.remove(btnSelect_2);
				lblCrew_2.setText("");
				lblCrew_3.setText("");
		}
	}
	
	public void selectCrewMember(int memberNum) {
		ArrayList<CrewMember> members = game.getCrew().getCrewMemberList();
		memberSelected = members.get(memberNum);
		setSelectBtnColours(memberNum);
		lblCrewMemberSelected.setText("Crew Member Selected: " + memberSelected.getName()); 
	}
	
	private void setSelectBtnColours(int memberNum) {
		btnSelect_1.setBackground(Color.LIGHT_GRAY);
		btnSelect_2.setBackground(Color.LIGHT_GRAY);
		btnSelect_3.setBackground(Color.LIGHT_GRAY);
		btnSelect_4.setBackground(Color.LIGHT_GRAY);
		
		switch (memberNum){
			case 0:
				btnSelect_1.setBackground(Color.GREEN);
				break;
			case 1:
				btnSelect_2.setBackground(Color.GREEN);
				break;
			case 2:
				btnSelect_3.setBackground(Color.GREEN);
				break;
			case 3:
				btnSelect_4.setBackground(Color.GREEN);
				break;
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
					window.frmGameScreen.setVisible(true);
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
		frmGameScreen = new JFrame();
		frmGameScreen.setTitle("Game Screen");
		frmGameScreen.setBounds(100, 100, 921, 534);
		frmGameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGameScreen.getContentPane().setLayout(null);
		
		lblCrewMembers = new JLabel("Crew members:");
		lblCrewMembers.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCrewMembers.setBounds(734, 121, 217, 42);
		frmGameScreen.getContentPane().add(lblCrewMembers);
		
		lblStuff = new JLabel("Days: x, Pieces: x, Crew: x");
		lblStuff.setFont(new Font("Arial", Font.PLAIN, 12));
		lblStuff.setBounds(734, 174, 217, 42);
		frmGameScreen.getContentPane().add(lblStuff);
		
		JButton btnQuit = new JButton("Quit Game");
		btnQuit.setBackground(Color.RED);
		btnQuit.setBounds(799, 461, 98, 23);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		frmGameScreen.getContentPane().add(btnQuit);
		
		lblDays = new JLabel("Day x/x");
		lblDays.setHorizontalAlignment(SwingConstants.CENTER);
		lblDays.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 24));
		lblDays.setBounds(10, 11, 163, 65);
		frmGameScreen.getContentPane().add(lblDays);
		
		JButton btnNexDay = new JButton("Next Day");
		btnNexDay.setBackground(Color.GREEN);
		btnNexDay.setFont(new Font("Arial", Font.BOLD, 24));
		btnNexDay.setBounds(734, 11, 163, 65);
		btnNexDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextDay();
			}
		});
		frmGameScreen.getContentPane().add(btnNexDay);
		
		lblPlanet = new JLabel("Planet");
		lblPlanet.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblPlanet.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlanet.setBounds(295, 9, 376, 65);
		frmGameScreen.getContentPane().add(lblPlanet);
		
		lblCrew = new JLabel("Crew1");
		lblCrew.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCrew.setBounds(20, 160, 219, 49);
		frmGameScreen.getContentPane().add(lblCrew);
		
		lblCrew_1 = new JLabel("Crew2");
		lblCrew_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCrew_1.setBounds(20, 230, 219, 42);
		frmGameScreen.getContentPane().add(lblCrew_1);
		
		lblCrew_2 = new JLabel("Crew3");
		lblCrew_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCrew_2.setBounds(20, 300, 219, 42);
		frmGameScreen.getContentPane().add(lblCrew_2);
		
		lblCrew_3 = new JLabel("Crew4");
		lblCrew_3.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCrew_3.setBounds(20, 370, 219, 42);
		frmGameScreen.getContentPane().add(lblCrew_3);
		
		lblCrewMembers_1 = new JLabel("Crew Members:");
		lblCrewMembers_1.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblCrewMembers_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCrewMembers_1.setBounds(20, 110, 163, 50);
		frmGameScreen.getContentPane().add(lblCrewMembers_1);
		
		btnCrewStatus = new JButton("Crew Status");
		btnCrewStatus.setBackground(Color.LIGHT_GRAY);
		btnCrewStatus.setBounds(360, 153, 115, 42);
		btnCrewStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crewStatus();
			}
		});
		frmGameScreen.getContentPane().add(btnCrewStatus);
		
		btnShipStatus = new JButton("Ship Status");
		btnShipStatus.setBackground(Color.LIGHT_GRAY);
		btnShipStatus.setBounds(499, 153, 115, 42);
		btnShipStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shipStatus();
			}
		});
		frmGameScreen.getContentPane().add(btnShipStatus);
		
		btnVisitOutpost = new JButton("Visit Outpost");
		btnVisitOutpost.setBackground(Color.LIGHT_GRAY);
		btnVisitOutpost.setBounds(360, 223, 115, 42);
		btnVisitOutpost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visitOutPost();
			}
		});
		frmGameScreen.getContentPane().add(btnVisitOutpost);
		
		btnUseItem = new JButton("Use Item");
		btnUseItem.setBackground(Color.LIGHT_GRAY);
		btnUseItem.setBounds(760, 363, 115, 42);
		btnUseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				useItem();
			}
		});
		frmGameScreen.getContentPane().add(btnUseItem);
		
		btnSleep = new JButton("Sleep");
		btnSleep.setBackground(Color.LIGHT_GRAY);
		btnSleep.setBounds(360, 293, 115, 42);
		btnSleep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takeAction(2);
			}
		});
		frmGameScreen.getContentPane().add(btnSleep);
		
		btnRepairShip = new JButton("Repair Ship");
		btnRepairShip.setBackground(Color.LIGHT_GRAY);
		btnRepairShip.setBounds(499, 293, 115, 42);
		btnRepairShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takeAction(3);
			}
		});
		frmGameScreen.getContentPane().add(btnRepairShip);
		
		btnSearchPlanet = new JButton("Search Planet");
		btnSearchPlanet.setBackground(Color.LIGHT_GRAY);
		btnSearchPlanet.setBounds(360, 363, 115, 42);
		btnSearchPlanet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				takeAction(4);
			}
		});
		frmGameScreen.getContentPane().add(btnSearchPlanet);
		
		btnPilotShip = new JButton("Pilot Ship");
		btnPilotShip.setBackground(Color.LIGHT_GRAY);
		btnPilotShip.setBounds(499, 363, 115, 42);
		btnPilotShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pilotShip();
			}
		});
		frmGameScreen.getContentPane().add(btnPilotShip);
		
		btnSelect_1 = new JButton("Select");
		btnSelect_1.setBackground(Color.LIGHT_GRAY);
		btnSelect_1.setBounds(192, 172, 89, 23);
		btnSelect_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectCrewMember(0);
			}
		});
		frmGameScreen.getContentPane().add(btnSelect_1);
		
		btnSelect_2 = new JButton("Select");
		btnSelect_2.setBackground(Color.LIGHT_GRAY);
		btnSelect_2.setBounds(192, 242, 89, 23);
		btnSelect_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectCrewMember(1);
			}
		});
		frmGameScreen.getContentPane().add(btnSelect_2);
		
		btnSelect_3 = new JButton("Select");
		btnSelect_3.setBackground(Color.LIGHT_GRAY);
		btnSelect_3.setBounds(192, 312, 89, 23);
		btnSelect_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectCrewMember(2);
			}
		});
		frmGameScreen.getContentPane().add(btnSelect_3);
		
		btnSelect_4 = new JButton("Select");
		btnSelect_4.setBackground(Color.LIGHT_GRAY);
		btnSelect_4.setBounds(192, 382, 89, 23);
		btnSelect_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectCrewMember(3);
			}
		});
		frmGameScreen.getContentPane().add(btnSelect_4);
		
		lblCrewMemberSelected = new JLabel("Crew Member Selected:");
		lblCrewMemberSelected.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		lblCrewMemberSelected.setBounds(20, 453, 604, 34);
		frmGameScreen.getContentPane().add(lblCrewMemberSelected);
		
		lblActions = new JLabel("Actions:");
		lblActions.setHorizontalAlignment(SwingConstants.LEFT);
		lblActions.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblActions.setBounds(360, 110, 163, 50);
		frmGameScreen.getContentPane().add(lblActions);
		
		String[] items = {"Apple", "LesserHealing", "PlagueCure"};
		comboBox = new JComboBox<String>(items);
		comboBox.setBackground(Color.LIGHT_GRAY);
		//comboBox.setModel(new DefaultComboBoxModel(new String[] {"Apple", "LesserHealing", "PlagueCure"}));
		comboBox.setBounds(734, 293, 161, 20);
		frmGameScreen.getContentPane().add(comboBox);
	}
}