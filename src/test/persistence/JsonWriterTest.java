package persistence;

import model.Exercise;
import model.ExerciseTracker;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Code was taken from Json in:
// https://github.students.cs.ubc.ca/CPSC210/
class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            ExerciseTracker et = new ExerciseTracker();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ExerciseTracker et = new ExerciseTracker();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(et);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            et = reader.read();
            assertEquals(0, et.getExercises().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ExerciseTracker et = new ExerciseTracker();
            et.addExercises(new Exercise("bench", 10, 2, 80));
            et.addExercises(new Exercise("squats", 10, 2, 80));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(et);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            et = reader.read();
            //assertEquals("My work room", et.getName());
            List<Exercise> thingies = et.getExercises();
            assertEquals(2, thingies.size());
//            checkThingy("saw", Category.METALWORK, thingies.get(0));
//            checkThingy("needle", Category.STITCHING, thingies.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}