package LoginRelated;
import Produse.Produs;
import java.util.List;

public class Client{
    int id;
    private String nume, prenume, email, parola, adresa, telefon;
    private int p1, p2, p3, p4, p5;

    public Client (int id, String nume, String prenume, String email, String parola, String adresa, String telefon){
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.parola = parola;
        this.adresa = adresa;
        this.telefon = telefon;
    }

    public String toString (){
        return "Nume : " + nume + " , prenume : " + prenume + " , email : " + email + " , parola : " + parola;
    }

    public int getId(){
        return id;
    }
    public String getEmail(){
        return this.email;
    }
    public String getParola(){
        return this.parola;
    }
    public String getAdresa(){return this.adresa;}

    public void setP1(int p1) {
        this.p1 = p1;
    }
    public void setP2(int p2) {
        this.p2 = p2;
    }
    public void setP3(int p3) {
        this.p3 = p3;
    }
    public void setP4(int p4){ this.p4 = p4;}
    public void setP5(int p5) {
        this.p5 = p5;
    }

    public int getP1() {
        return p1;
    }
    public int getP2() {
        return p2;
    }
    public int getP3() {
        return p3;
    }
    public int getP4() {
        return p4;
    }
    public int getP5() {
        return p5;
    }
}
