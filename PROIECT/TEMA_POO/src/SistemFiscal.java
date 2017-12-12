import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
public class SistemFiscal {
    Gestiune g;
    //Metoda pentru cautarea unui obiect Produs cu denumire si tara date
    public static Produs CautaProdus(ArrayList<Produs> lp, String denumire, String tara) {
        int i=0;
        while (i<lp.size()) {
            if ((lp.get(i).getDenumire().equals(denumire)) && lp.get(i).getTaraOrigine().equals(tara))
                return lp.get(i);
            i++;
        }
        return null;
    }
    //Metoda pentru creare de obiect Magazin cu un anumit tip
    public static Magazin NouMagazin(String tip) {
        if (tip==null)
            return null;
        if (tip.equals("MiniMarket"))
            return new MiniMarket();
        if (tip.equals("MediumMarket"))
            return new MediumMarket();
        if (tip.equals("HyperMarket"))
            return new HyperMarket();
        return null;
    }
    public SistemFiscal(String cale1, String cale2, String cale3) throws FileNotFoundException, IOException {
        Comparator<String> comparator=new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        };
        Comparator<Magazin> comparator1=new Comparator<Magazin>() {
            @Override
            public int compare(Magazin m1, Magazin m2) {
                if (m2.getTip().compareTo(m1.getTip())==0)
                    if (m1.getTotalFaraTaxe()<m2.getTotalFaraTaxe())
                        return -1;
                    else
                        return 1;
                else
                    return m2.getTip().compareTo(m1.getTip());
            }
        };
        Comparator<Factura> comparator2=new Comparator<Factura>() {
            @Override
            public int compare(Factura f1, Factura f2) {
                if (f1.getTotalCuTaxe()<f2.getTotalCuTaxe())
                        return -1;
                return 1;
            }
        };
        int i,j,k;
        ArrayList<Produs> lp=new ArrayList<Produs>();
        ArrayList<String> tari, categorii; 
        Produs p=null; String[] info; ProdusComandat pc=null; 
        Magazin m = null; Factura f=null;
        //Parsare fisier produse
        FileReader fr=new FileReader(cale1);
        LineNumberReader lnr=new LineNumberReader(fr);
        String linie,buffer;
        tari=new ArrayList<String>();
        categorii=new ArrayList<String>();
        while ((linie=lnr.readLine())!=null)
            if (lnr.getLineNumber()==1) {
                info=linie.split(" ");
                for (i=2;i<info.length;i++)
                    tari.add(info[i]);
            }
            else {
                    info=linie.split(" ");
                    for (i=0;i<tari.size();i++) {
                        p=new Produs();
                        p.setDenumire(info[0]);
                        p.setCategorie(info[1]);
                        p.setTaraOrigine(tari.get(i));
                        p.setPret(Double.parseDouble(info[i+2]));
                        lp.add(p);
                    }
            }
        fr.close();
        lnr.close();
        g=Gestiune.getInstance();
        g.produse=lp;
        g.tari=tari;
        g.magazine=new ArrayList<Magazin>();
        g.taxe=new HashMap<String, HashMap>();
        categorii=new ArrayList<String>();
        //Parsare fisier taxe
        FileReader fr1=new FileReader(cale2);
        LineNumberReader lr1=new LineNumberReader(fr1);
        ArrayList<HashMap<String,Double>> al=new ArrayList<HashMap<String,Double>>();
        for (i=0;i<tari.size();i++)
            al.add(i,new HashMap<String,Double>());
        tari=new ArrayList<String>();
        while ((linie=lr1.readLine())!=null)
            if (lr1.getLineNumber()==1) {
                info=linie.split(" ");
                for (i=1;i<info.length;i++)
                    tari.add(info[i]);
            }
            else {
                info=linie.split(" ");
                categorii.add(info[0]);
                for (i=0;i<tari.size();i++)
                        al.get(i).put(info[0],Double.parseDouble(info[i+1]));
            }
        g.categorii=categorii;
        Collections.sort(g.categorii,comparator);
        fr1.close();
        lr1.close();
        for (i=0;i<tari.size();i++)
            g.taxe.put(tari.get(i),al.get(i));
        //Parsare fisier facturi
        FileReader fr2=new FileReader(cale3);
        LineNumberReader lr2=new LineNumberReader(fr2);
        int nrf=0,nrpc=0;
        int nrm=0;
        int nrl=0;
        while ((linie=lr2.readLine())!=null) {
            nrl=lr2.getLineNumber();
            if (linie.startsWith("Denumire")==false && linie.trim().isEmpty()==false) {
                if (linie.startsWith("Magazin")==true) {
                    nrm=nrl;
                    info=linie.split(":");
                    nrf=0;
                    if (nrl!=1)
                        g.magazine.add(m);
                    m=NouMagazin(info[1]);
                    m.setTip(info[1]);
                    m.setNume(info[2]);
                    m.v=new Vector<Factura>();
                }
                else if (linie.startsWith("Factura")==true) {
                    nrpc=0;
                    f=new Factura();
                    f.setDenumire(linie);
                    f.vpc=new Vector<ProdusComandat>();
                }
                else {
                    info=linie.split(" ");
                    pc=new ProdusComandat();
                    pc.setProdus(CautaProdus(lp,info[0],info[1]));
                    pc.setCantitate(Integer.parseInt(info[2]));
                    pc.setTaxa((double)g.taxe.get(info[1]).get(pc.getProdus().getCategorie()));
                    f.vpc.insertElementAt(pc,nrpc);
                    nrpc++;
                }
            }
            else if (linie.trim().isEmpty() && nrl!=2 && nrl!=nrm+1) {
                m.v.insertElementAt(f,nrf);
                nrf++;
                }        
        }
        m.v.insertElementAt(f,nrf);
        g.magazine.add(m);
        fr2.close();
        lr2.close();
        Collections.sort(g.tari,comparator);
        Collections.sort(g.magazine,comparator1);
        for (i=0;i<g.magazine.size();i++)
            Collections.sort(g.magazine.get(i).v,comparator2);
        //Creare fisier out.txt
        File file=new File("C:\\Users\\aL3XaNdRu10\\Desktop\\324CC_Marinus_Alexandru\\SURSE\\out.txt");
        file.createNewFile();
        FileWriter wr=new FileWriter(file);
        String tip="Tip";
        for (i=0;i<g.magazine.size();i++) {
            if (g.magazine.get(i).getTip().equals(tip)==false) {
                wr.write(g.magazine.get(i).getTip()+"\n");
                tip=g.magazine.get(i).getTip();
            }
            wr.write(g.magazine.get(i).getNume()+"\n\n");
            buffer="Total ";
            buffer=buffer+g.magazine.get(i).getTotalFaraTaxe()+" ";
            buffer=buffer+g.magazine.get(i).getTotalCuTaxe()+" ";
            buffer=buffer+g.magazine.get(i).getTotalCuTaxeScutite();
            wr.write(buffer+"\n\n");
            wr.write("Tara\n");
            for (j=0;j<g.tari.size();j++)
                if (g.magazine.get(i).getTotalTaraFaraTaxe(g.tari.get(j))==0)
                    wr.write(g.tari.get(j)+" 0\n");
                else {
                    buffer=g.tari.get(j)+" ";
                    buffer=buffer+g.magazine.get(i).getTotalTaraFaraTaxe(g.tari.get(j))+" ";
                    buffer=buffer+g.magazine.get(i).getTotalTaraCuTaxe(g.tari.get(j))+" ";
                    buffer=buffer+g.magazine.get(i).getTotalTaraCuTaxeScutite(g.tari.get(j));
                    wr.write(buffer+"\n");
                    }
            wr.write("\n");
            for (k=0;k<g.magazine.get(i).v.size();k++) {
                wr.write(g.magazine.get(i).v.elementAt(k).denumire+"\n\n");
                buffer="Total ";
                buffer=buffer+g.magazine.get(i).v.elementAt(k).getTotalFaraTaxe()+" ";
                buffer=buffer+g.magazine.get(i).v.elementAt(k).getTotalCuTaxe();
                wr.write(buffer+"\n\n");
                wr.write("Tara\n");
                for (j=0;j<g.tari.size();j++) {
                    buffer=g.tari.get(j)+" ";
                    if (g.magazine.get(i).v.elementAt(k).getTotalTaraFaraTaxe(g.tari.get(j))==0) {
                        if (j!=g.tari.size()-1 || i!=g.magazine.size()-1)
                            wr.write(g.tari.get(j)+" 0\n");
                        else
                            wr.write(g.tari.get(j)+" 0");
                    }
                    else {
                        buffer=buffer+g.magazine.get(i).v.elementAt(k).getTotalTaraFaraTaxe(g.tari.get(j))+" ";
                        buffer=buffer+g.magazine.get(i).v.elementAt(k).getTotalTaraCuTaxe(g.tari.get(j));
                        if (i!=g.magazine.size()-1 || j!=g.tari.size()-1)
                            wr.write(buffer+"\n");
                        else
                            wr.write(buffer);
                    }
                }
                if (i!=g.magazine.size()-1)
                    wr.write("\n");    
            }       
        }
        wr.flush();
        wr.close();
    }
    public static void main(String[] args) throws IOException {
        String cale1="C:\\Users\\aL3XaNdRu10\\Desktop\\324CC_Marinus_Alexandru\\SURSE\\produse.txt";
        String cale2="C:\\Users\\aL3XaNdRu10\\Desktop\\324CC_Marinus_Alexandru\\SURSE\\taxe.txt";
        String cale3="C:\\Users\\aL3XaNdRu10\\Desktop\\324CC_Marinus_Alexandru\\SURSE\\facturi.txt";
        SistemFiscal sf=new SistemFiscal(cale1,cale2,cale3);
    }
}
