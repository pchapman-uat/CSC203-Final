package mainScreen;

import javax.swing.*;

import Classes.Reminder;
import Classes.SQL;
import JPanels.PreviewPan;
import static Values.colors.*;

import java.awt.FlowLayout;
import java.util.ArrayList;

public class Screen extends JFrame{
    public static int frameWidth = 400;
    public static int frameHeight = 400;

    PreviewPan previewPan = new PreviewPan();
    JButton loadButton = new JButton();

    JButton saveButton = new JButton();

    JButton todayButton = new JButton();

    JButton addButton = new JButton();

    JButton nextButton = new JButton();
    
    public static ArrayList<Reminder> loadedReminders = new ArrayList<>();
    public static Integer currentIndex = 0;
    public static Reminder currentReminder = null;

    private SQL sql = new SQL();
    private void load(ArrayList<Reminder> reminders){
        if(reminders != null){
            Screen.loadedReminders = reminders;
            Screen.currentReminder = Screen.loadedReminders.get(Screen.currentIndex);
            this.previewPan.updatePreview();
        }
    }

    public void loadAll() {
        this.load(this.sql.queryTable());
    }
    public void loadToday() {
        this.load(this.sql.getTodayReminders());
    }
    public void save() {
        this.previewPan.updateReminder();
        this.sql.updateItem();
    }
    public void saveAs() {
        this.previewPan.updateReminder();
        this.sql.addItem();
    }
    public void next(){
        System.out.println("Next reminder");
        Screen.currentIndex++;
        if(Screen.currentIndex > Screen.loadedReminders.size() -1) Screen.currentIndex = 0;
        this.load(Screen.loadedReminders);
    }
    public void newNote(){
        Screen.loadedReminders = new ArrayList<Reminder>();

        Screen.currentReminder = new Reminder(-1, "<Title>", "<Content>", "0/0/0", WHITE);
        Screen.currentReminder.priorityString = "WHITE";
        Screen.currentReminder.date.todayDate();
        Screen.currentIndex = 0;
        Screen.loadedReminders.add(Screen.currentReminder);
        this.previewPan.updatePreview();
    }
    public Screen(){
        this.newNote();
        this.setSize(Screen.frameWidth, Screen.frameWidth);
        this.setVisible(true);
        this.setLayout(new FlowLayout());
        this.loadButton.addActionListener(e -> this.loadAll());
        this.loadButton.setText("Load");

        this.saveButton.setText("Save");
        this.saveButton.addActionListener(e -> this.save());

        this.todayButton.setText("Testing");
        this.todayButton.addActionListener(e -> this.loadToday());

        this.addButton.setText("Save As");
        this.addButton.addActionListener(e -> this.saveAs());

        this.nextButton.setText("Next...");
        this.nextButton.addActionListener(e -> this.next());
        this.add(this.nextButton);
        this.add(this.addButton);
        this.add(this.todayButton);
        this.add(this.saveButton);
        this.add(this.loadButton);
        this.add(this.previewPan);
        this.update(null);
        

    }
}
