import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.Scanner;

public class GUILoginAdmin extends JFrame{
    private JLabel lab1, email, parola;
    private JButton login, back;
    private JTextField tfEmail;
    private JPasswordField pfParola;
    private JCheckBox afiseazaParola;
    public GUILoginAdmin(Connection connection){
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Admin");
        setSize(new Dimension(770, 500));
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(10, 38, 71));

        lab1 = new JLabel("ADMIN");
        lab1.setSize(new Dimension(264, 95));
        lab1.setBounds(320, 14, 264, 95);
        lab1.setFont(new Font("Monaco", Font.BOLD, 30));
        lab1.setBackground(new Color(10, 38, 71));
        lab1.setForeground(Color.WHITE);
        add(lab1);

        email = new JLabel("EMAIL ADMIN: ");
        email.setSize(new Dimension(172, 33));
        email.setBounds(181, 134, 172, 33);
        email.setFont(new Font("Monaco", Font.BOLD, 15));
        email.setBackground(new Color(10, 38, 71));
        email.setForeground(Color.WHITE);
        add(email);

        parola = new JLabel("PAROLA ADMIN: ");
        parola.setSize(new Dimension(172, 33));
        parola.setBounds(181, 202, 172, 33);
        parola.setFont(new Font("Monaco", Font.BOLD, 15));
        parola.setBackground(new Color(10, 38, 71));
        parola.setForeground(Color.WHITE);
        add(parola);

        tfEmail = new JTextField();
        tfEmail.setSize(new Dimension(270, 33));
        tfEmail.setBounds(310, 134, 270, 33);
        tfEmail.setFont(new Font("Monaco", Font.PLAIN,15));
        add(tfEmail);

        pfParola = new JPasswordField();
        pfParola.setEchoChar('•');
        pfParola.setSize(new Dimension(270, 33));
        pfParola.setBounds(310, 202, 270, 33);
        pfParola.setFont(new Font("Monaco", Font.PLAIN,15));
        add(pfParola);

        afiseazaParola = new JCheckBox("Arata parola");
        afiseazaParola.setForeground(Color.WHITE);
        afiseazaParola.setBackground(new Color(10, 38, 71));
        afiseazaParola.setOpaque(true);
        afiseazaParola.addActionListener(ae -> {
            JCheckBox c = (JCheckBox) ae.getSource();
            pfParola.setEchoChar(c.isSelected() ? '\u0000' : (Character)          UIManager.get("PasswordField.echoChar"));
        });
        afiseazaParola.setBounds(410, 240, 100, 50);
        add(afiseazaParola);


        login = new JButton("LOGIN");
        login.setSize(new Dimension(176, 49));
        login.setBounds(181, 291, 176, 49);
        login.setFont(new Font("Monaco", Font.BOLD, 20));
        login.setBackground(new Color(44, 116, 179));
        login.setForeground(Color.WHITE);
        add(login);

        back = new JButton("INAPOI");
        back.setSize(new Dimension(176, 49));
        back.setBounds(450, 291, 176, 49);
        back.setFont(new Font("Monaco", Font.BOLD, 20));
        back.setBackground(new Color(44, 116, 179));
        back.setForeground(Color.WHITE);
        add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                setVisible(false);
                GUIPaginaPrincipala guiPaginaPrincipala = new GUIPaginaPrincipala(connection);
                guiPaginaPrincipala.setVisible(true);
                guiPaginaPrincipala.setLocationRelativeTo(null);
                guiPaginaPrincipala.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            }
        });
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emAdmin = "", passAdmin = "";
                try {
                    Scanner sc = new Scanner(new File("admin.txt"));
                    while (sc.hasNextLine()) {
                        if (emAdmin.equals("")) {
                            emAdmin = sc.nextLine();
                        } else {
                            passAdmin = sc.nextLine();
                        }
                    }
                    sc.close();
                }catch (FileNotFoundException ex){
                    System.out.println("Nu exista fisier");
                    ex.printStackTrace();
                }

                String parolaIntrodusa = pfParola.getText(), emailIntrodus = tfEmail.getText();

                if (verificareParolaAdmin(passAdmin, parolaIntrodusa) == false){
                    JOptionPane.showMessageDialog(null, "Email sau parola incorecta");
                }else{
                    if (verificareEmailAdmin(emAdmin, emailIntrodus) == false){
                        JOptionPane.showMessageDialog(null, "Email sau parola incorect");
                    }else{
                        setVisible(false);
                        dispose();
                        JOptionPane.showMessageDialog(null, "Logat");
                        GUIAdmin newAdminModif = new GUIAdmin(connection);
                    }
                }
            }
        });
    }

    public boolean verificareEmailAdmin(String checkWithE, String toCheckE){
        if (toCheckE.equals(checkWithE)){
            return true;
        }
        return false;
    }

    public boolean verificareParolaAdmin(String checkWithP, String toCheckP){
        if (toCheckP.equals(checkWithP)){
            return true;
        }
        return false;
    }
}
