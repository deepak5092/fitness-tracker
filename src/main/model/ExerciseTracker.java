package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// the class manages the exercises
public class ExerciseTracker {

   // private Exercise exercise;
    private ArrayList<Exercise> exercises;

    // constructs the list of exercises
    public ExerciseTracker() {
        exercises = new ArrayList<>();
    }

    // MODIFIES : this
    // EFFECTS : adds an exercise to the exercise tracker list
    public void addExercises(Exercise exercise) {
        exercises.add(exercise);
        Event addEvent = new Event("Exercise is added");
        EventLog.getInstance().logEvent(addEvent);
    }



    // MODIFIES : this
    // EFFECTS : removes an exercise from the exercise tracker list
    public boolean removeExercises(Exercise e) {
        if (!exercises.isEmpty()) {
            exercises.remove(e);
            Event removeEvent = new Event("Exercise is removed");
            EventLog.getInstance().logEvent(removeEvent);
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS : returns the list of the exercises
    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    // Method was taken from Json in:
    // https://github.students.cs.ubc.ca/CPSC210/
    // EFFECTS : returns the number of exercise planned by the user
    public int getSize() {
        return exercises.size();
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();


        json.put("Exercises", exercisesToJsonObject());
        return json;
    }

    // Method was taken from Json in:
    // https://github.students.cs.ubc.ca/CPSC210/
    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray exercisesToJsonObject() {
        JSONArray jsonArray = new JSONArray();
        for (Exercise exercise : exercises) {
            jsonArray.put(exercise.toJson());
        }
        return jsonArray;
    }

}
