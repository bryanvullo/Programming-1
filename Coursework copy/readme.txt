To run the program with the extension, please use the following command:

java EcsBandAid musicians.txt compositions.txt 10
where:
  musicians.txt is the name of the files storing the list of musicians
    musicians.txt needs to be in the layout
      MusiciansName(Instrument)
        supported instruments: Piano, Violin, Cello

  compositions.txt is the name of the files storing the list of compositions
    compositions.txt needs to be in the layout
      Name: Name of composition
      Tempo: Name of tempo
        supported tempos: Larghissimo, Lento, Andante, Moderato, Allegro, Presto
      Length: Number of the longest score
      Scores: where the layout of the score is
        InstrumentName, loud/soft, {note, note, ...}

  10 is the number of years for simulation.

NOTE: I have used the JSON-simple-1.1 library. If you encounter a problem when compiling, add the
"json-simple-1.1.jar" file in the "Project Structure > Libraries" in IntelliJ.

### details of each part ###

## Part1: ##
created package 'people' with class 'Person' with attribute 'String name'.
I made the 'name' attribute 'private' to ensure encapsulation.
created the constructor which takes a String and assigns it to 'name'.
create the 'getName' method which returns the 'name' attribute.
all as specified in the specification

created a sub-package of 'people' called 'musicians'.
inside this package I created the 'Musicians' interface.
abstract methods:
  setSeat method which takes an int and returns nothing,
  readScore method which takes an array of ints and a boolean,
  playNextNote method which will play the next note in the musicians score.
I also implemented abstract methods:
  getInstrumentID - returns the instrumentID
  clearScore - clears the score
  hasScore - returns true is the musician already has a score

with in the 'musicians' package,
I also implemented a middle class 'MusicianPerson' which extends 'Person' and implements 'Musician'
this has been extended by 'Violinist', 'Pianist', 'Cellist'. This has reduced the amount of
redundant code in each of these three classes as they share very similar codes.

I then created the 'Violinist', 'Cellist' and the 'Pianist'.
these three classes all extend the 'MusicianPerson' abstract class.
Each of these classes have two constructors of signature:
  Class(String name, SoundSystem soundSystem, int seat,
      String instrument, int instrumentID, int SOFT, int LOUD)
    all passed into super class 'MusicianPerson' constructor of same signature which assigns
    these variables to the associated attribute
  Class(String name, SoundSystem soundSystem,
      String instrument, int instrumentID, int SOFT, int LOUD)
    all passed into super class 'MusicianPerson' constructor of same signature which assigns
    these variables to the associated attribute

The 'MusicianPerson' class sets all it's attributes as private. Especially as 'instrumentID',
 'instrument', 'SOFT' and 'LOUD' are a constant, not to be changed by foreign objects.
other private attributes of this class include: 'int loudness', 'List<Integer> notes',
 'Iterator<Integer> nextNote', 'int seat', 'SoundSystem soundSystem'.

The method implementation is also in the 'MusicianPerson' class:
  setSeat - this sets the seat of the musician to the seat associated to the int given.
  readScore - this sets the musicians score to the array of ints, converting from int[] to List<Integer>
    and sets loudness to the int (SOFT or LOUD) associated by 'soft' boolean.
    i.e. if soft is true, then loudness is set to SOFT
  playNextNote - if it has a next note then it plays it

## Part2: ##
New 'Orchestra' class
the constructor initialised a hashmap, type Integer-Musician, of size 16.
each Integer key's value is set to null at initialisation (no one is seated yet)

orchestra methods:
  isSeated - returns true/false if a musician is seated in the orchestra
  sitDown - sits a musician down in an available seat
    returns 2 if is already seated. uses isSeated method to check
    returns 1 if there is no space for the musician
    returns 0 if they have been seated successfully
  standUp - makes a musician stand up
  playNextNote - makes every musician that is seated play their next note

## Part3: ##
New 'MusicScore' class inside music package
the constructor: MusicScore(String instrumentName, int[] notes, boolean soft)
sets member variables 'instrumentName', 'notes', 'soft' to their respective parameter
methods:
  getInstrumentID - gets the ID of the respective 'instrumentName'
  getNotes - returns the int[] notes
  isSoft - returns 'soft'

