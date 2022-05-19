import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private static Semaphore semaphore ;
    public Tunnel(int CARS_COUNT) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        semaphore = new Semaphore(CARS_COUNT/2);
    }

    public static Semaphore getSemaphore() {
        return semaphore;
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " +
                        description);

                semaphore.acquire();

                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
// берем ЛОК чтобы выпонить сразу проврку на победу
                c.getLock().lock();

                System.out.println(c.getName() + " закончил этап: " +
                        description);
// если оставить эту строчку здесь то не получается выполнить условие задани
// нужно отдать семафор после проверки на победу
//                semaphore.release();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}