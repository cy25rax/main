import java.util.ArrayList;

public class HomeWork301Generik {
    static ArrayList arrayList = new ArrayList();
    public static void main(String[] args) {

        Box<Apple> box1 = new Box<>();
        box1.add(new Apple(10));
        box1.add(new Apple(20));

        Box<Orange> box2 = new Box<>();
        box2.add(new Orange(20));
        box2.add(new Orange(10));

        Box<Apple> box3 = new Box<>();
        box3.add(new Apple(15));
        box3.add(new Apple(30));

        System.out.println("вес 1й коробки " + box1.getWeight());
        System.out.println("вес 2й коробки " + box2.getWeight());
        System.out.println("вес 3й коробки " + box3.getWeight());

        System.out.println("сравнение 3й и 2й коробки по массе " + box3.compare(box2));

        box1.join(box3);

        System.out.println("вес 1й коробки после объединения с 3й " + box1.getWeight());
        System.out.println("вес 3й коробки после объединения с 1й " + box3.getWeight());





        String[] strings = new String[] {"1","2","3","4"};
        arrayTolist(strings);

        change(arrayList, 2,3);

    }

    public static <T> void arrayTolist (T[] array){
        int i=0;
        for (T t : array) {
            arrayList.add(array[i]);
            i++;
        }
    }

    public static <T> void change(ArrayList<T> list, int a, int b) {
        ArrayList<T> tmp = new ArrayList<>();
        tmp.add(list.get(b));

        list.set(b,list.get(a));
        list.set(a, tmp.get(0));

        System.out.println(list);
    }
}
