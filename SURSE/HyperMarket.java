/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aL3XaNdRu10
 */
public class HyperMarket extends Magazin {
    static String Tip="HyperMarket";
    @Override
    public double calculScutiriTaxe() {
        int i;
        for (i=0;i<v.size();i++)
            if (v.elementAt(i).getTotalCuTaxe()>getTotalCuTaxe()/10)
                return 1;
        return 0;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
