package ATM.GUI.User;

import ATM.BankIdentities.UserAccManager;
import ATM.InfoHandling.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserNetTotal extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public UserNetTotal(String id, InfoManager infoManager) {
		InfoStorer infoStorer = infoManager.getInfoStorer();
		UserAccManager manager = new UserAccManager(id, infoStorer.getUserMap(), infoStorer.getStaffMap());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNetTotal = new JLabel("");
		lblNetTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblNetTotal.setBounds(25, 63, 382, 16);
		contentPane.add(lblNetTotal);
		
		JLabel lblTitle = new JLabel("Net Total:");
		lblTitle.setBounds(182, 6, 83, 16);
		contentPane.add(lblTitle);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserNetTotal.this.dispose();
				new UserMainMenu(id, infoManager).setVisible(true);

			}
		});
		btnBack.setBounds(163, 212, 117, 29);
		contentPane.add(btnBack);
		
		lblNetTotal.setText(String.valueOf(manager.getNetTotal()));
	}

}
