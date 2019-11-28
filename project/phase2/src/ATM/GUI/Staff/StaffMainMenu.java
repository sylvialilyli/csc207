package ATM.GUI.Staff;

import ATM.GUI.IDMenu;
import ATM.GUI.Manager.*;
import ATM.GUI.PasswordMenu;
import ATM.GUI.StartMenu;
import ATM.GUI.User.UserMainMenu;
import ATM.InfoHandling.InfoManager;
import ATM.BankIdentities.*;

import javax.sound.sampled.Line;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StaffMainMenu extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public StaffMainMenu(String id, InfoManager infoManager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		createUser(id, infoManager);
		restock(id, infoManager);
		accessUserOptions(id, infoManager);
		logout(id, infoManager);
		checkMachineBalance(id, infoManager);
	}
	private void logout(String id, InfoManager infoManager){
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PasswordManager passwordManager =  new PasswordManager(id);
				passwordManager.logout();
				StaffMainMenu.this.dispose();
				infoManager.saveToFile();
				new IDMenu(infoManager).setVisible(true);
			}
		});
		btnLogout.setBounds(28, 146, 156, 29);
		contentPane.add(btnLogout);
	}

	private void accessUserOptions(String id, InfoManager infoManager){
		JButton btnAccessUserOption = new JButton("Access User Options");
		btnAccessUserOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffMainMenu.this.dispose();
				new UserMainMenu(id, infoManager).setVisible(true);
			}
		});
		btnAccessUserOption.setBounds(197, 25, 156, 29);
		contentPane.add(btnAccessUserOption);
	}

	private void createUser(String id, InfoManager infoManager){
		JButton btnCreateUser = new JButton("Create User");
		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffMainMenu.this.dispose();
				new StaffCreateUser(id, infoManager).setVisible(true);
			}
		});
		btnCreateUser.setBounds(28, 25, 117, 29);
		contentPane.add(btnCreateUser);
	}

	private void restock(String id, InfoManager infoManager){
		JButton btnRestock = new JButton("Restock");
		btnRestock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffMainMenu.this.dispose();
				new StaffRestock(id, infoManager).setVisible(true);
			}
		});
		btnRestock.setBounds(197, 146, 140, 29);
		contentPane.add(btnRestock);
	}

	private void checkMachineBalance(String id, InfoManager infoManager){
		JButton btnCheckMachineBalance = new JButton("Check Machine Balance");
		btnCheckMachineBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffMainMenu.this.dispose();
				new StaffCheckCashmachine(id, infoManager).setVisible(true);
			}
		});
		btnCheckMachineBalance.setBounds(197, 87, 200, 29);
		contentPane.add(btnCheckMachineBalance);
	}
}
