package assignment4;
 
import java.util.UUID;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.TextureKey;
import com.jme3.audio.AudioNode;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.light.SpotLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.LightControl;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Cylinder;
import com.jme3.scene.shape.Sphere;
import com.jme3.shadow.PointLightShadowRenderer;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import com.jme3.ui.Picture;

@SuppressWarnings("deprecation")
public class Main extends SimpleApplication implements ActionListener {
	
	/*************************************
	 * 
	 * Constants and Globals
	 * 
	 *************************************/
	
	private CharacterControl player;
	private Vector3f camera_direction = new Vector3f();
	private Vector3f camera_left = new Vector3f();
	private Vector3f walking_direction = new Vector3f();
	
	private boolean forward = false;
	private boolean left = false;
	private boolean backward = false;
	private boolean right = false;
 
	private BulletAppState bulletAppState;	
	
	private Node manipulatables;
    private Node inventory;
    private Node announcer;
    private Node proximity;
    private Node proximity_text;
    
    private Picture PAL_mode;
    private boolean PAL_text_on = false;
    
    private int camera_position = 0;
    private Vector3f last_player_camera_direction;
    private Vector3f last_PAL_camera_direction = new Vector3f(15f,  0f, 0f);

    private Vector3f last_scale;
    private RigidBodyControl last_physical;
    
    private PointLight light_ceiling;
    private PointLight light_PAL;
    
    private AudioNode audio_PAL_cantdo;
    private AudioNode audio_PAL_well;
        
    private boolean contact_PAL_cantdo = false;
    private boolean contact_PAL_well = false;
    private boolean ceiling_light_on = true;
    
	Material ground_material;
	Material ceiling_material;	
	Material wall_material;
	Material biobox_material;
	Material containmentcontainer_material;
	Material PAL_material;
	Material door_material;
	Material lamp_material;
	Material light_switch_material;
	
	private MotionPath motionpath1;
	private MotionEvent motioncontrol1;
	
	private RigidBodyControl ground_physical;
	private RigidBodyControl ceiling_physical;
	private RigidBodyControl wall_physical;
	private RigidBodyControl PAL_physical;
	private RigidBodyControl door_physical;
	private RigidBodyControl lamp_physical;
	private RigidBodyControl light_switch_physical;

	private static final Box ground;
	private static final Box ceiling;
	private static final Box wall;
	private static final Box PAL;
	private static final Box door;
	private static final Box lamp;
	private static final Box light_switch;
	
	private static final Cylinder rod;
	
	private Spatial selected_object;
	
	static {
		ground = new Box(100f, 2f, 100f);
		ground.scaleTextureCoordinates(new Vector2f(6, 6));
    	
		ceiling = new Box(100f, 2f, 100f);
		ceiling.scaleTextureCoordinates(new Vector2f(6, 6));
    	
		wall = new Box(2f, 100f, 100f);
		wall.scaleTextureCoordinates(new Vector2f(6, 6));
    	
		PAL = new Box(0.5f, 6f, 2f);
		PAL.scaleTextureCoordinates(new Vector2f(3, 6));
		
		lamp = new Box(3f, 0.1f, 3f);
		lamp.scaleTextureCoordinates(new Vector2f(1, 1));
    	
		door = new Box(0.5f, 5.3f, 4.5f);
		door.scaleTextureCoordinates(new Vector2f(3, 6));
		
		rod = new Cylinder(10, 50, 0.1f, 3f, true);
		rod.scaleTextureCoordinates(new Vector2f(0.1f, 1f));
		
		light_switch = new Box(0.2f, 0.2f, 0.2f);
		light_switch.scaleTextureCoordinates(new Vector2f(1, 1));
	}
	
	
	
	
	/*************************************
	 * 
	 * Main Method, Init, and Update 
	 * 
	 *************************************/
	
	public static void main(String args[]) {
		Main app = new Main();
		app.start();
  	}
	
	@Override
	public void simpleInitApp() {
		bulletAppState = new BulletAppState();
		stateManager.attach(bulletAppState);
		
		// Change near to stop clipping some objects on contact
		cam.setFrustumPerspective(45f, (float)cam.getWidth()/cam.getHeight(), 0.01f, 1000f);
		
		// Make camera to look at scene 
		cam.setLocation(new Vector3f(0, 4f, 6f));
		cam.lookAt(new Vector3f(-100, 15, 7), Vector3f.UNIT_Y);		
	
		rootNode.setShadowMode(ShadowMode.CastAndReceive);
		
		// Setting gravity
		bulletAppState.getPhysicsSpace().setGravity(new Vector3f(0, -9.82f*5, 0));
		
		// A node to contain all objects that can and should be manipulated in some way
		manipulatables = new Node("Manipulatables");
		announcer = new Node("Announcer");
		proximity = new Node("Proximity");
		proximity_text = new Node("ProximityText");
		inventory = new Node("Inventory");
		
		guiNode.attachChild(inventory);
		guiNode.attachChild(announcer);
		guiNode.attachChild(proximity_text);
		rootNode.attachChild(proximity);
		rootNode.attachChild(manipulatables);	
		
		// Initializing the world and everything
		initKeys();
		initMaterials();
		initGround();
		initCeiling();
		initWalls();
		initLight();
		initContainmentContainers();
		initBioBoxes();
		initPAL();
		initDrones();
		initDoor();
		initLamp();
		initLightSwitch();
		initPlayer();
		initCrossHair();
		initPALMode();
		initRods();	
		initMotions();
		initAudio();
	}
		
