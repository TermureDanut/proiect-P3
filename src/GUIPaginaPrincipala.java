import Produse.Produs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GUIPaginaPrincipala extends JFrame {

    private JButton login, register, favorite, cosulMeu;
    private JButton meniuL, meniuTT, meniuP, meniuCM, meniuTAV, meniuG, toate;
    private JLabel paginaPrincipala;
    private JMenuBar meniu;
    private JScrollPane scrollPanePrincipal;
    private JPanel panelPrincipal;
    private List<Produs> produse;
    private List<JPanel> listaProdusePaneluri;
    private List<JButton> butoane;

    public GUIPaginaPrincipala(Connection connection){
        setTitle("Pagina principala");
        setSize(new Dimension(980, 1000));
        getContentPane().setBackground(new Color(10, 38, 71));
        setResizable(false);
        setLayout(null);

        retrieve(connection);
        JPanel susStanga = new JPanel(new FlowLayout(FlowLayout.CENTER));
        susStanga.setSize(new Dimension(260, 70));
        susStanga.setBounds(350, 10, 250, 60);
        susStanga.setBackground(new Color(10, 38, 71));
        susStanga.setOpaque(true);
        paginaPrincipala = new JLabel("MAGAZIN ELECTRONICE");
        paginaPrincipala.setForeground(Color.WHITE);
        paginaPrincipala.setFont(new Font("Monaco", Font.BOLD, 20));
        paginaPrincipala.setPreferredSize(new Dimension(250, 50));
        paginaPrincipala.setBackground(new Color(10, 38, 71));
        paginaPrincipala.setOpaque(true);
        susStanga.add(paginaPrincipala);
        add(susStanga);

        JPanel susDreapta = new JPanel(new FlowLayout(FlowLayout.CENTER));
        susDreapta.setSize(new Dimension(420, 70));
        susDreapta.setBounds(530, 10, 420, 70);
        susDreapta.setBackground(new Color(10, 38, 71));
        susDreapta.setOpaque(true);
        login = new JButton("Login");
        login.setForeground(Color.WHITE);
        login.setFont(new Font("Monaco", Font.BOLD, 15));
        login.setPreferredSize(new Dimension(100, 30));
        login.setBackground(new Color(20, 66, 114));
        login.setOpaque(true);

        register = new JButton("Register");
        register.setForeground(Color.WHITE);
        register.setFont(new Font("Monaco", Font.BOLD, 15));
        register.setPreferredSize(new Dimension(100, 30));
        register.setBackground(new Color(20, 66, 114));
        register.setOpaque(true);

        susDreapta.add(login);
        susDreapta.add(register);
        add(susDreapta);

        JPanel mijlocSus = new JPanel();
        mijlocSus.setSize(new Dimension(960, 100));
        mijlocSus.setBounds(10, 100, 960, 100);
        mijlocSus.setBackground(new Color(10, 38, 71));
        mijlocSus.setLayout(new FlowLayout());
        mijlocSus.setOpaque(true);

        meniuL = new JButton("Laptopuri");
        meniuL.setForeground(Color.WHITE);
        meniuL.setFont(new Font("Monaco", Font.BOLD, 12));
        meniuL.setPreferredSize(new Dimension(110, 40));
        meniuL.setBackground(new Color(20, 66, 114));
        meniuL.setOpaque(true);

        meniuTT = new JButton("Telefoane/Tablete");
        meniuTT.setForeground(Color.WHITE);
        meniuTT.setFont(new Font("Monaco", Font.BOLD, 12));
        meniuTT.setPreferredSize(new Dimension(160, 40));
        meniuTT.setBackground(new Color(20, 66, 114));
        meniuTT.setOpaque(true);

        meniuP = new JButton("Periferice");
        meniuP.setForeground(Color.WHITE);
        meniuP.setFont(new Font("Monaco", Font.BOLD, 12));
        meniuP.setPreferredSize(new Dimension(110, 40));
        meniuP.setBackground(new Color(20, 66, 114));
        meniuP.setOpaque(true);

        meniuCM = new JButton("Calculatoare/Monitoare");
        meniuCM.setForeground(Color.WHITE);
        meniuCM.setFont(new Font("Monaco", Font.BOLD, 12));
        meniuCM.setPreferredSize(new Dimension(190, 40));
        meniuCM.setBackground(new Color(20, 66, 114));
        meniuCM.setOpaque(true);

        meniuTAV = new JButton("Televizoare/Audio/Video");
        meniuTAV.setForeground(Color.WHITE);
        meniuTAV.setFont(new Font("Monaco", Font.BOLD, 12));
        meniuTAV.setPreferredSize(new Dimension(190, 40));
        meniuTAV.setBackground(new Color(20, 66, 114));
        meniuTAV.setOpaque(true);

        meniuG = new JButton("Gaming");
        meniuG.setForeground(Color.WHITE);
        meniuG.setFont(new Font("Monaco", Font.BOLD, 12));
        meniuG.setPreferredSize(new Dimension(110, 40));
        meniuG.setBackground(new Color(20, 66, 114));
        meniuG.setOpaque(true);

        toate = new JButton("TOATE");
        toate.setForeground(Color.WHITE);
        toate.setFont(new Font("Monaco", Font.BOLD, 12));
        toate.setPreferredSize(new Dimension(110, 40));
        toate.setBackground(new Color(20, 66, 114));
        toate.setOpaque(true);

        mijlocSus.add(meniuL);
        mijlocSus.add(meniuTT);
        mijlocSus.add(meniuP);
        mijlocSus.add(meniuCM);
        mijlocSus.add(meniuTAV);
        mijlocSus.add(meniuG);
        mijlocSus.add(toate);
        add(mijlocSus);

        meniuL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stergere();
                addToPanelPrincipal(connection, "laptop");
            }
        });

        meniuTT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stergere();
                addToPanelPrincipal(connection, "telefoane/tablete");
            }
        });

        meniuP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stergere();
                addToPanelPrincipal(connection, "periferice");
            }
        });

        meniuCM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stergere();
                addToPanelPrincipal(connection, "calculatoare/monitoare");
            }
        });
        meniuG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stergere();
                addToPanelPrincipal(connection, "gaming");
            }}
        );
        meniuTAV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stergere();
                addToPanelPrincipal(connection, "televizoare/audio/video");
            }
        });

        toate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stergere();
                addToPanelPrincipal(connection, "toate");
            }
        });

        meniu = new JMenuBar();
        meniu.setBackground(new Color(10, 38, 71));
        meniu.setOpaque(true);
        JMenu file = new JMenu("Extra");
        file.setForeground(Color.WHITE);
        meniu.add(file);
        setJMenuBar(meniu);
        JMenuItem info = new JMenuItem(new AbstractAction("Admin") {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                GUILoginAdmin admin = new GUILoginAdmin(connection);
            }
        });

        JMenuItem logAngajat = new JMenuItem(new AbstractAction("Login Angajat") {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                GUILoginAngajat log = new GUILoginAngajat(connection);
            }
        });


        file.add(info);
        file.add(logAngajat);

        addToPanelPrincipal(connection, "toate");

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                setVisible(false);
                GUILogin loginEvent = new GUILogin(connection);
            }
        });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                setVisible(false);
                GUIRegister registerEvent = new GUIRegister(connection);
            }
        });

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    public int urmatorulMultiplu(int n){
        int x = n;
        boolean ok = false;
        while (ok == false){
            if (x % 3 == 0){
                ok = true;
                return x;
            }
            x = x + 1;
        }
        return x;
    }
    public int nrProduseCategorie(String categorie){
        int count = 0;
        for (Produs p : produse){
            if (categorie.equals(p.getCategorie().toLowerCase())){
                count = count + 1;
            }

            if (categorie.equals("toate")){
                count = count + 1;
            }
        }
        return count;
    }
    public void addToPanelPrincipal(Connection connection, String categorie){
        listaProdusePaneluri = new ArrayList<>();
        butoane = new ArrayList<>();
        addToListPanel(categorie);

        int count = nrProduseCategorie(categorie);
        int nr = getNrIntrari(connection);
        int urmatorulMultiplu = urmatorulMultiplu(nr);

        panelPrincipal = new JPanel();
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setOpaque(true);
        panelPrincipal.setLayout(new GridLayout(urmatorulMultiplu / 3, 3));

        for (JPanel p : listaProdusePaneluri){
            panelPrincipal.add(p);
            panelPrincipal.updateUI();
        }

        for (int i = 0; i < urmatorulMultiplu - count; i++) {
            JButton produs = new JButton();
            produs.setLayout(null);
            produs.setBorderPainted(false);
            produs.setContentAreaFilled(false);
            produs.setFocusPainted(false);
            produs.setOpaque(true);
            produs.setMargin(new Insets(10, 10, 10, 10));
            produs.setSize(new Dimension(300, 400));
            produs.setBackground(Color.WHITE);
            panelPrincipal.add(produs);
        }

        scrollPanePrincipal = new JScrollPane(panelPrincipal);
        scrollPanePrincipal.setBackground(Color.WHITE);
        scrollPanePrincipal.setOpaque(true);
        scrollPanePrincipal.setPreferredSize(new Dimension(900, 760));
        scrollPanePrincipal.setBounds(30, 200, 900, 760);
        scrollPanePrincipal.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanePrincipal.setOpaque(true);
        add(scrollPanePrincipal);

    }

    public void stergere(){
        remove(panelPrincipal);
        remove(scrollPanePrincipal);
    }

    public void addToListPanel(String categorie){
        for (Produs p : produse){
            if (categorie.equals(p.getCategorie().toLowerCase())){
                JPanel produs = new JPanel();
                produs.setPreferredSize(new Dimension(300, 400));
                produs.setBackground(Color.WHITE);
                produs.setLayout(null);

                JTextArea info = new JTextArea(p.toString());
                info.setFont(new Font("Calibri", Font.BOLD, 16));
                info.setBackground(Color.WHITE);
                info.setForeground(Color.BLACK);
                info.setSize(new Dimension(300, 50));
                info.setBounds(0, 0, 300, 50);
                produs.add(info);

                ImageIcon imgButton = new ImageIcon(p.getImagine());
                Image imagineButton = imgButton.getImage();
                Image imagineButtonFinal = imagineButton.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                imgButton = new ImageIcon(imagineButtonFinal);
                JLabel imagine = new JLabel(imgButton);
                imagine.setSize(new Dimension(300, 300));
                imagine.setBounds(0, 50, 300, 300);
                produs.add(imagine);

                JButton btn = new JButton("ADAUGA IN COS");
                btn.setFont(new Font("Calibri", Font.BOLD, 17));
                btn.setBackground(Color.WHITE);
                btn.setForeground(Color.BLACK);
                btn.setSize(new Dimension(300, 50));
                btn.setBounds(0, 350, 300, 50);
                btn.setBorderPainted(false);
                btn.setContentAreaFilled(false);
                btn.setFocusPainted(false);
                btn.setOpaque(true);
                btn.setMargin(new Insets(10, 10, 10, 10));
                produs.add(btn);
                produs.revalidate();

                listaProdusePaneluri.add(produs);
            }

            if (categorie.equals("toate")){
                JPanel produs = new JPanel();
                produs.setPreferredSize(new Dimension(300, 400));
                produs.setLayout(null);

                JTextArea info = new JTextArea(p.toString());
                info.setFont(new Font("Calibri", Font.BOLD, 16));
                info.setBackground(Color.WHITE);
                info.setForeground(Color.BLACK);
                info.setSize(new Dimension(300, 50));
                info.setBounds(0, 0, 300, 50);
                produs.add(info);

                ImageIcon imgButton = new ImageIcon(p.getImagine());
                Image imagineButton = imgButton.getImage();
                Image imagineButtonFinal = imagineButton.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                imgButton = new ImageIcon(imagineButtonFinal);
                JLabel imagine = new JLabel(imgButton);
                imagine.setSize(new Dimension(300, 300));
                imagine.setBounds(0, 50, 300, 300);
                produs.add(imagine);

                JButton btn = new JButton("ADAUGA IN COS");
                btn.setFont(new Font("Calibri", Font.BOLD, 17));
                btn.setBackground(Color.WHITE);
                btn.setForeground(Color.BLACK);
                btn.setSize(new Dimension(300, 50));
                btn.setBounds(0, 350, 300, 50);
                btn.setBorderPainted(false);
                btn.setContentAreaFilled(false);
                btn.setFocusPainted(false);
                btn.setOpaque(true);
                btn.setMargin(new Insets(10, 10, 10, 10));
                produs.revalidate();
                produs.add(btn);

                listaProdusePaneluri.add(produs);
            }
        }
    }

    public int getNrIntrari(Connection connection){
        Statement stmt = null;
        int count;

        try {
            stmt = connection.createStatement();
            String query = "select count(*) from produse";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return count;
    }

    public void retrieve(Connection connection){
        produse = new ArrayList<>();
        int count = getNrIntrari(connection);

        for (int i = 1; i <= count; i++){
            try{
                String categorie = "", marca = "", culoare = "", imagine = "";
                double pret = 0;
                int nrComenzi = 0;
                boolean discount = false;
                PreparedStatement prep = connection.prepareStatement("select categorie from produse where (idProduse)=(?)");
                prep.setInt(1, i);
                ResultSet rs = prep.executeQuery();
                if (rs.next()) {
                    categorie = rs.getString("categorie");
                }

                prep = connection.prepareStatement("select marca from produse where (idProduse)=(?)");
                prep.setInt(1, i);
                rs = prep.executeQuery();
                if (rs.next()) {
                    marca = rs.getString("marca");
                }

                prep = connection.prepareStatement("select culoare from produse where (idProduse)=(?)");
                prep.setInt(1, i);
                rs = prep.executeQuery();
                if (rs.next()) {
                    culoare = rs.getString("culoare");
                }

                prep = connection.prepareStatement("select imagine from produse where (idProduse)=(?)");
                prep.setInt(1, i);
                rs = prep.executeQuery();
                if (rs.next()) {
                    imagine = rs.getString("imagine");
                }

                prep = connection.prepareStatement("select pret from produse where (idProduse)=(?)");
                prep.setInt(1, i);
                rs = prep.executeQuery();
                if (rs.next()) {
                    pret = rs.getDouble("pret");
                }

                prep = connection.prepareStatement("select nrComenzi from produse where (idProduse)=(?)");
                prep.setInt(1, i);
                rs = prep.executeQuery();
                if (rs.next()) {
                    nrComenzi = rs.getInt("nrComenzi");
                }

                prep = connection.prepareStatement("select discount from produse where (idProduse)=(?)");
                prep.setInt(1, i);
                rs = prep.executeQuery();
                if (rs.next()) {
                    discount = rs.getBoolean("discount");
                }

                produse.add(new Produs(i, categorie, nrComenzi, marca, pret, culoare, imagine));
            }catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
