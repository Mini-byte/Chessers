package tk.minibyte.chessers.logic;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import tk.minibyte.inferno.math.Vector3f;
import tk.minibyte.inferno.rendering.Material;
import tk.minibyte.inferno.rendering.Mesh;
import tk.minibyte.inferno.rendering.Renderer;
import tk.minibyte.inferno.rendering.shading.BaseLight;
import tk.minibyte.inferno.rendering.shading.DirectionalLight;

public class MainState implements GameState {
	private Mesh board;
	private Material boardMaterial;

	public MainState() {
		Renderer.setAmbientIntensity(0.4f);

		Renderer.addDirectionalLight(new DirectionalLight(new BaseLight(new Vector3f(1, 1, 1), 2), new Vector3f(0, -1, 0)));

		board = new Mesh("./res/models/board.obj");
		board.rotate((float) Math.PI, 0, 0);

		boardMaterial = new Material("./res/textures/board.png", 1, 16);
	}

	public void update() {
		// TEMP CODE

		boolean rotateFurther = true;

		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && Mouse.isGrabbed()) {
			Mouse.setGrabbed(false);
		}

		if (Mouse.isButtonDown(0) && !Mouse.isGrabbed()) {
			Mouse.setGrabbed(true);
		}

		if (Mouse.isGrabbed()) {
			float dx = (float) Math.toRadians(Mouse.getDX()) / 2;
			float dy = (float) Math.toRadians(Mouse.getDY()) / 2;

			if (rotateFurther) {
				Renderer.getTransform().getCamera().rotate(new Vector3f(dy, dx, 0));
			}
		}
	}

	public void render() {
		Renderer.render(board, boardMaterial);
	}
}
