public class HomeWork201 {
    public static int participants = 3; //колво участников
    public static Treadmill[] treadmills = new Treadmill[participants*3]; // беговая дорожка
    public static Wall[] walls = new Wall[participants*3];  // стена
    public static String[] hindrance = new String[participants*3]; // массив препятствий

    public static void main(String[] args) {

        Team[] cats = new Cat[participants];
        Team[] humans = new Human[participants];
        Team[] robots = new Robot[participants];

        hindranceFill(); // создание массива препятсивий

        hindranceShow(); // вывод сгенерированных препятствий на экран

        cats[0] = new Cat("барсик", 11,11);
        cats[1] = new Cat("черныш", 8,2);
        cats[2] = new Cat("злыдень", 7,3);

        humans[0] = new Human( "Петр",6,4);
        humans[1] = new Human( "Федя",5,5);
        humans[2] = new Human( "Петр",4,6);

        robots[0] = new Robot("101",3,7);
        robots[1] = new Robot("111",2,8);
        robots[2] = new Robot("110",1,9);


        for (int i = 0; i < participants; i++) {

            humans[i].info();
            chek(i,humans);



            cats[i].info();
            chek(i,cats);


            robots[i].info();
            chek(i,robots);

        }

    }

    public static void hindranceFill(){
        int random;
        int random2;

        for (int i = 0; i < participants*3; i++) {

            random = (int) Math.round(Math.random());
            random2 = (int) (Math.random()*10+1);
            switch (random) {
                case 0:
                    hindrance[i]="run";
                    treadmills[i] = new Treadmill(random2);
                    break;
                case 1:
                    hindrance[i] = "jump";
                    walls[i] = new Wall(random2);
                    break;
            }
        }
    }

    public static void hindranceShow(){
        System.out.println("список препятствий и их сложность");
        for (int i = 0; i < participants*3; i++) {
            System.out.print(hindrance[i]+" ");
        }
        System.out.println();
        for (int i = 0; i < participants*3; i++) {
            if (hindrance[i].equals("run")) {
                System.out.print(treadmills[i].getTreadmillLength()+"\t ");
            } else {
                System.out.print(walls[i].getWallLength()+"\t ");
            }
        }
        System.out.println();
    }

    public static void chek (int i,Team[] teams) {
        System.out.println();
        for (int j = 0; j < hindrance.length; j++) {
            if (hindrance[j].equals("run")) {
                if (treadmills[j].getTreadmillLength() <= teams[i].getRunRange()) {
                    System.out.print("пробежал " + (j+1) + " препятствие ");
                } else {
                    System.out.println();
                    System.out.println("препятствие "+(j+1)+" слишком большое");
                    break;
                }
            } else if (hindrance[j].equals("jump")) {
                if (walls[j].getWallLength() <= teams[i].getJumpRange()) {
                    System.out.print("прыгнул " +(j+1)+ " препятствие ");
                } else {
                    System.out.println();
                    System.out.println("препятствие "+(j+1)+" слишком большое");
                    break;
                }                }
            if (j== hindrance.length-1) {
                System.out.println();
                System.out.println("все препятствия пройдены");
            }
        }
    }



}
