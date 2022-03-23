public class Cat implements Team{
    private String name;
    private int jumpRange;
    private int runRange;

    public Cat(String name,int jumpRange, int runRange) {
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
        System.out.print("кот "+this.name+" может прыгнуть на высоту "+jumpRange+" может пробежать "+runRange);
    }
}
