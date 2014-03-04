Assignment 4
=======================
### Disclaimer
The controls and the general player concept was inspired from the **HelloCollisions.java** example, and crosshairs were taken pretty straight off from **HelloPhysics.java**. The **HelloPicking.java** example was used for learning how to do picking.

This hilariously monolithic file could and propbably should be split up in several smaller files for readability, but so far, it has not turn out that way ;-)

### "Features"
#### Boundary-Penetrating Rod-Like Objects
When an object is thrown upwards, it might penetrate the floor when falling down.

#### Vision-Distorting Mouse-Wheel Zoom Effect
By default, the FlyCam has the mouse wheel zoom enabled, which will distort the view if fiddled with. This was not as straight forward as deleting the key mapping for those operation, and no simple and efficient solution was found.

#### Border-Zone Cam-Vibrations
When in HAL9000-mode and you pan the camera to the edge, the camera shakes a bit due to how I implemented the limited camera panning. Very minor, but still a bit on an annoyance.

### Required Features Implemented
##### Supports Egocentric Locomotion
The concept of a player, who can move around, is affected by gravity, can manipulate his environment and more is supported.

##### Direct Picking
Direct selection is implemented. The player has the ability to aim at an object and select it.

##### (At Least) Two Point of Views
THe user can switch camera view between the player character and the evil red surveilance eye a la HAL9000.

##### Look Somewhat Natural
Utilizing pretty textures from the internet and rudimentary physics, the scene looks and feels at least somewhat natural. Also, acceptably realistic shadows are used. 

##### Command Palette
The way the user controls his environment is through objects. Pick one up, use the **USE** key and watch something happen. Rinse, and repeat.

### Instructions
#### Moving the Player
Use the **WASD** and **SPACE** keys to move around the environment, as well as **MOUSE** or **ARROW** keys to move the camera.

#### Manipulating Objects
Left-click the **MOUSE** to pick an object up, left-click again to simply drop the object. Right-clicking when holding an object will throw it away with force. Using the **E** key when holding some objects will make the player use the object on the environment.

##### The Materializer, The Creator
This heavenly *white* strangely unsettling synthetically-made artifact has the godlike power to create objects from thin air. It is a powerful tool to create boxes for the player to play with.

##### The De-Materializer, The Destroyer
This terrificly horrifying *black* rod has the power to undo creations, and send them back to the *Very Vast Void of Uncreated Creations*. It is the evil adopted twin of the Creator, and allows the player to remove boxes.

##### The Redshifter, The Enlarger
This bright *red* rod has the awesome power to enlarge objects simply by tapping the with the rod. Its nickname is inspired from the Doppler effect on electromagnetic waves, where Redshifting is when an on object is increased in wavelength, but in this case, in size.

##### The Blueshifter, The Shrinker
This somberly *blue* rod has the power to shrink objects. As its red twin the name is inspired from the blueshift of the Dopplere ecceft on electromagnetic waves. 

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

