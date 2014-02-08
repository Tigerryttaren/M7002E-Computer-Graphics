package assignment2;

import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
import javax.swing.UIManager;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.util.Animator;

@SuppressWarnings("serial")
public class Main extends JFrame {

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
	
		final Animator animator = new Animator(canvas);
		animator.start();	
		
		MenuBar menubar = new MenuBar(animator);
		menubar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menubar.add(canvas);
		menubar.setVisibleLater();	
		
		final Renderer renderer = new Renderer();
		canvas.addMouseListener(renderer);
		
		// Adding a render listener
		canvas.addGLEventListener(renderer);
	}
	
	static class Renderer implements GLEventListener, MouseListener {
		// GL Utility
		private GLU glu;
				
		// Very inspired from the JOGL example "Picking.java"
		static final int NOSTATE = 0;
		static final int UPDATESTATE = 1;
		static final int SELECTSTATE = 2;
		
		int CMD = UPDATESTATE;
		int x_coordinate;
		int y_coordinate;
		
		private float left = 0.0f;
		private float right = 2.0f;
		private float bottom = 0.0f;
		private float top = 1.0f;
		
		public void mouseClicked(MouseEvent e) {
			CMD = SELECTSTATE;
	        x_coordinate = e.getX();
	        y_coordinate = e.getY();    	
	   	}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// Pass
		}
		
		@Override
		public void mouseExited(MouseEvent arg0) {
			// Pass
		} 
		
		@Override
		public void mousePressed(MouseEvent arg0) {
			// Pass
		}
		
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// Pass
		}
		
		private void draw(GL2 gl) {
			// Getting a handle to the singleton Data object
			Data handle = Data.getData();
			
			if (handle.light != null) {
				handle.light.draw(gl, glu);
			}
        
			for (Shape shape : handle.getShapes()) {
				shape.draw(gl, glu);
			}
		}
		
		@Override
		public void display(GLAutoDrawable drawable) {
			GL2 gl = drawable.getGL().getGL2();
			
			// Clearing the buffers
			gl.glClear(GL.GL_COLOR_BUFFER_BIT);
			gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
			
			// Based on the JOGL example "Picking.java"
			switch(CMD) {
		    	case UPDATESTATE:
		    		draw(gl);
		        	break;
		    	case SELECTSTATE:
		    		int buffsize = 512;
			        double x = (double) x_coordinate, y = (double) y_coordinate;
			        int[] viewPort = new int[4];
			        IntBuffer selectBuffer = Buffers.newDirectIntBuffer(buffsize);
			        int hits = 0;
			        gl.glGetIntegerv(GL2.GL_VIEWPORT, viewPort, 0);
			        gl.glSelectBuffer(buffsize, selectBuffer);
			        gl.glRenderMode(GL2.GL_SELECT);
			        gl.glInitNames();
			        gl.glMatrixMode(GL2.GL_PROJECTION);
			        gl.glPushMatrix();
			        gl.glLoadIdentity();
			        glu.gluPickMatrix(x, (double) viewPort[3] - y, 5.0d, 5.0d, viewPort, 0);    	
			        glu.gluOrtho2D(left, right, bottom, top);
			        draw(gl);
			        gl.glMatrixMode(GL2.GL_PROJECTION);
			        gl.glPopMatrix();
			        gl.glFlush();
			        hits = gl.glRenderMode(GL2.GL_RENDER);
			        processHits(hits, selectBuffer);
			        CMD = UPDATESTATE;
			        break;
		      }
		}
		
		// Also, very inspired and based on JOGL example "Picking.java"
		public void processHits(int hits, IntBuffer buffer) {
		    int offset = 0;
		    int names;
		    float z1, z2;
		    ArrayList<Integer> shapes_id_hit = new ArrayList<Integer>();
		    ArrayList<float[]> shapes_hit = new ArrayList<float[]>();
		    
		    for (int i = 0; i < hits; i++) {
		        names = buffer.get(offset); 
		        offset++;
		        
		        //TODO: Add bt three instead and remove the z1, z2
		        z1 = (float) (buffer.get(offset)& 0xffffffffL) / 0x7fffffff; 
		        offset++;
		        z2 = (float) (buffer.get(offset)& 0xffffffffL) / 0x7fffffff; 
		        offset++;

		        for (int j = 0; j < names; j++) { 
		            if (j == (names - 1)) {
		            	shapes_id_hit.add(buffer.get(offset));
		            }
		            //TODO: remove this snippet
		            /*else {
		              //System.out.println();
		            }*/
		            offset++;
		          }
		      }
		    
		    // Creates a list of shapes hit
		    Data handle = Data.getData();
		    
            for (Shape shape : handle.getShapes()) {
            	for (int m : shapes_id_hit) {
            		if (m == shape.getID()) {
            			shapes_hit.add(new float[]{shape.getID(), shape.getZ()});
                    }
            	}
            }
            
            //TODO: clean up this code a bit... looks awful
            Collections.sort(shapes_hit, new Comparator<float[]>() {
                @Override
                public int compare(float[] a, float[] b) {
                        return (int) (1000000*a[1]-1000000*b[1]);
                }
        	});
              	
            if (!shapes_hit.isEmpty()) {	
            	Data.getData().setSelectedID((int) Math.round(shapes_hit.get(shapes_hit.size()-1)[0]));  
            }
		}
		
		@Override
		public void dispose(GLAutoDrawable drawable) {
			// Pass
		}

		@Override
		public void init(GLAutoDrawable drawable) {
			GL2 gl = drawable.getGL().getGL2(); 
			
			// Get GLUtilities
			glu = new GLU(); 
			gl.glEnable(GL2.GL_DEPTH_TEST);
			
			// Lighting and shading
			gl.glEnable(GLLightingFunc.GL_LIGHT0);
			gl.glEnable(GLLightingFunc.GL_LIGHTING);
			gl.glShadeModel(GLLightingFunc.GL_SMOOTH);
		}

		@Override
		public void reshape(GLAutoDrawable drawable, int arg1, int arg2, int width, int height) {
			 GL2 gl = drawable.getGL().getGL2();
		
			 // Prevents a division by 0
			 if (height == 0) {
				 height = 1;
			 }
		 
		     // Setting the viewport, the area that is displayed, to cover the whole window
		     gl.glViewport(0, 0, width, height);
		     gl.glMatrixMode(GL_PROJECTION);  
		     gl.glLoadIdentity();  
		     glu.gluOrtho2D(left, right, bottom, top);
		     gl.glMatrixMode(GL_MODELVIEW);
		     gl.glLoadIdentity();
		}
	}
}
