package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;
import shaders.StaticShader;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
		
		// OpenGL expects vertices to be defined counter clockwise by default.
		
		float[] vertices = {
				// Left bottom triangle
				-0.5f, 0.5f, 0f,   //v0
				-0.5f, -0.5f, 0f,  //v1
				 0.5f, -0.5f, 0f,  //v2
				 0.5f, 0.5f, 0.0f  //v3	
		};
		
		int[] indices = {
				0,1,3, //TOP left (V0, V1, V3)
				3,1,2 //Buttom right (V3, V1, V2)
		};
		
		RawModel model = loader.loadToVAO(vertices, indices);
		
		while (!Display.isCloseRequested()) {
			renderer.prepare();
			//game logic
			//render
			shader.start();
			renderer.render(model);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
}
