import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        String filepath="wiadomosc1.txt";
       ArrayList<String>szyfr=Download(filepath);
        ArrayList<String>wynik=zad1(szyfr);
        for(String i:wynik){
            System.out.println(i);
        }
    }
    public static ArrayList<String> Download(String filepath) {
        ArrayList<String> Wynik = new ArrayList<>();
        Wynik.add("");
        try {
            FileReader fileReader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                Wynik.add(line);
            }
            bufferedReader.close();
            return Wynik;

        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }
    public static ArrayList<String>zad1(ArrayList<String>szyfr){
       ArrayList<String>Wynik=new ArrayList<>();
       String ciag=szyfr.get(2);
       char[] wiad=szyfr.get(1).toCharArray();
       String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
       int liczba1=Integer.parseInt(ciag.substring(0,8),2);
       int liczba2=Integer.parseInt(ciag.substring(8,16),2);
       int liczba1_copy=liczba1;
       int liczba2_copy=liczba2;
       int liczba3=0;
       int k=Integer.parseInt(ciag.substring(16,24),2);
        String oper=szyfr.get(3);
       int liczba_przes_w_prawo=0;
        int liczba_przes_w_lewo=0;
        int liczba_symmetri=0;
        int liczba_reset=0;
        StringBuilder odk=new StringBuilder();

       char[]chars=oper.toCharArray();
       int i=0;
       int j=0;
        while(i<wiad.length&&j<chars.length){
       //for (int i=0;i<wiad.length;i++){
           char ch=chars[j];
           char mes=wiad[i];
          if (i==0) {
              liczba3=liczba1;
         }
         else if(i==1){
              liczba3=liczba2;
        }
         else {
              liczba3 = (liczba1 + liczba2) % k;
              liczba1 = liczba2;
              liczba2 = liczba3;

          }
          Wynik.add(String.valueOf(liczba3));
           int pos=alphabet.indexOf(mes);
           switch (ch){
               case '\\':
                   liczba_przes_w_lewo++;
                   int decryptPos = (pos - liczba3)%26;
                   if (decryptPos < 0){
                       decryptPos += alphabet.length();
                   }
                   char decryptChar = alphabet.charAt(decryptPos);
                   odk.append(decryptChar);
                   i++;
                   j++;
                   break;
               case'/':
                   liczba_przes_w_prawo++;
                   int encpos=(liczba3+pos)%26;
                   char ench=alphabet.charAt(encpos);
                   odk.append(ench);
                   i++;
                   j++;
                   break;
               case '|':
                   liczba_symmetri++;
                   int sympos=25-pos;
                   char symchar=alphabet.charAt(sympos);
                   odk.append(symchar);
                   i++;
                   j++;
                   break;
               case '-':
                   liczba_reset++;
                   liczba1=liczba1_copy;
                   liczba2=liczba2_copy;
                   j++;
                   break;
           }

       }
       Wynik.add(odk.toString());
       Wynik.add(liczba_przes_w_lewo+" liczba \\");
       Wynik.add(liczba_przes_w_prawo+" liczba /");
       Wynik.add(liczba_symmetri+" liczba |");
       Wynik.add(liczba_reset+" liczba -");
       return Wynik;
    }
}