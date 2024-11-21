import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
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
       String ciag=szyfr.get(2);
       char[] wiad=szyfr.get(1).toCharArray();
       char[] UpperCaseAlphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
       int liczba1=Integer.parseInt(ciag.substring(0,7),2);
       int liczba2=Integer.parseInt(ciag.substring(8,15),2);
       int liczba1_copy=liczba1;
       int liczba2_copy=liczba2;
       int liczba3=0;
       int k=Integer.parseInt(ciag.substring(16,23),2);
        String oper=szyfr.get(3);
       int liczba_przes_w_prawo=0;
        int liczba_przes_w_lewo=0;
        int liczba_symmetri=0;
        int liczba_reset=0;
        StringBuilder odk=new StringBuilder();

       char[]chars=oper.toCharArray();
       for (int i=0;i<wiad.length;i++){
           char ch=chars[i];
           char mes=wiad[i];
           liczba3=(liczba1+liczba2)%k;
           liczba1=liczba2;
           liczba2=liczba3;
           int originalAlphabetPosition=0;
           int newAlphabetPosition=0;
           char newCharacter;
           Wynik.add(String.valueOf(liczba3));
           switch (ch){
               case '\\':
                   liczba_przes_w_prawo++;
                   originalAlphabetPosition = mes - 'a';
                    newAlphabetPosition = (originalAlphabetPosition + liczba3) % 26;
                    newCharacter = (char) ('a' + newAlphabetPosition);
                   odk.append(newCharacter);
                   break;
               case'/':
                   liczba_przes_w_lewo++;
                    originalAlphabetPosition = mes - 'a';
                    newAlphabetPosition = (originalAlphabetPosition + (26 - (liczba3 % 26))) % 26;
                    newCharacter = (char) ('a' + newAlphabetPosition);
                   odk.append(newCharacter);
                   break;
               case '|':
                   liczba_symmetri++;
                   liczba_przes_w_lewo++;
                   originalAlphabetPosition = mes - 'a';
                   newAlphabetPosition = (originalAlphabetPosition + ( 90 - (liczba3-64  ))) % 26;
                   newCharacter = (char) ('a' + newAlphabetPosition);
                   odk.append(newCharacter);
                   break;
               case '-':
                   liczba_reset++;
                   liczba1=liczba1_copy;
                   liczba2=liczba2_copy;
                   break;
           }

       }
       Wynik.add(odk.toString());
       Wynik.add(liczba_przes_w_prawo+" liczba \\");
       Wynik.add(liczba_przes_w_lewo+" liczba /");
       Wynik.add(liczba_reset+" liczba |");
       Wynik.add(liczba_reset+" liczba -");
       return Wynik;
    }
}