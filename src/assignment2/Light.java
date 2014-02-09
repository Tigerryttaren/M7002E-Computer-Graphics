package assignment2;

import javax.media.opengl.GL2;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.glu.GLU;

public class Light extends Shape {
	private float w;
	
	public Light(int id, float x, float y, float z, float w){
		super(id, x, y, z);
		this.w = w;
	}
	
	public float getW(){
		return this.w;
	}
	
	@Override
	public void draw(GL2 gl, GLU glu) {	
		// The light components
		float[] position_vector = new float[4];
		position_vector[0] = getX();
		position_vector[1] = getY();
		position_vector[2] = getZ();
		position_vector[3] = getW();
		
		// Set light properties
        float[] ambient_vector = new float[4];
	    ambient_vector[0] = getAmbientRed();
	    ambient_vector[1] = getAmbientGreen();
	    ambient_vector[2] = getAmbientBlue();
	    ambient_vector[3] = getAmbientAlpha();
	    
	    float[] specular_vector = new float[4];
	    specular_vector[0] = getSpecularRed();
	    specular_vector[1] = getSpecularGreen();
	    specular_vector[2] = getSpecularBlue();
	    specular_vector[3] = getSpecularAlpha();
	    
	    float[] diffuse_vector = new float[4];
	    diffuse_vector[0] = getDiffuseRed();
	    diffuse_vector[1] = getDiffuseGreen();
	    diffuse_vector[2] = getDiffuseBlue();
	    diffuse_vector[3] = getDiffuseAlpha();
	
		// Set properties to light source
	    gl.glLightfv(GLLightingFunc.GL_LIGHT0, GLLightingFunc.GL_POSITION, position_vector, 0);
		gl.glLightfv(GLLightingFunc.GL_LIGHT0, GLLightingFunc.GL_AMBIENT, ambient_vector, 0);
		gl.glLightfv(GLLightingFunc.GL_LIGHT0, GLLightingFunc.GL_SPECULAR, specular_vector, 0);
		gl.glLightfv(GLLightingFunc.GL_LIGHT0, GLLightingFunc.GL_DIFFUSE, diffuse_vector, 0);
	}

	@Override
	public void resize(float scalar) {
		// Pass
	}
	
	public String print() {
		// Pass, quite actually
		return "";
	}
}