package assignment2;

import static javax.media.opengl.GL2.GL_ALL_ATTRIB_BITS;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.gl2.GLUT;

public class Cube extends Shape {
	private float side;
	
	public Cube(int id, float x, float y, float z, float side){
		super(id, x, y, z);
		this.side = side;
	}

	public float getSide(){
		return this.side;
	}
	
	public void setSide(float side) {
		this.side = side;
	}
	
	public void resize(float scalar){
		this.side = scalar*side;
	}
	
	@Override
	public void draw(GL2 gl, GLU glu) {
		GLUT glut = new GLUT();
		// Pushing to matrix stack
		gl.glPushMatrix();
		gl.glPushName(getID());
				
		// Cube   
	    // Moves the figure in the (x, y, z)-axis
	    gl.glTranslatef(getX(), getY(), getZ()); 
	   
	    gl.glRotatef(getAngle(), getRotateX(), getRotateY(), getRotateZ()); 
	 
	    // Pushing to attribute stack
	    gl.glPushAttrib(GL_ALL_ATTRIB_BITS);
	    
	 	// Set material properties
        float[] ambient_vector = new float[4];
	    ambient_vector[0] = getAmbientRed();
	    ambient_vector[1] = getAmbientGreen();
	    ambient_vector[2] = getAmbientBlue();
	    ambient_vector[3] = getAmbientAlpha();
	    
	    float[] specular_vector = new float[4];
	    specular_vector[0] = getSpecularRed();
	    specular_vector[1] = getSpecularGreen();
	    specular_vector[2] = getSpecularBlue();
	    specular_vector[3] = getSpecularAlpha();
	    
	    float[] diffuse_vector = new float[4];
	    diffuse_vector[0] = getDiffuseRed();
	    diffuse_vector[1] = getDiffuseGreen();
	    diffuse_vector[2] = getDiffuseBlue();
	    diffuse_vector[3] = getDiffuseAlpha();
	    
	    gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, ambient_vector, 0);
	    gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, specular_vector, 0);
	    gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, diffuse_vector, 0);
	    
	    glut.glutSolidCube(side);
	   
	    // Popping state
	    gl.glPopAttrib();
	    gl.glPopMatrix();
	}
	
	public String print() {
		String res = "";
	
		String specs = "Specs(ID: " + getID() + ", Side: " + getSide() + ")";
		String position = "Position(X: " + getX() + ", Y: " + getY() + ", Z: " + getZ() + ")";
		String material_ambient = "Material-Ambient(R: " + getAmbientRed() + ", G: " + getAmbientGreen() + ", B: " + getAmbientBlue() + ", A: " + getAmbientAlpha() + ")";
		String material_specular = "Material-Specular(R: " + getSpecularRed() + ", G: " + getSpecularGreen() + ", B: " + getSpecularBlue() + ", A: " + getSpecularAlpha() + ")";
		String material_diffuse = "Material-Diffuse(R: " + getDiffuseRed() + ", G: " + getDiffuseGreen() + ", B: " + getDiffuseBlue() + ", A: " + getDiffuseAlpha() + ")";
		
		res = "Cube[ " + specs + ", " + position + ", " + material_ambient + ", " + material_specular + ", " + material_diffuse + "]";
		
		return res;
	}
}
