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
	private JLabel lblYourAtPlanet;
	private JLabel lblYouHavex;
	private JLabel lblItem;
	private JLabel lblItem_1;
	private JLabel lblItem_2;
	private JLabel lblItem_3;
	private JLabel lblPlayerItems;

	public OutPostGui(GameEnvironment ingame, OutPost inoutpost, Crew increw) {
		game = ingame;
		outpost = inoutpost;
		crew = increw;
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
		lblItem.setText(items.get(0).getName());
		lblItem_1.setText(items.get(1).getName());
		lblItem_2.setText(items.get(2).getName());
		lblItem_3.setText(items.get(3).getName());
		
		
		lblYourAtPlanet.setText(String.format("Your at %s outpost", crew.getTheShip().getLocation().getName()));
		lblYouHavex.setText(String.format("You have $%d", crew.getMoney()));
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
		frmOutpost.setBounds(100, 100, 461, 432);
		frmOutpost.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOutpost.getContentPane().setLayout(null);
		
		JLabel lblItemsForSale = new JLabel("Items For Sale:");
		lblItemsForSale.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemsForSale.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblItemsForSale.setBounds(210, 55, 195, 14);
		frmOutpost.getContentPane().add(lblItemsForSale);
		
		JButton btnLeave = new JButton("Leave");
		btnLeave.setBackground(Color.RED);
		btnLeave.setBounds(336, 359, 99, 23);
		btnLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		frmOutpost.getContentPane().add(btnLeave);
		
		lblYourAtPlanet = new JLabel("Your at...");
		lblYourAtPlanet.setFont(new Font("Arial", Font.BOLD, 14));
		lblYourAtPlanet.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourAtPlanet.setBounds(210, 11, 215, 14);
		frmOutpost.getContentPane().add(lblYourAtPlanet);
		
		lblYouHavex = new JLabel("You have $x");
		lblYouHavex.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblYouHavex.setBounds(10, 11, 162, 14);
		frmOutpost.getContentPane().add(lblYouHavex);
		
		lblItem = new JLabel("Item 1");
		lblItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblItem.setBounds(210, 102, 99, 14);
		frmOutpost.getContentPane().add(lblItem);
		
		JButton btnBuy = new JButton("Buy");
		btnBuy.setBackground(Color.LIGHT_GRAY);
		btnBuy.setBounds(210, 127, 99, 23);
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyItem(0);
			}
		});
		frmOutpost.getContentPane().add(btnBuy);
		
		lblItem_1 = new JLabel("Item 2");
		lblItem_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblItem_1.setBounds(336, 102, 99, 14);
		frmOutpost.getContentPane().add(lblItem_1);
		
		JButton button = new JButton("Buy");
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(336, 127, 99, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyItem(1);
			}
		});
		frmOutpost.getContentPane().add(button);
		
		lblItem_2 = new JLabel("Item 3");
		lblItem_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblItem_2.setBounds(210, 200, 99, 14);
		frmOutpost.getContentPane().add(lblItem_2);
		
		JButton btnBuy_1 = new JButton("Buy");
		btnBuy_1.setBackground(Color.LIGHT_GRAY);
		btnBuy_1.setBounds(210, 225, 99, 23);
		btnBuy_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyItem(2);
			}
		});
		frmOutpost.getContentPane().add(btnBuy_1);
		
		lblItem_3 = new JLabel("Item 4");
		lblItem_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblItem_3.setBounds(336, 200, 99, 14);
		frmOutpost.getContentPane().add(lblItem_3);
		
		JButton btnBuy_2 = new JButton("Buy");
		btnBuy_2.setBackground(Color.LIGHT_GRAY);
		btnBuy_2.setBounds(336, 225, 99, 23);
		btnBuy_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyItem(3);
			}
		});
		frmOutpost.getContentPane().add(btnBuy_2);
		
		JButton btnNewButton = new JButton("Description");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(210, 150, 99, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDescription(0);
			}
		});
		frmOutpost.getContentPane().add(btnNewButton);
		
		JButton button_1 = new JButton("Description");
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setBounds(336, 150, 99, 23);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDescription(1);
			}
		});
		frmOutpost.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Description");
		button_2.setBackground(Color.LIGHT_GRAY);
		button_2.setBounds(210, 247, 99, 23);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDescription(2);
			}
		});
		frmOutpost.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("Description");
		button_3.setBackground(Color.LIGHT_GRAY);
		button_3.setBounds(336, 247, 99, 23);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDescription(3);
			}
		});
		frmOutpost.getContentPane().add(button_3);
		
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
