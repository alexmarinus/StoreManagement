/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aL3XaNdRu10
 */
public class Produs {
    private String denumire;
    private String categorie;
    private String taraOrigine;
    private double pret;
    public void setDenumire(String s) {
        denumire=s;
    }
    public String getDenumire() {
        return denumire;
    }
    public void setCategorie(String s) {
        categorie=s;
    }
    public String getCategorie() {
        return categorie;
    }
    public void setTaraOrigine(String s) {
        taraOrigine=s;
    }
    public String getTaraOrigine() {
        return taraOrigine;
    }
    public void setPret(double p) {
        pret=p;
    }
    public double getPret() {
        return pret;
    }
    public String toString() {
        return denumire+" "+categorie+" "+taraOrigine+" "+pret;
    }
} 