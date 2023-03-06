import LoginRelated.Angajat;
import LoginRelated.Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GUILogin extends JFrame {

    private JLabel titlu, email, parola;
    private JTextField tfEmail;
    private JPasswordField tfParola;
    private JButton login, back;
    private JCheckBox arataParola;
    private List<Client> listaClienti;

    public GUILogin(Connection connection){
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login");
        setSize(new Dimension(500, 400));
        getContentPane().setBackground(new Color(10, 38, 71));
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        titlu = new JLabel("LOGIN");
        titlu.setBackground(new Color(10, 38, 71));
        titlu.setOpaque(true);
        titlu.setForeground(Color.WHITE);
        titlu.setSize(new Dimension(100, 50));
        titlu.setFont(new Font("Monaco", Font.BOLD, 20));

        JPanel susStanga = new JPanel(new FlowLayout(FlowLayout.CENTER));
        susStanga.setBackground(new Color(10, 38, 71));
        susStanga.setSize(new Dimension(120, 70));
        susStanga.setBounds(10, 10, 130, 80);
        susStanga.add(titlu);
        add(susStanga);

        email = new JLabel("Email : ");
        email.setBackground(new Color(10, 38, 71));
        email.setOpaque(true);
        email.setForeground(Color.WHITE);
        email.setSize(new Dimension(80, 20));
        email.setFont(new Font("Monaco", Font.BOLD, 15));
        email.setBounds(20, 120, 90, 30);

        parola = new JLabel("Parola : ");
        parola.setBackground(new Color(10, 38, 71));
        parola.setOpaque(true);
        parola.setForeground(Color.WHITE);
        parola.setSize(new Dimension(80, 20));
        parola.setFont(new Font("Monaco", Font.BOLD, 15));
        parola.setBounds(20, 170, 90, 30);

        tfEmail = new JTextField();
        tfEmail.setPreferredSize(new Dimension(310, 30));
        tfEmail.setFont(new Font("Monaco", Font.BOLD, 12));
        tfEmail.setBounds(120, 120, 310, 30);

        tfParola = new JPasswordField();
        tfParola.setEchoChar('•');
        tfParola.setPreferredSize(new Dimension(310, 30));
        tfParola.setFont(new Font("Monaco", Font.BOLD, 12));
        tfParola.setBounds(120, 170, 310, 30);

        login = new JButton("LOGIN");
        login.setBackground(new Color (20, 66, 114));
        login.setOpaque(true);
        login.setForeground(Color.WHITE);
        login.setSize(new Dimension(150, 50));
        login.setFont(new Font("Monaco", Font.BOLD, 17));
        login.setBounds(20, 250, 150, 50);

        back = new JButton("INAPOI");
        back.setBackground(new Color (20, 66, 114));
        back.setOpaque(true);
        back.setForeground(Color.WHITE);
        back.setSize(new Dimension(150, 50));
        back.setFont(new Font("Monaco", Font.BOLD, 17));
        back.setBounds(200, 250, 150, 50);
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
        arataParola.addActionListener(ae -> {
            JCheckBox c = (JCheckBox) ae.getSource();
            tfParola.setEchoChar(c.isSelected() ? '\u0000' : (Character)          UIManager.get("PasswordField.echoChar"));
        });
        arataParola.setBounds(120, 200, 100, 50);
        arataParola.setBackground(new Color(10, 38, 71));
        arataParola.setOpaque(true);
        arataParola.setForeground(Color.WHITE);

        add(email);
        add(tfEmail);
        add(parola);
        add(tfParola);
        add(arataParola);
        add(login);

        listaClienti = new ArrayList<>();

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String email = tfEmail.getText();
                String parola = tfParola.getText();
                addaugaListaClienti(connection);
                Client clientLogat = null;


                boolean ok = false;
                for (Client a : listaClienti){
                    if (a.getEmail().equals(email)){
                        if (a.getParola().equals(parola)){
                            clientLogat = a;
                            ok = true;
                        }
                    }
                }
                if (ok == true){
                    dispose();
                    setVisible(false);
                    JOptionPane.showMessageDialog(null, "Logat");
                    GUIPaginaPrincipalaClient pp = new GUIPaginaPrincipalaClient(connection, clientLogat);
                    pp.setVisible(true);
                    pp.setLocationRelativeTo(null);
                    pp.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                }else{
                    JOptionPane.showMessageDialog(null, "Introdu datele corecte.");
                }

            }
        });
    }

    public void addaugaListaClienti(Connection connection){
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

        listaClienti = new ArrayList<>();

        for (int i = 1; i <= count; i++){
            try{
                String nume = "", prenume = "", email = "", parola = "", adresa = "", telefon = "";
                int p1 = 0, p2 = 0, p3 = 0, p4 = 0, p5 = 0;
                PreparedStatement prep = connection.prepareStatement("select nume from clienti where (id)=(?)");
                prep.setInt(1, i);
                ResultSet rs = prep.executeQuery();
                if (rs.next()) {
                    nume = rs.getString("nume");
                }

                prep = connection.prepareStatement("select prenume from clienti where (id)=(?)");
                prep.setInt(1, i);
                rs = prep.executeQuery();
                if (rs.next()) {
                    prenume = rs.getString("prenume");
                }

                prep = connection.prepareStatement("select email from clienti where (id)=(?)");
                prep.setInt(1, i);
                rs = prep.executeQuery();
                if (rs.next()) {
                    email = rs.getString("email");
                }

                prep = connection.prepareStatement("select parola from clienti where (id)=(?)");
                prep.setInt(1, i);
                rs = prep.executeQuery();
                if (rs.next()) {
                    parola = rs.getString("parola");
                }

                prep = connection.prepareStatement("select produs1 from clienti where (id)=(?)");
                prep.setInt(1, i);
                rs = prep.executeQuery();
                if (rs.next()) {
                    p1 = rs.getInt("produs1");
                }

                prep = connection.prepareStatement("select produs2 from clienti where (id)=(?)");
                prep.setInt(1, i);
                rs = prep.executeQuery();
                if (rs.next()) {
                    p2 = rs.getInt("produs2");
                }

                prep = connection.prepareStatement("select produs3 from clienti where (id)=(?)");
                prep.setInt(1, i);
                rs = prep.executeQuery();
                if (rs.next()) {
                    p3 = rs.getInt("produs3");
                }

                prep = connection.prepareStatement("select produs4 from clienti where (id)=(?)");
                prep.setInt(1, i);
                rs = prep.executeQuery();
                if (rs.next()) {
                    p4 = rs.getInt("produs4");
                }
                prep = connection.prepareStatement("select produs5 from clienti where (id)=(?)");
                prep.setInt(1, i);
                rs = prep.executeQuery();
                if (rs.next()) {
                    p5 = rs.getInt("produs5");
                }

                prep = connection.prepareStatement("select adresa from clienti where (id)=(?)");
                prep.setInt(1, i);
                rs = prep.executeQuery();
                if (rs.next()) {
                    adresa = rs.getString("adresa");
                }

                prep = connection.prepareStatement("select telefon from clienti where (id)=(?)");
                prep.setInt(1, i);
                rs = prep.executeQuery();
                if (rs.next()) {
                    telefon = rs.getString("telefon");
                }

                Client c = new Client(i, nume, prenume, email, parola, adresa, telefon);
                c.setP1(p1);
                c.setP2(p2);
                c.setP3(p3);
                c.setP4(p4);
                c.setP5(p5);
                this.listaClienti.add(c);
            }catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
