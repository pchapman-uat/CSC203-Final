// Package with teh mainScreen folder
package src.mainScreen;

// Import all JavaX swing classes
import javax.swing.*;

import src.Classes.Reminder;
import src.Classes.SQL;
import src.JPanels.PreviewPan;

import static src.Values.colors.*;

// Import the flow layout
import java.awt.FlowLayout;
// Import the array list class
import java.util.ArrayList;

// Screen class extends the JFrame class
public class Screen extends JFrame{
    // Declare the frame width and height
    public static int frameWidth = 400;
    public static int frameHeight = 400;

    // Declare the Preview Panel
    PreviewPan previewPan = new PreviewPan();
    // Declare the buttons
    JButton loadButton = new JButton();

    JButton saveButton = new JButton();

    JButton todayButton = new JButton();

    JButton addButton = new JButton();

    JButton nextButton = new JButton();
    // Create an array list of loaded reminders
    public static ArrayList<Reminder> loadedReminders = new ArrayList<>();
    // Set the current index of the reminders to 0
    public static Integer currentIndex = 0;
    // Declare the current reminder, this is set to null as none are loaded
    public static Reminder currentReminder = null;

    // Create a new SQL object
    private SQL sql = new SQL();
    // Load the reminders
    private void load(ArrayList<Reminder> reminders){
        // If the reminders are not null
        if(reminders != null){
            // Set the loaded reminders to the reminders
            Screen.loadedReminders = reminders;
            // Set the current reminder to the current index
            Screen.currentReminder = Screen.loadedReminders.get(Screen.currentIndex);
            // Update the preview panel
            this.previewPan.updatePreview();
        }
    }

    // Load all reminders using teh SQL class query table method
    public void loadAll() {
        this.load(this.sql.queryTable());
    }
    // Load the reminders for today using the SQL class get today reminders method
    public void loadToday() {
        this.load(this.sql.getTodayReminders());
    }
    // Update the current reminder object, and save the currently opened reminder using the SQL class update item method
    public void save() {
        this.previewPan.updateReminder();
        this.sql.updateItem();
    }
    // Update the current reminder object, and append the currently opened reminder using the SQL class add item method
    public void saveAs() {
        this.previewPan.updateReminder();
        this.sql.addItem();
    }
    // Get the next reminder by increasing the index, looping around if greater than the size of the loaded reminders
    public void next(){
        System.out.println("Next reminder");
        Screen.currentIndex++;
        if(Screen.currentIndex > Screen.loadedReminders.size() -1) Screen.currentIndex = 0;
        this.load(Screen.loadedReminders);
    }
    // Create a default note
    public void newNote(){
        // Set the loaded reminders to an empty array list
        // This is done to clear any previous reminders
        Screen.loadedReminders = new ArrayList<Reminder>();

        // Create a new reminder object using the constructor
        Screen.currentReminder = new Reminder(-1, "<Title>", "<Content>", "0/0/0", WHITE);
        // Set its priority to white
        Screen.currentReminder.priorityString = "WHITE";
        // Set its date to today
        Screen.currentReminder.date.todayDate();
        // Set the index to 0
        Screen.currentIndex = 0;
        // Add the reminder to the array list
        Screen.loadedReminders.add(Screen.currentReminder);
        // Update the preview panel
        this.previewPan.updatePreview();
    }
    // Constructor for the main screen
    public Screen(){
        // Run the new note method
        this.newNote();
        // Set the size of the frame
        this.setSize(Screen.frameWidth, Screen.frameWidth);
        // Set the frame to visable
        this.setVisible(true);
        // Set the layout to flow layout
        this.setLayout(new FlowLayout());
        // Add an action listener to the load button
        this.loadButton.addActionListener(e -> this.loadAll());
        // Set the load button text
        this.loadButton.setText("Load");

        // Do the same for the remaining buttons...
        this.saveButton.setText("Save");
        this.saveButton.addActionListener(e -> this.save());

        this.todayButton.setText("Today");
        this.todayButton.addActionListener(e -> this.loadToday());

        this.addButton.setText("Save As");
        this.addButton.addActionListener(e -> this.saveAs());

        this.nextButton.setText("Next...");
        this.nextButton.addActionListener(e -> this.next());

        // Add the buttons to the frame
        this.add(this.nextButton);
        this.add(this.addButton);
        this.add(this.todayButton);
        this.add(this.saveButton);
        this.add(this.loadButton);
        this.add(this.previewPan);
        // Update the frame
        this.update(null);
        

    }
}
