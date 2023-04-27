package persistence;

import model.Exercise;
import model.ExerciseTracker;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Code was taken from Json in:
// https://github.students.cs.ubc.ca/CPSC210/
class JsonReaderTest extends JsonTest {


    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ExerciseTracker et = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            ExerciseTracker wr = reader.read();
            assertEquals(0, wr.getExercises().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            ExerciseTracker wr = reader.read();
            List<Exercise> thingies = wr.getExercises();
            assertEquals(2, thingies.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}