package assignment4.gui.member.member;

import assignment3.nota.Nota;
import assignment3.nota.NotaManager;
import assignment3.nota.service.AntarService;
import assignment3.nota.service.CuciService;
import assignment3.nota.service.LaundryService;
import assignment3.nota.service.SetrikaService;
import assignment3.user.Member;
import assignment4.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNotaGUI extends JPanel {
    public static final String KEY = "CREATE_NOTA";
    private JPanel mainPanel;
    private JLabel paketLabel;
    private JComboBox<String> paketComboBox;
    private JButton showPaketButton;
    private JLabel beratLabel;
    private JTextField beratTextField;
    private JCheckBox setrikaCheckBox;
    private JCheckBox antarCheckBox;
    private JButton createNotaButton;
    private JButton backButton;
    private final SimpleDateFormat fmt;
    private final Calendar cal;
    private final MemberSystemGUI memberSystemGUI;
    private static String[] paket = {"Express", "Fast", "Reguler"}; // membuat array paket
    private String selection; // placeholder pilihan paket

    public CreateNotaGUI(MemberSystemGUI memberSystemGUI) {
        super(new BorderLayout()); // Setup layout
        this.memberSystemGUI = memberSystemGUI;
        this.fmt = NotaManager.fmt; // set up tanggal
        this.cal = NotaManager.cal; // set up tanggal

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
        // membuat label paket input
        paketLabel = new JLabel("Paket Laundry:");
        paketLabel.setFont(new Font("Courier", Font.BOLD, 13));
        paketLabel.setBounds(100, 50, 120, 30);

        // membuat combobox paket
        paketComboBox = new JComboBox<>(paket);
        paketComboBox.setFont(new Font("Lucida Console", Font.BOLD, 12));
        paketComboBox.setSelectedIndex(0); // memilih objek di combobox index pertama array produk
        selection = (String) paketComboBox.getSelectedItem(); // mengupdate pilihan sesuai dengan paket yg dipilih
        paketComboBox.setBounds(340, 50, 110, 30);
        paketComboBox.addActionListener(new ActionListener() { // implementasi fungsi memilih paket di combobox

            @Override
            public void actionPerformed(ActionEvent e) {
                selection = (String) paketComboBox.getSelectedItem();
            }
        });
        
        // membuat tombol show paket
        showPaketButton = new JButton("Show Paket");
        showPaketButton.setFont(new Font("Courier", Font.BOLD, 12));
        showPaketButton.setBounds(470, 50, 110, 30);
        showPaketButton.addActionListener(new ActionListener() { // implementasi fungsi tombol show paket

            @Override
            public void actionPerformed(ActionEvent e) {
               showPaket();
            }
            
        });

        // membuat label berat input
        beratLabel = new JLabel("Berat Cucian (Kg):");
        beratLabel.setFont(new Font("Courier", Font.BOLD, 13));
        beratLabel.setBounds(100, 100, 180, 30);

        // membuat text field berat
        beratTextField = new JTextField();
        beratTextField.setFont(new Font("Lucida Console", Font.BOLD, 12));
        beratTextField.setBounds(340, 100, 110, 30);

        // membuat checkbox untuk services
        setrikaCheckBox = new JCheckBox("Tambah Setrika Service (1000 / Kg)");
        setrikaCheckBox.setFont(new Font("Courier", Font.BOLD, 13));
        setrikaCheckBox.setBounds(100, 160, 400, 30);

        antarCheckBox = new JCheckBox("Tambah Antar Service (2000 / 4 Kg, kemudian 500 / Kg)");
        antarCheckBox.setFont(new Font("Courier", Font.BOLD, 13));
        antarCheckBox.setBounds(100, 190, 500, 30);

        // membuat button create nota
        createNotaButton = new JButton("Buat Nota");
        createNotaButton.setFont(new Font("Monospaced", Font.BOLD, 14));
        createNotaButton.setBounds(100, 260, 485, 30);
        createNotaButton.addActionListener(new ActionListener() { // implementasi fungsi button create nota
            @Override
            public void actionPerformed(ActionEvent e) {
                createNota();
            }
        });
    
        // membuat button back 
        backButton = new JButton("Kembali");
        backButton.setFont(new Font("Monospaced", Font.BOLD, 14));
        backButton.setBounds(100, 300, 485, 30);
        backButton.addActionListener(new ActionListener() { // implementasi fungsi button back
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBack();
        }});
     
        // menambahkan semua ke main panel
        mainPanel.add(createNotaButton);
        mainPanel.add(backButton);
        mainPanel.add(antarCheckBox);
        mainPanel.add(setrikaCheckBox);
        mainPanel.add(beratTextField);
        mainPanel.add(showPaketButton);
        mainPanel.add(paketLabel);
        mainPanel.add(paketComboBox);
        mainPanel.add(beratLabel);
    }

    /**
     * Menampilkan list paket pada user.
     * Akan dipanggil jika pengguna menekan "showPaketButton"
     * */
    private void showPaket() {
        String paketInfo = """
                        <html><pre>
                        +-------------Paket-------------+
                        | Express | 1 Hari | 12000 / Kg |
                        | Fast    | 2 Hari | 10000 / Kg |
                        | Reguler | 3 Hari |  7000 / Kg |
                        +-------------------------------+
                        </pre></html>
                        """;

        JLabel label = new JLabel(paketInfo);
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, label, "Paket Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method untuk melakukan pengecekan input user dan mendaftarkan nota yang sudah valid pada sistem.
     * Akan dipanggil jika pengguna menekan "createNotaButton"
     * */
    private void createNota() {
        // validasi input berat dari user
        if(!beratTextField.getText().isBlank() && !beratTextField.getText().isEmpty()){
            if(!beratTextField.getText().matches("\\d+") || beratTextField.getText().equalsIgnoreCase("0")){
                paketComboBox.setSelectedIndex(0); beratTextField.setText("");
                setrikaCheckBox.setSelected(false); antarCheckBox.setSelected(false);
                JOptionPane.showMessageDialog(mainPanel,"Berat cucian harus berisi angka yang valid!", "Invalid input!", JOptionPane.ERROR_MESSAGE);
            }
            else{
                Member loginMember = memberSystemGUI.getLoggedInMember();
                int berat = Integer.valueOf(beratTextField.getText());
                if(berat < 2){
                    berat = 2;
                }

                // membuat objek nota
                String tanggalLaundry = fmt.format(cal.getTime());
                Nota nota = new Nota(loginMember, berat, selection, tanggalLaundry);

                // menambahkan service yang diinginkan
                LaundryService cuciService = new CuciService();
                nota.addService(cuciService);

                if (setrikaCheckBox.isSelected()){
                    LaundryService setrikaService = new SetrikaService();
                    nota.addService(setrikaService);
                }
            
                if (antarCheckBox.isSelected()){
                    LaundryService antarService = new AntarService();
                    nota.addService(antarService);
                }

                NotaManager.addNota(nota); // menambahkan nota ke list nota di notamanager

                // mengosongkan field, checkbox dan set combobox ke pilihan default
                paketComboBox.setSelectedIndex(0); beratTextField.setText(""); 
                setrikaCheckBox.setSelected(false); antarCheckBox.setSelected(false);

                if(berat == 2){
                    JOptionPane.showMessageDialog(mainPanel,"Cucian kurang dari 2 kg, maka cucian akan dianggap 2 kg", "Berat Cucian", JOptionPane.INFORMATION_MESSAGE);
                }
                JOptionPane.showMessageDialog(mainPanel,"Nota berhasil dibuat!", "Success!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else{
            // mengosongkan field, checkbox dan set combobox ke pilihan default
            paketComboBox.setSelectedIndex(0); beratTextField.setText("");
            setrikaCheckBox.setSelected(false); antarCheckBox.setSelected(false);
            JOptionPane.showMessageDialog(mainPanel,"Berat cucian harus diisi!", "Invalid input!", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        // mengosongkan field, checkbox dan set combobox ke pilihan default
        paketComboBox.setSelectedIndex(0); beratTextField.setText(""); 
        setrikaCheckBox.setSelected(false); antarCheckBox.setSelected(false);
        MainFrame.getInstance().navigateTo(MemberSystemGUI.KEY);
    }
}
