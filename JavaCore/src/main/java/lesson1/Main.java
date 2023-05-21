package lesson1;

import lesson1.classes.Calculator;
import lesson1.classes.decorators.CalcDecorator;

public class Main {
	public static void main(String[] args) {
		System.out.println(CalcDecorator.decorate(Calculator.summ(2, 3)));
	}
}
