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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MenuBar extends JFrame {
	
	public MenuBar() {
		super("Assignment 2");
		
		// Creating the top menu items
		JMenu create = new JMenu("Create");		
		JMenu manipulate = new JMenu("Manipulate");
		JMenu application = new JMenu("Application");
		
		// Creating sub items
		JMenuItem newPyramid = new JMenuItem("New Pyramid");
		JMenuItem newSquare = new JMenuItem("New Square");
		JMenuItem newStar = new JMenuItem("New Star");
		JMenuItem newLight = new JMenuItem("New Light Source");
		create.add(newLight);
		create.add(newPyramid);
		create.add(newSquare);	
		create.add(newStar);
		
		JMenuItem manipulateMove = new JMenuItem("Move");
		JMenuItem manipulateResize = new JMenuItem("Resize");
		JMenuItem manipulateRotate = new JMenuItem("Rotate");
		JMenuItem manipulateDelete = new JMenuItem("Delete");
		JMenuItem manipulateDeleteAll = new JMenuItem("Delete All");
		manipulate.add(manipulateMove);
		manipulate.add(manipulateResize);
		manipulate.add(manipulateRotate);
		manipulate.add(manipulateDelete);
		manipulate.add(manipulateDeleteAll);
		
		JMenuItem exit = new JMenuItem("Exit");
		application.add(exit);
		
		// Creating action listeners for the items
		newPyramid.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					System.out.println("new pyramid created.");
					
					ArrayList<Float> input = DialogPyramid(MenuBar.this);
						
					float base = input.get(0);
					float height = input.get(1);
					
					float x = input.get(2);
					float y = input.get(3);
					float z = input.get(4);
					
					float red = input.get(5);
					float green = input.get(6);
					float blue = input.get(7);
					
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
					Pyramid pyramid = new Pyramid(id, x, y, z, base, height);
					pyramid.setColor(red, green, blue);
					pyramid.setAmbient(ambient_red, ambient_green, ambient_blue, ambient_alpha);
					pyramid.setSpecular(specular_red, specular_green, specular_blue, specular_alpha);
					pyramid.setDiffuse(diffuse_red, diffuse_green, diffuse_blue, diffuse_alpha);
					
					handle.addShape(pyramid);
				}
			}
		);
		
		newSquare.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					System.out.println("new square created.");
					
					ArrayList<Float> input = DialogSquare(MenuBar.this);
						
					float side = input.get(0);
					
					float x = input.get(1);
					float y = input.get(2);
					float z = input.get(3);
					
					float red = input.get(4);
					float green = input.get(5);
					float blue = input.get(6);
					
					float ambient_red = input.get(7);
					float ambient_green = input.get(8);
					float ambient_blue = input.get(9);
					float ambient_alpha = input.get(10);
					
					float specular_red = input.get(11);
					float specular_green = input.get(12);
					float specular_blue = input.get(13);
					float specular_alpha = input.get(14);
					
					float diffuse_red = input.get(15);
					float diffuse_green = input.get(16);
					float diffuse_blue = input.get(17);
					float diffuse_alpha = input.get(18);
					
					Data handle = Data.getData();
					int id = handle.getNextID();
					handle.increaseNextID();
					Square square = new Square(id, x, y, z, side);
					square.setColor(red, green, blue);
					square.setAmbient(ambient_red, ambient_green, ambient_blue, ambient_alpha);
					square.setSpecular(specular_red, specular_green, specular_blue, specular_alpha);
					square.setDiffuse(diffuse_red, diffuse_green, diffuse_blue, diffuse_alpha);
					
					handle.addShape(square);
				}
			}
		);
		
		newStar.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//TODO: Implement new star
					System.out.println("new star created.");
				}
			}
		);
		
		manipulateMove.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						//TODO: Implement move item
						System.out.println("item moved.");
					}
				}
		);
		
		manipulateRotate.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						//TODO: Implement rotate item
						System.out.println("item rotated.");
					}
				}
		);
		
		manipulateResize.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						//TODO: Implement resize item
						System.out.println("item resized.");
					}
				}
		);
		
		manipulateDelete.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						//TODO: Implement delete
						System.out.println("item deleted.");
					}
				}
		);
		
		manipulateDeleteAll.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						System.out.println("all items deleted.");
						Data handle = Data.getData();
						handle.clearShapes();
					}
				}
		);
			
		// Closes the application
		exit.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						System.out.print("application closed.");
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
		setSize(800, 600);
		//setVisible(true);
	}
	
	public void setVisibleLater() {
		setVisible(true);
	}	
	
	public ArrayList<Float> DialogPyramid(JFrame frame) {
        JPanel p = new JPanel(new BorderLayout(5, 5));

        JPanel labels = new JPanel(new GridLayout(0,1,2,2));
        
        labels.add(new JLabel("Base", SwingConstants.RIGHT));
        labels.add(new JLabel("Height", SwingConstants.RIGHT));
        
        labels.add(new JLabel("X", SwingConstants.RIGHT));
        labels.add(new JLabel("Y", SwingConstants.RIGHT));
        labels.add(new JLabel("Z", SwingConstants.RIGHT));
        
        labels.add(new JLabel("Red", SwingConstants.RIGHT));
        labels.add(new JLabel("Green", SwingConstants.RIGHT));
        labels.add(new JLabel("Blue", SwingConstants.RIGHT));
        
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
        
        // Positon 
        JTextField xfield = new JTextField("0.0");
        panel.add(xfield);
        JTextField yfield = new JTextField("0.0");
        panel.add(yfield);
        JTextField zfield = new JTextField("0.0");
        panel.add(zfield);
        
        // Color
        JTextField redfield = new JTextField("0.0");
        panel.add(redfield);
        JTextField greenfield = new JTextField("0.0");
        panel.add(greenfield);
        JTextField bluefield = new JTextField("0.0");
        panel.add(bluefield);
        
        // Material
        JTextField ambientfield = new JTextField("0.0, 0.0, 0.0, 0.0");
        panel.add(ambientfield);
        JTextField specularfield = new JTextField("0.0, 0.0, 0.0, 0.0");
        panel.add(specularfield);
        JTextField diffusefield = new JTextField("0.0, 0.0, 0.0, 0.0");
        panel.add(diffusefield);
        p.add(panel, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(frame, p, "New Pyramid Properties", JOptionPane.QUESTION_MESSAGE);
        
        float base = Float.parseFloat(basefield.getText());
        float height = Float.parseFloat(heightfield.getText()); 
        
        float x = Float.parseFloat(xfield.getText()); 
        float y = Float.parseFloat(yfield.getText()); 
        float z = Float.parseFloat(zfield.getText()); 
        
        float red = Float.parseFloat(redfield.getText()); 
        float green = Float.parseFloat(greenfield.getText()); 
        float blue = Float.parseFloat(bluefield.getText()); 
        
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
        
        res.add(red);
        res.add(green);
        res.add(blue);
        
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
	public ArrayList<Float> DialogSquare(JFrame frame) {
        JPanel p = new JPanel(new BorderLayout(5, 5));

        JPanel labels = new JPanel(new GridLayout(0,1,2,2));
        
        labels.add(new JLabel("Side", SwingConstants.RIGHT));
        
        labels.add(new JLabel("X", SwingConstants.RIGHT));
        labels.add(new JLabel("Y", SwingConstants.RIGHT));
        labels.add(new JLabel("Z", SwingConstants.RIGHT));
        
        labels.add(new JLabel("Red", SwingConstants.RIGHT));
        labels.add(new JLabel("Green", SwingConstants.RIGHT));
        labels.add(new JLabel("Blue", SwingConstants.RIGHT));
        
        labels.add(new JLabel("Ambient RGBA", SwingConstants.RIGHT));
        labels.add(new JLabel("Specular RGBA", SwingConstants.RIGHT));
        labels.add(new JLabel("Diffuse RGBA", SwingConstants.RIGHT));
        p.add(labels, BorderLayout.WEST);

        JPanel panel = new JPanel(new GridLayout(0, 1, 2, 2));
        // Side
        JTextField sidefield = new JTextField("0.0");
        panel.add(sidefield);
        
        // Positon 
        JTextField xfield = new JTextField("0.0");
        panel.add(xfield);
        JTextField yfield = new JTextField("0.0");
        panel.add(yfield);
        JTextField zfield = new JTextField("0.0");
        panel.add(zfield);
        
        // Color
        JTextField redfield = new JTextField("0.0");
        panel.add(redfield);
        JTextField greenfield = new JTextField("0.0");
        panel.add(greenfield);
        JTextField bluefield = new JTextField("0.0");
        panel.add(bluefield);
        
        // Material
        JTextField ambientfield = new JTextField("0.0, 0.0, 0.0, 0.0");
        panel.add(ambientfield);
        JTextField specularfield = new JTextField("0.0, 0.0, 0.0, 0.0");
        panel.add(specularfield);
        JTextField diffusefield = new JTextField("0.0, 0.0, 0.0, 0.0");
        panel.add(diffusefield);
        p.add(panel, BorderLayout.CENTER);
        
        JOptionPane.showMessageDialog(frame, p, "New Square Properties", JOptionPane.QUESTION_MESSAGE);
        
        float side = Float.parseFloat(sidefield.getText()); 
        
        float x = Float.parseFloat(xfield.getText()); 
        float y = Float.parseFloat(yfield.getText()); 
        float z = Float.parseFloat(zfield.getText()); 
        
        float red = Float.parseFloat(redfield.getText()); 
        float green = Float.parseFloat(greenfield.getText()); 
        float blue = Float.parseFloat(bluefield.getText()); 
        
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
        
        res.add(red);
        res.add(green);
        res.add(blue);
        
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
}
