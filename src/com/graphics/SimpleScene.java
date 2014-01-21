package com.graphics;

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

import com.jogamp.opengl.util.FPSAnimator;

public class SimpleScene implements GLEventListener {
	
	public static void main(String[] args) {
		// Said to be very important
		GLProfile.initSingleton();
		
		// Initialization
		// Setting up GL
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		GLCanvas canvas = new GLCanvas(caps);
		
		// Making the AWT frame window
		Frame frame = new Frame("Standard Test Window");
		frame.setSize(300, 300);
		frame.add(canvas);
		frame.setVisible(true);
		
		// Adding a render listener
		canvas.addGLEventListener(new SimpleScene());
		
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
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int arg1, int arg2, int arg3, int arg4) {
		
	}
	
	
	
	
	
	// Support Methods
	private void update() {
		
	}
	
	private void render(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
		// Clearing
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		
		// Drawing the triangle
		gl.glBegin(GL.GL_TRIANGLES);
		gl.glColor3f(1, 0, 0);
		gl.glVertex2f(-1, -1);
		gl.glColor3f(0, 1, 0);
		gl.glVertex2f(0, 1);
		gl.glColor3f(0, 0, 1);
		gl.glVertex2f(1, -1);
		gl.glEnd();	
		
	}
}
