import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class PilotShipScreen {

	private JFrame frame;
	private GameEnvironment game;
	private ArrayList<CrewMember> membersSelected = new ArrayList<>();
	private int numMembersSelected = 0;
	private JLabel lblCrew;
	private JLabel lblCrew_1;
	private JLabel lblCrew_2;
	private JLabel lblCrew_3;
	private JButton btnSelect_1;
	private JButton btnSelect_2;
	private JButton btnSelect_3;
	private JButton btnSelect_4;
	private JComboBox<String> comboBox;
	private JLabel lblCrewMemberSelected;
	private JButton btnPilotShip;

	public PilotShipScreen(GameEnvironment inGame) {
		game = inGame;
		initialize();
		setLabels();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		game.closePilotShipGui(this);
	}
	
	public void pilotShip() {
		if(membersSelected.size() == 2) {
			CrewMember member1 = membersSelected.get(0);
			CrewMember member2 = membersSelected.get(1);
			String planet = comboBox.getSelectedItem().toString();
			
			ArrayList<String> strings = Actions.pilotShip(game.getCrew(), member1, member2, planet);
		
			for(String s : strings) {
				if(s.length() > 0) {
					JOptionPane.showMessageDialog(frame, s, "Pilot Ship",
													JOptionPane.INFORMATION_MESSAGE);
				}
			}
		
			finishedWindow();
		} else {
			JOptionPane.showMessageDialog(frame, "You need to select two crew members to fly the ship", "Pilot Ship",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void selectCrewMember(int memberNum) {
		ArrayList<CrewMember> members = game.getCrew().getCrewMemberList();
		
		if(!membersSelected.contains(members.get(memberNum))) {
			if(numMembersSelected < 2) {
				membersSelected.add(members.get(memberNum));
				setLabels();
			} else {
				membersSelected.remove(0);
				membersSelected.add(members.get(memberNum));
				setLabels();
			}
		} else {
			JOptionPane.showMessageDialog(frame, "This crew member has already been selected!", "Pilot Ship",
					JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		//setSelectBtnColours(memberNum);
		//lblCrewMemberSelected.setText("Crew Member Selected: " + memberSelected.getName()); 
	}
	
	public void setLabels() {
		String text = "Crew Members Selected: ";
		for(CrewMember member : membersSelected) {
			text += member.getName() + ", ";
		}
		lblCrewMemberSelected.setText(text);
		
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
				frame.remove(btnSelect_3);
				lblCrew_3.setText("");
				frame.remove(btnSelect_4);
				break;
			case 3:
				lblCrew.setText(String.format("%s: %s (%d/2)", members.get(0).getName(), members.get(0).getClassType(), members.get(0).getActionsRemaining()));
				lblCrew_1.setText(String.format("%s: %s (%d/2)", members.get(1).getName(), members.get(1).getClassType(), members.get(1).getActionsRemaining()));
				lblCrew_2.setText(String.format("%s: %s (%d/2)", members.get(2).getName(), members.get(2).getClassType(), members.get(2).getActionsRemaining()));
				lblCrew_3.setText("");
				frame.remove(btnSelect_4);
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
				frame.remove(btnSelect_2);
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
					PilotShipScreen window = new PilotShipScreen();
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
	public PilotShipScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 489, 358);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLeave = new JButton("leave");
		btnLeave.setBounds(335, 285, 128, 23);
		btnLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		frame.getContentPane().add(btnLeave);
		
		lblCrew = new JLabel("Crew1");
		lblCrew.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCrew.setBounds(20, 30, 219, 49);
		frame.getContentPane().add(lblCrew);
		
		lblCrew_1 = new JLabel("Crew2");
		lblCrew_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCrew_1.setBounds(20, 90, 219, 42);
		frame.getContentPane().add(lblCrew_1);
		
		lblCrew_2 = new JLabel("Crew3");
		lblCrew_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCrew_2.setBounds(20, 143, 219, 42);
		frame.getContentPane().add(lblCrew_2);
		
		lblCrew_3 = new JLabel("Crew4");
		lblCrew_3.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCrew_3.setBounds(20, 196, 219, 42);
		frame.getContentPane().add(lblCrew_3);
		
		btnSelect_1 = new JButton("Select");
		btnSelect_1.setBackground(Color.LIGHT_GRAY);
		btnSelect_1.setBounds(192, 44, 89, 23);
		btnSelect_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectCrewMember(0);
			}
		});
		frame.getContentPane().add(btnSelect_1);
		
		btnSelect_2 = new JButton("Select");
		btnSelect_2.setBackground(Color.LIGHT_GRAY);
		btnSelect_2.setBounds(192, 101, 89, 23);
		btnSelect_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectCrewMember(1);
			}
		});
		frame.getContentPane().add(btnSelect_2);
		
		btnSelect_3 = new JButton("Select");
		btnSelect_3.setBackground(Color.LIGHT_GRAY);
		btnSelect_3.setBounds(192, 154, 89, 23);
		btnSelect_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectCrewMember(2);
			}
		});
		frame.getContentPane().add(btnSelect_3);
		
		btnSelect_4 = new JButton("Select");
		btnSelect_4.setBackground(Color.LIGHT_GRAY);
		btnSelect_4.setBounds(192, 207, 89, 23);
		btnSelect_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectCrewMember(3);
			}
		});
		frame.getContentPane().add(btnSelect_4);
		
		String[] planets = {"StartPlanet", "Earth"};
		comboBox = new JComboBox<String>(planets);
		comboBox.setBounds(335, 102, 128, 20);
		frame.getContentPane().add(comboBox);
		
		btnPilotShip = new JButton("Pilot Ship");
		btnPilotShip.setBounds(335, 193, 128, 60);
		btnPilotShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pilotShip();
			}
		});
		frame.getContentPane().add(btnPilotShip);
		
		lblCrewMemberSelected = new JLabel("Crew Member Selected:");
		lblCrewMemberSelected.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		lblCrewMemberSelected.setBounds(20, 261, 387, 34);
		frame.getContentPane().add(lblCrewMemberSelected);
	}
}
