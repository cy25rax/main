package org.example.mistake;

// класс указан без модификаторы доступа, хотя может так и надо по ТЗ
class LightWeightCar extends Car implements Moveable {
	@Override
	void open() {
		System.out.println("Car is open");
	}
	@Override
	public void move() {
		System.out.println("Car is moving");
	}
}
