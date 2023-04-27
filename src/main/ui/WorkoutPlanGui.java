package ui;

import model.Event;
import model.EventLog;
import model.Exercise;
import model.ExerciseTracker;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkoutPlanGui implements ActionListener {

    private static final int width = 500;
    private static final int length = 500;
    private ExerciseTracker exerciseTracker;

    private JFrame workoutMainDisplay;
    private JInternalFrame jinternalFrame;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private static final String jsonLocation = "./data/workroom.json";

    private JTextField exerciseText;
    private JTextField setsText;
    private JTextField repsText;
    private JTextField prText;


    // EFFECTS : constructs the main workout display frame
    public WorkoutPlanGui() {
        exerciseTracker = new ExerciseTracker();
        jsonReader = new JsonReader(jsonLocation);
        jsonWriter = new JsonWriter(jsonLocation);
        setsText = new JTextField();
        exerciseText = new JTextField();
        repsText = new JTextField();
        prText = new JTextField();

        jinternalFrame = new JInternalFrame();
        jinternalFrame.setLayout(new BorderLayout());

        workoutMainDisplay = new JFrame();
        workoutMainDisplay.setSize(width, length);
        workoutMainDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        workoutMainDisplay.setTitle("Exercises Tracker");

        functionButtons();
        loadSaveMenu();

        workoutMainDisplay.add(jinternalFrame);
        workoutMainDisplay.setContentPane(jinternalFrame);
        workoutMainDisplay.setVisible(true);
        jinternalFrame.setVisible(true);
        workoutMainDisplay.pack();
        eventsPrinterOnConsole();
    }

    // EFFECTS : constructs load and save menu buttons on the menu bar
    private void loadSaveMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu load = new JMenu("Load");
        load.setActionCommand("Load");
        load.addActionListener(this);

        JMenu save = new JMenu("Save");
        save.setActionCommand("Save");
        save.addActionListener(this);

        menuBar.add(load);
        menuBar.add(save);
        workoutMainDisplay.setJMenuBar(menuBar);

    }

    // EFFECTS : constructs a grid for the different functions
    private void functionButtons() {
        JPanel buttonPanel = new JPanel();
        GridLayout layout = new GridLayout(2, 1);
        buttonPanel.setLayout(layout);

        JButton addExerciseButton = new JButton("Add/Remove Exercises");
        JButton viewExercise = new JButton("View Exercise");

        addExerciseButton.setActionCommand("Add/Remove Exercises");
        addExerciseButton.addActionListener(this);
        viewExercise.setActionCommand("View Exercise");
        viewExercise.addActionListener(this);

        buttonPanel.add(addExerciseButton);
        buttonPanel.add(viewExercise);
        jinternalFrame.add(buttonPanel, BorderLayout.NORTH);

    }

    // EFFECTS : this function gives command to the implement specific operation
    //           with each button
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Add/Remove Exercises")) {
            exerciseDataFrame();
        } else if (e.getActionCommand().equals("Add an exercise")) {
            addExercise();
        } else if (e.getActionCommand().equals("Remove exercise")) {
            removeExercise();
        } else if (e.getActionCommand().equals("Save")) {
            saveExercise();
        } else if (e.getActionCommand().equals("Load")) {
            loadExercise();
        } else if (e.getActionCommand().equals("View Exercise")) {
            viewExercises();
        }
    }

    // EFFECTS : display the list of exercises
    private void viewExercises() {
        List<String> displayExercises = new ArrayList<>();

        workoutMainDisplay = new JFrame();
        JPanel viewPanel = new JPanel();

        if (exerciseTracker.getExercises().size() != 0) {
            for (Exercise e : exerciseTracker.getExercises()) {
                String view = "Exercise name: " + e.getName() + "REPS: " + e.getReps() + "SETS: " + e.getSets()
                        + "PR: " + e.getPr();
                displayExercises.add(view);
            }
        }

        JList<Object> listOfExercises = new JList<>(displayExercises.toArray());
        viewPanel.add(listOfExercises);
        workoutMainDisplay.add(viewPanel);
        ImageIcon img = new ImageIcon("gym.jpeg");
        JLabel pic = new JLabel(img);
        viewPanel.add(pic);
        workoutMainDisplay.pack();
        workoutMainDisplay.setVisible(true);
    }

    // REQUIRES : exercise should be present in the list
    // EFFECTS : remove an exercise from the list of exercises
    public void removeExercise() {
        int sets = Integer.parseInt(setsText.getText());
        int reps = Integer.parseInt(repsText.getText());
        int pr = Integer.parseInt(prText.getText());
        Exercise e = new Exercise(exerciseText.getText(), reps, sets, pr);

        if (exerciseTracker.getExercises() != null) {
            for (Exercise exercise : exerciseTracker.getExercises()) {
                if (exercise.getName().equals(exerciseText.getText())) {
                    exerciseTracker.removeExercises(exercise);
                }
            }
        }
    }

    // EFFECTS : saves the exercises
    private void saveExercise() {
        try {
            jsonWriter.open();

            jsonWriter.write(exerciseTracker);
            jsonWriter.close();

            System.out.println("Exercises are saved");
        } catch (FileNotFoundException f) {
            System.out.println("There is an exception");
        }
    }

    // EFFECTS : loads the exercises
    private void loadExercise() {

        try {

            exerciseTracker = jsonReader.read();
            System.out.println("Exercises are loaded");

        } catch (IOException ioException) {

            System.out.println("There is an exception");
        }
    }

    // EFFECTS : adds exercises to the list of exercises with sets, pr, reps and name
    private void addExercise() {
        int sets = Integer.parseInt(setsText.getText());
        int reps = Integer.parseInt(repsText.getText());
        int pr = Integer.parseInt(prText.getText());
        Exercise e = new Exercise(exerciseText.getText(), reps, sets, pr);
        exerciseTracker.addExercises(e);
    }

    // EFFECTS : creates an internal frame to add or remove an exercise
    private void exerciseDataFrame() {
        jinternalFrame = new JInternalFrame();
        jinternalFrame.setSize(20, 20);
        jinternalFrame.setLayout(new GridLayout(5, 2, 5, 5));
        internalFrameCreator();

        workoutMainDisplay.add(jinternalFrame);
        jinternalFrame.setVisible(true);
        workoutMainDisplay.setVisible(true);
    }

    // EFFECTS : prints the instances once the application is ended
    public void eventsPrinterOnConsole() {
        workoutMainDisplay.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event toBePrinted : EventLog.getInstance()) {
                    System.out.println(toBePrinted.toString());
                }
            }
        });
    }

    // EFFECTS : creates labels and text fields for the internal frame
    private void internalFrameCreator() {
        exerciseText = new JTextField();
        JLabel exName = new JLabel("Exercise Name");
        setsText = new JTextField();
        repsText = new JTextField();
        prText = new JTextField();
        JLabel reps = new JLabel("REPS");
        JLabel sets = new JLabel("SETS");
        JLabel pr = new JLabel("PR");
        jinternalFrame.add(exName);
        jinternalFrame.add(exerciseText);
        jinternalFrame.add(reps);
        jinternalFrame.add(repsText);
        jinternalFrame.add(sets);
        jinternalFrame.add(setsText);
        jinternalFrame.add(pr);
        jinternalFrame.add(prText);
        internalFrameButtons();
    }

    // EFFECTS : creates internal frame buttons
    private void internalFrameButtons() {
        JButton addEx = new JButton("Add an exercise");
        addEx.setActionCommand("Add an exercise");
        addEx.addActionListener(this);
        JButton exit = new JButton("Remove exercise");
        exit.setActionCommand("Remove exercise");
        exit.addActionListener(this);
        jinternalFrame.add(addEx);
        jinternalFrame.add(exit);
    }

}



