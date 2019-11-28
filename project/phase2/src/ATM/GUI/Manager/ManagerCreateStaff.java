package ATM.GUI.Manager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import ATM.BankIdentities.BankManager;
import  ATM.InfoHandling.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerCreateStaff extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ManagerCreateStaff(String id, InfoManager infoManager) {
		InfoStorer infoStorer = infoManager.getInfoStorer();
		BankManager bankManager = infoManager.getBankManager(id);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCurrecy = new JLabel("Choose Currency Type:");
		lblCurrecy.setBounds(6, 6, 188, 16);
		contentPane.add(lblCurrecy);
		
		JRadioButton rdbtnCAD = new JRadioButton("CAD");
		rdbtnCAD.setSelected(true);
		rdbtnCAD.setBounds(0, 34, 70, 23);
		contentPane.add(rdbtnCAD);
		
		JRadioButton rdbtnUSD = new JRadioButton("USD");
		rdbtnUSD.setBounds(82, 34, 70, 23);
		contentPane.add(rdbtnUSD);
		
		JRadioButton rdbtnCNY = new JRadioButton("CNY");
		rdbtnCNY.setBounds(157, 33, 70, 23);
		contentPane.add(rdbtnCNY);
		
		JRadioButton rdbtnEUR = new JRadioButton("EUR");
		rdbtnEUR.setBounds(221, 34, 70, 23);
		contentPane.add(rdbtnEUR);
		
		JRadioButton rdbtnGBP = new JRadioButton("GBP");
		rdbtnGBP.setBounds(303, 34, 70, 23);
		contentPane.add(rdbtnGBP);
		
		JRadioButton rdbtnINR = new JRadioButton("INR");
		rdbtnINR.setBounds(380, 34, 70, 23);
		contentPane.add(rdbtnINR);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnCAD);
		group.add(rdbtnUSD);
		group.add(rdbtnCNY);
		group.add(rdbtnEUR);
		group.add(rdbtnGBP);
		group.add(rdbtnINR);

		JButton btnCreateStaff = new JButton("Create Staff");
		btnCreateStaff.addActionListener(new ActionListener() {
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

				String ID = bankManager.createBankStaff(type, infoStorer);
				JOptionPane.showMessageDialog(null, "New staff created! user ID: " + ID);


			}
		});
		btnCreateStaff.setBounds(190, 68, 117, 29);
		contentPane.add(btnCreateStaff);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerCreateStaff.this.dispose();
				new ManagerMainMenu(id, infoManager).setVisible(true);				
			}
		});
		btnBack.setBounds(6, 68, 117, 29);
		contentPane.add(btnBack);
	}

}
