public class HomeWork05 {
    public static void main(String[] args) {
        Person[] persArray = new Person[5]; // Вначале объявляем массив объектов
        persArray[0] = new Person("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", 964000002, 30000, 30);
        persArray[1] = new Person("Васильев Вася","директор","direktor@mail.ru",964000001,50000,45);
        persArray[2] = new Person("Станиславский Стас","уборщик","king@mail.ru",964000003,10000,55);
        persArray[3] = new Person("Аракумов Аркадий","охранник","BBB@mail.ru",964000004,15000,57);
        persArray[4] = new Person("Христофоров Кирилл","механие","kluch@mail.ru",964000005,40000,25);


        int age=50;
        System.out.println("\nсотрудники старше "+age+" лет\n");
        Person.ShowPersonUpAge(persArray,age);


        System.out.println("\nинформация о 5 сотруднике\n");
        persArray[4].Info();

    }
}