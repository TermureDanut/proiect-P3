import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class GUIRegister extends JFrame implements Serializable {

    private JLabel titlu;
    private Angajat angajat;
    private Client client;
    private JButton register;
    private JLabel nume, prenume, email, parola;
    private JTextField tfNume, tfPrenume, tfEmail;
    private JPasswordField tfParola;
    private JCheckBox bClient, bAngajat, arataParola;

    public GUIRegister(File clienti, File angajati){
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Register");
        setSize(new Dimension(500, 400));
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        titlu = new JLabel("REGISTER");
        titlu.setSize(new Dimension(100, 50));
        titlu.setFont(new Font("Monaco", Font.BOLD, 20));

        JPanel susStanga = new JPanel(new FlowLayout(FlowLayout.CENTER));
        susStanga.setSize(new Dimension(120, 70));
        susStanga.setBounds(10, 10, 130, 80);
        susStanga.add(titlu);
        add(susStanga);

        bClient = new JCheckBox("Register Client");
        bAngajat = new JCheckBox("Register Angajat");

        bClient.setSize(new Dimension(180, 20));
        bClient.setFont(new Font("Monaco", Font.BOLD, 12));
        bClient.setBounds(40, 50, 180, 20);

        bAngajat.setSize(new Dimension(180, 20));
        bAngajat.setFont(new Font("Monaco", Font.BOLD, 12));
        bAngajat.setBounds(40, 80, 180, 20);

        add(bClient);
        add(bAngajat);

        nume = new JLabel("Nume : ");
        nume.setSize(new Dimension(80, 20));
        nume.setFont(new Font("Monaco", Font.BOLD, 12));
        nume.setBounds(20, 100, 90, 30);

        prenume = new JLabel("Prenume : ");
        prenume.setSize(new Dimension(80, 20));
        prenume.setFont(new Font("Monaco", Font.BOLD, 12));
        prenume.setBounds(20, 150, 90, 30);

        email = new JLabel("Email : ");
        email.setSize(new Dimension(80, 20));
        email.setFont(new Font("Monaco", Font.BOLD, 12));
        email.setBounds(20, 200, 90, 30);

        parola = new JLabel("Parola : ");
        parola.setSize(new Dimension(80, 20));
        parola.setFont(new Font("Monaco", Font.BOLD, 12));
        parola.setBounds(20, 250, 90, 30);

        tfNume = new JTextField();
        tfNume.setPreferredSize(new Dimension(200, 20));
        tfNume.setFont(new Font("Monaco", Font.BOLD, 12));
        tfNume.setBounds(115, 110, 200, 20);

        tfPrenume = new JTextField();
        tfPrenume.setPreferredSize(new Dimension(200, 20));
        tfPrenume.setFont(new Font("Monaco", Font.BOLD, 12));
        tfPrenume.setBounds(115, 160, 200, 20);

        tfEmail = new JTextField();
        tfEmail.setPreferredSize(new Dimension(200, 20));
        tfEmail.setFont(new Font("Monaco", Font.BOLD, 12));
        tfEmail.setBounds(115, 210, 200, 20);

        tfParola = new JPasswordField();
        tfParola.setEchoChar('*');
        tfParola.setPreferredSize(new Dimension(200, 20));
        tfParola.setFont(new Font("Monaco", Font.BOLD, 12));
        tfParola.setBounds(115, 260, 200, 20);

        register = new JButton("REGISTER");
        register.setSize(new Dimension(100, 20));
        register.setFont(new Font("Monaco", Font.BOLD, 12));
        register.setBounds(150, 300, 100, 20);

        arataParola = new JCheckBox("Arata parola");
        arataParola.addActionListener(ae -> {
            JCheckBox c = (JCheckBox) ae.getSource();
            tfParola.setEchoChar(c.isSelected() ? '\u0000' : (Character)          UIManager.get("PasswordField.echoChar"));
        });
        arataParola.setBounds(20, 300, 100, 50);

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
                if (bClient.isSelected()){
                    String numeClient = tfNume.getText();
                    String prenumeClient = tfPrenume.getText();
                    String emailClient = tfEmail.getText();
                    String parolaClient = tfParola.getText();
                    client = new Client(numeClient,prenumeClient,emailClient,parolaClient);
                    try {
                        SerializareClenti(clienti);
                    } catch (IOException er) {
                        er.printStackTrace();
                    }
                }else{
                    String numeAngajat = tfNume.getText();
                    String prenumeAngajat = tfPrenume.getText();
                    String emailAngajat = tfEmail.getText();
                    String parolaAngajat = tfParola.getText();
                    angajat = new Angajat(numeAngajat, prenumeAngajat,emailAngajat,parolaAngajat);
                    try {
                        SerializareAngajati(angajati);
                    } catch (IOException er) {
                        er.printStackTrace();
                    }
                }


            }
        });

    }
    public void SerializareClenti(File clienti) throws IOException {
        FileOutputStream fos = new FileOutputStream(clienti);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(client);
        oos.close();
        fos.close();
    }

    public void SerializareAngajati(File angajati) throws IOException {
        FileOutputStream fos = new FileOutputStream(angajati);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(angajat);
        oos.close();
        fos.close();
    }



}
