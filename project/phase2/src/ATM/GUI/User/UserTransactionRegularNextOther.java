package ATM.GUI;

import ATM.Accounts.Account;
import ATM.Accounts.Currency;
import ATM.BankIdentities.NoSuchAccountException;
import ATM.BankIdentities.UserAccManager;
import ATM.GUI.User.UserTransactionRegularNext;
import ATM.InfoHandling.InfoManager;
import ATM.Machine.CashMachine;
import ATM.Transactions.Transaction;
import ATM.Transactions.TransactionManager;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UserTransactionRegularNextOther extends JFrame {

	private JPanel contentPane;
	private JTextField txtAmount;
	private JTextField txtAccount;

	/**
	 * Create the frame.
	 */
	public UserTransactionRegularNextOther(Map<String, Object> transMap, UserAccManager uam, TransactionManager tm,
										   CashMachine machine, String id, InfoManager infoManager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.setBounds(250, 203, 117, 29);
		contentPane.add(btnTransfer);
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Currency amount;
					if (((Account)transMap.get("fromAccount")).getCurrencyType().equals("CAD")) {
						amount = new Currency(Double.valueOf(txtAmount.getText()));
					} else {
						amount = new Currency(((Account)transMap.get("fromAccount")).getCurrencyType(),
								Double.valueOf(txtAmount.getText()));
					}
					transMap.put("toAccount", infoManager.getAccount(txtAccount.getText()));
					Transaction trans = tm.makeTransaction(transMap, machine);
					if (trans.isHappened()) {
						JOptionPane.showMessageDialog(null, "Transaction successful!");
					}
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(rootPane, "Transaction is not possible.");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(rootPane, e1.getMessage());
				}
			}
		});

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(55, 203, 117, 29);
		contentPane.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserTransactionRegularNextOther.this.dispose();
				new UserTransactionRegularNext(transMap, uam, tm, machine, id, infoManager).setVisible(true);
			}
		});

		txtAmount = new JTextField();
		txtAmount.addKeyListener(new KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				try {
					double number = Double.parseDouble(txtAmount.getText());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(rootPane, "Only Decimal Allowed");
					txtAmount.setText("");
				}
			}

		});
		txtAmount.setColumns(10);
		txtAmount.setBounds(198, 83, 130, 26);
		contentPane.add(txtAmount);

		JLabel lblAmount = new JLabel("Enter Amount:");
		lblAmount.setBounds(74, 88, 112, 16);
		contentPane.add(lblAmount);


		JLabel lblAccount = new JLabel("Account Number:");
		lblAccount.setBounds(74, 28, 112, 16);
		contentPane.add(lblAccount);

		txtAccount = new JTextField();
		txtAccount.setEditable(true);
		txtAccount.setBounds(198, 23, 130, 26);
		contentPane.add(txtAccount);
		txtAccount.setColumns(10);
	}

}