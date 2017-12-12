Nume: Marinus Alexandru
Grupa: 324 CC
Grad de dificultate tema: 7/10
Timp alocat(ore): aprox. 30

In realizarea temei, am implementat fiecare clasa si interfata in fisiere separate.
Celor specificate in enunt li se adauga clasa SistemFiscal, responsabila
de efectuarea Task 1, si Aplicatie, responsabila de Task 2.
Pentru Task 1, am presupus caile absolute ale fisierelor de parsat ca fiind
cunsocute si calea absoluta a fisierului de iesire (out.txt) ca fiind stabilita 
in directorul SURSE al continutului prezentei arhive, dupa dezarhivarea ei.
In cadrul Task 1, obiectele de tip Magazin sunt create folosind Factory Pattern
in cadrul clasei Sistem Fiscal, iar obiectul Gestiune este implementat cu
Singleton Pattern. Clasei Gestiune i-am adaugat variabile membru listele de
String-uri tari si categorii pentru simplificarea implementarii statisticilor
necesare la Task 2.
In cadrul Task 2, fisierele facturi.txt, produse.txt si taxe.txt pot fi incarcate
de oriunde, fisierul rezultat avand calea mentionata anterior. Am realizat si
afisarea produselor ordonate alfabetic, respectiv dupa tara, dar si statisticile
sistemului fiscal. De specificat pentru acest task este si implementarea in cadrul
clasei Magazin a metodelor de calcul pentru cele 3 tipuri de total de vanzari ale
respectivului magazin pentru produse de o categorie data (cu taxe, fara taxe, cu
taxe scutite).