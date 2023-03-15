package org.example.figures.models;

import org.example.figures.classes.Figure2D;

public class Circle extends Figure2D {
	private Double radius;
	
	public Circle(Double radius) {
		this.radius = radius;
	}
	
	@Override
	public void area() {
		Double area = 3.14 * this.radius * this.radius;
		System.out.printf("площадь %sа равна %f", this.getName(), area);
	}
	
	@Override
	public void perimeter() {
		Double perimeter = 2 * 3.14 * this.radius;
		System.out.printf("периметер %sа равен %f", this.getName(), perimeter);
	}
	
	@Override
	public String getName() {
		return "Круг";
	}
}
