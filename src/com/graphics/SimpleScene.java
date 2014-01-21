package com.graphics;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;

public class SimpleScene {
	
	public static void main(String[] args) {
		GLProfile.initSingleton();
		
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		GLCanvas canvas = new GLCanvas(caps);
		
		Frame frame = new Frame("Standard Test Window");
		frame.setSize(300, 300);
		frame.add(canvas);
		frame.setVisible(true);
		
		// Fixes so the exit button exits the program
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
	}
}
