package com.graphics;

import static javax.media.opengl.GL.GL_DEPTH_BUFFER_BIT;
import static javax.media.opengl.GL.GL_FRONT_AND_BACK;
import static javax.media.opengl.GL.GL_TRIANGLES;
import static javax.media.opengl.GL2.GL_QUADS;
import static javax.media.opengl.GL2GL3.GL_LINE;
import static javax.media.opengl.GL2GL3.GL_FILL;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

public class Assignment1 implements GLEventListener {
	
	// Constants and Globals
	// GL Utility
	private GLU glu;  
	
	public static void main(String[] args) {
		
		// Initialization
		// Setting up GL
		GLProfile.initSingleton(); 
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		GLCanvas canvas = new GLCanvas(caps);
		
		// Making the AWT frame window
		Frame frame = new Frame("Assignment 1");
		frame.setSize(800, 600);
		frame.add(canvas);
		frame.setVisible(true);
		
		// Adding a render listener
		canvas.addGLEventListener(new Assignment1());
		
		// Fixes so the exit button exits the program
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		update();
		render(drawable);
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// Pass
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		// Get GLUtilities
	    glu = new GLU();                         
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int arg1, int arg2, int width, int height) {
		 GL2 gl = drawable.getGL().getGL2();
		 
		 // Prevents division by 0
	     if (height == 0) { 
	    	 height = 1;
	     }
	     // Computing the aspect
	     float aspect = (float)width / height;
	 
	     // Setting the viewport, the area that is displayed, to cover the whole window
	     gl.glViewport(0, 0, width, height);
	 
	     // Setting the perspective projection with an aspect that matches that of the viewport
	     // Choose Projection Matrix	
	     gl.glMatrixMode(GL_PROJECTION);  
	     // Resets the Projection Matrix	
	     gl.glLoadIdentity();  
	     
	     // gluPerspective(fovy, aspect, zNear, zFar);
	     // Instructions taken from http://www.opengl.org/sdk/docs/man2/xhtml/glTranslate.xml
	     // fovy : Specifies the field of view angle, in degrees, in the y direction.
	     // aspect : Specifies the aspect ratio that determines the field of view in the x direction. The aspect ratio is the ratio of x (width) to y (height).
	     // zNear : Specifies the distance from the viewer to the near clipping plane (always positive).
	     // zFar : Specifies the distance from the viewer to the far clipping plane (always positive).
	     glu.gluPerspective(45.0, aspect, 0.1, 100.0);
	 
	     // Enable the model-view transform
	     gl.glMatrixMode(GL_MODELVIEW);
	     gl.glLoadIdentity();
	}
	
	// Support Methods
	private void update() {
		// Pass
	}
	
	private void render(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
		// Clearing the buffers
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glClear(GL_DEPTH_BUFFER_BIT);
		
		// Drawing the figures
		drawPyramid(gl, drawable);
		drawSquare(gl, drawable);
		drawStar(gl, drawable);
			
	}
	
	private void drawPyramid(GL2 gl, GLAutoDrawable drawable) {
		// PYRAMID 
		// Resetting the Model-View matrix
	    gl.glLoadIdentity();                 
	    // Moves the figure in the (x, y, z)-axis
	    gl.glTranslatef(-3.0f, 0.0f, -8.0f); 		
	    // Makes only the outlines
	    gl.glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);			
			    
	    // BEGIN Pyramid
	    gl.glBegin(GL_TRIANGLES); 
	 
	    // Set color to RED
	    gl.glColor3f(1.0f, 0.0f, 0.0f);
	    
	 	// Front triangle
	    gl.glVertex3f(0.0f, 1.0f, 0.0f);
	    gl.glVertex3f(-1.0f, -1.0f, 1.0f);
	    gl.glVertex3f(1.0f, -1.0f, 1.0f);
	 
	    // Right triangle
	    gl.glVertex3f(0.0f, 1.0f, 0.0f);
	    gl.glVertex3f(1.0f, -1.0f, 1.0f);
	    gl.glVertex3f(1.0f, -1.0f, -1.0f);
	    
	    // Left triangle
	    gl.glVertex3f(0.0f, 1.0f, 0.0f);
	    gl.glVertex3f(-1.0f, -1.0f, -1.0f);
	    gl.glVertex3f(-1.0f, -1.0f, 1.0f);
	 
	    // Back triangle
	    gl.glVertex3f(0.0f, 1.0f, 0.0f);
	    gl.glVertex3f(1.0f, -1.0f, -1.0f);
	    gl.glVertex3f(-1.0f, -1.0f, -1.0f);
	 
	    // END Pyramid
	    gl.glEnd(); 
	}
	private void drawSquare(GL2 gl, GLAutoDrawable drawable) {
		// SQUARE
		// Resetting the Model-View matrix
	    gl.glLoadIdentity();                 
	    // Moves the figure in the (x, y, z)-axis
	    gl.glTranslatef(0.0f, 0.0f, -8.0f); 		
	    // Makes only the outlines
	    gl.glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);		
			    
	    // BEGIN Square
	    gl.glBegin(GL_QUADS); 
	 
	    // Set color to BLUE
	    gl.glColor3f(0.0f, 0.0f, 1.0f); 
	    
	    // Front quad
	    gl.glVertex3f(1.0f, 1.0f, 1.0f);
	    gl.glVertex3f(-1.0f, 1.0f, 1.0f);
	    gl.glVertex3f(-1.0f, -1.0f, 1.0f);
	    gl.glVertex3f(1.0f, -1.0f, 1.0f);
	    
	    // END Square
	    gl.glEnd(); 
	    
	  
		
	}
	private void drawStar(GL2 gl, GLAutoDrawable drawable) {
		//TODO: Star
		// STAR
		// Resetting the Model-View matrix
		gl.glLoadIdentity();                 
		// Moves the figure in the (x, y, z)-axis
		gl.glTranslatef(0.0f, 0.0f, -8.0f); 		
		// Makes only the outlines
		gl.glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);		
					    
		// BEGIN Star
		//TODO_ How to star?
		gl.glBegin(GL_QUADS); 
		
		// Set color to GREEN
		gl.glColor3f(0.0f, 1.0f, 0.0f);
		
		//TODO: Draw the sides
		
		// END Star
	    gl.glEnd(); 
	}
}
