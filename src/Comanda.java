import LoginRelated.Client;
import Produse.Produs;
import com.twilio.Twilio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Comanda extends JFrame {
    private JTextArea textArea;
    private JButton button;
    private Produs p;
    private JTextField textField;
    public static final String ACCOUNT_SID = "AC1779549c53c199d39d31d876422e64aa";
    public static final String AUTH_TOKEN = "34b217e2197726f0a6143a0d6dd13c8b";

    /**
     *
     * @param connection = conexiunea la baza de date
     * @param c = clientul logat curent
     * toate operatiile referitoare la creare si popularea JFrame-ului legat de comanda unui client sunt facute in constructorul clasei
     * in Constructor se modifica un jtextarea in functie de produsele pe care clientul le are in cos si se afiseaza produsele respective folosind metoda addToTextArea
     * intr-un alt jtextarea se calculeaza suma totata a cosului folosind functia suma
     */
    public Comanda(Connection connection, Client c){
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Comanda");
        setSize(new Dimension(602, 267));
        getContentPane().setBackground(new Color(10, 38, 71));
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        if (c.getP1() != 0 || c.getP2() != 0 || c.getP3() != 0 || c.getP4() != 0 || c.getP5() != 0) {
            String toPrint = "";
            if (c.getP1() != 0)
                toPrint = toPrint + addToTextArea(connection, c.getP1()) + '\n';

            if (c.getP2() != 0)
                toPrint = toPrint + addToTextArea(connection, c.getP2()) + '\n';

            if (c.getP3() != 0)
                toPrint = toPrint + addToTextArea(connection, c.getP3()) + '\n';

            if (c.getP4() != 0)
                toPrint = toPrint + addToTextArea(connection, c.getP4()) + '\n';

            if (c.getP5() != 0)
                toPrint = toPrint + addToTextArea(connection, c.getP5()) + '\n';
            textArea = new JTextArea(toPrint);
            textArea.setFont(new Font("Monaco", Font.PLAIN, 17));
            textArea.setForeground(Color.WHITE);
            textArea.setSize(new Dimension(568, 150));
            textArea.setBounds(14, 14, 568, 150);
            textArea.setBackground(new Color(44, 116, 179));
            textArea.setOpaque(true);
            add(textArea);
        }

        double total = suma(connection, c.getP1()) + suma(connection, c.getP2()) + suma(connection, c.getP3()) + suma(connection, c.getP4()) + suma(connection, c.getP5());
        textField = new JTextField(Double.toString(total));
        textField.setBounds(300, 183, 134, 35);
        textField.setSize(134, 35);
        textField.setFont(new Font("Monaco", Font.PLAIN, 15));
        add(textField);

        button = new JButton("Comanda");
        button.setSize(new Dimension(134, 35));
        button.setBounds(447, 183, 134, 35);
        button.setFont(new Font("Monaco", Font.PLAIN, 15));
        button.setBackground(new Color(10, 38, 71));
        button.setForeground(Color.WHITE);
        add(button);

        /**
         * butonul button este responsabil de operatiile care se executa dupa plasarea unei comenzi
         * dupa plasare reseteaza valorile in baza de date astfel incat clientul sa poata plasa o alta comanda
         * afiseaza un messageDialog ca sa informeze clientul de statutul comenzii lui si trimite un mesaj de confirmare
         * pe numarul de telefon pentru o confirmare a plasarii comenzii
         */
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (c.getP1() == 0 && c.getP2() == 0 && c.getP3() == 0  && c.getP4() == 0 && c.getP5() == 0){
                    JOptionPane.showMessageDialog(null, "Nu exista produse in cos.");
                }else{
                    adauga(connection, 0, "produs1", c.getId());
                    adauga(connection, 0, "produs2", c.getId());
                    adauga(connection, 0, "produs3", c.getId());
                    adauga(connection, 0, "produs4", c.getId());
                    adauga(connection, 0, "produs5", c.getId());
                    dispose();
                    setVisible(false);
                    JOptionPane.showMessageDialog(null, "Comanda a fost plasata si va fi livrata la adresa '" + c.getAdresa() + "' in maxim 3 zile lucratoare.\nModaliatatea de plata este ramburs.\nVa multumim!");
                    c.setP1(0);
                    c.setP2(0);
                    c.setP3(0);
                    c.setP4(0);
                    c.setP5(0);

                    String mesaj = "Comanda a fost plasata si va fi livrata la adresa '" + c.getAdresa() + "' in maxim 3 zile lucratoare.\nModaliatatea de plata este ramburs.\nVa multumim!";
                    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                    com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(new com.twilio.type.PhoneNumber("+40764980720"), "MG40ac2c0e4c75237c2c923986c0928901", mesaj).create();
                }
            }
        });

    }

    /**
     *
     * @param connection
     * @param idProdus
     * @param nrIdProdus
     * @param idClient
     * metoda lucreaza pe baza de date si seteaza valori noi coloanelor destinate produselor folosind un PreparedStatement
     */
    public void adauga(Connection connection, int idProdus, String nrIdProdus, int idClient){
        String stmt = "update clienti set " + nrIdProdus + " = ? where id = " + Integer.toString(idClient);
        try {
            PreparedStatement prepStmt = connection.prepareStatement(stmt);
            prepStmt.setInt(1, idProdus);
            prepStmt.execute();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * metoda returneaza dupa ce a extras produsul dorit din baza de date pretul produsului respectiv care este folosit la costul total al comenzii
     * @param connection
     * @param prod
     * @return
     */
    public double suma(Connection connection, int prod){
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

        try{
            String categorie = "", marca = "", culoare = "", imagine = "";
            double pret = 0;
            int nrComenzi = 0;
            boolean discount = false;
            PreparedStatement prep = connection.prepareStatement("select categorie from produse where (idProduse)=(?)");
            prep.setInt(1, prod);
            ResultSet rs = prep.executeQuery();
            if (rs.next()) {
                categorie = rs.getString("categorie");
            }

            prep = connection.prepareStatement("select marca from produse where (idProduse)=(?)");
            prep.setInt(1, prod);
            rs = prep.executeQuery();
            if (rs.next()) {
                marca = rs.getString("marca");
            }

            prep = connection.prepareStatement("select culoare from produse where (idProduse)=(?)");
            prep.setInt(1, prod);
            rs = prep.executeQuery();
            if (rs.next()) {
                culoare = rs.getString("culoare");
            }

            prep = connection.prepareStatement("select imagine from produse where (idProduse)=(?)");
            prep.setInt(1, prod);
            rs = prep.executeQuery();
            if (rs.next()) {
                imagine = rs.getString("imagine");
            }

            prep = connection.prepareStatement("select pret from produse where (idProduse)=(?)");
            prep.setInt(1, prod);
            rs = prep.executeQuery();
            if (rs.next()) {
                pret = rs.getDouble("pret");
            }

            prep = connection.prepareStatement("select nrComenzi from produse where (idProduse)=(?)");
            prep.setInt(1, prod);
            rs = prep.executeQuery();
            if (rs.next()) {
                nrComenzi = rs.getInt("nrComenzi");
            }

            prep = connection.prepareStatement("select discount from produse where (idProduse)=(?)");
            prep.setInt(1, prod);
            rs = prep.executeQuery();
            if (rs.next()) {
                discount = rs.getBoolean("discount");
            }

            p = new Produs(prod, categorie, nrComenzi, marca, pret, culoare, imagine);
            p.setDiscount1(discount);
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return p.getPret();
    }

    /**
     * metoda preia un produs dorit din baza de date si creeaza un obiect de tip Produs dupa care returneaza informatiile despre acel produs
     * @param connection
     * @param prod
     * @return
     */
    public String addToTextArea(Connection connection, int prod){
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

        try{
            String categorie = "", marca = "", culoare = "", imagine = "";
            double pret = 0;
            int nrComenzi = 0;
            boolean discount = false;
            PreparedStatement prep = connection.prepareStatement("select categorie from produse where (idProduse)=(?)");
            prep.setInt(1, prod);
            ResultSet rs = prep.executeQuery();
            if (rs.next()) {
                categorie = rs.getString("categorie");
            }

            prep = connection.prepareStatement("select marca from produse where (idProduse)=(?)");
            prep.setInt(1, prod);
            rs = prep.executeQuery();
            if (rs.next()) {
                marca = rs.getString("marca");
            }

            prep = connection.prepareStatement("select culoare from produse where (idProduse)=(?)");
            prep.setInt(1, prod);
            rs = prep.executeQuery();
            if (rs.next()) {
                culoare = rs.getString("culoare");
            }

            prep = connection.prepareStatement("select imagine from produse where (idProduse)=(?)");
            prep.setInt(1, prod);
            rs = prep.executeQuery();
            if (rs.next()) {
                imagine = rs.getString("imagine");
            }

            prep = connection.prepareStatement("select pret from produse where (idProduse)=(?)");
            prep.setInt(1, prod);
            rs = prep.executeQuery();
            if (rs.next()) {
                pret = rs.getDouble("pret");
            }

            prep = connection.prepareStatement("select nrComenzi from produse where (idProduse)=(?)");
            prep.setInt(1, prod);
            rs = prep.executeQuery();
            if (rs.next()) {
                nrComenzi = rs.getInt("nrComenzi");
            }

            prep = connection.prepareStatement("select discount from produse where (idProduse)=(?)");
            prep.setInt(1, prod);
            rs = prep.executeQuery();
            if (rs.next()) {
                discount = rs.getBoolean("discount");
            }

            p = new Produs(prod, categorie, nrComenzi, marca, pret, culoare, imagine);
            p.setDiscount1(discount);
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return p.toString();
    }
}
