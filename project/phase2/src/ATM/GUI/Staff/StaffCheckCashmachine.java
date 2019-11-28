package ATM.GUI.Staff;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ATM.InfoHandling.InfoManager;
import ATM.InfoHandling.InfoStorer;
import ATM.Machine.CashMachine;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class StaffCheckCashmachine extends JFrame {

    private JPanel contentPane;


    /**
     * Create the frame.
     */
    public StaffCheckCashmachine(String id, InfoManager infoManager) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTwentyR = new JLabel("");
        lblTwentyR.setBounds(89, 132, 61, 16);
        contentPane.add(lblTwentyR);

        JLabel lblFiftyR = new JLabel("");
        lblFiftyR.setBounds(64, 176, 61, 16);
        contentPane.add(lblFiftyR);

        JLabel lblFifty = new JLabel("Fifty:");
        lblFifty.setBounds(16, 176, 61, 16);
        contentPane.add(lblFifty);

        JLabel lblTwenty = new JLabel("Twenty:");
        lblTwenty.setBounds(16, 132, 61, 16);
        contentPane.add(lblTwenty);

        JLabel lblTen = new JLabel("Ten:");
        lblTen.setBounds(16, 80, 61, 16);
        contentPane.add(lblTen);

        JLabel lblTenR = new JLabel("");
        lblTenR.setBounds(64, 80, 61, 16);
        contentPane.add(lblTenR);

        JLabel lblFive = new JLabel("Five:");
        lblFive.setBounds(16, 28, 61, 16);
        contentPane.add(lblFive);

        JLabel lblFiveR = new JLabel("");
        lblFiveR.setBounds(64, 28, 61, 16);
        contentPane.add(lblFiveR);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StaffCheckCashmachine.this.dispose();
                new StaffMainMenu(id, infoManager).setVisible(true);
            }
        });
        btnBack.setBounds(315, 216, 117, 29);
        contentPane.add(btnBack);

        CashMachine machine = infoManager.getCashMachine();
        String five = String.valueOf(machine.getNumFiveD());
        String ten = String.valueOf(machine.getNumTenD());
        String twenty = String.valueOf(machine.getNumTwentyD());
        String fifty = String.valueOf(machine.getNumFiftyD());
        lblFiveR.setText(five);
        lblTenR.setText(ten);
        lblFiftyR.setText(fifty);
        lblTwentyR.setText(twenty);
    }

}
