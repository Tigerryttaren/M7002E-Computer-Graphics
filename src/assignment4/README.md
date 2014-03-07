Assignment 4
=======================
### Introduction To Situation
You are Dave, a newborn age-acceletrated synthetic human being. You goal is to learn some basic motor skills and throw stuff around, while learing how to operate the **R.O.D.S.** or **R**od-like **O**bject of **D**oing **S**tuff. The sinister and ever-helpful PAL9001, **P**ropably-evil **AL**gorithmic computer model **9001**, is watching your every move. Have a nice day.

### Disclaimer
The controls and the general player concept was inspired from the **HelloCollisions.java** example, and crosshairs were taken pretty straight off from **HelloPhysics.java**. The **HelloPicking.java** example was used for learning how to do picking.

This hilariously monolithic file could and propbably should be split up in several smaller files for readability, but so far, it has not turn out that way ;-)

### Required Features Implemented
##### Supports Egocentric Locomotion
The concept of a player, who can move around, is affected by gravity, can manipulate his environment and more is supported.

##### Direct Picking
Direct selection is implemented. The player has the ability to aim at an object and select it. In this "game", there is no selection marker, and according to me, your humble developer, the crosshairs are well and enough.

##### (At Least) Two Point of Views
The user can switch camera view between the player character and the evil surveilance eye of PAL9001.

##### Look Somewhat Natural
Utilizing pretty textures from the internet and rudimentary physics, the scene looks and feels at least somewhat natural. Also, acceptably realistic shadows are used. 

##### Command Palette
The way the user controls his environment is through objects. Pick one up, use the **USE** key and watch something happen. Rinse, and repeat.

### Possible Extra Features Implemented
#### Proximity-Based Events
When the player approaches the door, he will trigger a proximity based event.

#### Proximity-Based Audio
When the player approaches the door, he will trigger a proximity-based aound effect. Also, try and approach PAL9001 on the wall and he'll tell you how it's going.

#### User-Regulated Lighting
The user can change the lighting of the room by turning on an off the large ceiling lamp. There are also buttons for turning the ceiling light red, yellow, green or white.

#### H.U.D. With Help Text Or Instructions
The user sees a HUD with "weapon" names and a brief explanation of what they to. Also, when operating the light switch board, a helpful text will appear on-screen.

#### Light Following Spatial
The red search light under the drone is attach to it and will follow it on its patrol route.

### Instructions
#### Moving the Player
Use the **WASD** and **SPACE** keys to move around the environment, as well as **MOUSE** or **ARROW** keys to pan the camera.

#### Manipulating Objects
Left-click the **MOUSE** to pick an object up, left-click again to simply drop the object. Using the **T** key when holding an object will throw it away with force. Using the **E** key when holding some objects will make the player use the object on the environment.

##### The Materializer
This heavenly *white* and strangely unsettling synthetically-made artifact has the godlike power to materialize objects out of thin air. It is a powerful tool to create boxes for the player to play with. Also known as The Creator.

##### The De-Materializer
This terrificly horrifying *black* rod has the power to undo creations, and send them back to the Very Vast Void of Uncreated Creations. It is the evil adopted twin of the Creator, and allows the player to remove boxes. Also known as The Destroyer.

##### The Redshifter
This bright *red* rod has the supremely awesome power to enlarge objects simply by tapping them with the rod. Its nickname is inspired from the Doppler effect on electromagnetic waves, where Redshifting is when an on object is increased in wavelength, but in this case, in size. Also known as The Enlarger.

##### The Blueshifter
This somberly *blue* rod has the power to shrink objects. As its red twin, the name is inspired from the blueshift of the Doppler effect on electromagnetic waves. Also known as The Shrinker.

##### Light Switch
To the left of PAL9001 there is a light switch panel on the wall. When the user walks up to them and presses the **E** key, the light switches mode, or color.

##### BioBoxes
These small red boxes containing extremely hazardous biological waste can be tossed around in an playful manner, but cannot really be used as anything other than a building block. Use them to climb the larger yellow boxes! Oh, the fun!

#### Switching Point of View
Use the **TAB** key to switch between the point of view of the player and the point of view of PAL9001.

### Resources
The textures and the audio used in this project was borrowed, based or inspired from these sites and projects. All credit to their respective creators.
#### Code Examples
- [jMonkey Tutorials](http://hub.jmonkeyengine.org/wiki/doku.php/jme3#tutorials_for_beginners)
- [jMonkey Tutorial Hello Picking](http://hub.jmonkeyengine.org/wiki/doku.php/jme3:beginner:hello_picking)
- [jMonkey Tutorial Hello Collision] (http://hub.jmonkeyengine.org/wiki/doku.php/jme3:beginner:hello_collision)
- [jMonkey Tutorial Hello Physics] (http://hub.jmonkeyengine.org/wiki/doku.php/jme3:beginner:hello_physics)

#### Audio References
- [HAL9000 Quotes](http://www.gotwavs.com/Movies/2001.html)

#### Texture References
- [Black Tile Texture](http://www.moddb.com/mods/h2f/images/the-even-newer-panel-texture1)
- [Base Crate texture](http://grabcad.com/library/wood-crate-1)
- [HAL9000 Texture](http://adventuretime.wikia.com/wiki/File:HAL9000.jpg)
- [Steel Door Texture](http://wiskha.com/steel-doors-for-home/fireproof-steel-doors-from-yongkang-mingdoor-manufacturing)
- [Biohazard Symbol](http://en.wikipedia.org/wiki/File:Biohazard_symbol.svg)
- [Radioactivity Symbol](http://commons.wikimedia.org/wiki/File:Radiation_warning_symbol2.svg)
- [Rod Surface Base Texture](http://www.4freephotos.com/Holes_in_metal_surface-limage-5a8bf56998f04916e02827eb3b0b88b6.html#.Uw3XCnVdWlg)
- [Ceiling Lamp Texture](http://natsukilo.blogspot.se/2012/06/fypparticle-effect-texture.html)
