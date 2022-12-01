import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import music.Composition;
import music.MusicScore;
import music.MusicSheet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import people.musicians.Musician;
import people.musicians.MusicianFactory;
import utils.Helper;
import utils.SoundSystem;

public class Json {

  //saving methods
  private static Map parseCompositionToJson(Composition composition) {
    //name, tempo and length of the composition
    Map compositionMap = new LinkedHashMap();
    String name = composition.getName();
    String tempo = composition.getTempo();
    int length = composition.getLength();
    compositionMap.put("name", name);
    compositionMap.put("tempo", tempo);
    compositionMap.put("length", length);

    //puts each score into an array
    MusicScore[] scores = composition.getScores();
    JSONArray scoresArray = new JSONArray();
    for (MusicScore score : scores) {
      Map scoreMap = new LinkedHashMap();
      String instrument = score.getInstrumentName();
      boolean soft = score.isSoft();

      //notes int array is converted into a string
      int[] notesArray = score.getNotes();
      String notes = "";
      for (int noteInt : notesArray) {
        String note = Integer.toString(noteInt);
        notes = notes + "," + note;
      }
      notes = notes.replaceFirst(",", "");

      scoreMap.put("instrument", instrument);
      scoreMap.put("soft", soft);
      scoreMap.put("notes", notes);
      scoresArray.add(scoreMap);
    }
    compositionMap.put("scores", scoresArray);
    return compositionMap;
  }

  private static Map parseMusicianToJson(Musician musician) {
    Map musicianMap = new LinkedHashMap();
    String name = musician.getName();
    String instrument = musician.getInstrument();
    musicianMap.put("name", name);
    musicianMap.put("instrument", instrument);
    return musicianMap;
  }

  public static void saveTheYear(int currentCompositionIndex, EcsBandAid myBand) {
    //create a JSON file with all the current information
    ArrayList<Musician> allMusicians = myBand.getMusicians();
    ArrayList<Musician> registeredMusicians = myBand.getBandConductor().getMusicians();
    ArrayList<Composition> allCompositions = myBand.getCompositions();
    ArrayList<Composition> compositionsToPlay = myBand.getCompositionsToPlay();

    //removes the compositions that have been played already
    for (int i = currentCompositionIndex; i <= 0; i--) {
      compositionsToPlay.remove(i);
    }

    JSONObject json = new JSONObject();
    //there are 5 main parts to save in the json file
    // 1) the number of the current year and how many years to go
    // 2) the compositions to play in the current year
    // 3) the musicians which are currently registered with the conductor
    // 4) all the musicians in the world of this simulation
    // 5) all the compositions given in this simulation

    //adds the years data
    json.put("year", myBand.getCurrentYear());
    json.put("totalYears", myBand.getYears());

    // adds all the compositions which still need to be played for the year
    JSONArray compositionsToPlayArray = new JSONArray();
    for (Composition composition : compositionsToPlay) {
      Map compositionMap = parseCompositionToJson(composition);
      compositionsToPlayArray.add(compositionMap);
    }
    json.put("compositionsToPlay", compositionsToPlayArray);

    // adds all the registered musicians which are playing in the current year
    JSONArray registeredMusiciansArray = new JSONArray();
    for (Musician musician : registeredMusicians) {
      Map musicianMap = parseMusicianToJson(musician);
      registeredMusiciansArray.add(musicianMap);
    }
    json.put("registeredMusicians", registeredMusiciansArray);

    // adds all the musicians of the world in this simulation
    JSONArray allMusiciansArray = new JSONArray();
    for (Musician musician : allMusicians) {
      Map musicianMap = parseMusicianToJson(musician);
      allMusiciansArray.add(musicianMap);
    }
    json.put("allMusicians", allMusiciansArray);

    // adds all the compositions given in this simulation
    JSONArray allCompositionsArray = new JSONArray();
    for (Composition composition : allCompositions) {
      Map compositionMap = parseCompositionToJson(composition);
      allCompositionsArray.add(compositionMap);
    }
    json.put("allCompositions", allCompositionsArray);

    // finally write the json file and save it
    try {
      PrintWriter myWriter = new PrintWriter("savedSimulation.json");
      myWriter.write(json.toJSONString());
      myWriter.flush();
      myWriter.close();
    } catch (FileNotFoundException e) {
      System.err.println("this simulation could not be saved");
    }
  }

