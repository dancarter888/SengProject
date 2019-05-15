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
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class CreateCrewMembers {

	private JFrame frmSetCrewMember;
	private GameEnvironment game;
	private int membersAdded=0;
	private JTextField textField;
	private JComboBox<String> comboBox;
	private JLabel lblMembersCreated;
	private JButton btnAdd;
	private JLabel lblNewLabel;

	public CreateCrewMembers(GameEnvironment ingame) {
		game = ingame;
		initialize();
		setHeader();
		frmSetCrewMember.setVisible(true);
	}
	
	public void closeWindow() {
		frmSetCrewMember.dispose();
	}
	
	public void finishedWindow() {
		game.closeCreateCrewMembersGui(this);
	}
	
	public void setHeader() {
		lblMembersCreated.setText(String.format("%d/%d Crew Members Created", membersAdded, game.getNumberOfCrew()));
	}
	
	public void createMember() {
		
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
		setHeader();
		
		if(membersAdded == game.getNumberOfCrew()) {
			frmSetCrewMember.remove(btnAdd);
			frmSetCrewMember.repaint();
			JButton btnContinue = new JButton("Continue");
			btnContinue.setBackground(Color.GREEN);
			btnContinue.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnContinue.setBounds(411, 97, 145, 74);
			btnContinue.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					finishedWindow();
				}
			});
			frmSetCrewMember.getContentPane().add(btnContinue);
			
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
					window.frmSetCrewMember.setVisible(true);
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
		frmSetCrewMember = new JFrame();
		frmSetCrewMember.setTitle("Add Crew Members");
		frmSetCrewMember.setBounds(100, 100, 582, 289);
		frmSetCrewMember.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSetCrewMember.getContentPane().setLayout(null);
		
		lblMembersCreated = new JLabel("x/x Crew Members Created");
		lblMembersCreated.setFont(new Font("Arial Black", Font.BOLD, 24));
		lblMembersCreated.setBounds(60, 11, 433, 41);
		lblMembersCreated.setHorizontalAlignment(SwingConstants.CENTER);
		lblMembersCreated.setVerticalAlignment(SwingConstants.TOP);
		frmSetCrewMember.getContentPane().add(lblMembersCreated);
		
		textField = new JTextField();
		textField.setBounds(189, 97, 212, 20);
		frmSetCrewMember.getContentPane().add(textField);
		textField.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setBackground(Color.LIGHT_GRAY);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Captain", "Engineer"}));
		comboBox.setBounds(189, 151, 212, 20);
		frmSetCrewMember.getContentPane().add(comboBox);
		
		JLabel lblCrewMemberName = new JLabel("Crew Member Name:");
		lblCrewMemberName.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblCrewMemberName.setBounds(10, 92, 182, 28);
		frmSetCrewMember.getContentPane().add(lblCrewMemberName);
		
		JLabel lblCrewMemberClass = new JLabel("Crew Member Type: ");
		lblCrewMemberClass.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblCrewMemberClass.setBounds(10, 146, 169, 28);
		frmSetCrewMember.getContentPane().add(lblCrewMemberClass);
		
		btnAdd = new JButton("Add To Crew");
		btnAdd.setBackground(Color.LIGHT_GRAY);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAdd.setBounds(411, 97, 145, 74);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().length() <= 10 && textField.getText().length() > 0) {
					createMember();
					btnAdd.setBackground(Color.LIGHT_GRAY);					
				} else {
					btnAdd.setBackground(Color.RED);
				}
				
			}
		});
		frmSetCrewMember.getContentPane().add(btnAdd);
		
		lblNewLabel = new JLabel("(Must be 1-10 characters)");
		lblNewLabel.setFont(new Font("Arial", Font.ITALIC, 10));
		lblNewLabel.setBounds(189, 117, 212, 14);
		frmSetCrewMember.getContentPane().add(lblNewLabel);
		
		
		
	}
}
