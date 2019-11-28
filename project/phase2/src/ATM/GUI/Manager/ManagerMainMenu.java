package ATM.GUI.Manager;

import ATM.BankIdentities.*;
import ATM.GUI.IDMenu;
import ATM.GUI.ResetPassword;
import ATM.GUI.StartMenu;
import ATM.InfoHandling.InfoManager;

import javax.sound.sampled.Line;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerMainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public ManagerMainMenu(String id, InfoManager infoManager) {
		PasswordManager passwordManager = new PasswordManager(id);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Welcome Manager!");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(6, 6, 440, 16);
		contentPane.add(label);

		btnCreateUser(id, infoManager);
		btnUndoTransaction(id, infoManager);
		btnUndoAccountTransaction(id, infoManager);
		btnCreateAccount(id, infoManager);
		btnRestock(id, infoManager);
		btnLogout(id, passwordManager, infoManager);
		btnCreatestaff(id, infoManager);
		btnCheckMachineBalance(id, infoManager);
		btnAddUserTo(id, infoManager);
		btnResetPassword(id, infoManager);

	}

	public void btnResetPassword(String id, InfoManager infoManager){
		JButton btnResetPassword = new JButton("Reset Password");
		btnResetPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerMainMenu.this.dispose();
				new ResetPassword(id, infoManager).setVisible(true);
			}
		});
		btnResetPassword.setBounds(321, 204, 125, 29);
		contentPane.add(btnResetPassword);
	}

	public void btnAddUserTo(String id, InfoManager infoManager){
		JButton btnAddUserTo = new JButton("Add user to account");
		btnAddUserTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerMainMenu.this.dispose();
				new ManagerJointAccount(id, infoManager).setVisible(true);
			}
		});
		btnAddUserTo.setBounds(145, 204, 164, 29);
		contentPane.add(btnAddUserTo);
	}

	public void btnCheckMachineBalance(String id, InfoManager infoManager){
		JButton btnCheckMachineBalance = new JButton("Check Machine Balance");
		btnCheckMachineBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerMainMenu.this.dispose();
				new ManagerCheckMachineBalance(id, infoManager).setVisible(true);
			}
		});
		btnCheckMachineBalance.setBounds(145, 148, 180, 29);
		contentPane.add(btnCheckMachineBalance);
	}

	public void btnCreatestaff(String id, InfoManager infoManager){
		JButton btnCreatestaff = new JButton("Create Staff");
		btnCreatestaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerMainMenu.this.dispose();
				new ManagerCreateStaff(id, infoManager).setVisible(true);
			}
		});
		btnCreatestaff.setBounds(16, 94, 117, 29);
		contentPane.add(btnCreatestaff);
	}

	public void btnLogout(String id, PasswordManager passwordManager, InfoManager infoManager){
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordManager.logout();
				ManagerMainMenu.this.dispose();
				infoManager.saveToFile();
				new IDMenu(infoManager).setVisible(true);
			}
		});
		btnLogout.setBounds(321, 243, 125, 29);
		contentPane.add(btnLogout);
	}

	public void btnRestock(String id, InfoManager infoManager){
		JButton btnRestock = new JButton("Restock");
		btnRestock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerMainMenu.this.dispose();
				new ManagerRestock(id, infoManager).setVisible(true);
			}
		});
		btnRestock.setBounds(16, 204, 125, 29);
		contentPane.add(btnRestock);
	}

	public void btnCreateAccount(String id, InfoManager infoManager){
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerMainMenu.this.dispose();
				new ManagerCreateAccount(id, infoManager).setVisible(true);
			}
		});
		btnCreateAccount.setBounds(16, 148, 125, 29);
		contentPane.add(btnCreateAccount);
	}

	public void btnUndoAccountTransaction(String id, InfoManager infoManager){
		JButton btnUndoAccountTransaction = new JButton("Undo Account Transaction");
		btnUndoAccountTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerMainMenu.this.dispose();
				new ManagerUndoAccountTrans(id, infoManager).setVisible(true);
			}
		});
		btnUndoAccountTransaction.setBounds(145, 94, 198, 29);
		contentPane.add(btnUndoAccountTransaction);
	}

	public void btnUndoTransaction(String id, InfoManager infoManager){
		JButton btnUndoTransaction = new JButton("Undo User Transaction");
		btnUndoTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerMainMenu.this.dispose();
				new ManagerUndoUserTrans(id, infoManager).setVisible(true);
			}
		});
		btnUndoTransaction.setBounds(145, 34, 187, 29);
		contentPane.add(btnUndoTransaction);
	}

	public void btnCreateUser(String id, InfoManager infoManager){
		JButton btnCreateUser = new JButton("Create User");
		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerMainMenu.this.dispose();
				new ManagerCreaterUser(id, infoManager).setVisible(true);
			}
		});
		btnCreateUser.setBounds(16, 34, 117, 29);
		contentPane.add(btnCreateUser);
	}
}
