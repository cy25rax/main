package org.example.figures.classes;

public class Figure2D extends Figure {
	
	public void area() {
		System.out.println("площадь 2Д фигуры: основание * на высоту");
	}
	
	@Override
	public String getName() {
		return "двумерная фигура";
	}
	
	public void perimeter() {
		System.out.println("периметер 2Д фигуры: сумма длинн всех сторон");
	}
}
