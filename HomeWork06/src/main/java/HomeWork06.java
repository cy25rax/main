public class HomeWork06 {
    public static void main(String[] args) {

        Cat[] cat = new Cat[30];
        Dog[] dog = new Dog[20];

        dog[0]=new Dog("черныш");
        dog[1]=new Dog("белый");
        dog[2]=new Dog("краснуля");

        cat[0]=new Cat("барсик");
        cat[1]=new Cat("петя");

        cat[0].Run(199);
        cat[1].Swim(12);

        dog[0].Run(501);
        dog[1].Swim(9);

        System.out.println("Количество собак         "+Dog.getDogI());
        System.out.println("КОличество котов         "+Cat.getCatI());
        System.out.println("Количество всех животных "+Animal.getI());
        System.out.println();

    }

}