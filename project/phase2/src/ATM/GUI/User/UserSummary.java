package ATM.GUI.User;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import ATM.InfoHandling.*;
import ATM.BankIdentities.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;

public class UserSummary extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public UserSummary(String id, InfoManager infoManager) {
		InfoStorer infoStorer = infoManager.getInfoStorer();
		UserAccManager manager = new UserAccManager(id, infoStorer.getUserMap(), infoStorer.getStaffMap());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserSummary.this.dispose();
				new UserMainMenu(id, infoManager).setVisible(true);
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(67, 6, 277, 200);
		contentPane.add(scrollPane);

		JTextArea txtSummary = new JTextArea();
		scrollPane.setViewportView(txtSummary);
		txtSummary.setWrapStyleWord(true);
		txtSummary.setEditable(false);
		txtSummary.setText(manager.getSummary());
		btnBack.setBounds(148, 229, 117, 29);
		contentPane.add(btnBack);

	}
}
