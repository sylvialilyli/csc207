package ATM.GUI.User;

import ATM.AccountTypeChecker.PayableChecker;
import ATM.AccountTypeChecker.TransferOutableChecker;
import ATM.AccountTypeChecker.TypeChecker;
import ATM.BankIdentities.UserAccManager;
import ATM.InfoHandling.InfoManager;
import ATM.Machine.CashMachine;
import ATM.Transactions.Transaction;
import ATM.Transactions.TransactionManager;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UserTransactionRegular extends JFrame {

	private JPanel contentPane;
	private JTextField txtFrom;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public UserTransactionRegular(Map<String, Object> transMap, UserAccManager uam, TransactionManager tm,
								  CashMachine machine, String id, InfoManager infoManager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegular = new JLabel("Regular Transaction:");
		lblRegular.setBounds(148, 18, 154, 16);
		contentPane.add(lblRegular);
		
		JLabel lblFromAccount = new JLabel("From Account:");
		lblFromAccount.setBounds(37, 68, 99, 16);
		contentPane.add(lblFromAccount);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(110, 78, 110, 27);
		contentPane.add(comboBox);
		TypeChecker checker = new TransferOutableChecker();
		ArrayList list = uam.getTypeAccounts(checker);
		for (Object o : list) {
			comboBox.addItem(o);
		}

		JButton btnNext = new JButton("Next");
		btnNext.setBounds(245, 168, 117, 29);
		contentPane.add(btnNext);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transMap.put("fromAccount", comboBox.getSelectedItem());
				UserTransactionRegular.this.dispose();
				new UserTransactionRegularNext(transMap, uam, tm, machine, id, infoManager).setVisible(true);
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserTransactionRegular.this.dispose();
				new UserMakeTransaction(id, infoManager).setVisible(true);
			}
		});
		btnBack.setBounds(26, 168, 117, 29);
		contentPane.add(btnBack);
	}

}
