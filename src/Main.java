import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        String filepath="wiadomosc.txt";
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
        String oper=szyfr.get(3);
       int liczba_przes_w_prawo=0;
        int liczba_przes_w_lewo=0;
        int liczba_symmetri=0;
        int liczba_reset=0;

       char[]chars=oper.toCharArray();
       for (char i:chars){
           switch (i){
               case '\\':
                   liczba_przes_w_prawo++;
                   break;
               case'/':
                   liczba_przes_w_lewo++;
                   break;
               case '|':
                   liczba_symmetri++;
                   break;
               case '-':
                   liczba_reset++;
                   break;
           }
       }
       Wynik.add(liczba_przes_w_prawo+" liczba \\");
       Wynik.add(liczba_przes_w_lewo+" liczba /");
       Wynik.add(liczba_reset+" liczba |");
       Wynik.add(liczba_reset+" liczba -");
       return Wynik;
    }
}