import java.util.Arrays;

public class HomeWork03 {

    // относится к 5 заданию
    public static int len=10;
    public static int initialValue=5;
    public static int[] array5 = new int[len];


    public static void main(String[] args) {
//        //1
//        zamena0na1(10);
//        System.out.println();
//
//        //2 создание массива 1..100
//        int[] arr100 = new int[100];
//
//        for (int i = 0; i < 100; i++) {
//            arr100[i]= i+1;
//        }
//        System.out.println(Arrays.toString(arr100));
//        System.out.println();
//
//        //3 создание массива [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] и уножение на 2 если < 6
//        int[] array = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
//
//        System.out.println("заданный массив   "+Arrays.toString(array));
//        for (int i = 0; i < array.length; i++) {
//            if (array[i] < 6) {
//                array[i] = array[i] * 2;
//            }
//        }
//        System.out.println("измененный массив"+Arrays.toString(array));
//        System.out.println();
//
//        //4 заполнить в квадратном массиве диагонали цифрами 1
//        int razmer=7;
//        int[][] arr2 = new int[razmer][razmer];
//
//        izmenenieDiagonal(arr2);
//
//        for (int i = 0; i < arr2.length; i++) {
//            for (int j = 0; j < arr2.length; j++) {
//                System.out.print(arr2[i][j]+" ");
//            }
//            System.out.println();
//        }
//
//        System.out.println();
//
//        //5 Написать метод, принимающий на вход два аргумента: len и initialValue,
//        //и возвращающий одномерный массив типа int длиной len, каждая ячейка
//        //которого равна initialValue;
//
//        // по условию в метод передается 2 переменные
//        //я нифига не понял как этот метод должен вернуть массив если этот массив не передавать в метод
//        // по этому пришлось записать массив в глобальную переменную
//
//        sozdanieMassiva(len,initialValue);
//        System.out.println(Arrays.toString(array5));
//        System.out.println();
//
//        //6 Задать одномерный массив и найти в нем минимальный и максимальный элементы
//
//        int razmer6=10;
//        int max;
//        int min;
//        int [] array6 = new int[razmer6];
//
//        for (int i = 0; i < razmer6; i++) {
//            array6[i]= (int) (Math.random()*200);
//        }
//
//        System.out.println("сгенерированный массив "+Arrays.toString(array6));
//
//        min = array6[1];
//        max = array6[1];
//        for (int i = 0; i < razmer6-1; i++) {
//            if (array6[i] < min) {
//                min = array6[i];
//            }
//            if (array6[i]>max) {
//                max=array6[i];
//            }
//        }
//
//        System.out.println("мин значение  "+min);
//        System.out.println("макс значение "+max);
//        System.out.println();
//
//
//        //7 Написать метод, в который передается не пустой одномерный целочисленный массив,
//        // метод должен вернуть true, если в массиве есть место, в котором сумма
//        // левой и правой части массива равны
//
//        int[] array7 = {2, 2, 3, 5, 5, 6, 18, 2, 0, 2, 1};
//
//        System.out.println(checkBalance(array7));

        //8 Написать метод, которому на вход подается одномерный массив и число n
        // (может быть положительным, или отрицательным), при этом метод должен сместить
        // все элементы массива на n позиций. Элементы смещаются циклично.
        // Для усложнения задачи нельзя пользоваться вспомогательными массивами.

        int razmer8=10;
        int[] array8 = new int[razmer8];
        for (int i = 0; i < razmer8; i++) {
            array8[i]=(int) (Math.random()*100);
        }
        System.out.println("сгенерированный массив"+Arrays.toString(array8));

        smeshenieMassiva(array8,0);

    }

    public static int[][] izmenenieDiagonal(int [][] array) {

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (i==j) {
                    array[i][j]=1;
                }
                if (j== array.length - i-1) {
                    array[i][j]=1;
                }
            }
        }
        return array;
    }

    public static void zamena0na1 (int i){

        int[] array = new int[i];

        for (int j = 1; j < i; j++) {
            array[j] = (int) (Math.random()*2);
        }

        System.out.println("первоначальный массив " + Arrays.toString(array));

        for (int j = 0; j < i-1; j++) {
            if (array[j] == 1) {
                array[j] = 0;
            } else {
                array[j] = 1;
            }
        }

        System.out.println("измененный массив     " + Arrays.toString(array));
    }

    public static int[] sozdanieMassiva(int len, int initialValue){

        for (int i = 0; i < len; i++) {
            array5[i]=initialValue;
        }
        return array5;
    }

    public static boolean checkBalance(int[] array) {
        int summ1=array[0];
        int summ2=array[array.length-1];
        int j=0;
        int k=0;
        for (int i = 0; i < array.length-1; i++) {
            if (summ1==summ2 && (j+k)==array.length-2) { //если просуммирован весь массив
                System.out.println("массив " +
                        "соответствует условию");        //j+k равно длинне массива
                return true;
            }
            if (summ1>summ2) {
                j++;
                summ2=summ2+array[array.length-j-1];
            } else if (summ1<summ2) {
                k++;
                summ1=summ1+array[k];
            } else if (summ1==summ2 && (j+k)!=array.length-2) {  //если суммы равно но массив весь
                j++;                                             //не просуммирован
                summ2=summ2+array[array.length-j-1];             //тогда к начальному прибавляем след
                k++;                                             //к последнему предпоследнее
                summ1=summ1+array[k];
            }

        }
        return false;
    }

    public static void smeshenieMassiva(int[] array,int n) {
        int b=0;
        if (n>0){
            for (int i = 0; i < n; i++) {
                b=array[0];
                for (int j = 0; j < array.length-1; j++) {
                    array[j]=array[j+1];
                }
                array[array.length-1]=b;
            }
            System.out.println("измененный массив "+Arrays.toString(array));
        } else if (n<0) {
            for (int i = n; i < 0; i++) {
                b=array[array.length-1];
                for (int j = array.length-1; j > 0; j--) {
                    array[j]=array[j-1];
                }
                array[0]=b;
            }
            System.out.println("измененный массив "+Arrays.toString(array));
        } else {
            System.out.println("массив не изменился");
        }


    }
}
