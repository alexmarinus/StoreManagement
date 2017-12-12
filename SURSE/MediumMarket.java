import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aL3XaNdRu10
 */
public class MediumMarket extends Magazin{
    static String Tip="MediumMarket";
    @Override
    public double calculScutiriTaxe() {
        int i,j,k; ArrayList<String> categorii;
        double tvc;
        categorii=new ArrayList<String>();
        for (i=0;i<v.size();i++)
            for (j=0;j<v.elementAt(i).vpc.size();j++)
                if (categorii.contains(v.elementAt(i).vpc.elementAt(j).getProdus().getCategorie())==false)
                    categorii.add(v.elementAt(i).vpc.elementAt(j).getProdus().getCategorie());
        for (i=0;i<categorii.size();i++) {
            tvc=0;
            for (j=0;j<v.size();j++)
                for (k=0;k<v.elementAt(j).vpc.size();k++)
                    if (v.elementAt(j).vpc.elementAt(k).getProdus().getCategorie().equals(categorii.get(i)))
                        tvc=tvc+v.elementAt(j).vpc.elementAt(k).getCantitate()*v.elementAt(j).vpc.elementAt(k).getProdus().getPret()*(1+v.elementAt(j).vpc.elementAt(k).getTaxa());
            if (tvc>getTotalCuTaxe()/2)
                return 5;
        }
        return 0;            
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
