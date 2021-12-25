public class HomeWork02 {

    public static void main(String[] args) {

        System.out.println(bolse10mense20(9,2));
        pologitelnoOtricatelnoe(10);
        System.out.println(provrkaPologitelnosti(0));
        vivodStrokiNraz("sdkjhg",3);
        System.out.println(visokosniiGod(100));

    }

    public static boolean bolse10mense20(int a, int b) {
        return (a + b) > 10 && (a + b) <= 20;
    }

    public static void pologitelnoOtricatelnoe(int a) {
        if (a>=0) {
            System.out.println("число положительное");
        } else {
            System.out.println("число отрицательное");
        }
    }

    public static boolean provrkaPologitelnosti(int a) {
        return a >= 0;
    }

    public static void vivodStrokiNraz(String a, int b) {
        for (int i = 1; i <= b; i++) {
            System.out.println(a);
        }
    }

    public static boolean visokosniiGod (int a) {

        if (a % 400 == 0) return true;
        if (a % 100 == 0) return false;
        if (a % 4 == 0) return true;
        return false; // почему без этой строки джава ругается, выше же есть return
    }


}
