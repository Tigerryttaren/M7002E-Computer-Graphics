M7002E Computer Graphics With Virtual Environments
=================================================
Student project at Luleå Tekniska Universitet.

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