  //reloading methods
  private static ArrayList<Musician> parseJsonToMusician(Iterator iterator,
      SoundSystem soundSystem) {
    //fetches musicians' data
    //layout: json array of maps | each map is a musicians data
    ArrayList<Musician> musicians = new ArrayList<>();
    while (iterator.hasNext()) { //whilst there is a map (musician) in the JSON array
      String name = null;
      String instrument = null;
      Map musicianMap = (Map) iterator.next();
      Iterator<Map.Entry> musicianIterator = musicianMap.entrySet().iterator();
      while (musicianIterator.hasNext()) {
        //assigns the musicians data to vairables
        Map.Entry musicianPair = musicianIterator.next();
        if (musicianPair.getKey().equals("name")) {
          name = (String) musicianPair.getValue();
        } else if (musicianPair.getKey().equals("instrument")) {
          instrument = (String) musicianPair.getValue();
        } else {
          System.err.println("unrecognised musician data in save file");
        }
      }
      //creates the corresponding musicians object
      Musician musician = MusicianFactory.createMusician(instrument, name, soundSystem);
      musicians.add(musician);
    }
    return musicians;
  }

  private static ArrayList<Musician> parseJsonToRegisteredMusicians(Iterator iterator,
      ArrayList<Musician> allMusicians) {
    //fetches musicians' data
    //layout: json array of maps | each map is a musicians data
    ArrayList<Musician> musicians = new ArrayList<>();
    while (iterator.hasNext()) { //whilst there is a map (musician) in the JSON array
      String name = null;
      String instrument = null;
      Map musicianMap = (Map) iterator.next();
      Iterator<Map.Entry> musicianIterator = musicianMap.entrySet().iterator();
      while (musicianIterator.hasNext()) {
        //assigns the musicians data to vairables
        Map.Entry musicianPair = musicianIterator.next();
        if (musicianPair.getKey().equals("name")) {
          name = (String) musicianPair.getValue();
        } else if (musicianPair.getKey().equals("instrument")) {
          instrument = (String) musicianPair.getValue();
        } else {
          System.err.println("unrecognised musician data in save file");
        }
      }
      //creates the corresponding musicians object
      for (Musician musician : allMusicians) {
        if (name.equals(musician.getName()) && instrument.equals(musician.getInstrument())) {
          musicians.add(musician);
        }
      }
    }
    return musicians;
  }

