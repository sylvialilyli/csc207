package ATM.GUI.Manager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import ATM.InfoHandling.InfoManager;
import ATM.InfoHandling.InfoStorer;
import ATM.BankIdentities.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;

public class ManagerCreateChequingAccount extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserID;


	/**
	 * Create the frame.
	 */
	public ManagerCreateChequingAccount(String id, InfoManager infoManager) {

		InfoStorer infoStorer = infoManager.getInfoStorer();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPleaseEnterUser = new JLabel("Please enter user ID:");
		lblPleaseEnterUser.setBounds(6, 26, 144, 16);
		contentPane.add(lblPleaseEnterUser);
		
		txtUserID = new JTextField();
		txtUserID.addKeyListener(new KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				try {
					long number = Long.parseLong(txtUserID.getText());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(rootPane, "Only Numbers Allowed");
					txtUserID.setText("");
				}
			}

		});
		txtUserID.setBounds(143, 21, 130, 26);
		contentPane.add(txtUserID);
		txtUserID.setColumns(10);
		
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerCreateChequingAccount.this.dispose();
				new ManagerCreateAccount(id, infoManager).setVisible(true);
			}
		});
		btnBack.setBounds(34, 132, 117, 29);
		contentPane.add(btnBack);
		
		JRadioButton rdbtnUSD = new JRadioButton("USD");
		rdbtnUSD.setBounds(82, 82, 70, 23);
		contentPane.add(rdbtnUSD);
		
		JRadioButton rdbtnCAD = new JRadioButton("CAD");
		rdbtnCAD.setSelected(true);
		rdbtnCAD.setBounds(0, 82, 70, 23);
		contentPane.add(rdbtnCAD);
		
		JLabel lblCurrency = new JLabel("Choose Currency Type:");
		lblCurrency.setBounds(6, 54, 188, 16);
		contentPane.add(lblCurrency);
		
		JRadioButton rdbtnCNY = new JRadioButton("CNY");
		rdbtnCNY.setBounds(157, 81, 70, 23);
		contentPane.add(rdbtnCNY);
		
		JRadioButton rdbtnEUR = new JRadioButton("EUR");
		rdbtnEUR.setBounds(221, 82, 70, 23);
		contentPane.add(rdbtnEUR);
		
		JRadioButton rdbtnGBP = new JRadioButton("GBP");
		rdbtnGBP.setBounds(303, 82, 70, 23);
		contentPane.add(rdbtnGBP);
		
		JRadioButton rdbtnINR = new JRadioButton("INR");
		rdbtnINR.setBounds(380, 82, 70, 23);
		contentPane.add(rdbtnINR);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnCAD);
		group.add(rdbtnUSD);
		group.add(rdbtnCNY);
		group.add(rdbtnEUR);
		group.add(rdbtnGBP);
		group.add(rdbtnINR);
		
		JButton btnCreateChequingAccount = new JButton("Create Chequing Account");
		btnCreateChequingAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String type = rdbtnCAD.getText();
				if (rdbtnCAD.isSelected()) {
					type = rdbtnCAD.getText();
				}else if (rdbtnUSD.isSelected()) {
					type = rdbtnUSD.getText();
				}else if (rdbtnCNY.isSelected()) {
					type = rdbtnCNY.getText();
				}else if (rdbtnEUR.isSelected()) {
					type = rdbtnEUR.getText();
				}else if (rdbtnGBP.isSelected()) {
					type = rdbtnGBP.getText();
				}else if (rdbtnINR.isSelected()) {
					type = rdbtnINR.getText();
				}
				String ID = txtUserID.getText();
				AccountOwnable user;
				if(infoManager.getStaffMap().containsKey(ID)){
					user = infoManager.getStaffMap().get(ID);
				}
				else{
					user = infoManager.getUser(ID);
				}
				AccountCreator creater = new AccountCreator(user, infoStorer, type);
				creater.createNewChequingAccount();
				JOptionPane.showMessageDialog(null, "Chequing Account Created");
			}
		});
		btnCreateChequingAccount.setBounds(199, 132, 191, 29);
		contentPane.add(btnCreateChequingAccount);
	}
}
