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

with in the 'musicians' package, I created the 'Violinist', 'Cellist' and the 'Pianist'.
these three classes all extend the 'Person' class and implements the 'Musician' interface.
Each of these classes have a unique 'instrumentID' and 'instrument' which I set as 'final'
 as it is a constant, not to be changed.
I also set some other 'final' constants such as 'SOFT' and 'LOUD' which and int constants.
other private attributes of these classes include: 'int loudness', 'List<Integer> notes',
 'Iterator<Integer> nextNote', 'int seat', 'SoundSystem soundSystem'.

Each of these classes have two constructors of signature:
  Class(String, SoundSystem, int)
    sets String to 'name'
    sets SoundSystem to 'soundSystem'
    sets int to 'seat'
  Class(String, SoundSystem)
    sets String to 'name'
    sets SoundSystem to 'soundSystem'

each of these classes implement the following methods:
setSeat - this sets the seat of the musician to the seat associated to the int given.
readScore - this sets the musicians score to the array of ints, converting from int[] to List<Integer>
  and sets loudness to the int associated by soft boolean.
playNextNote - if it has a next note then it plays it

### issues
I tried to implement a middle class 'MusicianPerson' which extends 'Person' and implements 'Musician'
this would have then been extended by 'Violinist', 'Pianist', 'Cellist'. This would have reduced
the amount of redundant code in each class as they share very similar codes. But each time
I tried this it broke my program, no sound would output.

Part2:


Part3
Part4
Part5
Part6