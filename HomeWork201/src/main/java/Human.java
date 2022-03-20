public class Human {
    private String name;
    private int jumpRange;
    private int runRange;

    public Human(String name,int jumpRange, int runRange) {
        this.name = name;
        this.jumpRange = jumpRange;
        this.runRange = runRange;
    }

    public int getJumpRange() {
        return jumpRange;
    }

    public int getRunRange() {
        return runRange;
    }

    public void info () {
        System.out.println();
        System.out.print("человек "+this.name+" может прыгнуть на высоту "+this.jumpRange+" может пробежать "+this.runRange);
    }
}
