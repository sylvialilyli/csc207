package ATM.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import ATM.AccountTypeChecker.*;
import ATM.Accounts.*;
import ATM.BankIdentities.*;
import ATM.InfoHandling.InfoManager;
import ATM.InfoHandling.InfoStorer;
import ATM.Machine.Money;
import ATM.BankSystem.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;

public class IDMenu extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private String ID;

	public void identityLog(String id, InfoStorer infoStorer, InfoManager infoManager) {

        if (infoStorer.getBankManagerMap().containsKey(id)) {
        	IDMenu.this.dispose();
        	new PasswordMenu(id, infoManager).setVisible(true);
        	
        } else if (infoStorer.getUserMap().containsKey(id)) {
        	IDMenu.this.dispose();
        	new PasswordMenu(id, infoManager).setVisible(true);
        	
        } else if (infoStorer.getStaffMap().containsKey(id)) {
        	IDMenu.this.dispose();
        	new PasswordMenu(id, infoManager).setVisible(true);
        } else {
			JOptionPane.showMessageDialog(null, "ID not found! Please enter again.");
        }
    }

	/**
	 * Create the frame.
	 */
	public IDMenu(InfoManager infoManager) {
		InfoStorer infoStorer = infoManager.getInfoStorer();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterID = new JLabel("Please enter your ID");
		lblEnterID.setBounds(5, 5, 440, 16);
		lblEnterID.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblEnterID);
		
		JLabel lblID = new JLabel("ID:");
		lblID.setBounds(117, 79, 61, 16);
		contentPane.add(lblID);
		
		txtID = new JTextField();
		txtID.addKeyListener(new KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
	            try {
	                long number = Long.parseLong(txtID.getText());
	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(rootPane, "Only Numbers Allowed");
	                txtID.setText("");
	            }
	        }
	
		});
		txtID.setBounds(145, 74, 130, 26);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if (infoManager.getBankManagerNum() == 0) {
		            BankManager defaultManager = new BankManager(infoManager.getBankManagerNum(),infoStorer.getBankManagerMap(), "1234", infoStorer.getPasswordMap());
		            infoManager.add("5101", defaultManager);
		        }
		        ID = txtID.getText();
		        identityLog(ID, infoStorer, infoManager);
			}
		});
		btnNext.setBounds(287, 74, 117, 29);
		contentPane.add(btnNext);
	}

}
