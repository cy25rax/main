package org.example.figures.models;

import org.example.figures.classes.Figure2D;

public class Square extends Figure2D {
	private Double side;
	
	public Square(Double side) {
		this.side = side;
	}
	
	@Override
	public void area() {
		Double area = this.side * this.side;
		System.out.printf("площадь %sа равна %f", this.getName(), area);
	}
	
	@Override
	public void perimeter() {
		Double perimeter = 4 * this.side;
		System.out.printf("периметер %sа равен %f", this.getName(), perimeter);
	}
	
	@Override
	public String getName() {
		return "Квадрат";
	}
}
