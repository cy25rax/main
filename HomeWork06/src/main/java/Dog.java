public class Dog extends Animal{
    static int run=500;
    static int swim=10;
    static int dogI=0;

    public Dog(String name) {
        super(name,run,swim);
        this.dogI=dogI+1;
    }

    public static int getDogI() {
        return dogI;
    }

    public void DogInfo(){
        System.out.println(getName());
    }
}
