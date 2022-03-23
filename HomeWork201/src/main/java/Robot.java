public class Robot implements Team{
    private String name;
    private int jumpRange;
    private int runRange;

    public Robot(String name,int jumpRange, int runRange) {
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
        System.out.print("робот "+this.name+" может прыгнуть на высоту "+jumpRange+" может пробежать "+runRange);
    }
}
