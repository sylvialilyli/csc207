package ATM.GUI.User;

import ATM.AccountTypeChecker.PayableChecker;
import ATM.AccountTypeChecker.TypeChecker;
import ATM.AccountTypeChecker.WithdrawableChecker;
import ATM.Accounts.Currency;
import ATM.BankIdentities.UserAccManager;
import ATM.InfoHandling.InfoManager;
import ATM.Machine.CashMachine;
import ATM.Transactions.Transaction;
import ATM.Transactions.TransactionManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UserTransactionWithdrawal extends JFrame {

	private JPanel contentPane;
	private JTextField txtAmount;
	private JTextField txtID;



	/**
	 * Create the frame.
	 */
	public UserTransactionWithdrawal(Map<String, Object> transMap, UserAccManager uam, TransactionManager tm,
									 CashMachine machine, String id, InfoManager infoManager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFromAccount = new JLabel("From Account:");
		lblFromAccount.setBounds(6, 80, 110, 16);
		contentPane.add(lblFromAccount);



		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(110, 78, 110, 27);
		contentPane.add(comboBox);
		TypeChecker checker = new WithdrawableChecker();
		ArrayList list = uam.getTypeAccounts(checker);
		for (Object o : list) {
			comboBox.addItem(o);
		}
		
		txtAmount = new JTextField();
		txtAmount.addKeyListener(new KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				try {
					long number = Long.parseLong(txtAmount.getText());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(rootPane, "Only Numbers Allowed");
					txtAmount.setText("");
				}
			}

		});
		txtAmount.setColumns(10);
		txtAmount.setBounds(90, 134, 130, 26);
		contentPane.add(txtAmount);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(28, 139, 60, 16);
		contentPane.add(lblAmount);


		JLabel lblWithdrawal = new JLabel("Withdrawal");
		lblWithdrawal.setBounds(159, 22, 89, 16);
		contentPane.add(lblWithdrawal);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserTransactionWithdrawal.this.dispose();
				new UserMakeTransaction(id, infoManager).setVisible(true);
			}
		});
		btnBack.setBounds(19, 219, 117, 29);
		contentPane.add(btnBack);

		JButton btnTransfer = new JButton("Withdraw");
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					transMap.put("fromAccount", comboBox.getSelectedItem());
					Currency amount = new Currency(Double.valueOf(txtAmount.getText()));
					transMap.put("amount", amount);
					Transaction trans = tm.makeTransaction(transMap, machine);
					if (trans.isHappened()) {
						JOptionPane.showMessageDialog(null, "Withdraw successful!");
					}
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(rootPane, "Transaction is not possible.");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(rootPane, e1.getMessage());
				}
			}
		});

		btnTransfer.setBounds(131, 216, 117, 29);
		contentPane.add(btnTransfer);
	}

}
