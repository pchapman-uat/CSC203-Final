package Classes;

import java.awt.Color;
import static Values.colors.*;
public class Reminder {
    public int id;
    public String title;
    public String content;
    public Date date;
    public Color priority;
    public String priorityString;
    public Reminder(int id, String title, String content, String date, Color priority) {
        if(priority == null) {
            this.priority = WHITE;
            this.priorityString = "WHITE";
        } else {
            this.priority = priority;
        }
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = new Date();
        this.date.stringDate(date);
        this.priority = priority;

        
    }
    public void setPriorityString(String priorityString) {
        this.priorityString = priorityString;
    }
}
