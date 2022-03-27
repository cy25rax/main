import java.util.*;

public class HomeWork203 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("q","q","w","e","e","r","t","y","u","i","o",
                "p","q","w"));
        String[] arr = new String[list.size()];
        int[] count = new int[list.size()];
        list.toArray(arr);

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[j].equals(list.get(i))) {
                    count[j]++;
                    break;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (count[i]==0) continue;
            System.out.print(arr[i] + " "+count[i]);
            System.out.println();
        }


// 2я часть дз


        TelDirectory surnameTelephone = new TelDirectory();
        surnameTelephone.add("qwe",123);
        surnameTelephone.add("asd",456);
        surnameTelephone.add("asd",654);
        surnameTelephone.add("zxc",789);

        surnameTelephone.get("asd");

    }
}
