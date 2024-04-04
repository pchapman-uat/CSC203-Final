
import javax.swing.*;

import Classes.Reminder;

import java.awt.FlowLayout;
import java.util.ArrayList;

import JPannels.Date;
import JPannels.ReminderPan;

public class Screen extends JFrame{
    int frameWidth = 400;
    int frameHeight = 400;

    Date datePannel = new Date(this.frameHeight, this.frameWidth);
    ReminderPan remindPannel = new ReminderPan(frameHeight, frameWidth);

    JButton loadButton = new JButton();

    JButton saveButton = new JButton();

    Reminder currentReminder = null;
    SQL sql = null;
    public void load() {
        ArrayList<Reminder> test = this.sql.queryTable();
        this.remindPannel.setTitle(test.get(0).title);
        this.remindPannel.setContent(test.get(0).content);
        this.remindPannel.setDate(test.get(0).date.dateString);
        this.remindPannel.setID(test.get(0).id);
        this.currentReminder = test.get(0);
    }
    public void save() {
        this.currentReminder = this.remindPannel.createReminderObj();
        this.sql.updateItem(this.currentReminder);
    }
    Screen(){
        this.sql = new SQL();
        this.setSize(this.frameWidth, this.frameWidth);
        this.setVisible(true);
        this.setLayout(new FlowLayout());
        this.loadButton.addActionListener(e -> this.load());
        this.loadButton.setText("Load");

        this.saveButton.setText("Save");
        this.saveButton.addActionListener(e -> this.save());
        this.add(this.saveButton);

        this.add(this.loadButton);
        this.add(this.datePannel);
        this.add(this.remindPannel);
        this.update(null);

    }
}
