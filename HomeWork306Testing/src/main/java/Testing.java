public class Testing {

    public boolean CheckOneFour (int[] arr){
        int oneCheck=0;
        int fourCheck=0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]==1) oneCheck++;
            if (arr[i]==4) fourCheck++;
        }
        if ((oneCheck+fourCheck)==arr.length && oneCheck!=0 && fourCheck!=0) return true;
        return false;
    }

    public int[] ArrAfterFour (int[] arr){
        int fourPosition=0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i]==4) fourPosition=i;
        }

        if (fourPosition==0) {
            throw new RuntimeException("в массиве нет числа 4");
        }

        int elementsAfterFour=arr.length-fourPosition-1;

        int [] ar = new int[elementsAfterFour];

        for (int i = 0; i < elementsAfterFour; i++) {
            ar[i] = arr[i+fourPosition+1];
        }

        return ar;
    }
}
