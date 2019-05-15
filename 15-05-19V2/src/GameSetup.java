import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;

public class GameSetup {

	private JFrame frmSetup;
	private GameEnvironment game;
	private JTextField txtSetCrewName;
	private JTextField txtMyShip;
	private JButton threeDays;
	private JButton fourDays;
	private JButton fiveDays;
	private JButton sixDays;
	private JButton sevenDays;
	private JButton eightDays;
	private JButton nineDays;
	private JButton tenDays;
	private JLabel lblNumPieces;
	private JButton twoCrew;
	private JButton threeCrew;
	private JButton fourCrew;
	private JButton btnSetCrewName;
	private JButton btnSetShipName;
	private JButton btnContinue;
	private JLabel lblErrorMsg;
	private boolean daysSet = false;
	private boolean crewSet = false;
	private boolean crewNameSet = false;
	private boolean shipNameSet = false;
	private JLabel lblMustBe_1;
	private JLabel lblMustBe_2;
	
	
	
	
	
	public GameSetup(GameEnvironment inGame) {
		game = inGame;
		initialize();
		frmSetup.setVisible(true);
	}
	
	public void closeWindow() {
		frmSetup.dispose();
	}

	public void setDays(int numDays) {
		game.setTotalDays(numDays);
		int piecesNeeded = 2 * (game.getTotalDays() / 3);
		setPieces(piecesNeeded);
		daysSet = true;
		makeContinueGreen();
	}
	
	public void highlightDayBtn(int btnNum){
		threeDays.setBackground(Color.LIGHT_GRAY);
		fourDays.setBackground(Color.LIGHT_GRAY);
		fiveDays.setBackground(Color.LIGHT_GRAY);
		sixDays.setBackground(Color.LIGHT_GRAY);
		sevenDays.setBackground(Color.LIGHT_GRAY);
		eightDays.setBackground(Color.LIGHT_GRAY);
		nineDays.setBackground(Color.LIGHT_GRAY);
		tenDays.setBackground(Color.LIGHT_GRAY);
		
		switch (btnNum) {
			case 3:
				threeDays.setBackground(Color.GREEN);
				break;
			case 4:
				fourDays.setBackground(Color.GREEN);
				break;
			case 5:
				fiveDays.setBackground(Color.GREEN);
				break;
			case 6:
				sixDays.setBackground(Color.GREEN);
				break;
			case 7:
				sevenDays.setBackground(Color.GREEN);
				break;
			case 8:
				eightDays.setBackground(Color.GREEN);
				break;
			case 9:
				nineDays.setBackground(Color.GREEN);
				break;
			case 10:
				tenDays.setBackground(Color.GREEN);
				break;
		}
	}
	
	public void setPieces(int piecesNeeded) {
		game.setPiecesNeeded(piecesNeeded);
		lblNumPieces.setText(String.format("%d", piecesNeeded));
	}
	
	public void setCrew(int numCrew) {
		game.setNumberOfCrew(numCrew);
		crewSet = true;
		makeContinueGreen();		
	}
	
	public void highlightCrewBtn(int btnNum){
		twoCrew.setBackground(Color.LIGHT_GRAY);
		threeCrew.setBackground(Color.LIGHT_GRAY);
		fourCrew.setBackground(Color.LIGHT_GRAY);
		
		switch (btnNum) {
			case 2:
				twoCrew.setBackground(Color.GREEN);
				break;
			case 3:
				threeCrew.setBackground(Color.GREEN);
				break;
			case 4:
				fourCrew.setBackground(Color.GREEN);
				break;
		}
	}
	
	public void setCrewName() {
		String name = txtSetCrewName.getText();
		if (txtSetCrewName.getText().equals("") || name.length() > 15) {
			btnSetCrewName.setBackground(Color.RED);
			btnContinue.setBackground(Color.RED);
			crewNameSet = false;
		} else {
			game.setCrewName(name);
			btnSetCrewName.setBackground(Color.GREEN);
			crewNameSet = true;
		}
		makeContinueGreen();
	}
	
	public void setShipName() {
		String name = txtMyShip.getText();
		if (txtMyShip.getText().equals("") || name.length() > 10) {
			btnSetShipName.setBackground(Color.RED);
			btnContinue.setBackground(Color.RED);
			shipNameSet = false;
		} else {
			game.setShipName(name);
			btnSetShipName.setBackground(Color.GREEN);
			shipNameSet = true;
		}
		makeContinueGreen();
	}
	
	public void makeContinueGreen() {
		if (daysSet && crewSet && crewNameSet && shipNameSet) {
			btnContinue.setBackground(Color.GREEN);		
		} else {
			btnContinue.setBackground(Color.RED);	
		}
	}
	
