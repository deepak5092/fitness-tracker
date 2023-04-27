package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// tests the Exercise class methods
class ExerciseTest {
    private Exercise bench;
    private Exercise squats;

    @BeforeEach
    public void initialiser() {
     bench = new Exercise("Bench press", 30, 3, 45);
     squats = new Exercise("Squats", 40, 4, 100);
    }

    @Test
    public void testGetterFunctions() {
        assertEquals("Bench press", bench.getName());
        assertEquals(30, bench.getReps());
        assertEquals(3, bench.getSets());
        assertEquals(45, bench.getPr());

    }
}