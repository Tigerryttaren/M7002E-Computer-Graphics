package assignment2;

import static javax.media.opengl.GL.GL_FRONT_AND_BACK;
import static javax.media.opengl.GL2.GL_ALL_ATTRIB_BITS;
import static javax.media.opengl.GL2GL3.GL_FILL;
import static javax.media.opengl.GL2GL3.GL_QUADS;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

public class Square extends Shape {
	private float side;
	
	public Square(int id, float x, float y, float z, float side){
		super(id, x, y, z);
		this.side = side;
	}

	public float getSide(){
		return this.side;
	}
	
	public void setSide(float side) {
		this.side = side;
	}
	
	@Override
	public void draw(GL2 gl, GLU glu) {
		// Pushing to matrix stack
		gl.glPushMatrix();
				
		// SQUARE	    
	    // Moves the figure in the (x, y, z)-axis
	    gl.glTranslatef(getX(), getY(), getZ()); 
	    
	    // Makes so that square is filled in
	    gl.glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);		
			    
	    // BEGIN Square
	    gl.glBegin(GL_QUADS); 
	 
	    // Pushing to attribute stack
	    gl.glPushAttrib(GL_ALL_ATTRIB_BITS);
	    
	    // Set color
	    gl.glColor3f(getRed(), getGreen(), getBlue());
	    
	    // The Quad
	    //TODO: Fix z values?
	    gl.glVertex3f((side/2), (side/2), 1.0f);
	    gl.glVertex3f(-(side/2), (side/2), 1.0f);
	    gl.glVertex3f(-(side/2), -(side/2), 1.0f);
	    gl.glVertex3f((side/2), -(side/2), 1.0f);
	    
	    // END Square
	    gl.glEnd();
	    
	    // Popping state
	    gl.glPopAttrib();
	    gl.glPopMatrix();
	}
}
