package ui;

import model.Exercise;
import model.ExerciseTracker;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// user interface for the ExerciseTracker
public class WorkoutPlan {

    private static final String location = "./data/workroom.json";
    private ExerciseTracker exerciseTracker;
    private Exercise exercise;
    private Scanner userData;
    JsonWriter exerciseWriter = new JsonWriter(location);
    JsonReader exerciseReader = new JsonReader(location);

    public WorkoutPlan() {
        exerciseTracker = new ExerciseTracker();
        System.out.println("Welcome to the workout planner application");
        displayPlanenr();
    }

    // MODIFIES : this
    // EFFECTS : displays the welcome messages and starts the planner
    public void displayPlanenr() {
        System.out.println("To add an exercise, type add");
        System.out.println("To remove an exercise, type remove");
        System.out.println("To view the list of all the exercises, type view");
        System.out.println("To see the number of exercises, type total");
        System.out.println("To load the exercises, type load");
        System.out.println("To save the exercises, type save");
        userData = new Scanner(System.in);
        String action = userData.nextLine();
        actionTaker(action);
    }

    // EFFECTS : displays the different actions and runs different funcitons accordingly
    public void actionTaker(String action) {
        switch (action) {
            case "add":
                addExercise();
            case "remove":
                removeExercise();
            case "view":
                viewExercises();
            case "total":
                totalNumberOfExercises();
            case "save":
                saveExercises();
            case "load":
                loadExercises();
            default:
                System.out.println("Option is invalid, please try again");
        }
    }

    // EFFECTS: saves the workroom to file
    private void saveExercises() {
        try {
            exerciseWriter.open();
            exerciseWriter.write(exerciseTracker);
            exerciseWriter.close();
            System.out.println("Saved ");
        } catch (FileNotFoundException e) {
            System.out.println("Cannot be written on file");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadExercises() {
        try {
            exerciseTracker = exerciseReader.read();
            System.out.println("Loaded ");
        } catch (IOException e) {
            System.out.println("Cannot be read from the file");
        }
        displayPlanenr();
    }

    // EFFECTS : returns the total number of exercises planned by the user
    private void totalNumberOfExercises() {
        int totalexercises = 0;
        for (Exercise e : exerciseTracker.getExercises()) {
            totalexercises++;
        }
        System.out.println("Total exercises planned: " + totalexercises);
        displayPlanenr();
    }

    // EFFECTS : the function displays the information of the exercises in the list
    private void viewExercises() {
        if (!exerciseTracker.getExercises().isEmpty()) {
            for (Exercise e : exerciseTracker.getExercises()) {
                System.out.println("Name:" + e.getName() + " Reps:" + e.getReps() + " Sets:" + e.getSets() + " Pr:"
                        + e.getPr() + " lbs");
            }
        } else {
            System.out.println("No exercises done till now");
        }
        displayPlanenr();
    }

    // MODIFIES : this
    // EFFECTS : remove the exercise from the list
    private void removeExercise() {
        System.out.println("Enter the exercise which you want to remove from workout: ");
        String exer = userData.nextLine();
        Exercise removeEx = new Exercise(exer,0,0,0);
        Boolean remove = false;
        for (Exercise exercise : exerciseTracker.getExercises()) {
            if (exercise.getName().equals(exer)) {
                remove = true;
                removeEx = exercise;
            }
        }
        if (remove) {
            exerciseTracker.removeExercises(removeEx);
        } else {
            System.out.println("Exercise is not yet added");
        }
        displayPlanenr();
    }

    // MODIFIES : this
    // EFFECTS : add the exercise to the list
    private void addExercise() {
        System.out.println("Enter name of exercise: ");
        String exName = userData.nextLine();
        System.out.println("Enter number of sets: ");
        int numSets = userData.nextInt();
        System.out.println("Enter number of reps: ");
        int numReps = userData.nextInt();
        System.out.println("Enter personal record: ");
        int pr = userData.nextInt();
        Exercise exercise = new Exercise(exName,numReps,numSets,pr);
        exerciseTracker.addExercises(exercise);
        displayPlanenr();
    }
}
