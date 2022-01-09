public class Cat {
    private String name;
    private int appetite;
    private boolean isFeed;
    private boolean death=false;
    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.isFeed= false;
    }
    public void eat(Plate p) {
        if (p.decreaseFood(appetite)==true){
            this.isFeed=true;
        } else if (this.isFeed==false){
            System.out.println("\tкоту "+this.name+" не хватило еды и он умер");
            this.death=true;
        } else {
            System.out.println("\tкоту "+this.name+" не хватило еды");
            this.isFeed=false;
        }
    }
    public static void info(Cat[] arr){
        System.out.println("name\t\tappetit\t\tisFeed");
        for (Cat cats: arr) {
            if (cats == null|| cats.isDeath()==true) continue;
            System.out.print(cats.name + "\t\t");
            System.out.print(cats.appetite + "\t\t\t");
            System.out.print(cats.isFeed);
            System.out.println();
        }
    }

    public boolean isDeath() {
        return death;
    }
}