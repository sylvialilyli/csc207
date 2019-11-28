package ATM.GUI.User;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import ATM.InfoHandling.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class UserRequest extends JFrame {

	private JPanel contentPane;
	private JTextField txtRequest;

	/**
	 * Create the frame.
	 */
	public UserRequest(String id, InfoManager infoManager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserRequest.this.dispose();
				new UserMainMenu(id, infoManager).setVisible(true);

			}
		});
		btnBack.setBounds(130, 99, 117, 29);
		contentPane.add(btnBack);

		txtRequest = new JTextField();
		txtRequest.setBounds(50, 61, 130, 26);
		contentPane.add(txtRequest);
		txtRequest.setColumns(10);

		JButton btnRequest = new JButton("Request");
		btnRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtRequest.getText()=="") {
					JOptionPane.showMessageDialog(null, "Empty input.");
				} else {
					JOptionPane.showMessageDialog(null, "Successful request");

				}
			}
		});
		btnRequest.setBounds(192, 61, 117, 29);
		contentPane.add(btnRequest);
	}

}
