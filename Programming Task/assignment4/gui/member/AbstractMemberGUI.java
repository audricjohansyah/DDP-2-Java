package assignment4.gui.member;

import assignment3.user.Member;
import assignment3.user.menu.SystemCLI;
import assignment4.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class AbstractMemberGUI extends JPanel implements Loginable{
    private JLabel welcomeLabel;
    private JLabel loggedInAsLabel;
    protected Member loggedInMember;
    private final SystemCLI systemCLI;
    public AbstractMemberGUI(SystemCLI systemCLI) {
        super(new BorderLayout());
        this.systemCLI = systemCLI;

        // set up header
        welcomeLabel = new JLabel("Welcome, " + "", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Papyrus", Font.BOLD, 35));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        add(welcomeLabel, BorderLayout.NORTH);

        // Set up footer
        loggedInAsLabel = new JLabel("Logged in as " + "", SwingConstants.CENTER);
        loggedInAsLabel.setFont(new Font("Monospaced", Font.BOLD, 15));
        loggedInAsLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        add(loggedInAsLabel, BorderLayout.SOUTH);

        // Initialize buttons
        JPanel buttonsPanel = initializeButtons();
        add(buttonsPanel, BorderLayout.CENTER);
    }

    /**
     * Membuat panel button yang akan ditampilkan pada Panel ini.
     * Buttons dan ActionListeners akan disupply oleh method createButtons() & createActionListeners() respectively.
     * <p> Feel free to make any changes. Be creative and have fun!
     *
     * @return JPanel yang di dalamnya berisi Buttons.
     * */
    protected JPanel initializeButtons() {
        ArrayList<JButton> buttons = createButtons();
        ArrayList<ActionListener> listeners = createActionListeners();

        if (buttons.size() != listeners.size()) {
            throw new IllegalStateException("Number of buttons and listeners must be equal.");
        }

        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(5, 5, 5, 5);

        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).addActionListener(listeners.get(i));;
            buttonsPanel.add(buttons.get(i), gbc);
        }

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Lucida Console",Font.BOLD, 14));
        logoutButton.setSize(90, 60);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getInstance().logout();
            }
        });
        buttonsPanel.add(logoutButton, gbc);
        return buttonsPanel;
    }

    /**
     * Method untuk login pada panel.
     * <p>
     * Method ini akan melakukan pengecekan apakah ID dan passowrd yang telah diberikan dapat login
     * pada panel ini. <p>
     * Jika bisa, member akan login pada panel ini, method akan:<p>
     *  - mengupdate Welcome & LoggedInAs label.<p>
     *  - mengupdate LoggedInMember sesuai dengan instance pemilik ID dan password.
     *
     * @param id -> ID anggota yang akan diautentikasi.
     * @param password -> password anggota untuk mengautentikasi.
     * @return true jika ID dan password sesuai dengan instance member, false jika tidak.
     * */
    public boolean login(String id, String password) {
        Member authMember = systemCLI.authUser(id, password);
        if (authMember != null) {
            loggedInMember = authMember;
            welcomeLabel.setText("Welcome, " + loggedInMember.getNama());
            loggedInAsLabel.setText("Logged in as " + loggedInMember.getId());
            return true;
        }
        return false;
    }

    /**
     * Method untuk logout pada panel ini.
     * Akan mengubah loggedInMemberMenjadi null.
     * */
    public void logout() {
        loggedInMember = null;
    }

    /**
     * Method ini mensupply buttons apa saja yang akan dimuat oleh panel ini.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     * Harus diimplementasikan sesuai class yang menginherit class ini.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    // protected abstract JButton[] createButtons();
    protected abstract ArrayList<JButton> createButtons();

    /**
     * Method ini mensupply ActionListener korespondensi dengan button yang dibuat createButtons().
     * Harus diimplementasikan sesuai class yang menginherit class ini.
     *
     * @return Array of ActionListener.
     * */
    // protected abstract ActionListener[] createActionListeners();
    protected abstract ArrayList<ActionListener> createActionListeners();

}