	public void finishedWindow() {
		if (daysSet && crewSet && crewNameSet && shipNameSet){
			game.createCrew(game.getNumberOfCrew(), game.getCrewName(), game.getShipName());
			game.closeSetupGui(this);
		} else {
			String errorMsg = "To Continue: ";
			if (!daysSet) {
				errorMsg += "Set Days. ";
			}
			if (!crewSet) {
				errorMsg += "Set Crew. ";
			}
			if (!crewNameSet) {
				errorMsg += "Set Crew Name. ";
			}
			if (!shipNameSet) {
				errorMsg += "Set Ship Name. ";
			}
			lblErrorMsg.setText(errorMsg);
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
					window.frmSetup.setVisible(true);
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
		frmSetup = new JFrame();
		frmSetup.setTitle("Setup");
		frmSetup.setBounds(100, 100, 491, 298);
		frmSetup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSetup.getContentPane().setLayout(null);
				
		JLabel lblDays = new JLabel("Set Days:");
		lblDays.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblDays.setBounds(10, 14, 89, 14);
		frmSetup.getContentPane().add(lblDays);
		
		threeDays = new JButton("3");
		threeDays.setBackground(Color.LIGHT_GRAY);
		threeDays.setFont(new Font("Tahoma", Font.PLAIN, 11));
		threeDays.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDays(3);
				highlightDayBtn(3);
			}
		});
		threeDays.setBounds(135, 11, 45, 23);
		frmSetup.getContentPane().add(threeDays);
		
		fourDays = new JButton("4");
		fourDays.setBackground(Color.LIGHT_GRAY);
		fourDays.setFont(new Font("Tahoma", Font.PLAIN, 11));
		fourDays.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDays(4);
				highlightDayBtn(4);
			}
		});
		fourDays.setBounds(175, 11, 45, 23);
		frmSetup.getContentPane().add(fourDays);
		
		fiveDays = new JButton("5");
		fiveDays.setBackground(Color.LIGHT_GRAY);
		fiveDays.setFont(new Font("Tahoma", Font.PLAIN, 11));
		fiveDays.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDays(5);
				highlightDayBtn(5);
			}
		});
		fiveDays.setBounds(215, 11, 45, 23);
		frmSetup.getContentPane().add(fiveDays);
		
		sixDays = new JButton("6");
		sixDays.setBackground(Color.LIGHT_GRAY);
		sixDays.setFont(new Font("Tahoma", Font.PLAIN, 11));
		sixDays.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDays(6);
				highlightDayBtn(6);
			}
		});
		sixDays.setBounds(255, 11, 45, 23);
		frmSetup.getContentPane().add(sixDays);
		
		sevenDays = new JButton("7");
		sevenDays.setBackground(Color.LIGHT_GRAY);
		sevenDays.setFont(new Font("Tahoma", Font.PLAIN, 11));
		sevenDays.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDays(7);
				highlightDayBtn(7);
			}
		});
		sevenDays.setBounds(295, 11, 45, 23);
		frmSetup.getContentPane().add(sevenDays);
		
		eightDays = new JButton("8");
		eightDays.setBackground(Color.LIGHT_GRAY);
		eightDays.setFont(new Font("Tahoma", Font.PLAIN, 11));
		eightDays.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDays(8);
				highlightDayBtn(8);
			}
		});
		eightDays.setBounds(335, 11, 45, 23);
		frmSetup.getContentPane().add(eightDays);
		
		nineDays = new JButton("9");
		nineDays.setBackground(Color.LIGHT_GRAY);
		nineDays.setFont(new Font("Tahoma", Font.PLAIN, 11));
		nineDays.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDays(9);
				highlightDayBtn(9);
			}
		});
		nineDays.setBounds(375, 11, 45, 23);
		frmSetup.getContentPane().add(nineDays);
		
		tenDays = new JButton("10");
		tenDays.setBackground(Color.LIGHT_GRAY);
		tenDays.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tenDays.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDays(10);
				highlightDayBtn(10);
			}
		});
		tenDays.setBounds(415, 11, 50, 23);
		frmSetup.getContentPane().add(tenDays);
		
		JLabel lblPieces = new JLabel("Pieces Needed:");
		lblPieces.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblPieces.setBounds(10, 50, 125, 14);
		frmSetup.getContentPane().add(lblPieces);
		
		lblNumPieces = new JLabel("");
		lblNumPieces.setBounds(140, 51, 108, 14);
		frmSetup.getContentPane().add(lblNumPieces);
		
		JLabel lblCrew = new JLabel("Set Crew:");
		lblCrew.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblCrew.setBounds(10, 85, 89, 14);
		frmSetup.getContentPane().add(lblCrew);
		
		twoCrew = new JButton("2");
		twoCrew.setBackground(Color.LIGHT_GRAY);
		twoCrew.setBounds(135, 81, 45, 23);
		twoCrew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCrew(2);
				highlightCrewBtn(2);
			}
		});
		frmSetup.getContentPane().add(twoCrew);
		
		threeCrew = new JButton("3");
		threeCrew.setBackground(Color.LIGHT_GRAY);
		threeCrew.setBounds(215, 82, 45, 23);
		threeCrew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCrew(3);
				highlightCrewBtn(3);
			}
		});
		frmSetup.getContentPane().add(threeCrew);
		
		fourCrew = new JButton("4");
		fourCrew.setBackground(Color.LIGHT_GRAY);
		fourCrew.setBounds(295, 82, 45, 23);
		fourCrew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCrew(4);
				highlightCrewBtn(4);
			}
		});
		frmSetup.getContentPane().add(fourCrew);
		
		JLabel lblCrewName = new JLabel("Set Crew Name:");
		lblCrewName.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblCrewName.setBounds(10, 120, 125, 14);
		frmSetup.getContentPane().add(lblCrewName);
		
		txtSetCrewName = new JTextField();
		txtSetCrewName.setToolTipText("");
		txtSetCrewName.setBounds(135, 119, 205, 20);
		frmSetup.getContentPane().add(txtSetCrewName);
		txtSetCrewName.setColumns(10);
		
		btnSetCrewName = new JButton("Set");
		btnSetCrewName.setBackground(Color.LIGHT_GRAY);
		btnSetCrewName.setBounds(350, 117, 115, 23);
		btnSetCrewName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCrewName();
			}
		});
		frmSetup.getContentPane().add(btnSetCrewName);
		
		JLabel lblShipName = new JLabel("Set Ship Name:");
		lblShipName.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblShipName.setBounds(10, 168, 125, 14);
		frmSetup.getContentPane().add(lblShipName);
		
		txtMyShip = new JTextField();
		txtMyShip.setBounds(134, 167, 206, 20);
		frmSetup.getContentPane().add(txtMyShip);
		txtMyShip.setColumns(10);
		
		btnSetShipName = new JButton("Set");
		btnSetShipName.setBackground(Color.LIGHT_GRAY);
		btnSetShipName.setBounds(350, 164, 115, 23);
		btnSetShipName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setShipName();
			}
		});
		frmSetup.getContentPane().add(btnSetShipName);

		btnContinue = new JButton("Continue");
		btnContinue.setBackground(Color.RED);
		btnContinue.setBounds(380, 227, 85, 23);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		frmSetup.getContentPane().add(btnContinue);
		
		lblErrorMsg = new JLabel("");
		lblErrorMsg.setBounds(10, 230, 384, 14);
		frmSetup.getContentPane().add(lblErrorMsg);
		
		lblMustBe_1 = new JLabel("(Must be 1-10 characters)");
		lblMustBe_1.setFont(new Font("Arial", Font.ITALIC, 10));
		lblMustBe_1.setBounds(135, 139, 205, 14);
		frmSetup.getContentPane().add(lblMustBe_1);
		
		lblMustBe_2 = new JLabel("(Must be 1-10 characters)");
		lblMustBe_2.setFont(new Font("Arial", Font.ITALIC, 10));
		lblMustBe_2.setBounds(135, 187, 205, 14);
		frmSetup.getContentPane().add(lblMustBe_2);
		
		
		
		
//		textField_5 = new JTextField();
//		textField_5.setBounds(10, 216, 86, 20);
//		frame.getContentPane().add(textField_5);
//		textField_5.setColumns(10);
//		
//		textField_6 = new JTextField();
//		textField_6.setBounds(117, 216, 86, 20);
//		frame.getContentPane().add(textField_6);
//		textField_6.setColumns(10);
//		
//		JButton btnAddMember = new JButton("add member");
//		btnAddMember.setBounds(230, 215, 103, 23);
//		btnAddMember.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				createMember();
//			}
//		});
//		frame.getContentPane().add(btnAddMember);
//		
//		JLabel lblMemberName = new JLabel("Member name:");
//		lblMemberName.setBounds(10, 194, 86, 14);
//		frame.getContentPane().add(lblMemberName);
//		
//		JLabel lblMemberClass = new JLabel("Member class:");
//		lblMemberClass.setBounds(117, 194, 86, 14);
//		frame.getContentPane().add(lblMemberClass);
	}
}
