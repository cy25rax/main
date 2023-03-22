package org.example.treds;

public class PingPong {
	private static Object mon = new Object();
	
	public static void main(String[] args) {
		Thread ping = new Thread(() -> {
			synchronized (mon) {
				while (true) {
					try {
						System.out.println("ping");
						mon.notify();
						mon.wait();
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
			}
		});
		Thread pong = new Thread(() -> {
			synchronized (mon) {
				while (true) {
					try {
						System.out.println("pong");
						mon.notify();
						mon.wait();
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
			}
		});
		
		pong.start();
		ping.start();
	}
}
