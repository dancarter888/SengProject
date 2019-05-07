import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GameSetup {

	private JFrame frame;
	private GameEnvironment game;
	private int memberAdded=0;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	
	public GameSetup(GameEnvironment inGame) {
		game = inGame;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		game.createCrew(game.getNumberOfCrew(), game.getCrewName(), game.getShipName());
		game.closeSetupGui(this);
	}

	public void setDays() {
		try {
			String days = textField.getText();
			Integer num = Integer.parseInt(days);
			game.setTotalDays(num);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Days must be number");
		}
	}
	
	public void setPieces() {
		try {
			String days = textField_1.getText();
			Integer num = Integer.parseInt(days);
			game.setPiecesNeeded(num);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Pieces must be number");
		}
	}
	
	public void setCrew() {
		try {
			String days = textField_2.getText();
			Integer num = Integer.parseInt(days);
			game.setNumberOfCrew(num);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "crew must be number");
		}
	}
	
	public void setCrewName() {
		String name = textField_3.getText();
		game.setCrewName(name);
	}
	
	public void setShipName() {
		String name = textField_4.getText();
		game.setShipName(name);
	}
	
	public void createMember() {
		if(memberAdded < game.getNumberOfCrew()) {
			Integer num = Integer.parseInt(textField_6.getText());
			switch (num) {
	        	case 1:
	        		Captain newCaptain = new Captain(textField_5.getText());
	            //System.out.println("Added Captain " + newCaptain.getName());
	            //this.crewMemberList.add(newCaptain);
	            //this.crewWithActionsRemaining.add(newCaptain);
	        		break;
	        	case 2:
	        		Engineer newEngineer = new Engineer(textField_5.getText());
	            //System.out.println("Added Engineer "  + newEngineer.getName());
	            //this.crewMemberList.add(newEngineer);
	            //this.crewWithActionsRemaining.add(newEngineer);
	        		break;
	        	default:
	        		break;
			}
			memberAdded++;
		} else {
			JOptionPane.showMessageDialog(null, "Too many crew");
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameSetup window = new GameSetup();
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
	public GameSetup() {
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
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.setBounds(345, 238, 89, 23);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		frame.getContentPane().add(btnContinue);
		
		textField = new JTextField();
		textField.setBounds(117, 11, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblDays = new JLabel("days:");
		lblDays.setBounds(10, 14, 46, 14);
		frame.getContentPane().add(lblDays);
		
		JLabel lblPieces = new JLabel("pieces:");
		lblPieces.setBounds(10, 45, 46, 14);
		frame.getContentPane().add(lblPieces);
		
		textField_1 = new JTextField();
		textField_1.setBounds(117, 42, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCrew = new JLabel("crew: ");
		lblCrew.setBounds(10, 73, 46, 14);
		frame.getContentPane().add(lblCrew);
		
		textField_2 = new JTextField();
		textField_2.setBounds(117, 70, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnSet = new JButton("set days");
		btnSet.setBounds(230, 10, 89, 23);
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDays();
			}
		});
		frame.getContentPane().add(btnSet);
		
		JButton btnSetPieces = new JButton("set pieces");
		btnSetPieces.setBounds(230, 41, 89, 23);
		btnSetPieces.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setPieces();
			}
		});
		frame.getContentPane().add(btnSetPieces);
		
		JButton btnSetCrew = new JButton("set crew");
		btnSetCrew.setBounds(230, 69, 89, 23);
		btnSetCrew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCrew();
			}
		});
		frame.getContentPane().add(btnSetCrew);
		
		JLabel lblCrewName = new JLabel("crew name:");
		lblCrewName.setBounds(10, 114, 63, 14);
		frame.getContentPane().add(lblCrewName);
		
		textField_3 = new JTextField();
		textField_3.setBounds(117, 111, 86, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnSetCrewName = new JButton("set crew name");
		btnSetCrewName.setBounds(230, 110, 103, 23);
		btnSetCrewName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCrewName();
			}
		});
		frame.getContentPane().add(btnSetCrewName);
		
		JLabel lblShipName = new JLabel("ship name:");
		lblShipName.setBounds(10, 148, 63, 14);
		frame.getContentPane().add(lblShipName);
		
		textField_4 = new JTextField();
		textField_4.setBounds(117, 142, 86, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnSetShipName = new JButton("set ship name");
		btnSetShipName.setBounds(230, 144, 103, 23);
		btnSetShipName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setShipName();
			}
		});
		frame.getContentPane().add(btnSetShipName);
		
		textField_5 = new JTextField();
		textField_5.setBounds(10, 216, 86, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(117, 216, 86, 20);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		JButton btnAddMember = new JButton("add member");
		btnAddMember.setBounds(230, 215, 103, 23);
		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createMember();
			}
		});
		frame.getContentPane().add(btnAddMember);
		
		JLabel lblMemberName = new JLabel("Member name:");
		lblMemberName.setBounds(10, 194, 86, 14);
		frame.getContentPane().add(lblMemberName);
		
		JLabel lblMemberClass = new JLabel("Member class:");
		lblMemberClass.setBounds(117, 194, 86, 14);
		frame.getContentPane().add(lblMemberClass);
	}
}
