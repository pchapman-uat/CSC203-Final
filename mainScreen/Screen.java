package mainScreen;

import javax.swing.*;

import Classes.Reminder;
import Classes.SQL;

import java.awt.FlowLayout;
import java.util.ArrayList;

import JPannels.PreviewPan;
import JPannels.ReminderPan;

public class Screen extends JFrame{
    int frameWidth = 400;
    int frameHeight = 400;

    ReminderPan remindPannel = new ReminderPan(frameHeight, frameWidth);

    PreviewPan previewPan = new PreviewPan();
    JButton loadButton = new JButton();

    JButton saveButton = new JButton();

    JButton updateButton = new JButton();

    public static Reminder currentReminder = null;

    private SQL sql = new SQL();

    public void load() {
        ArrayList<Reminder> reminders = this.sql.queryTable();
        Screen.currentReminder = reminders.get(0);
        this.remindPannel.setValues();
        this.previewPan.updatePreview();
    }
    public void save() {
        this.sql.updateItem();
    }
    public void update(){
        System.out.println("Upading Object...");
        this.remindPannel.datePannel.updateDate();
        this.remindPannel.setValues();
        this.previewPan.updatePreview();
    }
    public Screen(){
        this.setSize(this.frameWidth, this.frameWidth);
        this.setVisible(true);
        this.setLayout(new FlowLayout());
        this.loadButton.addActionListener(e -> this.load());
        this.loadButton.setText("Load");

        this.saveButton.setText("Save");
        this.saveButton.addActionListener(e -> this.save());

        this.updateButton.setText("Preview");
        this.updateButton.addActionListener(e -> this.update());

        this.add(this.saveButton);
        this.add(this.loadButton);
        this.add(this.updateButton);
        this.add(this.remindPannel);
        this.add(this.previewPan);
        this.update(null);

    }
}
