package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Exercise;
import model.ExerciseTracker;
import org.json.*;

// Code was taken from Json in:
// https://github.students.cs.ubc.ca/CPSC210/
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ExerciseTracker read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private ExerciseTracker parseWorkRoom(JSONObject jsonObject) {
        //String name = jsonObject.getString("name");
        ExerciseTracker exerciseTracker = new ExerciseTracker();
        addExercisesJson(exerciseTracker, jsonObject);
        return exerciseTracker;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addExercisesJson(ExerciseTracker exerciseTracker, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Exercises");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addExerciseJson(exerciseTracker, nextThingy);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addExerciseJson(ExerciseTracker exerciseTracker, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int reps = jsonObject.getInt("reps");
        int sets = jsonObject.getInt("sets");
        int pr = jsonObject.getInt("pr");

        //Category category = Category.valueOf(jsonObject.getString("category"));
        Exercise exercise = new Exercise(name, reps, sets, pr);
        exerciseTracker.addExercises(exercise);
    }
}