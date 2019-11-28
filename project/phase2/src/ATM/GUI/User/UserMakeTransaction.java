package ATM.GUI.User;

import ATM.Accounts.Account;
import ATM.BankIdentities.User;
import ATM.BankIdentities.UserAccManager;
import ATM.InfoHandling.*;
import ATM.Machine.CashMachine;
import ATM.Transactions.TransactionManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class UserMakeTransaction extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public UserMakeTransaction(String id, InfoManager infoManager) {
		InfoStorer infoStorer = infoManager.getInfoStorer();
		UserAccManager uam = new UserAccManager(id, infoStorer.getUserMap(), infoStorer.getStaffMap());
		TransactionManager tm = new TransactionManager(infoManager.getAccTransMap(), infoManager.getUserTransMap());
		User user = infoManager.getUser(id);
		Map<String, Account> accountMap = infoStorer.getAccountMap();
		Map<String, Object> transMap = new HashMap<>();
		CashMachine machine = infoManager.getCashMachine();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTrans = new JLabel("Transaction Menu:");
		lblTrans.setBounds(145, 6, 170, 16);
		contentPane.add(lblTrans);
		
		JButton btnRegularTransaction = new JButton("Regular Transaction");
		btnRegularTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transMap.put("Type", "Regular");
				UserMakeTransaction.this.dispose();
				new UserTransactionRegular(transMap, uam, tm, machine, id, infoManager).setVisible(true);
			}
		});
		btnRegularTransaction.setBounds(28, 55, 157, 29);
		contentPane.add(btnRegularTransaction);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transMap.put("Type", "Deposit");
				UserMakeTransaction.this.dispose();
				new UserTransactionDeposit(transMap, id, tm, machine, infoManager).setVisible(true);
			}
		});
		btnDeposit.setBounds(246, 55, 144, 29);
		contentPane.add(btnDeposit);
		
		JButton btnWithdraw = new JButton("Withdrawal");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transMap.put("Type", "Withdrawal");
				UserMakeTransaction.this.dispose();
				new UserTransactionWithdrawal(transMap, uam, tm, machine, id, infoManager).setVisible(true);
			}
		});
		btnWithdraw.setBounds(10, 141, 144, 29);
		contentPane.add(btnWithdraw);
		
		JButton btnPayBill = new JButton("Pay Bill");
		btnPayBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transMap.put("Type", "PayBill");
				UserMakeTransaction.this.dispose();
				new UserTransactionPayBill(transMap, uam, tm, machine, id, infoManager).setVisible(true);
			}
		});
		btnPayBill.setBounds(246, 141, 144, 29);
		contentPane.add(btnPayBill);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMakeTransaction.this.dispose();
				new UserMainMenu(id, infoManager).setVisible(true);

			}
		});
		btnBack.setBounds(144, 222, 144, 29);
		contentPane.add(btnBack);
	}

}
