import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class VendingMachine {
    public static JLabel optionLabel; // label dari homepage
    public VendingMachine() {

        // membuat frame
        JFrame frame = new JFrame("Vending Machine");
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setVisible(true);

        // membuat panel utama
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 400,300);
        mainPanel.setLayout(null);

        // mengubah tulisan label
        optionLabel = new JLabel("Please select an option");
        optionLabel.setBounds(125, 100, 200, 30);
        mainPanel.add(optionLabel);

        // membuat button add money
        JButton AddMoneyBtn = new JButton("Add Money");
        AddMoneyBtn.setBounds(60, 150, 120, 40);
        AddMoneyBtn.setFont(new Font("Arial", Font.BOLD, 10));
        AddMoneyBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new AddMoney(); // membuat window baru untuk jika memilih add money
            }
            
        });
        mainPanel.add(AddMoneyBtn);

        // membuat button purchase product
        JButton PurchaseProductBtn = new JButton("Purchase Product");
        PurchaseProductBtn.setBounds(210, 150, 120, 40);
        PurchaseProductBtn.setFont(new Font("Arial", Font.BOLD, 10));
        PurchaseProductBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new PurchaseProduct(); // membuat window baru jika memilih purchase product
            }
            
        });
        mainPanel.add(PurchaseProductBtn);

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // memberhentikan program jika menutup window
    }
}
