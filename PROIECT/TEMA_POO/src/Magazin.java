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
abstract class Magazin implements IMagazin {
    public String nume;
    public String tip;
    Vector<Factura> v;
    public void setNume(String s) {
        nume=s;
    }
    public String getNume() {
        return nume;
    }
    public void setTip(String s) {
        tip=s;
    }
    public String getTip() {
        return tip;
    }
    @Override
    public double getTotalFaraTaxe() {
        int i; double tft=0;
        for (i=0;i<v.size();i++)
           tft=tft+v.elementAt(i).getTotalFaraTaxe();
        BigDecimal bd = new BigDecimal(Double.toString(tft));
        bd = bd.setScale(4, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();       
    }
    @Override
    public double getTotalCuTaxe() {
        int i; double tct=0;
        for (i=0;i<v.size();i++)
            tct=tct+v.elementAt(i).getTotalCuTaxe();
        BigDecimal bd = new BigDecimal(Double.toString(tct));
        bd = bd.setScale(4, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
    @Override
    public double getTotalCuTaxeScutite() {
        BigDecimal bd = new BigDecimal(Double.toString(getTotalCuTaxe()*(1-calculScutiriTaxe()/100)));
        bd = bd.setScale(4, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
    @Override
    public double getTotalTaraFaraTaxe(String tara) {
        int i; double tft=0;
        for (i=0;i<v.size();i++)
            tft=tft+v.elementAt(i).getTotalTaraFaraTaxe(tara);
        BigDecimal bd = new BigDecimal(Double.toString(tft));
        bd = bd.setScale(4, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
    @Override
    public double getTotalTaraCuTaxe(String tara) {
        int i; double tct=0;
        for (i=0;i<v.size();i++)
            tct=tct+v.elementAt(i).getTotalTaraCuTaxe(tara);
        BigDecimal bd = new BigDecimal(Double.toString(tct));
        bd = bd.setScale(4, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
    @Override
    public double getTotalTaraCuTaxeScutite(String tara) {
        BigDecimal bd = new BigDecimal(Double.toString(getTotalTaraCuTaxe(tara)*(1-calculScutiriTaxe()/100)));
        bd = bd.setScale(4, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
    public double getTotalCategorieFaraTaxe(String categorie){
        int i,j,cantitate; double pret,tft=0;
        for (i=0;i<v.size();i++)
            for (j=0;j<v.elementAt(i).vpc.size();j++)
                if (v.elementAt(i).vpc.elementAt(j).getProdus().getCategorie().equals(categorie)) {
                    cantitate=v.elementAt(i).vpc.elementAt(j).getCantitate();
                    pret=v.elementAt(i).vpc.elementAt(j).getProdus().getPret();
                    tft=tft+cantitate*pret;
                }
        BigDecimal bd = new BigDecimal(Double.toString(tft));
        bd = bd.setScale(4, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();            
    }
    public double getTotalCategorieCuTaxe(String categorie){
        int i,j,cantitate; double pret,taxa,tct=0;
        for (i=0;i<v.size();i++)
            for (j=0;j<v.elementAt(i).vpc.size();j++)
                if (v.elementAt(i).vpc.elementAt(j).getProdus().getCategorie().equals(categorie)) {
                    cantitate=v.elementAt(i).vpc.elementAt(j).getCantitate();
                    pret=v.elementAt(i).vpc.elementAt(j).getProdus().getPret();
                    taxa=v.elementAt(i).vpc.elementAt(j).getTaxa();
                    tct=tct+cantitate*pret*(1+taxa/100);
                }
        BigDecimal bd = new BigDecimal(Double.toString(tct));
        bd = bd.setScale(4, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();            
    }
    public double getTotalCategorieCuTaxeScutite(String categorie) {
        BigDecimal bd = new BigDecimal(Double.toString(getTotalCategorieCuTaxe(categorie)*(1-calculScutiriTaxe()/100)));
        bd = bd.setScale(4, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
    @Override
    public String toString() {
        String s=nume+" "+tip+" ";
        int i;
        for (i=0;i<v.size();i++)
            s=s+v.get(i).toString()+"  ";
        return s;
    }
}
