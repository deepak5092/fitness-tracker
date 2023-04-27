package persistence;

import org.json.JSONObject;

// Code was taken from Json in:
// https://github.students.cs.ubc.ca/CPSC210/
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}