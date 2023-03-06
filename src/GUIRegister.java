import LoginRelated.Angajat;
import LoginRelated.Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class GUIRegister extends JFrame{

    private JLabel titlu;
    private Angajat angajat;
    private Client client;
    private JButton register, back;
    private JLabel nume, prenume, email, parola, confirmaParola, adresa, telefon;
    private JTextField tfNume, tfPrenume, tfEmail, tfAdresa, tfTelefon;
    private JPasswordField tfParola, tfConfirmare;
    private JCheckBox arataParola, arataConfirmare;
    public static final String ACCOUNT_SID = "AC1779549c53c199d39d31d876422e64aa";
    public static final String AUTH_TOKEN = "34b217e2197726f0a6143a0d6dd13c8b";

    public GUIRegister(Connection connection){
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Register");
        setSize(new Dimension(700, 600));
        getContentPane().setBackground(new Color(10, 38, 71));
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        titlu = new JLabel("REGISTER");
        titlu.setBackground(new Color(10, 38, 71));
        titlu.setOpaque(true);
        titlu.setForeground(Color.WHITE);
        titlu.setSize(new Dimension(100, 50));
        titlu.setFont(new Font("Monaco", Font.BOLD, 20));

        JPanel susStanga = new JPanel(new FlowLayout(FlowLayout.CENTER));
        susStanga.setBackground(new Color(10, 38, 71));
        susStanga.setOpaque(true);
        susStanga.setSize(new Dimension(120, 70));
        susStanga.setBounds(10, 10, 130, 80);
        susStanga.add(titlu);
        add(susStanga);

        nume = new JLabel("Nume : ");
        nume.setBackground(new Color(10, 38, 71));
        nume.setOpaque(true);
        nume.setForeground(Color.WHITE);
        nume.setSize(new Dimension(80, 20));
        nume.setFont(new Font("Monaco", Font.BOLD, 15));
        nume.setBounds(20, 100, 90, 30);

        prenume = new JLabel("Prenume : ");
        prenume.setBackground(new Color(10, 38, 71));
        prenume.setOpaque(true);
        prenume.setForeground(Color.WHITE);
        prenume.setSize(new Dimension(80, 20));
        prenume.setFont(new Font("Monaco", Font.BOLD, 15));
        prenume.setBounds(20, 150, 90, 30);

        email = new JLabel("Email : ");
        email.setBackground(new Color(10, 38, 71));
        email.setOpaque(true);
        email.setForeground(Color.WHITE);
        email.setSize(new Dimension(80, 20));
        email.setFont(new Font("Monaco", Font.BOLD, 15));
        email.setBounds(20, 200, 90, 30);

        parola = new JLabel("Parola : ");
        parola.setBackground(new Color(10, 38, 71));
        parola.setOpaque(true);
        parola.setForeground(Color.WHITE);
        parola.setSize(new Dimension(80, 20));
        parola.setFont(new Font("Monaco", Font.BOLD, 15));
        parola.setBounds(20, 250, 90, 30);

        confirmaParola = new JLabel("Confirma parola : ");
        confirmaParola.setBackground(new Color(10, 38, 71));
        confirmaParola.setOpaque(true);
        confirmaParola.setForeground(Color.WHITE);
        confirmaParola.setSize(new Dimension(150, 20));
        confirmaParola.setFont(new Font("Monaco", Font.BOLD, 15));
        confirmaParola.setBounds(20, 300, 150, 30);
        add(confirmaParola);

        telefon = new JLabel("Telefon : ");
        telefon.setBackground(new Color(10, 38, 71));
        telefon.setOpaque(true);
        telefon.setForeground(Color.WHITE);
        telefon.setSize(new Dimension(80, 20));
        telefon.setFont(new Font("Monaco", Font.BOLD, 15));
        telefon.setBounds(20, 350, 90, 30);
        add(telefon);

        adresa = new JLabel("Adresa : ");
        adresa.setBackground(new Color(10, 38, 71));
        adresa.setOpaque(true);
        adresa.setForeground(Color.WHITE);
        adresa.setSize(new Dimension(80, 20));
        adresa.setFont(new Font("Monaco", Font.BOLD, 15));
        adresa.setBounds(20, 400, 90, 30);
        add(adresa);

        tfNume = new JTextField();
        tfNume.setPreferredSize(new Dimension(310, 30));
        tfNume.setFont(new Font("Monaco", Font.BOLD, 12));
        tfNume.setBounds(170, 100, 310, 30);

        tfPrenume = new JTextField();
        tfPrenume.setPreferredSize(new Dimension(310, 30));
        tfPrenume.setFont(new Font("Monaco", Font.BOLD, 12));
        tfPrenume.setBounds(170, 150, 310, 30);

        tfEmail = new JTextField();
        tfEmail.setPreferredSize(new Dimension(310, 30));
        tfEmail.setFont(new Font("Monaco", Font.BOLD, 12));
        tfEmail.setBounds(170, 200, 310, 30);

        tfParola = new JPasswordField();
        tfParola.setEchoChar('•');
        tfParola.setPreferredSize(new Dimension(310, 30));
        tfParola.setFont(new Font("Monaco", Font.BOLD, 12));
        tfParola.setBounds(170, 250, 310, 30);

        tfConfirmare = new JPasswordField();
        tfConfirmare.setEchoChar('•');
        tfConfirmare.setPreferredSize(new Dimension(310, 30));
        tfConfirmare.setFont(new Font("Monaco", Font.BOLD, 12));
        tfConfirmare.setBounds(170, 300, 310, 30);
        add(tfConfirmare);

        tfTelefon = new JTextField();
        tfTelefon.setPreferredSize(new Dimension(310, 30));
        tfTelefon.setFont(new Font("Monaco", Font.BOLD, 12));
        tfTelefon.setBounds(170, 350, 310, 30);
        add(tfTelefon);

        tfAdresa = new JTextField();
        tfAdresa.setPreferredSize(new Dimension(310, 30));
        tfAdresa.setFont(new Font("Monaco", Font.BOLD, 12));
        tfAdresa.setBounds(170, 400, 310, 30);
        add(tfAdresa);

        register = new JButton("REGISTER");
        register.setForeground(Color.WHITE);
        register.setBackground(new Color(20, 66, 114));
        register.setOpaque(true);
        register.setSize(new Dimension(150, 50));
        register.setFont(new Font("Monaco", Font.BOLD, 17));
        register.setBounds(20, 450, 150, 50);

        back = new JButton("INAPOI");
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(20, 66, 114));
        back.setOpaque(true);
        back.setSize(new Dimension(150, 50));
        back.setFont(new Font("Monaco", Font.BOLD, 17));
        back.setBounds(200, 450, 150, 50);
        add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                setVisible(false);
                GUIPaginaPrincipala paginaPrincipala = new GUIPaginaPrincipala(connection);
                paginaPrincipala.setVisible(true);
                paginaPrincipala.setLocationRelativeTo(null);
                paginaPrincipala.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            }
        });

        arataParola = new JCheckBox("Arata parola");
        arataParola.setForeground(Color.WHITE);
        arataParola.setBackground(new Color(10, 38, 71));
        arataParola.setOpaque(true);
        arataParola.addActionListener(ae -> {
            JCheckBox c = (JCheckBox) ae.getSource();
            tfParola.setEchoChar(c.isSelected() ? '\u0000' : (Character)          UIManager.get("PasswordField.echoChar"));
        });
        arataParola.setBounds(490, 240, 100, 50);

        arataConfirmare = new JCheckBox("Arata parola");
        arataConfirmare.setForeground(Color.WHITE);
        arataConfirmare.setBackground(new Color(10, 38, 71));
        arataConfirmare.setOpaque(true);
        arataConfirmare.addActionListener(ae -> {
            JCheckBox c = (JCheckBox) ae.getSource();
            tfConfirmare.setEchoChar(c.isSelected() ? '\u0000' : (Character)          UIManager.get("PasswordField.echoChar"));
        });
        arataConfirmare.setBounds(490, 290, 100, 50);
        add(arataConfirmare);

        add(nume);
        add(prenume);
        add(email);
        add(parola);
        add(tfNume);
        add(tfPrenume);
        add(tfEmail);
        add(tfParola);
        add(arataParola);
        add(register);

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String n = tfNume.getText();
                String p = tfPrenume.getText();
                String em = tfEmail.getText();
                String pass = tfParola.getText();
                String confpass = tfConfirmare.getText();
                String tel = tfTelefon.getText();
                String adr = tfAdresa.getText();

                if (tfNume.getText().equals("") || tfPrenume.getText().equals("") || tfEmail.getText().equals("") ||
                        tfParola.getText().equals("") || tfTelefon.getText().equals("") || tfAdresa.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Completeaza toate campurile!");
                }else{
                    int count = getNrIntrari(connection);
                    if (verificareEmailValid(em) == false){
                        JOptionPane.showMessageDialog(null, "Email invalid!");
                    }else if (pass.length() < 10){
                        JOptionPane.showMessageDialog(null, "Parola trebuie sa fie de cel putin 10 caractere!");
                    }else if (!pass.equals(confpass)) {
                        JOptionPane.showMessageDialog(null, "Parolele nu coincid!");
                    }else{
                        Client c = new Client(count + 1, n, p, em, pass, adr, tel);
                        boolean ok = false;
                        Random r = new Random();
                        int limita = 999;
                        int limitajos = 100;
                        int codRandom = r.nextInt(limitajos, limita);
                        String mesaj = String.valueOf(codRandom);
                        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                        Message message = Message.creator(new com.twilio.type.PhoneNumber("+40764980720"), "MG40ac2c0e4c75237c2c923986c0928901", mesaj).create();
                        while (ok == false) {
                            String cod = JOptionPane.showInputDialog("Introdu codul primit prin mesaj: ");
                            if (cod.equals(mesaj)) {
                                ok = true;
                                String sql = "insert into clienti (id, nume, prenume, email, parola, adresa, telefon)" + " values(?, ?, ?, ?, ?, ?, ?)";
                                try {
                                    PreparedStatement addStmt = connection.prepareStatement(sql);

                                    addStmt.setInt(1, count + 1);
                                    addStmt.setString(2, n);
                                    addStmt.setString(3, p);
                                    addStmt.setString(4, em);
                                    addStmt.setString(5, pass);
                                    addStmt.setString(6, adr);
                                    addStmt.setString(7, tel);

                                    addStmt.execute();
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                } finally {
                                    JOptionPane.showMessageDialog(null, "Inregistrare completa.");
                                    dispose();
                                    setVisible(false);
                                    GUIPaginaPrincipalaClient guiMainPageLogat = new GUIPaginaPrincipalaClient(connection, c);
                                    guiMainPageLogat.setVisible(true);
                                    guiMainPageLogat.setLocationRelativeTo(null);
                                    guiMainPageLogat.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Cod incorect!");
                            }
                        }
                    }
                }
            }
        });
     }

     public int getNrIntrari(Connection connection){
         Statement stmt = null;
         int count;

         try {
             stmt = connection.createStatement();
             String query = "select count(*) from clienti";
             ResultSet rs = stmt.executeQuery(query);
             rs.next();
             count = rs.getInt(1);
         } catch (SQLException ex) {
             throw new RuntimeException(ex);
         }
         return count;
     }

     public boolean verificareEmailValid(String s){
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
}