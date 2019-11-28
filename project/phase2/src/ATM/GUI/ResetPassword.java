package ATM.GUI;

import ATM.BankIdentities.PasswordManager;
import ATM.BankIdentities.User;
import ATM.GUI.Manager.ManagerMainMenu;
import ATM.GUI.Staff.StaffMainMenu;
import ATM.GUI.User.UserMainMenu;
import ATM.InfoHandling.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;

public class ResetPassword extends JFrame {

	private JPanel contentPane;
	private JTextField txtReset;

	public void identityLog(String id, InfoStorer infoStorer, InfoManager infoManager) {

		if (infoStorer.getBankManagerMap().containsKey(id)) {
			ResetPassword.this.dispose();
			new ManagerMainMenu(id, infoManager).setVisible(true);

		} else if (infoStorer.getUserMap().containsKey(id)) {
			ResetPassword.this.dispose();
			new UserMainMenu(id, infoManager).setVisible(true);

		} else if (infoStorer.getStaffMap().containsKey(id)) {
			ResetPassword.this.dispose();
			new StaffMainMenu(id, infoManager).setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "No such account");
		}
	}

	/**
	 * Create the frame.
	 */
	public ResetPassword(String id, InfoManager infoManager) {
		InfoStorer infoStorer = infoManager.getInfoStorer();
		PasswordManager passwordManager = new PasswordManager(id);



		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblResetPassword = new JLabel("Reset Password:");
		lblResetPassword.setBounds(48, 91, 133, 16);
		contentPane.add(lblResetPassword);
		
		txtReset = new JTextField();
		txtReset.addKeyListener(new KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				try {
					long number = Long.parseLong(txtReset.getText());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(rootPane, "Only Numbers Allowed");
					txtReset.setText("");
				}
			}

		});
		txtReset.setBounds(159, 86, 130, 26);
		contentPane.add(txtReset);
		txtReset.setColumns(10);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordManager.setPassword(txtReset.getText(), infoManager.getPasswordMap());
				JOptionPane.showMessageDialog(null, "Reset Successful!");
			}
		});
		btnReset.setBounds(298, 86, 117, 29);
		contentPane.add(btnReset);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				identityLog(id, infoStorer, infoManager);
			}
		});
		btnBack.setBounds(159, 197, 117, 29);
		contentPane.add(btnBack);
	}

}
