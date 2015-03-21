package group.project.unknown;

public class Main implements Runnable {

	Thread thread;
	boolean running = false;

	public Main() {
		start();
	}

	public void run() {
		init();
		while (running) {

		}
		cleanup();
	}

	public synchronized void start() {
		thread = new Thread(this, "Game");
		running = true;
		thread.start();
	}

	public synchronized void stop() {
		running = false;
	}

	public static void main(String[] args) {
		new Main();
	}

	private void cleanup() {

	}

	private void init() {

	}

}