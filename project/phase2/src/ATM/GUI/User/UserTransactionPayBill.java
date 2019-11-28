package ATM.GUI.User;

import ATM.AccountTypeChecker.PayableChecker;
import ATM.AccountTypeChecker.TypeChecker;
import ATM.Accounts.Account;
import ATM.Accounts.Currency;
import ATM.BankIdentities.UserAccManager;
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

public class UserTransactionPayBill extends JFrame {

	private JPanel contentPane;
	private JTextField txtAmount;
	private JTextField txtTo;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public UserTransactionPayBill(Map<String, Object> transMap, UserAccManager uam, TransactionManager tm,
								  CashMachine machine, String id, InfoManager infoManager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPayBill = new JLabel("Pay Bill");
		lblPayBill.setBounds(159, 24, 61, 16);
		contentPane.add(lblPayBill);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(6, 141, 82, 16);
		contentPane.add(lblAmount);

		txtAmount = new JTextField();
		txtAmount.setEditable(true);
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
		txtAmount.setBounds(90, 136, 130, 26);
		contentPane.add(txtAmount);
		
		JLabel lblFromAccount = new JLabel("From Account:");
		lblFromAccount.setBounds(6, 82, 92, 16);
		contentPane.add(lblFromAccount);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(110, 78, 110, 27);
		contentPane.add(comboBox);
		TypeChecker checker = new PayableChecker();
		ArrayList list = uam.getTypeAccounts(checker);
		for (Object o : list) {
			comboBox.addItem(o);
		}
		
		JLabel lblTo = new JLabel("To:");
		lblTo.setBounds(78, 110, 92, 16);
		contentPane.add(lblTo);

		txtTo = new JTextField();
		txtTo.setEditable(true);
		txtTo.setBounds(110, 105, 130, 26);
		contentPane.add(txtTo);
		txtTo.setColumns(10);

		JButton button = new JButton("Pay");
		button.setBounds(131, 218, 117, 29);
		contentPane.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					transMap.put("to", txtTo.getText());
					transMap.put("fromAccount", comboBox.getSelectedItem());
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
						JOptionPane.showMessageDialog(null, "Pay Bill successful!");
					}
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(rootPane, "Transaction is not possible.");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(rootPane, e1.getMessage());
				}
			}
		});

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserTransactionPayBill.this.dispose();
				new UserMakeTransaction(id, infoManager).setVisible(true);
			}
		});
		btnBack.setBounds(19, 219, 117, 29);
		contentPane.add(btnBack);

	}
}
