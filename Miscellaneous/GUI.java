import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JFrame frame;
    private JPanel panel;
    private JLabel balanceLabel;
    private JLabel currencyLabel;

    private JButton addMoneyButton;
    private JButton purchaseButton;

    private double balance = 0;
    private String dynamicMessage = "";

    public GUI() {
        createUI();
    }

    private void createUI() {
        frame = new JFrame("Vending Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(400, 300));
        GridBagConstraints gbc = new GridBagConstraints();

        currencyLabel = new JLabel("Total Money: Rp");
        currencyLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(currencyLabel, gbc);

        balanceLabel = new JLabel(String.format("%.2f", balance));
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(balanceLabel, gbc);

        addMoneyButton = new JButton("Add Money");
        addMoneyButton.addActionListener(new AddMoneyListener());
        addMoneyButton.setPreferredSize(new Dimension(150, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(20, 0, 20, 0);
        panel.add(addMoneyButton, gbc);

        purchaseButton = new JButton("Purchase Product");
        purchaseButton.addActionListener(new PurchaseProductListener());
        purchaseButton.setPreferredSize(new Dimension(150, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(20, 0, 20, 0);
        panel.add(purchaseButton, gbc);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class AddMoneyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog(frame, "Enter the amount of money:", "Money Input", JOptionPane.PLAIN_MESSAGE);
            if (input != null && !input.isEmpty()) {
                try {
                    double amount = Double.parseDouble(input);
                    balance += amount;
                    balanceLabel.setText(String.format("%.2f", balance));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class PurchaseProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] products = {"Akua", "Fruti Apel", "Palpi Jeruk", "Neskafe Latte", "Koka kola"};
            JComboBox<String> productBox = new JComboBox<>(products);
            JTextField quantityField = new JTextField();
            JTextField priceField = new JTextField();
            JTextField totalField = new JTextField();
            totalField.setEditable(false);
            priceField.setEditable(false);

            productBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selection = (String) productBox.getSelectedItem();
                    if (selection.equals("Akua")) {
                        priceField.setText("5000");
                    } else if (selection.equals("Fruti Apel")) {
                        priceField.setText("8000");
                    } else if (selection.equals("Palpi Jeruk")) {
                        priceField.setText("7500");
                    } else if (selection.equals("Neskafe Latte")) {
                        priceField.setText("11000");
                    } else if (selection.equals("Koka kola")) {
                        priceField.setText("9500");
                    }
                    
                }
            });

            priceField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                    int quantity = Integer.parseInt(quantityField.getText());
                    int price = Integer.parseInt(priceField.getText());
                    int total = quantity * price;
                    dynamicMessage  = "Rp " + total + ".00";
                    totalField.setText(String.format("%.2f", dynamicMessage));
                    
                    if (quantity <= 0){
                        throw new NumberFormatException("");
                    }
                    } 
                    catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    }
                    });
                    JPanel panel = new JPanel(new GridLayout(4, 2));
                    panel.add(new JLabel("Product:"));
                    panel.add(productBox);
                    panel.add(new JLabel("Quantity:"));
                    panel.add(quantityField);
                    panel.add(new JLabel("Price per item:"));
                    panel.add(priceField);
                    panel.add(new JLabel("Total price:"));
                    panel.add(totalField);
                    panel.revalidate();
            
                    int result = JOptionPane.showConfirmDialog(frame, panel, "Purchase Product", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (result == JOptionPane.OK_OPTION) {
                        try {
                            int quantity = Integer.parseInt(quantityField.getText());
                            int price = Integer.parseInt(priceField.getText());
                            int total = quantity * price;
                            String message = "Berhasil! Kembalian anda sebesar Rp " + (balance - total);
                            if (balance >= total) {
                                balance -= total;
                                balanceLabel.setText(String.format("%.2f", balance));
                                JOptionPane.showMessageDialog(frame, message, "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(frame, "Insufficient funds", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(frame, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
            
    public static void main(String[] args) {
        new GUI();
    }
}