import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static Lock lock = new ReentrantLock();
    private Race race;
    private int speed;
    private String name;
    private CountDownLatch cdlStart;
    private CountDownLatch cdlFinish;


    public String getName() {
        return name;
    }

    public Lock getLock() {
        return lock;
    }

    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, CountDownLatch cdl, CountDownLatch cdl1) {
        this.race = race;
        this.speed = speed;
        this.cdlStart = cdl;
        this.cdlFinish = cdl1;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            cdlStart.countDown();
            System.out.println(this.name + " готов");

            cdlStart.await();

        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);

            // проверка на победу Гока последняя СДЛ последний
            if (CARS_COUNT == cdlFinish.getCount() && i==race.getStages().size()-1) {
                System.out.println("\t\t\t\tУра победитель " + this.name);
            }

            // после проверки отдаем ЛОК
            lock.unlock();

            // если препятствие было Туннелем отдаем семафор
            if (race.getStages().get(i) instanceof Tunnel) {
                Tunnel.getSemaphore().release();
            }
        }

        cdlFinish.countDown();
    }
}
