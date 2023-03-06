import LoginRelated.Angajat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GUILoginAngajat extends JFrame {
    private JLabel principal, email, parola, codAngajat;
    private JTextField tfEmail, tfCod;
    private JPasswordField pfParola;
    private JCheckBox arataParola;
    private JButton login;
    private List<Angajat> listaAngajati;

    public GUILoginAngajat(Connection connection){
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login angajat");
        setSize(new Dimension(770, 500));
        getContentPane().setBackground(new Color(10, 38, 71));
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        principal = new JLabel("LOGIN ANGAJAT");
        principal.setFont(new Font("Monaco", Font.PLAIN, 20));
        principal.setSize(new Dimension(198, 78));
        principal.setBounds(285, 43, 198, 78);
        principal.setBackground(new Color(10, 38, 71));
        principal.setOpaque(true);
        principal.setForeground(Color.WHITE);
        add(principal);

        email = new JLabel("Email: ");
        email.setFont(new Font("Monaco", Font.PLAIN, 18));
        email.setSize(new Dimension(180, 38));
        email.setBounds(105, 141, 180, 38);
        email.setBackground(new Color(10, 38, 71));
        email.setOpaque(true);
        email.setForeground(Color.WHITE);
        add(email);

        parola = new JLabel("Parola: ");
        parola.setFont(new Font("Monaco", Font.PLAIN, 18));
        parola.setSize(new Dimension(180, 38));
        parola.setBounds(105, 198, 180, 38);
        parola.setBackground(new Color(10, 38, 71));
        parola.setOpaque(true);
        parola.setForeground(Color.WHITE);
        add(parola);

        codAngajat = new JLabel("Cod angajat: ");
        codAngajat.setFont(new Font("Monaco", Font.PLAIN, 18));
        codAngajat.setSize(new Dimension(180, 38));
        codAngajat.setBounds(105, 251, 180, 38);
        codAngajat.setBackground(new Color(10, 38, 71));
        codAngajat.setOpaque(true);
        codAngajat.setForeground(Color.WHITE);
        add(codAngajat);

        tfEmail = new JTextField();
        tfEmail.setSize(new Dimension(282, 38));
        tfEmail.setBounds(338, 141, 282, 38);
        tfEmail.setFont(new Font("Monaco", Font.PLAIN,15));
        add(tfEmail);

        pfParola = new JPasswordField();
        pfParola.setEchoChar('•');
        pfParola.setSize(new Dimension(282, 38));
        pfParola.setBounds(338, 198, 282, 38);
        pfParola.setFont(new Font("Monaco", Font.PLAIN,15));
        add(pfParola);

        tfCod = new JTextField();
        tfCod.setSize(new Dimension(92, 38));
        tfCod.setBounds(338, 251, 92, 38);
        tfCod.setFont(new Font("Monaco", Font.PLAIN,15));
        add(tfCod);

        arataParola = new JCheckBox("Arata parola");
        arataParola.setBounds(556, 251, 100, 50);
        arataParola.setForeground(Color.WHITE);
        arataParola.setBackground(new Color(10, 38, 71));
        arataParola.setOpaque(true);
        arataParola.addActionListener(ae -> {
            JCheckBox c = (JCheckBox) ae.getSource();
            pfParola.setEchoChar(c.isSelected() ? '\u0000' : (Character)          UIManager.get("PasswordField.echoChar"));
        });
        add(arataParola);

        login = new JButton("LOGIN");
        login.setSize(new Dimension(177, 63));
        login.setBounds(295, 360, 177, 63);
        login.setFont(new Font("Monaco", Font.PLAIN, 17));
        login.setForeground(Color.WHITE);
        login.setBackground(new Color(20, 66, 114));
        add(login);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = tfEmail.getText();
                String pass = pfParola.getText();
                int cod = Integer.parseInt(tfCod.getText());
                addaugaListaAngajati(connection);


                boolean ok = false;
                for (Angajat a : listaAngajati){
                    if (a.getEmail().equals(user)){
                        if (a.getParola().equals(pass)){
                            if (a.getNrLogin() == cod){
                                ok = true;
                            }
                        }
                    }
                }
                if (ok == true){
                    dispose();
                    setVisible(false);
                    JOptionPane.showMessageDialog(null, "Logat");
                    GUIPaginaPrincipalaAngajat pp = new GUIPaginaPrincipalaAngajat(connection);
                    pp.setVisible(true);
                    pp.setLocationRelativeTo(null);
                    pp.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                }else{
                    JOptionPane.showMessageDialog(null, "Introdu datele corecte.");
                }
            }
        });
    }

    public void addaugaListaAngajati(Connection connection){
        Statement stmt = null;
        int count;

        try {
            stmt = connection.createStatement();
            String query = "select count(*) from angajati";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        listaAngajati = new ArrayList<>();

        for (int i = 1; i <= count; i++){
            try{
                String nume = "", prenume = "", email = "", parola = "";
                int nrLogin = 0;
                PreparedStatement prep = connection.prepareStatement("select nume from angajati where (idAngajati)=(?)");
                prep.setInt(1, i);
                ResultSet rs = prep.executeQuery();
                if (rs.next()) {
                    nume = rs.getString("nume");
                }

                prep = connection.prepareStatement("select prenume from angajati where (idAngajati)=(?)");
                prep.setInt(1, i);
                rs = prep.executeQuery();
                if (rs.next()) {
                    prenume = rs.getString("prenume");
                }

                prep = connection.prepareStatement("select email from angajati where (idAngajati)=(?)");
                prep.setInt(1, i);
                rs = prep.executeQuery();
                if (rs.next()) {
                    email = rs.getString("email");
                }

                prep = connection.prepareStatement("select parola from angajati where (idAngajati)=(?)");
                prep.setInt(1, i);
                rs = prep.executeQuery();
                if (rs.next()) {
                    parola = rs.getString("parola");
                }

                prep = connection.prepareStatement("select nrLogin from angajati where (idAngajati)=(?)");
                prep.setInt(1, i);
                rs = prep.executeQuery();
                if (rs.next()) {
                    nrLogin = rs.getInt("nrLogin");
                }

                this.listaAngajati.add(new Angajat(i, nume, prenume, email, parola, nrLogin));
            }catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
