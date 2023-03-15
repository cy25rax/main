package org.example.figures.models;

import org.example.figures.classes.Figure2D;

public class Triangle extends Figure2D {
	private Double sideA;
	private Double sideB;
	private Double sideC;
	
	public Triangle(Double sideA, Double sideB, Double sideC) {
		this.sideA = sideA;
		this.sideB = sideB;
		this.sideC = sideC;
	}
	
	
	@Override
	public void area() {
		double p = (this.sideA + this.sideB + this.sideC)/2;
		Double area = Math.sqrt(p*(p - this.sideA)*(p - this.sideB)*(p - this.sideC));
		System.out.printf("площадь %sа равна %f", this.getName(), area);
	}
	
	@Override
	public void perimeter() {
		Double perimeter = this.sideA+this.sideB+this.sideC;
		System.out.printf("периметер %sа равен %f", this.getName(), perimeter);
	}

	@Override
	public String getName() {
		return "Треугольник";
	}
}
