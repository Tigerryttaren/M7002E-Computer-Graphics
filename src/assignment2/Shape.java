package assignment2;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

public abstract class Shape {
	
	private int id;
	
	private float x;
	private float y;
	private float z;
	
	private float red;
	private float green;
	private float blue;
	
	private float ambient_red;
	private float ambient_green;
	private float ambient_blue;
	private float ambient_alpha;
	
	private float specular_red;
	private float specular_green;
	private float specular_blue;
	private float specular_alpha;
	
	private float diffuse_red;
	private float diffuse_green;
	private float diffuse_blue;
	private float diffuse_alpha;
	
	public Shape (int id, float x, float y, float z) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public abstract void draw (GL2 gl, GLU glu);
	
	public float getX(){
		return this.x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY(){
		return this.y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getZ(){
		return this.z;
	}
	
	public void setZ(float z) {
		this.z = z;
	}
	
	public int getID(){
		return this.id;
	}
	
	public float getRed() {
		return this.red;
	}
	
	public float getGreen() {
		return this.green;
	}
	
	public float getBlue() {
		return this.blue;
	}
	
	public float getAmbientRed() {
		return this.ambient_red;
	}
	
	public float getAmbientGreen() {
		return this.ambient_green;
	}
	
	public float getAmbientBlue() {
		return this.ambient_blue;
	}
	
	public float getAmbientAlpha() {
		return this.ambient_alpha;
	}
	
	public float getSpecularRed() {
		return this.specular_red;
	}
	
	public float getSpecularGreen() {
		return this.specular_green;
	}
	
	public float getSpecularBlue() {
		return this.specular_blue;
	}
	
	public float getSpecularAlpha() {
		return this.specular_alpha;
	}
	
	public float getDiffuseRed() {
		return this.diffuse_red;
	}
	
	public float getDiffuseGreen() {
		return this.diffuse_green;
	}
	
	public float getDiffuseBlue() {
		return this.diffuse_blue;
	}
	
	public float getDiffuseAlpha() {
		return this.diffuse_alpha;
	}
	
	public void setColor(float red, float green, float blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public void setAmbient(float red, float green, float blue, float alpha){
		this.ambient_red = red;
		this.ambient_green = green;
		this.ambient_blue = blue;
		this.ambient_alpha = alpha;
	}
	
	public void setSpecular(float red, float green, float blue, float alpha){
		this.specular_red = red;
		this.specular_green = green;
		this.specular_blue = blue;
		this.specular_alpha = alpha;		
	}
	
	public void setDiffuse(float red, float green, float blue, float alpha) {
		this.diffuse_red = red;
		this.diffuse_green = green;
		this.diffuse_blue = blue;
		this.diffuse_alpha = alpha;
	}
}
