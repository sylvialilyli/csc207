package ATM.GUI.Manager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import ATM.InfoHandling.InfoManager;
import ATM.InfoHandling.InfoStorer;
import ATM.BankIdentities.*;
import ATM.Transactions.*;
import ATM.Machine.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerUndoAccountTrans extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtTrans;


	/**
	 * Create the frame.
	 */
	public ManagerUndoAccountTrans(String id, InfoManager infoManager) {
		InfoStorer infoStorer = infoManager.getInfoStorer();
		BankManager bankManager = infoStorer.getBankManagerMap().get(id);
		TransactionManager transaction = new TransactionManager(infoStorer.getAccTransMap(),infoStorer.getUserTransMap());
		CashMachine cashMachine = new CashMachine();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUndo = new JLabel("Undo Account Transaction");
		lblUndo.setBounds(131, 17, 260, 16);
		contentPane.add(lblUndo);
		
		JLabel lblEnterID = new JLabel("Please enter an account number:");
		lblEnterID.setBounds(89, 63, 238, 16);
		contentPane.add(lblEnterID);
		
		txtID = new JTextField();
		txtID.setBounds(131, 88, 130, 26);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JButton btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accNum = txtID.getText();
				int times = Integer.valueOf(txtTrans.getText());
				try {
					bankManager.undoAccRecentTrans(accNum, transaction, cashMachine, times);
				} catch (NullPointerException ex) {
					JOptionPane.showMessageDialog(rootPane, "Transaction is not possible.");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(rootPane, ex);
				}
			}
		});
		btnUndo.setBounds(274, 214, 117, 29);
		contentPane.add(btnUndo);
		
		JLabel lblNumTrans = new JLabel("Please enter number of transactions needed to undo:");
		lblNumTrans.setBounds(89, 126, 352, 16);
		contentPane.add(lblNumTrans);
		
		txtTrans = new JTextField();
		txtTrans.setColumns(10);
		txtTrans.setBounds(131, 164, 130, 26);
		contentPane.add(txtTrans);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ManagerUndoAccountTrans.this.dispose();
				 new ManagerMainMenu(id, infoManager).setVisible(true);
			}
		});
		btnBack.setBounds(6, 214, 117, 29);
		contentPane.add(btnBack);
	}

}
