package ATM.GUI;

import ATM.BankIdentities.PasswordManager;
import ATM.GUI.Manager.ManagerMainMenu;
import ATM.GUI.Staff.StaffMainMenu;
import ATM.GUI.User.UserMainMenu;
import ATM.InfoHandling.InfoManager;
import ATM.InfoHandling.InfoStorer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class PasswordMenu extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;

    


	public void identityLog(String id, InfoStorer infoStorer, InfoManager infoManager) {

        if (infoStorer.getBankManagerMap().containsKey(id)) {
        	PasswordMenu.this.dispose();
        	new ManagerMainMenu(id, infoManager).setVisible(true);
        	
        } else if (infoStorer.getUserMap().containsKey(id)) {
        	PasswordMenu.this.dispose();
        	new UserMainMenu(id, infoManager).setVisible(true);
        	
        } else if (infoStorer.getStaffMap().containsKey(id)) {
        	PasswordMenu.this.dispose();
        	new StaffMainMenu(id, infoManager).setVisible(true);
        }
    }


	/**
	 * Create the frame.
	 */
	public PasswordMenu(String id, InfoManager infoManager) {
		InfoStorer infoStorer = infoManager.getInfoStorer();
		PasswordManager passwordManager = new PasswordManager(id);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(79, 80, 73, 16);
		contentPane.add(lblPassword);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = passwordField.getText();
				passwordManager.login(password, infoStorer.getPasswordMap());
				if (passwordManager.isLogin()){
					identityLog(id, infoStorer, infoManager);
				}else {
					JOptionPane.showMessageDialog(null, "Password wrong! Please enter again.");
				
					
					
				}
			}
		});
		btnNext.setBounds(288, 75, 117, 29);
		contentPane.add(btnNext);
		
		JLabel lblEnterPassword = new JLabel("Please enter your password");
		lblEnterPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterPassword.setBounds(6, 27, 440, 16);
		contentPane.add(lblEnterPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(147, 75, 117, 26);
		contentPane.add(passwordField);
	}
}
