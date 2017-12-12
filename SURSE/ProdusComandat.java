/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aL3XaNdRu10
 */
public class ProdusComandat {
    private Produs produs;
    private double taxa;
    private int cantitate;
    public void setProdus (Produs p) {
        produs=p;
    }
    public Produs getProdus() {
        return produs;
    }
    public void setTaxa(double t) {
        taxa=t;
    }
    public double getTaxa () {
        return taxa;
    }
    public void setCantitate (int ct) {
        cantitate=ct;
    }
    public int getCantitate () {
        return cantitate;
    }
    @Override
    public String toString() {
        return getProdus().toString()+" "+getTaxa()+" "+getCantitate();
    }
}
