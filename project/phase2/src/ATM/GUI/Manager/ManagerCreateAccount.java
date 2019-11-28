package ATM.GUI.Manager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ATM.BankIdentities.AccountCreator;
import ATM.BankIdentities.UserAccManager;
import ATM.InfoHandling.InfoManager;
import ATM.InfoHandling.InfoStorer;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerCreateAccount extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public ManagerCreateAccount(String id, InfoManager infoManager) {
        InfoStorer infoStorer = infoManager.getInfoStorer();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCreateChequingAccount = new JButton("Create Chequing Account");
		btnCreateChequingAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerCreateAccount.this.dispose();
				new ManagerCreateChequingAccount(id, infoManager).setVisible(true);
			}
		});
		btnCreateChequingAccount.setBounds(19, 32, 192, 29);
		contentPane.add(btnCreateChequingAccount);
		
		JButton btnCreateSavingAccount = new JButton("Create Saving Account");
		btnCreateSavingAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerCreateAccount.this.dispose();
				new ManagerCreateSavingAccount(id, infoManager).setVisible(true);
			}
		});
		btnCreateSavingAccount.setBounds(223, 32, 192, 29);
		contentPane.add(btnCreateSavingAccount);
		
		JButton btnCreateCreditAccount = new JButton("Create Credit Account");
		btnCreateCreditAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerCreateAccount.this.dispose();
				new ManagerCreateCreditAccount(id, infoManager).setVisible(true);
			}
		});
		btnCreateCreditAccount.setBounds(19, 90, 192, 29);
		contentPane.add(btnCreateCreditAccount);
		
		JButton btnCreate = new JButton("Create Line Of Credit Account");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerCreateAccount.this.dispose();
				new ManagerCreateLineOfCreditAccount(id, infoManager).setVisible(true);
			}
		});
		btnCreate.setBounds(223, 90, 211, 29);
		contentPane.add(btnCreate);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerCreateAccount.this.dispose();
				new ManagerMainMenu(id, infoManager).setVisible(true);
			}
		});
		btnBack.setBounds(157, 217, 117, 29);
		contentPane.add(btnBack);
		
		JButton btnCreateGicAccount = new JButton("Create GIC Account");
		btnCreateGicAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerCreateAccount.this.dispose();
				new ManagerCreateGICAccount(id, infoManager).setVisible(true);
			}
		});
		btnCreateGicAccount.setBounds(19, 146, 192, 29);
		contentPane.add(btnCreateGicAccount);
	}

}
