_______SUNDAY_______

- made all pitches enums.
- cleaned up methods that are importing data into this model
- changed the way that the sheet is stored (arraylist of sets)
- changed the note to be an independent class (no longer extending playable)
- add note would add several notes for a note that is longer than one beat.

_______MONDAY________
- change the way that the sheet is being stored into a treeMap, because addNote
  doesn't seem to work when the sheet is empty (can't get items when the arraylist
  is empty).
- decided to store lowest note because it is easier to iterate on toString.

_______TUESDAY_______
- store the highest note and removed the range field.
- going back from treeMap into arrayList
- added methods compareTo() to compare two Notes

_______WEDNESDAY______
- removed builder in the model because it looked confusing
- modified Add to set the highest and lowest note

_______THURSDAY_______
- length field is taken off from ModelImpl because builder doesn't work with length.

_______FRIDAY_________
- adding the field that contains the passed in piece in the ModelImpl constructor.
- making two constructors
- add the method addAll in the modelImpl
- changed equals() in note to only be true when two notes are identical (allow overlap)
- adding instrument field to note.

Music Editor Design: Rosaline Su, David Celentano

Our goal with this portion of the music editor project (in addition to adjusting our models to fit our current needs, see above) was to create 3 different types of views
that would allow functionality in preparation for implementing a controller, while still protecting our model data in order to preserve our invariants as well as ensure
the integrity of the overall program.  We had already established a toString method for our model making the console view quite trivial to implement.  The GUI view required
a bit more planning but our design goal was to base the scale of the rendering on global variables to allow easy resizing before we implement scrolling in the controller.
The midi view uses the head notes from the ViewModel to play the note with the right durations. The sleep was set to 100 so the song can be heard. We also used local
 variables to initialize the Synthesizer and the Receiver. We aimed to keep our main method free of clutter and very readable as the unifying link in our code to make the user experience
as simple and straightforward as possible.

INSTRUCTIONS FOR RUNNING MusicEditor:

	- The main class is “MusicEditor”
	- arguments should be as follows “<Filename.txt> <viewType>”
	- <viewType> can either be “console” “gui” or “midi”



	//////HW07//////
	_____TUESDAY____
	- implementing the interface View.
	//sometime between tuesday and saturday
	- using keyHandler in the controller, passing in data (for modifying a note) as keys.
	- use OptionalInt to store the relevant information (flags on modes, measures, pitches, etc)
	- create a method called initialize() in the controller, which stores a list of keys (that we want to use) and
	  creates a new runnable. The run() method then calls a a wrapper function that passes in an
	  argument (which exists in its environment).
	- the wrapper function runArgs(int key) would do the specific (high-level) task that is related to the model.
	- adds that runnable into the keyhandler's mappings.


  _____SATURDAY___
  - removed most fields in the controller class that depend on user inputs (beat, measure, duration of a note
    that is being added by an user).
  - use scanner for user inputs on modifying notes. adding a scanner field in the controller
  - add a Timer in the controller that would tick and calls midi to play each beat.
  - changing the play method in midi to take in the beat number that is being played.
  - adding a new method in both the viewModel and the Model that would return the set of notes at a given beat.
  - add methods that deal with scrolling in the controller.
  - changing the view field into GUIview in the controller.
  - implements addKeyListener and removeKeyListener in the view and they're set in the controller.
  - adding a mouse handler class, and use that in the controller
  - made the field view a composite view instead of GUIview
  - adding the state in the controller (that tracks when the piece is paused or playing)

  _____MONDAY____
  - pulled the JFrame out of the constructor.
  - added addKeyListener method in GuiViewFrame
  - using e.getExtendedKeyCode in keyTyped because e.getKeyCode is not recognized.

  _____TUESDAY_____
  - finishing run methods that deal with play, pause, jump to beginning, jump to end.
  - remove keyEvents for scrolling up, down, left, and right (handled in the GUIview)
  - made changes to move() in Model, removed the use of contains in
  removeNote().
  - abstract the method getHighest from both GuiViewFrame and compositeView
  into GUIview
  - removed scanner from controllerImpl
  - adding getLowest in GUIview
  - change getLowest and getHighest in modelImpl so that the low and the
  high in the model are not modified.
  - add find() in the model, because remove and move rely on identity equality.
  - add try-catch to the methods in controller to prevent the program from
  crashing.

  ////////Dec. 1///////
  ____TUESDAY____
  - there was a bug in find and now it's fixed
  - remove comparing duration when finding the note (tail notes are 1
  duration, but dragging it would give a different duration (comparing with
  base's duration)
  - removed runArgs()
  - change the starting state to be paused to avoid replication
  - adding getKeyHandler() to the Controller for testing purposes
  - fix the range printing the right way




  Keys:
  Play/Pause: space bar.
  Jump to beginning: ","
  Jump to end: "."
  Adding Mode: "e"
  Removing Mode: "r"


  ////////Dec. 7///////
  ______MONDAY_____
  - adding setInstrument, setDuration, and setVolume in the Note so that
  setNote from their ANote is completely adapted by Note.
  - making duration, instrument, and volume not final.
