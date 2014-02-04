package assignment2;

import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Main extends JFrame implements GLEventListener {
	
	// GL Utility
	private GLU glu;
	
	public static void main(String[] args) {
		
		// Makes the UI prettier, more native looking (http://stackoverflow.com/questions/2592207/how-to-improve-look-and-feel-of-java-swing-gui/2592258#2592258)
		try { 
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		// Initialization
		// Setting up GL
		GLProfile.initSingleton(); 
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		GLCanvas canvas = new GLCanvas(caps);
	
		MenuBar menubar = new MenuBar();
		menubar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menubar.add(canvas);
		menubar.setVisibleLater();		
		
		// Adding a render listener
		canvas.addGLEventListener(new Main());
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
		// Clearing the buffers
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
		
		// Getting a handle to the singleton Data object
		Data handle = Data.getData();
		
		for (Shape shape : handle.getShapes()) {
			shape.draw(gl, glu);
		}
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// Pass
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		// Get GLUtilities
		glu = new GLU(); 
		
		GL2 gl = drawable.getGL().getGL2(); 
        gl.glEnable(GL2.GL_DEPTH_TEST);
	    
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int arg1, int arg2, int width, int height) {
		 GL2 gl = drawable.getGL().getGL2();
	
		 // Prevents a divion by 0
		 if (height == 0) {
			 height = 1;
		 }
		 
	     // Computing the aspect ratio
	     float aspect = (float) width / height;
	 
	     // Setting the viewport, the area that is displayed, to cover the whole window
	     gl.glViewport(0, 0, width, height);
	     
	     // Setting the perspective projection with an aspect that matches that of the viewport
	     // Choose Projection Matrix	
	     gl.glMatrixMode(GL_PROJECTION);  
	     
	     // Resets the Projection Matrix	
	     gl.glLoadIdentity();  
	     
	     // gluPerspective(fovy, aspect, zNear, zFar);
	     glu.gluPerspective(45.0, aspect, 0.1, 100.0);
	     gl.glMatrixMode(GL_MODELVIEW);
	     gl.glLoadIdentity();
	}
}