package ATM.GUI.Manager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import ATM.BankIdentities.BankManager;
import ATM.InfoHandling.InfoManager;
import ATM.InfoHandling.InfoStorer;
import ATM.Machine.Money;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;

public class ManagerRestock extends JFrame {

	private JPanel contentPane;
	private JTextField txtFive;
	private JTextField txtTen;
	private JTextField txtTwenty;
	private JTextField txtFifty;


	/**
	 * Create the frame.
	 */
	public ManagerRestock(String id, InfoManager infoManager) {
		InfoStorer infoStorer = infoManager.getInfoStorer();
		BankManager bankManager = infoStorer.getBankManagerMap().get(id);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRestock = new JLabel("Restock");
		lblRestock.setBounds(174, 26, 61, 16);
		contentPane.add(lblRestock);
		
		JLabel lblFive = new JLabel("Number of Five dollars:");
		lblFive.setBounds(42, 70, 171, 16);
		contentPane.add(lblFive);
		
		JLabel lblTen = new JLabel("Number of Ten dollars:");
		lblTen.setBounds(42, 112, 171, 16);
		contentPane.add(lblTen);
		
		JLabel lblTwenty = new JLabel("Number of Twenty dollars:");
		lblTwenty.setBounds(42, 158, 171, 16);
		contentPane.add(lblTwenty);
		
		JLabel lblFifty = new JLabel("Number of Fifty dollars:");
		lblFifty.setBounds(42, 206, 171, 16);
		contentPane.add(lblFifty);
		
		txtFive = new JTextField();
		txtFive.addKeyListener(new KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
	            try {
	                long number = Long.parseLong(txtFive.getText());
	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(rootPane, "Only Numbers Allowed");
	                txtFive.setText("");
	            }
	        }
	
		});
		txtFive.setBounds(201, 65, 130, 26);
		contentPane.add(txtFive);
		txtFive.setColumns(10);
		
		txtTen = new JTextField();
		txtTen.addKeyListener(new KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
	            try {
	                long number = Long.parseLong(txtTen.getText());
	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(rootPane, "Only Numbers Allowed");
	                txtTen.setText("");
	            }
	        }
	
		});
		txtTen.setColumns(10);
		txtTen.setBounds(201, 107, 130, 26);
		contentPane.add(txtTen);
		
		txtTwenty = new JTextField();
		txtTwenty.addKeyListener(new KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
	            try {
	                long number = Long.parseLong(txtTwenty.getText());
	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(rootPane, "Only Numbers Allowed");
	                txtTwenty.setText("");
	            }
	        }
	
		});
		txtTwenty.setColumns(10);
		txtTwenty.setBounds(225, 153, 130, 26);
		contentPane.add(txtTwenty);
		
		txtFifty = new JTextField();
		txtFifty.addKeyListener(new KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
	            try {
	                long number = Long.parseLong(txtFifty.getText());
	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(rootPane, "Only Numbers Allowed");
	                txtFifty.setText("");
	            }
	        }
	
		});
		txtFifty.setColumns(10);
		txtFifty.setBounds(201, 201, 130, 26);
		contentPane.add(txtFifty);
		
		JButton btnRestock = new JButton("Restock");
		btnRestock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String five = txtFive.getText();
				String ten = txtTen.getText();
				String twenty = txtTwenty.getText();
				String fifty = txtFifty.getText();
				int numFive = Integer.valueOf(five);
				int numTen = Integer.valueOf(ten);
				int numTwenty = Integer.valueOf(twenty);
				int numFifty = Integer.valueOf(fifty);
				if (five!=null && ten != null && twenty != null && fifty!= null ) {
					Money m = new Money(numFive, numTen, numTwenty, numFifty);
					bankManager.restock(infoManager.getCashMachine(), m);
					JOptionPane.showMessageDialog(null, "Restock Successful");
					txtFive.setText("");
					txtTen.setText("");
					txtTwenty.setText("");
					txtFifty.setText("");
				} else{
					JOptionPane.showMessageDialog(null, "Invalid Input");
				}
			}
		});
		btnRestock.setBounds(327, 239, 117, 29);
		contentPane.add(btnRestock);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerRestock.this.dispose();
				new ManagerMainMenu(id, infoManager).setVisible(true);
			}
		});
		btnBack.setBounds(6, 239, 117, 29);
		contentPane.add(btnBack);
	}

}
