package com.main.models.counter;

public class Counter implements AutoCloseable {
	
	private static int counter;
	private static boolean isOpen = false;
	
	
	public Counter() {
		if (isOpen) try {
			throw new Exception("класс каунтер уже открыт");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		isOpen = true;
	}
	
	public int getCounter() {
		return counter;
	}
	
	public void add() {
		counter++;
	}
	
	@Override
	public void close() throws Exception {
		isOpen = false;
		System.out.println("counter close");
	}
	
}
