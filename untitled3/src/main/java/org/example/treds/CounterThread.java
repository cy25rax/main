package org.example.treds;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterThread {
	
	public static void main(String[] args) {
		Counter counter = new Counter();
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				while (true) {
					counter.count();
					System.out.printf("Поток %s увеличил счетчик %d",
							Thread.currentThread().getName(),
							counter.getCount());
				}
			}).start();
		}
	}
	
	static class Counter {
		private Lock lock = new ReentrantLock();
		private int count = 0;
		
		public int getCount() {
			return count;
		}
		
		public void count() {
			lock.lock();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			count++;
			System.out.println();
			System.out.println("счетчик увеличен: " + count);
			lock.unlock();
		}
	}
}
