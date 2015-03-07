package entities;

import org.lwjgl.util.vector.Vector3f;

public class Light {
	
	private Vector3f position;
	private Vector3f color;
	
	
	public Vector3f getPosition() {
		return position;
	}
	
	public Vector3f getColor() {
		return color;
	}
	
	public Light(Vector3f position, Vector3f color) {
		super();
		this.position = position;
		this.color = color;
	}
	
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	public void setColor(Vector3f color) {
		this.color = color;
	}
	
	
}
