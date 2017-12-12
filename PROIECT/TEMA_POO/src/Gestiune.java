import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
public class Gestiune {
    private static Gestiune instance=new Gestiune();
    public ArrayList<Produs> produse;
    public ArrayList<Magazin> magazine;
    public ArrayList<String> tari;
    public ArrayList<String> categorii;
    public HashMap <String, HashMap> taxe;
    private Gestiune() {}
    public static Gestiune getInstance() {
        return instance;
    }
    @Override
    public String toString() {
        String s="Gestiune LISTA PRODUSE: ";
        int i;
        for (Iterator<Produs> it=produse.iterator();it.hasNext();)
            s=s+it.next().toString()+" ";
        s=s+"LISTA MAGAZINE: ";
        for (Iterator<Magazin> it1=magazine.iterator();it1.hasNext();)
            s=s+it1.next().toString()+" ";
        s=s+"DICTIONAR TAXE: "+taxe.toString();
        return s;
    }
}