  private static ArrayList<Composition> parseJsonToCompositions(Iterator iterator) {
    //fetches compositions' data
    //layout: json array of maps | each map is a composition
    ArrayList<Composition> compositions = new ArrayList<>();
    while (iterator.hasNext()) { //whilst there is a Map (composition) in the JSON array
      MusicSheet composition;
      String name = null;
      String tempo = null;
      int length = 0;
      ArrayList<MusicScore> scores = new ArrayList<>();
      //gets the map of the composition
      Map compositionMap = (Map) iterator.next();
      Iterator<Map.Entry> compositionIterator = compositionMap.entrySet().iterator();
      while (compositionIterator.hasNext()) {
        //fetches all the data in the Map and stores it in variables
        Map.Entry compositionPair = compositionIterator.next();
        if (compositionPair.getKey().equals("name")) {
          name = (String) compositionPair.getValue();
        } else if (compositionPair.getKey().equals("tempo")) {
          tempo = (String) compositionPair.getValue();
        } else if (compositionPair.getKey().equals("length")) {
          length = (int) ((long) compositionPair.getValue());
        } else if (compositionPair.getKey().equals("scores")) {
          //scores are saved in a separate JSON array | each Map in the array is a score
          JSONArray scoresArray = (JSONArray) compositionPair.getValue();
          Iterator scoresIterator = scoresArray.iterator();
          while (scoresIterator.hasNext()) { //whilst there is a Map (score) in the array of scores
            String instrument = null;
            boolean soft = true;
            int[] notes = null;
            Map scoreMap = (Map) scoresIterator.next();
            Iterator<Map.Entry> scoreIterator = scoreMap.entrySet().iterator();
            while (scoreIterator.hasNext()) {
              //fetched all the data in the map and stores it in variables
              Map.Entry scorePair = scoreIterator.next();
              if (scorePair.getKey().equals("instrument")) {
                instrument = (String) scorePair.getValue();
              } else if (scorePair.getKey().equals("soft")) {
                soft = (boolean) scorePair.getValue();
              } else if (scorePair.getKey().equals("notes")) {
                String notesString = (String) scorePair.getValue();
                String[] notesArray = notesString.split(",");
                //converts String array into int array
                notes = new int[notesArray.length];
                for (int i = 0; i < notesArray.length; i++) {
                  notes[i] = Integer.parseInt(notesArray[i]);
                }
              } else {
                System.err.println("unrecognised score data in save file");
              }
            }
            //creates the score object and stores it in the array list scores
            MusicScore score = new MusicScore(instrument, notes, soft);
            scores.add(score);
          }
        } else {
          System.err.println("unrecognised composition data in save file");
        }
      }
      //creates the composition object, adds the scores
      composition = new MusicSheet(name, tempo, length);
      for (MusicScore score : scores) {
        composition.addScore(score);
      }
      compositions.add(composition);
      //next composition
    }
    return compositions;
  }

  public static EcsBandAid reloadTheYear() {
    //opens the JSON file
    JSONParser myParser = new JSONParser();
    Object myObject = null;
    try {
      myObject = myParser.parse(new FileReader("savedSimulation.json"));
    } catch (Exception e) {
      System.err.println("There are no saved simulations");
      System.err.println(e);
      System.exit(0);
    }
    JSONObject json = (JSONObject) myObject;

    //the years data of the saved simulation
    int currentYear = (int) ((long) json.get("year"));
    int totalYears = (int) ((long) json.get("totalYears"));

    //sound system to pass to musicians
    SoundSystem soundSystem = Helper.createSoundSystem();

    //creates an array list of all the compositions
    JSONArray allCompositionsJson = (JSONArray) json.get("allCompositions");
    Iterator allCompositionsIterator = allCompositionsJson.iterator();
    ArrayList<Composition> allCompositions = parseJsonToCompositions(allCompositionsIterator);

    //creates an array list of the compositions to play
    JSONArray compositionsToPlayJson = (JSONArray) json.get("compositionsToPlay");
    Iterator compositionsToPlayIterator = compositionsToPlayJson.iterator();
    ArrayList<Composition> compositionsToPlay = parseJsonToCompositions(compositionsToPlayIterator);

    //creates an array of all musicians
    JSONArray allMusiciansJson = (JSONArray) json.get("allMusicians");
    Iterator allMusiciansIterator = allMusiciansJson.iterator();
    ArrayList<Musician> allMusicians = parseJsonToMusician(allMusiciansIterator, soundSystem);

    //creates an array list of registered musicians
    JSONArray registeredMusiciansJson = (JSONArray) json.get("registeredMusicians");
    Iterator registeredMusiciansIterator = registeredMusiciansJson.iterator();
    ArrayList<Musician> registeredMusicians = parseJsonToRegisteredMusicians(
        registeredMusiciansIterator, allMusicians);

    //returns an EcsBandAid object
    EcsBandAid myBand = new EcsBandAid(soundSystem, allMusicians, allCompositions);
    myBand.registerMusicians(registeredMusicians);
    myBand.setCompositionsToPlay(compositionsToPlay);
    myBand.setYears(totalYears);
    myBand.setCurrentYear(currentYear);

    return myBand;
  }
}
