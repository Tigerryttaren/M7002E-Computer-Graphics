M7002E Computer Graphics With Virtual Environments
=================================================
Student project at Luleå Tekniska Universitet.

Assignment 3
=======================
### "Disclaimer"
The controls and the general player concept was inspired from the HelloCollisions.java example, and crosshairs were taken pretty straight off from HelloPhysics.java.

### World Breach
In some cases, a box that has gained significant speed or torque might penetrate the ceiling or ground due to awesome forces.

### Required features implemented

#### Creating an Enclosed Enviromnent
An enclosed room with a floor, a roof and four walls was created. All of the are solid and will not let the player pass through them.

#### Model Creation
I chose to create the models programatically and add some fancy textures. 

#### Instancing
The crates, drones and most objects are created by calling a method that takes the properties as input. One can easily instanciate several objects.

#### Smooth Motion
In front of HAL9000, there are three crates that can be manipulated to fidget around. Also, in the ceiling there are three surveilance drones that will move following a path when the player presses buttons.

### Instructions
Move the player with the WASD and SPACE keys, as well as the mouse or ARROW keys to move the camera. Use the 1 key to manipulate Crate 1, the 2 key to manipulate Crate 2, and the 3 key to manipulate Crate 3. Bounce the red rubber ball on the B key. Start surveilance drone 1 with key 7, drone 2 with key 8 and drone 3 with key 9.

### References
##### Black Tile Texture 
http://www.moddb.com/mods/h2f/images/the-even-newer-panel-texture1
##### Crate Texture
http://grabcad.com/library/wood-crate-1
##### HAL9000
http://adventuretime.wikia.com/wiki/File:HAL9000.jpg
##### Steel Door
http://wiskha.com/steel-doors-for-home/fireproof-steel-doors-from-yongkang-mingdoor-manufacturing
##### Ball 
http://www.psdgraphics.com/backgrounds/orange-basketball-texture/
##### Drone
http://community.fantasyflightgames.com/index.php?/topic/73877-death-star-scenario-rules/

Assignment 2
=======================
### "Disclaimer"
The robustness of this program may in many cases be a little less robust than one generally wants. Please, be careful when entering values into the program, and follow the structure of the standard values placed in the input boxes as examples. Faulty input structure will likely result in a program crash. 

### Required features implemented
#### Adding a light source
This is supported, as well as setting custom light components on creation. Only ONE light source is supported at the moment (as I interpreted the specification, this should be enough). The light source is not considered an object and will not be removed when clearing all objects.

#### Create shapes
The program allows for creation of three different shapes, a cube, a sphere and a cylinder. Adding support for other shapes is very easy. The shapes can be created anywhere the user wants to and of any size preferred.

#### Manipulate shapes
Shapes can be manipulated by the user. Support exists for adding shapes, deleting shapes, rotating shapes, deleting all shapes, moving a shape and resizing a shape.

#### Create shapes with custom material components
This is supported.

#### Selection by clicking on a shape
This is supported.

### Exiting the application
This can be done with the cancel button or the menu option.

### Instructions
#### Create a shape
Select the menu option for Create, then choose your shape.

#### Create a light source
Select the meny option, set the settings, and the light source is created. 

#### Manipulate a shape
First click on the shape you want to manipulate.
Then, select the menu option for your manipulation.

##### Resize a shape
Enter a scale value that will resize the shape. A value of 1.0 means the object remains the same size, whereas a value of 0.5 means current size will be multipliced by 0.5, and a value of 2.0 will multiply it by 2.

##### Move a shape
Choose new coordinates.

##### Delete a shape
Click on a shape, then click the menu option for delete.

Assignment 1
=======================
Required features implemented:

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
