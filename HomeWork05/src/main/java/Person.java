public class Person {
    private String fio;
    private String position;
    private String email;
    private int tel;
    private int salary;
    private int age;

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

    public void Info(){
        System.out.println(this.fio);
        System.out.println(this.position);
        System.out.println(this.email);
        System.out.println(this.tel);
        System.out.println(this.salary);
        System.out.println(this.age);
        System.out.println();
    }


}