New 'Composition' interface inside music package
abstract methods: (no body yet as it's an interface)
  getName
  addScore
  getScores
  getLength
  getNoteLength

New 'Conductor' class extends Person inside conductors sub-package of people
constructor: Conductor(String name, SoundSystem soundSystem)
sends name to the super constructor and sets soundSystem to its member variable 'soundSystem'
methods:
  registerMusician - adds the musician to the member attribute 'musicians' which is an ArrayList
  playComposition - gets the compositions, loops through the compositions,
    within that loop, loops through the 'musicians' ArrayList. Assigns each music score of that
    composition to one musician. I implemented it this way to ensure that if there are many music
    scores for one instrument in that composition, they'd all get played.
I also implemented more methods:
  isRegistered - returns true if the musician is in the ArrayList 'musicians'
  deregisterMusician - removes the musician from the ArrayList 'Musicians'
  getMusicians - returns the ArrayList 'Musicians'

## Part4 ##
New class 'MusicSheet' concrete implementation of 'Composition' in music 'package'
constructor: MusicSheet(String name, String tempo, int length)
  sets the respective parameters to the member attributes
implements all the 'Composition' methods:
  getName - returns 'name'
  addScore - adds the given score to the ArrayList scores
  getScores - returns 'scores' in an Array datatype
  getLength - returns 'length'
  getNoteLength - finds and returns the note length associated to the tempo given
I also implemented:
  convertNote - takes in the String representation of the note and returns the
   int of the note used in the sound system

## Part5 ##
New EcsBandAid class
constructor: EcsBandAid(SoundSystem soundSystem, ArrayList<Musician> musicians,
 ArrayList<Composition> compositions)
with method:
  performForAYear - I decided to do this function all together rather than
   splitting it up into smaller methods as there is no reason benefit in doing so
   4 parts of this method:
    Randomly chooses 3 compositions - I shuffle the 'compositions' ArrayList and pick the
      first element, 3 times to get three random compositions
    Invites musicians - it calculates the needed musicians of each type (Violinist, Cellist, Pianist)
     to perform for the year, and only invites the needed musicians
    Plays the compositions - loop through the 'compositionsToPlay' list and play each one.
      Note: not all accepted musicians will play this composition; only the ones needed.
    Musicians randomly leave - each musician has a 50% to leave after performing for a year,
      (3 compositions)

## Part6 ##
I decided to create an additional class for this part, 'Helper' placed in the 'utils' package.
'Helper' has a list of static methods to help remove and modularise the main method in 'EcsBandAid'
list of static methods can be seen as a library of methods.
these static methods include:
  createReader(String filename) - creates and returns a 'Scanner' object the given file
  createSoundSystem - creates and returns a 'SoundSystem' object
  createMusicianArrayList(SoundSystem mySoundSystem, Scanner musicianReader) - uses the 'Scanner'
    object given to read and parse the text file. From the text file and the given 'SoundSystem'
    object it create a number of 'Musician' objects and returns them all in an 'ArrayList'.
  createCompositionArrayList(Scanner compositionReader) - uses the 'Scanner' object given to read
    and parse the text file. From the text file, it creates a series of 'Composition' Objects and
    'MusicScore' objects. The 'MusicScore' objects are added to their respective 'Composition'
    Objects. The many Compositions are all return within an 'ArrayList'.

in the main method, within 'EcsBandAid':
the args are stored in variables
  musicians configuration text file
  compositions configuration text file
  the amount of years the simulation needs to run
then calls on the static methods in 'Helper' to create objects
these objects are used to create the 'EcsBandAid' object 'myBand'
'myBand' calls 'performForAYear' for the amount of years given as an arg to the main method

## Extension ##
I have implemented the following extensions:
After each composition, the user may choose to pause the simulation - by entering 'P'.
When paused the user can choose to either:
  resume - by entering 'R'
  save - by entering 'S'
Saving a simulation will store the current state of the program in one JSON file called
savedSimulation.json
Once a simulation is saved, when running the program again, the save file will be detected and
the program will ask if the user wishes to continue their new simulation or continue with their
saved simulation.
If the user wishes to continue their saved simulation, then all the data from the JSON file is loaded,
parsed and used to recreate the previous objects. The saved simulation will resume as normal and can
be saved again.
NOTE: only one simulation can be saved! When a simulation is saved, the previous saved
simulation is discarded.

As this code is run before any objects are made,
I created the "Json" class which has many 'static' methods.
There are two sets of methods in this class:
1) used to save the simulation
  saveTheYear - takes the EcsBandAid object and the current composition index
    stores 6 items in a JSON file:
    1) the current year of the simulation
    2) the total years the simulation needs to run for given from the configuration argument
    3) all the compositions given from the configuration file
    4) the compositions to be played in this current year
    5) all the musicians given from the configuration file
    6) the musicians which are currently registered with the conductor
  parseCompositionToJson - takes a Composition as a parameter and returns a Map
  parseMusicianToJson - takes a Musician as a parameter and returns a Map
  NOTE: Map objects are used to store information in a JSON file. I chose to use JSON files as I
    have previous experience in using them and find them very efficient in storing data.
2) used to load a saved simulation
  reloadTheYear - takes no parameters and returns an EcsBandAid object
    retrieves the 6 items described in 'saveTheYear' method and stores them in the EcsBandAid object
  parseJsonToComposition - takes an Iterator of the JSON array and returns an ArrayList of Composition objects
  parseJsonToMusician - takes an Iterator of the JSON array and a SoundSystem object
    and returns an ArrayList of MusicianPerson objects
  parseJsonToRegisteredMusician - takes an Iterator object and an Arraylist of MusicianPerson objects,
    and finds the Objects in the ArrayList which are described in the JSON array and adds them to another
    ArrayList and returns the new ArrayList.
    NOTE: this is so that duplicate objects are not created, i.e. if the Musician 'John' was registered
      when the simulation was saved. Then when reloaded, only one Musician object 'John' is created
      but referenced in both ArrayLists.

The class MusicianFactory implements a Factory pattern design so that when reading the text file or
JSON file, I can create the specific MusicianPerson objects such as Violinist, Cellist, Pianist all
from one method, which prevents me from writing duplicate code.

I also implemented other new methods in EcsBandAid:
getMusicians - returns all musicians
getCompositions - returns all compositions
getBandConductor - returns conductor
setCurrentYear - sets the current year
getCurrentYear - returns the current year
setYear - sets the total years given in configuration argument
getYears - returns total years given in configuration argument
setCompositionsToPlay - sets the ArrayList of compositions to play in the current year
getCompositionsToPlay - returns the ArrayList of compositions to play in the current year
musicPlayer - gets the input from the user after each composition
musiciansLeaveBand - musicians registered with the conductor leave at a 50% rate
registerMusicians - registers all musicians in the ArrayList given (only used when loading a saved simulation)
resumeSavedYear - plays the rest of the compositions for the year (only used when loading a saved simulation)
newSimulation - returns a new EscBandAid object made from the configuration files and arguments
runSimulation - runs the simulation until the final year

new attributes in EcsBandAid:
  compositionsToPlay
  years
  currentYear

getInstrument method in Musician class
getInstrumentName method in MusicScore class
getTempo is now public in MusicScore class