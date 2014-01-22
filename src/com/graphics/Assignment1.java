package com.graphics;

import static javax.media.opengl.GL.GL_DEPTH_BUFFER_BIT;
import static javax.media.opengl.GL.GL_DEPTH_TEST;
import static javax.media.opengl.GL.GL_FRONT_AND_BACK;
import static javax.media.opengl.GL.GL_LEQUAL;
import static javax.media.opengl.GL.GL_NICEST;
import static javax.media.opengl.GL.GL_TRIANGLES;
import static javax.media.opengl.GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT;
import static javax.media.opengl.GL2GL3.GL_LINE;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_SMOOTH;
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

import com.jogamp.opengl.util.FPSAnimator;

public class Assignment1 implements GLEventListener {
	
	// Constants and Globals
	private GLU glu;  // for the GL Utility
	
	public static void main(String[] args) {
		// Said to be important in tutorial
		//TODO: Get what this is doing
		GLProfile.initSingleton(); 
		
		// Initialization
		// Setting up GL
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
		
		// Adding an animator
		FPSAnimator animator = new FPSAnimator(canvas, 60);
		animator.start();
		
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

	//TODO: Clean up this
	@Override
	public void init(GLAutoDrawable drawable) {
		 GL2 gl = drawable.getGL().getGL2();      // get the OpenGL graphics context
	     glu = new GLU();                         // get GLUtilities
	     //gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background color
	     //gl.glClearDepth(1.0f);      // set clear depth value to farthest
	     //gl.glEnable(GL_DEPTH_TEST); // enables depth testing
	     //gl.glDepthFunc(GL_LEQUAL);  // the type of depth test to do
	      
	     //gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); // best perspective correction
	     //gl.glShadeModel(GL_SMOOTH); // blends colors nicely, and smoothes out lighting
	}

	//TODO: Clean up this
	@Override
	public void reshape(GLAutoDrawable drawable, int arg1, int arg2, int width, int height) {
		 GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
		 
	     if (height == 0) height = 1;   // prevent divide by zero
	     float aspect = (float)width / height;
	 
	     // Set the view port (display area) to cover the entire window
	     gl.glViewport(0, 0, width, height);
	 
	     // Setup perspective projection, with aspect ratio matches viewport
	     gl.glMatrixMode(GL_PROJECTION);  // choose projection matrix
	     gl.glLoadIdentity();             // reset projection matrix
	     glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar
	 
	     // Enable the model-view transform
	     gl.glMatrixMode(GL_MODELVIEW);
	     gl.glLoadIdentity(); // reset
	}
	
	
	
	
	// Support Methods
	private void update() {
		// Pass
	}
	
	private void render(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
		// Clearing
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glClear(GL_DEPTH_BUFFER_BIT);
		
	     // PYRAMID OUTLINES
	     gl.glLoadIdentity();                 					// reset the model-view matrix
	     gl.glTranslatef(-1.6f, 0.0f, -6.0f); 					// translate left and into the screen
	     gl.glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);			// Makes only lines
	     
	     // BEGIN Pyramid
	     gl.glBegin(GL_TRIANGLES); 
	 
	     // Font-face triangle
	     gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
	     gl.glVertex3f(0.0f, 1.0f, 0.0f);
	     gl.glVertex3f(-1.0f, -1.0f, 1.0f);
	     gl.glVertex3f(1.0f, -1.0f, 1.0f);
	 
	     // Right-face triangle
	     gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
	     gl.glVertex3f(0.0f, 1.0f, 0.0f);
	     gl.glVertex3f(1.0f, -1.0f, 1.0f);
	     gl.glVertex3f(1.0f, -1.0f, -1.0f);
	 
	     // Back-face triangle
	     gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
	     gl.glVertex3f(0.0f, 1.0f, 0.0f);
	     gl.glVertex3f(1.0f, -1.0f, -1.0f);
	     gl.glVertex3f(-1.0f, -1.0f, -1.0f);
	 
	     // Left-face triangle
	     gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
	     gl.glVertex3f(0.0f, 1.0f, 0.0f);
	     gl.glVertex3f(-1.0f, -1.0f, -1.0f);
	     gl.glVertex3f(-1.0f, -1.0f, 1.0f);
	 
	     // END Pyramid
	     gl.glEnd(); 	
	}
}
