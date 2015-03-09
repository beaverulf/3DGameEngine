package engineTester;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import renderEngine.EntityRenderer;
import shaders.StaticShader;
import terrains.Terrain;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
		
		// OpenGL expects vertices to be defined counter clockwise by default.
		
		RawModel model = OBJLoader.loadObjModel("globe", loader);
		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("world")));
		ModelTexture texture = staticModel.getTexture();
		texture.setShineDamper(10);
		texture.setReflectivity(1);
		
		Entity entity = new Entity(staticModel, new Vector3f(60,5,80), 0, 0, 0, 1);
		Light light = new Light(new Vector3f(60,10,110),new Vector3f(1,1,1));
		Terrain terrain = new Terrain(0, 0, loader, new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain2 = new Terrain(1, 0, loader, new ModelTexture(loader.loadTexture("grass")));
		Camera camera = new Camera();
		
		MasterRenderer renderer = new MasterRenderer();
		while (!Display.isCloseRequested()) {
			camera.move();
			entity.increaseRotation(0,0.2f, 0);
			
			renderer.processTerrain(terrain);
			renderer.processTerrain(terrain2);
			renderer.processEntity(entity);
			
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}
		
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
}
