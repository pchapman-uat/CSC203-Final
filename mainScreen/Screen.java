package mainScreen;

import javax.swing.*;

import Classes.Reminder;
import Classes.SQL;

import java.awt.FlowLayout;
import java.util.ArrayList;

import JPannels.ReminderPan;

public class Screen extends JFrame{
    int frameWidth = 400;
    int frameHeight = 400;

    ReminderPan remindPannel = new ReminderPan(frameHeight, frameWidth);

    JButton loadButton = new JButton();

    JButton saveButton = new JButton();

    public static Reminder currentReminder = null;

    private SQL sql = new SQL();

    public void load() {
        ArrayList<Reminder> reminders = this.sql.queryTable();
        Screen.currentReminder = reminders.get(0);
        this.remindPannel.setValues();
    }
    public void save() {
        Screen.currentReminder = this.remindPannel.createReminderObj();
        this.sql.updateItem();
    }
    public Screen(){
        this.setSize(this.frameWidth, this.frameWidth);
        this.setVisible(true);
        this.setLayout(new FlowLayout());
        this.loadButton.addActionListener(e -> this.load());
        this.loadButton.setText("Load");

        this.saveButton.setText("Save");
        this.saveButton.addActionListener(e -> this.save());
        this.add(this.saveButton);

        this.add(this.loadButton);
        this.add(this.remindPannel);
        this.update(null);

    }
}
