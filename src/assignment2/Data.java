package assignment2;

import java.util.ArrayList;

public class Data {
	// Reference to singleton object
	private static Data reference;
	private int nextID = 0;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	public Light light;
	private int currentSelectedID = -1;
	
	private Data() {
		// Pass
	}

	public static Data getData() {
		// If no instance exists, make one, otherwise use reference
		if (reference == null)
			reference = new Data();		
		return reference;
	}

	public Object clone() throws CloneNotSupportedException {
		// Prevents cloning
		throw new CloneNotSupportedException(); 
	}
	
	public int getSelectedID(){
		return this.currentSelectedID;
	}
	
	public void setSelectedID(int id){
		this.currentSelectedID = id;
	}
	
	public void clearSelectedID(){
		this.currentSelectedID = -1;
	}
	
	public ArrayList<Shape> getShapes() {
		return this.shapes;
	}	
	
	public void clearShapes() {
		this.shapes.clear();
		clearNextID();
	}
	
	public void clearNextID() {
		this.nextID = 0;
	}
	
	public int getNextID(){
		return this.nextID;
	}
	
	public void increaseNextID(){
		this.nextID++;
	};
	
	public void addShape(Shape shape) {
		shapes.add(shape);
	}
	
	public Shape getShapeByID(int id) throws Exception  {
		for (Shape shape : this.shapes) {
			if (shape.getID() == id) {
				return shape;
			}	
		}
		throw new Exception();	
	}
}