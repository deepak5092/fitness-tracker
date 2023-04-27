package model;

import org.json.JSONObject;
import persistence.Writable;

// the class creates Exercise object and performs various fucntions
public class Exercise implements Writable {

    private int reps;
    private int sets;
    private String name;
    private int pr;

    // EFFECTS : constructs an exercise with name, sets, reps and personal record
    public Exercise(String name, int reps, int sets, int pr) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.pr = pr;
    }

    //EFFECTS : returns the name of the exercise
    public String getName() {
        return name;
    }

    //EFFECTS : returns the number of reps of the exercise
    public int getReps() {
        return reps;
    }

    //EFFECTS : returns the number of sets of the exercise
    public int getSets() {
        return sets;
    }

    //EFFECTS : returns the personal record of the exercise
    public int getPr() {
        return pr;
    }

    // Method was taken from Json in:
    // https://github.students.cs.ubc.ca/CPSC210/
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("reps", reps);
        json.put("sets", sets);
        json.put("pr", pr);
        return json;
    }
}

