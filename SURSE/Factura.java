import java.math.BigDecimal;
import java.util.Vector;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aL3XaNdRu10
 */
public class Factura {
    public String denumire;
    public Vector<ProdusComandat> vpc;
    public void setDenumire(String s) {
        denumire=s;
    }
    public String getDenumire() {
        return denumire;
    }
    public double getTotalFaraTaxe() {
        int i; double tft=0;
        for (i=0;i<vpc.size();i++)
            tft=tft+vpc.elementAt(i).getProdus().getPret()*vpc.elementAt(i).getCantitate();
        BigDecimal bd = new BigDecimal(Double.toString(tft));
        bd = bd.setScale(3, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
    public double getTotalCuTaxe() {
        int i; double tct=0;
        for (i=0;i<vpc.size();i++)
            tct=tct+vpc.elementAt(i).getProdus().getPret()*vpc.elementAt(i).getCantitate()*(1+vpc.elementAt(i).getTaxa()/100);
        BigDecimal bd = new BigDecimal(Double.toString(tct));
        bd = bd.setScale(3, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
    public double getTaxe() {
        return getTotalCuTaxe()-getTotalFaraTaxe();
    }
    public double getTotalTaraFaraTaxe(String tara) {
        int i; double tft=0;
        for (i=0;i<vpc.size();i++)
            if (vpc.get(i).getProdus().getTaraOrigine().equals(tara))
                tft=tft+vpc.elementAt(i).getProdus().getPret()*vpc.elementAt(i).getCantitate();
        BigDecimal bd = new BigDecimal(Double.toString(tft));
        bd = bd.setScale(3, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
    public double getTotalTaraCuTaxe(String tara) {
        int i; double tct=0;
        for (i=0;i<vpc.size();i++)
            if (vpc.get(i).getProdus().getTaraOrigine().equals(tara))
                tct=tct+vpc.elementAt(i).getProdus().getPret()*vpc.elementAt(i).getCantitate()*(1+vpc.elementAt(i).getTaxa()/100);
        BigDecimal bd = new BigDecimal(Double.toString(tct));
        bd = bd.setScale(3, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
    public double getTaxeTara(String tara) {
        return getTotalTaraCuTaxe(tara)-getTotalTaraFaraTaxe(tara);
    }
    @Override
    public String toString() {
        String s=getDenumire()+" : ";
        int i;
        for (i=0;i<vpc.size();i++) {
            s=s+vpc.elementAt(i).toString();
            if (i!=vpc.size()-1)
                s=s+" ; ";
        }
        return s;
    }
}
