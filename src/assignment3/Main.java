package assignment3;
 
import com.jme3.app.SimpleApplication;
import com.jme3.asset.TextureKey;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

public class Main extends SimpleApplication implements ActionListener {
	
	//TODO: is this needed?
	private Spatial scene_model;
	//TODO: if there is time, change this to BetterCharacterControl so its not deprecated...
	private CharacterControl player;
	private Vector3f camera_direction = new Vector3f();
	private Vector3f camera_left = new Vector3f();
	private Vector3f walking_direction = new Vector3f();
	
	private boolean forward = false;
	private boolean left = false;
	private boolean backward = false;
	private boolean right = false;
	
	public static void main(String args[]) {
		Main app = new Main();
    	app.start();
  	}
 
	// Physics application state
	private BulletAppState bulletAppState;	
	
	Material ground_material;
	Material sky_material;	
	Material wall_material;
	Material crate_material;
	
	private RigidBodyControl ground_physical;
	private RigidBodyControl sky_physical;
	private RigidBodyControl wall_physical;
	private RigidBodyControl crate_physical;
	
	private static final Box ground;
	private static final Box sky;
	private static final Box wall;
	private static final Box crate;
	
	static {
		// Ground geometry
		ground = new Box(100f, 0.1f, 100f);
    	ground.scaleTextureCoordinates(new Vector2f(3, 6));
    	
    	sky = new Box(100f, 0.1f, 100f);
    	sky.scaleTextureCoordinates(new Vector2f(3, 6));
    	
    	wall = new Box(0f, 100f, 100f);
    	wall.scaleTextureCoordinates(new Vector2f(3, 6));
    	
    	crate = new Box (1f, 1f, 1f);
    	crate.scaleTextureCoordinates(new Vector2f(3, 6));
	}
 
	@Override
	public void simpleInitApp() {
		bulletAppState = new BulletAppState();
		stateManager.attach(bulletAppState);
		
		// CHanging the color of the "skies"
		viewPort.setBackgroundColor(new ColorRGBA(0.3f, 0.5f, 1f, 1f));
		
		// Change near to stop clipping some objects on contact
		cam.setFrustumPerspective(45f, (float)cam.getWidth()/cam.getHeight(), 0.01f, 1000f);
		
		// Make camera to look at scene 
		cam.setLocation(new Vector3f(0, 4f, 6f));
		cam.lookAt(new Vector3f(2, 2, 0), Vector3f.UNIT_Y);
		
		initKeys();
		initMaterials();
		initGround();
		initSky();
		initWalls();
		initCrates();
		initPlayer();
		initCrossHair();
	}
	
