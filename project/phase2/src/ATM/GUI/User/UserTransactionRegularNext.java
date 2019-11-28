package ATM.GUI.User;

import ATM.BankIdentities.UserAccManager;
import ATM.GUI.UserTransactionRegularNextMy;
import ATM.GUI.UserTransactionRegularNextOther;
import ATM.InfoHandling.InfoManager;
import ATM.Machine.CashMachine;
import ATM.Transactions.TransactionManager;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class UserTransactionRegularNext extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public UserTransactionRegularNext(Map<String, Object> transMap, UserAccManager uam, TransactionManager tm,
									  CashMachine machine, String id, InfoManager infoManager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnTransferToMy = new JButton("Transfer to my account");
		btnTransferToMy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserTransactionRegularNext.this.dispose();
				new UserTransactionRegularNextMy(transMap, uam, tm, machine, id, infoManager).setVisible(true);
			}
		});
		btnTransferToMy.setBounds(83, 39, 231, 29);
		contentPane.add(btnTransferToMy);
		
		JButton btnTransferToOther = new JButton("Transfer to other user account");
		btnTransferToOther.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserTransactionRegularNext.this.dispose();
				new UserTransactionRegularNextOther(transMap, uam, tm, machine, id, infoManager).setVisible(true);
			}
		});
		btnTransferToOther.setBounds(83, 112, 231, 29);
		contentPane.add(btnTransferToOther);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(83, 176, 231, 29);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserTransactionRegularNext.this.dispose();
				new UserTransactionRegular(transMap, uam, tm, machine, id, infoManager).setVisible(true);
			}
		});
	}

}
