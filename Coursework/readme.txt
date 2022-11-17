To run the program with the extension, please use the following
command

java EcsBandAid musicians.txt compositions.txt 10
where
  musicians.txt is the name of the files storing the list of musicians
  compositions.txt is the name of the files storing the list of compositions
  10 is the number of years for simulation.

Part1:
created package 'people' with class 'Person' with attribute 'String name'.
I made the 'name' attribute 'private' to ensure encapsulation.
created the constructor which takes a String and assigns it to 'name'.
create the 'getName' method which returns the 'name' attribute.
all as specified in the specification

created a sub-package of 'people' called 'musicians'.
inside this package I created the 'Musicians' interface.
created the abstract setSeat method which takes an int and returns nothing,
created the abstract readScore method which takes an array of ints and a boolean,
created the abstract playNextNote() method which will play the next note in the musicians score.

with in the 'musicians' package,
I implemented a middle class 'MusicianPerson' which extends 'Person' and implements 'Musician'
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


Part3
Part4
Part5
Part6