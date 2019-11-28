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

public class ManagerUndoUserTrans extends JFrame {

	private JPanel contentPane;
	private JTextField txtTrans;
	private JTextField txtID;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public ManagerUndoUserTrans(String id, InfoManager infoManager) {
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
		
		JButton btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID = txtID.getText();
				int times = Integer.valueOf(txtTrans.getText());
				try{
					bankManager.undoUserRecentTrans(ID, transaction, cashMachine, times);
				} catch (NullPointerException ex) {
					JOptionPane.showMessageDialog(rootPane, "Transaction is not possible.");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(rootPane, ex.getMessage());
				}
			}
		});
		btnUndo.setBounds(292, 222, 117, 29);
		contentPane.add(btnUndo);
		
		txtTrans = new JTextField();
		txtTrans.setColumns(10);
		txtTrans.setBounds(99, 172, 130, 26);
		contentPane.add(txtTrans);
		
		JLabel lblTrans = new JLabel("Please enter number of transactions needed to undo:");
		lblTrans.setBounds(57, 134, 352, 16);
		contentPane.add(lblTrans);
		
		txtID = new JTextField();
		txtID.setColumns(10);
		txtID.setBounds(99, 96, 130, 26);
		contentPane.add(txtID);
		
		JLabel lblID = new JLabel("Please enter the user ID:");
		lblID.setBounds(57, 71, 238, 16);
		contentPane.add(lblID);
		
		JLabel lblUndoUserTransaction = new JLabel("Undo User Transaction");
		lblUndoUserTransaction.setBounds(99, 25, 260, 16);
		contentPane.add(lblUndoUserTransaction);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerUndoUserTrans.this.dispose();
				new ManagerMainMenu(id, infoManager).setVisible(true);
			}
		});
		btnBack.setBounds(6, 222, 117, 29);
		contentPane.add(btnBack);
	}
}
