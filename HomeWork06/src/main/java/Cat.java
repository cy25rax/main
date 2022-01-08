public class Cat extends Animal{
    static int run=200;
    static int swim=0;
    static int catI=0;

    public Cat(String name) {
        super(name,run,swim);
        this.catI=catI+1;
    }

    @Override
    public void Swim (int distance){
        System.out.println("кошки не умеют плавать");
    }

    public static int getCatI() {
        return catI;
    }

    public void CatInfo(){
        System.out.println(getName());
    }
}
