package ATM.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ATM.BankIdentities.User;
import ATM.BankSystem.Date;
import ATM.InfoHandling.InfoManager;
import ATM.InfoHandling.InfoStorer;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartMenu extends JFrame {

	private JPanel contentPane;

    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartMenu frame = new StartMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartMenu() {		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAtm = new JLabel("ATM");
		lblAtm.setBounds(5, 5, 440, 95);
		lblAtm.setFont(new Font("Lucida Grande", Font.PLAIN, 80));
		lblAtm.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAtm);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartMenu.this.dispose();
				new DateMenu().setVisible(true);
			}
		});
		btnStart.setBounds(135, 144, 189, 82);
		btnStart.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		contentPane.add(btnStart);
	}
	
	

}
