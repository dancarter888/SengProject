import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class OutPostGui {

	private JFrame frmOutpost;
	private OutPost outpost;
	private Crew crew;
	private GameEnvironment game;
	private JLabel lblYouAreAtPlanet;
	private JLabel lblYouHaveMoney;
	private JLabel lblItem_1;
	private JLabel lblItem_2;
	private JLabel lblItem_3;
	private JLabel lblItem_4;
	private JLabel lblCost_1;
	private JLabel lblCost_2;
	private JLabel lblCost_3;
	private JLabel lblCost_4;
	private JLabel lblPlayerItems;

	public OutPostGui(GameEnvironment inGame, OutPost inOutPost, Crew inCrew) {
		game = inGame;
		outpost = inOutPost;
		crew = inCrew;
		initialize();
		setLabels();
		frmOutpost.setVisible(true);
	}

	public void closeWindow() {
		frmOutpost.dispose();
	}
	
	public void finishedWindow() {
		game.closeOutPostGui(this);
	}
	
	public void setLabels() {
		ArrayList<Item> items = outpost.getOutPostItems();
		lblItem_1.setText(items.get(0).getName());
		lblCost_1.setText(String.format("$%d", items.get(0).getCost()));
		lblItem_2.setText(items.get(1).getName());
		lblCost_2.setText(String.format("$%d", items.get(1).getCost()));
		lblItem_3.setText(items.get(2).getName());
		lblCost_3.setText(String.format("$%d", items.get(2).getCost()));
		lblItem_4.setText(items.get(3).getName());
		lblCost_4.setText(String.format("$%d", items.get(3).getCost()));
		
		
		lblYouAreAtPlanet.setText(String.format("You are at %s's outpost", crew.getTheShip().getLocation().getName()));
		lblYouHaveMoney.setText(String.format("You have $%d", crew.getMoney()));
		String playerItems = outpost.viewMyItems(crew);
		lblPlayerItems.setText(playerItems);
	}
	
	public void buyItem(int itemNum) {
		outpost.buyItems(crew, itemNum, frmOutpost);
		setLabels();
	}
	
	public void setDescription(int itemNum) {
		Item itemToDescribe = outpost.getOutPostItems().get(itemNum);
		JOptionPane.showMessageDialog(frmOutpost, itemToDescribe.getDescription());
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OutPostGui window = new OutPostGui();
					window.frmOutpost.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OutPostGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOutpost = new JFrame();
		frmOutpost.setTitle("OutPost");
		frmOutpost.setBounds(100, 100, 528, 432);
		frmOutpost.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOutpost.getContentPane().setLayout(null);
		
		JLabel lblItemsForSale = new JLabel("Items For Sale:");
		lblItemsForSale.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemsForSale.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblItemsForSale.setBounds(264, 55, 195, 14);
		frmOutpost.getContentPane().add(lblItemsForSale);
		
		JButton btnLeave = new JButton("Leave \r\nOutPost");
		btnLeave.setBackground(Color.RED);
		btnLeave.setBounds(264, 359, 238, 23);
		btnLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		frmOutpost.getContentPane().add(btnLeave);
		
		lblYouAreAtPlanet = new JLabel("You are at...");
		lblYouAreAtPlanet.setFont(new Font("Arial", Font.BOLD, 14));
		lblYouAreAtPlanet.setHorizontalAlignment(SwingConstants.LEFT);
		lblYouAreAtPlanet.setBounds(247, 11, 255, 14);
		frmOutpost.getContentPane().add(lblYouAreAtPlanet);
		
		lblYouHaveMoney = new JLabel("You have $x");
		lblYouHaveMoney.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblYouHaveMoney.setBounds(10, 11, 162, 14);
		frmOutpost.getContentPane().add(lblYouHaveMoney);
		
		lblItem_1 = new JLabel("Item 1");
		lblItem_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblItem_1.setBounds(264, 102, 99, 14);
		frmOutpost.getContentPane().add(lblItem_1);

		lblCost_1 = new JLabel("Cost 1");
		lblCost_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCost_1.setBounds(264, 120, 99, 14);
		frmOutpost.getContentPane().add(lblCost_1);
		
		JButton btnBuy_1 = new JButton("Buy");
		btnBuy_1.setBackground(Color.LIGHT_GRAY);
		btnBuy_1.setBounds(264, 140, 99, 23);
		btnBuy_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyItem(0);
			}
		});
		frmOutpost.getContentPane().add(btnBuy_1);
		
		JButton btnDescription_1 = new JButton("Description");
		btnDescription_1.setBackground(Color.LIGHT_GRAY);
		btnDescription_1.setBounds(264, 163, 99, 23);
		btnDescription_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDescription(0);
			}
		});
		frmOutpost.getContentPane().add(btnDescription_1);
		
		lblItem_2 = new JLabel("Item 2");
		lblItem_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblItem_2.setBounds(403, 102, 99, 14);
		frmOutpost.getContentPane().add(lblItem_2);
		
		lblCost_2 = new JLabel("Cost 2");
		lblCost_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCost_2.setBounds(403, 120, 99, 14);
		frmOutpost.getContentPane().add(lblCost_2);
		
		JButton btnBuy_2 = new JButton("Buy");
		btnBuy_2.setBackground(Color.LIGHT_GRAY);
		btnBuy_2.setBounds(403, 140, 99, 23);
		btnBuy_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyItem(1);
			}
		});
		frmOutpost.getContentPane().add(btnBuy_2);
		
		JButton btnDescription_2 = new JButton("Description");
		btnDescription_2.setBackground(Color.LIGHT_GRAY);
		btnDescription_2.setBounds(403, 163, 99, 23);
		btnDescription_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDescription(1);
			}
		});
		frmOutpost.getContentPane().add(btnDescription_2);
		
		lblItem_3 = new JLabel("Item 3");
		lblItem_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblItem_3.setBounds(264, 216, 99, 14);
		frmOutpost.getContentPane().add(lblItem_3);
		
		lblCost_3 = new JLabel("Cost 3");
		lblCost_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblCost_3.setBounds(264, 234, 99, 14);
		frmOutpost.getContentPane().add(lblCost_3);
		
		JButton btnBuy_3 = new JButton("Buy");
		btnBuy_3.setBackground(Color.LIGHT_GRAY);
		btnBuy_3.setBounds(264, 254, 99, 23);
		btnBuy_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyItem(2);
			}
		});
		frmOutpost.getContentPane().add(btnBuy_3);
		
		JButton btnDescription_3 = new JButton("Description");
		btnDescription_3.setBackground(Color.LIGHT_GRAY);
		btnDescription_3.setBounds(264, 277, 99, 23);
		btnDescription_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDescription(2);
			}
		});
		frmOutpost.getContentPane().add(btnDescription_3);
		
		lblItem_4 = new JLabel("Item 4");
		lblItem_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblItem_4.setBounds(403, 216, 99, 14);
		frmOutpost.getContentPane().add(lblItem_4);
		
		lblCost_4 = new JLabel("Cost 4");
		lblCost_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblCost_4.setBounds(403, 234, 99, 14);
		frmOutpost.getContentPane().add(lblCost_4);
		
		JButton btnBuy_4 = new JButton("Buy");
		btnBuy_4.setBackground(Color.LIGHT_GRAY);
		btnBuy_4.setBounds(403, 254, 99, 23);
		btnBuy_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyItem(3);
			}
		});
		frmOutpost.getContentPane().add(btnBuy_4);
		
		JButton btnDescription_4 = new JButton("Description");
		btnDescription_4.setBackground(Color.LIGHT_GRAY);
		btnDescription_4.setBounds(403, 277, 99, 23);
		btnDescription_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDescription(3);
			}
		});
		frmOutpost.getContentPane().add(btnDescription_4);
		
		lblPlayerItems = new JLabel("Items");
		lblPlayerItems.setBounds(10, 101, 190, 143);
		frmOutpost.getContentPane().add(lblPlayerItems);
		
		JLabel lblYourItems = new JLabel("Your Items:");
		lblYourItems.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblYourItems.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourItems.setBounds(10, 46, 162, 23);
		frmOutpost.getContentPane().add(lblYourItems);
		
	}
}
