package Produse;

public class Produs {
    private int id;
    private boolean discount;
    private String categorie;
    private int nrComenzi;
    private String marca;
    private double pret;
    private String culoare, imagine;

    public Produs(int id, String categorie, int nrComenzi, String marca, double pret, String culoare, String imagine){
        this.id = id;
        this.categorie = categorie;
        this.nrComenzi = nrComenzi;
        this.marca = marca;
        this.pret = pret;
        this.culoare = culoare;
        this.imagine = imagine;
    }
    public void setDiscount1(boolean discount){
        this.discount = discount;
    }
    public void setDiscount(){
        if (this.nrComenzi < 5){
            this.discount = true;
        }
    }

    public void setPretDiscount(int procent){
        if (this.discount == true){
            int s = (int) ((procent * this.pret) / 100);
            this.pret = this.pret - s;
        }
    }

    public void setNrComenzi(int n){
        this.nrComenzi = n;
    }

    public String toString() {
        return marca + ": categorie: " + categorie + "\nculoare: " + culoare + ", pret: " + pret + " lei";
    }

    public String toStringAdmin(){
        return "ID: " + id + ", categorie: " + categorie + ", marca: " + marca + ", pret: " + pret + " lei"
                + ",\n numar comenzi: " + nrComenzi + ", discount: " + discount;
    }

    public String detaliiProdus(){
        return marca + "\nCategorie: " + categorie + "\nCuloare: " + culoare + "\nPret: " + pret + " lei";
    }

    public int getId() {
        return id;
    }

    public boolean isDiscount() {
        return discount;
    }

    public String getCategorie() {
        return categorie;
    }

    public int getNrComenzi() {
        return nrComenzi;
    }

    public String getMarca() {
        return marca;
    }

    public double getPret() {
        return pret;
    }

    public String getCuloare() {
        return culoare;
    }

    public String getImagine() {
        return imagine;
    }
}