	@Override
	public void simpleUpdate(float tpf) {
		
		// Setting the camera
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
		
		if (camera_position == 0) {
			cam.setLocation(player.getPhysicsLocation());
		} else if (camera_position == 1) {
			cam.setLocation(new Vector3f(-97f, 26.5f, 0));
			
			// Limiting Camera Panning
			Vector3f dir = new Vector3f(1f, 0f, 0f);
			
			if (dir.angleBetween(cam.getDirection()) > 1f) {
				cam.lookAtDirection(new Vector3f(1.1f - cam.getDirection().x, cam.getDirection().y, cam.getDirection().z), new Vector3f(0, cam.getUp().y, 0));
			} 	
		}
		
		Spatial door = proximity.getChild("Door");
		if (door.getControl(RigidBodyControl.class).getPhysicsLocation().distance(cam.getLocation()) < 25f) {
			if (contact_PAL_cantdo == false) {
				
				// Changing the lights
				light_PAL.setColor(ColorRGBA.Red);
				light_PAL.setRadius(60000f);
				if (ceiling_light_on == true) {
					light_ceiling.setRadius(0f);
				}
				
				audio_PAL_cantdo.play();
				contact_PAL_cantdo = true;
			}
		} else {
			// Resetting the lights
			light_PAL.setColor(ColorRGBA.White);
			if (ceiling_light_on == true) {
				light_ceiling.setRadius(600f);
			}
			contact_PAL_cantdo = false;
		}
		
		Spatial pal = proximity.getChild("PAL9001");
		if (pal.getControl(RigidBodyControl.class).getPhysicsLocation().distance(cam.getLocation()) < 50f) {
			if (contact_PAL_well == false) {
			
				audio_PAL_well.play();
				contact_PAL_well = true;
			}
		} else {
			contact_PAL_well = false;
		}
	
		motioncontrol1.play();
		
		// Handling the text announcements
		if (!(selected_object == null)) {
			guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
			BitmapText text = new BitmapText(guiFont, false);
			text.setSize(guiFont.getCharSet().getRenderedSize()*2);
			
			if (selected_object.getName().equals("Materializer")) {
				text.setText("Materializer");        
			} else if (selected_object.getName().equals("De-Materializer")) {
				text.setText("De-Materializer");      
			} else if (selected_object.getName().equals("Redshifter")) {
				text.setText("Redshifter");      
			} else if (selected_object.getName().equals("Blueshifter")) {
				text.setText("Blueshifter");      
			} else {
				text.setText("");
			}
			text.setLocalTranslation(settings.getWidth()/38 - guiFont.getCharSet().getRenderedSize()/(3*2), settings.getHeight()/12 + text.getLineHeight()/6, 0);
			announcer.attachChild(text);
		}
	}
	
	
	
	
	/*************************************
	 * 
	 * (Monolithic) Action Listener Method
	 * 
	 *************************************/
	
