M7002E Computer Graphics With Virtual Environments
=================================================
Student project at Luleå Tekniska Universitet.

Lab 1
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

Lab 2
=======================
### "Disclaimer"
The robustness of this program are in many cases less robust than one generally wants. Please, be careful when entering values into the program, and follow the structure of the standard values placed there as examples. Fault input structure will result in a program crash. Apologizes for any inconveniance caused. 

### Instructions
##### Create a shape:
Select the menu option for Create, the choose your shape.

##### Create a light source:

##### Manipulate a shape:
First click on the shape you want to manipulate.
Then, select the menu option for your manipulation.

###### Resize a shape:
Enter a scale value that will resize the shape. A value of 1.0 means the object remains the same size, whereas a value of 0.5 means current size will be multipliced by 0.5, and a value of 2.0 will multiply it by 2.

###### Move a shape:
Choose new coordinates.

###### Delete a shape:
Click on a shape, the click the menu option for delete.

### Required features implemented:

##### Adding a light source
This is supported, as well as setting custom light components on creation. Only ONE light source is supported at the moment, as I interpreted the specification, this should be enough. The light source is not considered an object and will not be removed, unfortunately, when clearing all objects.

##### Create shapes
The program allows for creation of at least three different shapes. As of writing, a Sphere, a Cube and a Cylinder is supported. Adding support for other shapes is very easy. The shapes can be created anywhere the user wants to and of any size preferred.

##### Manipulate shapes
Shapes can be manipulated by the user. Support exists for adding shapes, deleting shapes, deleting all shapes, moving a shape and resizeing a shape.

##### Shapes with custom material components
This is supported on creation only.

##### Selection by clicking
This is supported. As of writing,



