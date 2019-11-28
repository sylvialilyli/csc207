package ATM.GUI.Manager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import ATM.BankIdentities.*;
import ATM.InfoHandling.*;
import ATM.Accounts.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerJointAccount extends JFrame {

    private JPanel contentPane;
    private JTextField txtID;
    private JTextField txtAccount;
    private AccountOwnable user;
    /**
     * Create the frame.
     */
    public ManagerJointAccount(String id, InfoManager infoManager) {
        BankManager bankManager = infoManager.getBankManager(id);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblEnterUserId = new JLabel("Enter User id:");
        lblEnterUserId.setBounds(28, 36, 89, 16);
        contentPane.add(lblEnterUserId);

        txtID = new JTextField();
        txtID.setBounds(129, 31, 130, 26);
        contentPane.add(txtID);
        txtID.setColumns(10);

        JLabel lblEnterAccountNumber = new JLabel("Enter Account Number:");
        lblEnterAccountNumber.setBounds(28, 76, 170, 16);
        contentPane.add(lblEnterAccountNumber);

        txtAccount = new JTextField();
        txtAccount.setBounds(184, 71, 130, 26);
        contentPane.add(txtAccount);
        txtAccount.setColumns(10);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountnum = txtAccount.getText();
                String ID = txtID.getText();
                Account account = infoManager.getAccountMap().get(accountnum);
                if (infoManager.getStaffMap().get(ID)!=null){
                     user = infoManager.getStaffMap().get(ID);
                } else {
                     user = infoManager.getUserMap().get(ID);
                }
                try {
                  bankManager.ShareAccount(user, account);
                    JOptionPane.showMessageDialog(null, "Joint account created");
                }catch (UserNotOwnAccountException exception){
                    JOptionPane.showMessageDialog(null, "This user do not own this account.");

                }
            }
        });
        btnAdd.setBounds(271, 149, 117, 29);
        contentPane.add(btnAdd);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ManagerJointAccount.this.dispose();
                new ManagerMainMenu(id, infoManager).setVisible(true);
            }
        });
        btnBack.setBounds(28, 149, 117, 29);
        contentPane.add(btnBack);
    }

}
