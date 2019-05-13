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

public class OutPostGui {

	private JFrame frame;
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
		frame.setVisible(true);
	}

	public void closeWindow() {
		frame.dispose();
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
		outpost.buyItems(crew, itemNum, frame);
		setLabels();
	}
	
	public void setDescription(int itemNum) {
		Item itemToDescribe = outpost.getOutPostItems().get(itemNum);
		JOptionPane.showMessageDialog(frame, itemToDescribe.getDescription());
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OutPostGui window = new OutPostGui();
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
	public OutPostGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 431);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblOutpost = new JLabel("OutPost");
		lblOutpost.setBounds(294, 11, 51, 14);
		frame.getContentPane().add(lblOutpost);
		
		JButton btnLeave = new JButton("Leave");
		btnLeave.setBounds(506, 326, 118, 55);
		btnLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		frame.getContentPane().add(btnLeave);
		
		lblYourAtPlanet = new JLabel("Your at planet Outpost");
		lblYourAtPlanet.setBounds(10, 11, 162, 14);
		frame.getContentPane().add(lblYourAtPlanet);
		
		lblYouHavex = new JLabel("You have $x");
		lblYouHavex.setBounds(506, 11, 118, 14);
		frame.getContentPane().add(lblYouHavex);
		
		lblItem = new JLabel("Item 1");
		lblItem.setBounds(10, 56, 89, 14);
		frame.getContentPane().add(lblItem);
		
		JButton btnBuy = new JButton("Buy");
		btnBuy.setBounds(10, 81, 89, 23);
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyItem(0);
			}
		});
		frame.getContentPane().add(btnBuy);
		
		lblItem_1 = new JLabel("Item 2");
		lblItem_1.setBounds(136, 56, 89, 14);
		frame.getContentPane().add(lblItem_1);
		
		JButton button = new JButton("Buy");
		button.setBounds(136, 81, 89, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyItem(1);
			}
		});
		frame.getContentPane().add(button);
		
		lblItem_2 = new JLabel("Item 3");
		lblItem_2.setBounds(10, 154, 89, 14);
		frame.getContentPane().add(lblItem_2);
		
		JButton btnBuy_1 = new JButton("Buy");
		btnBuy_1.setBounds(10, 179, 89, 23);
		btnBuy_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyItem(2);
			}
		});
		frame.getContentPane().add(btnBuy_1);
		
		lblItem_3 = new JLabel("Item 4");
		lblItem_3.setBounds(136, 154, 89, 14);
		frame.getContentPane().add(lblItem_3);
		
		JButton btnBuy_2 = new JButton("Buy");
		btnBuy_2.setBounds(136, 179, 89, 23);
		btnBuy_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyItem(3);
			}
		});
		frame.getContentPane().add(btnBuy_2);
		
		JButton btnNewButton = new JButton("Description");
		btnNewButton.setBounds(10, 104, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDescription(0);
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JButton button_1 = new JButton("Description");
		button_1.setBounds(136, 104, 89, 23);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDescription(1);
			}
		});
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Description");
		button_2.setBounds(10, 201, 89, 23);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDescription(2);
			}
		});
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("Description");
		button_3.setBounds(136, 201, 89, 23);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDescription(3);
			}
		});
		frame.getContentPane().add(button_3);
		
		lblPlayerItems = new JLabel("Items");
		lblPlayerItems.setBounds(434, 119, 190, 143);
		frame.getContentPane().add(lblPlayerItems);
		
		JLabel lblYourItems = new JLabel("Your Items:");
		lblYourItems.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblYourItems.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourItems.setBounds(434, 56, 162, 23);
		frame.getContentPane().add(lblYourItems);
	}
}
