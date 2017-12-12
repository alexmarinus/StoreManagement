/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aL3XaNdRu10
 */
public interface IMagazin {
    double getTotalFaraTaxe();
    double getTotalCuTaxe();
    double getTotalCuTaxeScutite();
    double getTotalTaraFaraTaxe(String tara);
    double getTotalTaraCuTaxe(String tara);
    double getTotalTaraCuTaxeScutite(String tara);
    abstract double calculScutiriTaxe();
}
