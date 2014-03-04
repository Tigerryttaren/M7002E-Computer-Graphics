M7002E Computer Graphics With Virtual Environments
=================================================
Student project at Luleå Tekniska Universitet.

=======================
ToDo List
=======================
#### Important

##### Limit Camera Panning in Other Point of View

Cats!!

#### Not-So-Important

##### Adding Some Player Model
##### Adding the Enlarger
##### Adding the Shrinker



#### If-Time-Allows

##### Disable Camera Scrolling/Zoom




=======================
Assignment 4
=======================
### "Disclaimer"
The controls and the general player concept was inspired from the **HelloCollisions.java** example, and crosshairs were taken pretty straight off from **HelloPhysics.java**. The **HelloPicking.java** example was used for learning how to do picking.

### "Features" aka Bugs
#### Boundary-Penetrating Rod-Like Objects
When an object is thrown upwards, it might penetrate the floor when falling down. Possibly just make the floow thicker or "denser", somehow? Changing bulletAppState to update more often? Refresh times on collision, increase them?

#### Vision-Distorting Wheel-Zoom Effect
By default, the FlyCam has the mouse wheel zoom enabled, which will distort the view if fiddled with. This was not as straight forward as deleting the key mapping for those operation. Not really sure how to turn it off, efficiently.

### Required Features Implemented
##### Supports Egocentric Locomotion
The concept of a player, who can move around, is affected by gravity, can manipulate his environment and more is supported.

##### Picking
Direct selection is implemented. The player has the ability to aim at an object and select it.

##### At Least Two Point of Views
THe user can switch camera view between the player character and the evil red surveilance eye a la HAL9000.

##### Look Somewhat Natural
Utilizing pretty textures from the internet and rudimentary physics, the scene looks and feels at least somewhat natural. Also, acceptably realistic shadows are used. 

### Instructions
#### Moving the Player
Use the **WASD** and **SPACE** keys to move around the environment, as well as **MOUSE** or **ARROW** keys to move the camera.

#### Manipulating Objects
Left-click the **MOUSE** to pick an object up, left-click again to simply drop the object. Right-clicking when holding an object will throw it away with force. Using the **E** key when holding some objects will make the player use the object on the environment.

##### The Creator
This ancient artifact in the form of a green rod is a powerful tool, it allows the player to magically create new boxes.

##### The Destroyer
The evil twin of the Creator, the red rod called the Destroyer will destroy the boxes it is used on. 

##### BioBoxes
These small red boxes containing extremely hazardous biological waste can be tossed around in an playful manner, but cannot really be used as anything other than a building block.

#### Switching Point of View
Use the **TAB** key to switch between the point of view of the player and the point of view of HAL9000.

### Resources
#### Code Examples
##### jMonkey Tutorials
http://hub.jmonkeyengine.org/wiki/doku.php/jme3#tutorials_for_beginners
#### Texture References
The textures for this project was borrowed from these sites and projects. All credit to their creators.
##### Black Tile Texture
http://www.moddb.com/mods/h2f/images/the-even-newer-panel-texture1
##### Crate Texture
http://grabcad.com/library/wood-crate-1
##### HAL9000 Texture
http://adventuretime.wikia.com/wiki/File:HAL9000.jpg
##### Steel Door Texture
http://wiskha.com/steel-doors-for-home/fireproof-steel-doors-from-yongkang-mingdoor-manufacturing
##### Biohazard Symbol
http://en.wikipedia.org/wiki/File:Biohazard_symbol.svg
##### Radioactivity Symbol
http://commons.wikimedia.org/wiki/File:Radiation_warning_symbol2.svg
##### Black Wood Texture 
http://www.shootersfm.com/?attachment_id=5
##### White Wood Texture
http://stphq-stock.deviantart.com/art/WHITE-PAINTED-WOOD-01-295106430
##### Dotted Metal Surface Texture
http://www.4freephotos.com/Holes_in_metal_surface-limage-5a8bf56998f04916e02827eb3b0b88b6.html#.Uw3XCnVdWlg
#### Lamp Texture
http://natsukilo.blogspot.se/2012/06/fypparticle-effect-texture.html

=======================
Assignment 3
=======================
### "Disclaimer"
The controls and the general player concept was inspired from the HelloCollisions.java example, and crosshairs were taken pretty straight off from HelloPhysics.java.

