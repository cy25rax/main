package lesson1.classes.decorators;

/**
 * Декоратор.
 * Получая на вход значение number добавляет к нему необходимые данные
 */
public class CalcDecorator {
	
	public static String decorate (int number) {
		return "Ваше число: " + number;
	}
}
