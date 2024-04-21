// Package with the classes folder
package Classes;
// Import the java color class
import java.awt.Color;
// Import the custom Color class
import static Values.colors.*;
public class Reminder {
    // Declare the id, title, content, date, and priority
    public int id;
    public String title;
    public String content;
    public Date date;
    public Color priority;
    public String priorityString;
    // Constructor for the reminder
    public Reminder(int id, String title, String content, String date, Color priority) {
        // If the priority is null, set it to white
        if(priority == null) {
            this.priority = WHITE;
            this.priorityString = "WHITE";
        } else {
            this.priority = priority;
        }
        // Set the values
        this.id = id;
        this.title = title;
        this.content = content;
        // Use the date class to parse the date
        this.date = new Date();
        this.date.stringDate(date);
        this.priority = priority;

        
    }
    // Set the priority String
    public void setPriorityString(String priorityString) {
        this.priorityString = priorityString;
    }
}
