package assignment4.gui;

import assignment3.LoginManager;
import assignment3.user.menu.MemberSystem;
import assignment3.user.menu.SystemCLI;
import assignment4.MainFrame;
import assignment4.gui.member.employee.EmployeeSystemGUI;
import assignment4.gui.member.member.MemberSystemGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JPanel {
    public static final String KEY = "LOGIN";
    private JPanel mainPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private LoginManager loginManager;

    public LoginGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout
        this.loginManager = loginManager;

        // Set up main panel
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
        // membuat label id input
        idLabel = new JLabel("Masukkan ID Anda:");
        idLabel.setFont(new Font("Lucida Console",Font.PLAIN, 18));
        idLabel.setBounds(40, 50, 1000, 30);

        // membuat textfield id
        idTextField = new JTextField();
        idTextField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        idTextField.setBounds(40, 100, 600, 30 );

        // membuat label password input
        passwordLabel = new JLabel("Masukkan password Anda:");
        passwordLabel.setFont(new Font("Lucida Console",Font.PLAIN, 18));
        passwordLabel.setBounds(40, 170, 1000, 30);

        // membuat textfield password
        passwordField = new JPasswordField();
        passwordField.setFont(passwordField.getFont().deriveFont(20f));
        passwordField.setBounds(40, 210, 600, 30 );

        // membuat tombol login
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Lucida Console",Font.BOLD, 18));
        loginButton.setBounds(100, 300, 200, 40);
        loginButton.addActionListener(new ActionListener() { // implementasi fungsi button login

            @Override
            public void actionPerformed(ActionEvent e) {
               handleLogin();
            }
            
        });

        // membuat button back
        backButton = new JButton("Kembali");
        backButton.setFont(new Font("Lucida Console",Font.BOLD, 18));
        backButton.setBounds(370, 300, 200, 40);
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) { // implementasi fungsi button login
                handleBack();
            }
           
        });

        // menambahkan semua ke main panel
        mainPanel.add(idLabel);
        mainPanel.add(idTextField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);
        mainPanel.add(backButton);
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        idTextField.setText(""); passwordField.setText(""); // mengosongkan field
        MainFrame.getInstance().navigateTo(HomeGUI.KEY); // pindah ke panel home
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin() {
        String password = new String(passwordField.getPassword()); // mendapatkan password
        String id = idTextField.getText(); // mendapatkan id

        // validasi id dan password
        if(!id.isEmpty() && !id.isBlank() && !password.isEmpty() && !password.isBlank()){
            if (!MainFrame.getInstance().login(id, password)){
                idTextField.setText(""); passwordField.setText("");
                JOptionPane.showMessageDialog(null, "ID atau password yang diinput salah", "Invalid login!", JOptionPane.ERROR_MESSAGE);
            }
            else{
                idTextField.setText(""); passwordField.setText("");
                SystemCLI systemCLI = loginManager.getSystem(id); // mendapatkan system yang akan dituju
                if (systemCLI instanceof MemberSystem) {
                    MainFrame.getInstance().navigateTo(MemberSystemGUI.KEY);
                }
                else{
                    MainFrame.getInstance().navigateTo(EmployeeSystemGUI.KEY);   
                }
                
            }
        }
        else{
            idTextField.setText("");
            passwordField.setText("");
            JOptionPane.showMessageDialog(mainPanel,"Semua field wajib diisi!", "Invalid input!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
