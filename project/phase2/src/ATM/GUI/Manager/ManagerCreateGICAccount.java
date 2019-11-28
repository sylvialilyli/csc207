package ATM.GUI.Manager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import ATM.BankIdentities.AccountCreator;
import ATM.BankIdentities.AccountOwnable;
import ATM.BankIdentities.User;
import ATM.InfoHandling.InfoManager;
import ATM.InfoHandling.InfoStorer;
import ATM.Accounts.Plans.GICPlans.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerCreateGICAccount extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtJoint;
	private JTextField txtAmount;


	/**
	 * Create the frame.
	 */
	public ManagerCreateGICAccount(String id, InfoManager infoManager) {

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblID = new JLabel("Please enter user ID:");
		lblID.setBounds(25, 11, 144, 16);
		contentPane.add(lblID);
		
		txtID = new JTextField();
		txtID.setColumns(10);
		txtID.setBounds(162, 6, 130, 26);
		contentPane.add(txtID);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerCreateGICAccount.this.dispose();
				new ManagerCreateAccount(id, infoManager).setVisible(true);
			}
		});
		btnBack.setBounds(41, 225, 117, 29);
		contentPane.add(btnBack);

		JRadioButton rdbtnCAD = new JRadioButton("CAD");
		rdbtnCAD.setSelected(true);
		rdbtnCAD.setBounds(0, 67, 70, 23);
		contentPane.add(rdbtnCAD);
		
		JLabel lblCurrency = new JLabel("Choose Currency Type:");
		lblCurrency.setBounds(6, 39, 188, 16);
		contentPane.add(lblCurrency);
		
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
		
		

		
		JLabel lblChoosePlan = new JLabel("Choose Plan:");
		lblChoosePlan.setBounds(6, 102, 188, 16);
		contentPane.add(lblChoosePlan);
		
		JRadioButton rdbtnAnnualInterest = new JRadioButton("Annual Interest");
		rdbtnAnnualInterest.setSelected(true);
		rdbtnAnnualInterest.setBounds(0, 130, 144, 23);
		contentPane.add(rdbtnAnnualInterest);
		
		JRadioButton rdbtnTwoYearInterest = new JRadioButton("Two Year Interest");
		rdbtnTwoYearInterest.setBounds(134, 130, 140, 23);
		contentPane.add(rdbtnTwoYearInterest);
		
		JRadioButton rdbtnFiveYearInterest = new JRadioButton("Five Year Interest");
		rdbtnFiveYearInterest.setBounds(286, 130, 164, 23);
		contentPane.add(rdbtnFiveYearInterest);
		
		JLabel lblEnterMoneyAmount = new JLabel("Enter Money Amount:");
		lblEnterMoneyAmount.setBounds(9, 165, 160, 16);
		contentPane.add(lblEnterMoneyAmount);
		
		txtAmount = new JTextField();
		txtAmount.setBounds(181, 160, 130, 26);
		contentPane.add(txtAmount);
		txtAmount.setColumns(10);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnCAD);
		group.add(rdbtnUSD);
		group.add(rdbtnCNY);
		group.add(rdbtnEUR);
		group.add(rdbtnGBP);
		group.add(rdbtnINR);
		
		ButtonGroup group2 = new ButtonGroup();
		group2.add(rdbtnAnnualInterest);
		group2.add(rdbtnTwoYearInterest);
		group2.add(rdbtnFiveYearInterest);
		
		JButton btnCreateGicAccount = new JButton("Create GIC Account");
		btnCreateGicAccount.addActionListener(new ActionListener() {
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
				GICPlan plan = new AnnualInterest();
				if (rdbtnAnnualInterest.isSelected()) {
					plan = new AnnualInterest();
				}else if (rdbtnTwoYearInterest.isSelected()) {
					 plan = new TwoYearInterest();
				}else if (rdbtnCNY.isSelected()) {
					plan = new FiveYearInterest();
				}
				String ID = txtID.getText();
				Double principle = Double.valueOf(txtAmount.getText());
				InfoStorer infoStorer = infoManager.getInfoStorer();
				AccountOwnable user;
				if(infoManager.getStaffMap().containsKey(ID)){
					user = infoManager.getStaffMap().get(ID);
				}
				else{
					user = infoManager.getUser(ID);
				}
				AccountCreator creater = new AccountCreator(user, infoStorer, type);
				creater.createNewGICAccount(plan, principle);
				JOptionPane.showMessageDialog(null, "GIC Account Created");
			}
		});
		btnCreateGicAccount.setBounds(221, 225, 191, 29);
		contentPane.add(btnCreateGicAccount);
	}
}
