import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class CreateCrewMembers {

	private JFrame frame;
	private GameEnvironment game;
	private int membersAdded=0;
	private JTextField textField;
	private JComboBox<String> comboBox;
	private JLabel lblMembersCreated;

	public CreateCrewMembers(GameEnvironment ingame) {
		game = ingame;
		initialize();
		setLabels();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		game.closeCreateCrewMembersGui(this);
	}
	
	public void setLabels() {
		lblMembersCreated.setText(String.format("%d/%d Crew Members Created", membersAdded, game.getNumberOfCrew()));
	}
	
	public void createMember() {
		if(membersAdded < game.getNumberOfCrew()) {
			//Integer num = Integer.parseInt(textField.getText());
			String selectedClass = comboBox.getSelectedItem().toString();
			switch (selectedClass) {
	        	case "Captain":
	        		//Captain newCaptain = new Captain(textField.getText());
	        		game.getCrew().createCrewMember(1, textField.getText());;
	        		resetLabels();
	            //System.out.println("Added Captain " + newCaptain.getName());
	            //this.crewMemberList.add(newCaptain);
	            //this.crewWithActionsRemaining.add(newCaptain);
	        		break;
	        	case "Engineer":
	        		//Engineer newEngineer = new Engineer(textField.getText());
	        		game.getCrew().createCrewMember(2, textField.getText());;
	        		//textField.setText("");
	        		resetLabels();
	            //System.out.println("Added Engineer "  + newEngineer.getName());
	            //this.crewMemberList.add(newEngineer);
	            //this.crewWithActionsRemaining.add(newEngineer);
	        		break;
	        	default:
	        		break;
			}
			membersAdded++;
			setLabels();
		} else {
			JOptionPane.showMessageDialog(null, "You all ready have " + membersAdded + " crew members!");
		}
	}
	
	public void resetLabels() {
		textField.setText("");
		comboBox.setSelectedIndex(0);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateCrewMembers window = new CreateCrewMembers();
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
	public CreateCrewMembers() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 693, 404);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblMembersCreated = new JLabel("x/x Crew Members Created");
		lblMembersCreated.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 24));
		lblMembersCreated.setBounds(151, 11, 353, 41);
		lblMembersCreated.setHorizontalAlignment(SwingConstants.CENTER);
		lblMembersCreated.setVerticalAlignment(SwingConstants.TOP);
		frame.getContentPane().add(lblMembersCreated);
		
		textField = new JTextField();
		textField.setBounds(189, 118, 212, 41);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		String[] x = {"Captain", "Engineer"};
		comboBox = new JComboBox<>(x);
		comboBox.setBounds(189, 191, 212, 41);
		frame.getContentPane().add(comboBox);
		
		JLabel lblCrewMemberName = new JLabel("Crew Member Name:");
		lblCrewMemberName.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblCrewMemberName.setBounds(10, 121, 169, 28);
		frame.getContentPane().add(lblCrewMemberName);
		
		JLabel lblCrewMemberClass = new JLabel("Crew Member Class: ");
		lblCrewMemberClass.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblCrewMemberClass.setBounds(10, 194, 169, 28);
		frame.getContentPane().add(lblCrewMemberClass);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		btnAdd.setBounds(495, 191, 127, 41);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createMember();
			}
		});
		frame.getContentPane().add(btnAdd);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.setBounds(495, 282, 89, 23);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		frame.getContentPane().add(btnContinue);
	}
}
