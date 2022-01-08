public class Animal {
    private String name;
    private int run;
    private int swim;
    private static int i=0;

    public Animal(String name,int run,int swim) {
        this.name = name;
        this.run=run;
        this.swim=swim;
        this.i++;
    }

    public void Run (int distance){
        if (this.run>=distance) {
            System.out.println("животное "+ getName() +" пробежало "+distance+" метров");
        } else {
            System.out.println("это животное не может бегать больше "+this.run+"  метров");
        }
    }
    public void Swim (int distance){
        if (this.swim>=distance) {
            System.out.println("животное "+ getName() +" проплыло "+distance+" метров");
        } else {
            System.out.println("это животное не может плыть больше "+this.swim+"  метров");
        }
    }

    public String getName() {
        return name;
    }

    public static int getI() {
        return i;
    }
}
