import java.util.ArrayList;
import java.util.List;

public class TelDirectory{

    private static List<String> surname1 = new ArrayList<>();
    private static List<Integer> tel1 = new ArrayList<>();

    public TelDirectory(){

    };

    public static void add(String surna, Integer te){
        surname1.add(surna);
        tel1.add(te);
    }

    public void get(String str) {

        for (int i = 0; i < surname1.size(); i++) {
            if (str.equals(surname1.get(i))) {
                System.out.println(tel1.get(i));
            }
        }

    }

}
