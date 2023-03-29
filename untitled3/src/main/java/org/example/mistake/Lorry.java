package org.example.mistake;

class Lorry extends Car implements Moveable, Stopable {
// не правильная запись ипользования интерфейсов
// класс указан без модификаторы доступа, хотя может так и надо по ТЗ

//class Lorry extends Car, Moveable, Stopable {
	
	//мне кажется не хватает @Override, работать будет и без них, но для понимания ...
	public void move(){
		System.out.println("Car is moving");
	}
	
	public void stop(){
		System.out.println("Car is stop");
	}
//	не переопределен метод абстрактного класса
	@Override
	void open() {

	}
}

