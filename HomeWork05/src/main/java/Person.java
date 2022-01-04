public class Person {
    public String fio;
    public String position;
    public String email;
    public int tel;
    public int salary;
    public int age;

    public Person (String fio, String position, String email, int tel, int salary, int age){
        this.fio=fio;
        this.position=position;
        this.email=email;
        this.tel=tel;
        this.salary=salary;
        this.age=age;
    }

    public static void ShowPersonUpAge (Person[] arr,int age1) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].age>=age1) {
                System.out.println(arr[i].fio);
                System.out.println(arr[i].position);
                System.out.println(arr[i].email);
                System.out.println(arr[i].tel);
                System.out.println(arr[i].salary);
                System.out.println(arr[i].age);
                System.out.println();
            }
        }
    }

}