import LoginRelated.Client;
import Produse.Produs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Cos extends JFrame {
    private JLabel label;
    private JTextArea textArea;
    private JTextField textField;
    private JButton button;
    private Produs p;
    public Cos(Connection connection, Client c){
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cos");
        setSize(new Dimension(602, 267));
        getContentPane().setBackground(new Color(10, 38, 71));
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

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

        label = new JLabel("Selecteaza numarul produsului de sters: ");
        label.setSize(new Dimension(275, 35));
        label.setBounds(14, 183, 275, 35);
        label.setFont(new Font("Monaco", Font.PLAIN, 15));
        label.setBackground(new Color(10, 38, 71));
        label.setForeground(Color.WHITE);
        add(label);

        textField = new JTextField();
        textField.setBounds(300, 183, 134, 35);
        textField.setSize(134, 35);
        textField.setFont(new Font("Monaco", Font.PLAIN, 15));
        add(textField);

        button = new JButton("Sterge");
        button.setSize(new Dimension(134, 35));
        button.setBounds(447, 183, 134, 35);
        button.setFont(new Font("Monaco", Font.PLAIN, 15));
        button.setBackground(new Color(10, 38, 71));
        button.setForeground(Color.WHITE);
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Integer.parseInt(textField.getText()) == 1){
                    c.setP1(0);
                    String toPrint = addToTextArea(connection, c.getP2()) + '\n' + addToTextArea(connection, c.getP3());
                    textArea.setText(toPrint);
                    adauga(connection, 0, "produs1", c.getId());
                    JOptionPane.showMessageDialog(null, "Sters");
                }
                if (Integer.parseInt(textField.getText()) == 2){
                    c.setP2(0);
                    String toPrint = addToTextArea(connection, c.getP1()) + '\n' + addToTextArea(connection, c.getP3());
                    textArea.setText(toPrint);
                    adauga(connection, 0, "produs2", c.getId());
                    JOptionPane.showMessageDialog(null, "Sters");
                }
                if (Integer.parseInt(textField.getText()) == 3){
                    c.setP3(0);
                    String toPrint = addToTextArea(connection, c.getP1()) + '\n' + addToTextArea(connection, c.getP2());
                    textArea.setText(toPrint);
                    adauga(connection, 0, "produs3", c.getId());
                    JOptionPane.showMessageDialog(null, "Sters");
                }
                if (Integer.parseInt(textField.getText()) == 4){
                    c.setP4(0);
                    String toPrint = addToTextArea(connection, c.getP1()) + '\n' + addToTextArea(connection, c.getP2()) + '\n' + addToTextArea(connection, c.getP3());
                    textArea.setText(toPrint);
                    adauga(connection, 0, "produs4", c.getId());
                    JOptionPane.showMessageDialog(null, "Sters");
                }
                if (Integer.parseInt(textField.getText()) == 5){
                    c.setP5(0);
                    String toPrint = addToTextArea(connection, c.getP1()) + '\n' + addToTextArea(connection, c.getP2()) + '\n' + addToTextArea(connection, c.getP3()) + '\n' + addToTextArea(connection, c.getP4());
                    textArea.setText(toPrint);
                    adauga(connection, 0, "produs5", c.getId());
                    JOptionPane.showMessageDialog(null, "Sters");
                }
            }
        });

    }

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
