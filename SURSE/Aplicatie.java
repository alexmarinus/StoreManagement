import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aL3XaNdRu10
 */
public class Aplicatie implements ActionListener {
    private JFrame start,link1,link2,link3;
    private JButton buton1,buton2,buton3,buton4;
    private Gestiune g;
    private JList lproduse;
    private JScrollPane jsp;
    private Vector v;
    Comparator<Produs> compalf=new Comparator<Produs>() {
        @Override
        public int compare(Produs p1, Produs p2) {
            return p1.getDenumire().compareTo(p2.getDenumire());
        }    
    };
    Comparator<Produs> comptara=new Comparator<Produs>() {
        @Override
        public int compare(Produs p1, Produs p2) {
            return p1.getTaraOrigine().compareTo(p2.getTaraOrigine());
        }
    };
    public static Magazin MaximVanzariTotale(ArrayList<Magazin> magazine) {
        int i; double max=0; Magazin m=null;
        for (i=0;i<magazine.size();i++)
            if (magazine.get(i).getTotalCuTaxe()>max) {
                max=magazine.get(i).getTotalCuTaxe();
                m=magazine.get(i);
            }
        return m;
    }
    public static Magazin MaximVanzariTara(ArrayList<Magazin> magazine, String tara) {
        int i; double max=0; Magazin m=null;
        for (i=0;i<magazine.size();i++)
            if (magazine.get(i).getTotalTaraCuTaxe(tara)>max) {
                max=magazine.get(i).getTotalTaraCuTaxe(tara);
                m=magazine.get(i);
            }
        return m;
    }
    public static Magazin MaximVanzariCategorie(ArrayList<Magazin> magazine, String categorie) {
        int i,j,k,cantitate; double max=0,vanz,pret,taxa; Magazin m=null;
        for (i=0;i<magazine.size();i++) {
            vanz=0;
            for (j=0;j<magazine.get(i).v.size();j++)
                for (k=0;k<magazine.get(i).v.elementAt(j).vpc.size();k++)
                    if (magazine.get(i).v.elementAt(j).vpc.elementAt(k).getProdus().getCategorie().equals(categorie)) {
                        pret=magazine.get(i).v.elementAt(j).vpc.elementAt(k).getProdus().getPret();
                        cantitate=magazine.get(i).v.elementAt(j).vpc.elementAt(k).getCantitate();
                        taxa=magazine.get(i).v.elementAt(j).vpc.elementAt(k).getTaxa();
                        vanz=vanz+cantitate*pret*(1+taxa/100);
                    }
            if (vanz>max) {
                max=vanz;
                m=magazine.get(i);
            }
        }
        return m;    
    }
    public static Factura FacturaSumaMaxFaraTaxe(ArrayList<Magazin> magazine) {
        int i,j; double max=0; Factura f=null;
        for (i=0;i<magazine.size();i++)
            for (j=0;j<magazine.get(i).v.size();j++)
                if (magazine.get(i).v.elementAt(j).getTotalFaraTaxe()>max) {
                    max=magazine.get(i).v.elementAt(j).getTotalFaraTaxe();
                    f=magazine.get(i).v.elementAt(j);
                }
        return f;
    }
    public Aplicatie() {
        g=Gestiune.getInstance();
        start=new JFrame("Sistem de facturi fiscale");
        start.setDefaultCloseOperation(EXIT_ON_CLOSE);
        start.setSize(400,400);
        start.setLayout(null);
        start.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }  
        });
        start.setVisible(true);
        buton1=new JButton("Incarcare fisiere");
        buton1.setBounds(100,100,200,18);
        start.add(buton1);
        buton2=new JButton("Administrare produse");
        buton2.setBounds(100,150,200,18);
        start.add(buton2);
        buton3=new JButton("Statistici");
        buton3.setBounds(100,200,200,18);
        start.add(buton3);
        buton4=new JButton("Iesire");
        buton4.setBounds(100,250,200,18);
        start.add(buton4);
        //Pagina Incarcare fisiere
        link1=new JFrame("Incarcare fisiere");
        JLabel et1,et2,et3;
        JTextField text1,text2,text3,text4;
        JButton b1,b2,b3,b4,b5;
        JFileChooser jc;
        link1.setSize(850,300);
        link1.setLayout(null);
        et1=new JLabel("Fisier produse");
        et1.setBounds(33,34,100,18);        
        text1=new JTextField(50);
        text1.setBounds(147,34,350,18);
        text1.setEditable(true);
        et2=new JLabel("Fisier taxe");
        et2.setBounds(33,75,100,18);
        text2=new JTextField(15);
        text2.setBounds(147,75,350,18);
        text2.setEditable(true);
        et3=new JLabel("Fisier facturi");
        et3.setBounds(33,116,100,18);        
        text3=new JTextField(50);
        text3.setBounds(147,113,350,18);        
        text3.setEditable(true);
        link1.add(et1);
        link1.add(text1);
        b1=new JButton("Cautare 1");
        b1.setBounds(500,34,100,18);
        link1.add(b1);
        link1.add(et2);
        link1.add(text2);
        b2=new JButton("Cautare 2");
        b2.setBounds(500,75,100,18);
        link1.add(b2);
        link1.add(et3);
        link1.add(text3);
        b3=new JButton("Cautare 3");
        b3.setBounds(500,113,100,18);
        link1.add(b3);
        b4=new JButton("Creare output");
        b4.setBounds(610,55,200,18);
        link1.add(b4);
        b5=new JButton("Revenire");
        b5.setBounds(610,92,200,18);
        link1.add(b5);
        b1.addActionListener(new ActionListener() {    
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser jc=new JFileChooser();
                int alegere=jc.showOpenDialog(link1);
                if (alegere==JFileChooser.APPROVE_OPTION) {
                    File fis=jc.getSelectedFile();
                    if (fis.getName().equals("produse.txt")==false) {
                        JOptionPane.showMessageDialog(link1,"Fisier invalid!","Eroare",JOptionPane.ERROR_MESSAGE);
                        text1.setText("");
                    }
                    else
                        text1.setText(fis.getPath());
                }
                else
                    ;
            }
        });
        b2.addActionListener(new ActionListener() {    
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser jc=new JFileChooser();
                int alegere=jc.showOpenDialog(link1);
                if (alegere==JFileChooser.APPROVE_OPTION) {
                    File fis=jc.getSelectedFile();
                    if (fis.getName().equals("taxe.txt")==false) {
                        JOptionPane.showMessageDialog(link1,"Fisier invalid!","Eroare",JOptionPane.ERROR_MESSAGE);
                        text2.setText("");
                    }
                    else
                        text2.setText(fis.getPath());
                }
                else
                    ;
            }
        });
        b3.addActionListener(new ActionListener() {    
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser jc=new JFileChooser();
                int alegere=jc.showOpenDialog(link1);
                if (alegere==JFileChooser.APPROVE_OPTION) {
                    File fis=jc.getSelectedFile();
                    if (fis.getName().equals("facturi.txt")==false) {
                        JOptionPane.showMessageDialog(link1,"Fisier invalid!","Eroare",JOptionPane.ERROR_MESSAGE);
                        text3.setText("");
                    }
                    else
                        text3.setText(fis.getPath());
                }
                else
                    ;
            }
        });
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String cale1=text1.getText(),cale2=text2.getText(),cale3=text3.getText();
                if (cale1==null || cale2==null || cale3==null)
                    JOptionPane.showMessageDialog(link1,"Insuficiente fisiere introduse!","Eroare",JOptionPane.ERROR_MESSAGE);
                else {
                    try {
                        SistemFiscal sf=new SistemFiscal(cale1,cale2,cale3);
                        g=sf.g;
                        JOptionPane.showMessageDialog(link1,"Outputul a fost creat cu succes!","Succes",JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(link1,"Insuficiente fisiere introduse sau destinatie implicita inexistenta!","Eroare",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                link1.dispose();
            }            
        });
        buton1.addActionListener(this);
        buton2.addActionListener(this);
        buton3.addActionListener(this);
        buton4.addActionListener(this);
        //Pagina Administrare produse
        link2=new JFrame("Administrare produse");
        link2.setSize(650,300);
        link2.setLayout(null);
        v=new Vector(100);
        lproduse=new JList(v);
        jsp=new JScrollPane(lproduse);        
        jsp.setBounds(370,60,200,300);
        jsp.setVisible(false);
        link2.add(jsp);
        JButton bt1=new JButton("Afisare alfabetica");
        bt1.setBounds(60,60,300,18);
        bt1.addActionListener(new ActionListener() {    
            @Override
            public void actionPerformed(ActionEvent ae) {                
                if (v!=null)
                    v=new Vector(100);
                Collections.sort(g.produse,compalf);               
                v=new Vector(g.produse);
                lproduse.setListData(v);
                jsp.add(lproduse);
                jsp.setVisible(true);
            }
        });
        JButton bt2=new JButton("Afisare dupa tara");
        bt2.setBounds(60,100,300,18);
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (v!=null)
                    v=new Vector(100);
                Collections.sort(g.produse,comptara);               
                v=new Vector(g.produse);
                lproduse.setListData(v);
                jsp.add(lproduse);
                jsp.setVisible(true);
            }            
        });
        JButton bt3=new JButton("Revenire");
        bt3.setBounds(60,140,300,18);
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                link2.dispose();
            }
        });
        link2.add(bt1);
        link2.add(bt2);
        link2.add(bt3);
        //Pagina Statistici
        link3=new JFrame("Statistici");
        link3.setBackground(Color.lightGray);
        link3.setSize(750,650);
        link3.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton x=(JButton)ae.getSource();
        if (x.getText().equals("Incarcare fisiere"))            
            link1.setVisible(true);
        else if (x.getText().equals("Administrare produse")) {
            if (g.produse==null)
                JOptionPane.showMessageDialog(start,"Nu au fost incarcate fisiere!","Eroare",JOptionPane.ERROR_MESSAGE);
            else
                link2.setVisible(true);
        }
        else if (x.getText().equals("Statistici")) {
            if (g.magazine==null)
                JOptionPane.showMessageDialog(start,"Nu au fost incarcate fisiere!","Eroare",JOptionPane.ERROR_MESSAGE);
            else
                {
                    int i,j;
                    String stats="";
                    Magazin m=MaximVanzariTotale(g.magazine);
                    Factura f=FacturaSumaMaxFaraTaxe(g.magazine);
                    stats=stats+"Magazinul cu cele mai mari vanzari este "+m.getNume()+".\n";
                    stats=stats+"\tTVFT : "+m.getTotalFaraTaxe()+"  TVCT : "+m.getTotalCuTaxe()+"  TVCTS : "+m.getTotalCuTaxeScutite()+"\n";
                    for (i=0;i<g.tari.size();i++) {
                        m=MaximVanzariTara(g.magazine, g.tari.get(i));
                        stats=stats+"Magazinul cu cele mai mari vanzari pentru tara "+g.tari.get(i)+" este "+m.getNume()+".\n";
                        stats=stats+"\tTVFT : "+m.getTotalTaraFaraTaxe(g.tari.get(i))+"  TVCT : "+m.getTotalTaraCuTaxe(g.tari.get(i))+"  TVCTS : "+m.getTotalTaraCuTaxeScutite(g.tari.get(i))+"\n";
                    }
                    for (i=0;i<g.categorii.size();i++) {
                        m=MaximVanzariCategorie(g.magazine,g.categorii.get(i));
                        stats=stats+"Magazinul cu cele mai mari vanzari pentru categoria "+g.categorii.get(i)+" este "+m.getNume()+".\n";
                        stats=stats+"\tTVFT : "+m.getTotalCategorieFaraTaxe(g.categorii.get(i))+"  TVCT : "+m.getTotalCategorieCuTaxe(g.categorii.get(i))+"  TVCTS : "+m.getTotalCategorieCuTaxeScutite(g.categorii.get(i))+"\n";
                    }
                    stats=stats+"Factura cu suma maxima este : \n";
                    for(i=0;i<f.vpc.size();i++)
                        stats=stats+"\t"+f.vpc.elementAt(i).getProdus().getDenumire()+"  "+f.vpc.elementAt(i).getProdus().getTaraOrigine()+" "+f.vpc.elementAt(i).getCantitate()+"\n";
                    JTextArea st=new JTextArea(stats);
                    st.setBounds(50,50,550,450);
                    st.setBackground(Color.lightGray);
                    st.setEditable(false);
                    Font font=new Font("Times New Roman",Font.BOLD,14);
                    st.setForeground(Color.red);
                    st.setFont(font);
                    link3.add(st);
                    JButton bt=new JButton("Revenire");
                    bt.setBounds(620,200,100,18);
                    bt.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            link3.dispose();
                        }                        
                    });
                    link3.add(bt);
                    link3.setVisible(true);
                }
        }
        else
            System.exit(0);
    }
    public static void main(String args[]) {
        new Aplicatie();
    }    
}
