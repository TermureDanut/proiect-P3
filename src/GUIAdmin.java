import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import LoginRelated.Angajat;
import Produse.*;
import arbore.bstClass;

public class GUIAdmin extends JFrame{
    private List<Produs> listaProduse;
    private List<Angajat> listaAngajati;
    private List<Raport> listaRaporturi;
    private JTextArea tArea;
    private JScrollPane scrollPane;
    private JButton adaugaProdus, stergeProdus, updateProdus, adaugaAngajat, stergeAngajat, updateAngajat, veziMin, veziMax, discount, done;
    private JButton veziAngajati, veziProduse, raporturi;

    public GUIAdmin(Connection connection){
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Admin");
        setSize(new Dimension(700, 600));
        getContentPane().setBackground(new Color(10, 38, 71));
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        adaugaProdus = new JButton("Adauga produs");
        adaugaProdus.setForeground(Color.WHITE);
        adaugaProdus.setBackground(new Color(20, 66, 114));
        adaugaProdus.setOpaque(true);
        adaugaProdus.setSize(new Dimension(133, 61));
        adaugaProdus.setFont(new Font("Monaco", Font.BOLD, 13));
        adaugaProdus.setBounds(35, 39, 133, 61);
        add(adaugaProdus);

        stergeProdus = new JButton("Sterge produs");
        stergeProdus.setForeground(Color.WHITE);
        stergeProdus.setBackground(new Color(20, 66, 114));
        stergeProdus.setOpaque(true);
        stergeProdus.setSize(new Dimension(133, 61));
        stergeProdus.setFont(new Font("Monaco", Font.BOLD, 13));
        stergeProdus.setBounds(35, 113, 133, 61);
        add(stergeProdus);

        updateProdus = new JButton("Update produs");
        updateProdus.setForeground(Color.WHITE);
        updateProdus.setBackground(new Color(20, 66, 114));
        updateProdus.setOpaque(true);
        updateProdus.setSize(new Dimension(133, 61));
        updateProdus.setFont(new Font("Monaco", Font.BOLD, 13));
        updateProdus.setBounds(35, 187, 133, 61);
        add(updateProdus);

        adaugaAngajat = new JButton("Adauga angajat");
        adaugaAngajat.setForeground(Color.WHITE);
        adaugaAngajat.setBackground(new Color(20, 66, 114));
        adaugaAngajat.setOpaque(true);
        adaugaAngajat.setSize(new Dimension(133, 61));
        adaugaAngajat.setFont(new Font("Monaco", Font.BOLD, 13));
        adaugaAngajat.setBounds(35, 264, 133, 61);
        add(adaugaAngajat);

        stergeAngajat = new JButton("Sterge angajat");
        stergeAngajat.setForeground(Color.WHITE);
        stergeAngajat.setBackground(new Color(20, 66, 114));
        stergeAngajat.setOpaque(true);
        stergeAngajat.setSize(new Dimension(133, 61));
        stergeAngajat.setFont(new Font("Monaco", Font.BOLD, 13));
        stergeAngajat.setBounds(35, 338, 133, 61);
        add(stergeAngajat);

        updateAngajat = new JButton("Update angajat");
        updateAngajat.setForeground(Color.WHITE);
        updateAngajat.setBackground(new Color(20, 66, 114));
        updateAngajat.setOpaque(true);
        updateAngajat.setSize(new Dimension(133, 61));
        updateAngajat.setFont(new Font("Monaco", Font.BOLD, 13));
        updateAngajat.setBounds(35, 414, 133, 61);
        add(updateAngajat);

        veziMin = new JButton("Minim");
        veziMin.setForeground(Color.WHITE);
        veziMin.setBackground(new Color(20, 66, 114));
        veziMin.setOpaque(true);
        veziMin.setSize(new Dimension(133, 61));
        veziMin.setFont(new Font("Monaco", Font.BOLD, 13));
        veziMin.setBounds(191, 39, 133, 61);
        add(veziMin);

        veziMax = new JButton("Maxim");
        veziMax.setForeground(Color.WHITE);
        veziMax.setBackground(new Color(20, 66, 114));
        veziMax.setOpaque(true);
        veziMax.setSize(new Dimension(133, 61));
        veziMax.setFont(new Font("Monaco", Font.BOLD, 13));
        veziMax.setBounds(191, 113, 133, 61);
        add(veziMax);

        veziProduse = new JButton("Produse");
        veziProduse.setForeground(Color.WHITE);
        veziProduse.setBackground(new Color(20, 66, 114));
        veziProduse.setOpaque(true);
        veziProduse.setSize(new Dimension(133, 61));
        veziProduse.setFont(new Font("Monaco", Font.BOLD, 13));
        veziProduse.setBounds(191, 187, 133, 61);
        add(veziProduse);

        veziAngajati = new JButton("Angajati");
        veziAngajati.setForeground(Color.WHITE);
        veziAngajati.setBackground(new Color(20, 66, 114));
        veziAngajati.setOpaque(true);
        veziAngajati.setSize(new Dimension(133, 61));
        veziAngajati.setFont(new Font("Monaco", Font.BOLD, 13));
        veziAngajati.setBounds(191, 264, 133, 61);
        add(veziAngajati);

        discount = new JButton("Discount");
        discount.setForeground(Color.WHITE);
        discount.setBackground(new Color(20, 66, 114));
        discount.setOpaque(true);
        discount.setSize(new Dimension(133, 61));
        discount.setFont(new Font("Monaco", Font.BOLD, 13));
        discount.setBounds(191, 338, 133, 61);
        add(discount);

        raporturi = new JButton("Raporturi");
        raporturi.setForeground(Color.WHITE);
        raporturi.setBackground(new Color(20, 66, 114));
        raporturi.setOpaque(true);
        raporturi.setSize(new Dimension(133, 51));
        raporturi.setFont(new Font("Monaco", Font.BOLD, 13));
        raporturi.setBounds(191, 414, 133, 61);
        add(raporturi);

        done = new JButton("Done");
        done.setForeground(Color.WHITE);
        done.setBackground(new Color(20, 66, 114));
        done.setOpaque(true);
        done.setSize(new Dimension(133, 51));
        done.setFont(new Font("Monaco", Font.BOLD, 13));
        done.setBounds(191, 496, 133, 61);
        add(done);

        raporturi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String print = setListaRaporturi(connection, listaRaporturi);
                show(print);
            }
        });
        adaugaProdus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                setVisible(false);
                GUIAdaugaProdus guiadd = new GUIAdaugaProdus(connection);
            }
        });

        stergeProdus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                GUIStergeProdus guisterge = new GUIStergeProdus(connection);
            }
        });

        updateProdus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                GUIUpdateProdus guiupdate = new GUIUpdateProdus(connection);
            }
        });

        adaugaAngajat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                GUIAdaugaAngajat guiadauga = new GUIAdaugaAngajat(connection);
            }
        });

        stergeAngajat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                GUIStergeAngajat guisterge = new GUIStergeAngajat(connection);
            }
        });

        updateAngajat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                GUIUpdateAngajat guiupdate = new GUIUpdateAngajat(connection);
            }
        });

        show("Fereastra destinata modificarilor pe care le poate face admin-ul");
        veziMin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Statement stmt;
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

                Produs obj;
                bstClass bst = new bstClass();

                for (int i = 1; i <= count; i++){
                    try {
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

                        obj = new Produs(i, categorie,nrComenzi, marca, pret, culoare, imagine);
                        bst.insert(obj);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                bstClass.Node temp = bst.minValueNode(bst.root);
                delete();
                show(temp.a.toStringAdmin());
            }
        });

        veziMax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Statement stmt;
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

                Produs obj;
                bstClass bst = new bstClass();

                for (int i = 1; i <= count; i++){
                    try {
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

                        obj = new Produs(i, categorie,nrComenzi, marca, pret, culoare, imagine);
                        bst.insert(obj);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                bstClass.Node temp = bst.maxValueNode(bst.root);
                delete();
                show(temp.a.toStringAdmin());
            }
        });

        veziAngajati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String toPrint = printAngajati(connection, listaAngajati);
                delete();
                show(toPrint);
            }
        });

        veziProduse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String toPrint = printProduse(connection, listaProduse);
                delete();
                show(toPrint);
            }
        });

        discount.setToolTipText("Adauga discount produselor");
        discount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = JOptionPane.showInputDialog(null, "Introdu procent reducere.");
                if (str != null) {
                    int procent = Integer.parseInt(str);
                    setListaProduse(connection);
                    for (Produs p : listaProduse) {
                        p.setDiscount();
                        if (p.isDiscount() == true) {
                            p.setPretDiscount(procent);
                        }
                    }

                    Statement stmtC;
                    int count;

                    try {
                        stmtC = connection.createStatement();
                        String query = "select count(*) from produse";
                        ResultSet rs = stmtC.executeQuery(query);
                        rs.next();
                        count = rs.getInt(1);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    String stmt;
                    for (int i = 1; i <= count; i++) {
                        stmt = "update produse set pret=? where idProduse=?";
                        try {
                            PreparedStatement prepStmt = connection.prepareStatement(stmt);
                            prepStmt.setDouble(1, listaProduse.get(i - 1).getPret());
                            prepStmt.setInt(2, i);
                            prepStmt.execute();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                        stmt = "update produse set discount=? where idProduse=?";
                        try {
                            PreparedStatement prepStmt = connection.prepareStatement(stmt);
                            prepStmt.setBoolean(1, listaProduse.get(i - 1).isDiscount());
                            prepStmt.setInt(2, i);
                            prepStmt.execute();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });

        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                setVisible(false);
                GUIPaginaPrincipala guip = new GUIPaginaPrincipala(connection);
                guip.setVisible(true);
                guip.setLocationRelativeTo(null);
                guip.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            }
        });
    }

    public void delete(){
        remove(tArea);
        remove(scrollPane);
    }

    public void show(String s){
        tArea = new JTextArea(s);
        tArea.setSize(new Dimension(342, 542));
        tArea.setLineWrap(true);
        tArea.setBounds(345, 14, 332, 542);
        tArea.setFont(new Font("Monaco", Font.PLAIN, 13));
        tArea.setForeground(Color.WHITE);
        tArea.setBackground(new Color(44, 116, 179));
        tArea.setOpaque(true);
        add(tArea);

        scrollPane = new JScrollPane(tArea);
        scrollPane.setSize(new Dimension(342, 542));
        scrollPane.setBounds(345, 14, 342, 542);
        scrollPane.setBackground(new Color(44, 116, 179));
        scrollPane.setOpaque(true);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
    }

    public String setListaRaporturi(Connection connection, List<Raport> listaRaporturi){
        Statement stmt = null;
        int count;
        String toPrint = "";
        try {
            stmt = connection.createStatement();
            String query = "select count(*) from raporturi";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        listaRaporturi = new ArrayList<>();

        for (int i = 1; i <= count; i++){
            try{
                int idProdus = 0;
                String deMod = null;
                PreparedStatement prep = connection.prepareStatement("select idProdus from raporturi where (id)=(?)");
                prep.setInt(1, i);
                ResultSet rs = prep.executeQuery();
                if (rs.next()) {
                    idProdus = rs.getInt("idProdus");
                }

                prep = connection.prepareStatement("select DeModificat from raporturi where (id)=(?)");
                prep.setInt(1, i);
                rs = prep.executeQuery();
                if (rs.next()) {
                    deMod = rs.getString("DeModificat");
                }

                Raport r = new Raport(i, idProdus, deMod);
                listaRaporturi.add(r);
            }catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        for (Raport r : listaRaporturi){
            toPrint = toPrint + r.toString() + '\n';
        }
        return toPrint;
    }
    public void setListaProduse(Connection connection){
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

        listaProduse = new ArrayList<>();

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

                Produs prod = new Produs(i, categorie, nrComenzi, marca, pret, culoare, imagine);
                prod.setDiscount1(discount);
                listaProduse.add(prod);
            }catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    public String printAngajati(Connection connection, java.util.List<Angajat> listaAngajati){
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

        Produs temp;
        listaAngajati = new ArrayList<>();
        String toPrint = "";

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

                listaAngajati.add(new Angajat(i, nume, prenume, email, parola, nrLogin));
            }catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        for (Angajat a : listaAngajati){
            toPrint = toPrint + a.toStringAdmin() + '\n';
        }
        return toPrint;
    }

    public String printProduse(Connection connection, List<Produs> listaProduse){
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

        Produs temp;
        listaProduse = new ArrayList<>();
        String toPrint = "";

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

                Produs prod = new Produs(i, categorie, nrComenzi, marca, pret, culoare, imagine);
                prod.setDiscount1(discount);
                listaProduse.add(prod);
            }catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        for (Produs p : listaProduse){
            toPrint = toPrint + p.toStringAdmin() + '\n';
        }
        return toPrint;
    }
}
