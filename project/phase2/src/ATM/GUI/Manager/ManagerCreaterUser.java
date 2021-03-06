package ATM.GUI.Manager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import ATM.BankIdentities.BankManager;
import ATM.BankIdentities.PasswordManager;
import ATM.InfoHandling.InfoManager;
import ATM.InfoHandling.InfoStorer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class ManagerCreaterUser extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public ManagerCreaterUser(String id, InfoManager infoManager) {
        InfoStorer infoStorer = infoManager.getInfoStorer();
        BankManager bankManager = infoManager.getBankManager(id);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChooseCurrencyType = new JLabel("Choose Currency Type:");
		lblChooseCurrencyType.setBounds(6, 6, 188, 16);
		contentPane.add(lblChooseCurrencyType);
		
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerCreaterUser.this.dispose();
				new ManagerMainMenu(id, infoManager).setVisible(true);
			}
		});
		btnBack.setBounds(6, 68, 117, 29);
		contentPane.add(btnBack);
		
		JRadioButton rdbtnCAD = new JRadioButton("CAD");
		rdbtnCAD.setSelected(true);
		rdbtnCAD.setBounds(0, 34, 70, 23);
		contentPane.add(rdbtnCAD);
		
		JRadioButton rdbtnUsd = new JRadioButton("USD");
		rdbtnUsd.setBounds(82, 34, 70, 23);
		contentPane.add(rdbtnUsd);
		
		JRadioButton rdbtnRmb = new JRadioButton("CNY");
		rdbtnRmb.setBounds(157, 33, 70, 23);
		contentPane.add(rdbtnRmb);
		
		JRadioButton rdbtnEur = new JRadioButton("EUR");
		rdbtnEur.setBounds(221, 34, 70, 23);
		contentPane.add(rdbtnEur);
		
		JRadioButton rdbtnGbp = new JRadioButton("GBP");
		rdbtnGbp.setBounds(303, 34, 70, 23);
		contentPane.add(rdbtnGbp);
		
		JRadioButton rdbtnInr = new JRadioButton("INR");
		rdbtnInr.setBounds(380, 34, 70, 23);
		contentPane.add(rdbtnInr);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnCAD);
		group.add(rdbtnUsd);
		group.add(rdbtnRmb);
		group.add(rdbtnEur);
		group.add(rdbtnGbp);
		group.add(rdbtnInr);
		
		JButton btnCreateUser = new JButton("Create User");
		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String type = rdbtnCAD.getText();
				if (rdbtnCAD.isSelected()) {
					type = rdbtnCAD.getText();
				}else if (rdbtnUsd.isSelected()) {
					type = rdbtnUsd.getText();
				}else if (rdbtnRmb.isSelected()) {
					type = rdbtnRmb.getText();
				}else if (rdbtnEur.isSelected()) {
					type = rdbtnEur.getText();
				}else if (rdbtnGbp.isSelected()) {
					type = rdbtnGbp.getText();
				}else if (rdbtnInr.isSelected()) {
					type = rdbtnInr.getText();
				}
				
				String ID = bankManager.createUser(type, infoStorer);
				JOptionPane.showMessageDialog(null, "New user created! user ID: " + ID);

			}
			
		});
		btnCreateUser.setBounds(190, 68, 117, 29);
		contentPane.add(btnCreateUser);
	}
}
