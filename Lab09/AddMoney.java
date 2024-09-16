import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMoney {
    private JTextField moneyInput; // field untuk menerima uang yang ingin dimasukkan
    public static double balance; // variable static jumlah uang yang dimiliki
    public AddMoney(){

        // membuat frame
        JFrame frame = new JFrame("Money Input");
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setVisible(true);

        // membuat panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 400,300);
        mainPanel.setLayout(null);

        // membuat label
        JLabel AmountLabel = new JLabel("Enter the amount of money:");
        AmountLabel.setBounds(110, 30, 200, 30);
        AmountLabel.setLayout(null);
        mainPanel.add(AmountLabel);

        // meminta jumlah uang
        moneyInput = new JTextField();
        moneyInput.setBounds(50, 70, 280, 100);
        moneyInput.setLayout(null);
        mainPanel.add(moneyInput);

        // membuat button untuk submit jumlah uang dan validasi input
        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(50, 180, 280, 30);
        submitBtn.setLayout(null);
        submitBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!moneyInput.getText().isEmpty() && !moneyInput.getText().isBlank()){
                    try{
                        double money = Double.valueOf(moneyInput.getText());
                        if(money <= 0.0){
                            money = 0.0;
                            JOptionPane.showMessageDialog(frame,"Maaf, jumlah uang yang Anda masukkan tidak valid!", "Info", JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                            balance += money;
                            VendingMachine.optionLabel.setText("Total Money: Rp." + String.format("%.1f",balance));
                            frame.dispose();
                        }
                    }
                    catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(frame,"Maaf, jumlah uang yang Anda masukkan tidak valid!", "Info", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame,"Maaf, jumlah uang yang Anda masukkan tidak valid!", "Info", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        mainPanel.add(submitBtn);

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // menutup window dan kembali ke homepage vendingmachine saat exit
    }
}