	// Actions performed when button is pressed
	public void onAction(String key_binding, boolean is_pressed, float tpf) {
		if (key_binding.equals("Switch") && selected_object == null) {		
			if (is_pressed == true) {
				if (camera_position == 0) {
					// Switching to PAL9001 mode
					last_player_camera_direction = cam.getDirection();
					//last_player_camera_location = cam.getLocation();
					
					guiNode.attachChild(PAL_mode);
					
					cam.lookAtDirection(last_PAL_camera_direction, Vector3f.UNIT_Y);
					left = false;
					right = false;
					forward = false;
					backward = false;
					camera_position = 1;
					
				} else if (camera_position == 1) {
					// Switching to player mode
					
					guiNode.detachChild(PAL_mode);
					last_PAL_camera_direction = cam.getDirection();
					cam.lookAtDirection(last_player_camera_direction, Vector3f.UNIT_Y);
					camera_position = 0;					
				}
			}
		} else if (key_binding.equals("Switch") && selected_object != null) {
			guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
			BitmapText text = new BitmapText(guiFont, false);
			text.setSize(guiFont.getCharSet().getRenderedSize()*2);
			text.setText("Put down the item!");        
			text.setLocalTranslation(settings.getWidth()/1.5f - guiFont.getCharSet().getRenderedSize()/(3*2), settings.getHeight()/2 + text.getLineHeight()/6, 0);
			announcer.attachChild(text);	
		} else if (camera_position == 0) {
			if (key_binding.equals("Forward")) {
					forward = is_pressed;
			} else if (key_binding.equals("Left")) {
				left = is_pressed;
			} else if (key_binding.equals("Backward")) {
				backward = is_pressed;
			} else if (key_binding.equals("Right")) {
				right = is_pressed;
			} else if (key_binding.equals("Jump")) {
				if (is_pressed == true) { 
					player.jump(); 
				}
			} else if (key_binding.equals("Throw")) { 
				if (is_pressed == true) {	
					
					if (selected_object == null) {
						return;
					} else if (inventory.getChildren().isEmpty() == false) {					
						operationDrop();
						operationThrow();
						selected_object = null;
						announcer.detachAllChildren();
					}
				}	    	
			} else if (key_binding.equals("Drop")) { 
				if (is_pressed == true) {	
					
					if (selected_object == null) {
						return;
					} else if (inventory.getChildren().isEmpty() == false) {					
						operationDrop();
						selected_object = null;
						announcer.detachAllChildren();
					}
				}	    	
			} else if (key_binding.equals("Use")) {
				if (is_pressed == true) {
					
					// For the light switch
					if (inventory.getChildren().isEmpty() == true) {
						CollisionResults collisions = new CollisionResults();
						Ray ray = new Ray(cam.getLocation(), cam.getDirection());
						rootNode.collideWith(ray, collisions);
						
						if (collisions.size() > 0) {
							CollisionResult closest = collisions.getClosestCollision();
							Spatial spatial = closest.getGeometry();
							
							if (spatial.getName().equals("LightSwitch") && spatial.getControl(RigidBodyControl.class).getPhysicsLocation().distance(cam.getLocation()) < 15f) {
								if (ceiling_light_on == true) {
									
									light_ceiling.setRadius(1f);
									ceiling_light_on = false;
									
								} else if (ceiling_light_on == false) {
									
									light_ceiling.setRadius(60000f);
									ceiling_light_on = true;
								}
							}
						}
					}
						
					// Check that you are holding the some item, else do not allow action
					if (inventory.getChildren().isEmpty() == false) {
						if (inventory.getChild(0).getName().equals("Materializer") == true) {
							operationCreate();
						} else if (inventory.getChild(0).getName().equals("De-Materializer") == true) {
							operationDestroy();
						} else if (inventory.getChild(0).getName().equals("Redshifter") == true) {
							operationEnlarge();
						} else if (inventory.getChild(0).getName().equals("Blueshifter") == true) {
							operationShrink();
						} else {
							return;
						}
					} else {
						return;
					}
				}
			} else if (key_binding.equals("Pick") && camera_position == 0) {
				if (is_pressed == false) {
					// If holding an item and clicking you put it down
					if (inventory.getChildren().isEmpty() == false) {
						operationDrop();
						selected_object = null;
						announcer.detachAllChildren();
					} else {
						CollisionResults collisions = new CollisionResults();
						Ray ray = new Ray(cam.getLocation(), cam.getDirection());
						manipulatables.collideWith(ray, collisions);
						
						if (collisions.size() > 0) {
							CollisionResult closest = collisions.getClosestCollision();
							Spatial spatial = closest.getGeometry();
							
							if (spatial.getControl(RigidBodyControl.class).getPhysicsLocation().distance(cam.getLocation()) < 25f) {
								last_scale = spatial.getLocalScale().clone();
								
								last_physical = spatial.getControl(RigidBodyControl.class);
								bulletAppState.getPhysicsSpace().remove(last_physical);
								spatial.removeControl(RigidBodyControl.class);
								
								manipulatables.detachChild(spatial);
								inventory.attachChild(spatial);
								
								// Rotating the rods for correct holding
								if (inventory.getChild(0).getName().equals("Materializer") 
										|| inventory.getChild(0).getName().equals("De-Materializer") 
										|| inventory.getChild(0).getName().equals("Redshifter") 
										|| inventory.getChild(0).getName().equals("Blueshifter")) {
									
									Quaternion rotate_x = new Quaternion(); 
									rotate_x.fromAngleAxis(FastMath.PI/2, new Vector3f(1, 0, 0));  
									
									Quaternion rotate_z = new Quaternion(); 
									rotate_z.fromAngleAxis(FastMath.PI/12, new Vector3f(0, 1, 0));  
									
									spatial.setLocalRotation(rotate_x);
									spatial.rotate(rotate_z);
									
									spatial.setLocalScale(150f);
									spatial.setLocalTranslation(settings.getWidth()/1.5f, settings.getHeight()/4, 0);	
									
								} else {
									spatial.setLocalScale(150f);
									spatial.setLocalTranslation(settings.getWidth()/2, settings.getHeight()/2, 0);	
								}
								
								selected_object = spatial;
				
							} else {
								return;
							}
						}  
					}
				}		
			} 
		} else if (camera_position == 1) {			
			if (key_binding.equals("Use")) {
				if (is_pressed == true) {
					guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
					BitmapText text = new BitmapText(guiFont, false);
					text.setSize(guiFont.getCharSet().getRenderedSize()*2);
					text.setText("I'm sorry, Dave. I'm afraid I can't do that.");        
					text.setLocalTranslation(settings.getWidth()/4 - guiFont.getCharSet().getRenderedSize()/(3*2), settings.getHeight()/1.7f + text.getLineHeight()/6, 0);
					
					if (PAL_text_on == true) {
						announcer.attachChild(text);
						PAL_text_on = false;
					} else if (PAL_text_on == false) {
						announcer.detachAllChildren();
						PAL_text_on = true;
					}
				}
			}		
		} 
	}
	
	
	
	/*************************************
	 * 
	 * User Operation Methods
	 * 
	 *************************************/
	
	public void operationDrop(){
		Spatial spatial = inventory.getChild(0);
		spatial.setLocalScale(last_scale);
		
		Vector3f location = cam.getLocation();
		Vector3f direction = cam.getDirection();
		float trans_x = location.getX() + (7) * direction.getX();
		float trans_y = location.getY() + (7) * direction.getY();
		float trans_z = location.getZ() + (7) * direction.getZ();
		Vector3f new_position = new Vector3f(trans_x, trans_y, trans_z);
		
		last_physical.setPhysicsLocation(new_position);
		spatial.setLocalTranslation(new_position);
		spatial.addControl(last_physical);
		
		bulletAppState.getPhysicsSpace().add(last_physical);
		
		last_physical.activate();
		inventory.detachAllChildren();
		manipulatables.attachChild(spatial);
	}
	
	public void operationThrow() {
		// Handling not selected any object
		if (selected_object == null) {
			return;
		}
		
		String name = selected_object.getName();
		Spatial spatial = manipulatables.getChild(name);
		Vector3f direction = cam.getDirection();
		spatial.getControl(RigidBodyControl.class).applyImpulse(direction.mult(500), new Vector3f(0, 0, 0));
	}
	
