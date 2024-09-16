import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PurchaseProduct {
    // set up variable yang dibutuhkan
    private static String[] products = {"Akua", "Fruti Apel", "Palpi Jeruk", "Neskafe Latte", "Koka kola"};
    private static String[] productPrices = {"Rp.5000.0", "Rp.8000.0", "Rp.7500.0", "Rp.11000.0", "Rp.9500.0"};
    private JTextField priceField;
    private JTextField totalField;
    private double productPrice;
    private double quantity;
    private double totalPrice;
    public PurchaseProduct(){

        // membuat frame
        JFrame frame = new JFrame("Purchase Product");
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setVisible(true);

        // membuat panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 400,300);
        mainPanel.setLayout(null);

        // membuat price label dan field
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(90, 110, 80, 30);
        priceField = new JTextField();
        priceField.setBounds(200, 110, 100, 30);
        priceField.setEditable(false);

        // membuat label total dan field total
        JLabel totalLabel = new JLabel("Total:");
        totalLabel.setBounds(90, 150, 80, 30);
        totalField = new JTextField();
        totalField.setBounds(200, 150, 100, 30);
        totalField.setEditable(false);

        // membuat combobox product dan field product
        JLabel productLabel = new JLabel("Product:");
        productLabel.setBounds(90, 30, 80, 30);
        JComboBox<String> productBox = new JComboBox<>(products);
        productBox.setBounds(200, 30, 100, 30);
        productBox.setSelectedIndex(0); // memilih objek di combobox index pertama array produk
        priceField.setText("Rp.5000.0"); // memasang harga default awal untuk item index pertama array produk
        productPrice = 5000.0; // memasang harga product
        productBox.addActionListener(new ActionListener() {  // mengupdate price field sesuai dengan item pilihan

            @Override
            public void actionPerformed(ActionEvent e) {
                String selection = (String) productBox.getSelectedItem();
                if (selection.equalsIgnoreCase("Akua")) {
                    priceField.setText("Rp.5000.0");
                } else if (selection.equalsIgnoreCase("Fruti Apel")) {
                    priceField.setText("Rp.8000.0");
                } else if (selection.equalsIgnoreCase("Palpi Jeruk")) {
                    priceField.setText("Rp.7500.0");
                } else if (selection.equalsIgnoreCase("Neskafe Latte")) {
                    priceField.setText("Rp.11000.0");
                } else if (selection.equalsIgnoreCase("Koka kola")) {
                    priceField.setText("Rp.9500.0");
                }
                String[] price = priceField.getText().split("\\.");
                productPrice = Double.valueOf(price[1]);
                if(quantity != 0){
                    totalPrice = quantity * productPrice;
                    totalField.setText("Rp." + String.format("%.1f",totalPrice));
                }
            }
        });
        
        // membuat label kuantitas dan field kuantitas
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(90, 70, 80, 30);
        JTextField quantityField = new JTextField();
        quantityField.setBounds(200, 70, 100, 30);
        quantityField.addKeyListener(new KeyListener() { // melakukan live update jika kuantitas diubah

            @Override
            public void keyTyped(KeyEvent e) {
                if(!quantityField.getText().isEmpty() && !quantityField.getText().isBlank()){
                    try{
                        quantity = Double.valueOf(quantityField.getText());
                        if(quantity <= 0.0){
                            quantity = 0.0;
                        }
                    }
                    catch(NumberFormatException ex){
                        quantity = 0.0;
                    }
                    totalPrice = quantity * productPrice;
                    totalField.setText("Rp." + String.format("%.1f",totalPrice));
                }
                else{
                    quantity = 0.0;
                    totalField.setText("");
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(!quantityField.getText().isEmpty() && !quantityField.getText().isBlank()){
                    try{
                        quantity = Double.valueOf(quantityField.getText());
                        if(quantity <= 0.0){
                            quantity = 0.0;
                        }
                    }
                    catch(NumberFormatException ex){
                        quantity = 0.0;
                    }
                    totalPrice = quantity * productPrice;
                    totalField.setText("Rp." + String.format("%.1f",totalPrice));
                }
                else{
                    quantity = 0.0;
                    totalField.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(!quantityField.getText().isEmpty() && !quantityField.getText().isBlank()){
                    try{
                        quantity = Double.valueOf(quantityField.getText());
                        if(quantity <= 0.0){
                            quantity = 0.0;
                        }
                    }
                    catch(NumberFormatException ex){
                        quantity = 0.0;
                    }
                    totalPrice = quantity * productPrice;
                    totalField.setText("Rp." + String.format("%.1f",totalPrice));
                }
                else{
                    quantity = 0.0;
                    totalField.setText("");
                }
            }
        });

        // membuat tombol purchase dan validasi input kuantitas dan jumlah produk
        JButton purchaseBtn = new JButton("Purchase");
        purchaseBtn.setBounds(130, 200, 120, 30);
        purchaseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!quantityField.getText().isEmpty() && !quantityField.getText().isBlank()){
                    try{
                        quantity = Double.valueOf(quantityField.getText());
                        if(quantity <= 0.0){
                            quantity = 0.0;
                            JOptionPane.showMessageDialog(frame,"Maaf, jumlah barang yang Anda masukkan tidak valid!", "Info", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(frame,"Maaf, jumlah barang yang Anda masukkan tidak valid!", "Info", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame,"Maaf, jumlah barang yang Anda masukkan tidak valid!", "Info", JOptionPane.ERROR_MESSAGE);
                }

                totalPrice = quantity * productPrice;
                if(totalPrice != 0.0){
                    // evaluasi balance jika transaksi berhasil
                    if(AddMoney.balance == totalPrice){
                        AddMoney.balance -= totalPrice;
                        JOptionPane.showMessageDialog(frame,"Berhasil!, Anda membayar dengan uang pas!", "Info", JOptionPane.INFORMATION_MESSAGE);
                        VendingMachine.optionLabel.setText("Total Money: Rp." + String.format("%.1f",AddMoney.balance));
                        productBox.setSelectedItem(productBox.getSelectedItem()); // mereset item di combobox menjadi pilihan terakhir
                        priceField.setText(productPrices[productBox.getSelectedIndex()]); // memasang harga default berdasarkan item pilihan terakhir
                        String[] str = productPrices[productBox.getSelectedIndex()].split("\\.");
                        quantity = 0; // mereset kuantitas
                        productPrice = Double.valueOf(str[1]); // mereset harga produk berdasarkan item pilihan terakhir
                        totalPrice = 0; // mereset total harga yang harus dibayar
                        totalField.setText(""); // mereset total field
                        quantityField.setText(""); // mereset quantity field
                    }
                    else if(AddMoney.balance > totalPrice){
                        AddMoney.balance -= totalPrice;
                        JOptionPane.showMessageDialog(frame,"Berhasil! Kembalian Anda sebesar Rp." + String.format("%.1f",AddMoney.balance), "Info", JOptionPane.INFORMATION_MESSAGE);
                        VendingMachine.optionLabel.setText("Total Money: Rp." + String.format("%.1f",AddMoney.balance));
                        // penjelasan sama seperti di atas
                        productBox.setSelectedItem(productBox.getSelectedItem());
                        priceField.setText(productPrices[productBox.getSelectedIndex()]);
                        String[] str = productPrices[productBox.getSelectedIndex()].split("\\.");
                        quantity = 0;
                        productPrice = Double.valueOf(str[1]);
                        totalPrice = 0;
                        totalField.setText("");
                        quantityField.setText("");
                    }
                    else if(AddMoney.balance < totalPrice){
                        JOptionPane.showMessageDialog(frame,"Maaf, Uang Anda tidak cukup!", "Info", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        mainPanel.add(productBox);
        mainPanel.add(productLabel);
        mainPanel.add(quantityLabel);
        mainPanel.add(quantityField);
        mainPanel.add(priceLabel);
        mainPanel.add(priceField);
        mainPanel.add(totalLabel);
        mainPanel.add(totalField);
        mainPanel.add(purchaseBtn);

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // menutup window dan kembali ke homepage vendingmachine saat exit
    }
}