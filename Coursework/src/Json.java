import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import music.Composition;
import music.MusicScore;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import people.musicians.Musician;

public class Json {
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
  public static void saveTheYear(ArrayList<Composition> compositionsToPlay,
      int currentCompositionIndex, EcsBandAid myBand,
      int currentYear, int years) {
    //create a JSON file with all the current information
    ArrayList<Musician> allMusicians = myBand.getMusicians();
    ArrayList<Musician> registeredMusicians = myBand.getBandConductor().getMusicians();
    ArrayList<Composition> allCompositions = myBand.getCompositions();

    //removes the compositions that have been played already
    for (int i = currentCompositionIndex; i == 0; i--) {
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
    json.put("year", currentYear);
    json.put("totalYears", years);

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

  public static void resumeTheYear() {
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

    int currentYear = (int) json.get("year");
    int totalYears = (int) json.get("totalYears");

    //TODO fetch all the data and create the objects
    JSONArray compositionsToPlay = (JSONArray) json.get("compositionsToPlay");

    JSONArray registeredMusicians = (JSONArray) json.get("registeredMusicians");

    JSONArray allCompositions = (JSONArray) json.get("allCompositions");

    JSONArray allMusicians = (JSONArray) json.get("allMusicians");

  }
}
