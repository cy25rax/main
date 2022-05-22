import java.util.concurrent.Semaphore;

public abstract class Stage {
    protected int length;
    protected String description;
    protected Semaphore semaphore;

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public abstract void go(Car c);
}