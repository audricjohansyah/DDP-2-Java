package assignment4.gui;

import assignment3.LoginManager;
import assignment3.user.Member;
import assignment4.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI extends JPanel {
    public static final String KEY = "REGISTER";
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private LoginManager loginManager;
    private JButton backButton;

    public RegisterGUI(LoginManager loginManager) {
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
        // membuat label input nama
        nameLabel = new JLabel("Masukkan nama Anda:");
        nameLabel.setFont(new Font("Monospaced",Font.BOLD, 16));
        nameLabel.setBounds(40, 30, 1000, 30);

        // membuat textfield nama
        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Lucida Console",Font.PLAIN, 14));
        nameTextField.setBounds(40, 60, 600, 30 );

        // membuat label input no hp
        phoneLabel = new JLabel("Masukkan nomor handphone Anda:");
        phoneLabel.setFont(new Font("Monospaced",Font.BOLD, 16));
        phoneLabel.setBounds(40, 110, 1000, 30);

        // membuat textfield no hp
        phoneTextField = new JTextField();
        phoneTextField.setFont(new Font("Lucida Console",Font.PLAIN, 14));
        phoneTextField.setBounds(40, 140, 600, 30);

        // membuat label input password
        passwordLabel = new JLabel("Masukkan password Anda:");
        passwordLabel.setFont(new Font("Monospaced",Font.BOLD, 16));
        passwordLabel.setBounds(40, 190, 1000, 30);

        // membuat textfield password
        passwordField = new JPasswordField();
        passwordField.setFont(passwordField.getFont().deriveFont(20f));
        passwordField.setBounds(40, 220, 600, 30);

        // membuat button register
        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Monospaced",Font.BOLD, 16));
        registerButton.setBounds(100, 300, 200, 40);
        registerButton.addActionListener(new ActionListener() { // implementasi fungsi button register

            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
            
        });

        // membuat button back
        backButton = new JButton("Back");
        backButton.setFont(new Font("Monospaced",Font.BOLD, 16));
        backButton.setBounds(350, 300, 200, 40);
        backButton.addActionListener(new ActionListener() { // implementasi fungsi button back

            @Override
            public void actionPerformed(ActionEvent e) {
                handleBack();
            }
            
        });

        // menambahkan semua ke main panel
        mainPanel.add(nameLabel);
        mainPanel.add(nameTextField);
        mainPanel.add(phoneLabel);
        mainPanel.add(phoneTextField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(registerButton);
        mainPanel.add(backButton);
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        nameTextField.setText(""); phoneTextField.setText(""); passwordField.setText(""); // mengosongkan field
        MainFrame.getInstance().navigateTo(HomeGUI.KEY); // pindah ke home panel
    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {
        // mendapatkan password, nama, dan no hp
        String password = new String(passwordField.getPassword());
        String nama = nameTextField.getText();
        String nomorHp = phoneTextField.getText();
        Member newMember = loginManager.register(nama, nomorHp, password); // membuat member

        // validasi input dan validasi member
        if(!nama.isBlank() && !nama.isEmpty() && !nomorHp.isBlank() && !nomorHp.isEmpty() && !password.isEmpty() && !password.isBlank()){
            if (!nomorHp.matches("\\d+")){
                nameTextField.setText(""); phoneTextField.setText(""); passwordField.setText("");
                JOptionPane.showMessageDialog(mainPanel,"Nomor handphone harus berupa angka yang valid!", "Invalid input!", JOptionPane.ERROR_MESSAGE);
            }
            else{
                if (newMember == null){
                    nameTextField.setText(""); phoneTextField.setText(""); passwordField.setText("");
                    JOptionPane.showMessageDialog(mainPanel, "User dengan nama " + nama + " dan nomor hp " + nomorHp + " sudah ada!",  "User already exists!", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    nameTextField.setText(""); phoneTextField.setText(""); passwordField.setText("");
                    JOptionPane.showMessageDialog(mainPanel, "Berhasil membuat user dengan ID " + newMember.getId() + "!", "Registered successfully!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        else{
            nameTextField.setText(""); phoneTextField.setText(""); passwordField.setText("");
            JOptionPane.showMessageDialog(mainPanel,"Semua field wajib diisi!", "Invalid input!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
