
import javax.swing.*;
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

    SQL sql = null;
    public void testing() {
        ArrayList<Reminder> test = this.sql.queryTable();
        this.remindPannel.setTitle(test.get(0).title);
        this.remindPannel.setContent(test.get(0).content);
    }
    Screen(){
        this.sql = new SQL();

        this.setSize(frameWidth, frameHeight);
        this.setVisible(true);
        this.setLayout(new FlowLayout());
        this.loadButton.addActionListener(e -> this.testing());
        this.loadButton.setText("Load");
        this.add(this.loadButton);
        this.add(this.datePannel);
        this.add(this.remindPannel);
        this.update(null);

    }
}
