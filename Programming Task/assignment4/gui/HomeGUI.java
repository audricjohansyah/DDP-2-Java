package assignment4.gui;

import assignment3.nota.NotaManager;
import assignment4.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static assignment3.nota.NotaManager.toNextDay;
import java.util.Calendar;
import java.text.SimpleDateFormat;
public class HomeGUI extends JPanel {
    public static final String KEY = "HOME";
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton toNextDayButton;
    private final SimpleDateFormat fmt;
    private final Calendar cal; 

    public HomeGUI(){
        super(new BorderLayout()); // Setup layout
        this.fmt = NotaManager.fmt; // set up tanggal
        this.cal = NotaManager.cal; // set up tanggal
        mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 700, 432);
        mainPanel.setLayout(null);
        initGUI();
        add(mainPanel);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // membuat label title
        titleLabel = new JLabel("Selamat datang di CuciCuci System!");
        titleLabel.setFont(new Font("Papyrus", Font.BOLD, 30));
        titleLabel.setBounds(90, 20, 2000, 50);
        titleLabel.setForeground(Color.RED);

        // membuat login button
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Courier", Font.BOLD, 16));
        loginButton.setBounds(280, 90, 120, 40);
        loginButton.addActionListener(new ActionListener() { // implementasi fungsi button

            @Override
            public void actionPerformed(ActionEvent e) {
                handleToLogin();
            }

        });

        // membuat register button 
        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Courier", Font.BOLD, 16));
        registerButton.setBounds(280, 170, 120, 40);
        registerButton.addActionListener(new ActionListener() { // implementasi register button

            @Override
            public void actionPerformed(ActionEvent e) {
               handleToRegister();
            }
            
        });

        // membuat next day button
        toNextDayButton = new JButton("Next Day");
        toNextDayButton.setFont(new Font("Courier", Font.BOLD, 16));
        toNextDayButton.setBounds(280, 250, 120, 40);
        toNextDayButton.addActionListener(new ActionListener() { // implementasi nextday button

            @Override
            public void actionPerformed(ActionEvent e) {
                handleNextDay();
                JOptionPane.showMessageDialog(mainPanel,"Kamu tidur hari ini... zzz...", "Next day Zzz...", JOptionPane.INFORMATION_MESSAGE);
            }
            
        });

        // membuat label tanggal
        dateLabel = new JLabel("Hari ini   :   " + fmt.format(cal.getTime()));
        dateLabel.setFont(new Font("Courier New", Font.BOLD, 15));
        dateLabel.setBounds(230, 330, 1000, 30);

        // menambahkan semua ke mainpanel
        mainPanel.add(titleLabel);
        mainPanel.add(loginButton);
        mainPanel.add(registerButton);
        mainPanel.add(toNextDayButton);
        mainPanel.add(dateLabel);
    }

    /**
     * Method untuk pergi ke halaman register.
     * Akan dipanggil jika pengguna menekan "registerButton"
     * */
    private static void handleToRegister() {
        MainFrame.getInstance().navigateTo(RegisterGUI.KEY); // pindah ke panel register
    }

    /**
     * Method untuk pergi ke halaman login.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private static void handleToLogin() {
        MainFrame.getInstance().navigateTo(LoginGUI.KEY); // pindah ke panel login
    }

    /**
     * Method untuk skip hari.
     * Akan dipanggil jika pengguna menekan "toNextDayButton"
     * */
    private void handleNextDay() {
        toNextDay();
        dateLabel.setText("Hari ini   :   " + fmt.format(cal.getTime())); //update label tanggal
    }
}
