public class HomeWork202 {
    public static String[][] arrOfString = new String[][] {
            {"1","2","3","4"},
            {"2","3","4","5"},
            {"1","2","3","4"},
            {"1","2","3","4","1"}
    };
//    public static String[][] arrOfString = new String[][] {
//            {"1","2","3","4"},
//            {"2","3","4","5"},
//            {"1","2","3","4"},
//            {"1","2","h","4"}
//    };

    public static void main(String[] args) {
        try {

            System.out.println(arraySumm(arrOfString));

        } catch (MyArrayDataException e) {
            e.printStackTrace();
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        }


        System.out.println("программа работает дальше");

    }

//    public static int arraySumm(String[][] arr) throws MyArraySizeException, MyArrayDataException{
//    вроде надо написать так, но и без этого работает

    public static int arraySumm(String[][] arr) {
        int ii=0;
        int jj=0;
        try {

            if (arr.length!=4 || arr[0].length!=4 ||
                arr[1].length!=4 || arr[2].length!=4 ||
                arr[3].length!=4)  throw new MyArraySizeException();

            int summ = 0;

            for (int i = 0;i < arr.length; i++) {
                for (int j =0; j < arr[0].length; j++) {
                    ii=i;
                    jj=j;
                    summ = summ + Integer.parseInt(arr[i][j]);
                }
            }

            return summ;

        } catch (NumberFormatException e) {
              throw new MyArrayDataException(ii,jj);
        }
//        return 0;
    }
}
