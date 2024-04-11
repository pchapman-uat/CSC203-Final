package mainScreen;

import javax.swing.*;

import Classes.Reminder;
import Classes.SQL;
import JPanels.PreviewPan;
import JPanels.ReminderPan;

import java.awt.FlowLayout;
import java.util.ArrayList;

public class Screen extends JFrame{
    int frameWidth = 400;
    int frameHeight = 400;

    ReminderPan remindPannel = new ReminderPan(frameHeight, frameWidth);

    PreviewPan previewPan = new PreviewPan();
    JButton loadButton = new JButton();

    JButton saveButton = new JButton();

    JButton updateButton = new JButton();

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
            this.remindPannel.setValues();
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
        this.sql.updateItem();
    }
    public void saveAs() {
        this.sql.addItem();
    }
    public void update(){
        System.out.println("Upading Object...");
        if(Screen.loadedReminders.size() == 0){
            Screen.loadedReminders.add(new Reminder(1,"dummy","dummy","04/22/2024"));
            Screen.currentReminder = loadedReminders.get(0);
        }
        this.remindPannel.datePannel.updateDate();
        this.remindPannel.setValues();
        this.previewPan.updatePreview();
    }
    public void next(){
        System.out.println("Next reminder");
        Screen.currentIndex++;
        if(Screen.currentIndex > Screen.loadedReminders.size() -1) Screen.currentIndex = 0;
        this.load(Screen.loadedReminders);
    }
    public Screen(){
        this.setSize(this.frameWidth, this.frameWidth);
        this.setVisible(true);
        this.setLayout(new FlowLayout());
        this.loadButton.addActionListener(e -> this.loadAll());
        this.loadButton.setText("Load");

        this.saveButton.setText("Save");
        this.saveButton.addActionListener(e -> this.save());

        this.updateButton.setText("Preview");
        this.updateButton.addActionListener(e -> this.update());

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
        this.add(this.updateButton);
        this.add(this.remindPannel);
        this.add(this.previewPan);
        this.update(null);

    }
}
