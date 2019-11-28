package ATM.GUI.Manager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import ATM.BankIdentities.AccountCreator;
import ATM.BankIdentities.AccountOwnable;
import ATM.BankIdentities.User;
import ATM.InfoHandling.InfoManager;
import ATM.InfoHandling.InfoStorer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;

public class ManagerCreateLineOfCreditAccount extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtJoint;
	private JTextField txtLimit;


	/**
	 * Create the frame.
	 */
	public ManagerCreateLineOfCreditAccount(String id, InfoManager infoManager) {

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblID = new JLabel("Please enter user ID:");
		lblID.setBounds(31, 11, 144, 16);
		contentPane.add(lblID);
		
		txtID = new JTextField();
		txtID.addKeyListener(new KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				try {
					long number = Long.parseLong(txtID.getText());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(rootPane, "Only Numbers Allowed");
					txtID.setText("");
				}
			}

		});
		txtID.setColumns(10);
		txtID.setBounds(168, 6, 130, 26);
		contentPane.add(txtID);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerCreateLineOfCreditAccount.this.dispose();
				new ManagerCreateAccount(id, infoManager).setVisible(true);
			}
		});
		btnBack.setBounds(37, 150, 117, 29);
		contentPane.add(btnBack);
		
		JLabel lblLimit = new JLabel("Limit:");
		lblLimit.setBounds(32, 112, 61, 16);
		contentPane.add(lblLimit);
		
		txtLimit = new JTextField();
		txtLimit.setColumns(10);
		txtLimit.setBounds(78, 107, 130, 26);
		contentPane.add(txtLimit);
		
		JRadioButton rdbtnCAD = new JRadioButton("CAD");
		rdbtnCAD.setSelected(true);
		rdbtnCAD.setBounds(0, 67, 70, 23);
		contentPane.add(rdbtnCAD);
		
		JLabel label = new JLabel("Choose Currency Type:");
		label.setBounds(6, 39, 188, 16);
		contentPane.add(label);
		
		JRadioButton rdbtnUSD = new JRadioButton("USD");
		rdbtnUSD.setBounds(82, 67, 70, 23);
		contentPane.add(rdbtnUSD);
		
		JRadioButton rdbtnCNY = new JRadioButton("CNY");
		rdbtnCNY.setBounds(157, 66, 70, 23);
		contentPane.add(rdbtnCNY);
		
		JRadioButton rdbtnEUR = new JRadioButton("EUR");
		rdbtnEUR.setBounds(221, 67, 70, 23);
		contentPane.add(rdbtnEUR);
		
		JRadioButton rdbtnGBP = new JRadioButton("GBP");
		rdbtnGBP.setBounds(303, 67, 70, 23);
		contentPane.add(rdbtnGBP);
		
		JRadioButton rdbtnINR = new JRadioButton("INR");
		rdbtnINR.setBounds(380, 67, 70, 23);
		contentPane.add(rdbtnINR);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnCAD);
		group.add(rdbtnUSD);
		group.add(rdbtnCNY);
		group.add(rdbtnEUR);
		group.add(rdbtnGBP);
		group.add(rdbtnINR);
		
		JButton btnCreateLineOf = new JButton("Create Line of Credit Account");
		btnCreateLineOf.addActionListener(new ActionListener() {
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
				String ID = txtID.getText();
				String limit = txtLimit.getText();
				int limitint = Integer.valueOf(limit);
				InfoStorer infoStorer = infoManager.getInfoStorer();
				AccountOwnable user;
				if(infoManager.getStaffMap().containsKey(ID)){
					user = infoManager.getStaffMap().get(ID);
				}
				else{
					user = infoManager.getUser(ID);
				}
				AccountCreator creater = new AccountCreator(user, infoStorer, type);
				
				creater.createNewLineOfCredit(limitint);
				JOptionPane.showMessageDialog(null, "Line Of Credit Account Created");
			}
		});
		btnCreateLineOf.setBounds(217, 150, 219, 29);
		contentPane.add(btnCreateLineOf);
	}

}