	public void operationCreate() {
		Vector3f location = cam.getLocation();
		Vector3f direction = cam.getDirection();
		
		float trans_x = location.getX() + (5) * direction.getX();
		float trans_y = location.getY() + (5) * direction.getY();
		float trans_z = location.getZ() + (5) * direction.getZ();
		float rad = 2;
		float rot_x = 0;
		float rot_y = 0;
		float rot_z = 0;
		float scale = 1;
		float mass = 9;
		String name = UUID.randomUUID().toString();
		
		manipulatables.attachChild(makeBioBox(trans_x, trans_y, trans_z, rad, rot_x, rot_y, rot_z, scale, mass, name));
	}
	
	public void operationDestroy() {
		CollisionResults collisions = new CollisionResults();
		Ray ray = new Ray(cam.getLocation(), cam.getDirection());
		manipulatables.collideWith(ray, collisions);
		
		if (collisions.size() > 0) {
			CollisionResult closest = collisions.getClosestCollision();
			Spatial spatial = closest.getGeometry();
			bulletAppState.getPhysicsSpace().remove(spatial.getControl(RigidBodyControl.class));
			spatial.removeControl(RigidBodyControl.class);
			manipulatables.detachChild(spatial);
		}  
	}
	
	public void operationEnlarge() {
		CollisionResults collisions = new CollisionResults();
		Ray ray = new Ray(cam.getLocation(), cam.getDirection());
		manipulatables.collideWith(ray, collisions);
		
		if (collisions.size() > 0) {
			CollisionResult closest = collisions.getClosestCollision();
			Spatial spatial = closest.getGeometry();
			
			bulletAppState.getPhysicsSpace().remove(spatial.getControl(RigidBodyControl.class));
			spatial.removeControl(RigidBodyControl.class);
			manipulatables.detachChild(spatial);
			
			float current_scale = spatial.getLocalScale().x; // Whichever really
			
			float trans_x = closest.getGeometry().getWorldTranslation().x; 
			float trans_y = closest.getGeometry().getWorldTranslation().y; 
			float trans_z = closest.getGeometry().getWorldTranslation().z; 
			float rad = 2;
			float rot_x = 0;
			float rot_y = 0;
			float rot_z = 0;
			float scale = current_scale * 1.1f;
			float mass = 9;
			String name = UUID.randomUUID().toString();
			
			manipulatables.attachChild(makeBioBox(trans_x, trans_y, trans_z, rad, rot_x, rot_y, rot_z, scale, mass, name));
		}  
	}
	
	public void operationShrink() {
		CollisionResults collisions = new CollisionResults();
		Ray ray = new Ray(cam.getLocation(), cam.getDirection());
		manipulatables.collideWith(ray, collisions);
		
		if (collisions.size() > 0) {
			CollisionResult closest = collisions.getClosestCollision();
			Spatial spatial = closest.getGeometry();
			
			bulletAppState.getPhysicsSpace().remove(spatial.getControl(RigidBodyControl.class));
			spatial.removeControl(RigidBodyControl.class);
			manipulatables.detachChild(spatial);
			
			float current_scale = spatial.getLocalScale().x; // Whichever really
			
			float trans_x = closest.getGeometry().getWorldTranslation().x; 
			float trans_y = closest.getGeometry().getWorldTranslation().y; 
			float trans_z = closest.getGeometry().getWorldTranslation().z; 
			float rad = 2;
			float rot_x = 0;
			float rot_y = 0;
			float rot_z = 0;
			float scale = current_scale * 0.9f;
			float mass = 9;
			String name = UUID.randomUUID().toString();
			
			manipulatables.attachChild(makeBioBox(trans_x, trans_y, trans_z, rad, rot_x, rot_y, rot_z, scale, mass, name));
		}
	}
	
	
	
	/*************************************
	 * 
	 * Initialization Methods
	 * 
	 *************************************/
	
