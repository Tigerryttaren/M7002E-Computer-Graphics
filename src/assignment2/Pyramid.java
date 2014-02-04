package assignment2;

import static javax.media.opengl.GL.GL_TRIANGLES;
import static javax.media.opengl.GL2.GL_ALL_ATTRIB_BITS;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

public class Pyramid extends Shape {
	
	private float base;
	private float height;
	
	public Pyramid (int id, float x, float y, float z, float base, float height) {
		super(12, x, y, z);
		this.base = base; 
		this.height = height; 
	}
	
	public float getBase() {
		return this.base;
	}
	
	public void setBase(float base){
		this.base = base;
	}
	
	public float getHeight() {
		return this.height;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}

	@Override
	public void draw(GL2 gl, GLU glu) {
		// Pushing to matrix stack
		gl.glPushMatrix();
		
		// PYRAMID
	    // Moves the figure in the (x, y, z)-axis
	    gl.glTranslatef(getX(), getY(), getZ()); 	
	    
	    // Rotates the pyramid, just for "fun" 
	    //gl.glRotatef(-55.0f, 0.0f, 1.0f, 0.0f); 
	    
	    // Makes only the outlines
	    //gl.glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);			
			    
	    // BEGIN Pyramid
	    gl.glBegin(GL_TRIANGLES); 
	    
	    // Pushing current color to stack
	    gl.glPushAttrib(GL_ALL_ATTRIB_BITS);
	    
	    // Set color
	    gl.glColor3f(getRed(), getGreen(), getBlue());
	    
	    // ENables lighting
	    gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_LIGHTING);
	    
	    // Set material properties
        float[] ambient_vector = new float[4];
	    ambient_vector[0] = getAmbientRed();
	    ambient_vector[1] = getAmbientRed();
	    ambient_vector[2] = getAmbientRed();
	    ambient_vector[3] = getAmbientRed();
	    
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
	   
	    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, ambient_vector, 0);
	    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, specular_vector, 0);
	    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, diffuse_vector, 0);
	    
	 	// Front triangle
	    gl.glVertex3f(0.0f, (height/2), 0.0f);
	    gl.glVertex3f(-(base/2), -(height/2), (base/2));
	    gl.glVertex3f((base/2), -(height/2), (base/2));
	 
	    // Right triangle
	    gl.glVertex3f(0.0f, (height/2), 0.0f);
	    gl.glVertex3f((base/2), -(height/2), (base/2));
	    gl.glVertex3f((base/2), -(height/2), -(base/2));
	    
	    // Left triangle
	    gl.glVertex3f(0.0f, (height/2), 0.0f);
	    gl.glVertex3f(-(base/2), -(height/2), -(base/2));
	    gl.glVertex3f(-(base/2), -(height/2), (base/2));
	 
	    // Back triangle
	    gl.glVertex3f(0.0f, (height/2), 0.0f);
	    gl.glVertex3f((base/2), -(height/2), -(base/2));
	    gl.glVertex3f(-(base/2), -(height/2), -(base/2));
	 
	    // END Pyramid
	    gl.glEnd(); 
	    
	    // Popping state
	    gl.glPopAttrib();
	    gl.glPopMatrix();
	

	}

}