	private void initKeys() {
		inputManager.addMapping("Forward", new KeyTrigger(KeyInput.KEY_W));
		inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
		inputManager.addMapping("Backward", new KeyTrigger(KeyInput.KEY_S));
		inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
	    inputManager.addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));
	    
	    inputManager.addListener(this, "Forward");
	    inputManager.addListener(this, "Left");
	    inputManager.addListener(this, "Backward");
	    inputManager.addListener(this, "Right"); 
	    inputManager.addListener(this, "Jump");
	}
	
	@Override
    public void simpleUpdate(float tpf) {
		rootNode.updateGeometricState();
		rootNode.updateLogicalState(tpf);
		
		camera_direction.set(cam.getDirection()).multLocal(0.6f);
		camera_left.set(cam.getLeft()).multLocal(0.4f);
		walking_direction.set(0, 0, 0);
		
		if (left == true) {
			walking_direction.addLocal(camera_left);
		}
		if (right == true) {
			walking_direction.addLocal(camera_left.negate());
		}
		if (forward == true) {
			walking_direction.addLocal(camera_direction);
		}
		if (backward == true) {
			walking_direction.addLocal(camera_direction.negate());
		}
		player.setWalkDirection(walking_direction);
		cam.setLocation(player.getPhysicsLocation());
    }
	
	public void onAction(String key_binding, boolean is_pressed, float tpf) {
		if (key_binding.equals("Forward")) {
	    	forward = is_pressed;
	    } else if (key_binding.equals("Left")) {
	    	left = is_pressed;
	    } else if (key_binding.equals("Backward")) {
	    	backward = is_pressed;
	    } else if (key_binding.equals("Right")) {
	    	right = is_pressed;
	    } else if (key_binding.equals("Jump")) {
	    	if (is_pressed) { 
	    		player.jump(); 
	    	}
	    }
	}
 
	// Materials used in the scene
	public void initMaterials() { 
		ground_material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		TextureKey ground_key = new TextureKey("black_tile.png");
		ground_key.setGenerateMips(true);
		Texture ground_texture = assetManager.loadTexture(ground_key);
		ground_texture.setWrap(WrapMode.Repeat);
		ground_material.setTexture("ColorMap", ground_texture);
		
		sky_material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		TextureKey sky_key = new TextureKey("black_tile.png");
		sky_key.setGenerateMips(true);
		Texture sky_texture = assetManager.loadTexture(sky_key);
		sky_texture.setWrap(WrapMode.Repeat);
		sky_material.setTexture("ColorMap", sky_texture);
		
		crate_material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
	    TextureKey crate_key = new TextureKey("companion.jpg");
	    //crate_key.setAsCube(true);
	    crate_key.setGenerateMips(true);
	    Texture crate_texture = assetManager.loadTexture(crate_key);
	    //crate_texture.setWrap(WrapMode.Repeat);
	    crate_material.setTexture("ColorMap", crate_texture);
		
		wall_material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
	    //TextureKey wall_key = new TextureKey("Textures/Terrain/BrickWall/BrickWall.jpg");
		TextureKey wall_key = new TextureKey("black_tile.png");
	    wall_key.setGenerateMips(true);
	    Texture wall_texture = assetManager.loadTexture(wall_key);
	    wall_texture.setWrap(WrapMode.Repeat);
	    wall_material.setTexture("ColorMap", wall_texture);
	}
	
	// Make a player
	public void initPlayer() {
		player = new CharacterControl(new CapsuleCollisionShape(1.5f, 6f, 1), 0.05f);
		player.setJumpSpeed(20); 	// Default 20
		player.setFallSpeed(30); 	// Default 30
		player.setGravity(50); 		// Default 30
		player.setPhysicsLocation(new Vector3f(0, 10, 0));
		
    	bulletAppState.getPhysicsSpace().add(player);
	}
 
	// Make solid ground and add it to scene
	public void initGround() {
		Geometry ground_geometry = new Geometry("Ground", ground);
		ground_geometry.setMaterial(ground_material);
		ground_geometry.setLocalTranslation(0, -0.1f, 0);
		this.rootNode.attachChild(ground_geometry);
		
		// Creates the ground physical with a mass 0.0f
		ground_physical = new RigidBodyControl(0.0f);
		ground_geometry.addControl(ground_physical);
		bulletAppState.getPhysicsSpace().add(ground_physical);
	}
	
	// Make solid sky and add it to scene
	public void initSky() {
		Geometry sky_geometry = new Geometry("Sky", sky);
		sky_geometry.setMaterial(sky_material);
		sky_geometry.setLocalTranslation(0, 60f, 0);
		this.rootNode.attachChild(sky_geometry);
		
		// Creates the ground physical with a mass 0.0f
		sky_physical = new RigidBodyControl(0.0f);
		sky_geometry.addControl(sky_physical);
		bulletAppState.getPhysicsSpace().add(sky_physical);
	}
	
	public void initWalls() {
		// Making the four walls
		makeWall(0, 30, 100, 2, 0, 1, 0);
		makeWall(0, 30, -100, 2, 0, 1, 0);
		makeWall(100, 30, 0, 2, 0, 0, 0);
		makeWall(-100, 30, 0, 2, 0, 0, 0);
	}
	
	private void makeWall(float trans_x, float trans_y, float trans_z, float rad, float rot_x, float rot_y, float rot_z) {
		Geometry wall_geometry = new Geometry("Wall", wall);
		wall_geometry.setMaterial(wall_material);
		
		// Translating the wall to its location
		wall_geometry.setLocalTranslation(trans_x, trans_y, trans_z);
		
		// Using a quaternion to save a rotation to be used on the wall
		Quaternion rotate90 = new Quaternion(); 
		rotate90.fromAngleAxis(FastMath.PI/rad, new Vector3f(rot_x, rot_y, rot_z));  
		wall_geometry.setLocalRotation(rotate90);
		
		this.rootNode.attachChild(wall_geometry);
		
		// Creates the ground physical with a mass 0.0f
		wall_physical = new RigidBodyControl(0.0f);
		wall_geometry.addControl(wall_physical);
		bulletAppState.getPhysicsSpace().add(wall_physical);
	}
	
	public void initCrates() {
		makeCrate(10, 6, 10, 2, 0, 0, 0, 1.5f);		
	}
	
	private void makeCrate(float trans_x, float trans_y, float trans_z, float rad, float rot_x, float rot_y, float rot_z, float scale) {
		Geometry crate_geometry = new Geometry("Crate", crate);
		crate_geometry.setMaterial(crate_material);
		this.rootNode.attachChild(crate_geometry);
		// Translating the wall to its location
		crate_geometry.setLocalTranslation(trans_x, trans_y, trans_z);
		crate_geometry.setLocalScale(scale);
		
		// Using a quaternion to save a rotation to be used on the wall
		Quaternion rotate90 = new Quaternion(); 
		rotate90.fromAngleAxis(FastMath.PI/rad, new Vector3f(rot_x, rot_y, rot_z));  
		crate_geometry.setLocalRotation(rotate90);
		
		crate_geometry.getMesh().scaleTextureCoordinates(new Vector2f(0.335f, 0.166f));
		
		// Creates the physical with a mass 0.0f
		crate_physical = new RigidBodyControl(1f);
		crate_geometry.addControl(crate_physical);
		bulletAppState.getPhysicsSpace().add(crate_physical);
	}
 
	// Crosshair design from HelloPhysics.java
	protected void initCrossHair() {
		guiNode.detachAllChildren();
		guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
		BitmapText crosshairs = new BitmapText(guiFont, false);
		crosshairs.setSize(guiFont.getCharSet().getRenderedSize()*2);
		crosshairs.setText("+");        
		crosshairs.setLocalTranslation(settings.getWidth()/2 - guiFont.getCharSet().getRenderedSize()/(3*2), settings.getHeight()/2 + crosshairs.getLineHeight()/2, 0);
		guiNode.attachChild(crosshairs);
	}
}