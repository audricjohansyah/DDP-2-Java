package assignment4.gui.member.employee;

import assignment3.nota.Nota;
import assignment3.nota.NotaManager;
import assignment3.nota.service.LaundryService;
import assignment3.user.Member;
import assignment3.user.menu.SystemCLI;
import assignment4.gui.member.AbstractMemberGUI;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EmployeeSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "EMPLOYEE";

    public EmployeeSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }


    @Override
    public String getPageName(){
        return KEY;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements Employee.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected ArrayList<JButton> createButtons() {
        ArrayList<JButton> buttons = new ArrayList<JButton>();

        // membuat button untuk cuci
        JButton nyuciButton = new JButton("It's nyuci time");
        nyuciButton.setFont(new Font("Lucida Console",Font.BOLD, 14));
        nyuciButton.setSize(90, 60);
        buttons.add(nyuciButton);

        // membuat button untuk display list nota
        JButton displayNotaButton = new JButton("Display list nota");
        displayNotaButton.setFont(new Font("Lucida Console",Font.BOLD, 14));
        displayNotaButton.setSize(90, 60);
        buttons.add(displayNotaButton);

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
        
        // membuat fungsi mencuci
        ActionListener nyuciListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               cuci();
            }
        };
        listeners.add(nyuciListener);

        // membuat fungsi display nota
        ActionListener displayNotaListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayNota();
            } 
        };
        listeners.add(displayNotaListener);
        
        return listeners;
    }

    /**
     * Menampilkan semua Nota yang ada pada sistem.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void displayNota() {
        ArrayList <Nota> notaList = NotaManager.notaList;
        String infoNota = "";

        if (notaList.size() != 0){
            for(Nota nota: notaList){ // mencetak status semua nota di list nota
                infoNota += "<html>" + "Nota " + nota.getId() + " : " + nota.getNotaStatus()+ "<br>" + "<html>";
            }
        }

        if(infoNota.isEmpty()){
            JOptionPane.showMessageDialog(this,"Belum ada nota yang terdaftar di dalam sistem!", "Cucian unavailable!", JOptionPane.ERROR_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(this,infoNota, "Display nota", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Menampilkan dan melakukan action mencuci.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void cuci() {
        boolean allDone = false;
        ArrayList <Nota> notaList = NotaManager.notaList;
        Member loginMember = loggedInMember;
        String infoCuci = "";

        // mengecek list nota kosong atau tidak
        if(notaList.size() != 0){
            JOptionPane.showMessageDialog(this,"Stand back! " + loginMember.getNama() + " beginning to nyuci!", "Sedang mencuci!", JOptionPane.INFORMATION_MESSAGE);
            // melaksanakan service pada semua nota
            for (Nota nota : notaList){
                for (LaundryService service : nota.getServices()){
                    if(service.isDone() == false){
                        infoCuci += "<html>" + "Nota " + nota.getId() + " : " + service.doWork() + "<br>" + "<html>";
                        allDone = false;
                        break;
                    }
                    else{
                        allDone = true;
                    }
                }
                nota.setNotaStatus(); // mengubah status nota jika semua service sudah selesai
                if(allDone == true){ // output nota sudah selesai
                    infoCuci += "<html>" + "Nota " + nota.getId() + " : " + nota.getNotaStatus() + "<br>" + "<html>";
                    continue;
                }
            }
        }

        if(infoCuci.isEmpty()){
            JOptionPane.showMessageDialog(this,"Belum ada nota yang terdaftar di dalam sistem!", "Cucian unavailable!", JOptionPane.ERROR_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(this,infoCuci, "Doing work!", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
}
