public class HomeWork304Thread {
    private static Object mon = new Object();
    private static volatile char aChar= 'A';


    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (mon) {
                    try {
                        for (int i = 0; i < 5; i++) {
                            while (aChar !='A'){
                                mon.wait();
                            }
                            System.out.print("A");
                            aChar='B';
                            mon.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (aChar !='B'){
                        mon.wait();
                    }
                    System.out.print("B");
                    aChar='C';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }}
        });
        Thread thread3 = new Thread(() -> {
            synchronized (mon) {
                try {
                    for (int i = 0; i < 5; i++) {
                        while (aChar !='C'){
                            mon.wait();
                        }
                        System.out.print("C");
                        aChar='A';
                        mon.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }}
        });

        thread1.start();
        thread2.start();
        thread3.start();

    }
}
