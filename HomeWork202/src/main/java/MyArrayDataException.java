public class MyArrayDataException extends RuntimeException{
    public MyArrayDataException(int i,int j) {
        super("в массиве на позиции "+ i + " " +j + " не число");
    }
}
