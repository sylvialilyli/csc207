package ATM.GUI;

import ATM.AccountTypeChecker.PayableChecker;
import ATM.AccountTypeChecker.TransferInableChecker;
import ATM.AccountTypeChecker.TypeChecker;
import ATM.Accounts.Account;
import ATM.Accounts.Currency;
import ATM.BankIdentities.UserAccManager;
import ATM.GUI.User.UserMakeTransaction;
import ATM.GUI.User.UserTransactionRegular;
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
import java.util.ArrayList;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UserTransactionRegularNextMy extends JFrame {

	private JPanel contentPane;
	private JTextField txtAmount;


	/**
	 * Create the frame.
	 */
	public UserTransactionRegularNextMy(Map<String, Object> transMap, UserAccManager uam, TransactionManager tm,
										CashMachine machine, String id, InfoManager infoManager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSelectAccount = new JLabel("Select Account:");
		lblSelectAccount.setBounds(60, 26, 112, 16);
		contentPane.add(lblSelectAccount);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(184, 22, 124, 27);
		contentPane.add(comboBox);
		TypeChecker checker = new TransferInableChecker();
		ArrayList list = uam.getTypeAccounts(checker);
		for (Object o : list) {
			comboBox.addItem(o);
		}

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
		txtAmount.setBounds(184, 81, 130, 26);
		contentPane.add(txtAmount);
		txtAmount.setColumns(10);

		JLabel lblEnterAmount = new JLabel("Enter Amount:");
		lblEnterAmount.setBounds(60, 86, 112, 16);
		contentPane.add(lblEnterAmount);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(41, 201, 117, 29);
		contentPane.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserTransactionRegularNextMy.this.dispose();
				new UserTransactionRegularNext(transMap, uam, tm, machine, id, infoManager).setVisible(true);
			}
		});

		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.setBounds(236, 201, 117, 29);
		contentPane.add(btnTransfer);
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					transMap.put("toAccount", comboBox.getSelectedItem());
					Currency amount;
					if (((Account)transMap.get("fromAccount")).getCurrencyType().equals("CAD")) {
						amount = new Currency(Double.valueOf(txtAmount.getText()));
					} else {
						amount = new Currency(((Account)transMap.get("fromAccount")).getCurrencyType(),
								Double.valueOf(txtAmount.getText()));
					}
					transMap.put("amount", amount);
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
	}
}