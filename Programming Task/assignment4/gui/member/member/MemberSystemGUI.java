package assignment4.gui.member.member;

import assignment3.nota.Nota;
import assignment3.nota.NotaManager;
import assignment3.user.Member;
import assignment3.user.menu.SystemCLI;
import assignment4.MainFrame;
import assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MemberSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "MEMBER";

    public MemberSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }

    @Override
    public String getPageName(){
        return KEY;
    }

    public Member getLoggedInMember(){
        return loggedInMember;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements MemberSystem.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected ArrayList<JButton> createButtons() {
        ArrayList<JButton> buttons = new ArrayList<JButton>();

        // membuat button utk laundry
        JButton laundryButton = new JButton("Saya ingin laundry");
        laundryButton.setFont(new Font("Lucida Console",Font.BOLD, 14));
        laundryButton.setSize(90, 60);
        buttons.add(laundryButton);

        // membuat button untuk lihat nota
        JButton lihatNotaButton = new JButton("Lihat Nota Saya");
        lihatNotaButton.setFont(new Font("Lucida Console",Font.BOLD, 14));
        lihatNotaButton.setSize(90, 60);
        buttons.add(lihatNotaButton);

        return buttons;
    }

    /**
     * Method ini mensupply ActionListener korespondensi dengan button yang dibuat createButtons()
     * sesuai dengan requirements MemberSystem.
     *
     * @return Array of ActionListener.
     * */
    @Override
    protected ArrayList<ActionListener> createActionListeners() {
        ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();

        // membuat fungsi tombol laundry
        ActionListener laundryListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNota();
            }
        };
        listeners.add(laundryListener);

        // membuat fungsi tombol lihat nota
        ActionListener lihatNotaListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDetailNota();
            } 
        };
        listeners.add(lihatNotaListener);
        
        return listeners;
    }

    /**
     * Menampilkan detail Nota milik loggedInMember.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void showDetailNota() {
        String notaInfo = "";
        JTextArea detailNota;
        JScrollPane scrollPane;

        if(NotaManager.notaList.size() != 0){
            for (Nota nota : NotaManager.notaList) {
                if(nota.getMember().equals(loggedInMember)){
                    notaInfo += nota.toString() + "\n";
                }
            }
        }

        if(!notaInfo.isEmpty()){
            notaInfo = notaInfo.substring(0, notaInfo.length()-1);
            detailNota = new JTextArea(notaInfo);
            detailNota.setEditable(false);
            detailNota.setLineWrap(true);
            detailNota.setWrapStyleWord(true);
            detailNota.setSize(500, 600);
            detailNota.setFont(new Font("Lucida Console", Font.PLAIN, 12));

            scrollPane = new JScrollPane(detailNota);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setSize(500, 600);

            JOptionPane.showMessageDialog(null, scrollPane, "Detail nota", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JLabel labelNota = new JLabel("Anda belum pernah laundry!");
            labelNota.setFont(new Font("Lucida Console", Font.PLAIN, 12));
            JOptionPane.showMessageDialog(this, labelNota, "Detail Nota", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Pergi ke halaman CreateNotaGUI.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void createNota() {
        MainFrame.getInstance().navigateTo(CreateNotaGUI.KEY); // pindah ke panel createnota
    }
}
