package lesson4;

public class MyArrayDataException extends Exception{
	private final int i,j;
	private final String message;
	
	public int getI() {
		return i;
	}
	
	public int getJ() {
		return j;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	public MyArrayDataException(String message, int i, int j) {
		this.message = message;
		this.i = i;
		this.j = j;
	}
}
