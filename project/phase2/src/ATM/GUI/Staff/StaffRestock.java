package ATM.GUI.Staff;

import ATM.BankIdentities.BankManager;
import ATM.BankIdentities.BankStaff;
import ATM.GUI.Manager.ManagerCheckMachineBalance;
import ATM.GUI.Manager.ManagerMainMenu;
import ATM.InfoHandling.InfoManager;
import ATM.InfoHandling.InfoStorer;
import ATM.Machine.Money;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class StaffRestock extends JFrame {

    private JPanel contentPane;
    private JTextField txtFive;
    private JTextField txtTen;
    private JTextField txtTwenty;
    private JTextField txtFifty;

    /**
     * Create the frame.
     */
    public StaffRestock(String id, InfoManager infoManager) {
        InfoStorer infoStorer = infoManager.getInfoStorer();
        BankStaff bankStaff = infoStorer.getStaffMap().get(id);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblRestock = new JLabel("Restock");
        lblRestock.setBounds(174, 6, 61, 16);
        contentPane.add(lblRestock);

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
        txtFive.setColumns(10);
        txtFive.setBounds(201, 45, 130, 26);
        contentPane.add(txtFive);

        JLabel lblFive = new JLabel("Number of Five dollars:");
        lblFive.setBounds(42, 50, 171, 16);
        contentPane.add(lblFive);

        JLabel lblTen = new JLabel("Number of Ten dollars:");
        lblTen.setBounds(42, 92, 171, 16);
        contentPane.add(lblTen);

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
        txtTen.setBounds(201, 87, 130, 26);
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
        txtTwenty.setBounds(225, 133, 130, 26);
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
        txtFifty.setBounds(201, 181, 130, 26);
        contentPane.add(txtFifty);

        JLabel lblFifty = new JLabel("Number of Fifty dollars:");
        lblFifty.setBounds(42, 186, 171, 16);
        contentPane.add(lblFifty);

        JLabel lblTwenty = new JLabel("Number of Twenty dollars:");
        lblTwenty.setBounds(42, 138, 171, 16);
        contentPane.add(lblTwenty);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StaffRestock.this.dispose();
                new StaffMainMenu(id, infoManager).setVisible(true);
            }
        });
        btnBack.setBounds(6, 219, 117, 29);
        contentPane.add(btnBack);

        JButton btnRestock = new JButton("Restock");
        btnRestock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String five = txtFive.getText();
                String ten = txtTen.getText();
                String twenty = txtTwenty.getText();
                String fifty = txtFifty.getText();
                int numFive;
                int numTen;
                int numTwenty;
                int numFifty;
                if (five == ""){
                    numFive = 0;
                } else{
                    numFive = Integer.valueOf(five);
                }
                if (ten == ""){
                    numTen = 0;
                } else {
                    numTen = Integer.valueOf(ten);
                }
                if (twenty == ""){
                    numTwenty = 0;
                } else {
                    numTwenty = Integer.valueOf(twenty);
                }
                if (ten == ""){
                    numFifty = 0;
                } else {
                    numFifty = Integer.valueOf(fifty);
                }
                if (five!=null && ten != null && twenty != null && fifty!= null ) {
                    Money m = new Money(numFive, numTen, numTwenty, numFifty);
                    bankStaff.restock(infoManager.getCashMachine(), m);
                    JOptionPane.showMessageDialog(null, "Restock Successful");
                    txtFive.setText("");
                    txtTen.setText("");
                    txtTwenty.setText("");
                    txtFifty.setText("");
                } else if (txtFifty.getText().isEmpty() & txtTen.getText().isEmpty() &
                        txtTwenty.getText().isEmpty() &txtTen.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Invalid Input");
                }
            }
        });
        btnRestock.setBounds(327, 219, 117, 29);
        contentPane.add(btnRestock);
    }

}
