import LoginRelated.Client;
import Produse.Produs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GUIProdus extends JFrame {
    private JLabel imagine;
    private JTextArea informatii;
    private JButton adaugaInCos;
    private List<Client> listaClienti;
    public GUIProdus(Connection connection, Produs p, Client c){
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalii Produs");
        setSize(new Dimension(700, 365));
        getContentPane().setBackground(new Color(10, 38, 71));
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        ImageIcon imgButton = new ImageIcon(p.getImagine());
        Image imagineButton = imgButton.getImage();
        Image imagineButtonFinal = imagineButton.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        imgButton = new ImageIcon(imagineButtonFinal);

        imagine = new JLabel(imgButton);
        imagine.setSize(new Dimension(300, 300));
        imagine.setBounds(26, 14, 300, 300);
        add(imagine);

        informatii = new JTextArea(p.detaliiProdus());
        informatii.setFont(new Font("Monaco", Font.PLAIN, 17));
        informatii.setForeground(Color.WHITE);
        informatii.setSize(new Dimension(324, 154));
        informatii.setBounds(349, 14, 324, 154);
        informatii.setBackground(new Color(44, 116, 179));
        informatii.setOpaque(true);
        add(informatii);

        adaugaInCos = new JButton("Adauga in cos");
        adaugaInCos.setBackground(new Color (20, 66, 114));
        adaugaInCos.setOpaque(true);
        adaugaInCos.setForeground(Color.WHITE);
        adaugaInCos.setSize(new Dimension(232, 64));
        adaugaInCos.setFont(new Font("Monaco", Font.BOLD, 12));
        adaugaInCos.setBounds(395, 198, 232, 64);
        add(adaugaInCos);


        adaugaInCos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (c.getP1() == 0) {
                    JOptionPane.showMessageDialog(null, "Produs adaugat in cos.");
                    c.setP1(p.getId());
                    adauga(connection, p.getId(), "produs1",c.getId());
                }else{
                    if (c.getP2() == 0 && p.getId() != c.getP1()){
                        JOptionPane.showMessageDialog(null, "Produs adaugat in cos.");
                        c.setP2(p.getId());
                        adauga(connection, p.getId(), "produs2", c.getId());
                    }else{
                        if (c.getP3() == 0 && p.getId() != c.getP1() && p.getId() != c.getP2()){
                            JOptionPane.showMessageDialog(null, "Produs adaugat in cos.");
                            c.setP3(p.getId());
                            adauga(connection, p.getId(), "produs3", c.getId());
                        }else{
                            if (c.getP4() == 0 && p.getId() != c.getP1() && p.getId() != c.getP2() && p.getId() != c.getP3()) {
                                JOptionPane.showMessageDialog(null, "Produs adaugat in cos.");
                                c.setP4(p.getId());
                                adauga(connection, p.getId(), "produs4", c.getId());
                            }else{
                                if (c.getP5() == 0 && p.getId() != c.getP1() && p.getId() != c.getP2() && p.getId() != c.getP3() && p.getId() != c.getP4()) {
                                    JOptionPane.showMessageDialog(null, "Produs adaugat in cos.");
                                    c.setP5(p.getId());
                                    adauga(connection, p.getId(), "produs5", c.getId());
                                }else{
                                    if (c.getP5() != 0){
                                        JOptionPane.showMessageDialog(null, "Ai atins limita maxima de elemente in cos.");
                                    }
                                }
                            }
                        }
                    }
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
}
