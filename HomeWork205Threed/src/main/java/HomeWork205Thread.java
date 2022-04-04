import java.util.Arrays;

public class HomeWork205Thread {

    public static int size = 10_000_000;
    public static float[] arr = new float[size];
    public static float[] arrr = new float[size];


    public static void main(String[] args) {


        firstMethod();

        secondMethod();
        System.out.println(Arrays.equals(arr,arrr));


    }

    public static void firstMethod() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i
                    / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("One thread time: " + (System.currentTimeMillis() -
                startTime) + " ms.");
        System.arraycopy(arr, 0, arrr, 0, size);
    }

    public static void secondMethod() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }

        long startTime = System.currentTimeMillis();
        Thread thread1 = new MyThread1();
        Thread thread2 = new MyThread2();
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Two thread time: " + (System.currentTimeMillis() -
                startTime) + " ms.");
    }
    public static class MyThread1 extends Thread {
        @Override
        public void run() {
            float[] arr1 = new float[size/2];
            System.arraycopy(arr, 0, arr1, 0, size/2);

            for (int i = 0; i < arr1.length; i++) {
                arr1[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i
                        / 5) * Math.cos(0.4f + i / 2));
            }

            System.arraycopy(arr1, 0, arr, 0, size/2);
        }
    }
    public static class MyThread2 extends Thread {
        @Override
        public void run() {
            float[] arr2 = new float[size/2];
            System.arraycopy(arr, size/2, arr2, 0, size/2);

// я долго искал ошибку. тут точно правильно ((i+size/2) / 5 ) чем в 1м (i / 5)

            for (int i = 0; i < arr2.length; i++) {
                arr2[i] = (float) (arr2[i] * Math.sin(0.2f + (i+size/2) / 5) * Math.cos(0.2f +
                        (i+size/2) / 5) * Math.cos(0.4f + (i+size/2) / 2));
            }

            System.arraycopy(arr2, 0, arr, size/2, size/2);

        }
    }

}
