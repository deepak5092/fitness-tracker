package persistence;

import model.Exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Code was taken from Json in:
// https://github.students.cs.ubc.ca/CPSC210/
public class JsonTest {
    protected void checkThingy(String name, Exercise e) {
        assertEquals(name, e.getName());
    }
}
