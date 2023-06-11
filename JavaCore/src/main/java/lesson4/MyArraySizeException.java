package lesson4;

public class MyArraySizeException extends Exception{
	private final String message;
	
	@Override
	public String getMessage() {
		return message;
	}
	
	public MyArraySizeException(String message) {
		this.message = message;
	}
}
