package org.example.mistake;

// класс указан без модификаторы доступа, 1 метод класса как протектед остальные паблик
//
abstract class Car {

//	публичное поле при условии что для него созданы геттеры и сеттеры
	public Engine engine;
	private String color;
	private String name;
	protected void start() {
		System.out.println("Car starting");
	}
	abstract void open();
	public Engine getEngine() {
		return engine;
	}
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

