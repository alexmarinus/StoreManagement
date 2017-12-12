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
public class MiniMarket extends Magazin {
    static String Tip="MiniMarket";
    @Override
    public double calculScutiriTaxe() {
        int i,j; 
        for (i=0;i<v.size();i++)
            for (j=0;j<v.elementAt(i).vpc.size();j++)
                if (getTotalTaraCuTaxe(v.elementAt(i).vpc.elementAt(j).getProdus().getTaraOrigine())>getTotalCuTaxe()/2)
                    return 10;
        return 0;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}