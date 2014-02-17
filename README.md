M7002E Computer Graphics With Virtual Environments
=================================================
Student project at Luleå Tekniska Universitet.

Assignment 3
=======================
### "Disclaimer"
The controls and the generall plauer concept was inspired from the HelloCollisions.java example, and Crosshairs were inspired from HelloPhysics.java.

### Required features implemented

#### Creating an Enclosed Enviromnent
An enclosed room with a floor, a roof and 4 walls was created. All of the are solid and will not let the player ass through them.

#### Model Creation


#### Instancing

#### Smooth Motion

### Instructions


### References
##### Black Tile Texture 
http://www.moddb.com/mods/h2f/images/the-even-newer-panel-texture1
##### Crate Texture
http://grabcad.com/library/wood-crate-1
##### HAL9000
http://adventuretime.wikia.com/wiki/File:HAL9000.jpg
##### Steel Door
http://wiskha.com/steel-doors-for-home/fireproof-steel-doors-from-yongkang-mingdoor-manufacturing


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
