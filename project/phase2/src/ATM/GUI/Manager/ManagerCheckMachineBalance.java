package ATM.GUI.Manager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import ATM.InfoHandling.InfoManager;
import ATM.InfoHandling.InfoStorer;
import ATM.Machine.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerCheckMachineBalance extends JFrame {

	private JPanel contentPane;
	private JTextField txtFive;
	private JTextField txtTen;
	private JTextField txtTwenty;
	private JTextField txtFifty;
	private JButton btnBack;


	/**
	 * Create the frame.
	 */
	public ManagerCheckMachineBalance(String id, InfoManager infoManager) {


		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFive = new JLabel("Five:");
		lblFive.setBounds(28, 43, 61, 16);
		contentPane.add(lblFive);
		
		JLabel lblTen = new JLabel("Ten:");
		lblTen.setBounds(28, 95, 61, 16);
		contentPane.add(lblTen);
		
		JLabel lblTwenty = new JLabel("Twenty:");
		lblTwenty.setBounds(28, 147, 61, 16);
		contentPane.add(lblTwenty);
		
		JLabel lblFifty = new JLabel("Fifty:");
		lblFifty.setBounds(28, 191, 61, 16);
		contentPane.add(lblFifty);
		
		txtFive = new JTextField("0");
		txtFive.setEditable(false);
		txtFive.setBounds(65, 38, 130, 26);
		contentPane.add(txtFive);
		txtFive.setColumns(10);
		
		txtTen = new JTextField("0");
		txtTen.setEditable(false);
		txtTen.setColumns(10);
		txtTen.setBounds(65, 90, 130, 26);
		contentPane.add(txtTen);
		
		txtTwenty = new JTextField("0");
		txtTwenty.setEditable(false);
		txtTwenty.setColumns(10);
		txtTwenty.setBounds(89, 142, 130, 26);
		contentPane.add(txtTwenty);
		
		txtFifty = new JTextField("0");
		txtFifty.setEditable(false);
		txtFifty.setColumns(10);
		txtFifty.setBounds(65, 186, 130, 26);
		contentPane.add(txtFifty);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerCheckMachineBalance.this.dispose();
				new ManagerMainMenu(id, infoManager).setVisible(true);
			}
		});
		btnBack.setBounds(327, 231, 117, 29);
		contentPane.add(btnBack);

		CashMachine machine = infoManager.getCashMachine();
		String five = String.valueOf(machine.getNumFiveD());
		String ten = String.valueOf(machine.getNumTenD());
		String twenty = String.valueOf(machine.getNumTwentyD());
		String fifty = String.valueOf(machine.getNumFiftyD());
		txtFive.setText(five);
		txtTen.setText(ten);
		txtFifty.setText(fifty);
		txtTwenty.setText(twenty);
	}

}
