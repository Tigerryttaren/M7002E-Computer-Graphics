package assignment2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.jogamp.opengl.util.Animator;

@SuppressWarnings("serial")
public class MenuBar extends JFrame {
	private final Animator animator;
	
	public MenuBar(Animator animator) {
		super("Assignment 2");
		this.animator = animator;
		
		// Creating the top menu items
		JMenu create = new JMenu("Create");		
		JMenu manipulate = new JMenu("Manipulate");
		JMenu application = new JMenu("Application");
		
		// Creating sub items
		JMenuItem newCube = new JMenuItem("New Cube");
		JMenuItem newSphere = new JMenuItem("New Sphere");
		JMenuItem newCylinder = new JMenuItem("New Cylinder");
		JMenuItem newLight = new JMenuItem("Create New Light Source");
		
		create.add(newLight);
		create.add(newCube);
		create.add(newSphere);
		create.add(newCylinder);
		
		JMenuItem manipulateMove = new JMenuItem("Move");
		JMenuItem manipulateRotate = new JMenuItem("Rotate");
		JMenuItem manipulateResize = new JMenuItem("Resize");
		JMenuItem manipulateDelete = new JMenuItem("Delete");
		JMenuItem manipulateDeleteAll = new JMenuItem("Delete All");
		
		manipulate.add(manipulateMove);
		manipulate.add(manipulateRotate);
		manipulate.add(manipulateResize);
		manipulate.add(manipulateDelete);
		manipulate.add(manipulateDeleteAll);
		
		JMenuItem print = new JMenuItem("Print Data Structure");
		application.add(print);
		JMenuItem exit = new JMenuItem("Exit");
		application.add(exit);
		
		// Creating action listeners for the items
		newCylinder.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						ArrayList<Float> input = DialogCylinder(MenuBar.this);
							
						float base = input.get(0);
						float height = input.get(1);
						
						// To handle if user exist the dialog
						if (base == 0.0) {
							return;
						}
												
						float x = input.get(2);
						float y = input.get(3);
						float z = input.get(4);
						
						float angle = input.get(5);
						float rotationx = input.get(6);
						float rotationy = input.get(7);
						float rotationz = input.get(8);
						
						float ambient_red = input.get(9);
						float ambient_green = input.get(10);
						float ambient_blue = input.get(11);
						float ambient_alpha = input.get(12);
						
						float specular_red = input.get(13);
						float specular_green = input.get(14);
						float specular_blue = input.get(15);
						float specular_alpha = input.get(16);
						
						float diffuse_red = input.get(17);
						float diffuse_green = input.get(18);
						float diffuse_blue = input.get(19);
						float diffuse_alpha = input.get(20);
						
						Data handle = Data.getData();
						int id = handle.getNextID();
						handle.increaseNextID();
						Cylinder cylinder = new Cylinder(id, x, y, z, base, height);
						cylinder.setRotation(angle, rotationx, rotationy, rotationz);
						cylinder.setAmbient(ambient_red, ambient_green, ambient_blue, ambient_alpha);
						cylinder.setSpecular(specular_red, specular_green, specular_blue, specular_alpha);
						cylinder.setDiffuse(diffuse_red, diffuse_green, diffuse_blue, diffuse_alpha);
						
						handle.addShape(cylinder);
					}
				}
			);

		newCube.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {					
						ArrayList<Float> input = DialogCube(MenuBar.this);
							
						float side = input.get(0);
						
						// To handle if user exist the dialog
						if (side == 0.0) {
							return;
						}
						
						float x = input.get(1);
						float y = input.get(2);
						float z = input.get(3);
						
						float angle = input.get(4);
						float rotationx = input.get(5);
						float rotationy = input.get(6);
						float rotationz = input.get(7);
						
						float ambient_red = input.get(8);
						float ambient_green = input.get(9);
						float ambient_blue = input.get(10);
						float ambient_alpha = input.get(11);
						
						float specular_red = input.get(12);
						float specular_green = input.get(13);
						float specular_blue = input.get(14);
						float specular_alpha = input.get(15);
						
						float diffuse_red = input.get(16);
						float diffuse_green = input.get(17);
						float diffuse_blue = input.get(18);
						float diffuse_alpha = input.get(19);
						
						Data handle = Data.getData();
						int id = handle.getNextID();
						handle.increaseNextID();
						Cube cube = new Cube(id, x, y, z, side);
						cube.setRotation(angle, rotationx, rotationy, rotationz);
						cube.setAmbient(ambient_red, ambient_green, ambient_blue, ambient_alpha);
						cube.setSpecular(specular_red, specular_green, specular_blue, specular_alpha);
						cube.setDiffuse(diffuse_red, diffuse_green, diffuse_blue, diffuse_alpha);
						
						handle.addShape(cube);
					}
				}
			);
		
		newSphere.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){						
						ArrayList<Float> input = DialogSphere(MenuBar.this);
						float radius = input.get(0);
						
						// To handle if user exist the dialog
						if (radius == 0.0) {
							return;
						}
						
						float x = input.get(1);
						float y = input.get(2);
						float z = input.get(3);
						
						float angle = input.get(4);
						float rotationx = input.get(5);
						float rotationy = input.get(6);
						float rotationz = input.get(7);
						
						float ambient_red = input.get(8);
						float ambient_green = input.get(9);
						float ambient_blue = input.get(10);
						float ambient_alpha = input.get(11);
						
						float specular_red = input.get(12);
						float specular_green = input.get(13);
						float specular_blue = input.get(14);
						float specular_alpha = input.get(15);
						
						float diffuse_red = input.get(16);
						float diffuse_green = input.get(17);
						float diffuse_blue = input.get(18);
						float diffuse_alpha = input.get(19);
						
						Data handle = Data.getData();
						int id = handle.getNextID();
						handle.increaseNextID();
						Sphere sphere = new Sphere(id, x, y, z, radius);
						sphere.setRotation(angle, rotationx, rotationy, rotationz);
						sphere.setAmbient(ambient_red, ambient_green, ambient_blue, ambient_alpha);
						sphere.setSpecular(specular_red, specular_green, specular_blue, specular_alpha);
						sphere.setDiffuse(diffuse_red, diffuse_green, diffuse_blue, diffuse_alpha);
						
						handle.addShape(sphere);
					}
				}
			);
			
		newLight.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){						
						ArrayList<Float> input = DialogLight(MenuBar.this);
						
						float x = input.get(0);
						float y = input.get(1);
						float z = input.get(2);
						float w = input.get(3);
						
						float ambient_red = input.get(4);
						float ambient_green = input.get(5);
						float ambient_blue = input.get(6);
						float ambient_alpha = input.get(7);
						
						float specular_red = input.get(8);
						float specular_green = input.get(9);
						float specular_blue = input.get(10);
						float specular_alpha = input.get(11);
						
						float diffuse_red = input.get(12);
						float diffuse_green = input.get(13);
						float diffuse_blue = input.get(14);
						float diffuse_alpha = input.get(15);
						
						Data handle = Data.getData();
						Light light = new Light(0, x, y, z, w);
						light.setAmbient(ambient_red, ambient_green, ambient_blue, ambient_alpha);
						light.setSpecular(specular_red, specular_green, specular_blue, specular_alpha);
						light.setDiffuse(diffuse_red, diffuse_green, diffuse_blue, diffuse_alpha);
						
						handle.light = light;
					}
				}
		);
		
		manipulateMove.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						ArrayList<Float> input = DialogMove(MenuBar.this);
						
						float x = input.get(0);
						float y = input.get(1);
						float z = input.get(2);
						
						// To handle manipulation with no selected object
						if (Data.getData().getSelectedID() == -1) {
							return;
						}
						
						Data handle = Data.getData();
						int selectedID = handle.getSelectedID();
						try {
							handle.getShapeByID(selectedID).move(x, y, z);	
						} catch (Exception move_e) {
							move_e.printStackTrace();
						}
					}
				}
		);
		
		manipulateRotate.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						ArrayList<Float> input = DialogRotate(MenuBar.this);
						float angle = input.get(0);
						float x = input.get(1);
						float y = input.get(2);
						float z = input.get(3);
						
						// To handle manipulation with no selected object
						if (Data.getData().getSelectedID() == -1) {
							return;
						}
						
						Data handle = Data.getData();
						int selectedID = handle.getSelectedID();
						try {
							handle.getShapeByID(selectedID).rotate(angle, x, y, z);	
						} catch (Exception rotate_e) {
							rotate_e.printStackTrace();
						}
					}
				}
		);
		
		manipulateResize.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){						
						ArrayList<Float> input = DialogResize(MenuBar.this);
						float scalar = input.get(0);
						
						// To handle manipulation with no selected object
						if (Data.getData().getSelectedID() == -1) {
							return;
						}
						
						Data handle = Data.getData();
						int selectedID = handle.getSelectedID();
						try {
							handle.getShapeByID(selectedID).resize(scalar);	
						} catch (Exception resize_e) {
							resize_e.printStackTrace();
						}
					}
				}
		);
		
		manipulateDelete.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						Data handle = Data.getData();
						int selectedID = handle.getSelectedID();
						
						// To handle manipulation with no selected object
						if (Data.getData().getSelectedID() == -1) {
							return;
						}
						
						for (Shape shape : handle.getShapes()) {
							if (shape.getID() == selectedID) {
								handle.getShapes().remove(shape);
								handle.clearSelectedID();
								break;
							}
						}
					}
				}
		);
		
		manipulateDeleteAll.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						Data handle = Data.getData();
						handle.clearShapes();
						handle.clearSelectedID();	
					}
				}
		);
		
		print.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						DialogPrint(MenuBar.this);
					}
				}
		);
			
		exit.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						stopAnimator();
						System.exit(0);
					}
				}
		);
		
		// Creating the menubar
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		
		// Adding items to bar
		bar.add(application);
		bar.add(create);
		bar.add(manipulate);
		
		getContentPane();
		setSize(1200, 600);
	}
	
	public void stopAnimator() {
		animator.stop();
	}	
	
	public void setVisibleLater() {
		setVisible(true);
	}	
	
	public ArrayList<Float> DialogCylinder(JFrame frame) {
        JPanel p = new JPanel(new BorderLayout(5, 5));

        JPanel labels = new JPanel(new GridLayout(0,1,2,2));
        
        labels.add(new JLabel("Base", SwingConstants.RIGHT));
        labels.add(new JLabel("Height", SwingConstants.RIGHT));
        
        labels.add(new JLabel("X", SwingConstants.RIGHT));
        labels.add(new JLabel("Y", SwingConstants.RIGHT));
        labels.add(new JLabel("Z", SwingConstants.RIGHT));
        
        labels.add(new JLabel("Rotation Angle", SwingConstants.RIGHT));
        labels.add(new JLabel("Rotation X", SwingConstants.RIGHT));
        labels.add(new JLabel("Rotation Y", SwingConstants.RIGHT));
        labels.add(new JLabel("Rotation Z", SwingConstants.RIGHT));
        
        labels.add(new JLabel("Ambient RGBA", SwingConstants.RIGHT));
        labels.add(new JLabel("Specular RGBA", SwingConstants.RIGHT));
        labels.add(new JLabel("Diffuse RGBA", SwingConstants.RIGHT));
        p.add(labels, BorderLayout.WEST);

        JPanel panel = new JPanel(new GridLayout(0,1,2,2));
        // Base & Height
        JTextField basefield = new JTextField("0.0");
        panel.add(basefield);
        JTextField heightfield = new JTextField("0.0");
        panel.add(heightfield);
        
        // Position 
        JTextField xfield = new JTextField("0.0");
        panel.add(xfield);
        JTextField yfield = new JTextField("0.0");
        panel.add(yfield);
        JTextField zfield = new JTextField("-0.5");
        panel.add(zfield);
        
        // Rotation 
        JTextField anglefield = new JTextField("0.0");
        panel.add(anglefield);
        JTextField rotationxfield = new JTextField("0.0");
        panel.add(rotationxfield);
        JTextField rotationyfield = new JTextField("0.0");
        panel.add(rotationyfield);
        JTextField rotationzfield = new JTextField("0.0");
        panel.add(rotationzfield);
        
        // Material
        JTextField ambientfield = new JTextField("0.0, 0.0, 0.0, 1.0");
        panel.add(ambientfield);
        JTextField specularfield = new JTextField("0.0, 0.0, 0.0, 1.0");
        panel.add(specularfield);
        JTextField diffusefield = new JTextField("0.0, 0.0, 0.0, 1.0");
        panel.add(diffusefield);
        p.add(panel, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(frame, p, "New Cylinder Properties", JOptionPane.QUESTION_MESSAGE);
        
        float base = Float.parseFloat(basefield.getText());
        float height = Float.parseFloat(heightfield.getText()); 
        
        float x = Float.parseFloat(xfield.getText()); 
        float y = Float.parseFloat(yfield.getText()); 
        float z = Float.parseFloat(zfield.getText()); 
        
        float angle = Float.parseFloat(anglefield.getText()); 
        float rotationx = Float.parseFloat(rotationxfield.getText()); 
        float rotationy = Float.parseFloat(rotationyfield.getText()); 
        float rotationz = Float.parseFloat(rotationzfield.getText()); 
        
        String ambient_input = ambientfield.getText();
        String[] ambient_list = ambient_input.split(","); 
        float ambient_red = Float.parseFloat(ambient_list[0]);
        float ambient_green = Float.parseFloat(ambient_list[1]);
        float ambient_blue = Float.parseFloat(ambient_list[2]);
        float ambient_alpha = Float.parseFloat(ambient_list[3]);
        
        String specular_input = specularfield.getText();
        String[] specular_list = specular_input.split(","); 
        float specular_red = Float.parseFloat(specular_list[0]);
        float specular_green = Float.parseFloat(specular_list[1]);
        float specular_blue = Float.parseFloat(specular_list[2]);
        float specular_alpha = Float.parseFloat(specular_list[3]);
        
        String diffuse_input = diffusefield.getText();
        String[] diffuse_list = diffuse_input.split(","); 
        float diffuse_red = Float.parseFloat(diffuse_list[0]);
        float diffuse_green = Float.parseFloat(diffuse_list[1]);
        float diffuse_blue = Float.parseFloat(diffuse_list[2]);
        float diffuse_alpha = Float.parseFloat(diffuse_list[3]);
     
        ArrayList<Float> res = new ArrayList<Float>();
        res.add(base);
        res.add(height);
        
        res.add(x);
        res.add(y);
        res.add(z);
        
        res.add(angle);
        res.add(rotationx);
        res.add(rotationy);
        res.add(rotationz);
        
        res.add(ambient_red);
        res.add(ambient_green);
        res.add(ambient_blue);
        res.add(ambient_alpha);
        
        res.add(specular_red);
        res.add(specular_green);
        res.add(specular_blue);
        res.add(specular_alpha);
        
        res.add(diffuse_red);
        res.add(diffuse_green);
        res.add(diffuse_blue);
        res.add(diffuse_alpha);
        
        return res;
    }
	
	public ArrayList<Float> DialogCube(JFrame frame) {
        JPanel p = new JPanel(new BorderLayout(5, 5));

        JPanel labels = new JPanel(new GridLayout(0,1,2,2));
        
        labels.add(new JLabel("Side", SwingConstants.RIGHT));
        
        labels.add(new JLabel("X", SwingConstants.RIGHT));
        labels.add(new JLabel("Y", SwingConstants.RIGHT));
        labels.add(new JLabel("Z", SwingConstants.RIGHT));
        
        labels.add(new JLabel("Rotation Angle", SwingConstants.RIGHT));
        labels.add(new JLabel("Rotation X", SwingConstants.RIGHT));
        labels.add(new JLabel("Rotation Y", SwingConstants.RIGHT));
        labels.add(new JLabel("Rotation Z", SwingConstants.RIGHT));
        
        labels.add(new JLabel("Ambient RGBA", SwingConstants.RIGHT));
        labels.add(new JLabel("Specular RGBA", SwingConstants.RIGHT));
        labels.add(new JLabel("Diffuse RGBA", SwingConstants.RIGHT));
        p.add(labels, BorderLayout.WEST);

        JPanel panel = new JPanel(new GridLayout(0, 1, 2, 2));
        // Side
        JTextField sidefield = new JTextField("0.0");
        panel.add(sidefield);
        
        // Position 
        JTextField xfield = new JTextField("0.0");
        panel.add(xfield);
        JTextField yfield = new JTextField("0.0");
        panel.add(yfield);
        JTextField zfield = new JTextField("-0.5");
        panel.add(zfield);
        
        // Rotation 
        JTextField anglefield = new JTextField("0.0");
        panel.add(anglefield);
        JTextField rotationxfield = new JTextField("0.0");
        panel.add(rotationxfield);
        JTextField rotationyfield = new JTextField("0.0");
        panel.add(rotationyfield);
        JTextField rotationzfield = new JTextField("0.0");
        panel.add(rotationzfield);
        
        // Material
        JTextField ambientfield = new JTextField("0.0, 0.0, 0.0, 1.0");
        panel.add(ambientfield);
        JTextField specularfield = new JTextField("0.0, 0.0, 0.0, 1.0");
        panel.add(specularfield);
        JTextField diffusefield = new JTextField("0.0, 0.0, 0.0, 1.0");
        panel.add(diffusefield);
        p.add(panel, BorderLayout.CENTER);
        
        JOptionPane.showMessageDialog(frame, p, "New Square Properties", JOptionPane.QUESTION_MESSAGE);
        
        float side = Float.parseFloat(sidefield.getText()); 
        
        float x = Float.parseFloat(xfield.getText()); 
        float y = Float.parseFloat(yfield.getText()); 
        float z = Float.parseFloat(zfield.getText()); 
        
        float angle = Float.parseFloat(anglefield.getText()); 
        float rotationx = Float.parseFloat(rotationxfield.getText()); 
        float rotationy = Float.parseFloat(rotationyfield.getText()); 
        float rotationz = Float.parseFloat(rotationzfield.getText()); 
        
        String ambient_input = ambientfield.getText();
        String[] ambient_list = ambient_input.split(","); 
        float ambient_red = Float.parseFloat(ambient_list[0]);
        float ambient_green = Float.parseFloat(ambient_list[1]);
        float ambient_blue = Float.parseFloat(ambient_list[2]);
        float ambient_alpha = Float.parseFloat(ambient_list[3]);
        
        String specular_input = specularfield.getText();
        String[] specular_list = specular_input.split(","); 
        float specular_red = Float.parseFloat(specular_list[0]);
        float specular_green = Float.parseFloat(specular_list[1]);
        float specular_blue = Float.parseFloat(specular_list[2]);
        float specular_alpha = Float.parseFloat(specular_list[3]);
        
        String diffuse_input = diffusefield.getText();
        String[] diffuse_list = diffuse_input.split(","); 
        float diffuse_red = Float.parseFloat(diffuse_list[0]);
        float diffuse_green = Float.parseFloat(diffuse_list[1]);
        float diffuse_blue = Float.parseFloat(diffuse_list[2]);
        float diffuse_alpha = Float.parseFloat(diffuse_list[3]);
     
        ArrayList<Float> res = new ArrayList<Float>();
        res.add(side);
        
        res.add(x);
        res.add(y);
        res.add(z);
        
        res.add(angle);
        res.add(rotationx);
        res.add(rotationy);
        res.add(rotationz);
        
        res.add(ambient_red);
        res.add(ambient_green);
        res.add(ambient_blue);
        res.add(ambient_alpha);
        
        res.add(specular_red);
        res.add(specular_green);
        res.add(specular_blue);
        res.add(specular_alpha);
        
        res.add(diffuse_red);
        res.add(diffuse_green);
        res.add(diffuse_blue);
        res.add(diffuse_alpha);
        
        return res;
    }
	
	public ArrayList<Float> DialogSphere(JFrame frame) {
        JPanel p = new JPanel(new BorderLayout(5, 5));

        JPanel labels = new JPanel(new GridLayout(0,1,2,2));
        
        labels.add(new JLabel("Radius", SwingConstants.RIGHT));
        
        labels.add(new JLabel("X", SwingConstants.RIGHT));
        labels.add(new JLabel("Y", SwingConstants.RIGHT));
        labels.add(new JLabel("Z", SwingConstants.RIGHT));
        
        labels.add(new JLabel("Rotation Angle", SwingConstants.RIGHT));
        labels.add(new JLabel("Rotation X", SwingConstants.RIGHT));
        labels.add(new JLabel("Rotation Y", SwingConstants.RIGHT));
        labels.add(new JLabel("Rotation Z", SwingConstants.RIGHT));
        
        labels.add(new JLabel("Ambient RGBA", SwingConstants.RIGHT));
        labels.add(new JLabel("Specular RGBA", SwingConstants.RIGHT));
        labels.add(new JLabel("Diffuse RGBA", SwingConstants.RIGHT));
        p.add(labels, BorderLayout.WEST);

        JPanel panel = new JPanel(new GridLayout(0, 1, 2, 2));
        // Radius
        JTextField radiusfield = new JTextField("0.0");
        panel.add(radiusfield);
        
        // Position 
        JTextField xfield = new JTextField("0.0");
        panel.add(xfield);
        JTextField yfield = new JTextField("0.0");
        panel.add(yfield);
        JTextField zfield = new JTextField("-0.5");
        panel.add(zfield);
        
        // Rotation 
        JTextField anglefield = new JTextField("0.0");
        panel.add(anglefield);
        JTextField rotationxfield = new JTextField("0.0");
        panel.add(rotationxfield);
        JTextField rotationyfield = new JTextField("0.0");
        panel.add(rotationyfield);
        JTextField rotationzfield = new JTextField("0.0");
        panel.add(rotationzfield);
        
        // Material
        JTextField ambientfield = new JTextField("0.0, 0.0, 0.0, 1.0");
        panel.add(ambientfield);
        JTextField specularfield = new JTextField("0.0, 0.0, 0.0, 1.0");
        panel.add(specularfield);
        JTextField diffusefield = new JTextField("0.0, 0.0, 0.0, 1.0");
        panel.add(diffusefield);
        p.add(panel, BorderLayout.CENTER);
        
        JOptionPane.showMessageDialog(frame, p, "New Sphere Properties", JOptionPane.QUESTION_MESSAGE);
        
        float radius = Float.parseFloat(radiusfield.getText()); 
        
        float x = Float.parseFloat(xfield.getText()); 
        float y = Float.parseFloat(yfield.getText()); 
        float z = Float.parseFloat(zfield.getText()); 
        
        float angle = Float.parseFloat(anglefield.getText()); 
        float rotationx = Float.parseFloat(rotationxfield.getText()); 
        float rotationy = Float.parseFloat(rotationyfield.getText()); 
        float rotationz = Float.parseFloat(rotationzfield.getText()); 
        
        String ambient_input = ambientfield.getText();
        String[] ambient_list = ambient_input.split(","); 
        float ambient_red = Float.parseFloat(ambient_list[0]);
        float ambient_green = Float.parseFloat(ambient_list[1]);
        float ambient_blue = Float.parseFloat(ambient_list[2]);
        float ambient_alpha = Float.parseFloat(ambient_list[3]);
        
        String specular_input = specularfield.getText();
        String[] specular_list = specular_input.split(","); 
        float specular_red = Float.parseFloat(specular_list[0]);
        float specular_green = Float.parseFloat(specular_list[1]);
        float specular_blue = Float.parseFloat(specular_list[2]);
        float specular_alpha = Float.parseFloat(specular_list[3]);
        
        String diffuse_input = diffusefield.getText();
        String[] diffuse_list = diffuse_input.split(","); 
        float diffuse_red = Float.parseFloat(diffuse_list[0]);
        float diffuse_green = Float.parseFloat(diffuse_list[1]);
        float diffuse_blue = Float.parseFloat(diffuse_list[2]);
        float diffuse_alpha = Float.parseFloat(diffuse_list[3]);
     
        ArrayList<Float> res = new ArrayList<Float>();
        res.add(radius);
        
        res.add(x);
        res.add(y);
        res.add(z);
        
        res.add(angle);
        res.add(rotationx);
        res.add(rotationy);
        res.add(rotationz);
      
        res.add(ambient_red);
        res.add(ambient_green);
        res.add(ambient_blue);
        res.add(ambient_alpha);
        
        res.add(specular_red);
        res.add(specular_green);
        res.add(specular_blue);
        res.add(specular_alpha);
        
        res.add(diffuse_red);
        res.add(diffuse_green);
        res.add(diffuse_blue);
        res.add(diffuse_alpha);
        
        return res;
    }
	
	public ArrayList<Float> DialogLight(JFrame frame) {
        JPanel p = new JPanel(new BorderLayout(5, 5));

        JPanel labels = new JPanel(new GridLayout(0,1,2,2));
        
        labels.add(new JLabel("X", SwingConstants.RIGHT));
        labels.add(new JLabel("Y", SwingConstants.RIGHT));
        labels.add(new JLabel("Z", SwingConstants.RIGHT));
        labels.add(new JLabel("W", SwingConstants.RIGHT));
        
        labels.add(new JLabel("Ambient RGBA", SwingConstants.RIGHT));
        labels.add(new JLabel("Specular RGBA", SwingConstants.RIGHT));
        labels.add(new JLabel("Diffuse RGBA", SwingConstants.RIGHT));
        p.add(labels, BorderLayout.WEST);

        JPanel panel = new JPanel(new GridLayout(0, 1, 2, 2));
        
        // Position 
        JTextField xfield = new JTextField("1.0");
        panel.add(xfield);
        JTextField yfield = new JTextField("1.0");
        panel.add(yfield);
        JTextField zfield = new JTextField("1.0");
        panel.add(zfield);
        JTextField wfield = new JTextField("0.0");
        panel.add(wfield);
        
        // Material
        JTextField ambientfield = new JTextField("1.0, 1.0, 1.0, 1.0");
        panel.add(ambientfield);
        JTextField specularfield = new JTextField("1.0, 1.0, 1.0, 1.0");
        panel.add(specularfield);
        JTextField diffusefield = new JTextField("1.0, 1.0, 1.0, 1.0");
        panel.add(diffusefield);
        p.add(panel, BorderLayout.CENTER);
        
        JOptionPane.showMessageDialog(frame, p, "New Light Source Properties", JOptionPane.QUESTION_MESSAGE);
        
        float x = Float.parseFloat(xfield.getText()); 
        float y = Float.parseFloat(yfield.getText()); 
        float z = Float.parseFloat(zfield.getText()); 
        float w = Float.parseFloat(wfield.getText()); 
        
        String ambient_input = ambientfield.getText();
        String[] ambient_list = ambient_input.split(","); 
        float ambient_red = Float.parseFloat(ambient_list[0]);
        float ambient_green = Float.parseFloat(ambient_list[1]);
        float ambient_blue = Float.parseFloat(ambient_list[2]);
        float ambient_alpha = Float.parseFloat(ambient_list[3]);
        
        String specular_input = specularfield.getText();
        String[] specular_list = specular_input.split(","); 
        float specular_red = Float.parseFloat(specular_list[0]);
        float specular_green = Float.parseFloat(specular_list[1]);
        float specular_blue = Float.parseFloat(specular_list[2]);
        float specular_alpha = Float.parseFloat(specular_list[3]);
        
        String diffuse_input = diffusefield.getText();
        String[] diffuse_list = diffuse_input.split(","); 
        float diffuse_red = Float.parseFloat(diffuse_list[0]);
        float diffuse_green = Float.parseFloat(diffuse_list[1]);
        float diffuse_blue = Float.parseFloat(diffuse_list[2]);
        float diffuse_alpha = Float.parseFloat(diffuse_list[3]);
     
        ArrayList<Float> res = new ArrayList<Float>();
        
        res.add(x);
        res.add(y);
        res.add(z);
        res.add(w);
        
        res.add(ambient_red);
        res.add(ambient_green);
        res.add(ambient_blue);
        res.add(ambient_alpha);
        
        res.add(specular_red);
        res.add(specular_green);
        res.add(specular_blue);
        res.add(specular_alpha);
        
        res.add(diffuse_red);
        res.add(diffuse_green);
        res.add(diffuse_blue);
        res.add(diffuse_alpha);
        
        return res;
	}
	
	public ArrayList<Float> DialogMove(JFrame frame) {
        JPanel p = new JPanel(new BorderLayout(5, 5));

        JPanel labels = new JPanel(new GridLayout(0,1,2,2));
        labels.add(new JLabel("New X", SwingConstants.RIGHT));
        labels.add(new JLabel("New Y", SwingConstants.RIGHT));
        labels.add(new JLabel("New Z", SwingConstants.RIGHT));
        p.add(labels, BorderLayout.WEST);

        JPanel panel = new JPanel(new GridLayout(0, 1, 2, 2));      
        // Getting the current values
        float curr_x = 0.0f;
    	float curr_y = 0.0f;
    	float curr_z = 0.0f;
    	
        if (Data.getData().getSelectedID() > -1) {
        	try {
        	curr_x = Data.getData().getShapeByID(Data.getData().getSelectedID()).getX();
        	curr_y = Data.getData().getShapeByID(Data.getData().getSelectedID()).getY();
        	curr_z = Data.getData().getShapeByID(Data.getData().getSelectedID()).getZ();
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        }
        
        // Position
        JTextField xfield = new JTextField("" + curr_x);
        panel.add(xfield);
        JTextField yfield = new JTextField("" + curr_y);
        panel.add(yfield);
        JTextField zfield = new JTextField("" + curr_z);
        panel.add(zfield);
        
        p.add(panel, BorderLayout.CENTER);
        
        JOptionPane.showMessageDialog(frame, p, "Move Shape", JOptionPane.QUESTION_MESSAGE);
        
        float x = Float.parseFloat(xfield.getText()); 
        float y = Float.parseFloat(yfield.getText()); 
        float z = Float.parseFloat(zfield.getText()); 
       
        ArrayList<Float> res = new ArrayList<Float>();
  
        res.add(x);
        res.add(y);
        res.add(z);
      
        return res;
    }
	
	public ArrayList<Float> DialogRotate(JFrame frame) {
        JPanel p = new JPanel(new BorderLayout(5, 5));

        JPanel labels = new JPanel(new GridLayout(0,1,2,2));
        labels.add(new JLabel("Rotate Angle", SwingConstants.RIGHT));
        labels.add(new JLabel("Around X", SwingConstants.RIGHT));
        labels.add(new JLabel("Around Y", SwingConstants.RIGHT));
        labels.add(new JLabel("Around Z", SwingConstants.RIGHT));
        p.add(labels, BorderLayout.WEST);

        JPanel panel = new JPanel(new GridLayout(0, 1, 2, 2));      
        // Getting the current values
        float curr_angle = 0.0f;
        float curr_x = 0.0f;
    	float curr_y = 0.0f;
    	float curr_z = 0.0f;
    	
        if (Data.getData().getSelectedID() > -1) {
        	try {
        		curr_angle = Data.getData().getShapeByID(Data.getData().getSelectedID()).getAngle();
        		curr_x = Data.getData().getShapeByID(Data.getData().getSelectedID()).getRotateX();
        		curr_y = Data.getData().getShapeByID(Data.getData().getSelectedID()).getRotateY();
        		curr_z = Data.getData().getShapeByID(Data.getData().getSelectedID()).getRotateZ();
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        }
        
        // Rotation
        JTextField anglefield = new JTextField("" + curr_angle);
        panel.add(anglefield);
        JTextField xfield = new JTextField("" + curr_x);
        panel.add(xfield);
        JTextField yfield = new JTextField("" + curr_y);
        panel.add(yfield);
        JTextField zfield = new JTextField("" + curr_z);
        panel.add(zfield);
        
        p.add(panel, BorderLayout.CENTER);
        
        JOptionPane.showMessageDialog(frame, p, "Rotate Shape", JOptionPane.QUESTION_MESSAGE);
        
        float angle = Float.parseFloat(anglefield.getText()); 
        float x = Float.parseFloat(xfield.getText()); 
        float y = Float.parseFloat(yfield.getText()); 
        float z = Float.parseFloat(zfield.getText()); 
       
        ArrayList<Float> res = new ArrayList<Float>();
  
        res.add(angle);
        res.add(x);
        res.add(y);
        res.add(z);
      
        return res;
    }
	
	public ArrayList<Float> DialogResize(JFrame frame) {
        JPanel p = new JPanel(new BorderLayout(5, 5));

        JPanel labels = new JPanel(new GridLayout(0,1,2,2));
        labels.add(new JLabel("Resize Scalar", SwingConstants.RIGHT));
        p.add(labels, BorderLayout.WEST);

        JPanel panel = new JPanel(new GridLayout(0, 1, 2, 2));      
        // Scalar 
        JTextField scalarfield = new JTextField("0.0");
        panel.add(scalarfield);        
        p.add(panel, BorderLayout.CENTER);
        
        JOptionPane.showMessageDialog(frame, p, "Resize Shape", JOptionPane.QUESTION_MESSAGE);
        
        float scalar = Float.parseFloat(scalarfield.getText());  
        ArrayList<Float> res = new ArrayList<Float>();
        res.add(scalar);

        return res;
    }
	
	public void DialogPrint(JFrame frame) {
		ArrayList<Shape> shapes = Data.getData().getShapes();
		String printout = "Datastructure{\n";
		
		for (Shape shape : shapes) {
			printout = printout + shape.print() + "\n";	
		}
		
		printout = printout + "}";
	
        JPanel p = new JPanel(new BorderLayout(5, 5));
        JTextArea text = new JTextArea(printout);
        p.add(text);
        JOptionPane.showMessageDialog(frame, p, "Printed Datastructure", JOptionPane.QUESTION_MESSAGE);
    }
}
