package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

// tests the methods of the ExerciseTracker class
public class ExerciseTrackerTest {

    private Exercise bench;
    private Exercise bench1;
    private Exercise bench2;
    private Exercise squats;
    private Exercise deadlift;
    private ExerciseTracker exercises;

    @BeforeEach
    public void initialiser() {
        bench = new Exercise("Bench press", 30, 3, 45);
        bench1 = new Exercise("Bench press", 12, 2, 75);
        bench2 = new Exercise("Bench press", 12, 6, 100);
        squats = new Exercise("Squats", 40, 4, 100);
        deadlift = new Exercise("Deadlift", 40, 3, 150);
        exercises = new ExerciseTracker();
    }

    @Test
    public void testRemoveExercies() {
        assertFalse(exercises.removeExercises(squats));
        exercises.addExercises(bench);
        exercises.addExercises(squats);
        exercises.removeExercises(bench);
        assertEquals(1, exercises.getSize());

    }

    @Test
    public void addDifferentExercises() {
        exercises.addExercises(bench);
        exercises.addExercises(squats);
        exercises.addExercises(deadlift);
        assertEquals(3, exercises.getSize());
    }

    @Test
    public void testGetSize() {
        exercises.addExercises(bench);
        exercises.addExercises(squats);
        assertEquals(2, exercises.getSize());
    }

    @Test
    public void testGetExercises() {
        exercises.addExercises(bench);
        exercises.addExercises(squats);
        exercises.getExercises();
        assertEquals(2, exercises.getSize());
    }
}