### "Features" 
#### Occational World Breach
In some cases, a box that has gained significant speed or torque might penetrate the ceiling or ground due to *awesome* forces.

### Required Features Implemented
##### Creating an Enclosed Enviromnent
An enclosed room with a floor, a roof and four walls was created. All of the are solid and will not let the player pass through them.

##### Model Creation
I chose to create the models programatically and add some fancy textures. 

##### Instancing
The crates, drones and most objects are created by calling a method that takes the properties as input. One can easily instanciate several objects.

##### Smooth Motion
In front of HAL9000, there are three crates that can be manipulated to fidget around. Also, in the ceiling there are three surveilance drones that will move following a path when the user presses buttons.

### Instructions

##### Heroic Player 
Use the **WASD** and **SPACE** keys to move around the environment, as well as **MOUSE** or **ARROW** keys to move the camera.

##### Small Crates
Use the **1** key to manipulate one of the crates, and use the **2** and **3** keys to manipulate the ovther two. Only the small crates can be manipulated. 

##### Red Rubber Ball
Use the **B** key to make the red rubber ball bounce.

##### Surveilance Drones
Use the **7** key to make one of the drones move, and the **8** and **9** keys to move the other two drones.

### Texture References
The textures for this project was borrowed from these sites and projects. All credit to their creators.

##### Black Tile Texture
http://www.moddb.com/mods/h2f/images/the-even-newer-panel-texture1
##### Crate Texture
http://grabcad.com/library/wood-crate-1
##### HAL9000 Texture
http://adventuretime.wikia.com/wiki/File:HAL9000.jpg
##### Steel Door Texture
http://wiskha.com/steel-doors-for-home/fireproof-steel-doors-from-yongkang-mingdoor-manufacturing
##### Ball Texture
http://www.psdgraphics.com/backgrounds/orange-basketball-texture/
##### Drone Texture
http://community.fantasyflightgames.com/index.php?/topic/73877-death-star-scenario-rules/

=======================
Assignment 2
=======================
### "Disclaimer"
The robustness of this program may in many cases be a little less robust than one generally wants. Please, be careful when entering values into the program, and follow the structure of the standard values placed in the input boxes as examples. Faulty input structure will likely result in a program crash. 

### Required Features Implemented
##### Adding a Light Source
This is supported, as well as setting custom light components on creation. Only ONE light source is supported at the moment (as I interpreted the specification, this should be enough). The light source is not considered an object and will not be removed when clearing all objects.

##### Create Shapes
The program allows for creation of three different shapes, a cube, a sphere and a cylinder. Adding support for other shapes is very easy. The shapes can be created anywhere the user wants to and of any size preferred.

##### Manipulate Shapes
Shapes can be manipulated by the user. Support exists for adding shapes, deleting shapes, rotating shapes, deleting all shapes, moving a shape and resizing a shape.

##### Create Shapes with Custom Material Components
This is supported.

##### Selection by Clicking on a Shape
This is supported.

##### Exiting the Application
This can be done with the cancel button or the menu option.

### Instructions
#### Create a Shape
Select the menu option for Create, then choose your shape.

#### Create a Light Source
Select the meny option, set the settings, and the light source is created. 

#### Manipulate a Shape
First click on the shape you want to manipulate.
Then, select the menu option for your manipulation.

##### Resize a Shape
Enter a scale value that will resize the shape. A value of 1.0 means the object remains the same size, whereas a value of 0.5 means current size will be multipliced by 0.5, and a value of 2.0 will multiply it by 2.

##### Move a Shape
Choose new coordinates.

##### Delete a Shape
Click on a shape, then click the menu option for delete.

=======================
Assignment 1
=======================
### Required Features Implemented

##### Outlines of a Square-based Pyramid
The outlines of a red square based pyramid has been drawn. 

##### Blue Filled-in Square
A blue filled in square has been drawn.

##### Any Kind of Green Star
A green star has been drawn. A four-pointed star was chosen because it is very easy do draw compared to a five-pointed star, for example. 

##### Easy Creation of Additional Figures
The code was designed so that the drawing of the figures are done in functions, which can easily be called on to make additional figures. 

##### Preserves the OpenGL State
The functions *glPushAttrib()*, *glPushMatrix()*, *glPopAttrib()*, and *glPopMatrix()* was used  for preserving the openGL state before translating or rotating the figures, and also before changing attributes such as colors.
