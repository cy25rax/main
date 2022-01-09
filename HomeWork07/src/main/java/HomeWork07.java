import java.util.Scanner;

public class HomeWork07 {

    public static void main(String[] args){
        Scanner scan=new Scanner(System.in);
        int feedQuantity;
        Cat[] cat = new Cat[10];
        Plate plate = new Plate(35);
        cat[0]=new Cat("Barsik", 5);
        cat[1]=new Cat("filya",10);
        cat[2]=new Cat("riziy",15);
        cat[3]=new Cat("poprigun",20);

        do {

            Cat.info(cat);              //выводит информацию о всех котах
            plate.info();               //выводит информацию о кол корма в тарелке
            System.out.println();
            System.out.println("введи -1 для выхода");
            System.out.println("введи количество корма для добавки в миску");
            feedQuantity=scan.nextInt();
            if (feedQuantity==-1) break;
            plate.setFood(feedQuantity);    //наполняет тарелку на х количества корма

            for (Cat cats: cat){            // кормит котов из миски
                if (cats!=null&& cats.isDeath()!=true) cats.eat(plate);
            }

            screen();                   //обновляет экран

        } while (true);

    }

    public static void screen(){
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
        System.out.println("прошло пол дня");
        System.out.println();
    }
}