	// Initialized the key mapping to controls work
	private void initKeys() {
		// For moving the player
		inputManager.addMapping("Forward", new KeyTrigger(KeyInput.KEY_W));
		inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
		inputManager.addMapping("Backward", new KeyTrigger(KeyInput.KEY_S));
		inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
		inputManager.addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));
	    
		inputManager.addMapping("Use", new KeyTrigger(KeyInput.KEY_E));
		inputManager.addMapping("Throw", new KeyTrigger(KeyInput.KEY_T));
		inputManager.addMapping("Pick", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
		inputManager.addMapping("Drop", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
		
		inputManager.addMapping("Switch", new KeyTrigger(KeyInput.KEY_TAB));
	   
		inputManager.addListener(this, "Forward");
		inputManager.addListener(this, "Left");
		inputManager.addListener(this, "Backward");
		inputManager.addListener(this, "Right"); 
		inputManager.addListener(this, "Jump");
		
		inputManager.addListener(this, "Throw");
		inputManager.addListener(this, "Use");
		inputManager.addListener(this, "Drop");
		inputManager.addListener(this, "Pick");
		
		inputManager.addListener(this, "Switch");
	}
	
	public void initLight() {
		// Adding a light to the HUD
		DirectionalLight hud_light = new DirectionalLight();
		hud_light.setDirection(new Vector3f(0, 0, -1.0f));
		guiNode.addLight(hud_light);
		
		// Adding a light from the ceiling
		light_ceiling = new PointLight();
		light_ceiling.setColor(ColorRGBA.White);
		light_ceiling.setRadius(600f);
		light_ceiling.setPosition(new Vector3f(0, 50, 0));
		rootNode.addLight(light_ceiling);
		
		// Adding a point light from PAL
		light_PAL = new PointLight();
		light_PAL.setColor(ColorRGBA.White);
		light_PAL.setRadius(600f);
		light_PAL.setPosition(new Vector3f(-95, 20, 0));
		rootNode.addLight(light_PAL);
		
		// Shadows
		final int SHADOWMAP_SIZE = 512;
		
		PointLightShadowRenderer plsr_ceiling = new PointLightShadowRenderer(assetManager, SHADOWMAP_SIZE);
		plsr_ceiling.setLight(light_ceiling);
        
        plsr_ceiling.setFlushQueues(false);
        
        PointLightShadowRenderer plsr_PAL = new PointLightShadowRenderer(assetManager, SHADOWMAP_SIZE);
        plsr_PAL.setLight(light_PAL);
        
        viewPort.addProcessor(plsr_ceiling);
        viewPort.addProcessor(plsr_PAL);
	}
 
	// Materials used in the scene
	public void initMaterials() { 
		ceiling_material = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
		TextureKey ceiling_key = new TextureKey("black_tile.png");
		ceiling_key.setGenerateMips(true);
		Texture ceiling_texture = assetManager.loadTexture(ceiling_key);
		ceiling_texture.setWrap(WrapMode.Repeat);
		ceiling_material.setTexture("DiffuseMap", ceiling_texture);
		
		wall_material = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
		TextureKey wall_key = new TextureKey("black_tile.png");
		wall_key.setGenerateMips(true);
		Texture wall_texture = assetManager.loadTexture(wall_key);
		wall_texture.setWrap(WrapMode.Repeat);
		wall_material.setTexture("DiffuseMap", wall_texture);
		
		ground_material = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
		TextureKey ground_key = new TextureKey("black_tile.png");
		ground_key.setGenerateMips(true);
		Texture ground_texture = assetManager.loadTexture(ground_key);
		ground_texture.setWrap(WrapMode.Repeat);
		ground_material.setTexture("DiffuseMap", ground_texture);
		
		lamp_material = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
		TextureKey lamp_key = new TextureKey("glow.jpg");
		lamp_key.setGenerateMips(true);
		Texture lamp_texture = assetManager.loadTexture(lamp_key);
		lamp_material.setTexture("DiffuseMap", lamp_texture);
		
		light_switch_material = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
		TextureKey light_switch_key = new TextureKey("glow.jpg");
		light_switch_key.setGenerateMips(true);
		Texture light_switch_texture = assetManager.loadTexture(light_switch_key);
		light_switch_material.setTexture("DiffuseMap", light_switch_texture);
	
		PAL_material = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
		TextureKey PAL_key = new TextureKey("PAL9001.jpg");
		PAL_key.setGenerateMips(true);
		Texture PAL_texture = assetManager.loadTexture(PAL_key);
		PAL_material.setTexture("DiffuseMap", PAL_texture);
	    
		door_material = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
		TextureKey door_key = new TextureKey("dark_steel_door.jpg");
		door_key.setGenerateMips(true);
		Texture door_texture = assetManager.loadTexture(door_key);
		door_material.setTexture("DiffuseMap", door_texture);
	}
	
	// Make the player
	public void initPlayer() {
		player = new CharacterControl(new CapsuleCollisionShape(1.5f, 6f), 0.05f);
		
		player.setJumpSpeed(25);
		player.setFallSpeed(40);
		player.setGravity(80);
		
		player.setPhysicsLocation(new Vector3f(0, 10, 0));
		bulletAppState.getPhysicsSpace().add(player);
	}
 
	// Make solid ground and add it to scene
	public void initGround() {
		rootNode.attachChild(makeGround());
	}
	
	private Geometry makeGround() {
		Geometry ground_geometry = new Geometry("Ground", ground);
		ground_geometry.setMaterial(ground_material);
		ground_geometry.setLocalTranslation(0, -2, 0);
		this.rootNode.attachChild(ground_geometry);
		
		ground_geometry.setShadowMode(ShadowMode.Receive);
	
		// Creates the ground physical with a mass 0.0f
		ground_physical = new RigidBodyControl(0.0f);
		
		ground_geometry.addControl(ground_physical);
		bulletAppState.getPhysicsSpace().add(ground_physical);	
		return ground_geometry;
	}
	
	// Make solid ceiling and add it to scene
	public void initCeiling() {
		rootNode.attachChild(makeCeiling());
	}
	
	private Geometry makeCeiling() {
		Geometry ceiling_geometry = new Geometry("Ceiling", ceiling);
		ceiling_geometry.setMaterial(ceiling_material);
		ceiling_geometry.setLocalTranslation(0, 62f, 0);
		this.rootNode.attachChild(ceiling_geometry);
		
		ceiling_geometry.setShadowMode(ShadowMode.Receive);
		
		// Creates the ground physical with a mass 0.0f
		ceiling_physical = new RigidBodyControl(0.0f);
		ceiling_geometry.addControl(ceiling_physical);
		bulletAppState.getPhysicsSpace().add(ceiling_physical);
		return ceiling_geometry;
	}
	
	// Making all walls
	public void initWalls() {
		rootNode.attachChild(makeWall(0, 30, 100, 2, 0, 1, 0));
		rootNode.attachChild(makeWall(0, 30, -100, 2, 0, 1, 0));
		rootNode.attachChild(makeWall(100, 30, 0, 2, 0, 0, 0));
		rootNode.attachChild(makeWall(-100, 30, 0, 2, 0, 0, 0));
	}
	
	// Making a single wall
	private Geometry makeWall(float trans_x, float trans_y, float trans_z, float rad, float rot_x, float rot_y, float rot_z) {
		Geometry wall_geometry = new Geometry("Wall", wall);
		wall_geometry.setMaterial(wall_material);
		
		// Translating the wall to its location
		wall_geometry.setLocalTranslation(trans_x, trans_y, trans_z);
		
		wall_geometry.setShadowMode(ShadowMode.Receive);
		
		// Using a quaternion to save a rotation to be used on the wall
		Quaternion rotate90 = new Quaternion(); 
		rotate90.fromAngleAxis(FastMath.PI/rad, new Vector3f(rot_x, rot_y, rot_z));  
		wall_geometry.setLocalRotation(rotate90);
		
		// Creates the wall physical with a mass 0.0f
		wall_physical = new RigidBodyControl(0.0f);
		wall_geometry.addControl(wall_physical);
		bulletAppState.getPhysicsSpace().add(wall_physical);
		return wall_geometry;
	}
	
	// Making all ContainmentContainers
	public void initContainmentContainers() {
		// Large corner crates
		rootNode.attachChild(makeContainmentContainer(50, 10.1f, 50, 2, 0, 0, 0, 10, 1000f, "ContainmentContainer1"));
		rootNode.attachChild(makeContainmentContainer(50, 30.1f, 50, 2, 0, 0, 0, 10, 1000f, "ContainmentContainer2"));
		rootNode.attachChild(makeContainmentContainer(29, 10.1f, 50, 2, 0, 0, 0, 10, 1000f, "ContainmentContainer3"));		
		rootNode.attachChild(makeContainmentContainer(50, 10.1f, 29, 2, 0, 0, 0, 10, 1000f, "ContainmentContainer4"));
	
		rootNode.attachChild(makeContainmentContainer(-50, 30.1f, 50, 2, 0, 0, 0, 10, 1000f, "ContainmentContainer5"));
		rootNode.attachChild(makeContainmentContainer(-50, 10.1f, 50, 2, 0, 0, 0, 10, 1000f, "ContainmentContainer6"));
		
		rootNode.attachChild(makeContainmentContainer(29, 10.1f, -50, 2, 0, 0, 0, 10, 1000f, "ContainmentContainer7"));
		rootNode.attachChild(makeContainmentContainer(50, 10.1f, -50, 2, 0, 0, 0, 10, 1000f, "ContainmentContainer8"));	
		rootNode.attachChild(makeContainmentContainer(50, 30.1f, -50, 2, 0, 0, 0, 10, 1000f, "ContainmentContainer9"));	
		rootNode.attachChild(makeContainmentContainer(50, 10.1f, -29, 2, 0, 0, 0, 10, 1000f, "ContainmentContainer10"));	
		
		rootNode.attachChild(makeContainmentContainer(-50, 30.1f, -50, 2, 0, 0, 0, 10, 1000f, "ContainmentContainer11"));
		rootNode.attachChild(makeContainmentContainer(-50, 10.1f, -50, 2, 0, 0, 0, 10, 1000f, "ContainmentContainer12"));
	}
	
	// Making a single ContainmentContainer
	private Geometry makeContainmentContainer(float trans_x, float trans_y, float trans_z, float rad, float rot_x, float rot_y, float rot_z, float scale, float mass, String name) {
		Box cube = new Box(1f, 1f, 1f);
		Geometry cube_geometry = new Geometry(name, cube);
		cube_geometry.setLocalTranslation(new Vector3f(trans_x, trans_y, trans_z));
		Material cube_material = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
		Texture cube_texture = assetManager.loadTexture("containmentcontainer.png");
		cube_material.setTexture("DiffuseMap", cube_texture);
	    
		// Using a quaternion to save a rotation to be used on the wall
		Quaternion rotate90 = new Quaternion(); 
		rotate90.fromAngleAxis(FastMath.PI/rad, new Vector3f(rot_x, rot_y, rot_z));
		cube_geometry.setLocalRotation(rotate90);
	 
		cube_geometry.setLocalScale(scale);
		cube_geometry.setMaterial(cube_material);
	    
		// Adding a collision box to geometry
		CollisionShape cube_shape = CollisionShapeFactory.createBoxShape(cube_geometry);
		RigidBodyControl cube_physical = new RigidBodyControl(cube_shape, mass);
			    
		cube_physical.setFriction(5f);
		cube_geometry.addControl(cube_physical);
		bulletAppState.getPhysicsSpace().add(cube_physical);
		return cube_geometry;
	}
	
	public void initRods () {
		manipulatables.attachChild(makeRod("Materializer", "rod_white.jpg", -50, 2, 5));
		manipulatables.attachChild(makeRod("De-Materializer", "rod_black.jpg", -50, 2, -5));
		manipulatables.attachChild(makeRod("Redshifter", "rod_red.jpg", -40, 2, 5));
		manipulatables.attachChild(makeRod("Blueshifter", "rod_blue.jpg", -40, 2, -5));		
	}
	
	private Geometry makeRod(String name, String texture_path, float trans_x, float trans_y, float trans_z) {
		Geometry rod_geometry = new Geometry(name, rod);
		rod_geometry.setLocalTranslation(new Vector3f(trans_x, trans_y, trans_z));
		Material rod_material = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
		Texture rod_texture = assetManager.loadTexture(texture_path);
		rod_material.setTexture("DiffuseMap", rod_texture);
	    
		rod_geometry.setLocalScale(1f);
		rod_geometry.setMaterial(rod_material);
	    
		// Adding a collision box to geometry
		CollisionShape creator_shape = CollisionShapeFactory.createBoxShape(rod_geometry);
		RigidBodyControl rod_physical = new RigidBodyControl(creator_shape, 10f);
	    	    
		rod_physical.setFriction(5f);
		rod_geometry.addControl(rod_physical);
		bulletAppState.getPhysicsSpace().add(rod_physical);
		return rod_geometry;
	}
	
	// Making the BioBoxes
	public void initBioBoxes() {
		// BioBoxes
		manipulatables.attachChild(makeBioBox(-75f, 1.5f, 10f, 2, 0, 0, 0, 1.5f, 9f, "BioBox1"));
		manipulatables.attachChild(makeBioBox(-75f, 1.5f, 0, 2, 0, 0, 0, 1.5f, 9f, "BioBox2"));	
		manipulatables.attachChild(makeBioBox(-75f, 1.5f, -10f, 2, 0, 0, 0, 1.5f, 9f, "BioBox3"));
	}
	
	// Making a single BioBox
	private Geometry makeBioBox(float trans_x, float trans_y, float trans_z, float rad, float rot_x, float rot_y, float rot_z, float scale, float mass, String name) {
		Box cube = new Box(1f, 1f, 1f);
		Geometry cube_geometry = new Geometry(name, cube);
		cube_geometry.setLocalTranslation(new Vector3f(trans_x, trans_y, trans_z));
		Material cube_material = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
		Texture cube_texture = assetManager.loadTexture("biohazard.png");
		cube_material.setTexture("DiffuseMap", cube_texture);
		
		// Using a quaternion to save a rotation to be used on the wall
		Quaternion rotate90 = new Quaternion(); 
		rotate90.fromAngleAxis(FastMath.PI/rad, new Vector3f(rot_x, rot_y, rot_z));
		cube_geometry.setLocalRotation(rotate90);
	 
		cube_geometry.setLocalScale(scale);
		cube_geometry.setMaterial(cube_material);
	    
		// Adding a collision box to geometry
		CollisionShape cube_shape = CollisionShapeFactory.createBoxShape(cube_geometry);
		RigidBodyControl cube_physical = new RigidBodyControl(cube_shape, mass);
	    	    
		cube_physical.setFriction(5f);
		cube_geometry.addControl(cube_physical);
		bulletAppState.getPhysicsSpace().add(cube_physical);
		return cube_geometry;
	}
	
	// Making all PAL9001s
	public void initPAL(){
		proximity.attachChild(makePAL(-99.6f, 30, 0, 2, 0, 0, 0, 4));
	}
	
	// Make a single PAL9001
	private Geometry makePAL(float trans_x, float trans_y, float trans_z, float rad, float rot_x, float rot_y, float rot_z, float scale) {
		Geometry PAL_geometry = new Geometry("PAL9001", PAL);
		PAL_geometry.setMaterial(PAL_material);
		
		// Translating to its location
		PAL_geometry.setLocalTranslation(trans_x, trans_y, trans_z);
		PAL_geometry.setLocalScale(scale);
		
		PAL_geometry.setShadowMode(ShadowMode.Receive);
		
		// Using a quaternion to save a rotation to be used
		Quaternion rotate90 = new Quaternion(); 
		rotate90.fromAngleAxis(FastMath.PI/rad, new Vector3f(rot_x, rot_y, rot_z));  
		PAL_geometry.setLocalRotation(rotate90);
		
		// Scales the textures
		PAL_geometry.getMesh().scaleTextureCoordinates(new Vector2f(0.335f, 0.166f));
		
		// Creates the physical with mass as argument
		PAL_physical = new RigidBodyControl(0f);
		PAL_geometry.addControl(PAL_physical);
		bulletAppState.getPhysicsSpace().add(PAL_physical);
		return PAL_geometry;
	}
	
	// Making all doors
	public void initDoor(){
		proximity.attachChild((makeDoor(8, 6, 98.4f, 2, 0, 1, 0, 1.2f)));
	}
	
	// Making a single door
	private Geometry makeDoor(float trans_x, float trans_y, float trans_z, float rad, float rot_x, float rot_y, float rot_z, float scale) {
		Geometry door_geometry = new Geometry("Door", door);
		door_geometry.setMaterial(door_material);
		
		// Translating to its location
		door_geometry.setLocalTranslation(trans_x, trans_y, trans_z);
		door_geometry.setLocalScale(scale);
		
		door_geometry.setShadowMode(ShadowMode.Receive);
		
		// Using a quaternion to save a rotation to be used
		Quaternion rotate90 = new Quaternion(); 
		rotate90.fromAngleAxis(FastMath.PI/rad, new Vector3f(rot_x, rot_y, rot_z));  
		door_geometry.setLocalRotation(rotate90);
		
		// Experimented with these values and this gave good resutls
		door_geometry.getMesh().scaleTextureCoordinates(new Vector2f(0.335f, 0.166f));
		
		// Creates the physical with mass as argument
		door_physical = new RigidBodyControl(0f);
		door_geometry.addControl(door_physical);
		bulletAppState.getPhysicsSpace().add(door_physical);
		return door_geometry;
	}
	
	public void initLamp() {
		rootNode.attachChild(makeLamp());
	}
	
	private Geometry makeLamp() {
		Geometry lamp_geometry = new Geometry("Lamp", lamp);
		lamp_geometry.setMaterial(lamp_material);
		lamp_geometry.setLocalTranslation(0, 60, 0);
				
		lamp_geometry.setShadowMode(ShadowMode.Cast);
	
		lamp_physical = new RigidBodyControl(0.0f);
		lamp_geometry.addControl(lamp_physical);
		bulletAppState.getPhysicsSpace().add(lamp_physical);	
		return lamp_geometry;
	}
	
	public void initLightSwitch() {
		rootNode.attachChild(makeLightSwitch());
	}
	
	private Geometry makeLightSwitch() {
		Geometry light_switch_geometry = new Geometry("LightSwitch", light_switch);
		light_switch_geometry.setMaterial(light_switch_material);
		light_switch_geometry.setLocalTranslation(-97.8f, 5, 20);
				
		light_switch_geometry.setShadowMode(ShadowMode.Cast);
	
		light_switch_physical = new RigidBodyControl(0.0f);
		light_switch_geometry.addControl(light_switch_physical);
		bulletAppState.getPhysicsSpace().add(light_switch_physical);	
		return light_switch_geometry;
	}
	
	public void initPALMode() {
		PAL_mode = new Picture("PAL9001 Mode");
		PAL_mode.setImage(assetManager, "PAL_mode_filter.png", true);
		PAL_mode.setHeight(settings.getHeight());
		PAL_mode.setWidth(settings.getWidth());	
	}
	
	public void initDrones() {
		proximity.attachChild(makeDrone(75f, 25f, 75f, 2, 1, 0, 0, 1.0f, 0f, "Drone1"));
	}
	
	private Geometry makeDrone(float trans_x, float trans_y, float trans_z, float rad, float rot_x, float rot_y, float rot_z, float scale, float mass, String name) {
		Sphere drone = new Sphere(32, 32, 1f);
		Geometry drone_geometry = new Geometry(name, drone);
		drone_geometry.setLocalTranslation(trans_x, trans_y, trans_z);
		Material drone_material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		Texture drone_texture = assetManager.loadTexture("drone.jpg");
		drone_material.setTexture("ColorMap", drone_texture);
		
		drone_geometry.setLocalScale(scale);
		drone_geometry.setMaterial(drone_material);
		
		CollisionShape drone_shape = CollisionShapeFactory.createDynamicMeshShape(drone_geometry);
		RigidBodyControl drone_physical = new RigidBodyControl(drone_shape, mass);
		
		// The spotlight of the drone
		SpotLight light_drone = new SpotLight();
		light_drone.setSpotRange(100000000f);
		light_drone.setSpotInnerAngle(35f * FastMath.DEG_TO_RAD);
		light_drone.setSpotOuterAngle(45f * FastMath.DEG_TO_RAD);
		light_drone.setColor(ColorRGBA.Red.mult(1.3f));
		light_drone.setDirection(Vector3f.UNIT_X.negate()); 
		rootNode.addLight(light_drone);
		LightControl light_control_drone = new LightControl(light_drone);
		drone_geometry.addControl(light_control_drone);
		
		drone_geometry.addControl(drone_physical);
		bulletAppState.getPhysicsSpace().add(drone_physical);
		return drone_geometry;
	}
	
	public void initAudio() {
		// Quote: I'm sorry, Dave. I'm afraid I can do that.
		audio_PAL_cantdo = new AudioNode(assetManager, "audio/cantdo.wav", false);
		audio_PAL_cantdo.setPositional(false);
		audio_PAL_cantdo.setLooping(false);
		audio_PAL_cantdo.setVolume(2);
		audio_PAL_cantdo.setReverbEnabled(false);
		rootNode.attachChild(audio_PAL_cantdo);
		
		// Quote: Everything is going extremely well.
		audio_PAL_well = new AudioNode(assetManager, "audio/well.wav", false);
		audio_PAL_well.setPositional(false);
		audio_PAL_well.setLooping(false);
		audio_PAL_well.setVolume(2);
		audio_PAL_well.setReverbEnabled(false);
		rootNode.attachChild(audio_PAL_well);	
	}
	
	public void initMotions() {
		// Makes the motion path for the first drone
		Spatial spatial = proximity.getChild("Drone1");
		initMotionPath1(spatial);
	}
	
	public void initMotionPath1 (Spatial spatial) {
		// Drawing the animation path
		motionpath1 = new MotionPath();
		motionpath1.addWayPoint(new Vector3f(75, 25, 75));
		motionpath1.addWayPoint(new Vector3f(75, 25, -75));
		motionpath1.addWayPoint(new Vector3f(-75, 25, -75));
		motionpath1.addWayPoint(new Vector3f(-75, 25, 75));
		 
		// Closing it into a cycle
		motionpath1.setCycle(true);
		
		//For debugging the motion path
		//motionpath1.enableDebugShape(assetManager, rootNode);
		
		motioncontrol1 = new MotionEvent(spatial, motionpath1);
		motioncontrol1.setDirectionType(MotionEvent.Direction.Path);
		motioncontrol1.setRotation(new Quaternion().fromAngleNormalAxis(FastMath.HALF_PI, Vector3f.UNIT_X));
		motioncontrol1.setInitialDuration(20f);
		motioncontrol1.setSpeed(0.5f);       
	}
	
	// Crosshairs
	protected void initCrossHair() {
		// Hiding stat box
		setDisplayStatView(false);
		
		//guiNode.detachAllChildren();
		guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
		BitmapText crosshairs = new BitmapText(guiFont, false);
		
		crosshairs.setSize(guiFont.getCharSet().getRenderedSize()*2);
		crosshairs.setText("+");        
		crosshairs.setLocalTranslation(settings.getWidth()/2 - guiFont.getCharSet().getRenderedSize()/(3*2), settings.getHeight()/2 + crosshairs.getLineHeight()/2, 0);
		guiNode.attachChild(crosshairs);
	}
}