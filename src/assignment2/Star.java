package assignment2;

import static javax.media.opengl.GL.GL_LINE_LOOP;
import static javax.media.opengl.GL2.GL_ALL_ATTRIB_BITS;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

public class Star extends Shape {

	public Star(int id, float x, float y, float z) {
		super(id, x, y, z);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(GL2 gl, GLU glu) {
		//TODO: Fix star
		// Pushing to matrix stack
		gl.glPushMatrix();
				
		// STAR
		// Moves the figure in the (x, y, z)-axis
		gl.glTranslatef(getX(), getY(), getZ()); 
					    
		// BEGIN Star 
		gl.glBegin (GL_LINE_LOOP); 
		
		// Pushing to attribute stack
	    gl.glPushAttrib(GL_ALL_ATTRIB_BITS);
	    
		// Set color 
		gl.glColor3f(getRed(), getGreen(), getBlue());
		
		// Draw the sides
		gl.glVertex3f(0.0f, 1.0f, 0.0f);
		gl.glVertex3f(0.2f, 0.2f, 0.0f);
		gl.glVertex3f(1.0f, 0.0f ,0.0f);
		gl.glVertex3f(0.2f, -0.2f, 0.0f);
		gl.glVertex3f(0.0f, -1.0f, 0.0f);
		gl.glVertex3f(-0.2f, -0.2f, 0.0f);
		gl.glVertex3f(-1.0f, 0.0f, 0.0f);
		gl.glVertex3f(-0.2f, 0.2f, 0.0f);
		
		// END Star
	    gl.glEnd(); 
	    
	    // Popping state
	    gl.glPopAttrib();
	    gl.glPopMatrix();
		
	}
}
