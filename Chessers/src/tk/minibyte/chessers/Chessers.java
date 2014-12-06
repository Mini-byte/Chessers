package tk.minibyte.chessers;

import tk.minibyte.chessers.logic.GameState;
import tk.minibyte.chessers.logic.MainState;
import tk.minibyte.inferno.math.Vector3f;
import tk.minibyte.inferno.rendering.Renderer;
import tk.minibyte.inferno.rendering.Window;

public class Chessers {
	public static final int MAJOR = 0;
	public static final int MINOR = 0;
	public static final int PATCH = 0;

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final String TITLE = "Chessers " + MAJOR + "." + MINOR + "." + PATCH;

	private boolean running = false;

	private GameState state;

	public Chessers() {
		Window.create(WIDTH, HEIGHT, TITLE);

		Renderer.initializeOpenGL();
		Renderer.initializeTransform((float) Math.toRadians(65), 0.1f, 100.0f);
		
		Renderer.getTransform().getCamera().setPosition(new Vector3f(0, 2, 0));

		state = new MainState();
	}

	public void start() {
		if (running) {
			return;
		}

		running = true;

		loop();
	}

	public void stop() {
		if (!running) {
			return;
		}

		running = false;
	}

	public void loop() {
		long last = System.nanoTime();

		double ns = 1000000000.0 / 60.0;
		double delta = 0.0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - last) / ns;
			last = now;

			while (delta > 1) {
				delta--;
				update();
			}

			render();
		}

		Window.destroy();
		System.exit(0);
	}

	public void update() {
		if (Window.isCloseRequested()) {
			stop();
		}

		state.update();
	}

	public void render() {
		Window.clear(0.1f, 0.1f, 0.1f);

		state.render();

		Window.update();
	}

	public static void main(String[] args) {
		new Chessers().start();
	}
}
