public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public boolean decreaseFood(int n) {
        if (food>=n){
            food -= n;
            return true;
        }
        System.out.println("в миске маловато еды");
        return false;
    }

    public void info() {
        System.out.println("в миске еды: " + food);
    }

    public void setFood(int food) {
        this.food += food;
    }
}