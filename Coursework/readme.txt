To run the program with the extension, please use the following
command

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

### details of each part ###

Part1:
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

Part2:
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

Part3:
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

Part4
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

Part5
New EcsBandAid class
constructor: EcsBandAid(SoundSystem soundSystem, ArrayList<Musician> musicians,
 ArrayList<Composition> compositions)
with method:
  performForAYear - I decided to do this function all together rather than
   splitting it up into smaller methods as there is no reason benefit in doing so
   4 parts of this method:
    Randomly chooses 3 compositions - I shuffle the 'compositions' ArrayList and pick the
      first element, 3 times to get three random compositions
    Randomly invites musicians - I shuffle the 'musicians' ArrayList and sequentially invite the
      musicians. Each invitation has a 70% chance that it will be accepted.
    Plays the compositions - loop through the 'compositionsToPlay' list and play each one.
      Note: not all accepted musicians will play this composition; only the ones needed.
    Musicians randomly leave - each musician has a 50% to leave after performing for a year,
      (3 compositions)

Part6
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